package com.AppointmentConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class AppointmentConsumerApplication {

	private static final Logger LOGGER= LoggerFactory.getLogger(AppointmentConsumerApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(AppointmentConsumerApplication.class, args);

		LOGGER.info("START :: Appointment-Consumer :: AppointmentConsumerApplication");
	}

}
