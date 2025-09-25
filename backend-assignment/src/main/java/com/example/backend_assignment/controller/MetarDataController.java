package com.example.backend_assignment.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend_assignment.dto.MetarDTO;
import com.example.backend_assignment.model.Metar;
import com.example.backend_assignment.service.MetarDataStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

@RestController
public class MetarDataController {
	
	@Autowired
	MetarDataStorageService service;
	
	@GetMapping("/airport/{icaoCode}/METAR")
	public MetarDTO getLastMetarDataForAirport(@PathVariable String icaoCode,  @RequestParam (required = false) List<String> fields) {
		Optional<MetarDTO> resultMetarDTO = service.retrieveStorageData(icaoCode);
		if (resultMetarDTO.isEmpty()) {
			System.out.println("There's no data for this ICAO code.");
		}
		return resultMetarDTO.orElse(new MetarDTO("N/A"));
	}
	
	@PostMapping("/airport/{icaoCode}/METAR")
	public void postNewMetarDataForAirport(@PathVariable String icaoCode, @RequestBody MetarDTO metarDTO) {
		service.storeStorageData(icaoCode, metarDTO);
	}
}
