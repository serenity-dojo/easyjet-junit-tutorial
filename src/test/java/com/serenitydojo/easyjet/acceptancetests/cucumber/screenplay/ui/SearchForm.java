package com.serenitydojo.easyjet.acceptancetests.cucumber.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import org.openqa.selenium.By;

public class SearchForm {
    public static final By ERROR_MESSAGE = By.cssSelector("[data-ref=input-button__error]");
    public static final Target SEARCH_BUTTON = Button.withText("Search");
}
