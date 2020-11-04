package com.Vendor.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Vendor not found")
public class VendorNotFoundException extends Exception {
}
