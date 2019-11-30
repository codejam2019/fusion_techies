package com.weather.weatherreport;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherReportController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String candidateForm(@PathVariable("formUniqueId") String formUniqueId) {
		
		return "welcome to weather forcast";
	}
	
	
}
