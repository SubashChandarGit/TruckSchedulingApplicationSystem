package com.Appointment.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AppointmentDTO {
    private long id;
    private long truckNumber;
    private long dcNumber;
    private String dcSlot;
    private long poNumber;
    private Date dateOfAppointment;
}
