package org.avanti.service;

import org.avanti.entity.Vehicle;
import org.avanti.exception.BadRequestException;
import org.avanti.exception.VehicleNotFoundException;
import org.avanti.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository repository;

    @Override
    public List<Vehicle> findAll() {
        return (List<Vehicle>) repository.findAll();
    }

    @Override
    public Vehicle findOne(String vin) {
        Optional<Vehicle> vehicle = repository.findById(vin);
        if(!vehicle.isPresent()){
            throw new VehicleNotFoundException("Vehicle not found "+vin);
        }
        return vehicle.get();
    }

    @Override
    @Transactional
    public Vehicle create(Vehicle vehicle) {
        Optional<Vehicle> existing = repository.findById(vehicle.getVin());
        if(existing.isPresent()){
            throw new BadRequestException("Vehicle already present "+vehicle.getVin());
        }
        return repository.save(vehicle) ;
    }

    @Override
    @Transactional
    public void update(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
               repository.save(v);
        }
    }

}
