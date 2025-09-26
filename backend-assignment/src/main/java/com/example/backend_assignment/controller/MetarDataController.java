package com.example.backend_assignment.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend_assignment.dto.MetarDTO;
import com.example.backend_assignment.service.MetarDataStorageService;

@RestController
public class MetarDataController {
	
	@Autowired
	MetarDataStorageService service;
	
	@GetMapping("/airport/{icaoCode}/METAR")
	public ResponseEntity<String> getLastMetarDataForAirport(@PathVariable String icaoCode,  @RequestParam (required = false) List<String> fields) {
		Optional<MetarDTO> resultMetarDTO = service.retrieveStorageData(icaoCode);
		if (resultMetarDTO.isEmpty()) {
			return ResponseEntity.ok("There's no data for this ICAO code.");
		}
		return ResponseEntity.ok(service.getMetarData(icaoCode, fields));
	}
	
	@PostMapping("/airport/{icaoCode}/METAR")
	public void postNewMetarDataForAirport(@PathVariable String icaoCode, @RequestBody MetarDTO metarDTO) {
		service.storeStorageData(icaoCode, metarDTO);
	}
}
