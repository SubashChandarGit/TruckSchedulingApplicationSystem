package com.Appointment;

import com.Appointment.Controller.AppointmentController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(Source.class)
public class AppointmentSchedulingApplication {

	public static void main(String[] args) {
		LOGGER.info("START :: Appointment-Scheduling :: AppointmentSchedulingApplication ");
		SpringApplication.run(AppointmentSchedulingApplication.class, args);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentSchedulingApplication.class);


	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
