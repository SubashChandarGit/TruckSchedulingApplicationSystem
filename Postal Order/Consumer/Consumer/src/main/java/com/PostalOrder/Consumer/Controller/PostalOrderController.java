package com.PostalOrder.Consumer.Controller;

import com.PostalOrder.Consumer.Exception.PostalOrderNotFoundException;
import com.PostalOrder.Consumer.Model.PostalOrder;
import com.PostalOrder.Consumer.Repository.PostalOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostalOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostalOrderController.class);

    @Autowired
    private PostalOrderRepository postalOrderRepository;

    @GetMapping("/PO/{id}")
    public PostalOrder getPOById(@PathVariable long id) throws PostalOrderNotFoundException {
        LOGGER.info("START :: Postal Order :: Consumer :: PostalOrderController :: getPOById");
        Optional<PostalOrder> postalOrderOptional = postalOrderRepository.findById(id);
        PostalOrder postalOrder = null;
        if(postalOrderOptional.isPresent()) {
            postalOrder = postalOrderOptional.get();
        }
        return postalOrder;
    }

    @GetMapping("/POByNumber/{number}")
    public PostalOrder getPOByNumber(@PathVariable long number) throws PostalOrderNotFoundException {
        LOGGER.info("START :: Postal Order :: Consumer :: PostalOrderController :: getPOByNumber");
        return postalOrderRepository.findByPoNumber(number);
    }
}
