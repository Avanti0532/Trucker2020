package org.avanti.service;

import org.avanti.entity.Location;

import java.util.List;

public interface LocationService {
    List<Location> findByVinAndTime(String vin);
}
