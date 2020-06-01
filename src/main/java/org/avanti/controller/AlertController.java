package org.avanti.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.avanti.entity.Alert;
import org.avanti.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("alerts")
public class AlertController {
    @Autowired
    private AlertService service;

    @RequestMapping(method= RequestMethod.GET,value="fetchbyhighpriority")
    @ApiOperation(value = "fetch all high alerts within 2 hours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public List<Alert> findAllHigh(){
        return service.findAllHigh();
    }

    @RequestMapping(method = RequestMethod.GET, value="{id}")
    @ApiOperation(value = "Fetch all alerts of a vehicle by using vin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public List<Alert> findByVin(@PathVariable("id") String vin){
        return service.findByVin(vin);
    }
}
