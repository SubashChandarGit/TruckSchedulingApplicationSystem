package com.DistributionCenter.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DC {
    private long id;
    private long dcNumber;
    private String dcCity;
    private String dcType;
}
