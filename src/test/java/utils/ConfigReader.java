package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/configs/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("❌ Gagal load config.properties: " + e.getMessage());
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public static boolean isIncognito() {
        return Boolean.parseBoolean(properties.getProperty("incognito", "false"));
    }

    public static String getResolution() {
        return properties.getProperty("resolution", "1920x1080");
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl", "https://the-internet.herokuapp.com");
    }
}
