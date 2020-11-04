package com.Truck.Service;

import com.Truck.Model.Truck;

import com.Truck.Repository.TruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TruckServiceImpl implements TruckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TruckServiceImpl.class);

    @Autowired
    private TruckRepository truckRepository;

    @Override
    public Truck addTruck(Truck truck) {
        LOGGER.info("START :: Truck :: TruckServiceImpl :: addTruck");
        truckRepository.save(truck);
        return truck;
    }

    @Override
    public List<Truck> getAllTruck() {
        LOGGER.info("START :: Truck :: TruckServiceImpl :: getAllTruck");
        return (List<Truck>) truckRepository.findAll();
    }

    @Override
    public Truck getTruckById(long id) {
        LOGGER.info("START :: Truck :: TruckServiceImpl :: getTruckById");
        Optional<Truck> truckOptional = truckRepository.findById(id);
        Truck truck = null;
        if(truckOptional.isPresent()){
            truck = truckOptional.get();
        }
        return truck;
    }

    @Override
    public void deleteTruck(long id) {
        LOGGER.info("START :: Truck :: TruckServiceImpl :: deleteTruck");
        truckRepository.deleteById(id);
    }

    @Override
    public Truck getTruckByNumber(long number) {
        LOGGER.info("START :: Truck :: TruckServiceImpl :: getTruckByNumber");
        return truckRepository.findByNumber(number);
    }
}
