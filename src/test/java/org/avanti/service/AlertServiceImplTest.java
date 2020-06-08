package org.avanti.service;

import org.avanti.entity.Alert;
import org.avanti.repository.AlertRepository;
import org.avanti.repository.VehicleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class AlertServiceImplTest {

    @TestConfiguration
    static class AlertServiceImplTestConfiguration{

        @Bean
        public AlertService getService(){
            return new AlertServiceImpl();
        }

    }

    @Autowired
    private AlertService service;

    @MockBean
    private AlertRepository repository;

    private List<Alert> alerts;

    @Before
    public void setup(){
        Alert alert = new Alert();
        alert.setVin("123455abcde");
        alert.setPriority("HIGH");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DAY_OF_MONTH, 7);
        Date date = cal.getTime();
        alert.setAlertTimeStamp(date);

        alerts = Collections.singletonList(alert);

        Mockito.when(repository.findAllHigh()).thenReturn(alerts);
        Mockito.when(repository.findByVin(alerts.get(0).getVin())).thenReturn(alerts);
    }

    @Test
    public void findAllHigh() {

        List<Alert> allHighAlerts = service.findAllHigh();
        Assert.assertEquals("alerts should match", alerts, allHighAlerts);

    }

    @Test
    public void findByVin() {
        List<Alert> allAlerts = service.findByVin(alerts.get(0).getVin());
        Assert.assertEquals("alerts should match", alerts, allAlerts);
    }
}