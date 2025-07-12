package testCases;

import io.appium.java_client.AppiumDriver;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchResultPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.NavigationHelper;
import utils.ExtentReportManager;

import java.util.Map;

@Listeners(TestListener.class)
public class IkeaCartFlowTest
{
    int quantity = 2;
    @BeforeMethod
    public void setup() {
        ConfigReader.loadProperties();
        DriverFactory.initializeDriver();
        //ScreenRecorder.startRecording("IKEA_videoNew");
    }
    @Test
    public void  addProductToCartAndVerifyDetails()
    {
        ExtentReportManager.getTest().info("Test started: addProductToCartAndVerifyDetails");
        SoftAssert softAssert = new SoftAssert();
        AppiumDriver driver = DriverFactory.getDriver();
        HomePage home = new HomePage(driver);
        SearchResultPage resultPage = new SearchResultPage(driver);
        ProductDetailsPage productDetail = new ProductDetailsPage(driver);
        NavigationHelper navigationHelper = new NavigationHelper(driver);
        CheckoutPage checkout = new CheckoutPage(driver);
        home.clickLocationBtn();
        home.searchForItem("TABLE");
        home.selectFirstItem();
        Map<String, String> listProductInfo = resultPage.selectFirstProductAndCaptureDetails();
        Map<String, String> selectedProductInfo = productDetail.readProductAndAddToCart(quantity);
        softAssert.assertEquals(listProductInfo.get("name"), selectedProductInfo.get("name"), "Product name mismatch");
        softAssert.assertEquals(listProductInfo.get("price"), selectedProductInfo.get("price"), "Product price mismatch");
        navigationHelper.navigateBackUntilCheckoutVisible();
        home.clickOnCheckoutBtn();
        Assert.assertEquals(checkout.numberOfQty(),Integer.toString(quantity));
        checkout.removeProductFromCart();
        softAssert.assertEquals("",checkout.EmptyCart());
        softAssert.assertAll();
        ExtentReportManager.getTest().info("Test Ended:addProductToCartAndVerifyDetails ");


    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
        //  ScreenRecorder.stopRecording();


    }
}
