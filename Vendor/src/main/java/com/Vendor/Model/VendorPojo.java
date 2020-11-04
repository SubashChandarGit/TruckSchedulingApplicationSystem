package com.Vendor.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorPojo {
    private long id;
    private String name;
    private String email;
    private long phoneNumber;
    private String address;
}
