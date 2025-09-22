package com.example.backend_assignment.service;

import java.util.*;

import com.example.backend_assignment.model.Subscription;

public interface SubscriptionServiceInterface<E> {
	public List<E> listAllSubscriptions();
	public void subscribeNewSubscriber(E subscription);
	public void deleteSubscriber(String codeName);
}
