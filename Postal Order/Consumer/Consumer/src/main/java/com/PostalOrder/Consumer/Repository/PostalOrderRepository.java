package com.PostalOrder.Consumer.Repository;


import com.PostalOrder.Consumer.Model.PostalOrder;
import org.springframework.data.repository.CrudRepository;

public interface PostalOrderRepository extends CrudRepository<PostalOrder, Long> {

    public PostalOrder findByPoNumber(long number);
}
