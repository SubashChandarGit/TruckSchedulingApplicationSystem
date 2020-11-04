package com.PostalOrder.Producer.Controller;

import com.PostalOrder.Producer.Model.PostalOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostalOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostalOrderController.class);


    @Autowired
    private MessageChannel output;

    @PostMapping("PO/add")
    public PostalOrder addPOInfo(@RequestBody PostalOrder postalOrder) {
        LOGGER.info("START :: Postal Order :: Producer :: PostalOrderController :: addPOInfo");
        Message<PostalOrder> message = MessageBuilder.withPayload(postalOrder).build();
        output.send(message);
        return postalOrder;
    }
}
