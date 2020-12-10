package com.example.muhammad.armairlinemanagementsystem.service;

import com.example.muhammad.armairlinemanagementsystem.model.Aircraft;
import com.example.muhammad.armairlinemanagementsystem.model.Flight;
import com.example.muhammad.armairlinemanagementsystem.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FlightServiceImpl implements IFlightService {

    @Autowired
    CodeGeneratorServiceImpl codeGeneratorService;

    @Autowired
    SeatServiceImpl seatService;


    @Autowired
    AircraftServiceImpl aircraftService;

    @Autowired
    FlightRepository flightRepository;

    @Override
    public void addFlight(int aircraft_id, String departure, Date estArrivalTime, String destination,
                          Date departureDate, double price) {
        Aircraft a = aircraftService.getAircraft(aircraft_id);
        String regNum = codeGeneratorService.genFlightCode(a.getAircraftModel(), departure,destination);

        if (regNum != null){

            int seat = a.getCapacity();
            Flight flight = new Flight(regNum, departure, destination, departureDate, estArrivalTime,  seat, price, a);

            flightRepository.save(flight);
            seatService.addSeat(flight,seat);
            aircraftService.updateAircraftStatus(aircraft_id, true);

        }
    }

    @Override
    public List<Flight> getFlightList() {
        List<Flight> flights = (List<Flight>) flightRepository.findAll();

        return flights;
    }

    @Override
    public Flight getFlightById(int id) {
        return flightRepository.findById(id).get();
    }

    @Override
    public void updateFlight(int flight_id, String aircraft_model, String departure, Date estArrivalTime,
                             String destination, Date departureDate, double price) {

        Flight flight = getFlightById(flight_id);
        String regNum = codeGeneratorService.genFlightCode(aircraft_model, departure, destination);
        if(flight != null){
        Aircraft aircraft = flight.getAircraft();
        flight.setAircraft(aircraft);
        flight.setDeparture(departure);
        flight.setArrivalTime(estArrivalTime);
        flight.setDestination(destination);
        flight.setDepartureDate(departureDate);
        flight.setPrice(price);
        flight.setFlightRegNum(regNum);

        flightRepository.save(flight);

        }

    }

    @Override
    public List<Flight> getAvailableFlightList(String departure, String date, String destination) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        Date deptDate = dateFormat.parse(date);

        List<Flight> flightList = flightRepository.getAvailableFlights(departure,destination,deptDate);

        return flightList;
    }


}
