package com.DistributionCenter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
public class DistributionCenter implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private long dcNumber;
    private String dcCity;
    private String dcType;

}

