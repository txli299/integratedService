package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.model.Machine;
import com.gomobile.integratedService.model.User;
import com.gomobile.integratedService.repo.MachineRepository;
import com.gomobile.integratedService.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MachineController {
    @Autowired
    MachineRepository machineRepository;
    private final OrderService _OrderService;

    public MachineController(OrderService orderService) {
        this._OrderService = orderService;
    }

    @GetMapping("/machine/allMachines")
    public List<Machine> getAllMachine(){
        return machineRepository.findAll();
    }

    @PostMapping("/machine/addMachine")
    public Machine addMachine(@RequestBody Machine machine){
        return machineRepository.save(machine);
    }

    @GetMapping("/machine/{mid}")
    public Machine getMachine(@PathVariable String mid){
        Optional<Machine> machine = machineRepository.findById(mid);
        if(machine.isPresent()){
            return machine.get();
        }else{
            throw new IllegalArgumentException("Machine does not exist when get machine");
        }
    }

    @PostMapping("/machine/activate")
    public ResponseEntity<String> activate(@RequestParam String userId, @RequestParam String mid) {
        try {
            _OrderService.activateNewOrder(userId,mid);

            return ResponseEntity.ok("Order activated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error activating order: " + e.getMessage());
        }
    }

    @PostMapping("/machine/deactivate")
    public ResponseEntity<String> deactivate(@RequestParam String userId, @RequestParam String orderId) {
        try {
            _OrderService.deactivateOrder(userId,orderId);

            return ResponseEntity.ok("Order deactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deactivating order: " + e.getMessage());
        }
    }

    @PostMapping("/machine/scheduledActivate")
    public ResponseEntity<String> scheduledActivate(@RequestParam String userId,
                                                    @RequestParam String mid,
                                                    @RequestParam double duration ) {
        try {
            _OrderService.scheduledActivate(userId,mid,duration);

            return ResponseEntity.ok("Order Created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error scheduledActivate order: " + e.getMessage());
        }
    }

}
