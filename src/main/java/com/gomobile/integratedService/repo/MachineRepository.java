package com.gomobile.integratedService.repo;

import com.gomobile.integratedService.model.Machine;
import com.gomobile.integratedService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MachineRepository extends MongoRepository<Machine, String> {
    Optional<Machine> findById(String id);
}
