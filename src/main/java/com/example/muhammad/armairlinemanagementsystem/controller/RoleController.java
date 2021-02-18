package com.example.muhammad.armairlinemanagementsystem.controller;

import com.example.muhammad.armairlinemanagementsystem.model.Role;
import com.example.muhammad.armairlinemanagementsystem.repository.RoleRepository;
import com.example.muhammad.armairlinemanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/roles/list")
    public String roles(Model model){
        model.addAttribute("roles",roleRepository.findAll());

        return "role/list";
    }

    @GetMapping("/roles/create")
    public String createRole(Model model){
        return "role/create";
    }

    @PostMapping("/roles/add")
    public String add(Model model, @RequestParam String name){
        Role role = new Role(name);
        roleRepository.save(role);

        return "redirect:/roles/list";
    }
    @GetMapping(value = "/roles/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("role", roleRepository.findById(id).get());
        return "role/edit";
    }

    @PostMapping(value = "/roles/update")
    public String updateRole(Model model, @RequestParam int id, @RequestParam String name) {

        Role role= roleRepository.findById(id).get();
        role.setName(name);

        roleRepository.save(role);

        return "redirect:/roles/list";
    }

    @GetMapping(value = "/roles/delete/{id}")
    public String remove(@PathVariable("id") int id, Model model) {

        Role role = roleRepository.findById(id).get();

        roleRepository.delete(role);
        return "redirect:/roles/list";
    }

}
