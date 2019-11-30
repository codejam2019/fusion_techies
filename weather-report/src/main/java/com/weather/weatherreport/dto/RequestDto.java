package com.weather.weatherreport.dto;

import java.util.List;

public class RequestDto {

	private Integer cityId;
	private Integer startTime;
	private Integer endTime;
	private List<String> query;
	private Integer sensorId;
	
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
	
	public List<String> getQuery() {
		return query;
	}
	public void setQuery(List<String> query) {
		this.query = query;
	}
	public Integer getSensorId() {
		return sensorId;
	}
	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
	}
	public RequestDto()
	{
		
	}
}
