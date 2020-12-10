package com.example.muhammad.armairlinemanagementsystem.service;


import com.example.muhammad.armairlinemanagementsystem.model.Aircraft;

import java.util.List;

public interface IAircraftService {
    void addAircraft(Aircraft aircraft);
    List<Aircraft> getAircraftList();
    List<Aircraft> getAvailableAircraftList();
    Aircraft getAircraft(int id);
    void updateAircraft(int id, String name, String model, int capacity );
    void updateAircraftStatus(int id, boolean status);

}
