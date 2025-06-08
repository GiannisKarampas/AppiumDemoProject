package org.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pages.base.BasePage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class MyBagPage extends BasePage {

    @AndroidFindBy(id = "dk.TrackMan.Range:id/editBagIcon")
    private WebElement editBagButton;

    @AndroidFindBy(accessibility = "Dr")
    private WebElement driverCard;

    @AndroidFindBy(accessibility = "2w")
    private WebElement twoWoodsCard;

    @AndroidFindBy(accessibility = "ClubSelectionContinueButton")
    private WebElement continueButton;

    @AndroidFindBy(accessibility = "MyBagList")
    private WebElement myBagList;

    private final By myBagItemsXpath = By.xpath("//android.widget.TextView[@resource-id='dk.TrackMan.Range:id/clubName']");
    private final By activeBagItemsXpath = By.xpath(
            "//android.widget.TextView[@resource-id='dk.TrackMan.Range:id/title' and @text='PREVIOUSLY USED']//preceding-sibling::android.view.ViewGroup//android.widget.TextView[@resource-id='dk.TrackMan.Range:id/clubName']");
    private final By previouslyUsedItemsXpath = By.xpath(
            "//android.widget.TextView[@resource-id='dk.TrackMan.Range:id/title' and @text='PREVIOUSLY USED']//following-sibling::android.view.ViewGroup//android.widget.TextView[@resource-id='dk.TrackMan.Range:id/clubName']");

    SoftAssert soft = new SoftAssert();
    public MyBagPage(AppiumDriver driver) {
        super(driver);
    }

    public void editBagItems() {
        safeClick(editBagButton, 5);
        safeClick(driverCard, 5);
        safeClick(twoWoodsCard, 5);
        continueButton.click();
    }

    public void assertItemsAdded() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> elements = driver.findElements(myBagItemsXpath);
        List<String> elementTexts = elements.stream()
                .map(WebElement::getText)
                .toList();

        boolean foundDr = elementTexts.stream().anyMatch(text -> text.equalsIgnoreCase("Dr"));
        boolean found2w = elementTexts.stream().anyMatch(text -> text.equalsIgnoreCase("2W"));

        Assert.assertTrue(foundDr && found2w, "Expected to find 'Dr' or '2w' in the list.");
    }

    public void assertItemsRemovedFromMyBag() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> activeClubTexts = driver.findElements(activeBagItemsXpath)
                .stream()
                .map(WebElement::getText)
                .toList();

        boolean drIsActive = activeClubTexts.stream().anyMatch(text -> text.equalsIgnoreCase("Dr"));
        boolean twoWoodIsActive = activeClubTexts.stream().anyMatch(text -> text.equalsIgnoreCase("2w"));
        soft.assertFalse(drIsActive, "Driver (Dr) should have been removed from the active bag list.");
        soft.assertFalse(twoWoodIsActive, "2 Wood (2w) should have been removed from the active bag list.");


        List<String> previouslyUsedClubTexts = driver.findElements(previouslyUsedItemsXpath)
                .stream()
                .map(WebElement::getText)
                .toList();

        for (String active: previouslyUsedClubTexts) {
            System.out.println("Previous used items: " + active);
        }

        boolean drIsPreviouslyUsed = previouslyUsedClubTexts.stream().anyMatch(text -> text.equalsIgnoreCase("Dr"));
        boolean twoWoodIsPreviouslyUsed = previouslyUsedClubTexts.stream().anyMatch(text -> text.equalsIgnoreCase("2w"));
        soft.assertTrue(drIsPreviouslyUsed, "Driver (Dr) was not found in the 'Previously Used' list.");
        soft.assertTrue(twoWoodIsPreviouslyUsed, "2 Wood (2w) was not found in the 'Previously Used' list.");
    }
}
