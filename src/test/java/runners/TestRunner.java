package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",   // lokasi feature
        glue = {"stepDefinitions", "hooks"},        // lokasi steps & hooks
        plugin = {
                "pretty",                               // console log lebih jelas
                "html:reports/cucumber-reports.html",    // report HTML
                "json:reports/cucumber.json",            // report JSON
                "junit:reports/cucumber.xml"             // report JUnit XML
        },
        monochrome = true
)

public class TestRunner {
}
