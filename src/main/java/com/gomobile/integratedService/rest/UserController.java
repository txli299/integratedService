package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return "Hello" + name;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }
}
