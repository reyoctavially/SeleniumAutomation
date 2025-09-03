package pageObjects;
import locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;

public class LoginPage implements LoginPageLocators {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(USERNAME_FIELD).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getFlashMessage() {
        return driver.findElement(FLASH_MESSAGE).getText();
    }
}
