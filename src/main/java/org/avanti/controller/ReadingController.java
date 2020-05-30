package org.avanti.controller;

import org.avanti.entity.Reading;
import org.avanti.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="readings")
public class ReadingController {
    @Autowired
    private ReadingService service;

    @RequestMapping(method= RequestMethod.POST)
    public Reading create(@RequestBody Reading reading){
        return service.create(reading);
    }
}
