package com.serenitydojo.easyjet.acceptancetests.cucumber.screenplay;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.chrome.ChromeOptions;

public class CommonStepDefinitions {
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());

        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--lang=fr");

    }

    @ParameterType(".*")
    public Actor actor(String actor) {
        return OnStage.theActorCalled(actor);
    }
}
