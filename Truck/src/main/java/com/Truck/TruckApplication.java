package com.Truck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TruckApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(TruckApplication.class);

	public static void main(String[] args) {
		LOGGER.info("START :: Truck :: TruckApplication");
		SpringApplication.run(TruckApplication.class, args);
	}

}
