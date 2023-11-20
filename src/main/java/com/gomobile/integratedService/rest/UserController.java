package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.model.User;
import com.gomobile.integratedService.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    private final OrderService _OrderService;

    public UserController(OrderService orderService) {
        this._OrderService = orderService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return "Hello" + name;
    }

    @GetMapping("/user/allUsers")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable String userId){
        Optional<User> currUser =  userRepository.findById(userId);

        if (currUser.isPresent()) {
            return currUser.get();
        }else{
            throw new IllegalArgumentException("User does not exist");
        }
    }

    @PatchMapping("/user/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User updatedUserDetails) {
        Optional<User> currUserOptional = userRepository.findById(userId);

        if (currUserOptional.isPresent()) {
            User currUser = currUserOptional.get();
            // Update user details with the new values
            currUser.setCredit(currUser.getCredit() + updatedUserDetails.getCredit());
            // Save the updated user to the database
            return userRepository.save(currUser);
        } else {
            throw new IllegalArgumentException("User does not exist");
        }
    }

    @PostMapping("/user/addUser")
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }
    /*
        This is for develop purpose.
     */
    @PostMapping("/user/emptyOrders")
    public ResponseEntity<String> emptyOrders(@RequestParam String userId){
        try {
            _OrderService.emptyUserOrders(userId);

            return ResponseEntity.ok("Order Empty successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Empty order: " + e.getMessage());
        }
    }
}
