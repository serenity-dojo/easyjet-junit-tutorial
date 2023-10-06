package com.serenitydojo.easyjet.acceptancetests.cucumber.screenplay;

import com.google.common.collect.ImmutableMap;
import com.serenitydojo.easyjet.actions.FillTheSearchField;
import com.serenitydojo.easyjet.actions.NavigateTo;
import com.serenitydojo.easyjet.actions.SetItinerary;
import com.serenitydojo.easyjet.acceptancetests.cucumber.screenplay.ui.SearchForm;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.serenitydojo.easyjet.acceptancetests.cucumber.screenplay.ui.SearchForm.ERROR_MESSAGE;
import static java.util.Arrays.stream;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotEmpty;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchStepDefinitions {

    Locale locale;

    private static final Map<String, Performable> PAGE_NAVIGATION =
            Map.of(
                    "home", NavigateTo.theHomePage(),
                    "search", NavigateTo.theHomePage()
            );

    @Given("{actor} is on the {word} page")
    public void onTheSearchPage(Actor actor, String pageName) {
        actor.wasAbleTo(PAGE_NAVIGATION.get(pageName));
    }


    @When("{actor} wants to search for flights with the following details:")
    public void searchForFlights(Actor actor, Map<String, String> searchDetails) {

        searchDetails.forEach((key, value) -> actor.attemptsTo(
                FillTheSearchField.of(key).with(value)
        ));

        actor.attemptsTo(Click.on(".search-submit"));
    }

    // departure date => Please choose a departure date
    // destination => Please select a destination


    private static final Map<String, String> EXPECTED_VALIDATION_MESSAGE =
            ImmutableMap.of(
                    "departure date", "Please choose a departure date",
                    "destination", "Please select a destination",
                    "origin", "Please select an origin"
            );


    @Then("{actor} should be asked to enter the {}")
    public void shouldBeAskedToEnterTheDepartureDate(Actor actor, String fieldName) {
        String expectedMessage = EXPECTED_VALIDATION_MESSAGE.get(fieldName);
        String pageContents = actor.asksFor(Text.of("html"));

        assertTrue(
                pageContents.contains(expectedMessage),
                "Expected to see the message '" + expectedMessage + "' but didn't"
        );
    }

    @ParameterType(".*")
    public Locale country(String countryName) {
        return stream(Locale.getISOCountries())
                .map(code -> new Locale("", code, ""))
                .filter(locale -> locale.getDisplayCountry().equalsIgnoreCase(countryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown country " + countryName));
    }

    @Given("Terry lives in {country}")
    public void terryLivesInCountry(Locale country) {
        this.locale = country;
    }

    @Then("{actor} should be asked to select the travel dates")
    public void terryShouldBeAskedToSelectTheTravelDates(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(ERROR_MESSAGE, isNotEmpty())
        );
        Collection<String> errorMessages = actor.asksFor(Text.ofEach(ERROR_MESSAGE));
        assertThat(errorMessages).contains("Please select travel dates");

    }

    @When("{actor} wants to look for flights for the following itinerary:")
    public void wantsToLookForFlightsForTheFollowingItinerary(Actor actor, List<Map<String, String>> itinerary) {
        String departureCountry = itinerary.get(0).get("Departure Country");
        String departureAirport = itinerary.get(0).get("Departure Airport");
        String destinationCountry = itinerary.get(0).get("Destination Country");
        String destinationAirport = itinerary.get(0).get("Destination Airport");

        actor.attemptsTo(
                SetItinerary.from(departureCountry, departureAirport)
                            .to(destinationCountry, destinationAirport)
        );
    }

    @When("{actor} wants to look for {} flights for the following itinerary:")
    public void wantsToLookForOneWayOrReturnFlightsForTheFollowingItinerary(Actor actor,
                                                                            String tripType,
                                                                            List<Map<String, String>> itinerary) {

        String departureCountry = itinerary.get(0).get("Departure Country");
        String departureAirport = itinerary.get(0).get("Departure Airport");
        String destinationCountry = itinerary.get(0).get("Destination Country");
        String destinationAirport = itinerary.get(0).get("Destination Airport");

        actor.attemptsTo(
                SetItinerary.tripTypeTo(tripType),
                SetItinerary.from(departureCountry, departureAirport)
                            .to(destinationCountry, destinationAirport)
        );
    }

    @And("{actor} requests to see the available flights")
    public void terryRequestsToSeeTheAvailableFlights(Actor actor) {
        actor.attemptsTo(
                Click.on(SearchForm.SEARCH_BUTTON)
        );
    }

    public static Target RETURN_DATE_FIELD
                            = Target.the("Return date field")
                            .located(By.cssSelector("fsw-input-button"))
                            .containingText("Return");

    @Then("{actor} should be asked to select the return date")
    public void shouldBeAskedToSelectTheReturnDate(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(RETURN_DATE_FIELD, isVisible())
        );
        boolean fieldIsVisible = actor.asksFor(Visibility.of(RETURN_DATE_FIELD));
        assertTrue(fieldIsVisible);
    }

    @Then("{actor} should not be asked to select the return date")
    public void shouldNotBeAskedToSelectTheReturnDate(Actor actor) {
        boolean fieldIsVisible = actor.asksFor(Visibility.of(RETURN_DATE_FIELD));
        assertFalse(fieldIsVisible);
    }
}
