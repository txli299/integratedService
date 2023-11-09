package com.gomobile.integratedService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection= "cafe")
public class Cafe {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Coordinate coordinate;
    private List<Machine> machines;

    public Cafe(String name, Coordinate coordinate, List<Machine> machines) {
        this.name = name;
        this.coordinate = coordinate;
        this.machines = machines;
    }
}
