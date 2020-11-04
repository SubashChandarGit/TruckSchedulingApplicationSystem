package com.Vendor.Repository;

import com.Vendor.Model.Vendor;
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


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class VendorRepositoryTest {

    @Autowired
    private VendorRepository vendorRepository;
    
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testSaveVendor() {
        Vendor vendor = new Vendor();
        vendor.setName("Vendor");
        vendor.setEmail("vendor@gmail.com");
        vendor.setPhoneNumber(9876543210L);
        vendor.setAddress("aaa,bbb,ccc");
        Vendor saveInDb = testEntityManager.persist(vendor);
        Vendor getFromDb = vendorRepository.findById(saveInDb.getId()).get();
        assertThat(getFromDb).isEqualTo(saveInDb);
    }

    @Test
    public void testGetAllVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor1");
        vendor1.setEmail("vendor2@gmail.com");
        vendor1.setPhoneNumber(9876543210L);
        vendor1.setAddress("aaa,bbb,ccc");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor2");
        vendor2.setEmail("vendor2@gmail.com");
        vendor2.setPhoneNumber(9876534210L);
        vendor2.setAddress("aaa,bbb,ccc");

        testEntityManager.persist(vendor1);
        testEntityManager.persist(vendor2);

        List<Vendor> vendorList = new ArrayList<>();
        vendorList = (List<Vendor>) vendorRepository.findAll();

        assertThat(vendorList.size()).isEqualTo(2);
    }

    @Test
    public void testGetById() {
        Vendor vendor = new Vendor();
        vendor.setName("Vendor");
        vendor.setEmail("vendor@gmail.com");
        vendor.setPhoneNumber(9876543210L);
        vendor.setAddress("aaa,bbb,ccc");
        Vendor saveInDb = testEntityManager.persist(vendor);
        Vendor getFromDb = vendorRepository.findById(saveInDb.getId()).get();
        assertThat(getFromDb).isEqualTo(saveInDb);
    }

    @Test
    public void testUpdateVendor() {
        Vendor vendor = new Vendor();
        vendor.setName("Vendor");
        vendor.setEmail("vendor@gmail.com");
        vendor.setPhoneNumber(9876543210L);
        vendor.setAddress("aaa,bbb,ccc");
        testEntityManager.persist(vendor);
        Vendor getFromDb = vendorRepository.findByName("Vendor");
        getFromDb.setName("Scania");
        testEntityManager.persist(getFromDb);
        assertThat(getFromDb.getName()).isEqualTo("Scania");
    }

    @Test
    public void testDeleteVendor() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor1");
        vendor1.setEmail("vendor2@gmail.com");
        vendor1.setPhoneNumber(9876543210L);
        vendor1.setAddress("aaa,bbb,ccc");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor2");
        vendor2.setEmail("vendor2@gmail.com");
        vendor2.setPhoneNumber(9876534210L);
        vendor2.setAddress("aaa,bbb,ccc");

        Vendor persist = testEntityManager.persist(vendor1);
        testEntityManager.persist(vendor2);
        testEntityManager.remove(persist);
        List<Vendor> vendorList = new ArrayList<>();
        vendorList = (List<Vendor>) vendorRepository.findAll();
        assertThat(vendorList.size()).isEqualTo(1);
    }

    @Test
    public void findByName() {
        Vendor vendor = new Vendor();
        vendor.setName("Vendor");
        vendor.setEmail("vendor@gmail.com");
        vendor.setPhoneNumber(9876543210L);
        vendor.setAddress("aaa,bbb,ccc");
        testEntityManager.persist(vendor);
        Vendor getFromDb = vendorRepository.findByName("Vendor");
        assertThat(getFromDb.getEmail()).isEqualTo("vendor@gmail.com");
    }


}