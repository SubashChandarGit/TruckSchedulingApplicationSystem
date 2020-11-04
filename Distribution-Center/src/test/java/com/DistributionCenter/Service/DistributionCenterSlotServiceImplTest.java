package com.DistributionCenter.Service;

import com.DistributionCenter.Model.DCSlot;
import com.DistributionCenter.Model.DistributionCenter;
import com.DistributionCenter.Model.DistributionCenterSlot;
import com.DistributionCenter.Repository.DistributionCenterSlotRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DistributionCenterSlotServiceImplTest {

    @Autowired
    private DistributionCenterSlotService distributionCenterSlotService;

    @MockBean
    private DistributionCenterSlotRepository distributionCenterSlotRepository;

    @Test
    void addDCSlot() {
        DCSlot dcSlot = new DCSlot(1L,12344L,"8.00 - 9.00",64L);

        DistributionCenterSlot distributionCenterSlot =
                new DistributionCenterSlot(1L,new DistributionCenter(1L,12344L,"Chennai","regional"),"8.00 - 9.00",64L);

        Mockito.when(distributionCenterSlotRepository.save(distributionCenterSlot)).thenReturn(distributionCenterSlot);

        assertThat(distributionCenterSlotService.addDCSlot(dcSlot)).isEqualTo(dcSlot);
    }

    @Test
    void getAllDCSlots() {
        DistributionCenterSlot distributionCenterSlot1 =
                new DistributionCenterSlot(1L,new DistributionCenter(1L,12344L,"Chennai","regional"),"8.00 - 9.00",64L);
        DistributionCenterSlot distributionCenterSlot2 =
                new DistributionCenterSlot(2L,new DistributionCenter(2L,12344L,"Bangalore","National"),"9.00 - 10.00",35L);

        List<DistributionCenterSlot> distributionCenterSlotList = new ArrayList<>();
        distributionCenterSlotList.add(distributionCenterSlot1);
        distributionCenterSlotList.add(distributionCenterSlot2);

        Mockito.when(distributionCenterSlotRepository.findAll()).thenReturn(distributionCenterSlotList);

        assertThat(distributionCenterSlotService.getAllDCSlots()).isEqualTo(distributionCenterSlotList);
    }

    @Test
    void getSlotById() {
        DistributionCenterSlot distributionCenterSlot =
                new DistributionCenterSlot(1L,new DistributionCenter(1L,12344L,"Chennai","regional"),"8.00 - 9.00",64L);
        Mockito.when(distributionCenterSlotRepository.findById(1L)).thenReturn(java.util.Optional.of(distributionCenterSlot));
        assertThat(distributionCenterSlotService.getSlotById(1L)).isEqualTo(distributionCenterSlot);
    }

    @Test
    void deleteDCSlot() {
        DistributionCenterSlot distributionCenterSlot =
                new DistributionCenterSlot(1L,new DistributionCenter(1L,12344L,"Chennai","regional"),"8.00 - 9.00",64L);
        Mockito.when(distributionCenterSlotRepository.findById(1L)).thenReturn(java.util.Optional.of(distributionCenterSlot));
        Mockito.when(distributionCenterSlotRepository.existsById(1L)).thenReturn(false);
        assertFalse(distributionCenterSlotRepository.existsById(1L));
    }

    @Test
    void getSlotByNumber() {
        DCSlot dcSlot = new DCSlot(1L,12344L,"8.00 - 9.00",64L);

        DistributionCenterSlot distributionCenterSlot =
                new DistributionCenterSlot(1L,new DistributionCenter(1L,12344L,"Chennai","regional"),"8.00 - 9.00",64L);
        Mockito.when(distributionCenterSlotRepository.findByDistributionCenterDcNumber(12344L)).thenReturn(distributionCenterSlot);
        assertThat(distributionCenterSlotService.getSlotByNumber(12344L)).isEqualTo(dcSlot);

    }
}