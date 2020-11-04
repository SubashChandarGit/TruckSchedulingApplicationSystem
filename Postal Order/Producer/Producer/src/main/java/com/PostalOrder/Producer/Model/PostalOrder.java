package com.PostalOrder.Producer.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostalOrder {

    private long id;
    private long poNumber;
    private Date poDate;
    private String poAddress;
    private long poLineNumber;
    private long upcNumber;
    private String upcName;
    private long orderedQuantity;
}
