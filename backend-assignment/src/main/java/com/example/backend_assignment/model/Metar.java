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
	
	@Column(name = "data", nullable = true, columnDefinition = "TEXT")
    private String data;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn (name="metar_data_id", nullable=true)
	private MetarData metarData;

	public MetarData getMetarData() {
		return metarData;
	}

	public void setMetarData(MetarData metarData) {
		this.metarData = metarData;
	}

	public Subscription getSubscription() {
		return subscription;
	}
	
	public Long getId() {
		return id;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
