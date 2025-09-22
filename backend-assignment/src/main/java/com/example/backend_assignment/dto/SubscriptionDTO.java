package com.example.backend_assignment.dto;

public record SubscriptionDTO (
		String icaoCode
){

	public String icaoCode() {
		return icaoCode;
	}
}
