package com.DistributionCenter.Repository;

import com.DistributionCenter.Model.DistributionCenter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DistributionCenterRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DistributionCenterRepository distributionCenterRepository;

    @Test
    public void testSaveTicket() {
        DistributionCenter distributionCenter = getDistributionCenter();
        DistributionCenter saveInDb = testEntityManager.persist(distributionCenter);
        DistributionCenter getFromDb = distributionCenterRepository.findById(saveInDb.getId()).get();
        assertThat(getFromDb).isEqualTo(saveInDb);
    }

    @Test
    public void testGetDCBYId() {
        DistributionCenter distributionCenter = new DistributionCenter();
        distributionCenter.setDcNumber(12345L);
        distributionCenter.setDcCity("Mumbai");
        distributionCenter.setDcType("National");

        DistributionCenter saveInDb = testEntityManager.persist(distributionCenter);
        DistributionCenter getFromDb = distributionCenterRepository.findById(saveInDb.getId()).get();

        assertThat(getFromDb).isEqualTo(saveInDb);

    }

    @Test
    public void testGetAllDCs(){
        DistributionCenter distributionCenter1 = new DistributionCenter();
        distributionCenter1.setDcNumber(12345L);
        distributionCenter1.setDcCity("Mumbai");
        distributionCenter1.setDcType("National");

        DistributionCenter distributionCenter2 = new DistributionCenter();
        distributionCenter2.setDcNumber(12344L);
        distributionCenter2.setDcCity("Chennai");
        distributionCenter2.setDcType("Regional");

        testEntityManager.merge(distributionCenter1);
        testEntityManager.merge(distributionCenter2);

        List<DistributionCenter> distributionCenterList = new ArrayList<>();
        distributionCenterList = (List<DistributionCenter>) distributionCenterRepository.findAll();

        assertThat(distributionCenterList.size()).isEqualTo(2);

    }

    @Test
    public void testUpdateDC() {
        DistributionCenter distributionCenter = new DistributionCenter();
        distributionCenter.setDcNumber(12346L);
        distributionCenter.setDcCity("Mumbai");
        distributionCenter.setDcType("National");

        testEntityManager.persist(distributionCenter);

        DistributionCenter getFromDb = distributionCenterRepository.findByDcNumber(12345L);

        getFromDb.setDcCity("Chennai");
        testEntityManager.persist(getFromDb);

        assertThat(getFromDb.getDcCity()).isEqualTo("Chennai");
    }

    @Test
    public void testDeleteDC() {
        DistributionCenter distributionCenter1 = new DistributionCenter();
        distributionCenter1.setDcNumber(12345L);
        distributionCenter1.setDcCity("Mumbai");
        distributionCenter1.setDcType("National");

        DistributionCenter distributionCenter2 = new DistributionCenter();
        distributionCenter2.setDcNumber(12344L);
        distributionCenter2.setDcCity("Chennai");
        distributionCenter2.setDcType("Regional");

        DistributionCenter persist = testEntityManager.merge(distributionCenter1);
        testEntityManager.merge(distributionCenter2);

        testEntityManager.remove(persist);

        List<DistributionCenter> distributionCenterList = new ArrayList<>();
        distributionCenterList = (List<DistributionCenter>) distributionCenterRepository.findAll();

        assertThat(distributionCenterList.size()).isEqualTo(1);
    }

    @Test
    void findByDcNumber() {
        DistributionCenter distributionCenter = getDistributionCenter();
        testEntityManager.persist(distributionCenter);
        DistributionCenter getFromDb = distributionCenterRepository.findByDcNumber(12344L);
        assertThat(getFromDb.getDcCity()).isEqualTo("Chennai");
    }

    private DistributionCenter getDistributionCenter() {
        DistributionCenter distributionCenter = new DistributionCenter();
        distributionCenter.setDcNumber(12344L);
        distributionCenter.setDcCity("Chennai");
        distributionCenter.setDcType("Regional");
        return distributionCenter;
    }
}