package com.weather.weatherreport.dto;

import java.util.List;

public class CityFilterDto {

	private Integer cityId;
	private Integer startTime;
	private Integer endTime;
	private List<String> sensorId;
	
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getStartTime() {
		return startTime;
	}
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}
	public Integer getEndTime() {
		return endTime;
	}
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}
	public List<String> getSensorId() {
		return sensorId;
	}
	public void setSensorId(List<String> sensorId) {
		this.sensorId = sensorId;
	}
	
}
