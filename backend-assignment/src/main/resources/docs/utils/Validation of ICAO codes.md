# Validation of ICAO codes

As a simulation of implementing a validity check for the ICAO codes, the`ValidIcaoCodeCheck` class was implemented.

Most APIs that offer an entire list of available ICAO codes are subscription/payment only, so this application uses a subset of the available codes, which are stored in a CSV file.
___

## ValidIcaoCodeCheck

The class is made up of two static methods, one private and one public. The public method is used by the service layer to check whether an ICAO code is valid or not. The private method is used inside the class to parse the CSV data file into a list of available codes.

The CSV file was downloaded from a public GitHub repository available here: https://github.com/ip2location/ip2location-iata-icao.git

### `private static void `getListOfCodes()`

This method uses the `BufferedReader` class to parse the CSV file in the `src/main/resources/data` folder. It fetches the `iata-icao.csv` file and converts it into an input stream. It splits every string in the stream into a list of strings using the comma delimiter. It takes the fourth value of every list (the one representing the ICAO code value) and trims it. The value, if it exists, is then stored in the list of ICAO codes. The list is a private static class variable available for future uses.

### `public static boolean isValidIcaoCode(String icaoCode)`

This method is used by the service layer each time a new POST request is made to the /subscriptions endpoint. It accepts a string value that needs to be checked and returns a `boolean` value `true` when the input string is an existing ICAO code and `false` when it isn't.

The method first checks if this is the first time the method is being called, and if it is, it fetches the list of available ICAO codes with the `getListOfCodes()` method. If it isn't, it uses the existing list of codes saved previously by the class.

If the list of codes contains the input string, it returns true; otherwise, it returns false.