package org.avanti.service;

import org.avanti.entity.Vehicle;
import org.avanti.exception.BadRequestException;
import org.avanti.exception.VehicleNotFoundException;
import org.avanti.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
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
    public Vehicle put(String id, Vehicle vehicle) {
        Optional<Vehicle> existing = repository.findById(id);
        Vehicle updateVehicle = existing.get();
        if(!existing.isPresent()){
            updateVehicle.setVin(vehicle.getVin());
        }
        updateVehicle.setLastServiceDate(vehicle.getLastServiceDate());
        updateVehicle.setMake(vehicle.getMake());
        updateVehicle.setMaxFuelVolume(vehicle.getMaxFuelVolume());
        updateVehicle.setModel(vehicle.getModel());
        updateVehicle.setRedLineRpm(vehicle.getRedLineRpm());
        updateVehicle.setYear(vehicle.getYear());

        return repository.save(updateVehicle);
    }
}
