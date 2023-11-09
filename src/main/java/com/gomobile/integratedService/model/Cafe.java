package com.gomobile.integratedService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
public class Cafe {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Coordinate coordinate;
    private List<Machine> machines;
}
