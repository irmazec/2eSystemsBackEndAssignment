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
	
	@GetMapping("/subscriptions/active")
	public List<SubscriptionDTO> getActiveAirportSubscriptions(){
		return service.findActiveAirportSubscriptions();
	}
	
	@GetMapping("/subscriptions/inactive")
	public List<SubscriptionDTO> getInctiveAirportSubscriptions(){
		return service.findInactiveAirportSubscriptions();
	}
	
	@GetMapping("/subscriptions/{airportName}")
	public List<SubscriptionDTO> getMatchingAirportNames(@PathVariable String airportName){
		return service.searchAirportSubscriptionsStartingWith(airportName);
	}
	
	@PostMapping("/subscriptions")
	public void postAirportSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
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
