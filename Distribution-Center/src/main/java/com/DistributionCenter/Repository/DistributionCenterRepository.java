package com.DistributionCenter.Repository;

import com.DistributionCenter.Model.DistributionCenter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface DistributionCenterRepository extends CrudRepository<DistributionCenter,Long> {
    public DistributionCenter findByDcNumber(long number);
}
