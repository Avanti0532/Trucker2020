package org.avanti.service;

import org.avanti.entity.Alert;

import java.util.List;

public interface AlertService {
    List<Alert> findAllHigh();

    List<Alert> findByVin(String vin);
}
