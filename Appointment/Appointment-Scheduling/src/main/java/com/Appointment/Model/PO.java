package com.Appointment.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PO {

    private long id;
    private long poNumber;
    private Date poDate;
    private String poAddress;
    private long poLineNumber;
    private long upcNumber;
    private String upcName;
    private long Quantity;
}
