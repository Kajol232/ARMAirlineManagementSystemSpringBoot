package com.example.muhammad.armairlinemanagementsystem.service;

import com.example.muhammad.armairlinemanagementsystem.model.Aircraft;
import com.example.muhammad.armairlinemanagementsystem.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AircraftServiceImpl implements IAircraftService {
    @Autowired
    private AircraftRepository aircraftRepo;

    @Override
    public void addAircraft(Aircraft aircraft) {
        if(aircraft != null) {
            aircraft.setStatus(false);

            aircraftRepo.save(aircraft);
        }
    }

    @Override
    public List<Aircraft> getAircraftList() {
        return (List<Aircraft>) aircraftRepo.findAll();
    }

    @Override
    public List<Aircraft> getAvailableAircraftList() {
        List<Aircraft> availableAircraft = new ArrayList<>();
        List<Aircraft> aircraftList = (List<Aircraft>) aircraftRepo.findAll();

        for (Aircraft a:aircraftList) {
            if(a.getStatus() == false){
                availableAircraft.add(a);
            }

        }
        return availableAircraft;
    }

    @Override
    public Aircraft getAircraft(int id) {
        Aircraft aircraft = aircraftRepo.findById(id).get();
        return aircraft;
    }

    @Override
    public void updateAircraft(int id, String name, String model, int capacity) {
        Aircraft aircraft = getAircraft(id);
        if (aircraft != null) {
            aircraft.setName(name);
            aircraft.setAircraftModel(model);
            aircraft.setCapacity(capacity);
            aircraftRepo.save(aircraft);
        }
    }

    @Override
    public void updateAircraftStatus(int id, boolean status) {
        Aircraft aircraft = getAircraft(id);
        if(aircraft != null){
            aircraft.setStatus(status);
            aircraftRepo.save(aircraft);
        }

    }

}
