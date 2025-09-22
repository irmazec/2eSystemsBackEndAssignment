package com.example.backend_assignment.utils;
import java.io.*;
import java.nio.file.*;
import java.nio.file.Path;
import java.util.*;

public class ValidIcaoCodeCheck {
	private static boolean firstCheck = true;
	private static final String COMMA_DELIMITER = ",";
	private static ArrayList<String> icaoCodes = new ArrayList<String>();
	
	
	public static boolean isValidIcaoCode(String icaoCode) {
		boolean isValidIcaoCode = false;
		if (firstCheck) {
			getListOfCodes();
			if (icaoCodes.isEmpty()) {
				isValidIcaoCode = false;
			}else {
				isValidIcaoCode = icaoCodes.contains(icaoCode);
			}
		}else {
			if (!icaoCodes.isEmpty()) {
				isValidIcaoCode = icaoCodes.contains(icaoCode);
			}
		}
		return isValidIcaoCode;
	}
	
	private static void getListOfCodes(){
		try (BufferedReader br = new BufferedReader(new InputStreamReader(ValidIcaoCodeCheck.class.getResourceAsStream("/data/iata-icao.csv")))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        List<String> values = Arrays.asList(line.split(COMMA_DELIMITER));
		        if (values.size() > 3) {
		        	String icaoCode = values.get(3).trim().replaceAll("^\"|\"$", "");;
		        	if (!icaoCode.isEmpty()) {
		        		icaoCodes.add(icaoCode);
		        	}
		        }     
		    }   
		    br.close();
		}catch(IOException exception) {
			System.out.println("Unfortunately the file containing ICAO Codes isn't available.");
		}
	}
}
