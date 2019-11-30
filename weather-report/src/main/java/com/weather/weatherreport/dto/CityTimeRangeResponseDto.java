package com.weather.weatherreport.dto;

import java.util.List;

public class CityTimeRangeResponseDto {

	public String cityName;
	
	List<WeatherDetails> weatherDetails;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	
	
	
}
