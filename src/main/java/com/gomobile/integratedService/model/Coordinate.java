package com.gomobile.integratedService.model;

import lombok.Data;

@Data
public class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
