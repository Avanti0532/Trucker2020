package org.avanti.service;
import org.avanti.entity.Alert;
import org.avanti.entity.Location;
import org.avanti.entity.Reading;
import org.avanti.entity.Vehicle;
import org.avanti.exception.VehicleNotFoundException;
import org.avanti.repository.AlertRepository;
import org.avanti.repository.LocationRepository;
import org.avanti.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class ReadingServiceImpl implements ReadingService{

    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    @Transactional
    public void create(Reading reading) {
        readingRepository.save(reading);
        createAlerts(reading);
        saveLocations(reading);
    }

    private void saveLocations(Reading reading) {
        Location location = new Location();
        location.setVin(reading.getVin());
        location.setLatitude(reading.getLatitude());
        location.setLongitude(reading.getLongitude());
        location.setTimeStamp(reading.getTimestamp());
        locationRepository.save(location);
    }

    public void createAlerts(Reading reading){
        Vehicle vehicle = vehicleService.findOne(reading.getVin());
        int frontLeft = reading.getTires().getFrontLeft();
        int frontRight = reading.getTires().getFrontRight();
        int rearLeft = reading.getTires().getRearLeft();
        int rearRight = reading.getTires().getRearRight();

        if(vehicle == null){
            throw new VehicleNotFoundException("Vehicle not found with vin "+reading.getVin());
        }
        if(reading.getEngineRpm() > vehicle.getRedlineRpm()){
             saveAlerts("HIGH",reading);
        }
        if(reading.getFuelVolume()< (0.1 * vehicle.getMaxFuelVolume())){
            saveAlerts("MEDIUM",reading);
        }
        if((frontLeft < 32 || frontLeft > 36) || (frontRight < 32 || frontRight > 36)
            || (rearLeft < 32 || rearLeft > 36) ||(rearRight < 32 || rearRight > 36)) {
            saveAlerts("LOW", reading);
        }
        if(reading.isEngineCoolantLow() || reading.isCheckEngineLightOn()){
            saveAlerts("LOW",reading);
        }

    }
    public void saveAlerts(String priority, Reading reading){
        Alert alert = new Alert();
        alert.setVin(reading.getVin());
        alert.setPriority(priority);
        alert.setAlertTimeStamp(reading.getTimestamp());
        alertRepository.save(alert);
    }
}
