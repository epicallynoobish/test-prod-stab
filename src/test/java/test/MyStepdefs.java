package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Disabled;
import com.codeborne.selenide.impl.WebElementSelector;
import com.codeborne.selenide.impl.WebElementsCollection;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class MyStepdefs {

    @Before
    public void setUp() throws InterruptedException {
    System.out.println("Открытие страницы...");
    open("http://test-web/hse/management/contracts");
    SelenideElement title = $(By.xpath("/html/body/app-root/app-management/div/app-header/div/div[1]/app-header-logo/div/a/div[3]"));
    title.waitUntil(visible, 60000);
    System.out.print("title is ");
    System.out.println(title.getText());
    }

    @Given("open main page")
    public void openMainPage() throws NullPointerException {
        //$("__next").waitUntil(visible, 50);
        assert(executeJavaScript("return document.readyState").equals("complete"));
        System.out.println(url());
        System.out.println(executeJavaScript("return document.readyState").toString());
    }

    @Then("check page title")
    public void checkPageTitle() {
        //SelenideElement title = $(By.xpath("/html/body/app-root/app-management/div/app-header/div/div[1]/app-header-logo/div/a/div[3]"));
        //title.shouldHave(attribute("text", "ГПН"));

        $("title").shouldHave(attribute("text", "ГПН"));
        System.out.print("Заголовок страницы = ");
        //System.out.println(title.getText());
        System.err.println( $("title").getText());
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
        //Condition.and(((url().contains("http://test-web/hse/management/contracts"))));
        System.out.println(url());
    }

    @Given("i have HSE_UID_Customer and HSE_Contract_EOL roles")
    public void i_have_HSE_UID_Customer_and_HSE_Contract_EOL_roles() {
        refresh();
        SelenideElement roles_group_drpdwn = $(By.xpath("//*[@id=\"button\"]/span[1]"));
        SelenideElement roles_name_drpdwn = $(By.xpath("//*[@id=\"button\"]/span[1]"));
        roles_group_drpdwn.waitUntil(visible, 50000);
        assert(roles_group_drpdwn.getText().contentEquals("HSE_UID_Customer"));
        Condition.and (String.valueOf(roles_name_drpdwn.getText().contentEquals("HSE_Contract_EOL")));
        System.out.print("Роли = ");
        System.out.print(roles_group_drpdwn.getText());
        System.out.print("/");
        System.out.println(roles_name_drpdwn.getText());
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
        System.out.print("Модалка открылась. Заголовок = ");
        System.out.println($(By.className("modal-header")).getText());
    }

    @Then("there are valid creation dialogs")
    public void there_are_valid_creation_dialogs() {
        $(By.className("modal-header")).waitUntil(visible, 5000);

        //коллекция элементов в модалке
        ElementsCollection createList = $$(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div"));

        //выводим имеющиеся поля
        System.out.println("----------");
        System.out.println("Есть следующие поля: ");
        for (SelenideElement item : createList) {System.out.println(item.getText());}
        System.out.println("----------");

        //проверяем, что Сохранить нельзя
        assert ($("#tvz2jz").is(Condition.attribute("Disabled")));
        if (!$("#tvz2jz").is(disabled))
            System.out.println("Кнопка 'Сохранить' не активна -- ОК");
        else
            System.out.println("Кнопка 'Сохранить' активна!");
    }

    @Given("creation dialog opened")
    public void creation_dialog_opened() {
        //$("title").waitUntil(visible, 500);
        SelenideElement crtBttn = $(By.xpath("/html/body/app-root/app-management/div/div/app-side-panel/div/div[2]/div/app-management-side-panel/div[3]/div[1]/app-button[1]/div"));
        crtBttn.waitUntil(visible, 500);
        //SelenideElement crtWndw = $(By.xpath("");
        if (!$(By.className("modal-header")).getText().contains("Инициирование договора")) {
            System.err.println("Не открыта модалка!");
            System.out.println("Жмякаем на +Создать...");
            crtBttn.shouldBe(visible);
            crtBttn.click();
            assert ($(By.className("modal-header")).getText().contains("Инициирование договора"));
        }
        System.out.print("Открыта модалка ");
        System.out.println($(By.className("modal-header")).getText());
    }

    @When("i type name {string} and number {string}")
    public void i_type_name_and_number(String string, String string2) {
        SelenideElement fieldName = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[1]/div[1]/app-text-area/div[1]/textarea"));
        SelenideElement fieldNum = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[1]/div[2]/app-input/input"));

        //трогаем поле Имя договора
        fieldName.click();
        fieldName.sendKeys(string);
        System.out.print("Введено имя: ");
        System.out.println(string);

        //трогаем поле Номер
        fieldNum.click();
        fieldNum.sendKeys(string2);
        System.out.print("Введен номер: ");
        System.out.println(string2);

        assert fieldName.has(attribute("ng-valid"));
        assert fieldNum.has(attribute("ng-valid"));
        System.out.println("Имя и номер валидны");
    }

    @When("i set job type {string}")
    public void i_set_job_type(String string) {
        SelenideElement btnDrpDwn_jobtype = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[2]/div[2]/app-dropdown/div[1]/button"));
        btnDrpDwn_jobtype.click();
        $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[2]/div[2]/app-dropdown/div[1]/ul"))
                .shouldBe(visible);
        ElementsCollection jobTypes = $$(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[2]/div[2]/app-dropdown"));
        System.out.println("В комбике 'Вид работ' есть опции:");
        for (SelenideElement item : jobTypes) {
            System.out.println(item.getText());
            /*if (item.getText().equalsIgnoreCase(string) || item.getText().contentEquals(string) || item.getText().equals(string)) {
                item.click();
                System.out.println("ВОТЪ ОНЪ!");
            }*/
        }
        $("#dropdown-menu > p-tree > div > ul > p-treenode:nth-child(2) > li > div > span.ui-treenode-label.ui-corner-all").click();
        SelenideElement jobTypeTextSpan = $("#dropdown-menu > p-tree > div > ul > p-treenode:nth-child(2) > li > div");
        assert jobTypeTextSpan.is(selected);
        System.out.print("Выбрана опция: ");
        System.out.print($(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[2]/div[2]/app-dropdown/div[1]/button/span[1]"))
                .getText());
    }

    @When("i set start date at {string}")
    public void i_set_start_date_at(String string) throws InterruptedException {
        SelenideElement start_pick = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[2]/div[1]/app-datepicker/div/button"));
        /*SelenideElement YearsTbl = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-years-calendar-view > bs-calendar-layout > div.bs-datepicker-body > table");
        ElementsCollection Years = $$((Collection<? extends WebElement>) YearsTbl.closest("tr"));
        for (SelenideElement item : Years) {
            YearsTbl.closest("tr");
        }

        SelenideElement yearPicker = $(By.xpath("/html/body/bs-dropdown-container/div/div/bs-datepicker-inline/bs-datepicker-inline-container/div/div/div/div/bs-days-calendar-view/bs-calendar-layout/div[1]/bs-datepicker-navigation-view/button[3]"));
        startDatePicker.click();
        yearPicker.click();

        //сначала выбираем год
        for (SelenideElement item : Years) {
            System.out.print(item.getText());
            System.out.print(";");
            if (item.getText().equals("2020")) {
                assert item.isDisplayed();
                item.shouldBe(visible);
                item.click();
                System.out.println("Выбран год: ");
                System.out.println(item.getText());
                }
            }

        //потом выбираем месяц
        ElementsCollection Month = $$("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-month-calendar-view > bs-calendar-layout > div.bs-datepicker-body > table");
        for (SelenideElement item : Month) {
            //item.shouldBe(visible);
            assert item.isDisplayed();
            System.out.println(item.getText());
            if (item.getText().equalsIgnoreCase("Январь")) {
                assert item.isDisplayed();
                item.click();
                System.out.println("Выбран месяц: ");
                System.out.println(item.getText());
            }
            //System.err.println("Не вижу месяцов!");
            item.shouldBe(visible);
            System.out.println(item.getText());
        }

        //наконец, выбираем день
        ElementsCollection Days = $$("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-body > table > tbody");
        for (SelenideElement item : Days) {
            //item.shouldBe(visible);
            assert item.isDisplayed();
            System.out.println(item.getText());
            if (item.getText().equalsIgnoreCase("1") && item.has(attribute("role", "gridcell"))) {
                assert item.isDisplayed();
                item.click();
                System.out.println("Выбран день: ");
                System.out.println(item.getText());
            }
            //System.err.println("Не вижу месяцов!");
            item.shouldBe(visible);
            System.out.println(item.getText());
        }
        */
        start_pick.click();
         //SelenideElement CurrMonth =$("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-head > bs-datepicker-navigation-view > button.current.ng-star-inserted > span");
         //CurrMonth.waitUntil(visible, 100);
         //CurrMonth.click();
         //SelenideElement monJan = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-month-calendar-view > bs-calendar-layout > div.bs-datepicker-body > table > tbody > tr:nth-child(1) > td:nth-child(1) > span");
         //monJan.waitUntil(visible, 100);
         //monJan.click();

        //scroll all the way to Jan 20
        SelenideElement monthName = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-head > bs-datepicker-navigation-view > button.current.ng-star-inserted");
        SelenideElement arrLerf = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-head > bs-datepicker-navigation-view > button.previous");
        while (!monthName.getText().equalsIgnoreCase("Январь")) {
            arrLerf.click();
            }

        //pick 01 Jan 2020
        SelenideElement dayOne = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-body > table > tbody > tr:nth-child(1) > td:nth-child(4)");
        dayOne.waitUntil(visible, 100);
        dayOne.click();

        System.out.print("Дата старта: ");
        String startDateText = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[2]/div[1]/app-datepicker/div/button")).getText();
        System.out.println(startDateText);
        assert startDateText.equalsIgnoreCase(string);
    }

    @When("i set jobstart date at {string}")
    public void i_set_jobstart_date_at(String string) throws InterruptedException {
        SelenideElement jobstart_pick = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[3]/div[1]/app-datepicker/div"));
        jobstart_pick.click();

        SelenideElement arrLerf = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-head > bs-datepicker-navigation-view > button.previous");
        SelenideElement monthName = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-head > bs-datepicker-navigation-view > button.current.ng-star-inserted");

        while (!monthName.getText().equalsIgnoreCase("Январь")) {
            arrLerf.click();
        }

        SelenideElement dayTwo = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-body > table > tbody > tr:nth-child(1) > td:nth-child(5)");
        dayTwo.waitUntil(visible, 100);
        dayTwo.click();

        System.out.print("Дата начала работ: ");
        String jobstartDate = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[3]/div[1]/app-datepicker/div/button")).getText();
        assert jobstartDate.equalsIgnoreCase(string);
        System.out.println(jobstartDate);
    }

    @When("i set finish date at {string}")
    public void i_set_finish_date_at(String string) throws InterruptedException {
        SelenideElement finish_pick =$(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[4]/div[1]/app-datepicker/div"));
        finish_pick.click();

        SelenideElement arrLeft = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-head > bs-datepicker-navigation-view > button.previous");
        SelenideElement monthName = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-head > bs-datepicker-navigation-view > button.current.ng-star-inserted");

        while (!monthName.getText().equalsIgnoreCase("Январь")) {
            arrLeft.click();
        }

        SelenideElement dayOne = $("body > bs-dropdown-container > div > div > bs-datepicker-inline > bs-datepicker-inline-container > div > div > div > div > bs-days-calendar-view > bs-calendar-layout > div.bs-datepicker-body > table > tbody > tr:nth-child(1) > td:nth-child(4)");
        dayOne.waitUntil(visible, 100);
        dayOne.click();

        System.out.print("Дата окончания работ: ");
        String finishDate = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[4]/div[1]/app-datepicker/div/button")).getText();
        assert finishDate.equalsIgnoreCase(string);
        System.out.println(finishDate);
    }

    @When("i check if all required fields are filled")
    public void i_check_if_all_required_fields_are_filled() {
        WebElement startDate = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[2]/div[1]/app-datepicker/div/button"));
        WebElement jobstartDate = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[3]/div[1]/app-datepicker/div/button"));
        WebElement finishDate = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[4]/div[1]/app-datepicker/div/button"));
        WebElement conName =$(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[1]/div[1]/app-text-area/div[1]/div"));
        WebElement conNumber = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[1]/div[2]/app-input"));
        WebElement workType = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[2]/div[2]/app-dropdown/div[1]/label"));
        WebElement Cluster = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[3]/div[2]/app-dropdown/div[1]/button"));
        WebElement Project = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[4]/div[2]/app-dropdown/div[1]/button"));
        WebElement LicArea = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[5]/div[2]/app-dropdown/div[1]/button"));
        WebElement Place = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[6]/div[2]/app-dropdown/div[1]/button"));
        WebElement Object = $(By.xpath("/html/body/app-root/app-management/div/div/div/app-contracts/app-modal/app-modal-template/div/div/div/div[2]/app-create-contract/div/div[7]/div[2]/app-dropdown/div[1]/button"));

        
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("Save button is active")
    public void save_button_is_active() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }


}
