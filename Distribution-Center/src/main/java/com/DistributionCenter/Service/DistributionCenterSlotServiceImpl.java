package com.DistributionCenter.Service;

import com.DistributionCenter.Repository.DistributionCenterRepository;
import com.DistributionCenter.Repository.DistributionCenterSlotRepository;
import com.DistributionCenter.Model.DCSlot;
import com.DistributionCenter.Model.DistributionCenter;
import com.DistributionCenter.Model.DistributionCenterSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistributionCenterSlotServiceImpl implements DistributionCenterSlotService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionCenterSlotServiceImpl.class);

    @Autowired
    private DistributionCenterSlotRepository distributionCenterSlotRepository;

    @Autowired
    private DistributionCenter distributionCenter;

    @Autowired
    private DistributionCenterSlot distributionCenterSlot;

    @Autowired
    private DistributionCenterRepository distributionCenterRepository;

    @Autowired
    private DCSlot dcSlot;

    @Override
    public DCSlot addDCSlot(DCSlot dcSlot) {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotServiceImpl :: addDCSlot");
        distributionCenter = distributionCenterRepository.findByDcNumber(dcSlot.getDcNumber());
        distributionCenterSlot.setDistributionCenter(distributionCenter);
        distributionCenterSlot.setDcTimeSlot(dcSlot.getTimeSlot());
        distributionCenterSlot.setMaxTrucks(dcSlot.getMaxTrucks());
        distributionCenterSlotRepository.save(distributionCenterSlot);
        return dcSlot;
    }

    @Override
    public List<DistributionCenterSlot> getAllDCSlots() {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotServiceImpl :: getAllDCSlots");
        return (List<DistributionCenterSlot>) distributionCenterSlotRepository.findAll();
    }

    @Override
    public DistributionCenterSlot getSlotById(long id) {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotServiceImpl :: getSlotById");
        Optional<DistributionCenterSlot> distributionCenterSlotOptional = distributionCenterSlotRepository.findById(id);
        distributionCenterSlot = null;
        if(distributionCenterSlotOptional.isPresent()){
            distributionCenterSlot = distributionCenterSlotOptional.get();
        }
        return distributionCenterSlot;
    }

    @Override
    public void deleteDCSlot(long id) {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotServiceImpl :: deleteDCSlot");
        distributionCenterSlotRepository.deleteById(id);
    }

    @Override
    public DCSlot getSlotByNumber(long number) {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotServiceImpl :: getSlotByNumber");
        distributionCenterSlot = distributionCenterSlotRepository.findByDistributionCenterDcNumber(number);
        dcSlot.setId(distributionCenterSlot.getId());
        dcSlot.setDcNumber(distributionCenterSlot.getDistributionCenter().getDcNumber());
        dcSlot.setTimeSlot(distributionCenterSlot.getDcTimeSlot());
        dcSlot.setMaxTrucks(distributionCenterSlot.getMaxTrucks());
        return dcSlot;
    }
}
