package com.serenitydojo.easyjet.actions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ErrorMessageIsDisplayed {
    public static Question<Boolean> withText(String expectedText) {
        return Question.about("error message").answeredBy(
                actor -> Text.of("html").answeredBy(actor).contains(expectedText)
        );
    }
}
