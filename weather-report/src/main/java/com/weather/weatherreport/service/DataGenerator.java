package com.weather.weatherreport.service;

import java.util.List;

import com.weather.weatherreport.dto.CityTimeRangeResponseDto;
import com.weather.weatherreport.dto.HottestColdestDto;
import com.weather.weatherreport.dto.ResponseDto;
import com.weather.weatherreport.dto.SensorResponseDto;
import com.weather.weatherreport.dto.WettiestDryestDto;

public class DataGenerator {

	ResponseDto respoonseDto = new ResponseDto();
	
	public CityTimeRangeResponseDto cityDetails ;
	public HottestColdestDto hottestColdestCity;
	public WettiestDryestDto wettiestDryestCity;
	public List<SensorResponseDto> sensorDetails;
	
}
