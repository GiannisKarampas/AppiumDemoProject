package org.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pages.base.BasePage;

import java.util.List;
import java.util.Random;

public class CalendarPage extends BasePage {

    @AndroidFindBy(id = "android:id/datePicker")
    private WebElement datePicker;

    @AndroidFindBy(id = "android:id/date_picker_header_date")
    private WebElement dateField;

    @AndroidFindBy(id = "android:id/date_picker_header_year")
    private WebElement yearPicker;

    @AndroidFindBy(id = "android:id/date_picker_year_picker")
    private WebElement yearSelection;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement dateConfirmButton;

    public CalendarPage(AppiumDriver driver) {
        super(driver);
    }

    public void dateSelection(int day, int year) {
        selectYear();
        selectDate(day);
        dateConfirmButton.click();
    }

    private void selectYear() {
        safeClick(yearPicker, 5);
        List<WebElement> visibleYears = driver.findElements(
                        By.xpath("//android.widget.TextView[@resource-id='android:id/text1']")
                ).stream()
                .filter(WebElement::isDisplayed)
                .toList();

        Random random = new Random();
        WebElement yearToSelect = visibleYears.get(random.nextInt(visibleYears.size()));
        yearToSelect.click();
    }

    private void selectDate(int day) {
        String dateXPath = String.format("new UiSelector().text(\"%s\")", day);
        driver.findElement(AppiumBy.androidUIAutomator(dateXPath)).click();
    }
}
