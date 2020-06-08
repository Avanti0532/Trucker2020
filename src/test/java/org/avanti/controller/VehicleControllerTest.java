package org.avanti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.avanti.entity.Vehicle;
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

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private VehicleRepository repository;

    @Before
    public void setup(){
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("12345");
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
        repository.save(vehicle);


        vehicle = new Vehicle();
        vehicle.setVin("67890");
        vehicle.setMake("Tata");
        vehicle.setYear(2018);
        vehicle.setModel("Creta");
        vehicle.setRedlineRpm(5500);
        vehicle.setMaxFuelVolume(15);
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2020);
        cal1.set(Calendar.MONTH, Calendar.JUNE);
        cal1.set(Calendar.DAY_OF_MONTH, 7);
        Date date1 = cal1.getTime();
        vehicle.setLastServiceDate(date1);

        repository.save(vehicle);
    }

    @After
    public void cleanup(){
        repository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        mvc.perform((MockMvcRequestBuilders.get("/vehicles")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].make",Matchers.is("Honda")));
    }

    @Test
    public void findOne() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/12345"))
                .andExpect((MockMvcResultMatchers.status().isOk()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.make",Matchers.is("Honda")));
    }

    @Test
    public void findOneNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/abcdef"))
                .andExpect((MockMvcResultMatchers.status().isNotFound()));
    }

    @Test
    public void create() throws Exception{
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("9015975");
        vehicle.setMake("Maruti");
        vehicle.setYear(2018);
        vehicle.setModel("Suzuki");
        vehicle.setRedlineRpm(5500);
        vehicle.setMaxFuelVolume(15);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DAY_OF_MONTH, 7);
        Date date = cal.getTime();
        vehicle.setLastServiceDate(date);

        mvc.perform(MockMvcRequestBuilders.post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(vehicle)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.make",Matchers.is("Maruti")));
    }

    @Test
    public void createBadRequest() throws Exception{
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("12345");
        vehicle.setMake("Maruti");
        vehicle.setYear(2018);
        vehicle.setModel("Suzuki");
        vehicle.setRedlineRpm(5500);
        vehicle.setMaxFuelVolume(15);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DAY_OF_MONTH, 7);
        Date date = cal.getTime();
        vehicle.setLastServiceDate(date);

        mvc.perform(MockMvcRequestBuilders.post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(vehicle)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void update() throws Exception{

        Vehicle vehicle = new Vehicle();
        vehicle.setVin("9015975");
        vehicle.setMake("Maruti");
        vehicle.setYear(2018);
        vehicle.setModel("Suzuki");
        vehicle.setRedlineRpm(5500);
        vehicle.setMaxFuelVolume(15);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DAY_OF_MONTH, 7);
        Date date = cal.getTime();
        vehicle.setLastServiceDate(date);
        List<Vehicle> allVehicles = Collections.singletonList(vehicle);
        mvc.perform(MockMvcRequestBuilders.put("/vehicles")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(mapper.writeValueAsBytes(allVehicles)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}