package test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.Condition;

public class MyStepdefs {

    @Given("open main page")
    public void openMainPage() throws NullPointerException, InterruptedException {
        open("https://xn--80az8a.xn--d1aqf.xn--p1ai/");
        //$("__next").waitUntil(visible, 50);
        assert(executeJavaScript("return document.readyState").equals("complete"));
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
