package com.DistributionCenter.Controller;

import com.DistributionCenter.DistributionCenterApplication;
import com.DistributionCenter.Exception.DistributionCenterSlotNotFoundException;
import com.DistributionCenter.Service.DistributionCenterSlotService;
import com.DistributionCenter.Model.DCSlot;
import com.DistributionCenter.Model.DistributionCenterSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistributionCenterSlotController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionCenterSlotController.class);

    @Autowired
    private DistributionCenterSlotService distributionCenterSlotService;

    @PostMapping("/DCSlot")
    public DCSlot addDistributionCenterSlot( @RequestBody DCSlot dcSlot) {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotController :: addDistributionCenterSlot");
        distributionCenterSlotService.addDCSlot(dcSlot);
        return dcSlot;
    }
    @GetMapping("/DCSlots")
    public List<DistributionCenterSlot> getAllDistributionCenterSlots(){
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotController :: getAllDistributionCenterSlots");
        return  distributionCenterSlotService.getAllDCSlots();
    }

    @GetMapping("/DCSlot/{id}")
    public DistributionCenterSlot getDistributionCenterSlot(@PathVariable long id) throws DistributionCenterSlotNotFoundException {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotController :: getDistributionCenterSlot");
        return distributionCenterSlotService.getSlotById(id);
    }

    @GetMapping("/DCSlotByNumber/{number}")
    public DCSlot getDistributionCenterSlotByNumber(@PathVariable long number) throws DistributionCenterSlotNotFoundException{
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotController :: getDistributionCenterSlotByNumber");
        return distributionCenterSlotService.getSlotByNumber(number);
    }

    @PutMapping("/DCSlot/{id}")
    public void updateDistributionCenterSlot(@RequestBody DCSlot dcSlot ,@PathVariable long id) throws DistributionCenterSlotNotFoundException{
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotController :: updateDistributionCenterSlot");
        distributionCenterSlotService.getSlotById(id);
        distributionCenterSlotService.addDCSlot(dcSlot);
    }

    @DeleteMapping("DCSlot/{id}")
    public void deleteDistributionCenterSlot(@PathVariable long id) throws DistributionCenterSlotNotFoundException{
        LOGGER.info("START :: DistributionCenter :: DistributionCenterSlotController :: deleteDistributionCenterSlot");
        distributionCenterSlotService.deleteDCSlot(id);
    }
}
