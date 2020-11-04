package com.Vendor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VendorApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(VendorApplication.class);

	public static void main(String[] args) {
		LOGGER.info("START :: Vendor :: VendorApplication");
		SpringApplication.run(VendorApplication.class, args);
	}

}
