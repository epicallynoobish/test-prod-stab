package test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

    @CucumberOptions(
        features = { "./src/features/OpenProd.feature" },
        //glue = {
            //				"info.seleniumcucumber.stepdefinitions", 	// predefined step definitions package
            //				"info.seleniumcucumber.userStepDefintions" 	// user step definitions package
            //		},
        plugin = {
                "pretty",
                //"html:target/cucumberHtmlReport",                       // for html result
                //"pretty:target/cucumber-json-report.json",          // for json result
                //"pretty:junit:target/cucumber.xml"
        }
)

public class TestRunner {
}