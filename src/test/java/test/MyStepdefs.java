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
}
