# Data Transfer Objects

DTOs, or Data Transfer Objects, are objects that carry data between processes in order to reduce the number of method calls.

They represent the model sent from the client and provide only relevant information to the client.

In this application three DTOs are used and are modeled with `Record` classes in Java, as they are immutable and useful for holding data.

## SubscriptionDTO

SubscriptionDTO represents the Subscription entity and is sent from the client when the client wants to subscribe to a new airport. A list of SubscriptionDTOs is returned to the client when accessing a list of available/active/inactive airports or when searching subscribed airports.

The SubscriptionDTO contains an icaoCode and a subscription status.

## StatusDTO

StatusDTO represents the Status enum and contains only a string that represents whether a subscription is active or not.

## MetarDTO
MetarDTO represents the Metar object and contains the Metar data in string format.