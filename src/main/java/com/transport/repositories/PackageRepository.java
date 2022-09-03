package com.transport.repositories;

import com.transport.models.Drone;
import com.transport.models.Package;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends CrudRepository<Package, Integer> {

    @Query(value = "SELECT * FROM PACKAGE WHERE name = ?1 and code = ?2", nativeQuery = true)
    Package findByNameAndCode(String name, String code);

    @Query(value = "SELECT * FROM PACKAGE WHERE drone_serial_number = ?1", nativeQuery = true)
    List<Package> findBySerialNumber(String serialNumber);
}
