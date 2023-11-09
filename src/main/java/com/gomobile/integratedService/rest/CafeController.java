package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.model.Cafe;
import com.gomobile.integratedService.model.Machine;
import com.gomobile.integratedService.repo.CafeRepository;
import com.gomobile.integratedService.repo.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CafeController {
    @Autowired
    CafeRepository cafeRepository;

    @GetMapping("/cafe/allCafes")
    public List<Cafe> getAllMachine(){
        return cafeRepository.findAll();
    }
}
