package com.DistributionCenter.Service;

import com.DistributionCenter.Repository.DistributionCenterRepository;
import com.DistributionCenter.Model.DistributionCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistributionCenterServiceImpl implements DistributionCenterService{

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionCenterServiceImpl.class);

    @Autowired
    private DistributionCenterRepository distributionCenterRepository;

    @Override
    public DistributionCenter addDC(DistributionCenter distributionCenter) {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterServiceImpl :: addDC");
        distributionCenterRepository.save(distributionCenter);
        return distributionCenter;
    }

    @Override
    public List<DistributionCenter> getAllDC() {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterServiceImpl :: getAllDC");
        return (List<DistributionCenter>) distributionCenterRepository.findAll();
    }

    @Override
    public DistributionCenter getById(long id) {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterServiceImpl :: getById");
        Optional<DistributionCenter> distributionCenterOptional =  distributionCenterRepository.findById(id);
        DistributionCenter distributionCenter = null;
        if(distributionCenterOptional.isPresent()) {
            distributionCenter = distributionCenterOptional.get();
        }
        return distributionCenter;
    }

    @Override
    public void deleteDC(long number) {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterServiceImpl :: deleteDC");
        distributionCenterRepository.deleteById(number);
    }

    @Override
    public DistributionCenter getByNumber(long number) {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterServiceImpl :: getByNumber");
        return distributionCenterRepository.findByDcNumber(number);
    }
}
