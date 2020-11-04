package com.Truck.Controller;

import com.Truck.Exception.TruckNotFoundException;
import com.Truck.Model.Truck;
import com.Truck.Model.TruckPojo;
import com.Truck.Service.TruckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TruckController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TruckController.class);


    @Autowired
    private TruckService truckService;

    @PostMapping("/Truck")
    public Truck addTruck( @RequestBody TruckPojo truckPojo) {
        LOGGER.info("START :: Truck :: TruckServiceImpl :: addTruck");
        Truck truck = new Truck();
        truck.setName(truckPojo.getName());
        truck.setNumber(truckPojo.getNumber());
        truck.setType(truckPojo.getType());
        truckService.addTruck(truck);
        return truck;
    }

    @GetMapping("/Trucks")
    public List<Truck> getAllTrucks(){
        LOGGER.info("START :: Truck :: TruckServiceImpl :: getAllTrucks");
        return  truckService.getAllTruck();
    }

    @GetMapping("/Truck/{id}")
    public Truck getTruckById(@PathVariable long id) throws TruckNotFoundException {
        LOGGER.info("START :: Truck :: TruckServiceImpl :: getTruckById");
        return truckService.getTruckById(id);
    }

    @GetMapping("/TruckByNumber/{number}")
    public Truck getTruckByNumber(@PathVariable long number) throws TruckNotFoundException {
        LOGGER.info("START :: Truck :: TruckServiceImpl :: getTruckByNumber");
        return truckService.getTruckByNumber(number);
    }

    @PutMapping("/Truck/{id}")
    public void updateTruck(@RequestBody TruckPojo truckPojo,@PathVariable long id) throws TruckNotFoundException {
        LOGGER.info("START :: Truck :: TruckServiceImpl :: updateTruck");
        truckService.getTruckById(id);
        Truck truck = new Truck();
        truck.setName(truckPojo.getName());
        truck.setNumber(truckPojo.getNumber());
        truck.setType(truckPojo.getType());
        truckService.addTruck(truck);
    }

    @DeleteMapping("Truck/{id}")
    public void deleteTruck(@PathVariable long id) throws TruckNotFoundException {
        LOGGER.info("START :: Truck :: TruckServiceImpl :: deleteTruck");
        truckService.deleteTruck(id);
    }
}
