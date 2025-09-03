package locators;
import org.openqa.selenium.By;

public interface LoginPageLocators {
    By USERNAME_FIELD = By.id("username");
    By PASSWORD_FIELD = By.id("password");
    By LOGIN_BUTTON   = By.cssSelector("button[type='submit']");
    By FLASH_MESSAGE  = By.id("flash");
}
