package pages;

import base.PageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ExtentReportManager;


import java.util.List;

public class HomePage extends PageBase {
    public HomePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


     By noBtn = By.id("com.ikea.egypt.store:id/popup_other_btn");
     By searchBtn = By.id("com.ikea.egypt.store:id/textView7");
     By suggestionResults = By.xpath("//android.widget.TextView[@resource-id='com.ikea.egypt.store:id/tv_text' and @text='Table']");
     By searchProductBtn = By.id("com.ikea.egypt.store:id/search_products_text_view");
     By checkoutBtn = By.id("com.ikea.egypt.store:id/tab_badge" );


    public void clickLocationBtn()
    {
        ExtentReportManager.getTest().info("Application Lunched");
        click(noBtn);
        ExtentReportManager.getTest().info("Clicked on location popup button");
    }

    public void searchForItem (String text)
    {
        click(searchBtn);
        sendText(searchProductBtn,text);
        ExtentReportManager.getTest().info("Searched for item: " + text);
    }

    public void selectFirstItem() {
        List<WebElement> suggestions = driver.findElements(suggestionResults);

        if (suggestions != null && !suggestions.isEmpty()) {
            WebElement firstItem = suggestions.get(0);
            waitForClickable(firstItem);
            click(firstItem);
            ExtentReportManager.getTest().info("Select First Item Displayed in List");

        } else {
            System.out.println("No suggestion results found to select.");
        }
    }

    public void clickOnCheckoutBtn()
    {

        click(checkoutBtn);
        ExtentReportManager.getTest().info("We click on checkOut Button");

    }


   }



