package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.model.Order;
import com.gomobile.integratedService.model.User;
import com.gomobile.integratedService.repo.UserRepository;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
  private final UserRepository userRepository;

  public void activateNewOrder(String userId, String machineName) {
    LocalDateTime record = LocalDateTime.now();

    Order newOrder = new Order(machineName, record);
    newOrder.setId(UUID.randomUUID().toString());
    Optional<User> currUser =  userRepository.findById(userId);
    if (currUser.isPresent()) {
      User user = currUser.get();
      user.addOrder(newOrder);
      userRepository.save(user);
    }else{
      throw new IllegalArgumentException("User is not existed");
    }
  }

  public void emptyUserOrders(String userId){
    Optional<User> currUser =  userRepository.findById(userId);
    if (currUser.isPresent()) {
      User user = currUser.get();
      user.setOrders(new ArrayList<>());
      userRepository.save(user);
    }else{
      throw new IllegalArgumentException("User is not existed");
    }
  }

  public void deactivateOrder(String userId, String orderId) {
    Optional<User> currUser =  userRepository.findById(userId);
    if (currUser.isPresent()) {
      User user = currUser.get();
      List<Order> orders = user.getOrders();

      for (int i = 0; i < orders.size(); i++) {
        if (orders.get(i).getId().equals(orderId)) {
          Order updatedOrder = orders.get(i);
          if (updatedOrder == null){
            throw new IllegalArgumentException("Order not found");
          }
          updatedOrder.setExpired(true);
          updatedOrder.setEndTime(LocalDateTime.now());
          // The Credit should be set to Double instead of money for easy money calculation
          // So before discussion i am just putting int here
          int currMoney = user.getCredit();
          user.setCredit((int) (currMoney-calculateCost(updatedOrder)));
          orders.set(i, updatedOrder);
          break;
        }
      }
      userRepository.save(user);
    }else{
      throw new IllegalArgumentException("User is not existed");
    }
  }

  public double calculateCost(Order order){
    // This rate needs to be changed
    double rate = 60.0;
    double time = calculateTime(order.getStartTime(),order.getEndTime());
    return rate * time;

  }
  private double calculateTime(LocalDateTime start, LocalDateTime end){
    Duration duration = Duration.between(start, end);
    return duration.toHours() + duration.toMinutesPart() / 60.0;
  }
}
