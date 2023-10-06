package com.serenitydojo.easyjet.actions;

import com.google.common.collect.ImmutableMap;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;

import java.util.Map;

public class SetItinerary {
    private final String departureCountry;
    private final String departureAirport;

    private static final Map<String, Performable> TRIPTYPE_ACTION = ImmutableMap.of(
            "one way", Click.on(Button.withText("One way")),
            "return", Click.on(Button.withText("Return trip"))
    );

    private static final Target COUNTRY_NAME = Target.the("country '{0}'")
            .locatedBy("//span[@data-ref='country__name'][contains(.,'{0}')]");

    private static final Target AIRPORT_NAME = Target.the("airport '{0}'")
            .locatedBy("//span[@data-ref='airport-item__name'][contains(.,'{0}')]");

    private static final Target DEPARTURE_AIRPORT_FIELD =
            Target.the("Departure airport field").locatedBy("#input-button__departure");

    public SetItinerary(String departureCountry, String departureAirport) {
        this.departureCountry = departureCountry;
        this.departureAirport = departureAirport;
    }

    public static SetItinerary from(String departureCountry, String departureAirport) {
        return new SetItinerary(departureCountry, departureAirport);
    }

    public Performable to(String destinationCountry, String destinationAirport) {
        return Task.where("{0} defines an itinerary from " + departureAirport + " to " + destinationAirport,
                Click.on(DEPARTURE_AIRPORT_FIELD),
                Click.on(COUNTRY_NAME.of(departureCountry)),
                Click.on(AIRPORT_NAME.of(departureAirport)),
                Click.on(COUNTRY_NAME.of(destinationCountry)),
                Click.on(AIRPORT_NAME.of(destinationAirport))
        );
    }

    public static Performable tripTypeTo(String tripType) {
        return TRIPTYPE_ACTION.get(tripType);
    }
}
