package org.avanti.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Vehicle {
    @Id
    private String vin;
    private String make;
    private String model;
    private int year;
    private int redLineRpm;
    private int maxFuelVolume;
    private Date lastServiceDate;

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRedLineRpm() {
        return redLineRpm;
    }

    public void setRedLineRpm(int redLineRpm) {
        this.redLineRpm = redLineRpm;
    }

    public int getMaxFuelVolume() {
        return maxFuelVolume;
    }

    public void setMaxFuelVolume(int maxFuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }



}
