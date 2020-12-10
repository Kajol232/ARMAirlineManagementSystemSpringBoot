package com.example.muhammad.armairlinemanagementsystem.service;

import com.example.muhammad.armairlinemanagementsystem.model.Flight;
import com.example.muhammad.armairlinemanagementsystem.model.Seat;
import com.example.muhammad.armairlinemanagementsystem.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements ISeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public void addSeat(Flight flight, int capacity) {
        if (capacity != 0){
           int economy = (int) (capacity*0.6);
           int business = (int) (capacity *0.3);
           int firstClass = (int) (capacity * 0.1);

           Seat seat = new Seat(flight,capacity,economy,firstClass,business);

           seatRepository.save(seat);
        }

    }
}
