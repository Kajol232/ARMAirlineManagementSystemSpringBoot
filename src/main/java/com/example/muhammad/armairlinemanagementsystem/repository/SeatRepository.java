package com.example.muhammad.armairlinemanagementsystem.repository;

import com.example.muhammad.armairlinemanagementsystem.model.Flight;
import com.example.muhammad.armairlinemanagementsystem.model.Seat;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Integer> {
    void deleteByFlight(Flight flight);
}
