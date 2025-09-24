package com.example.backend_assignment.utils;

import org.springframework.data.jpa.domain.Specification;

import com.example.backend_assignment.model.Status;
import com.example.backend_assignment.model.Subscription;

public class AirportSubscriptionSpecifications {

	public static Specification<Subscription> isActive() {
		return (root, query, builder) -> {
		      return builder.equal(root.get("status"), Status.ACTIVE);
		    };
	}
	
	public static Specification<Subscription> isInactive() {
		return (root, query, builder) -> {
		      return builder.equal(root.get("status"), Status.INACTIVE);
		    };
	}
	
	public static Specification<Subscription> matchesAirportTitle(String airportTitle) {
		return (root, query, builder) -> {
		      return builder.like(root.get("icaoCode"), "%"+airportTitle.toUpperCase()+"%");
		    };
	}
}
