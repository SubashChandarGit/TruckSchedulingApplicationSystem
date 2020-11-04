package com.DistributionCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DistributionCenterApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DistributionCenterApplication.class);

	public static void main(String[] args) {
		LOGGER.info("START :: DistributionCenter :: DistributionCenterApplication");
		SpringApplication.run(DistributionCenterApplication.class, args);
	}

}
