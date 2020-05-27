package org.avanti.repository;

import org.avanti.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleRepositoryImpl implements VehicleRespository{
    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public Vehicle findOne(String vin) {
        return null;
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle put(String id, Vehicle vehicle) {
        return null;
    }
}
