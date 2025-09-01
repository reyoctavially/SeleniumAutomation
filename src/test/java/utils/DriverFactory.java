package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getBrowser();

            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();

                    if (ConfigReader.isIncognito()) {
                        options.addArguments("--incognito");
                    }
                    if (ConfigReader.isHeadless()) {
                        options.addArguments("--headless=new");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");
                    }

                    driver = new ChromeDriver(options);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "safari":
                    // SafariDriver hanya jalan di macOS
                    driver = new SafariDriver();
                    break;

                default:
                    throw new RuntimeException("❌ Browser tidak dikenali: " + browser);
            }

            // Atur resolusi window dari config
            try {
                String[] res = ConfigReader.getResolution().split("x");
                int width = Integer.parseInt(res[0]);
                int height = Integer.parseInt(res[1]);
                driver.manage().window().setSize(new Dimension(width, height));
            } catch (Exception e) {
                System.out.println("⚠️ Resolusi tidak valid di config.properties, pakai default maximize()");
                driver.manage().window().maximize();
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit(); // gunakan quit biar semua session tertutup
            driver = null;
        }
    }
}
