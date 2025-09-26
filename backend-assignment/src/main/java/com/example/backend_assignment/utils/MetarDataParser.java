package com.example.backend_assignment.utils;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_assignment.model.MetarData;

import io.github.mivek.model.Cloud;
import io.github.mivek.model.WeatherCondition;
import io.github.mivek.model.trend.MetarTrend;
import io.github.mivek.service.MetarService;

@Service
public class MetarDataParser {
	
	@Autowired
	static MetarService metarService = MetarService.getInstance();
	
	public static MetarData parseMetarData(MetarData parsedData, String data){
		io.github.mivek.model.Metar decodedMetar;
		try {
			decodedMetar = metarService.decode(data);
		} catch (io.github.mivek.exception.ParseException e) {
			return null;
		}

		parsedData.setMessageType("METAR");
		parsedData.setIcaoCode(decodedMetar.getAirport().getIcao());
		parsedData.setAirport(decodedMetar.getAirport().getName());
		parsedData.setTimestamp(decodedMetar.getTime());
		parsedData.setDay(decodedMetar.getDay());
		parsedData.setWind(decodedMetar.getWind().getSpeed()+decodedMetar.getWind().getUnit().toString());
		parsedData.setHorizontalVisibility(decodedMetar.getVisibility().getMainVisibility());
		parsedData.setTemperature(String.valueOf(decodedMetar.getTemperature()));
		parsedData.setPressure(String.valueOf(decodedMetar.getAltimeter()));
		return parsedData;
	}
}
