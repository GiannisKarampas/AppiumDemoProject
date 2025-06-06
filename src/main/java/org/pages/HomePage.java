package org.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.pages.base.BasePage;

public class HomePage extends BasePage {

    @AndroidFindBy(id = "dk.TrackMan.Range:id/playView")
    private WebElement playerView;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/myPlannedRoundsView")
    private WebElement myPlannedRounds;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        playerView.click();
    }

    public void navigateToPlannedRounds() {
        myPlannedRounds.click();
    }
}
