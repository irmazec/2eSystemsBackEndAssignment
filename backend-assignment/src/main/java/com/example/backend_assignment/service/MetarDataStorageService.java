package com.example.backend_assignment.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_assignment.dto.MetarDTO;
import com.example.backend_assignment.model.Metar;
import com.example.backend_assignment.model.MetarData;
import com.example.backend_assignment.model.Subscription;
import com.example.backend_assignment.repository.*;
import com.example.backend_assignment.utils.MetarDataNaturalLanguageDecoder;
import com.example.backend_assignment.utils.MetarDataParser;

@Service
public class MetarDataStorageService implements DataStorageInterface <MetarDTO> {
	
	@Autowired
	MetarRepository metarRepository;
	@Autowired
	MetarDataRepository metarDataRepository;
	@Autowired
	AirportSubscriptionsRepository airportRepository;
	
	@Override
	public Optional<MetarDTO> retrieveStorageData(String codeName) {
		Subscription s = airportRepository.findByIcaoCode(codeName).orElse(null);
		MetarDTO metarDTO = new MetarDTO("N/A");
		if (s != null) {
			 metarDTO = metarRepository.findBySubscriptionId(s.getId())
					.stream().map(metar -> new MetarDTO(
							metar.getData()
							)).collect(Collectors.toList()).getFirst();
		}
		return Optional.of(metarDTO);
	}
	
	public String getMetarData(String icaoCode, List<String> fields){
		HashMap<String, String> response = new HashMap<String, String>();
		Subscription subscription = airportRepository.findByIcaoCode(icaoCode).orElse(null);
		if (subscription == null) {
			return "No subscription exists for this airport!";
		}
		Metar metar = metarRepository.findBySubscriptionId(subscription.getId()).orElse(null);
		if (metar == null) {
			return "No data exists for this airport!";
		}
		MetarData metarData = metar.getMetarData();
		
		if (fields == null || fields.isEmpty()) {
			fields = List.of("time","temperature", "wind", "visibility");
		}
		
		for(String field : fields) {
			switch(field) {
			case "time": 
				response.put("timestamp", metarData.getTimestamp().toString()+" UTC");
				break;
			case "wind":
				response.put("wind", metarData.getWind());
				break;
			case "temperature":
				response.put("temperature", metarData.getTemperature() +" C");
				break;
			case "visibility":
				response.put("visibility", metarData.getHorizontalVisibility());
				break;
			}
		}
		
		String decodedText = MetarDataNaturalLanguageDecoder.decode(metarData, response);
		return decodedText;
	}

	@Override
	public void storeStorageData(String codeName, MetarDTO data) {
		Subscription subscription = airportRepository.findByIcaoCode(codeName)
				.orElseGet(() -> {
				Subscription s = new Subscription();
				s.setIcaoCode(codeName);
				return airportRepository.saveAndFlush(s);
				});
		
		Metar metar = metarRepository.findBySubscriptionId(subscription.getId())
				.orElseGet(() -> {
					Metar m = new Metar();
					m.setSubscription(subscription);
					return m;
				});
		metar.setData(data.data());
		
		MetarData metarData = metarDataRepository.findByIcaoCode(codeName).orElse(new MetarData());
		MetarDataParser.parseMetarData(metarData, data.data());
		metarDataRepository.save(metarData);
		metar.setMetarData(metarData);
		
		metarRepository.save(metar);
	}
}
