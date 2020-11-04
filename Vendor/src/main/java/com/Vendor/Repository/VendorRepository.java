package com.Vendor.Repository;


import com.Vendor.Model.Vendor;
import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends CrudRepository<Vendor,Long> {
    public Vendor findByName(String name);
}
