package org.avanti.repository;

import org.avanti.entity.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlertRepository extends CrudRepository<Alert, String> {
    @Query(value = "SELECT * FROM Alert WHERE priority='HIGH' and HOUR(TIMEDIFF(CURRENT_TIMESTAMP,alert_time_stamp)) <= 2",
    nativeQuery = true)
    List<Alert> findAllHigh();

    List<Alert> findByVin(String vin);
}
