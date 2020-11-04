package com.Appointment.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apmt {

    private long truckNumber;
    private long dcNumber;
    private String timeSlot;
    private List<Long> poNumbers;
    private Date date;
}
