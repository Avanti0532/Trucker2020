package org.avanti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.avanti.entity.Alert;
import org.avanti.entity.Vehicle;
import org.avanti.repository.AlertRepository;
import org.avanti.repository.ReadingRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class AlertControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private AlertRepository repository;

    @Before
    public void setup(){
        Alert alert = new Alert();
        alert.setVin("12345abc");
        alert.setPriority("HIGH");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DAY_OF_MONTH, 8);
        Date date = cal.getTime();
        System.out.println("date "+ date);
        alert.setAlertTimeStamp(date);
        repository.save(alert);

        alert = new Alert();
        alert.setVin("12345abcd");
        alert.setPriority("HIGH");
        alert.setAlertTimeStamp(date);
        repository.save(alert);

        alert = new Alert();
        alert.setVin("12345abc");
        alert.setPriority("LOW");
        repository.save(alert);

    }


    @After
    public void cleanup(){
        repository.deleteAll();
    }

    @Test
    public void findAllHigh() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/alerts/fetchbyhighpriority"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priority", Matchers.is("HIGH")));
    }

    @Test
    public void findByVin() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/alerts/12345abc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].vin", Matchers.contains("12345abc","12345abc")));

    }
}