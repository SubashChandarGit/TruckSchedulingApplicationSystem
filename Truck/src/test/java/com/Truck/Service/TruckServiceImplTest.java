package com.Truck.Service;

import com.Truck.Model.Truck;
import com.Truck.Repository.TruckRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class TruckServiceImplTest {

    @Autowired
    private TruckService truckService;

    @MockBean
    private TruckRepository truckRepository;

    @Test
    void addTruck() {
        Truck truck = new Truck(1L,231L,"Volvo","Flat-Bed");
        Mockito.doReturn(truck).when(truckRepository).save(truck);
        assertThat(truckService.addTruck(truck)).isEqualTo(truck);
    }

    @Test
    void getAllTruck() {
        Truck truck1 = new Truck(1L,231L,"Volvo","Flat-Bed");
        Truck truck2= new Truck(2L,221L,"Scania","Straight");

        List<Truck> truckList = new ArrayList<>();
        truckList.add(truck1);
        truckList.add(truck2);

        Mockito.when(truckRepository.findAll()).thenReturn(truckList);

        assertThat(truckService.getAllTruck()).isEqualTo(truckList);
    }

    @Test
    void getTruckById() {
        Truck truck = new Truck(1L,231L,"Volvo","Flat-Bed");
        Mockito.when(truckRepository.findById(1L)).thenReturn(java.util.Optional.of(truck));
        assertThat(truckService.getTruckById(1L)).isEqualTo(truck);
    }

    @Test
    void deleteTruck() {
        Truck truck = new Truck(1L,231L,"Volvo","Flat-Bed");
        Mockito.when(truckRepository.findById(1L)).thenReturn(java.util.Optional.of(truck));
        Mockito.when(truckRepository.existsById(1L)).thenReturn(false);
        assertThat(truckRepository.existsById(1L)).isEqualTo(false);
    }

    @Test
    void getTruckByNumber() {
        Truck truck = new Truck(1L,231L,"Volvo","Flat-Bed");
        Mockito.when(truckRepository.findByNumber(231L)).thenReturn(truck);
        assertThat(truckService.getTruckByNumber(231L)).isEqualTo(truck);
    }
}