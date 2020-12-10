package com.example.muhammad.armairlinemanagementsystem.controller;

import com.example.muhammad.armairlinemanagementsystem.model.RegisterUserModel;
import com.example.muhammad.armairlinemanagementsystem.model.Role;
import com.example.muhammad.armairlinemanagementsystem.model.User;
import com.example.muhammad.armairlinemanagementsystem.repository.RoleRepository;
import com.example.muhammad.armairlinemanagementsystem.repository.UserRepository;
import com.example.muhammad.armairlinemanagementsystem.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
   private PasswordEncoder passwordEncoder;


    @GetMapping(value = "/users/create")
    public String createPassenger(){
        return "user/create";
    }

    @PostMapping(value = "/users/register")
    public String add(Model model, RedirectAttributes redirectAttributes, RegisterUserModel registerUserModel) throws
            ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse(registerUserModel.getDob());

        if (!registerUserModel.getPassword().equals(registerUserModel.getConfirmPassword())){
            redirectAttributes.addAttribute("passError", "Password does not match");
        }
        else if(userRepository.existsByUsername(registerUserModel.getEmail())){
            redirectAttributes.addAttribute("emailError", "Email already exists");
        }
        else if((!registerUserModel.getEmail().contains("@")) || (!registerUserModel.getEmail().contains("."))){
            redirectAttributes.addAttribute("emailError", "Invalid email address");
        }
        else if (userDetailsService.dobConversion(dob) < 18){
            redirectAttributes.addAttribute("ageError", "You must be 18+ to register");
        }
        else if( registerUserModel.getPassword().isBlank()||registerUserModel.getPassword().isEmpty()){
            redirectAttributes.addAttribute("passError","Password can not be empty or blank ");
        }
        else{
            User user = userDetailsService.addUser(registerUserModel, "Customer");

            if(user != null) {
                redirectAttributes.addAttribute("success", "You have successfully register");
                redirectAttributes.addAttribute("successful", true);
            }

        }


        return "redirect:/users/create";

    }


    @GetMapping(value = "/myPage")
    public String myPage(Model model, Authentication authentication){

        String email = userDetailsService.getSignedUser(authentication);
        User u = userRepository.findUserByUsername(email);
        List<String> roles = new ArrayList<>();
        for(Role r:u.getRoles()){
            roles.add(r.getName());
        }

        model.addAttribute("user", u);
        model.addAttribute("roles", roles);
        return "admin/dashboard";
    }

    @GetMapping(value = "/users/moreinfo/{id}")
    public String users(@PathVariable("id") long id, Model model){
        User u = userRepository.findById(id).get();
        List<String> roles = new ArrayList<>();
        for(Role r : u.getRoles()){
            roles.add(r.getName());
        }
        model.addAttribute("user", u);
        model.addAttribute("roles", roles);
        return "user/details";
    }

    @GetMapping(value = "users/details/{id}")
    public String passengerDetails(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userRepository.findUserById(id));
        return "/user/details";
    }

    @GetMapping(value = "/users/profile")
    public String showUpdateForm(Model model, Authentication authentication){
        String email = userDetailsService.getSignedUser(authentication);
        model.addAttribute("user", userRepository.findUserByUsername(email));

        return "user/edit";
    }

    @PostMapping(value = "/users/update")
    public String updatePassengerProfile(Model model, @RequestParam long id, RegisterUserModel registerUserModel){
        User user = userDetailsService.updateUserDetails(registerUserModel,id);

        if(user != null) {
            String email = user.getUsername();
        }

        return "redirect:/myPage";
    }

    @GetMapping(value = "/users/changePassword")
    public String changePassword(Model model, Authentication authentication){
        String email = userDetailsService.getSignedUser(authentication);
        model.addAttribute("user", userRepository.findUserByUsername(email));

        return "user/editPassword";
    }

    @PostMapping(value = "/users/updatePassword")
    public String updatePassword(Model model, @RequestParam long id, @RequestParam String oldPassword,
                                 @RequestParam String newPassword, @RequestParam String confirmPassword,
                                 RedirectAttributes redirectAttributes){


        User user = userRepository.findById(id).get();
        String email = user.getUsername();

        if(!passwordEncoder.matches(oldPassword, user.getPassword())){
            redirectAttributes.addAttribute("error","Password is not correct...");
        }

        else if(!newPassword.equals(confirmPassword)){
            redirectAttributes.addAttribute("passError","Password does not match ");
        }
        else if(newPassword.isBlank()||newPassword.isEmpty()){
            redirectAttributes.addAttribute("passError","New Password can not be empty or blank ");
        }

        else{
            String new_password = passwordEncoder.encode(newPassword);
            user.setPassword(new_password);
            userRepository.save(user);
            return "redirect:/myPage";
        }

        return "redirect:/users/changePass";
    }

    @GetMapping(value = "/users/delete/{id}")
    public String removeUser(@PathVariable("id") long id, Model model) {

        User user = userRepository.findById(id).get();

        userRepository.delete(user);
        return "redirect:/users/list";
    }

    @GetMapping(value = "/users/list")
    public String Sellers(Model model){
        model.addAttribute("users", userRepository.findUsersByTitleEquals("Customer"));
        return "user/customersList";
    }

    @GetMapping("/admins/create")
    public String createAdmin(Model model){
        return "admin/create";
    }

    @PostMapping(value = "/admins/add")
    public String addAdmin(Model model, RedirectAttributes redirectAttributes, RegisterUserModel registerUserModel) throws ParseException {
        if (!registerUserModel.getPassword().equals(registerUserModel.getConfirmPassword())) {
            redirectAttributes.addAttribute("passError", "Password does not match ");
        } else if (userRepository.existsByUsername(registerUserModel.getEmail())) {
            redirectAttributes.addAttribute("emailError", "this email already exist");
        } else if ((!registerUserModel.getEmail().contains("@")) || (!registerUserModel.getEmail().contains("."))) {
            redirectAttributes.addAttribute("emailError", "the email is not a valid email address");
        } else if (registerUserModel.getPassword().isBlank() || registerUserModel.getPassword().isEmpty()) {
            redirectAttributes.addAttribute("passError", "Password can not be empty or blank ");
        } else {
            User user = userDetailsService.addUser(registerUserModel, "Admin");

            if (user != null) {
                redirectAttributes.addAttribute("success", "You have successfully register");
                redirectAttributes.addAttribute("successful", true);
            }
        }
        return "redirect:/admins/create";
    }

    @GetMapping(value = "/admins/list")
    public String staffs(Model model){
        model.addAttribute("admins", userRepository.findUsersByTitleEquals("Admin"));
        return "admin/list";
    }

    @GetMapping(value = "/admins/details/{id}")
    public String staffDetails(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userRepository.findById(id).get());
        return "/admin/adminDetails";
    }

    @GetMapping(value = "/admins/delete/{id}")
    public String remove(@PathVariable("id") long id, Model model) {

        User s = userRepository.findById(id).get();

        userRepository.delete(s);
        return "redirect:/admins/list";
    }

    @GetMapping(value = "/admins/assignRole/{id}")
    public String showAssignRoleForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("admin", userRepository.findById(id).get());
        return "admin/assignRole";
    }

    @PostMapping(value = "/admins/assignNewRole")
    public String updateRole(Model model, @RequestParam long id, @RequestParam String name) {

        User s= userRepository.findById(id).get();
        Role role = roleRepository.findByName(name).get();

        List<Role> roleList = s.getRoles();
        roleList.add(role);
        s.setRoles(roleList);

        userRepository.save(s);

        return "redirect:/admins/list";
    }


}
