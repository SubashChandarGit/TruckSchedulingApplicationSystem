package com.Vendor.Controller;

import com.Vendor.Exception.VendorNotFoundException;
import com.Vendor.Model.Vendor;
import com.Vendor.Model.VendorPojo;
import com.Vendor.Service.VendorService;
import com.Vendor.Service.VendorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorController.class);

    @Autowired
    private VendorService vendorService;

    @PostMapping("/Vendor")
    public Vendor addVendor( @RequestBody VendorPojo vendorPojo) {
        LOGGER.info("START :: Vendor :: VendorController :: addVendor");
        Vendor vendor = new Vendor();
        vendor.setName(vendorPojo.getName());
        vendor.setEmail(vendorPojo.getEmail());
        vendor.setAddress(vendorPojo.getAddress());
        vendor.setPhoneNumber(vendorPojo.getPhoneNumber());
        vendorService.addVendor(vendor);
        return vendor;
    }

    @GetMapping("/Vendors")
    public List<Vendor> getAllVendors(){
        LOGGER.info("START :: Vendor :: VendorController :: getAllVendors");
        return  vendorService.getAllVendor();
    }

    @GetMapping("/Vendor/{id}")
    public Vendor getVendorById(@PathVariable long id) throws VendorNotFoundException {
        LOGGER.info("START :: Vendor :: VendorController :: getVendorById");
        return vendorService.getVendorById(id);
    }

    @PutMapping("/Vendor/{id}")
    public void updateVendor(@RequestBody VendorPojo vendorPojo , @PathVariable long id) throws VendorNotFoundException {
        LOGGER.info("START :: Vendor :: VendorController :: updateVendor");
        vendorService.getVendorById(id);
        Vendor vendor = new Vendor();
        vendor.setName(vendorPojo.getName());
        vendor.setEmail(vendorPojo.getEmail());
        vendor.setAddress(vendorPojo.getAddress());
        vendor.setPhoneNumber(vendorPojo.getPhoneNumber());
        vendorService.addVendor(vendor);
    }

    @DeleteMapping("Vendor/{id}")
    public void deleteVendor(@PathVariable long id) throws VendorNotFoundException {
        LOGGER.info("START :: Vendor :: VendorController :: deleteVendor");
        vendorService.deleteVendor(id);
    }
}
