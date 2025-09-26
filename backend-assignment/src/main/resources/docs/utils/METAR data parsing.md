# METAR Data Parsing

The parsing of METAR data is achieved using https://github.com/mivek/MetarParser.git by author mivek. It is a Java library that provides a METAR and TAF data parser.

Link to the entire GitHub Repository: https://github.com/mivek/MetarParser.

## Parser implementation

The parser is registered as a service inside the Spring application with the `@Service` annotation. It is implemented as a `MetarDataParser` class.

The `MetarDataParser` is used by the `MetarDataStorageService` to safely parse METAR data into a new or existing MetarData object.

The class contains one public static method, `public static MetarData parseMetarData(MetarData parsedData, String data)`, that accepts a MetarData object and a String representation of the data that must be parsed.

The class contains one variable, an instance of the `MetarService` class from the MetarParser library. The method then calls the `MetarService` function `decode(String data`, which returns an `io.github.mivek.model.Metar` object.

From that object, new or existing data is then updated or set with setter functions from the `MetarData` class. The method returns a `MetarData` object that is then used by the service layer to save into the `MetarDataRepository`.