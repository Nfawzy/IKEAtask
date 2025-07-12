package listeners;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed");
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.getInstance().flush();
        ExtentReportManager.removeTest();
    }
    @Override
    public void onTestFailure(ITestResult result) {
        AppiumDriver driver = DriverFactory.getDriver(); // get current driver
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());

        ExtentReportManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
        ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
    }
}
