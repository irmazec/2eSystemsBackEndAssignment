package com.example.backend_assignment.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="metar_data")
public class MetarData {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="message_type", length=50, nullable=false, unique=false)
    private String messageType;
	
	@Column(name="icao_identifier", length=4, nullable=false, unique=false)
    private String icaoIdentifier;
	
	@Column(name="issuance_time", length=50, nullable=false, unique=false)
    private Date issuanceTime;
	
	@Column(name="wind", length=50, nullable=false, unique=false)
    private String wind;
	
	@Column(name="horizontal_visibility", length=50, nullable=false, unique=false)
    private String horizontalVisibility;
	
	@Column(name="present_weather", length=50, nullable=false, unique=false)
    private String presentWeather;
	
	@Column(name="sky_cover", length=50, nullable=false, unique=false)
    private String skyCover;
	
	@Column(name="temperature_dewpoint", length=50, nullable=false, unique=false)
    private String temperatureDewpoint;
	
	@Column(name="altimeter_setting", length=50, nullable=false, unique=false)
    private String altimeterSetting;
	
	@Column(name="supplementary_information", length=50, nullable=false, unique=false)
    private String supplementaryInformation;
	
	@Column(name="trend_forecast", length=50, nullable=false, unique=false)
    private String trendForcast;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getIcaoIdentifier() {
		return icaoIdentifier;
	}

	public void setIcaoIdentifier(String icaoIdentifier) {
		this.icaoIdentifier = icaoIdentifier;
	}

	public Date getIssuanceTime() {
		return issuanceTime;
	}

	public void setIssuanceTime(Date issuanceTime) {
		this.issuanceTime = issuanceTime;
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

	public String getPresentWeather() {
		return presentWeather;
	}

	public void setPresentWeather(String presentWeather) {
		this.presentWeather = presentWeather;
	}

	public String getSkyCover() {
		return skyCover;
	}

	public void setSkyCover(String skyCover) {
		this.skyCover = skyCover;
	}

	public String getTemperatureDewpoint() {
		return temperatureDewpoint;
	}

	public void setTemperatureDewpoint(String temperatureDewpoint) {
		this.temperatureDewpoint = temperatureDewpoint;
	}

	public String getAltimeterSetting() {
		return altimeterSetting;
	}

	public void setAltimeterSetting(String altimeterSetting) {
		this.altimeterSetting = altimeterSetting;
	}

	public String getSupplementaryInformation() {
		return supplementaryInformation;
	}

	public void setSupplementaryInformation(String supplementaryInformation) {
		this.supplementaryInformation = supplementaryInformation;
	}

	public String getTrendForcast() {
		return trendForcast;
	}

	public void setTrendForcast(String trendForcast) {
		this.trendForcast = trendForcast;
	}

	@Override
	public String toString() {
		return messageType + " " + icaoIdentifier + " "
				+ issuanceTime + " " + wind + " " + horizontalVisibility
				+ " " + presentWeather + " " + skyCover + " "
				+ temperatureDewpoint + " " + altimeterSetting + " "
				+ supplementaryInformation + " " + trendForcast;
	}
	
	
	
}
