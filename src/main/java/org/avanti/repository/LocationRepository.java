package org.avanti.repository;

import org.avanti.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, String> {
    @Query(value = "SELECT * FROM Location WHERE vin = ?1 and (TIMESTAMPDIFF(MINUTE ,CURRENT_TIMESTAMP,time_stamp)) <= 30",
            nativeQuery = true)
    List<Location> findVinTime(String vinId);
}
