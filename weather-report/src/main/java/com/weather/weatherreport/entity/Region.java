package com.weather.weatherreport.entity;


import javax.persistence.*;

@Table(name = "region")
public class Region {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "regionId")
  private Long id;

  @Column(name = "country")
  private String city;

  @Column(name = "cityName")
  private String country;

  @Column(name = "sensors")
  private String sensors;
}
