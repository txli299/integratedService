package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.repo.MachineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MachineService {
    private final MachineRepository machineRepository;
}
