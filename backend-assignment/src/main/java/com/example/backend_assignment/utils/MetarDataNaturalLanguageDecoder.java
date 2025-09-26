package com.example.backend_assignment.utils;

import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.example.backend_assignment.model.MetarData;

public class MetarDataNaturalLanguageDecoder {
	public static String decode (MetarData metarData, Map<String, String> data) {
		StringBuilder sb = new StringBuilder();
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH)+1;
		int year = c.get(Calendar.YEAR);
		
		sb.append("Current available informations for the airport "
		+metarData.getAirport()+" ("+nullCheck(metarData.getICAOCode())+")\n");
		sb.append("Date: "+nullCheck(String.valueOf(metarData.getDay()))+"."+month+"."+year+"\n");
		
		for(Entry<String, String> e : data.entrySet()) {
			sb.append(StringUtils.capitalize(e.getKey())+": "+ nullCheck(e.getValue())+"\n");
		}

		return sb.toString();
	}
	
	public static String nullCheck(String data) {
		if (data == null) {
			return "N/A";
		}else {
			return data;
		}
	}
}
