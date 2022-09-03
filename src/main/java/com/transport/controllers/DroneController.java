package com.transport.controllers;

import com.transport.exceptions.BusinessException;
import com.transport.exceptions.ResourceNotFoundException;
import com.transport.models.Drone;
import com.transport.models.DroneHealth;
import com.transport.models.Item;
import com.transport.models.Package;
import com.transport.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@RestController
public class DroneController {

    @Autowired
    DroneService droneService;

    @RequestMapping(value = "/drones/{serialNumber}/battery-capacity", method = RequestMethod.GET)
    ResponseEntity<Object> getDroneHealth(@PathVariable("serialNumber") String serialNumber) throws ResourceNotFoundException {
        DroneHealth health = droneService.getDroneHealth(serialNumber);
        return new ResponseEntity<>(health, HttpStatus.OK);
    }

    @RequestMapping(value = "/drones", method = RequestMethod.GET)
    ResponseEntity<List<Drone>> getDronesbyState(    @Pattern(regexp = "^(IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING)$", message = "Invalid drone state specified")
                                                     @RequestParam String state) throws ResourceNotFoundException {
        List<Drone> list = droneService.getDronesByState(state);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/drones/{serialNumber}/packages", method = RequestMethod.GET)
    ResponseEntity<Object> getDroneItems(@PathVariable("serialNumber") String serialNumber) throws ResourceNotFoundException {
        List<Item> items = droneService.getItems(serialNumber);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/drones/{serialNumber}/packages", method = RequestMethod.PUT)
    ResponseEntity<Object> updateDrone(@PathVariable("serialNumber") String serialNumber, @RequestBody List<Package> aPackages) throws ResourceNotFoundException, BusinessException {
        Drone drone = droneService.updateDrone(serialNumber, aPackages);
        return new ResponseEntity<>( drone, HttpStatus.OK);
    }

    @RequestMapping(value = "/drones", method = RequestMethod.POST)
    ResponseEntity<Object> registerDrone(@RequestBody Drone drone) throws BusinessException {
        Drone createdDrone = droneService.createDrone(drone);
        return new ResponseEntity<>(createdDrone, HttpStatus.CREATED);
    }

}
