package org.avanti.repository;

import org.avanti.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, String> {
    @Query(value = "SELECT * FROM Location WHERE vin = :id  and TIMESTAMPDIFF(MINUTE ,time_stamp,CURRENT_TIMESTAMP) <= 30",
            nativeQuery = true)
    List<Location> findVinTime(@Param("id") String vinId);
}
