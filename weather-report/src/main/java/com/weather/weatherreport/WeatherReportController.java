package com.weather.weatherreport;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherReportController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@PathVariable("formUniqueId") String formUniqueId) {
		
		return "welcome to weather forcast";
	}
	
	@RequestMapping(value = "/getCityWeatherDetails", method = RequestMethod.POST)
    public String citySensorDetails() {
		
		return "welcome to weather forcast";
	}
	
}
