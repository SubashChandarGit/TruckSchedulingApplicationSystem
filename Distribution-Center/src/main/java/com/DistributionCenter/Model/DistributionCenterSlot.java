package com.DistributionCenter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
public class DistributionCenterSlot {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dcNumber", referencedColumnName = "dcNumber")
    private DistributionCenter distributionCenter;
    private String dcTimeSlot;
    private long maxTrucks;
}
