package com.weather.weatherreport.dto;

import java.util.List;

public class ResponseDto {
private CityTimeRangeResponseDto cityDetails ;
private HottestColdestDto hottestColdestCity;
private WettiestDryestDto wettiestDryestCity;
private List<SensorResponseDto> sensorDetails;

public CityTimeRangeResponseDto getCityDetails() {
	return cityDetails;
}
public void setCityDetails(CityTimeRangeResponseDto cityDetails) {
	this.cityDetails = cityDetails;
}
public HottestColdestDto getHottestColdestCity() {
	return hottestColdestCity;
}
public void setHottestColdestCity(HottestColdestDto hottestColdestCity) {
	this.hottestColdestCity = hottestColdestCity;
}
public WettiestDryestDto getWettiestDryestCity() {
	return wettiestDryestCity;
}
public void setWettiestDryestCity(WettiestDryestDto wettiestDryestCity) {
	this.wettiestDryestCity = wettiestDryestCity;
}
public List<SensorResponseDto> getSensorDetails() {
	return sensorDetails;
}
public void setSensorDetails(List<SensorResponseDto> sensorDetails) {
	this.sensorDetails = sensorDetails;
}

public ResponseDto(CityTimeRangeResponseDto cityDetails, HottestColdestDto hottestColdestCity,
		WettiestDryestDto wettiestDryestCity, List<SensorResponseDto> sensorDetails) {
	super();
	this.cityDetails = cityDetails;
	this.hottestColdestCity = hottestColdestCity;
	this.wettiestDryestCity = wettiestDryestCity;
	this.sensorDetails = sensorDetails;
}

public ResponseDto()
{
	
}

}
