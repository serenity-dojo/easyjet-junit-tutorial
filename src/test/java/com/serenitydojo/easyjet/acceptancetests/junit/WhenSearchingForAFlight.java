package com.serenitydojo.easyjet.acceptancetests.junit;

import com.serenitydojo.easyjet.actions.NavigateTo;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.ui.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenSearchingForAFlight {

    @CastMember
    Actor actor;

    @BeforeEach
    void openHomePage() {
        actor.wasAbleTo(
                NavigateTo.theHomePage()
        );
    }

    // Return journeys require the departure and arrival dates
    @Test
    void theDepartureShouldBeGatwickByDefault() {

        String fromAirport = actor.asksFor(Value.of(".origin"));

        assertThat(fromAirport).isEqualTo("London Gatwick (LGW)");
    }


    @Test
    void theDestinationIsMandatory() {

        actor.attemptsTo(
                Click.on(Button.withText("Show flights"))
        );
        boolean errorMessageIsDisplay
                = actor.asksFor(Visibility.of(reminderMessage("Please select a destination")));

        assertThat(errorMessageIsDisplay).isTrue();
    }

    String reminderMessage(String message) {
        return "//div[text()='" + message + "']";
    }
}
