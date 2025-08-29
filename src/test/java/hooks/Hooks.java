package hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        System.out.println(">> Starting browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
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

        if (driver != null) {
            driver.quit();
        }
    }
}
