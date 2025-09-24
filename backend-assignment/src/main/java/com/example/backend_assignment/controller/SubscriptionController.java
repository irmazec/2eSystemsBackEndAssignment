package com.example.backend_assignment.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend_assignment.dto.MetarDTO;
import com.example.backend_assignment.dto.StatusDTO;
import com.example.backend_assignment.dto.SubscriptionDTO;
import com.example.backend_assignment.model.Status;
import com.example.backend_assignment.model.Subscription;
import com.example.backend_assignment.service.AirportSubscriptionService;

@RestController
public class SubscriptionController {
	
	@Autowired
	AirportSubscriptionService service;
	
	@GetMapping("/subscriptions")
	public List<SubscriptionDTO> getAirportSubscriptions(){
		return service.listAllSubscriptions();
	}
	
	@PostMapping("/subscriptions")
	public void postAirportSubscription(SubscriptionDTO subscriptionDTO) {
		service.subscribeNewSubscriber(subscriptionDTO);
	}
	
	@PostMapping("/subscriptions/{icaoCode}")
	public void postAirportSubscriptionStatus(@PathVariable String icaoCode, @RequestBody StatusDTO statusDTO) {
		service.setSubscriptionStatus(icaoCode, statusDTO);
	}
	
	@DeleteMapping("/subscriptions/{codeName}")
	public void deleteAirportSubscription(@PathVariable String codeName) {
		service.deleteSubscriber(codeName);
	}
}
