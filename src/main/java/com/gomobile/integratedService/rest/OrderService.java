package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.model.Machine;
import com.gomobile.integratedService.model.Order;
import com.gomobile.integratedService.model.User;
import com.gomobile.integratedService.repo.MachineRepository;
import com.gomobile.integratedService.repo.UserRepository;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
  private final UserRepository _userRepository;
  private final MachineRepository _machineRepository;
  private final TaskScheduler _taskScheduler;

  public void activateNewOrder(String userId, String mid) {
    // Record current time
    LocalDateTime record = LocalDateTime.now();
    Order newOrder = new Order(mid, record);
    // Assign new Id to order
    newOrder.setId(UUID.randomUUID().toString());

    Optional<User> currUser =  _userRepository.findById(userId);
    Optional<Machine> currMachine = _machineRepository.findById(mid);

    if (currUser.isPresent()) {
      if (currMachine.isPresent()){
        // Add order to user
        User user = currUser.get();
        user.addOrder(newOrder);
        // Set machine to activated
        Machine machine = currMachine.get();
        machine.setActivated(true);

        _machineRepository.save(machine);
        _userRepository.save(user);


      }else{
        throw new IllegalArgumentException("machine is not existed");
      }

    }else{
      throw new IllegalArgumentException("User is not existed");
    }
  }

  public void emptyUserOrders(String userId){
    Optional<User> currUser =  _userRepository.findById(userId);
    if (currUser.isPresent()) {
      User user = currUser.get();
      user.setOrders(new ArrayList<>());
      _userRepository.save(user);
    }else{
      throw new IllegalArgumentException("User is not existed");
    }
  }

  public void deactivateOrder(String userId, String orderId) {
    Optional<User> currUser =  _userRepository.findById(userId);
    if (currUser.isPresent()) {
      User user = currUser.get();
      List<Order> orders = user.getOrders();

      for (int i = 0; i < orders.size(); i++) {
        if (orders.get(i).getId().equals(orderId)) {
          Order updatedOrder = orders.get(i);
          Optional<Machine> currMachine = _machineRepository.findById(updatedOrder.getMid());
          if (updatedOrder == null){
            throw new IllegalArgumentException("Order not found");
          }
          if (!currMachine.isPresent()){
            throw new IllegalArgumentException("Machine not found");
          }
          // Set machine to unactivated
          Machine machine = currMachine.get();
          machine.setActivated(false);
          _machineRepository.save(machine);
          // Update Order
          updatedOrder.setExpired(true);
          updatedOrder.setEndTime(LocalDateTime.now());

          // Deduct money
          double currMoney = user.getCredit();
          user.setCredit( (currMoney-calculateCost(updatedOrder)));
          orders.set(i, updatedOrder);
          break;
        }
      }
      _userRepository.save(user);
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
  public double calculateCost(double duration){
    // This rate needs to be changed
    double rate = 60.0;
    return rate * duration;

  }
  private double calculateTime(LocalDateTime start, LocalDateTime end){
    Duration duration = Duration.between(start, end);
    return duration.toHours() + duration.toMinutesPart() / 60.0;
  }

  public void scheduledActivate(String userId, String mid, double duration) {
    Optional<User> currUser =  _userRepository.findById(userId);
    Optional<Machine> currMachine = _machineRepository.findById(mid);
    // Set up new Order
    LocalDateTime record = LocalDateTime.now();
    Order newOrder = new Order(mid, record);
    newOrder.setId(UUID.randomUUID().toString());
    if (!currUser.isPresent()) {
      throw new IllegalArgumentException("User cannot be found");
    }else{
      if (!currMachine.isPresent()){
        throw new IllegalArgumentException("Machine not found");
      }else{
        User user = currUser.get();
        Machine machine = currMachine.get();
        // Set machine to Activated
        machine.setActivated(true);
        _machineRepository.save(machine);
        // Add order to user
        user.addOrder(newOrder);
        _userRepository.save(user);
        ScheduledFuture<?> scheduledTask = _taskScheduler.schedule(() -> {
          for (Order userOrder : user.getOrders()) {
            if (userOrder.equals(newOrder)) {
              // Update Order
              LocalDateTime endTime = LocalDateTime.now();
              userOrder.setEndTime(endTime);
              userOrder.setExpired(false);
              break;
            }
          }
          // Reset Machine
          Optional<Machine> newExpiredMachine = _machineRepository.findById(mid);
          Machine expiredMachine = newExpiredMachine.get();
          expiredMachine.setActivated(false);
          // Deduct Money
          double currMoney = user.getCredit();
          user.setCredit( (currMoney-calculateCost(duration)));
          _machineRepository.save(expiredMachine);

          _userRepository.save(user);
        }, new Date(System.currentTimeMillis() + (long) (duration * 3600 * 1000)));
      }
    }
  }
}
