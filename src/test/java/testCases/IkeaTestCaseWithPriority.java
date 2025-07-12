package testCases;

import io.appium.java_client.AppiumDriver;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.*;

import java.util.Map;

@Listeners(TestListener.class)
public class IkeaTestCaseWithPriority {

    int quantity = 2;
    AppiumDriver driver;
    HomePage home;
    SearchResultPage resultPage;
    ProductDetailsPage productDetail;
    NavigationHelper navigationHelper;
    CheckoutPage checkout;
    Map<String, String> listProductInfo;
    Map<String, String> selectedProductInfo;

    @BeforeClass
    public void setup() {
        ConfigReader.loadProperties();
        DriverFactory.initializeDriver();
        driver = DriverFactory.getDriver();
        home = new HomePage(driver);
        resultPage = new SearchResultPage(driver);
        productDetail = new ProductDetailsPage(driver);
        navigationHelper = new NavigationHelper(driver);
        checkout = new CheckoutPage(driver);
    }

    @Test(priority = 1)
    public void searchAndSelectProduct() {
        ExtentReportManager.getTest().info("Step 1: Search and select product");
        home.clickLocationBtn();
        home.searchForItem("TABLE");
        home.selectFirstItem();
        listProductInfo = resultPage.selectFirstProductAndCaptureDetails();
            }

    @Test(priority = 2, dependsOnMethods = "searchAndSelectProduct")
    public void addToCartAndVerifyDetails() {
        ExtentReportManager.getTest().info("Step 2: Add product to cart and verify details");
        selectedProductInfo = productDetail.readProductAndAddToCart(quantity);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(selectedProductInfo.get("name"), listProductInfo.get("name"), "Product name mismatch");
        softAssert.assertEquals(selectedProductInfo.get("price"), listProductInfo.get("price"), "Product price mismatch");
        softAssert.assertAll();
    }

    @Test(priority = 3, dependsOnMethods = "addToCartAndVerifyDetails")
    public void checkoutAndVerifyQuantity() {
        ExtentReportManager.getTest().info("Step 3: Go to checkout and verify quantity");
        navigationHelper.navigateBackUntilCheckoutVisible();
        home.clickOnCheckoutBtn();
        Assert.assertEquals(checkout.numberOfQty(), String.valueOf(quantity), "Incorrect quantity in checkout");
    }

    @Test(priority = 4, dependsOnMethods = "checkoutAndVerifyQuantity")
    public void removeAndVerifyCartIsEmpty() {
        ExtentReportManager.getTest().info("Step 4: Remove product and verify cart is empty");
        checkout.removeProductFromCart();
        Assert.assertEquals(checkout.EmptyCart(), "", "Cart is not empty after removal");
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
