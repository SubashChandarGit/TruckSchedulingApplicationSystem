package com.DistributionCenter.Service;

import com.DistributionCenter.Model.DistributionCenter;

import java.util.List;

public interface DistributionCenterService {
    public DistributionCenter addDC(DistributionCenter distributionCenter);
    public List<DistributionCenter> getAllDC();
    public DistributionCenter getById(long id);
    public void deleteDC(long number);
    public DistributionCenter getByNumber(long number);
}

