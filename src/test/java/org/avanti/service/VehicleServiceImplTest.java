package org.avanti.service;


import org.avanti.entity.Vehicle;
import org.avanti.exception.BadRequestException;
import org.avanti.exception.VehicleNotFoundException;
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

import java.util.*;


@RunWith(SpringRunner.class)
public class VehicleServiceImplTest {
    @TestConfiguration
    static class VehicleServiceImplTestConfiguration{

        @Bean
        public VehicleService getService(){
            return new VehicleServiceImpl();
        }

    }
    @Autowired
    private VehicleService service;

    @MockBean
    private VehicleRepository repository;

    private List<Vehicle> vehicles;

    @Before
    public void setup(){

        Vehicle vehicle = new Vehicle();
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

        vehicles = Collections.singletonList(vehicle);
        Mockito.when(repository.findAll())
                .thenReturn(vehicles);
        Mockito.when(repository.findById(vehicle.getVin()))
                .thenReturn(Optional.of(vehicle));
        Mockito.when(repository.save(vehicle))
                .thenReturn(vehicle);
    }

    @Test
    public void findAll() {
        List<Vehicle> allVehicles = service.findAll();
        Assert.assertEquals("Vehicles should match",vehicles, allVehicles);
    }

    @Test
    public void findOne() {
        Vehicle vehicleByVin = service.findOne(vehicles.get(0).getVin());
        Assert.assertEquals("vehicles should match by vin",vehicles.get(0),vehicleByVin);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void findVinNotFound() {
        Vehicle vehicleByVin = service.findOne("1234agdr");
    }


    @Test(expected = BadRequestException.class)
    public void createAlreadyPresent() {
        Vehicle existing = service.create(vehicles.get(0));
    }

    @Test
    public void create() {
        Vehicle newVehicle = new Vehicle();
        newVehicle.setMake("Tata");
        newVehicle.setYear(2018);
        newVehicle.setModel("Creta");
        newVehicle.setRedlineRpm(5500);
        newVehicle.setMaxFuelVolume(15);
        Mockito.when(repository.findById(newVehicle.getVin()))
                .thenReturn(Optional.empty());
        Mockito.when(repository.save(newVehicle))
                .thenReturn(newVehicle);
        Vehicle newV = service.create(newVehicle);
        Assert.assertEquals("Vehicle should be saved", newVehicle, newV);

    }

    @Test
    public void update() {
        service.update(vehicles);
        Mockito.verify(repository).save(Mockito.any(Vehicle.class));
    }

}