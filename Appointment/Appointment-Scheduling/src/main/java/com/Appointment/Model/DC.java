package com.Appointment.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DC {

    private long id;
    private long dcNumber;
    private String dcCity;
    private String dcType;
}
