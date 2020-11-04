package com.PostalOrder.Consumer;

import com.PostalOrder.Consumer.Model.PostalOrder;
import com.PostalOrder.Consumer.Repository.PostalOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);


    @Autowired
    private PostalOrderRepository postalOrderRepository;

    @StreamListener(Processor.INPUT)
    public void consumerMessage(PostalOrder postalOrder) {
        LOGGER.info("START :: Postal Order :: Consumer :: MessageListener :: consumerMessage");
        postalOrderRepository.save(postalOrder);
    }
}
