package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static String takeScreenshot(AppiumDriver driver, String testName) {
        String folderPath = "screenshots";
        File screenshotDir = new File(folderPath);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        String screenshotPath = folderPath + "/" + testName + "_" + System.currentTimeMillis() + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);

        try {
            Files.copy(srcFile.toPath(), destFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destFile.getAbsolutePath();
    }
}
