package com.Truck.Service;

import com.Truck.Model.Truck;

import java.util.List;

public interface TruckService {
    public Truck addTruck(Truck truck);
    public List<Truck> getAllTruck();
    public Truck getTruckById(long id);
    public void deleteTruck(long id);
    public Truck getTruckByNumber(long number);
}
