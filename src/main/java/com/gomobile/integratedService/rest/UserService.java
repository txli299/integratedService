package com.gomobile.integratedService.rest;
import com.gomobile.integratedService.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
}
