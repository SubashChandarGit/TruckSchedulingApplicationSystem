package com.Appointment.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DCSlot {

    private long id;
    private long dcNumber;
    private String TimeSlot;
    private long maxTrucks;
}
