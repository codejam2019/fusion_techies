package com.weather.weatherreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.weather.weatherreport.entity.*")   
@SpringBootApplication
public class WeatherReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherReportApplication.class, args);
	}

}
