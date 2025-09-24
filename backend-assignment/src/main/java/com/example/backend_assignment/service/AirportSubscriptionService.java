package com.example.backend_assignment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_assignment.dto.StatusDTO;
import com.example.backend_assignment.dto.SubscriptionDTO;
import com.example.backend_assignment.model.Status;
import com.example.backend_assignment.model.Subscription;
import com.example.backend_assignment.repository.AirportSubscriptionsRepository;
import com.example.backend_assignment.utils.ValidIcaoCodeCheck;

@Service
public class AirportSubscriptionService implements SubscriptionServiceInterface <SubscriptionDTO> {
	
	@Autowired
	AirportSubscriptionsRepository repository;
	
	@Override
	public List<SubscriptionDTO> listAllSubscriptions() {
		return repository.findAll()
				.stream()
				.map(subscription -> new SubscriptionDTO(				
						subscription.getIcaoCode(),
						subscription.getStatus()
						)).collect(Collectors.toList());
	}

	@Override
	public void subscribeNewSubscriber(SubscriptionDTO subscriptionDTO) {
		if (ValidIcaoCodeCheck.isValidIcaoCode(subscriptionDTO.icaoCode())) {
			repository.save(repository.findByIcaoCode(subscriptionDTO.icaoCode())
					.orElseGet(() -> {
						Subscription s = new Subscription();
						s.setIcaoCode(subscriptionDTO.icaoCode());
						s.setStatus(null);
						return s;
					}
			));
		}
	}

	@Override
	public void deleteSubscriber(String codeName) {
		Subscription subscription = repository.findByIcaoCode(codeName).orElse(null);
		if (subscription != null) {
			repository.delete(subscription);
		}
	}
	
	public void setSubscriptionStatus(String icaoCode, StatusDTO statusDTO) {
		Subscription subscription = repository.findByIcaoCode(icaoCode)
				.orElseGet(() -> {
					Subscription s = new Subscription();
					s.setIcaoCode(icaoCode);
					return s;
				});
		subscription.setStatus(Status.getStatusFromValue(statusDTO.active()));
		repository.save(subscription);
	}

}
