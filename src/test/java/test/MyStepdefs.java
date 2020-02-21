package test;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.openqa.selenium.support.ui.ExpectedConditions.and;

public class MyStepdefs {

    /*@Before public void getLogs() {

    }*/

    @Given("open main page")
    public void openMainPage() throws NullPointerException, InterruptedException {
        System.out.print("Открытие страницы...");
        open("http://test-web/hse/management/contracts");
        //$("__next").waitUntil(visible, 50);
        assert(executeJavaScript("return document.readyState").equals("complete"));
        System.out.println(url().toString());
    }

    @Then("check page title")
    public void checkPageTitle() {
        $("title")
                .shouldHave(attribute("text", "ГПН"));
        System.out.print("Заголовок страницы = ");
        System.out.print($("title").getText());
    }

    @Then("title is not 500 or 400")
    public void titleIsNot500or400() {
        $("title")
                .shouldNotHave(attribute("text", "Ошибка 500"));
        $("title")
                .shouldNotHave(attribute("text", "Ошибка 404"));
        System.out.println("Загружено ОК");
    }

    @Then("status is ok")
    public void status_is_ok() {
        $("title")
                .shouldNotHave(attribute("text", "Ошибка 500"));
        $("title")
                .shouldNotHave(attribute("text", "Ошибка 404"));
    }

    @Given("main page is opened")
    public void main_page_is_opened() {
        System.out.print("Открывается URL ... ");
        open("http://test-web/hse/management/contracts");
        assert(executeJavaScript("return document.readyState").equals("complete"));
        boolean a = false;
        //Condition.and(((url().contains("http://test-web/hse/management/contracts"))));
        System.out.println(url().toString());
    }

    @Given("i have HSE_UID_Customer and HSE_Contract_EOL roles")
    public void i_have_HSE_UID_Customer_and_HSE_Contract_EOL_roles() {
        refresh();
        //$("#button").click();
        assert(($("#button > span.text-truncate").getText().contentEquals("HSE_UID_Customer")));
        Condition.and (String.valueOf($("#button > span.text-truncate").getText().contentEquals("HSE_Contract_EOL")));
        System.out.print("Роли = ");
        System.out.print($("#button > span.text-truncate").getText());
        System.out.print("/");
        System.out.println($("#button > span.text-truncate").getText());
    }

    @When("i click +Create button")
    public void i_click_Create_button() {
        System.out.println("Жмякаем на +Создать...");
        $("body > app-root > app-management > div > div > app-side-panel > div > div.content-container.show > div > app-management-side-panel > div:nth-child(3) > div.buttons.mb-2 > app-button.text-nowrap")
                .click();
    }

    @Then("modal window is open")
    public void modal_window_is_open() {
        assert ($(By.className("modal-header")).isDisplayed());
        Condition.and (String.valueOf(($(By.className("modal-title")).getText())
                .contentEquals("Инициирование договора")));
        // Write code here that turns the phrase above into concrete actions
        System.out.print("Модалка открылась. Заголовок = ");
        System.out.println($(By.className("modal-header")).getText());
    }

    @Then("there are valid creation dialogs")
    public void there_are_valid_creation_dialogs() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println($(By.className("input-label required ng-star-inserted")).getText());
        throw new cucumber.api.PendingException();
    }

}
