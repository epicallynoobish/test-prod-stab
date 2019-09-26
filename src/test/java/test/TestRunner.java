package test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "./src/features/OpenProd.feature" },
        glue = {"MyStepdefs"}
)

public class TestRunner {
}