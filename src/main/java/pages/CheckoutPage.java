package pages;

import base.PageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utils.ExtentReportManager;

public class CheckoutPage extends PageBase {
    public CheckoutPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    By productQty = By.id("com.ikea.egypt.store:id/product_qty");
    By removeBtn = By.id("com.ikea.egypt.store:id/iv_remove");
    By removeConfirmationBtn = By.id("com.ikea.egypt.store:id/popup_btn");
    By emptyCheckout = By.xpath("//android.view.ViewGroup[@resource-id=\"com.ikea.egypt.store:id/swiperefresh\"]/android.widget.LinearLayout\n");

    // another solution to back but i refactored it and make genreic method used now
    public void returnToHomeAfterAddingProduct(int Num) {
        pressBack(Num);
    }

    public String numberOfQty() {
        return getLocatorText(productQty);
    }

    public void removeProductFromCart() {
        click(removeBtn);
        ExtentReportManager.getTest().info("Display Screen of confirmation remove " );
        waitForClickable(removeConfirmationBtn);
        click(removeConfirmationBtn);
        ExtentReportManager.getTest().info("click on remove button " );
    }

    public String EmptyCart() {

        String emptyCart = getLocatorText(emptyCheckout);
        ExtentReportManager.getTest().info("check that cart Empty and return Empty string" + emptyCart );
        return emptyCart;

    }


}
