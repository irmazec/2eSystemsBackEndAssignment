package com.example.backend_assignment.model;

import jakarta.persistence.*;

@Entity
@Table(name="metar")
public class Metar {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "subscription_id", nullable = false)
	private Subscription subscription;
	
	@OneToOne
	@JoinColumn(name = "metar_data_id", nullable = false)
    private MetarData metarData;

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public MetarData getMetarData() {
		return metarData;
	}

	public void setMetarData(MetarData metarData) {
		this.metarData = metarData;
	}
	
}
