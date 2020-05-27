package org.avanti.service;

import org.avanti.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();

    Vehicle findOne(String vin);

    Vehicle create(Vehicle vehicle);

    Vehicle put(Vehicle vehicle);
}
