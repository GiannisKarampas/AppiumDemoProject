package org.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pages.base.BasePage;
import org.testng.Assert;
import org.testng.SkipException;

import java.util.List;
import java.util.UUID;

public class PlannedRoundPage extends BasePage {

    @AndroidFindBy(id = "dk.TrackMan.Range:id/addRoundButton")
    private WebElement newPlannedRoundButton;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/actionButton")
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

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"dk.TrackMan.Range:id/plannedRoundOverviewView\"])[1]//android.widget.TextView[@resource-id=\"dk.TrackMan.Range:id/roundNameTextView\"]")
    private WebElement firstCreateRoundName;

    // Use a By locator for dynamic finds/counts
    private static final By plannedRounds = By.id("dk.TrackMan.Range:id/plannedRoundOverviewView");

    private String generatedRoundName = generateRandomRoundName();

    public PlannedRoundPage(AppiumDriver driver) {
        super(driver);
    }

    public void createPlannedRound() {

        if (isElementDisplayed(plannedRoundIntroDrawer, 5)) {
            safeClick(planVirtualRoundDrawerButton, 5);
        } else {
            safeClick(newPlannedRoundButton, 5);
        }

        Assert.assertTrue(courses.isDisplayed());
        safeClick(firstCourse, 3);
        safeClick(planGolfRound, 5);
        safeSendKeys(roundName, generatedRoundName, 3);

        String roundNameText = roundName.getText();
        System.out.println(roundNameText);
        String courseNameText = courseName.getText();

        safeClick(saveButton, 2);

        String savedCourseNameText = courseName.getText();
        String savedRoundNameText = savedRoundName.getText();

        Assert.assertEquals(roundNameText, savedRoundNameText, String.format("Expected saved round name '%s', but found '%s'.", roundNameText, savedRoundNameText));
        Assert.assertEquals(courseNameText, savedCourseNameText, String.format("Expected saved course name '%s', but found '%s'.", courseNameText, savedCourseNameText));

        backButton.click();
    }

    public void confirmCourseIsCreated() {
        WebElement createdRounds = waitForElement(createdPlannedRoundCourses, 10);
        System.out.println(firstCreateRoundName.getText());
        Assert.assertEquals(firstCreateRoundName.getText(), generatedRoundName);
        Assert.assertTrue(createdRounds.isDisplayed(), "Created planned round courses are not displayed.");
    }

    public void deleteRoundCourse() {
        if (isElementDisplayed(planVirtualRoundDrawerButton, 5)) {
            safeClick(closePlanGolfDrawer, 5);
        }

        int beforeCount = getPlannedRoundCount();

        if (beforeCount == 0)
            throw new SkipException("No planned round items to delete. Skip the test!");

        safeClick(createdPlannedRound, 5);
        safeClick(moreButton, 5);
        safeClick(deleteButton, 5);
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        Assert.assertNotEquals(firstCreateRoundName.getText(), generatedRoundName);
    }

    private int getPlannedRoundCount() {
        try {
            WebElement plannedRoundsContainer = waitForElement(plannedRounds, 5);
            if (plannedRoundsContainer == null) {
                throw new RuntimeException("Planned rounds container not found.");
            }

            List<WebElement> items = driver.findElements(plannedRounds);
            return items.size();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to get planned round count: " + e.getMessage(), e);
        }
    }

    private String generateRandomRoundName() {
        return "Test round " + UUID.randomUUID()
                .toString()
                .substring(0,8);
    }
}
