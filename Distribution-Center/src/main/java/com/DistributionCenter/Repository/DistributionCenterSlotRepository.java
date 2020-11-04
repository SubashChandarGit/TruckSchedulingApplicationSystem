package com.DistributionCenter.Repository;

import com.DistributionCenter.Model.DistributionCenterSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface DistributionCenterSlotRepository extends CrudRepository<DistributionCenterSlot,Long> {

    public DistributionCenterSlot findByDistributionCenterDcNumber(long number);
}
