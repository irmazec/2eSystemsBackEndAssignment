package com.example.backend_assignment.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name="metar_data")
public class MetarData {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		
	@Column(name = "messageType", nullable = true)
	private String messageType;
		
    @Column (name="icaoCode", nullable=true)
	private String icaoCode;
    
    @Column (name="timestamp", nullable=true)
	private LocalTime timestamp;
    
    @Column(name="day", nullable=true)
    private int day;
    
    @Column (name="wind", nullable=true)
	private String wind;
    
    @Column (name="horizontalVisibility", nullable=true)
	private String horizontalVisibility;
    
    @Column (name="weather", nullable=true)
	private String weather;
    
    @Column (name="clouds", nullable=true)
	private String clouds;
    
    @Column (name="temperature", nullable=true)
	private String temperature;
    
    @Column (name="pressure", nullable=true)
	private String pressure;
    
    @Column (name="trendForecast", nullable=true)
	private String trendForecast;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getICAOCode() {
		return icaoCode;
	}

	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}

	public LocalTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalTime localTime) {
		this.timestamp = localTime;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getHorizontalVisibility() {
		return horizontalVisibility;
	}

	public void setHorizontalVisibility(String horizontalVisibility) {
		this.horizontalVisibility = horizontalVisibility;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
}
