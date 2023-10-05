Feature: Searching for flights

  As a traveller
  I want to search for flights
  so that I can find the best flight for my needs.

  Rule: Return journeys require the departure and arrival dates
    Example: The one where Terry chooses Gatwick to Barcelona
      Given Terry is on the search page
      When Terry wants to look for flights for the following itinerary:
        | Departure Country | Departure Airport | Destination Country | Destination Airport |
        | United Kingdom    | Birmingham        | Spain               | Alicante            |
      And Terry requests to see the available flights
      Then Terry should be asked to select the travel dates

  Rule: One way journeys should not ask for the Return date
    Example: The one where Sue books a one way flight to Rome
        Given Sue is on the search page
        When Sue wants to look for one way flights for the following itinerary:
            | Departure Country | Departure Airport | Destination Country | Destination Airport |
            | United Kingdom    | Birmingham        | Spain               | Barcelona                |
        And Sue requests to see the available flights
        Then Sue should not be asked to select the return date

    Example: The one where Sue switches to a return flight
      Given Sue is on the search page
      When Sue wants to look for return flights for the following itinerary:
        | Departure Country | Departure Airport | Destination Country | Destination Airport |
        | United Kingdom    | Birmingham        | Spain               | Barcelona                |
      And Sue requests to see the available flights
      Then Sue should be asked to select the return date
