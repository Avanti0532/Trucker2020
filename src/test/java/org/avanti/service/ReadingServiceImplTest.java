package org.avanti.service;

import org.avanti.entity.*;
import org.avanti.repository.AlertRepository;
import org.avanti.repository.LocationRepository;
import org.avanti.repository.ReadingRepository;
import org.avanti.repository.VehicleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
public class ReadingServiceImplTest {

    @TestConfiguration
    static class ReadingServiceImplTestConfig {
        @Bean
        public ReadingService getReadingService() {
            return new ReadingServiceImpl();
        }

        @Bean
        public VehicleService getVehicleService() {
            return new VehicleServiceImpl();
        }

    }
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ReadingService service;

    @MockBean
    private ReadingRepository readingRepository;

    @MockBean
    private AlertRepository alertRepository;

    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    private List<Reading> readings;
    @Before
    public void setup(){
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
        readings = Collections.singletonList(reading);
        Mockito.when(readingRepository.save(reading)).thenReturn(reading);

        Vehicle vehicle = new Vehicle();
        vehicle.setVin("123456abcde");
        vehicle.setModel("Honda");
        vehicle.setYear(2019);
        vehicle.setRedlineRpm(50);
        vehicle.setMaxFuelVolume(500);
        Mockito.when(vehicleRepository.findById(reading.getVin())).thenReturn(Optional.of(vehicle));

        Alert alert = new Alert();
        alert.setVin("123456abcde");
        alert.setPriority("HIGH");
        Mockito.when(alertRepository.save(alert)).thenReturn(alert);

        Location location = new Location();
        location.setVin("123456abcde");
        location.setLatitude(41.44);
        location.setLongitude(-80.00);
        Mockito.when(locationRepository.save(location)).thenReturn(location);
    }
    @Test
    public void create() {
        service.create(readings.get(0));
        Mockito.verify(readingRepository).save(Mockito.any(Reading.class));
    }

}