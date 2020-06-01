package org.avanti.repository;

import org.avanti.entity.Reading;
import org.springframework.data.repository.CrudRepository;

public interface ReadingRepository extends CrudRepository<Reading, String> {
}
