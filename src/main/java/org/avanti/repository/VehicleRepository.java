package org.avanti.repository;

import org.avanti.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;


public interface VehicleRepository extends CrudRepository<Vehicle, String> {
}
