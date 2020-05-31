package org.avanti.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.UUID;

@Entity
public class Alert {
    @Id
    private String id;
    private String vin;
    private String priority;
    private Date alertTimeStamp;

    public Alert() {
        this.id = UUID.randomUUID().toString();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getAlertTimeStamp() {
        return alertTimeStamp;
    }

    public void setAlertTimeStamp(Date alertTimeStamp) {
        this.alertTimeStamp = alertTimeStamp;
    }
}
