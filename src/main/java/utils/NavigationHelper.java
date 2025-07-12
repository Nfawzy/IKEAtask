package utils;

import base.PageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationHelper extends PageBase {
    public NavigationHelper(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    int numberAttempts = 5 ;
    public void navigateByBackUntilElementVisible(By locator, int maxAttempts) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));

        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                waitForVisibility(locator);
               ExtentReportManager.getTest().info("Target element is visible. " );

                break;
            } catch (Exception e) {
                ExtentReportManager.getTest().info("Target element not found, going back. Attempt" + (attempts + 1 ));
                System.out.println("Target element not found, going back. Attempt " + (attempts + 1));
                driver.navigate().back();
                attempts++;
            }
        }

        if (attempts == maxAttempts) {
            ExtentReportManager.getTest().info("Failed to find target element after  " + maxAttempts + " back navigations."  );
            throw new RuntimeException("Failed to find target element after " + maxAttempts + " back navigations.");

        }
    }

    public void navigateBackUntilCheckoutVisible() {
        By checkoutBtn = By.id("com.ikea.egypt.store:id/tab_badge" );
        navigateByBackUntilElementVisible(checkoutBtn, numberAttempts);


    }

}
