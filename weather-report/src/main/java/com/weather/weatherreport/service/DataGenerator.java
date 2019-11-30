package com.weather.weatherreport.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weatherreport.dto.CityTimeRangeResponseDto;
import com.weather.weatherreport.dto.HottestColdestDto;
import com.weather.weatherreport.dto.ResponseDto;
import com.weather.weatherreport.dto.SensorResponseDto;
import com.weather.weatherreport.dto.WeatherDetails;
import com.weather.weatherreport.dto.WettiestDryestDto;

@Component
public class DataGenerator {

	ResponseDto respoonseDto = new ResponseDto();
	
	public CityTimeRangeResponseDto cityDetails = new CityTimeRangeResponseDto() ;
	public HottestColdestDto hottestColdestCity = new HottestColdestDto();
	public WettiestDryestDto wettiestDryestCity = new WettiestDryestDto();
	public List<SensorResponseDto> sensorDetails = new ArrayList<SensorResponseDto>();
	public List<WeatherDetails> weatherDetailList = new ArrayList<WeatherDetails>();
	
	@Autowired
	ObjectMapper objectMapper;
	
	public String generrateData()
	{
		cityDetails.setCityName("Pune");
		long currentTime = Instant.now().toEpochMilli();
		
		int rainFall = 10;
		int temp = 20;
		
		WeatherDetails weatherDetails = new WeatherDetails();
		weatherDetails.setRainfall(String.valueOf(rainFall));
		weatherDetails.setTemp(String.valueOf(temp));
		weatherDetails.setTimeStamp(String.valueOf(currentTime));
		weatherDetailList.add(weatherDetails);
		for(int i=0;i<10;i++)
		{
			rainFall = rainFall+5;
			temp = temp +1;
			
			weatherDetails.setRainfall(String.valueOf(rainFall));
			weatherDetails.setTemp(String.valueOf(temp));
			long nowPlus5Minutes = currentTime + TimeUnit.MINUTES.toMillis(5 * i);
			weatherDetails.setTimeStamp(String.valueOf(nowPlus5Minutes));
			weatherDetailList.add(weatherDetails);
			
		}
		cityDetails.setWeatherDetails(weatherDetailList);
		respoonseDto.setCityDetails(cityDetails);
		String responseDtoStr = "";
		try
		{
			responseDtoStr = objectMapper.writeValueAsString(respoonseDto);
			return responseDtoStr;
		}catch(Exception e)
		{
			
		}

		return null;
	}
	
		public static void main(String args[])
		{
			
		}
}
