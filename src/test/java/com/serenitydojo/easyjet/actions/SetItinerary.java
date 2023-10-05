package com.serenitydojo.easyjet.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

public class SetItinerary {
    private final String departureCountry;
    private final String departureAirport;

    private static Target COUNTRY_NAME = Target.the("country")
            .locatedBy("//span[@data-ref='country__name'][contains(.,'{0}')]");

    private static Target AIRPORT_NAME = Target.the("airport")
            .locatedBy("//span[@data-ref='airport-item__name'][contains(.,'{0}')]");

    public SetItinerary(String departureCountry, String departureAirport) {
        this.departureCountry = departureCountry;
        this.departureAirport = departureAirport;
    }

    public static SetItinerary from(String departureCountry, String departureAirport) {
        return new SetItinerary(departureCountry, departureAirport);
    }

    public Performable to(String destinationCountry, String destinationAirport) {
        return Task.where("{0} defines an itinerary from " + departureAirport + " to " + destinationAirport,
                Click.on("#input-button__departure"),
                Click.on(COUNTRY_NAME.of(departureCountry)),
                Click.on(AIRPORT_NAME.of(departureAirport)),
                Click.on(COUNTRY_NAME.of(destinationCountry)),
                Click.on(AIRPORT_NAME.of(destinationAirport))
        );
    }
}
