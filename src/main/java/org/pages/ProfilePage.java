package org.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.base.BasePage;
import org.testng.Assert;
import org.utils.ConfigReader;

import java.time.Duration;

public class ProfilePage extends BasePage {

    @AndroidFindBy(accessibility = "Me")
    private WebElement profilePage;
    @AndroidFindBy(id = "dk.TrackMan.Range:id/profile_header_background_top")
    private WebElement profileHeader;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").instance(6)")
    private WebElement emailAddressField;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").instance(9)")
    private WebElement playerNameField;

    public ProfilePage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateToProfilePage() {
        profilePage.click();
    }

    public void assertThatAvatarProfileComponentIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(profileHeader));
        Assert.assertTrue(profileHeader.isDisplayed(), "Profile header should be displayed");

    }

    public void navigateToProfileAndAssertValues() {
        profileHeader.click();
        String email = emailAddressField.getText();
        String playerName = playerNameField.getText();
        Assert.assertEquals(email, ConfigReader.getProperty("username"));
        Assert.assertEquals(playerName, ConfigReader.getProperty("playerName"));
    }
}
