package com.DistributionCenter.Repository;

import com.DistributionCenter.Model.DistributionCenter;
import com.DistributionCenter.Model.DistributionCenterSlot;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DistributionCenterSlotRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DistributionCenterSlotRepository distributionCenterSlotRepository;

    @Test
    public void testSaveTicket() {
        DistributionCenterSlot distributionCenterSlot = new DistributionCenterSlot();
        distributionCenterSlot.setDistributionCenter(new DistributionCenter(5L,12345L,"Chennai","regional"));
        distributionCenterSlot.setDcTimeSlot("6.00 - 7.00");
        distributionCenterSlot.setMaxTrucks(64L);

        DistributionCenterSlot saveInDb = testEntityManager.merge(distributionCenterSlot);
        DistributionCenterSlot getFromDb = distributionCenterSlotRepository.findById(saveInDb.getId()).get();
        assertThat(getFromDb).isEqualTo(saveInDb);

    }

    @Test
    public void testGetDCSlotById() {
        DistributionCenterSlot distributionCenterSlot = new DistributionCenterSlot();
        distributionCenterSlot.setDistributionCenter(new DistributionCenter(1L,12344L,"Chennai","regional"));
        distributionCenterSlot.setDcTimeSlot("6.00 - 7.00");
        distributionCenterSlot.setMaxTrucks(64L);

        DistributionCenterSlot saveInDb = testEntityManager.merge(distributionCenterSlot);
        DistributionCenterSlot getFromDb = distributionCenterSlotRepository.findById(saveInDb.getId()).get();
        assertThat(getFromDb).isEqualTo(saveInDb);

    }

    @Test
    public void testGetAllDCSlots () {
        DistributionCenterSlot distributionCenterSlot1 = new DistributionCenterSlot();
        distributionCenterSlot1.setDistributionCenter(new DistributionCenter(1L,12346L,"Chennai","regional"));
        distributionCenterSlot1.setDcTimeSlot("6.00 - 7.00");
        distributionCenterSlot1.setMaxTrucks(64L);

        DistributionCenterSlot distributionCenterSlot2 = new DistributionCenterSlot();
        distributionCenterSlot2.setDistributionCenter(new DistributionCenter(1L,12347L,"Chennai","regional"));
        distributionCenterSlot2.setDcTimeSlot("6.00 - 7.00");
        distributionCenterSlot2.setMaxTrucks(64L);

        testEntityManager.merge(distributionCenterSlot1);
        testEntityManager.merge(distributionCenterSlot2);

        List<DistributionCenterSlot> distributionCenterSlotList = new ArrayList<>();
        distributionCenterSlotList = (List<DistributionCenterSlot>) distributionCenterSlotRepository.findAll();

        assertThat(distributionCenterSlotList.size()).isEqualTo(2);
    }

    @Test
    public void testUpdateDcSlot() {
        DistributionCenterSlot distributionCenterSlot = new DistributionCenterSlot();
        distributionCenterSlot.setDistributionCenter(new DistributionCenter(1L,12346L,"Chennai","regional"));
        distributionCenterSlot.setDcTimeSlot("6.00 - 7.00");
        distributionCenterSlot.setMaxTrucks(64L);

        testEntityManager.merge(distributionCenterSlot);

        DistributionCenterSlot getFromDb = distributionCenterSlotRepository.findByDistributionCenterDcNumber(12346L);

        getFromDb.setMaxTrucks(67);
        testEntityManager.merge(getFromDb);

        assertThat(getFromDb.getMaxTrucks()).isEqualTo(67);
    }

    @Test
    public void testDeleteDcSlot() {

        DistributionCenterSlot distributionCenterSlot1 = new DistributionCenterSlot();
        distributionCenterSlot1.setDistributionCenter(new DistributionCenter(1L,12346L,"Chennai","regional"));
        distributionCenterSlot1.setDcTimeSlot("6.00 - 7.00");
        distributionCenterSlot1.setMaxTrucks(64L);

        DistributionCenterSlot distributionCenterSlot2 = new DistributionCenterSlot();
        distributionCenterSlot2.setDistributionCenter(new DistributionCenter(1L,12347L,"Chennai","regional"));
        distributionCenterSlot2.setDcTimeSlot("6.00 - 7.00");
        distributionCenterSlot2.setMaxTrucks(64L);

        DistributionCenterSlot persist = testEntityManager.merge(distributionCenterSlot1);
        testEntityManager.merge(distributionCenterSlot2);

        testEntityManager.remove(persist);

        List<DistributionCenterSlot> distributionCenterSlotList = new ArrayList<>();
        distributionCenterSlotList = (List<DistributionCenterSlot>) distributionCenterSlotRepository.findAll();

        assertThat(distributionCenterSlotList.size()).isEqualTo(1);

    }

    @Test
    void findByDistributionCenterDcNumber() {
        DistributionCenterSlot distributionCenterSlot = new DistributionCenterSlot();
        distributionCenterSlot.setDistributionCenter(new DistributionCenter(5L,12345L,"Chennai","regional"));
        distributionCenterSlot.setDcTimeSlot("6.00 - 7.00");
        distributionCenterSlot.setMaxTrucks(64L);

        testEntityManager.merge(distributionCenterSlot);
        DistributionCenterSlot getFromDb = distributionCenterSlotRepository.findByDistributionCenterDcNumber(12345L);
        assertThat(getFromDb.getMaxTrucks()).isEqualTo(64L);
    }
}