package com.serenitydojo.easyjet.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.targets.Target;

public class NavigateTo {

    public static final Target ACCEPT_ALL_COOKIES_BUTTON = Target.the("Accept all cookies button")
                                                                 .locatedBy("#ensCloseBanner");

    public static Performable theHomePage() {
        return Task.where("{0} opens the EasyJet home page",
                Open.url("https://www.easyjet.com/en/"),
                Click.on("#ensCloseBanner")
        );
    }
}
