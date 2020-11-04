package com.DistributionCenter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class DCSlot {

    private long id;
    private long dcNumber;
    private String timeSlot;
    private long maxTrucks;
}
