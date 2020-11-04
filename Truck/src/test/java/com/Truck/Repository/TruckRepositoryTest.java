package com.Truck.Repository;

import com.Truck.Model.Truck;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TruckRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TruckRepository truckRepository;

    @Test
    public void testSaveTruck() {
        Truck truck = new Truck();
        truck.setName("Volvo");
        truck.setNumber(221L);
        truck.setType("Straight");
        Truck saveInDb = testEntityManager.persist(truck);
        Truck getFromDb = truckRepository.findById(saveInDb.getId()).get();
        assertThat(getFromDb).isEqualTo(saveInDb);
    }

    @Test
    public void testGetAllTrucks() {
        Truck truck1 = new Truck();
        truck1.setName("Volvo");
        truck1.setNumber(231L);
        truck1.setType("Straight");

        Truck truck2 = new Truck();
        truck2.setName("Scania");
        truck2.setNumber(221L);
        truck2.setType("Flatbed");

        testEntityManager.persist(truck1);
        testEntityManager.persist(truck2);

        List<Truck> truckList  = (List<Truck>) truckRepository.findAll();

        assertThat(truckList.size()).isEqualTo(2);
    }

    @Test
    public void testGetById() {
        Truck truck = new Truck();
        truck.setName("Volvo");
        truck.setNumber(231L);
        truck.setType("Straight");
        Truck saveInDb = testEntityManager.persist(truck);
        Truck getFromDb = truckRepository.findById(saveInDb.getId()).get();
        assertThat(getFromDb).isEqualTo(saveInDb);
    }

    @Test
    public void testUpdateTruck() {
        Truck truck = new Truck();
        truck.setName("Volvo");
        truck.setNumber(231L);
        truck.setType("Straight");
        testEntityManager.persist(truck);
        Truck getFromDb = truckRepository.findByNumber(231L);
        getFromDb.setName("Scania");
        testEntityManager.persist(getFromDb);
        assertThat(getFromDb.getName()).isEqualTo("Scania");
    }

    @Test
    public void testDeleteTruck() {
        Truck truck1 = new Truck();
        truck1.setName("Volvo");
        truck1.setNumber(231L);
        truck1.setType("Straight");

        Truck truck2 = new Truck();
        truck2.setName("Scania");
        truck2.setNumber(221L);
        truck2.setType("Flatbed");

        Truck persist = testEntityManager.persist(truck1);
        testEntityManager.persist(truck2);
        testEntityManager.remove(persist);
        List<Truck> truckList = new ArrayList<>();
        truckList = (List<Truck>) truckRepository.findAll();
        assertThat(truckList.size()).isEqualTo(1);
    }

    @Test
    void findByNumber() {
        Truck truck = new Truck();
        truck.setName("Volvo");
        truck.setNumber(231L);
        truck.setType("Straight");
        testEntityManager.persist(truck);
        Truck getFromDb = truckRepository.findByNumber(231L);
        assertThat(getFromDb.getName()).isEqualTo("Volvo");
    }
}