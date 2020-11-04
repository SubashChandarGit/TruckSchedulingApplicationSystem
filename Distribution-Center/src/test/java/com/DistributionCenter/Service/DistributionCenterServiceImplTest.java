package com.DistributionCenter.Service;

import com.DistributionCenter.Model.DistributionCenter;
import com.DistributionCenter.Repository.DistributionCenterRepository;
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
class DistributionCenterServiceImplTest {

    @Autowired
    private DistributionCenterService distributionCenterService;

    @MockBean
    private DistributionCenterRepository distributionCenterRepository;

    @Test
    void addDC() {
        DistributionCenter distributionCenter = new DistributionCenter(1L,12344L,"Chennai","regional");

        Mockito.doReturn(distributionCenter).when(distributionCenterRepository).save(distributionCenter);

        assertThat(distributionCenterService.addDC(distributionCenter)).isEqualTo(distributionCenter);

    }

    @Test
    void getAllDC() {
        DistributionCenter distributionCenter1 = new DistributionCenter(1L,12344L,"Chennai","regional");
        DistributionCenter distributionCenter2 = new DistributionCenter(2L,12344L,"Bangalore","National");

        List<DistributionCenter> distributionCenterList = new ArrayList<>();
        distributionCenterList.add(distributionCenter1);
        distributionCenterList.add(distributionCenter2);

        Mockito.when(distributionCenterRepository.findAll()).thenReturn(distributionCenterList);

        assertThat(distributionCenterService.getAllDC()).isEqualTo(distributionCenterList);
    }

    @Test
    void getById() {
        DistributionCenter distributionCenter = new DistributionCenter(1L,12344L,"Chennai","regional");

        Mockito.when(distributionCenterRepository.findById(1L)).thenReturn(java.util.Optional.of(distributionCenter));

        assertThat(distributionCenterService.getById(1l)).isEqualTo(distributionCenter);
    }

    @Test
    void deleteDC() {
        DistributionCenter distributionCenter = new DistributionCenter(1L,12344L,"Chennai","regional");

        Mockito.when(distributionCenterRepository.findById(1L)).thenReturn(java.util.Optional.of(distributionCenter));

        Mockito.when(distributionCenterRepository.existsById(1L)).thenReturn(false);

        assertFalse(distributionCenterRepository.existsById(1L));
    }

    @Test
    void getByNumber() {
        DistributionCenter distributionCenter = new DistributionCenter(1L,12344L,"Chennai","regional");

        Mockito.when(distributionCenterRepository.findByDcNumber(12344L)).thenReturn(distributionCenter);

        assertThat(distributionCenterService.getByNumber(12344L)).isEqualTo(distributionCenter);
    }
}