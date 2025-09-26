# Entities

Entities represent a table stored in the database and can be persisted in the base.

The service layer uses entities and repository objects to store or fetch data from the database. DTO objects are modeled after the entities and sent back to the client.

This project contains three classes marked with the JPA `@Entity` annotation.
## Subscription

The subscription entity contains three attributes, or three database columns. An ID, a string representing an ICAO code, and an activity status. The status is further explained in the text.
## Status - enum

Status isn't an entity but represents the model for the subscription status. An enum represents a data type that enables a variable to be a predefined set of constants. In this case, `INACTIVE(0)` and `ACTIVE(1)`. If the client POST request that defines the status for a certain airport contains {"active": "0"}, then the status is mapped to inactive, and if "active" is set to "1", then the status is marked as active. This is done through a static method inside the enum `public static Status getStatusFromValue(String activeStatusString)`. If the given value isn't "0" or "1", then the status is set as `null`.
## Metar

Metar entity contains an ID, a string of METAR data, a Subscription entity association, and a MetarData entity association. The Subscription entity relationship is defined with a `@ManyToOne` relation, and the MetarData entity is defined with a `@OnetoOne` relation.
## MetarData

MetarData entity contains an id, a string message type, a string ICAO code, a string airport, a `LocalDate` timestamp, an int day, a string wind, a string horizontal visibility, a string weather, a string clouds, a string temperature, a string pressure, and a string trend forecast. Not all of the fields are used in this implementation of the application but were put in the model for possible future upgrades.
