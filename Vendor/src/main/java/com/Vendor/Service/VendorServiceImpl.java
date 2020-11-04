package com.Vendor.Service;

import com.Vendor.Model.Vendor;
import com.Vendor.Repository.VendorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorServiceImpl.class);

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public Vendor addVendor(Vendor vendor) {
        LOGGER.info("START :: Vendor :: VendorServiceImpl :: addVendor");
        vendorRepository.save(vendor);
        return vendor;
    }


    @Override
    public List<Vendor> getAllVendor() {
        LOGGER.info("START :: Vendor :: VendorServiceImpl :: getAllVendor");
        return (List<Vendor>) vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(long id) {
        LOGGER.info("START :: Vendor :: VendorServiceImpl :: getVendorById");
        Optional<Vendor> vendorOptional = vendorRepository.findById(id);
        Vendor vendor = null;
        if(vendorOptional.isPresent()){
            vendor = vendorOptional.get();
        }
        return vendor;
    }

    @Override
    public void deleteVendor(long id) {
        LOGGER.info("START :: Vendor :: VendorServiceImpl :: deleteVendor");
        vendorRepository.deleteById(id);
    }
}
