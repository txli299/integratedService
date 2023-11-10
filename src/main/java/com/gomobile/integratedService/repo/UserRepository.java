package com.gomobile.integratedService.repo;

import com.gomobile.integratedService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUid(String id);
}
