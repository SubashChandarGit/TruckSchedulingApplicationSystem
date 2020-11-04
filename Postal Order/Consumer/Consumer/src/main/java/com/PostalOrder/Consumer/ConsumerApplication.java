package com.PostalOrder.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
@EnableEurekaClient
public class ConsumerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);


	public static void main(String[] args) {
		LOGGER.info("START :: Postal Order :: Consumer :: ConsumerApplication");
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
