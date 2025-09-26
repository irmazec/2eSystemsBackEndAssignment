# METAR Data Natural Language Decoder

Decoding METAR data into natural language is used by the service layer to decode data so it provides a cleaner and more understandable overview of information to the user. This is implemented with the `MetarDataNaturalLanguageDecoder` class.

## `MetarDataNaturalLanguageDecoder`

This class contains one static method, `public static String decode (MetarData metarData, Map<String, String> data)`, that is used by the service layer before returning the data to the controller.

This method takes an existing `MetarData` object and a `Map<String, String>` object that represents the data fields that need to be decoded.

It uses the `StringBuilder` class for creating a String object.

It starts by fetching data from the `MetarData` object, which should be available for every airport (the airport name, ICAO code, and date). For date manipulation the `Calendar` class from the `java.util` library is used. Every piece of data (the product of getter functions from the `MetarData` class) is further checked with the helper function inside of the `MetarDataNaturalLanguageDecoder` class. The helper function `private static String nullCheck(String data)` returns data if data isn't `null` and returns "N/A" if it is.

After appending data that every airport must show, data from the `Map<String, String>` object is appended by iterating through the map entries.

```

for(Entry<String, String> e : data.entrySet()) {
			sb.append(StringUtils.capitalize(e.getKey())+": "+nullCheck(e.getValue())+"\n");
		}

```

The `StringBuilder` object is then turned into a string and returned to the service layer.