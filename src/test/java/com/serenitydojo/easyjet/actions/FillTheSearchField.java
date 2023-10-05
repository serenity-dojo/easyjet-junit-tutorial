package com.serenitydojo.easyjet.actions;


import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.targets.Target;

import java.util.Map;

public class FillTheSearchField {
    private final String fieldName;

    Map<String, Target> SEARCH_FIELD =
            Map.of(
                    "Departure", Target.the("Departure airport").locatedBy("#input-button__departure"),
                    "Destination", Target.the("Destination airport").locatedBy("input-button__destination")
            );

    public FillTheSearchField(String fieldName) {
        this.fieldName = fieldName;
    }

    public static FillTheSearchField of(String fieldName) {
        return new FillTheSearchField(fieldName);
    }

    public Performable with(String value) {
        Target field = SEARCH_FIELD.get(fieldName);
        return Task.where("{0} enters the " + fieldName + " field with " + value,
                Click.on(field),

                Click.on("#selected-autocomplete-item a")
        );
    }
}
