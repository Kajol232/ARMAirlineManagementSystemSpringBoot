package com.example.muhammad.armairlinemanagementsystem.controller;


import com.example.muhammad.armairlinemanagementsystem.model.Flight;
import com.example.muhammad.armairlinemanagementsystem.repository.FlightRepository;
import com.example.muhammad.armairlinemanagementsystem.repository.SeatRepository;
import com.example.muhammad.armairlinemanagementsystem.service.AircraftServiceImpl;
import com.example.muhammad.armairlinemanagementsystem.service.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FlightController {
    @Autowired
    private AircraftServiceImpl aircraftService;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightServiceImpl flightService;
    @Autowired
    private SeatRepository seatRepository;


    @GetMapping(value = "/flights/create")
    public String createFlights(Model model){
        model.addAttribute("aircrafts", aircraftService.getAvailableAircraftList());

        return "flight/create";
    }

    @PostMapping(value = "/flights/add")
    public String add(Model model, @RequestParam int aircraft , @RequestParam String departure,
                      @RequestParam String estArrivalTime, @RequestParam String destination,
                      @RequestParam String departureDate, @RequestParam double price) throws ParseException {


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        Date deptDate = dateFormat.parse(departureDate);
        Date estTime = dateFormat.parse(estArrivalTime);

        flightService.addFlight(aircraft, departure, estTime, destination, deptDate, price);

        model.addAttribute("message", "Flight created successfully");

        return "redirect:/flights/list";
    }

    @GetMapping(value = "/flights/list")
    public String listFlight(Model model){
        model.addAttribute("flights", flightService.getFlightList());

        return "flight/list";
    }

    @GetMapping(value = "/flights/details/{id}")
    public String flightDetails(Model model, @PathVariable("id") int id){
        model.addAttribute("flight", flightService.getFlightById(id));

        return "flight/flightDetails";
    }

    @GetMapping(value = "/flights/edit/{id}")
    public String editFlight(Model model, @PathVariable("id") int id){
        model.addAttribute("flight", flightService.getFlightById(id));

        return "flight/edit";
    }
    @PostMapping(value = "/flights/update")
    public String updateFlight(Model model, @RequestParam String a_model, @RequestParam int id , @RequestParam String departure,
                               @RequestParam String estArrivalTime, @RequestParam String destination,
                               @RequestParam String departureDate, @RequestParam double price) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");


        Date deptDate =dateFormat.parse (departureDate);
        Date estTime = dateFormat.parse(estArrivalTime);

        flightService.updateFlight(id,a_model, departure, estTime,destination,deptDate,price);

        return "redirect:/flights/list";
    }

    @GetMapping(value = "/flights/delete/{id}")
    public String remove(@PathVariable("id") int id){
        Flight flight = flightRepository.findById(id).get();
        flightRepository.delete(flight);
        seatRepository.deleteByFlight(flight);
        aircraftService.updateAircraftStatus(flight.getAircraft().getId(), false);


        return "redirect:/flights/list";
    }
    






}
