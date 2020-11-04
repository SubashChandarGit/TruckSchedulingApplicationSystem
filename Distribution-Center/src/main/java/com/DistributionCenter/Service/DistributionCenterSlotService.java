package com.DistributionCenter.Service;

import com.DistributionCenter.Model.DCSlot;
import com.DistributionCenter.Model.DistributionCenterSlot;

import java.util.List;

public interface DistributionCenterSlotService {
    public DCSlot addDCSlot(DCSlot dcSlot);
    public List<DistributionCenterSlot> getAllDCSlots();
    public DistributionCenterSlot getSlotById(long id);
    public void deleteDCSlot(long id);
    public DCSlot getSlotByNumber(long number);
}
