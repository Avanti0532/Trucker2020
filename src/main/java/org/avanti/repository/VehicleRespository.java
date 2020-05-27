package org.avanti.repository;

import org.avanti.entity.Vehicle;

import java.util.List;

public interface VehicleRespository {
    List<Vehicle> findAll();
    Vehicle findOne(String vin);
    Vehicle create(Vehicle vehicle);
    Vehicle put(String id, Vehicle vehicle);
}
