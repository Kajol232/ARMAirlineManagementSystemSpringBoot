package com.example.muhammad.armairlinemanagementsystem.service;

import com.example.muhammad.armairlinemanagementsystem.model.Flight;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IFlightService {
    void addFlight(int aircraft_id, String departure, Date estArrivalTime, String destination,
                   Date departureDate, double price);
    List<Flight> getFlightList();
    Flight getFlightById(int id);
    void updateFlight(int flight_id , String aircraft_model ,String departure, Date estArrivalTime, String destination,
                      Date departureDate, double price);
    List<Flight> getAvailableFlightList(String departure, String date, String destination) throws ParseException;

}
