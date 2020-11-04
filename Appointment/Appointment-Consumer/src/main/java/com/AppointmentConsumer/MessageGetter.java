package com.AppointmentConsumer;

import com.AppointmentConsumer.Model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Service;

@Service
public class MessageGetter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageGetter.class);

    @StreamListener(Processor.INPUT)
    public void consumerMessage(Appointment appointment) {
        LOGGER.info("START :: Appointment-Consumer :: MessageGetter :: consumerMessage");
    }
}
