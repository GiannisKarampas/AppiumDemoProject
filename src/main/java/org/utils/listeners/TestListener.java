package org.utils.listeners;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {
    private static final String LOGGER_SEPARATOR = "====================================================";
    private static final Logger log = LoggerFactory.getLogger(TestListener.class);
    
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error(LOGGER_SEPARATOR);
        log.error("TEST FAILED: {}", result.getName());
        log.error(LOGGER_SEPARATOR);

        try {
            Object drv = result.getTestContext().getAttribute("driver");
            if (drv instanceof AppiumDriver) {
                byte[] png = ((TakesScreenshot) drv).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(png), ".png");

                log.info("Screenshot has been explicitly attached to the Allure report.");
            } else {
                log.warn("No AppiumDriver in context");
            }
        } catch (Exception e) {
            log.error("Could not capture screenshot", e);
        }
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        log.error(LOGGER_SEPARATOR);
        log.error("TEST FAILED: {}", result.getName());
        log.error(LOGGER_SEPARATOR);

        try {
            Object drv = result.getTestContext().getAttribute("driver");
            if (drv instanceof AppiumDriver) {
                byte[] png = ((TakesScreenshot) drv).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(png), ".png");

                log.info("Screenshot has been explicitly attached to the Allure report.");
            } else {
                log.warn("No AppiumDriver in context");
            }
        } catch (Exception e) {
            log.error("Could not capture screenshot", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info("skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("start");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("finish");
    }
}
