package org.avanti.repository;

import org.avanti.entity.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlertRepository extends CrudRepository<Alert, String> {
    @Query(value = "SELECT * FROM alert WHERE priority='HIGH' and (TIMESTAMPDIFF(HOUR,alert_time_stamp,CURRENT_TIMESTAMP)) <= 2 ORDER BY alert_time_stamp ASC",
    nativeQuery = true)
    List<Alert> findAllHigh();
    List<Alert> findByVin(String vin);
}
