package com.example.muhammad.armairlinemanagementsystem.repository;

import com.example.muhammad.armairlinemanagementsystem.model.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Integer> {
    @Query(value = "SELECT * from Flight f where f.departure = :departure and f.destination =:destination " +
            "and date(f.departure_date) = date(:date) and f.seat > 0", nativeQuery = true)
    List<Flight> getAvailableFlights(String departure, String destination, Date date);

    Flight getFlightsByFlightRegNum(String flightNum);

}
