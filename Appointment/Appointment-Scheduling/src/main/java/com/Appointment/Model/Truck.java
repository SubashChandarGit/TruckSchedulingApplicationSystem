package com.Appointment.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Truck {

    private long id;
    private long number;
    private String name;
    private String type;
}
