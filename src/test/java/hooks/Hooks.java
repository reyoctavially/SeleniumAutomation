package hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        System.out.println(">> Closing browser");

        if (scenario.isFailed()) {
            try {
                final byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                // attach screenshot to cucumber report
                scenario.attach(screenshot, "image/png", "Failed Screenshot");
                System.out.println(">> Screenshot attached to report");
            } catch (Exception e) {
                System.out.println(">> Failed to capture screenshot: " + e.getMessage());
            }
        }
        DriverFactory.quitDriver();
    }
}
