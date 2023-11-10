package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.repo.CafeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;
}
