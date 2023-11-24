package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.model.Order;
import com.gomobile.integratedService.model.OrderDto;
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

    @GetMapping("user/{userId}/credit")
    public Double getCredit(@PathVariable String userId){
        Optional<User> currUser =  userRepository.findByUid(userId);
        if (currUser.isPresent()) {
            return currUser.get().getCredit();
        }else{
            throw new IllegalArgumentException("User does not exist");
        }
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable String userId){
        // Jeff, this is Dinabang Changing this line
        // This may need to change to findByUid
        Optional<User> currUser =  userRepository.findByUid(userId);

        if (currUser.isPresent()) {
            return currUser.get();
        }else{
            throw new IllegalArgumentException("User does not exist");
        }
    }

    @PatchMapping("/user/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User updatedUserDetails) {
        // Jeff, this is Dinabang Changing this line
        // This may need to change to findByUid
        Optional<User> currUserOptional = userRepository.findByUid(userId);

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

    @GetMapping("/user/currentOrders/{userId}")
    public ResponseEntity<?> currentOrders(@PathVariable String userId){
        try {
            List<OrderDto> result = _OrderService.getCurrentOrders(userId);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Empty order: " + e.getMessage());
        }
    }
}
