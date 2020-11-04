package com.DistributionCenter.Controller;


import com.DistributionCenter.Exception.DistributionCenterNotFoundException;
import com.DistributionCenter.Model.DC;
import com.DistributionCenter.Service.DistributionCenterService;
import com.DistributionCenter.Model.DistributionCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistributionCenterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionCenterController.class);

    @Autowired
    private DistributionCenterService distributionCenterService;

    @PostMapping("/DC")
    public DistributionCenter addDistributionCenter( @RequestBody DC dc) {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterController :: addDistributionCenter");
        DistributionCenter distributionCenter = new DistributionCenter();
        distributionCenter.setDcNumber(dc.getDcNumber());
        distributionCenter.setDcCity(dc.getDcCity());
        distributionCenter.setDcType(dc.getDcType());
        distributionCenterService.addDC(distributionCenter);
        return distributionCenter;
    }
    @GetMapping("/DCs")
    public List<DistributionCenter> getAllDistributionCenter(){
        LOGGER.info("START :: DistributionCenter :: DistributionCenterController :: getAllDistributionCenter");
        return  distributionCenterService.getAllDC();
    }

    @GetMapping("/DC/{id}")
    public DistributionCenter getDistributionCenter (@PathVariable long id) throws DistributionCenterNotFoundException {
        LOGGER.info("START :: DistributionCenter :: DistributionCenterController :: getDistributionCenter");
        return distributionCenterService.getById(id);
    }

    @GetMapping("/DCByNumber/{number}")
    public DistributionCenter getDistributionCenterByNumber(@PathVariable long number) throws DistributionCenterNotFoundException{
        LOGGER.info("START :: DistributionCenter :: DistributionCenterController :: getDistributionCenterByNumber");
        return distributionCenterService.getByNumber(number);
    }

    @PutMapping("/DC/{id}")
    public void updateDistributionCenter(@RequestBody DC dc  ,@PathVariable long id) throws DistributionCenterNotFoundException{
        LOGGER.info("START :: DistributionCenter :: DistributionCenterController :: updateDistributionCenter");
        distributionCenterService.getById(id);
        DistributionCenter distributionCenter = new DistributionCenter();
        distributionCenter.setDcNumber(dc.getDcNumber());
        distributionCenter.setDcCity(dc.getDcCity());
        distributionCenter.setDcType(dc.getDcType());
        distributionCenterService.addDC(distributionCenter);
    }

    @DeleteMapping("DC/{id}")
    public void deleteDistributionCenter(@PathVariable long id) throws DistributionCenterNotFoundException{
        LOGGER.info("START :: DistributionCenter :: DistributionCenterController :: deleteDistributionCenter");
        distributionCenterService.deleteDC(id);
    }
}
