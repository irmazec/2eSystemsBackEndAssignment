package com.example.backend_assignment.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.backend_assignment.dto.SubscriptionDTO;
import com.example.backend_assignment.model.Subscription;

@Service
public class SubscriptionDTOMapper implements Function<Subscription, SubscriptionDTO>{

	@Override
	public SubscriptionDTO apply(Subscription subscription) {
		return new SubscriptionDTO(				
				subscription.getIcaoCode(),
				subscription.getStatus()
				);
	}
	
}
