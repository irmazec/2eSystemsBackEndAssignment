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
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable = true)
	private Status status;
	
	public Long getId() {
		return id;
	}

	public String getIcaoCode() {
		return icaoCode;
	}

	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
