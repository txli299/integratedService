package com.gomobile.integratedService.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection= "cafe")
public class MachineInfo {
  private String type; // This will contain the machine type
  private int total; // This will contain the count of machines of this type
  private int activeCount;

  public MachineInfo(String type, int total, int activeCount) {
    this.type = type;
    this.total = total;
    this.activeCount = activeCount;
  }
}
