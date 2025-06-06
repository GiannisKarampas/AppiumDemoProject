package org.pages.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected AppiumDriver driver;
    public WebDriverWait wait;


    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isElementDisplayed(WebElement element, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOf(element));
            boolean isDisplayed = element.isDisplayed();
            System.out.println("Element " + element + " isDisplayed: " + isDisplayed);
            return isDisplayed;
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Element " + element + " not found or not visible: " + e.getMessage());
            return false;
        }
    }

    public void safeClick(WebElement element, int timeoutInSeconds) {
        WebElement clickableElement = waitForElementToBeClickable(element, timeoutInSeconds);
        if (clickableElement != null) {
            clickableElement.click();
        } else {
            throw new RuntimeException(element + " is not clickable");
        }
    }

    public void safeSendKeys(WebElement element, String text, int timeoutInSeconds) {
        WebElement webElement = waitForElement(element, timeoutInSeconds);
        if (webElement != null) {
            webElement.sendKeys(text);
        } else {
            throw new RuntimeException(element + " is not clickable");
        }
    }

    public WebElement waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (RuntimeException e) {
            System.out.println("Element not clickable: " + element.toString());
            return null;
        }
    }

    public WebElement waitForElement(By locator, int timeoutInSeconds) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (RuntimeException e) {
            System.out.println("Element not found: " + locator.toString());
            return null;
        }
    }

    public WebElement waitForElement(WebElement locator, int timeoutInSeconds) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOf(locator));
        } catch (RuntimeException e) {
            System.out.println("Element not found: " + locator.toString());
            return null;
        }
    }

}
