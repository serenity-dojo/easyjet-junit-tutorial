package com.serenitydojo.easyjet.acceptancetests.cucumber.actionclasses;

import io.cucumber.java.en.Given;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.screenplay.ui.Button;

public class NavigationStepDefinitions extends UIInteractionSteps {

    //     public static final Target ACCEPT_COOKIES_BUTTON = Button.withText("Yes, I agree");
    @Given("{word} is on the {word} page")
    public void onAGivenPage(String traveler, String pageName) {
        openUrl("https://www.ryanair.com/gb/en");
        $(Button.withText("Yes, I agree")).click();
    }

}
