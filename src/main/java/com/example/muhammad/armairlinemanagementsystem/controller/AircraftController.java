package com.example.muhammad.armairlinemanagementsystem.controller;

import com.example.muhammad.armairlinemanagementsystem.model.Aircraft;
import com.example.muhammad.armairlinemanagementsystem.repository.AircraftRepository;
import com.example.muhammad.armairlinemanagementsystem.service.AircraftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AircraftController {
    @Autowired
    private AircraftRepository aircraftRepository;
    @Autowired
    private AircraftServiceImpl aircraftService;

//connects to the create form to display the form; for creation of aircraft.
    @RequestMapping(value = "/aircrafts/create", method = RequestMethod.GET)
    public String createAircrafts(){

        return "aircraft/create";
    }

    //process the input from the form
    @PostMapping(value = "/aircrafts/add")
    public String add(Model model, @RequestParam String name, @RequestParam String aircraftModel,
                      @RequestParam int capacity){

        Aircraft aircraft = new Aircraft(name, aircraftModel,capacity);
                aircraftService.addAircraft(aircraft);

        //return successful message
        model.addAttribute("message","Aircraft created successfully");

        return "redirect:/aircrafts/list";

    }
    //returns aircraft list page.
    @GetMapping(value = "/aircrafts/list")
    public String listAircraft(Model model){
        model.addAttribute("aircrafts", aircraftService.getAircraftList());

        return "aircraft/list";
    }

    //returns edit aircraft edit page.
    @GetMapping(value = "/aircrafts/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("aircraft", aircraftService.getAircraft(id));

        return "aircraft/edit";
    }

    //processing input from the edit page.
    @PostMapping(value = "/aircrafts/update")
    public String updateAircraft(Model model, @RequestParam int id ,@RequestParam String name, @RequestParam String aircraftModel,
                                 @RequestParam int capacity){
        aircraftService.updateAircraft(id, name, aircraftModel, capacity);

        model.addAttribute("result", "Aircraft record updated successfully");

        return "redirect:/aircrafts/list";
    }

    @GetMapping(value = "/aircrafts/delete/{id}")
    public String remove(Model model, @PathVariable("id") int id){
        Aircraft aircraft = aircraftRepository.findById(id).get();
        aircraftRepository.delete(aircraft);

        return "redirect:/aircrafts/list";
    }

}
