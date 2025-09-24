package com.example.backend_assignment.model;

public enum Status {
	INACTIVE(0), ACTIVE(1);
	
	private int active;
	
	Status(int value){
		this.active = value;
	}
	
	public int getStatus() {
		return active;
	}
	
	public static Status getStatusFromValue(String activeStatusString) {
		if (("0").equals(activeStatusString)) {
			return INACTIVE;
		}else if (("1").equals(activeStatusString)) {
			return ACTIVE;
		}else {
			return null;
		}
	}
}
