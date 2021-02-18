package com.example.muhammad.armairlinemanagementsystem.controller;

import com.example.muhammad.armairlinemanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    final UserRepository userRepository;

    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/layout")
    public String layout(Model model){
        return "/layout";
    }

    @GetMapping(value = "/layout2")
    public String layout2(Model model){
        return "/layout2";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "/about";
    }

    @GetMapping("/contact")
    public String contact(Model model){
        return "/contact";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/")
    public String index(){
        return "/index";
    }

}
