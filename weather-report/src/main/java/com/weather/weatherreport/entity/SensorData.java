package com.weather.weatherreport.entity;

import javax.persistence.*;

@Entity(name = "sensorData")
public class SensorData {

  @Id
  @Column(name = "sensiorId")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long sensorId;

  @Column(name = "createdTime")
  private Long createdTime;

  @Column(name = "temrature")
  private double temperature;

  @Column(name = "rainfall")
  private double rainFall;

  @Column(name = "regionName")
  private String regionName;
}
