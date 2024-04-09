package dev.naamad.listeners;

import dev.naamad.tests.TestSetup;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    private static final Logger LOGGER = LogManager.getLogger(Listeners.class);

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info(String.format("Test %s has failed", result.getName()));
        Object testInstance = result.getInstance();
        if (testInstance instanceof TestSetup testSetup) {
            WebDriver driver = testSetup.getBaseSteps().getDriver();
            if (driver != null) {
                takeScreenshot(driver);
                saveTextLog(String.format("Current URL is: %s", driver.getCurrentUrl()));
            }
        }
    }

    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    private String saveTextLog(String message) {
        return message;
    }

}
