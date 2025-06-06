package org.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.base.BasePage;
import org.testng.Assert;
import org.testng.SkipException;

import java.time.Duration;
import java.util.List;

public class PlannedRoundPage extends BasePage {

    @AndroidFindBy(id = "dk.TrackMan.Range:id/addRoundButton")
    private WebElement newPlannedRoundButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.ViewGroup\").instance(1)")
    private WebElement plannedRoundIntroDrawer;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/actionButton")
    private WebElement planVirtualRoundDrawerButton;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/closeImageView")
    private WebElement closePlanGolfDrawer;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/coursesRecyclerView")
    private WebElement courses;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"dk.TrackMan.Range:id/courseOverviewView\").instance(0)")
    private WebElement firstCourse;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/selectButton")
    private WebElement planGolfRound;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/roundNameEditText")
    private WebElement roundName;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/roundNameTextView")
    private WebElement savedRoundName;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/saveButton")
    private WebElement saveButton;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/courseNameTextView")
    private WebElement courseName;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/closeButtonImageView")
    private WebElement backButton;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/roundsRecyclerView")
    private WebElement createdPlannedRoundCourses;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"dk.TrackMan.Range:id/plannedRoundOverviewView\").instance(0)")
    private WebElement createdPlannedRound;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/moreImageView")
    private WebElement moreButton;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/deleteRoundTextView")
    private WebElement deleteButton;

    // Use a By locator for dynamic finds/counts
    private static final By plannedRounds = By.id("dk.TrackMan.Range:id/plannedRoundOverviewView");


    public PlannedRoundPage(AppiumDriver driver) {
        super(driver);
    }

    public void createPlannedRound() {

        if (plannedRoundIntroDrawer.isDisplayed()) {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(planVirtualRoundDrawerButton))
                    .click();
        } else {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(newPlannedRoundButton))
                    .click();
        }

        Assert.assertTrue(courses.isDisplayed());
        firstCourse.click();
        planGolfRound.click();
        String roundNameText = roundName.getText();
        String courseNameText = courseName.getText();
        saveButton.click();
        String savedCourseNameText = courseName.getText();
        String savedRoundNameText = savedRoundName.getText();

        Assert.assertEquals(roundNameText, savedRoundNameText);
        Assert.assertEquals(courseNameText, savedCourseNameText);
        backButton.click();
    }

    public void confirmCourseIsCreated() {
        Assert.assertTrue(createdPlannedRoundCourses.isDisplayed());
    }

    public void deleteRoundCourse() {
        if (plannedRoundIntroDrawer.isDisplayed()) {
            closePlanGolfDrawer.click();
        }

        int beforeCount = getPlannedRoundCount();
        if (beforeCount == 0)
            throw new SkipException("No planned round items to delete. Skip the test!");

        createdPlannedRound.click();
        moreButton.click();
        deleteButton.click();
        driver.findElement(AppiumBy.id("android:id/button1")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> getPlannedRoundCount() == beforeCount - 1);

        int afterCount = getPlannedRoundCount();
        Assert.assertEquals(afterCount, beforeCount - 1,
                "After deleting one planned round, expected count to decrease by one.");
    }

    private int getPlannedRoundCount() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(plannedRounds));

        List<WebElement> items = driver.findElements(plannedRounds);
        return items.size();
    }
}
