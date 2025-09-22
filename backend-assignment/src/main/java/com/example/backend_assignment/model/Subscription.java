package com.example.backend_assignment.model;

import jakarta.persistence.*;

@Entity
@Table(name="subscriptions")
public class Subscription {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="icao_code", length=4, nullable=false, unique=true)
    private String icaoCode;

	public Long getId() {
		return id;
	}

	public String getIcaoCode() {
		return icaoCode;
	}

	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}	
}
