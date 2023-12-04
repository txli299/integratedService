package com.gomobile.integratedService.model;

import lombok.Data;

import javax.crypto.Mac;
import java.util.List;

@Data
public class MachineDto {
  MachineType type;
  List<Machine> activated;
  List<Machine> notActivated;


  public MachineDto(MachineType type, List<Machine> activated, List<Machine> notActivated) {
    this.type = type;
    this.activated = activated;
    this.notActivated = notActivated;
  }
}
