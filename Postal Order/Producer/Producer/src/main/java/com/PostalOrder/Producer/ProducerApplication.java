package com.PostalOrder.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding(Source.class)
public class ProducerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerApplication.class);


	public static void main(String[] args) {
		LOGGER.info("START :: Postal Order :: Producer :: ProducerApplication");
		SpringApplication.run(ProducerApplication.class, args);
	}

}
