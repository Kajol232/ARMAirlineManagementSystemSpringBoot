package com.example.muhammad.armairlinemanagementsystem.controller;

import com.example.muhammad.armairlinemanagementsystem.repository.BookingRepository;
import com.example.muhammad.armairlinemanagementsystem.repository.UserRepository;
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
    private FlightServiceImpl flightService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingServiceImpl bookingService;

    @GetMapping(value = "/flights/SearchFlight")
    public String searchFlights(){
        return "flight/searchFlight";
    }

    @PostMapping(value = "/flights/getAvailableFlight")
    public String getAvailableFlights(Model model, @RequestParam String departure, @RequestParam String destination,
                                      @RequestParam String departureDate) throws ParseException {
        model.addAttribute("flights", flightService.getAvailableFlightList(departure,departureDate,destination));

        return "bookings/selectFlight";

    }
    
    @PostMapping(value = "/booking/bookFlight")
    public String bookFlight(Model model, Authentication authentication){
        if(authentication == null){
            model.addAttribute("message", "Kindly Login");
            return "login";
        }else{
            String email = userDetailsService.getSignedUser(authentication);
            //model.addAttribute("flight", flightService.getFlightById(id));
            model.addAttribute("user", userRepository.findUserByUsername(email));

            return "bookings/create";
        }
    }

    @PostMapping(value = "/booking/createBooking/{id}")
    public String createBooking(Model model){return null;}
}
