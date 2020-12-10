package com.example.muhammad.armairlinemanagementsystem.service;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class CodeGeneratorServiceImpl implements  ICodeGeneratorService{
    private String code;
    @Override
    public String genFlightCode(String aircraftModel, String departure, String destination) {
        code = departure.substring(0,2).toUpperCase() + "-" + destination.substring(0,3).toUpperCase() + "-" + aircraftModel;

        return code;
    }

    @Override
    public String genPassengerId(String passengerName) {
        String sub = passengerName.substring(0,3).toUpperCase();
        Random rand = new Random();
        int randNum = rand.nextInt(1000);
        code = sub + randNum;
        return code;
    }

    @Override
    public String genTicketId(String seatNum, String flightCode, String passengerId) {
        code = flightCode + "/" + passengerId + "/" + seatNum;
        return code;
    }
}
