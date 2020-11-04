package com.Appointment.Model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Appointment {

    @Id
    @GeneratedValue
    private long id;
    private long truckNumber;
    private long dcNumber;
    private String dcSlot;
    private long poNumber;
    private Date dateOfAppointment;
}
