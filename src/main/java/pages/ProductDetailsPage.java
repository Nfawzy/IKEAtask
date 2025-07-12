package pages;

import base.PageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utils.ExtentReportManager;

import java.util.HashMap;
import java.util.Map;


public class ProductDetailsPage extends PageBase {
    public ProductDetailsPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


    By productName = By.id("com.ikea.egypt.store:id/tv_product_name");
    By productPrice = By.id("com.ikea.egypt.store:id/tv_regular_price");
    By addToCartBtn = By.id("com.ikea.egypt.store:id/bt_add_to_cart");

    public Map<String, String> readProductAndAddToCart(int quantity) {
        Map<String, String> productDetails = new HashMap<>();

        String name = getLocatorText(productName);
        String price = getLocatorText(productPrice);
        productDetails.put("name", name);
        productDetails.put("price", price);
        ExtentReportManager.getTest().info("Set product Details info from product Detail page " + productDetails);
        for (int i = 0; i < quantity; i++) {
            click(addToCartBtn);
            ExtentReportManager.getTest().info("Add product to Cart time " + i);
        }
        return productDetails;
    }

}
