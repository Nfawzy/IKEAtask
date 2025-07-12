package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    protected AppiumDriver driver;
    public static final long waitTime = Long.parseLong(System.getProperty("waitTime", "10"));

    public PageBase(AppiumDriver appiumDriver) {
        driver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(waitTime)), this);
    }

     // wait method for visibility by locator & element
    public WebElement waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    // wait method for clickable by locator & element
    public WebElement waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public WebElement waitForClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

     // clear method  By locator & Element
    public void clearText(By locator) {
        WebElement element = waitForVisibility(locator);
        element.clear();
    }
    public void clearText(WebElement element) {
         waitForVisibility(element);
          element.clear();
    }

    // Click method using locator & eleemnt
    public void click(By locator) {
        WebElement element = waitForVisibility(locator);
        element.click();
    }

        public void click(WebElement element) {
        waitForVisibility(element).click();
    }

    // Send text using By & elemnt
    public void sendText(By locator, String text) {
         WebElement element = waitForClickable(locator);
          element.clear();
        element.sendKeys(text);
    }

       public void sendText(WebElement element, String text) {
        WebElement visibleElement = waitForVisibility(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    // Get attribute from WebElement or locator
    public String getAttribute(WebElement element, String attribute) {
        return waitForVisibility(element).getAttribute(attribute);
    }
    public String getAttribute(By locator, String attribute) {
        return waitForVisibility(locator).getAttribute(attribute);
    }
     // get text from locator or Element
    public String getLocatorText(By locator) {

        return  waitForVisibility(locator).getText();
    }
    public String getLocatorText(WebElement element) {

        return  waitForVisibility(element).getText();
    }

    public void pressBack(int times) {
        for (int i = 0; i < times; i++) {
            driver.navigate().back();
        }
    }


}
