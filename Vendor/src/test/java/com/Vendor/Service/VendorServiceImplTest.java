package com.Vendor.Service;

import com.Vendor.Model.Vendor;
import com.Vendor.Repository.VendorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VendorServiceImplTest {

    @Autowired
    private VendorService vendorService;

    @MockBean
    private VendorRepository vendorRepository;

    @Test
    void addVendor() {
        Vendor vendor = new Vendor(1L,"Vendor","vendor@gmail.com",9876543210L,"abc,def,ghi");
        Mockito.doReturn(vendor).when(vendorRepository.save(vendor));
        assertThat(vendorService.addVendor(vendor)).isEqualTo(vendor);
    }

    @Test
    void getAllVendor() {
        Vendor vendor1 = new Vendor(1L,"Vendor1","vendor1@gmail.com",9876543210L,"abc,def,ghi");
        Vendor vendor2 = new Vendor(1L,"Vendor2","vendor2@gmail.com",9876501234L,"xxx,yyy,zzz");
        List<Vendor> vendorList = new ArrayList<>();
        vendorList.add(vendor1);
        vendorList.add(vendor2);
        Mockito.when(vendorRepository.findAll()).thenReturn(vendorList);
        assertThat(vendorService.getAllVendor()).isEqualTo(vendorList);
    }

    @Test
    void getVendorById() {
        Vendor vendor = new Vendor(1L,"Vendor","vendor@gmail.com",9876543210L,"abc,def,ghi");
        Mockito.when(vendorRepository.findById(1L)).thenReturn(java.util.Optional.of(vendor));
        assertThat(vendorService.getVendorById(1L)).isEqualTo(vendor);
    }

    @Test
    void deleteVendor() {
        Vendor vendor = new Vendor(1L,"Vendor","vendor@gmail.com",9876543210L,"abc,def,ghi");
        Mockito.when(vendorRepository.findById(1L)).thenReturn(java.util.Optional.of(vendor));
        Mockito.when(vendorRepository.existsById(1L)).thenReturn(false);
        assertThat(vendorRepository.existsById(1L)).isEqualTo(false);
    }
}