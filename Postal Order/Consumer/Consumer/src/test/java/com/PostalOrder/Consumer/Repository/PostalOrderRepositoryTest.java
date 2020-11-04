package com.PostalOrder.Consumer.Repository;

import com.PostalOrder.Consumer.Model.PostalOrder;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PostalOrderRepositoryTest {

    @Autowired
    private PostalOrderRepository postalOrderRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    
    @Test
    public void testGetPOById() {
        PostalOrder postalOrder = new PostalOrder();
        postalOrder.setPoNumber(14L);
        postalOrder.setPoLineNumber(15L);
        postalOrder.setPoDate(new Date());
        postalOrder.setUpcNumber(12L);
        postalOrder.setUpcName("upc");
        postalOrder.setOrderedQuantity(20L);
        PostalOrder saveInDb = testEntityManager.persist(postalOrder);
        PostalOrder getFromDb = postalOrderRepository.findById(saveInDb.getId()).get();
        assertThat(getFromDb).isEqualTo(saveInDb);
    }

    @Test
    void findByPoNumber() {
        PostalOrder postalOrder = new PostalOrder();
        postalOrder.setPoNumber(14L);
        postalOrder.setPoLineNumber(15L);
        postalOrder.setPoDate(new Date());
        postalOrder.setUpcNumber(12L);
        postalOrder.setUpcName("upc");
        postalOrder.setOrderedQuantity(20L);
        testEntityManager.persist(postalOrder);
        PostalOrder getFromDb = postalOrderRepository.findByPoNumber(14L);
        assertThat(getFromDb.getUpcName()).isEqualTo("upc");
    }
}