package com.example.muhammad.armairlinemanagementsystem.controller;

import com.example.muhammad.armairlinemanagementsystem.repository.BookingRepository;
import com.example.muhammad.armairlinemanagementsystem.service.BookingServiceImpl;
import com.example.muhammad.armairlinemanagementsystem.service.FlightServiceImpl;
import com.example.muhammad.armairlinemanagementsystem.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
public class BookingController {
    @Autowired
    FlightServiceImpl flightService;
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingServiceImpl bookingService;

    @GetMapping(value = "/flights/SearchFlight")
    public String searchFlights(){
        return "flight/searchFlight";
    }

    @PostMapping(value = "/flights/getAvailableFlight")
    public String getAvailableFlights(Model model, @RequestParam String departure, @RequestParam String destination,
                                      @RequestParam String departureDate) throws ParseException {
        model.addAttribute("flights", flightService.getAvailableFlightList(departure,destination,departureDate));

        return "bookings/create";

    }
    
    @PostMapping(value = "/booking/bookFlight")
    public String bookFlight(Model model, Authentication authentication){
        String email = userDetailsService.getSignedUser(authentication);

        return null; //"redirect:/booking/list";
        
    }
}
