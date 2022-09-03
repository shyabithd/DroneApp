package com.transport.repositories;

import com.transport.models.Drone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends CrudRepository<Drone, Integer> {

    @Query(value = "SELECT * FROM DRONE WHERE serial_number = ?1", nativeQuery = true)
    Drone findBySerialId(String serialNumber);

    @Query(value = "SELECT * FROM DRONE WHERE drone_state = ?1", nativeQuery = true)
    List<Drone> findByState(String state);

    @Query(value = "SELECT COUNT(*) FROM DRONE", nativeQuery = true)
    Integer getCount();
}
