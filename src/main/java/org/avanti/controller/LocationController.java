package org.avanti.controller;

import org.avanti.entity.Location;
import org.avanti.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {

    @Autowired
    private LocationService service;

    @RequestMapping(method = RequestMethod.GET, value="{id}")
    public List<Location> findByVinAndTime(@PathVariable("id") String vin){
        return service.findByVinAndTime(vin);
    }
}
