package com.example.backend_assignment.dto;

public record MetarDataDTO(
		String timeStamp,
		String windStrength,
		int temperature,
		String overallVisibility
){

	public String timeStamp() {
		return timeStamp;
	}

	public String windStrength() {
		return windStrength;
	}

	public int temperature() {
		return temperature;
	}

	public String overallVisibility() {
		return overallVisibility;
	}
	
}
