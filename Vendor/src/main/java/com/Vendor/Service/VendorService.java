package com.Vendor.Service;

import com.Vendor.Model.Vendor;

import java.util.List;

public interface VendorService {
    public Vendor addVendor(Vendor vendor);
    public List<Vendor> getAllVendor();
    public Vendor getVendorById(long id);
    public void deleteVendor(long id);
}
