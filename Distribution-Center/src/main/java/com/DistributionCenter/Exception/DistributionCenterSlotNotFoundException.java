package com.DistributionCenter.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "The Distribution Center is not found ")
public class DistributionCenterSlotNotFoundException extends Exception {
}
