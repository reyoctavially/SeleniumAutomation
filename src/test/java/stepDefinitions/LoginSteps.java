package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import utils.DriverFactory;
import utils.ConfigReader;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        driver = DriverFactory.getDriver(); // ✅ ambil dari DriverFactory
        driver.get(ConfigReader.getBaseUrl() + "/login");
        loginPage = new LoginPage(driver);
    }

    @When("user enters valid username and password")
    public void userEntersValidUsernameAndPassword() {
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();
    }

    @Then("user should be redirected to homepage")
    public void userShouldBeRedirectedToHomepage() {
        String message = loginPage.getFlashMessage();
        if (message.contains("You logged into a secure area!")) {
            System.out.println("✅ Login sukses!");
        } else {
            System.out.println("❌ Login gagal!");
        }
    }
}
