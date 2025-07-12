package pages;

import base.PageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utils.ExtentReportManager;

import java.util.HashMap;
import java.util.Map;

public class SearchResultPage extends PageBase {
    public SearchResultPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    By firstProduct = By.xpath("(//androidx.recyclerview.widget.RecyclerView[@resource-id='com.ikea.egypt.store:id/rv_products']//android.view.ViewGroup)[1]");
    By firstProductName = By.xpath("(//android.widget.TextView[@resource-id='com.ikea.egypt.store:id/tv_product_name'])[1]");
    By firstProductPrice = By.xpath("//android.widget.TextView[@resource-id='com.ikea.egypt.store:id/tv_regular_price']");

    public Map<String, String> selectFirstProductAndCaptureDetails() {
        Map<String, String> productDetails = new HashMap<>();

        String name = getLocatorText(firstProductName);
        String price = getLocatorText(firstProductPrice);
        productDetails.put("name", name);
        productDetails.put("price", price);
        ExtentReportManager.getTest().info("Set product Details info from listing search " + productDetails);
        waitForClickable(firstProduct);
        click(firstProduct);
        ExtentReportManager.getTest().info("clicked on First product on list ");
        return productDetails;

    }
}
