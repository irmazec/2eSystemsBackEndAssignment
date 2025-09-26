# Services

Services represent an implementation of the business logic side of the application.

## Interfaces

Every service in this application implements a generic interface used to define a basic layout of the service logic. The interfaces are used as a possible future stepping stone for upgrades.

### Data Storage Interface

The `interface DataStorageInterface <E>` represents a basic layout for a data storage service. The data handled isn't defined, and it contains two methods that every implementation must implement.

```
	public Optional<E> retrieveStorageData(String codeName);
	public void storeStorageData(String codeName, E data);
```

### Subscription Service Interface

The `interface SubscriptionServiceInterface<E>` represents a basic layout for a subscription service. The type of subscription isn't defined, and it contains three methods every future implementation must follow.

```
	public List<E> listAllSubscriptions();
	public void subscribeNewSubscriber(E subscription);
	public void deleteSubscriber(String codeName);
```

A possible future upgrade would be making an abstract Subscription parent class that every subscription type would extend, so the interface could apply to only Subscription objects.

## Service implementations

Service implementations define the business logic behind each endpoint and use repository classes for interactions with the database. Classes marked with the repository annotation `@Repository` provide the mechanism for storage, retrieval, update, delete, and search operations on objects. For each entity in the database, a repository class was created.

### Airport Subscription Service

The `AirportSubscriptionService` class implements the `SubscriptionServiceInterface<SubscriptionDTO>` that holds all the business logic behind requests made to the /subscriptions endpoint.

#### public List<SubscriptionDTO> listAllSubscriptions() method

This method uses the `AirportSubscriptionRepository.findAll()` method that returns every Subscription entity in the base. This is turned into a stream of Subscription entities, and using the `SubscriptionDTOMapper` class, it is turned into a stream of SubscriptionDTO objects and collected into a list. The list is then returned to the controller.

`SubscriptionDTOMapper` is a helper class that maps Subscription objects into SubscriptionDTO objects to avoid code duplication.

#### public void subscribeNewSubscriber(SubscriptionDTO subscriptionDTO) method

This method is used for saving new airports into the database. It checks whether the SubscriptionDTO icaoCode is a valid ICAO code using the `ValidIcaoCodeCheck` class. If it is, then it calls the repository function `Optional<Subscription> findByIcaoCode(String codeName)` to check whether the airport already exists in the database. If it does, it is saved once again, and if it doesn't, a new Subscription object is created with the ICAO code and then persisted into the database.
#### public void deleteSubscriber(String codeName) method

This method checks if a Subscription object with an ICAO code that matches the codeName parameter exists in the base. If it does, the subscription is deleted with the repository delete method.

#### public void setSubscriptionStatus(String icaoCode, StatusDTO statusDTO) method

This method is called when the client wishes to update an existing subscription by adding a subscription status or add a new airport with the defined status.

It fetches an existing subscription that matches the icaoCode parameter or creates a new one. After a subscription was created or confirmed, the Subscription `public void setStatus(Status status)` method is called on the subscription object. A new status is created by calling the static method from the Status enum. The Subscription object is then saved into the database.

#### Filtering and searching

The filtering and searching was achieved by implementing the `public interface JpaSpecificationExecutor<Subscription>` in the `AirportSubscriptionRepository`. This is an interface that allows execution of Specifications based on the JPA criteria API. `Specification<T>` is an interface designed to encapsulate query logic in a type-safe and reusable manner. Three such specifications were defined in the `AirportSubscriptionSpecifications` helper class. This class contains three specifications: `isActive()`, `isInactive` and `matchesAirportTitle(String airportTitle)`.

These were then used in the following methods.

- `public List<SubscriptionDTO> findActiveAirportSubscriptions()` which calls the `AirportSubscriptionRepository.findAll(AirportSubscriptionSpecifications.isActive())` method, which maps the found Subscription objects into SubscriptionDTO objects

- `public List<SubscriptionDTO> findInactiveAirportSubscriptions()` which calls the `AirportSubscriptionRepository.findAll(AirportSubscriptionSpecifications.isInactive())` method, which maps the found Subscription objects into SubscriptionDTO objects

- `public List<SubscriptionDTO> searchAirportSubscriptionsStartingWith(String airportName)` which calls the `AirportSubscriptionRepository.findAll(AirportSubscriptionSpecifications.matchesAirportTitle(airportName))` method, which maps the found Subscription objects into SubscriptionDTO objects

### Metar Data Storage Service

The `MetarDataSubscriptionService` class implements the `DataStorageInterface<MetarDTO>` that holds all the business logic behind requests made to the /airport/{icaoCode}/METAR endpoint.

This service contains a variable for each of the three repositories `AirportSubscriptionRepository`, `MetarRepository`, and `MetarDataRepository`.

#### public Optional<MetarDTO> retrieveStorageData(String codeName) method

This method first checks if a subscription with an ICAO code matching the codeName parameter exists, and if it doesn't, it creates a MetarDTO with a data field saying "N/A". If the subscription exists, it creates a new MetarDTO object by finding the existing Metar object in the database with the `MetarRepository.findBySubscriptionId(Long id)` method.

#### public String getMetarData(String icaoCode, List<String> fields) method

This method retrieves METAR data for an airport in natural language. It returns the entire MetarData information or filters by the parameter fields list.

First it checks whether a subscription containing an ICAO Code that matches the icaoCode parameter exists. If it doesn't, it returns a warning message to the client. If it does exist, the method checks whether a Metar object with said Subscription object exists. If it doesn't, it returns a warning message, and if it does, a MetarData object is gotten from the Metar object.

If the fields parameter list is empty, it puts all the available data into the list: timestamp, temperature, visibility, and wind. If it isn't empty, only the data fields in the list are put into the `HashMap<String, String> response` where the key is the name of the attribute, and the value is a corresponding value of the attribute gotten from the MetarData object.

The MetarData object and the response object are then passed as parameters to the `MetarDataNaturalLanguageDecoder.decode(MetarData metarData, HashMap<String, String> response)` method. The method returns a string that is then returned to the client.

#### public void storeStorageData(String codeName, MetarDTO data) method

This method stores Metar objects into the database.

First it checks whether a subscription containing an ICAO Code that matches the icaoCode parameter exists. If it doesn't, it creates a new one and immediately saves and flushes it to the repository.

Then the method searches for an existing Metar object with the `MetarRepository.findBySubscriptionId(Long id)` function. If no Metar object already exists, it creates a new one.

It saves the data from the MetarDTO object to the new or existing Metar object and then searches for an existing MetarData object based on the ICAO code. If it doesn't exist, a new one is created. `MetarDataParser.parseMetarData(MetarData metarData, String data)` function is called to parse new METAR data. New data is then saved in the MetarDataRepository, and the Metar object is connected with the new/updated MetarData object.
