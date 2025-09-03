package stepDefinitions;

import io.cucumber.java.PendingException;
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

    @When("user enters valid {string} and {string}")
    public void userEntersValidUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
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
