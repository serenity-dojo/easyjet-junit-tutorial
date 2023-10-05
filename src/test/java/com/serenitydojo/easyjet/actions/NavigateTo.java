package com.serenitydojo.easyjet.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;

public class NavigateTo {

    public static final Target ACCEPT_COOKIES_BUTTON = Button.withText("Yes, I agree");

    public static Performable theHomePage() {
        return Task.where("{0} opens the RyanAir home page",
                Open.url("https://www.ryanair.com/gb/en"),
                Click.on(ACCEPT_COOKIES_BUTTON)
        );
    }
}
