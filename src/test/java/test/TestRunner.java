package test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "./src/features/OpenProd.feature" },
        glue = {"./src/test/java/test/MyStepdefs"}
)

public class TestRunner {
}