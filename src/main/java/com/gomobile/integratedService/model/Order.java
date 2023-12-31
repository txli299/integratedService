package com.gomobile.integratedService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
@Document(collection = "order")
public class Order {
  @Id
  private String id;
  private String mid;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private boolean expired;

  public Order(String mid, LocalDateTime startTime) {
    this.mid = mid;
    this.startTime = startTime;
    this.expired = false;
  }
}
