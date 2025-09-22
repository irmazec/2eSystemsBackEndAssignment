package com.example.backend_assignment.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_assignment.dto.MetarDTO;
import com.example.backend_assignment.model.Metar;
import com.example.backend_assignment.model.Subscription;
import com.example.backend_assignment.repository.*;

@Service
public class MetarDataStorageService implements DataStorageInterface <MetarDTO> {
	
	@Autowired
	MetarRepository metarRepository;
	@Autowired
	AirportSubscriptionsRepository airportRepository;

	@Override
	public MetarDTO retrieveStorageData(String codeName) {
		Subscription s = airportRepository.findByIcaoCode(codeName).orElse(null);
		if (s != null) {
			MetarDTO metarDTO = metarRepository.findBySubscriptionId(s.getId())
					.stream().map(metar -> new MetarDTO(
							metar.getData()
							)).collect(Collectors.toList()).getFirst();
			return metarDTO;
		}
		return null;
	}

	@Override
	public void storeStorageData(String codeName, MetarDTO data) {
		Subscription subscription = airportRepository.findByIcaoCode(codeName)
				.orElseGet(() -> {
				Subscription s = new Subscription();
				s.setIcaoCode(codeName);
				airportRepository.save(s);
				return s;
				});
		
		
		Metar metar = metarRepository.findBySubscriptionId(subscription.getId()).orElse(null);
		if (metar == null) {
			metar = new Metar();
			metar.setSubscription(subscription);
		}
		metar.setData(data.data());
		airportRepository.save(subscription);
		metarRepository.save(metar);
	}

}
