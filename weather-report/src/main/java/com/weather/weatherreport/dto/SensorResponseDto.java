package com.weather.weatherreport.dto;

public class SensorResponseDto {

	public String temp;
	public String rainfall;
	public String timeStamp;
	
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getRainfall() {
		return rainfall;
	}
	public void setRainfall(String rainfall) {
		this.rainfall = rainfall;
	}
	
}
