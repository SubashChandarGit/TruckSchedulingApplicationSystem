package com.PostalOrder.Consumer.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Postal Order Not Found")
public class PostalOrderNotFoundException extends Exception {
}
