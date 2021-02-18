package com.example.muhammad.armairlinemanagementsystem.service;

import com.example.muhammad.armairlinemanagementsystem.model.RegisterUserModel;
import com.example.muhammad.armairlinemanagementsystem.model.Role;
import com.example.muhammad.armairlinemanagementsystem.model.User;
import com.example.muhammad.armairlinemanagementsystem.repository.RoleRepository;
import com.example.muhammad.armairlinemanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, IUserService{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<com.example.muhammad.armairlinemanagementsystem.model.User> optionalUser = userRepository.findByUsername(email);

        if (optionalUser.isPresent()){
            com.example.muhammad.armairlinemanagementsystem.model.User user = optionalUser.get();


            List<String> roleList = new ArrayList<String>();
            for (Role role: user.getRoles()) {
                roleList.add(role.getName());
            }
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .disabled(user.isDisabled())
                    .accountLocked(user.isAccountLocked())
                    .roles(roleList.toArray(new String [0]))
                    .build();

        }else {
            throw new UsernameNotFoundException("Email not found");
        }

    }



    public int dobConversion(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int d = c.get(Calendar.DATE);
        LocalDate l1 = LocalDate.of(year, month, d);
        LocalDate now1 = LocalDate.now();
        Period diff1 = Period.between(l1, now1);

        return diff1.getYears();
    }


    @Override
    public User addUser(RegisterUserModel registerUserModel, String roleName) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse(registerUserModel.getDob());

        User user = new User();
        user.setUsername(registerUserModel.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserModel.getPassword()));
        Optional<Role> optionalRole = roleRepository.findByName(roleName);
        if (optionalRole.isPresent()){
            Role role = optionalRole.get();
            List<Role> roleList = new ArrayList<>();
            roleList.add(role);
            user.setRoles(roleList);
        }
        user.setFirstName(registerUserModel.getFirstName());
        user.setLastName(registerUserModel.getLastName());
        user.setDob(dob);
        user.setSex(registerUserModel.getSex());
        user.setAddress(registerUserModel.getAddress());
        user.setMobile(registerUserModel.getMobile());
        user.setTitle(roleName);

        userRepository.save(user);

        return user;
    }

    @Override
    public String getSignedUser(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findUserByUsername(email);
        if (user != null){
            return  email;
        }
        return null;
    }

    @Override
    public User updateUserDetails(RegisterUserModel registerUserModel, long id) {
        User u = userRepository.findById(id).get();

        u.setFirstName(registerUserModel.getFirstName());
        u.setLastName(registerUserModel.getLastName());
        u.setAddress(registerUserModel.getAddress());
        u.setMobile(registerUserModel.getMobile());

        userRepository.save(u);

        return u;
    }

}
