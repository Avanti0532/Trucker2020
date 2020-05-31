package org.avanti.controller;

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
    public List<Alert> findAllHigh(){
        return service.findAllHigh();
    }

    @RequestMapping(method = RequestMethod.GET, value="{id}")
    public List<Alert> findByVin(@PathVariable("id") String vin){
        return service.findByVin(vin);
    }
}
