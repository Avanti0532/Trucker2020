package org.avanti.controller;

import org.avanti.entity.Reading;
import org.avanti.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="readings")
public class ReadingController {
    @Autowired
    private ReadingService service;

    @CrossOrigin(origins = "*", maxAge = 4800)
    @RequestMapping(method= RequestMethod.POST)
    public void create(@RequestBody Reading reading){
        service.create(reading);
    }
}
