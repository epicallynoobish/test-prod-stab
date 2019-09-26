package test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MyStepdefs {

    @Given("open main page")
    public void openMainPage() {
        open("https://xn--80az8a.xn--d1aqf.xn--p1ai/");
    }

    @Then("check page title")
    public void checkPageTitle() {
        $("title")
                .shouldHave(attribute("text", "Единая информационная система жилищного строительства"));
    }

    @Then("title is not 500 or 400")
    public void titleIsNot500or400() {
        $("title")
                .shouldNotHave(attribute("text", "Ошибка 500"));
        $("title")
                .shouldNotHave(attribute("text", "Ошибка 404"));
    }

    /*
    Given("open main page", () -> {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    });

    Then("check page title", () -> {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    });

    Then("title is not {int} or {int}", (Integer int1, Integer int2) -> {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    });
    */
}
