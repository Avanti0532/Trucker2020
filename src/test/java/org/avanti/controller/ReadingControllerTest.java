package org.avanti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.avanti.entity.Reading;
import org.avanti.entity.Tire;
import org.avanti.entity.Vehicle;
import org.avanti.repository.ReadingRepository;
import org.avanti.repository.VehicleRepository;
import org.hamcrest.Matchers;
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
public class ReadingControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private ReadingRepository repository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Before
    public void setup(){
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("123456abcde");
        vehicle.setMake("Honda");
        vehicle.setYear(2018);
        vehicle.setModel("Accord");
        vehicle.setRedlineRpm(5500);
        vehicle.setMaxFuelVolume(15);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DAY_OF_MONTH, 7);
        Date date = cal.getTime();
        vehicle.setLastServiceDate(date);
        vehicleRepository.save(vehicle);
    }

    @After
    public void cleanup(){
        repository.deleteAll();
    }

    @Test
    public void createVehicleNotFound() throws Exception {

        Reading reading = new Reading();
        reading.setVin("123456abc");
        reading.setCheckEngineLightOn(true);
        reading.setCruiseControlOn(false);
        reading.setEngineHp(240);
        reading.setEngineCoolantLow(true);
        reading.setLatitude(41.80);
        reading.setLongitude(-88.94);
        reading.setFuelVolume(1.5);
        reading.setSpeed(85);
        reading.setEngineRpm(6300);

        Tire tire = new Tire();
        tire.setRearLeft(29);
        tire.setRearRight(34);
        tire.setFrontLeft(34);
        tire.setFrontRight(36);
        reading.setTires(tire);

        mvc.perform(MockMvcRequestBuilders.post("/readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(reading)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void create() throws Exception{

        Reading reading = new Reading();
        reading.setVin("123456abcde");
        reading.setCheckEngineLightOn(true);
        reading.setCruiseControlOn(false);
        reading.setEngineHp(240);
        reading.setEngineCoolantLow(true);
        reading.setLatitude(41.80);
        reading.setLongitude(-88.94);
        reading.setFuelVolume(1.5);
        reading.setSpeed(85);
        reading.setEngineRpm(6300);

        Tire tire = new Tire();
        tire.setRearLeft(29);
        tire.setRearRight(34);
        tire.setFrontLeft(34);
        tire.setFrontRight(36);
        reading.setTires(tire);

        mvc.perform(MockMvcRequestBuilders.post("/readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(reading)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
