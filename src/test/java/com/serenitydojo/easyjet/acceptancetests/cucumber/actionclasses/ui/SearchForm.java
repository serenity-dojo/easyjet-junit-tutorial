package com.serenitydojo.easyjet.acceptancetests.cucumber.actionclasses.ui;

import com.google.common.collect.ImmutableMap;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageComponent;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import net.serenitybdd.screenplay.targets.SearchableTarget;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

public class SearchForm extends PageComponent {

    private static final String COUNTRY_SELECTOR = "//span[@data-ref='country__name'][contains(text(),'{0}')]";
    private static final String AIRPORT_SELECTOR = "//span[@data-ref='airport-item__name'][contains(.,'{0}')]";

    private static final By ERROR_MESSAGE = By.cssSelector("[data-ref=input-button__error]");
    private static final SearchableTarget SEARCH_BUTTON = Button.withText("Search");

    private static final By RETURN_DATE_FIELD = By.cssSelector("[data-ref='input-button__dates-to']");

    @FindBy(id = "input-button__departure")
    WebElementFacade departureAirportField;



    private static final Map<String, Target> TRIPTYPE_BUTTON = ImmutableMap.of(
            "one way", Button.withText("One way"),
            "return", Button.withText("Return trip")
    );

    public void setDepartureAirport(String departureCountry, String departureAirport) {
        departureAirportField.click();
        findBy(COUNTRY_SELECTOR, departureCountry).click();
        findBy(AIRPORT_SELECTOR, departureAirport).click();
    }

    public void setDestinationAirport(String destinationCountry, String destinationAirport) {
        waitFor(COUNTRY_SELECTOR, destinationCountry);
        findBy(COUNTRY_SELECTOR, destinationCountry).click();
        findBy(AIRPORT_SELECTOR, destinationAirport).click();
    }

    public void searchForFlights() {
        find(SEARCH_BUTTON).click();
    }

    public List<String> getErrorMessages() {
        waitForRenderedElements(ERROR_MESSAGE);
        return findAll(ERROR_MESSAGE).texts();
    }

    public void setTripType(String tripType) {
        find(TRIPTYPE_BUTTON.get(tripType)).click();
    }

    public boolean returnDateFieldIsDisplayed() {
        return !findAll(RETURN_DATE_FIELD).isEmpty();
    }
}
