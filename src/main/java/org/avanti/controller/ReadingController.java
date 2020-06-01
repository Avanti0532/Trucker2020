package org.avanti.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Create readings")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public void create(@RequestBody Reading reading){
        service.create(reading);
    }
}
