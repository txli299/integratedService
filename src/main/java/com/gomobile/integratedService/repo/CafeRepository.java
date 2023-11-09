package com.gomobile.integratedService.repo;

import com.gomobile.integratedService.model.Cafe;
import com.gomobile.integratedService.model.Machine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CafeRepository extends MongoRepository<Cafe,String> {
    Optional<Cafe> findById(String id);
}
