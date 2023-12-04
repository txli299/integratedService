package com.gomobile.integratedService.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection= "cafe")
public class CafeDto {


  public String id;
  public String name;
  public String address;
  public double distance;
  public List<MachineInfo> machines;

  public CafeDto(String id, String name,String address, double distance, List<MachineInfo> machines) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.distance = distance;
    this.machines = machines;
  }
}

