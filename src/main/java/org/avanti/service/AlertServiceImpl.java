package org.avanti.service;

import org.avanti.entity.Alert;
import org.avanti.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository repository;
    @Override
    public List<Alert> findAllHigh() {
        return repository.findAllHigh();
    }

    @Override
    public List<Alert> findByVin(String vin) {
        return repository.findByVin(vin);
    }
}
