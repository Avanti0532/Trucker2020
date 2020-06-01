package org.avanti.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Find all the vehicles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public List<Vehicle> findAll(){
         return service.findAll();
    }

    @RequestMapping(method=RequestMethod.GET, value="{id}")
    @ApiOperation(value = "Find a vehicle by vin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Vehicle findOne(@PathVariable("id") String vin){
        return service.findOne(vin);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ApiOperation(value = "Create a vehicle")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Vehicle create(@RequestBody Vehicle vehicle){
        return service.create(vehicle);
    }

    @CrossOrigin(origins = "*", maxAge = 4800)
    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Create/update a vehicle", notes = "create/update a vehicle. An array of vehicles will sent via PUT method.If a vehicle is present, it will be updated. Otherwise, a new vehicle object will be created in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public void update(@RequestBody List<Vehicle> vehicle){
          service.update(vehicle);
    }





}
