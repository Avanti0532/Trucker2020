package org.avanti.controller;

import org.avanti.entity.Vehicle;
import org.avanti.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @RequestMapping(method= RequestMethod.GET)
    public List<Vehicle> findAll(){
         return service.findAll();
    }

    @RequestMapping(method=RequestMethod.GET, value="{id}")
    public Vehicle findOne(@PathVariable("id") String vin){
        return service.findOne(vin);
    }

    @RequestMapping(method=RequestMethod.POST)
    public Vehicle create(@RequestBody Vehicle vehicle){
        return service.create(vehicle);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Vehicle put(@PathVariable("id") String vin, @RequestBody Vehicle vehicle){
        return service.put(vehicle);
    }





}
