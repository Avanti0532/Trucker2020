package org.avanti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.avanti.entity.Location;
import org.avanti.repository.LocationRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class LocationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    LocationRepository locationRepository;

    @Before
    public void setup(){
        Location location = new Location();
        location.setVin("12345adc");
        location.setLongitude(41.99);
        location.setLatitude(-88.55);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DAY_OF_MONTH, 8);
        Date date = cal.getTime();
        location.setTimeStamp(date);

        locationRepository.save(location);

    }

    @After
    public void cleanup(){
        locationRepository.deleteAll();
    }

    @Test
    public void findByVinAndTime() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/locations/12345adc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}