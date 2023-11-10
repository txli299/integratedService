package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.model.Machine;
import com.gomobile.integratedService.model.User;
import com.gomobile.integratedService.repo.MachineRepository;
import com.gomobile.integratedService.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MachineController {
    @Autowired
    MachineRepository machineRepository;

    @GetMapping("/machine/allMachines")
    public List<Machine> getAllMachine(){
        return machineRepository.findAll();
    }

    @PostMapping("/machine/addMachine")
    public Machine addMachine(@RequestBody Machine machine){
        return machineRepository.save(machine);
    }


}
