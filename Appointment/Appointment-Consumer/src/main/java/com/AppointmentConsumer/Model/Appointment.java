package com.AppointmentConsumer.Model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Appointment {

    private long id;
    private long truckNumber;
    private long dcNumber;
    private String dcSlot;
    private long poNumber;
    private Date dateOfAppointment;
}
