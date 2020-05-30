package org.avanti.service;
import org.avanti.entity.Reading;
import org.avanti.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingServiceImpl implements ReadingService{

    @Autowired
    private ReadingRepository readingRepository;

    @Override
    public Reading create(Reading reading) {
        return readingRepository.save(reading) ;
    }
}
