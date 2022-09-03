package com.transport.services;

import com.transport.models.Drone;
import com.transport.repositories.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    @Autowired
    private DroneRepository droneRepository;
    
    Logger logger = LoggerFactory.getLogger(AuditService.class);

    @Scheduled(fixedDelay = 20000)
    private void scheduleFixedDelayTask() {

        Iterable<Drone> iterator = droneRepository.findAll();
        for (Drone drone : iterator) {
            logger.info("Drone Serial Number: " + drone.getSerialNumber() + " Drone Battery Capacity: " + drone.getBatteryCapacity());
        }
    }
}
