package com.example.backend_assignment.dto;

import com.example.backend_assignment.model.Status;

public record SubscriptionDTO (
		String icaoCode,
		Status status
){

	public String icaoCode() {
		return icaoCode;
	}
	
	public Status status() {
		return status;
	}
}
