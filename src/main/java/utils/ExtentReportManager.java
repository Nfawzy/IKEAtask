package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

        public static ExtentReports getInstance() {
        if (extent == null) {
            String reportDir = "reports";
            File directory = new File(reportDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = reportDir + "/ExtentReport_" + System.currentTimeMillis() + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(filePath);
            spark.config().setDocumentTitle("IKEA Automation Report");
            spark.config().setReportName("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Naglaa Fawzy");
        }
        return extent;
    }


    public static void createTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        testThread.set(test);
    }


    public static ExtentTest getTest() {
        return testThread.get();
    }


    public static void removeTest() {
        testThread.remove();
    }
}
