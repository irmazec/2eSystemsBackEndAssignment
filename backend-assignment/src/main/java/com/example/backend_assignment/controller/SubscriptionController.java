package com.example.backend_assignment.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {
	
	@GetMapping("/subscriptions")
	public ArrayList<String> getZagrebAirportSubscriptions(){
		ArrayList<String> subscriptions = new ArrayList<String>();
		subscriptions.add("Maka");
		subscriptions.add("Paka");
		return subscriptions;
		
	}
	
	@PostMapping("/subscriptions")
	public void postZagrebAirportSubscription() {
		
	}
	
//	@DeleteMapping("/subscriptions")
//	public void deleteZagrebAirportSubscription() {
//		
//	}
	
	
	
}
