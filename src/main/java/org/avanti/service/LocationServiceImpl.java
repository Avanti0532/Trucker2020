package org.avanti.service;

import org.avanti.entity.Location;
import org.avanti.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;
    @Override
    public List<Location> findByVinAndTime(String vin) {
        return repository.findVinTime(vin);
    }
}
