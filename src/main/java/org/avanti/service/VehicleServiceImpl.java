package org.avanti.service;

import org.avanti.entity.Vehicle;
import org.avanti.exception.BadRequestException;
import org.avanti.exception.VehicleNotFoundException;
import org.avanti.repository.VehicleRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRespository respository;

    @Override
    public List<Vehicle> findAll() {
        return respository.findAll();
    }

    @Override
    public Vehicle findOne(String vin) {
        Vehicle vehicle = respository.findOne(vin);
        if(vehicle == null){
            throw new VehicleNotFoundException("Vehicle not found "+vin);
        }
        return vehicle;
    }

    @Override
    @Transactional
    public Vehicle create(Vehicle vehicle) {
        Vehicle existing = respository.findOne(vehicle.getVin());
        if(existing!=null){
            throw new BadRequestException("Vehicle already present "+vehicle.getVin());
        }
        return respository.create(vehicle) ;
    }

    @Override
    @Transactional
    public Vehicle put(String id, Vehicle vehicle) {
        Vehicle existing = respository.findOne(id);
        if(existing == null){
            throw new VehicleNotFoundException("Vehicle not found "+id);
        }

        return respository.put(id,vehicle);
    }
}
