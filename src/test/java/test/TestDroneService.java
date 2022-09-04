package test;

import com.transport.exceptions.BusinessException;
import com.transport.exceptions.ResourceNotFoundException;
import com.transport.models.Drone;
import com.transport.models.DroneHealth;
import com.transport.repositories.DroneRepository;
import com.transport.services.DroneService;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestDroneService {

    @InjectMocks
    DroneService droneService;

    @Mock
    DroneRepository droneRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateDroneSuccessfully() {

        Drone drone = new Drone();
        drone.setDroneModel("model1");
        drone.setSerialNumber("serial1");
        drone.setWeight(200);
        drone.setDroneState("IDLE");
        drone.setDroneModel("LightWeight");
        try {
            Mockito.when(droneRepository.findBySerialId(drone.getSerialNumber())).thenReturn(null);
            Mockito.when(droneRepository.save(drone)).thenReturn(drone);
            Drone createdDrone = droneService.createDrone(drone);
            org.junit.Assert.assertNotNull("Object should not be null", createdDrone);
        } catch (BusinessException e) {
            Assert.fail("Should successfully create drone");
        }
    }

    @Test
    public void testCreateDroneExistingSerialID() {

        Drone drone = new Drone();
        drone.setDroneModel("model1");
        drone.setSerialNumber("serial1");
        drone.setWeight(200);
        drone.setDroneState("IDLE");
        drone.setDroneModel("LightWeight");
        try {
            Mockito.when(droneRepository.findBySerialId(drone.getSerialNumber())).thenReturn(drone);
            droneService.createDrone(drone);
            Assert.fail("Should not create drone successfully");
        } catch (BusinessException e) {
            Assert.assertEquals( "Cannot create drone. Found for the serial number: serial1", e.getMessage());
        }
    }

    @Test
    public void testGetDroneHealthSuccessfully() {

        Drone drone = new Drone();
        drone.setDroneModel("model1");
        drone.setSerialNumber("serial1");
        drone.setWeight(200);
        drone.setBatteryCapacity(100);
        drone.setDroneState("IDLE");
        drone.setDroneModel("LightWeight");
        try {
            Mockito.when(droneRepository.findBySerialId(drone.getSerialNumber())).thenReturn(drone);
            DroneHealth health = droneService.getDroneHealth("serial1");
            Assert.assertEquals(health.getHealth().intValue(), 100);
        } catch (ResourceNotFoundException e) {
            Assert.fail("Should successfully create drone");
        }
    }

    @Test
    public void testGetDroneHealthDroneDoesntExist() {

        Drone drone = new Drone();
        drone.setDroneModel("model1");
        drone.setSerialNumber("serial1");
        drone.setWeight(200);
        drone.setBatteryCapacity(100);
        drone.setDroneState("IDLE");
        drone.setDroneModel("LightWeight");
        try {
            Mockito.when(droneRepository.findBySerialId(drone.getSerialNumber())).thenReturn(null);
            droneService.getDroneHealth("serial1");
            Assert.fail("Drone doesn't exist");
        } catch (ResourceNotFoundException e) {
            Assert.assertEquals("No drone found for the serial number: serial1", e.getMessage());
        }
    }

}
