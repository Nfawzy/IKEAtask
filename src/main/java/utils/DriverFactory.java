package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private static AppiumDriver driver;

    public static void initializeDriver() {
        try {
            ConfigReader.loadProperties();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("appium:platformName", ConfigReader.get("platformName"));
            caps.setCapability("appium:deviceName", ConfigReader.get("deviceName"));
            caps.setCapability("appium:platformVersion", ConfigReader.get("platformVersion"));
            caps.setCapability("appium:automationName", ConfigReader.get("automationName"));
            caps.setCapability("appium:appPackage", ConfigReader.get("appPackage"));
            caps.setCapability("appium:appActivity", ConfigReader.get("appActivity"));
            caps.setCapability("appium:noReset", Boolean.parseBoolean(ConfigReader.get("noReset")));
            driver = new AppiumDriver(new URL(ConfigReader.get("appiumServerURL")), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
