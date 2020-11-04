package com.Truck.Repository;


import com.Truck.Model.Truck;
import org.springframework.data.repository.CrudRepository;

public interface TruckRepository extends CrudRepository<Truck,Long> {

    public Truck findByNumber(long number);
}
