package com.transport.services;

import com.transport.exceptions.BusinessException;
import com.transport.exceptions.ResourceNotFoundException;
import com.transport.models.Drone;
import com.transport.models.DroneHealth;
import com.transport.models.Item;
import com.transport.models.Package;
import com.transport.repositories.DroneRepository;
import com.transport.repositories.PackageRepository;
import com.transport.utils.DateTimeService;
import com.transport.utils.DroneState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private DateTimeService dateTimeService;

    @Value("${battery_low_limit}")
    private int battery_low_limit;

    @Value("${drone_count}")
    private int maxDronecount;

    public Drone createDrone(Drone drone) throws BusinessException {

        if (!drone.getDroneState().equals(DroneState.IDLE.toString())) {
            throw new BusinessException("Cannot create drone. Invalid state received: " + drone.getDroneState());
        }

        Drone existingDrone = droneRepository.findBySerialId(drone.getSerialNumber());
        if (existingDrone != null) {
            throw new BusinessException("Cannot create drone. Found for the serial number: " + drone.getSerialNumber());
        }

        Integer createdDroneCount = droneRepository.getCount();
        if (maxDronecount < createdDroneCount) {
            throw new BusinessException("Max drone count exceeded: " + maxDronecount);
        }

        return droneRepository.save(drone);
    }

    public Drone updateDrone(String serialNumber, List<Package> aPackages)
            throws ResourceNotFoundException, BusinessException {

        Drone drone = droneRepository.findBySerialId(serialNumber);
        if (drone == null) {
            throw new ResourceNotFoundException("No drone found for the serial number: " + serialNumber);
        }

        if (drone.getBatteryCapacity() <= battery_low_limit) {
            throw new BusinessException("Drone: " + serialNumber + " battery level is low: " + drone.getBatteryCapacity());
        }

        int sum = aPackages.stream().mapToInt(Package::getWeight).sum();
        if (sum  > drone.getWeight()) {
            throw new BusinessException("Package weight: " + drone.getWeight() +
                    " is greater than drone max carry weight: " + sum + " Drone: " + serialNumber);
        }

        if (!drone.getDroneState().equals(DroneState.IDLE.toString())) {
            throw new BusinessException("Drone state is: " + drone.getDroneState());
        }

        for (Package aPackage : aPackages) {
            if (packageRepository.findByNameAndCode(aPackage.getName(), aPackage.getCode()) != null) {
                throw new BusinessException("Package has already been dispatched: Name: " + aPackage.getName() + " Code: " + aPackage.getCode());
            }
        }

        String timestamp = dateTimeService.getUniqueTimestamp().toString();
        drone.setDroneState(DroneState.LOADING.toString());
        drone.setWeight(drone.getWeight() - sum);
        droneRepository.save(drone);

        aPackages.forEach(aPackage -> {
            aPackage.setDrone(drone);
            aPackage.setTimestamp(timestamp);
            packageRepository.save(aPackage);
        });
        drone.setDroneState(DroneState.LOADED.toString());
        droneRepository.save(drone);

        return drone;
    }

    public List<Item> getItems(String serialNumber) throws ResourceNotFoundException {
        Drone drone = droneRepository.findBySerialId(serialNumber);
        if (drone == null) {
            throw new ResourceNotFoundException("No drone found for the serial number: " + serialNumber);
        }

        List<Package> packageList =  drone.getDroneState().equals(DroneState.LOADED.toString()) ?
                packageRepository.findBySerialNumber(serialNumber) : new ArrayList<>();

        return packageList.stream()
                .map(aPackage -> {
                    Item item = new Item();
                    item.setName(aPackage.getName());
                    item.setCode(aPackage.getCode());
                    item.setWeight(aPackage.getWeight());
                    item.setImage(aPackage.getImgName());
                    return item;
                }).collect(Collectors.toList());
    }

    public DroneHealth getDroneHealth(String serialNumber) throws ResourceNotFoundException {
        Drone drone = droneRepository.findBySerialId(serialNumber);
        if (drone == null) {
            throw new ResourceNotFoundException("No drone found for the serial number: " + serialNumber);
        }

        DroneHealth droneHealth = new DroneHealth();
        droneHealth.setHealth(drone.getBatteryCapacity());
        return droneHealth;
    }

    public List<Drone> getDronesByState(String state) throws ResourceNotFoundException {
        List<Drone> droneList = droneRepository.findByState(state);
        if (droneList == null) {
            throw new ResourceNotFoundException("No drone found for the state: " + state);
        }
        return droneList;
    }

}
