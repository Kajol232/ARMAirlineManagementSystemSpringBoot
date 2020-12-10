package com.example.muhammad.armairlinemanagementsystem.service;

public interface ICodeGeneratorService {

   String genFlightCode(String aircraftModel, String departure, String destination);
   String genPassengerId(String passengerName);
   String genTicketId(String seatNum,String flightCode, String passengerId);
}
