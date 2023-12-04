package com.gomobile.integratedService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "machine")
public class Machine {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private MachineType type;
    private String cafeId;
    private Boolean activated;

    public Machine(String name, MachineType type, String cafeId, Boolean activated) {
        this.name = name;
        this.type = type;
        this.cafeId = cafeId;
        this.activated = activated;
    }
}
