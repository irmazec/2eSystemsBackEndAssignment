# Controllers
___

In RESTful applications, controllers represent the layer closest to the client, the presentation layer. Each controller defines the way client requests are processed at some RESTful endpoint. Multiple HTTP request methods can be defined in each controller, so the application can process them in an according way.

The basic version of this application consists of two controllers: `SubscriptionController` and `MetarDataController`.
___

## SubscriptionController

The SubscriptionController defines an endpoint for subscribing/unsubscribing airports and listing existing subscriptions. It contains mapping for three HTTP request methods: GET, POST, and DELETE.

### GET requests

GET requests to the endpoint _/subscriptions_ are mapped to the method `public List<SubscriptionDTO> getAirportSubscriptions()`. This method retrieves a list of `SubscriptionDTO`. These are a simplified version of the Subscription entity.

This method calls the `AirportSubscriptionService` and its method `public List<SubscriptionDTO> listAllSubscriptions()`. The request handling is defined in the controller, but the list of airports is received through a service call.

### POST requests

POST requests to the endpoint are mapped to the `public void postAirportSubscription(SubscriptionDTO subscriptionDTO)` method. This method receives a `SubscriptionDTO` from the user and makes a call to the `AirportSubscriptionService` method `public void subscribeNewSubscriber(SubscriptionDTO subscriptionDTO)` to check the validity of the DTO and properly store it in the database.

### DELETE requests

DELETE requests are mapped to the endpoint _\subscriptions\{icaoCode}_ and the method `public void deleteAirportSubscription(@PathVariable String codeName)`. This method takes the ICAO code from the request path and delegates the responsibility of safely deleting the airport with the correct ICAO code to the service. The `AirportSubscriptionService` `public void deleteSubscriber(String codeName)` method deletes the airport with the code from the DELETE requests path.

### Extra tasks - status
To expand the Subscription with a status another POST mapping was made to the method `public void postAirportSubscriptionStatus(@PathVariable String icaoCode, @RequestBody StatusDTO statusDTO)` . This method receives a `StatusDTO` object from the client and calls the `public void setSubscriptionStatus(String icaoCode, StatusDTO statusDTO)` from the `AirportSubscriptionService` repository.
### Extra tasks - filtering and searching
In the extended version of this application filtering through active/inactive airports and searching an airport based on their ICAO code is also possible.

These were achieved with three more GET mappings in the controller.

- `@GetMapping("/subscriptions/active")` which is mapped to the `List<SubscriptionDTO> getActiveAirportSubscriptions()` and contains a call to the `AirportSubscriptionService` method `public List<SubscriptionDTO> findActiveAirportSubscriptions()`
- `@GetMapping("/subscriptions/inactive")` which is mapped to the `List<SubscriptionDTO> getInactiveAirportSubscriptions()` and contains a call to the `AirportSubscriptionService` method `public List<SubscriptionDTO> findInactiveAirportSubscriptions()`
- `@GetMapping("/subscriptions/{airportName}")` which is mapped to the `List<SubscriptionDTO> getMatchingAirportNames(@PathVariable String airportName)` and contains a call to the `AirportSubscriptionService` method `List<SubscriptionDTO> searchAirportSubscriptionsStartingWith(String airportName)`

## MetarDataController

The MetarDataController defines an endpoint for METAR data storage and retrieval. It contains mappings for two HTTP methods: GET and POST.

### GET requests

GET requests to the endpoint _airport/{icaoCode}/METAR_ are mapped to the method `public MetarDTO getLastMetarDataForAirport(@PathVariable String icaoCode)`. This method returns the last METAR data record previously stored as a MetarDTO. This is achieved through a call to the method `public MetarDTO retrieveStorageData(String codeName)` defined in the service `MetarDataStorageService`.

In the updated version of the task this method return a string of METAR data written out in natural language.

### POST requests

POST requests to the endpoint are mapped to the `public void postNewMetarDataForAirport(@PathVariable String icaoCode, @RequestBody MetarDTO metarDTO)`. This method extracts the ICAO code from the request path and passes it as a method parameter as well as converts the JSON payload into a MetarDTO. These are then passed to the `MetarDataStorageService` and its method `public void storeStorageData(String codeName, MetarDTO data)` that saves the data into the database and, in case the airport doesn't exist beforehand, creates a new airport subscription entity and saves it.