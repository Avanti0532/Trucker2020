package org.avanti.service;


import org.avanti.entity.Location;
import org.avanti.repository.LocationRepository;
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

@RunWith(SpringRunner.class)
public class LocationServiceImplTest {

    @TestConfiguration
    static class LocationServiceImplTestConfig{
        @Bean
        public LocationService getService(){
            return new LocationServiceImpl();
        }
    }

    @Autowired
    public LocationService service;

    @MockBean
    public LocationRepository repository;

    private List<Location> locations;

    @Before
    public void setup(){
        Location location = new Location();
        location.setVin("123456abcd");
        location.setLatitude(41.33);
        location.setLongitude(50.22);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DAY_OF_MONTH, 7);
        Date date = cal.getTime();
        location.setTimeStamp(date);

        locations = Collections.singletonList(location);

        Mockito.when(repository.findVinTime(locations.get(0).getVin())).thenReturn(locations);


    }

    @Test
    public void findByVinAndTime(){

        List<Location> allLocations = service.findByVinAndTime(locations.get(0).getVin());
        Assert.assertEquals("locations should match", locations, allLocations);
    }


}