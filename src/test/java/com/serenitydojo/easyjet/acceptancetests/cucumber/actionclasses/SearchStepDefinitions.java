package com.serenitydojo.easyjet.acceptancetests.cucumber.actionclasses;

import com.serenitydojo.easyjet.acceptancetests.cucumber.actionclasses.ui.SearchForm;
import com.serenitydojo.easyjet.actions.SetItinerary;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.ui.Button;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchStepDefinitions {

    SearchForm searchForm;

    @When("{} wants to look for flights for the following itinerary:")
    public void wantsToLookForFlightsForTheFollowingItinerary(String name, List<Map<String, String>> itinerary) {
        String departureCountry = itinerary.get(0).get("Departure Country");
        String departureAirport = itinerary.get(0).get("Departure Airport");
        String destinationCountry = itinerary.get(0).get("Destination Country");
        String destinationAirport = itinerary.get(0).get("Destination Airport");

        searchForm.setDepartureAirport(departureCountry, departureAirport);
        searchForm.setDestinationAirport(destinationCountry, destinationAirport);
    }

    @When("{} wants to look for {} flights for the following itinerary:")
    public void wantsToLookForOneWayOrReturnFlightsForTheFollowingItinerary(String actor,
                                                                            String tripType,
                                                                            List<Map<String, String>> itinerary) {

        String departureCountry = itinerary.get(0).get("Departure Country");
        String departureAirport = itinerary.get(0).get("Departure Airport");
        String destinationCountry = itinerary.get(0).get("Destination Country");
        String destinationAirport = itinerary.get(0).get("Destination Airport");


        searchForm.setTripType(tripType);
        searchForm.setDepartureAirport(departureCountry, departureAirport);
        searchForm.setDestinationAirport(destinationCountry, destinationAirport);
    }

    @And("{} requests to see the available flights")
    public void terryRequestsToSeeTheAvailableFlights(String actor) {
        searchForm.searchForFlights();
    }

    @Then("{} should be asked to select the travel dates")
    public void terryShouldBeAskedToSelectTheTravelDates(String actor) {
        List<String> errorMessages = searchForm.getErrorMessages();
        assertThat(errorMessages).contains("Please select travel dates");
    }

    @Then("{} should be asked to select the return date")
    public void shouldBeAskedToSelectTheReturnDate(String actor) {
        assertTrue(searchForm.returnDateFieldIsDisplayed());
    }

    @Then("{} should not be asked to select the return date")
    public void shouldNotBeAskedToSelectTheReturnDate(String actor) {
        assertFalse(searchForm.returnDateFieldIsDisplayed());
    }

}
