package org.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import net.datafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.base.BasePage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.utils.Profile;

import java.time.Duration;

public class ProfilePage extends BasePage {

    @AndroidFindBy(accessibility = "Me")
    private WebElement profilePage;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/profile_avatar_view")
    private WebElement profileHeader;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/playerFullName")
    private WebElement playerFullName;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/playerNickname")
    private WebElement playerNickName;

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='dk.TrackMan.Range:id/textInputLayout'])[1]/android.widget.FrameLayout//android.widget.EditText")
    private WebElement emailAddressField;

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='dk.TrackMan.Range:id/textInputLayout'])[2]/android.widget.FrameLayout//android.widget.EditText")
    private WebElement playerNameField;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/firstNameInputField")
    private WebElement firstNameField;

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='dk.TrackMan.Range:id/textInputLayout'])[3]/android.widget.FrameLayout//android.widget.EditText")
    private WebElement firstNameText;

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='dk.TrackMan.Range:id/textInputLayout'])[4]//android.widget.EditText")
    private WebElement lastNameText;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/birthdayInputField")
    private WebElement birthdayField;

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='dk.TrackMan.Range:id/textInputLayout'])[5]/android.widget.FrameLayout//android.widget.EditText")
    private WebElement birthdayText;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/genderInputField")
    private WebElement genderField;

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='dk.TrackMan.Range:id/textInputLayout'])[6]/android.widget.FrameLayout//android.widget.EditText")
    private WebElement genderText;

    @AndroidFindBy(xpath = "//android.widget.Button")
    private WebElement selectedGender;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/genderOkButton")
    private WebElement genderOkButton;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/nationalityInputField")
    private WebElement nationalityField;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/saveChangesButton")
    private WebElement saveButton;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/loadingImage")
    private WebElement loadImage;

    @AndroidFindBy(id = "dk.TrackMan.Range:id/textViewStatus")
    private WebElement updateDataConfirmText;

    @AndroidFindBy(accessibility = "MyBagButton")
    private WebElement myBagButton;

    private static final Faker faker = new Faker();
    int day = faker.number().numberBetween(1, 28);
    int year = faker.number().numberBetween(1993, 1999);
    CalendarPage calendarPage = PageObjectFactory.getCalendarPage(driver);

    SoftAssert soft = new SoftAssert();

    public ProfilePage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateToProfilePage() {
        safeClick(profilePage, 5);
    }

    public void assertThatAvatarProfileComponentIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(profileHeader));
        Assert.assertTrue(profileHeader.isDisplayed(), "Profile header should be displayed");

    }

    public Profile getProfileData() {
        wait.until(ExpectedConditions.visibilityOf(playerFullName));
        String fullName = playerFullName.getText();
        String email = emailAddressField.getText();
        String playerName = playerNameField.getText();
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String birthday = birthdayText.getText();
        String gender = genderField.getText();

        return new Profile(fullName, email, playerName, firstName, lastName, birthday, gender);
    }

    public void updateProfileData(Profile expected) {
        safeSendKeys(playerNameField, expected.getPlayerName(), 5);
        safeSendKeys(firstNameText, expected.getFirstName(), 5);
        safeSendKeys(lastNameText, expected.getLastName(), 5);
        updateTheBirthDate(day, year);
        safeClick(genderField, 5);
        safeClick(selectedGender, 5);
        genderOkButton.click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"dk.TrackMan.Range:id/saveChangesButton\"))"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        saveButton.click();
    }

    public void assertProfileHomeNameUpdated(Profile updated) {
        soft.assertEquals(playerFullName.getText(),updated.getFullName());
        soft.assertEquals(playerNickName.getText(),updated.getFullName());
    }
    public void assertDataUpdated(Profile updated) {
        wait.until(ExpectedConditions.visibilityOf(loadImage));
        Assert.assertEquals(updateDataConfirmText.getText(), "Profile changes saved successfully.", "Data may not be updated");

        soft.assertEquals(updated.getFullName(),firstNameText.getText() + " " + lastNameText.getText());
        soft.assertEquals(playerFullName.getText(), updated.getPlayerName());
        soft.assertEquals(emailAddressField.getText(), updated.getEmailAddress());
        soft.assertEquals(playerNameField.getText(), updated.getPlayerName());
        soft.assertEquals(firstNameText.getText(), updated.getFirstName());
        soft.assertEquals(lastNameText.getText(), updated.getLastName());
        soft.assertEquals(birthdayText.getText(), updated.getBirthday());
        soft.assertEquals(genderField.getText(), updated.getGender());
    }

    public void clickOnProfile() {
        safeClick(profileHeader, 5);
    }

    public void clickOnMyBag() {
        safeClick(myBagButton, 5);
    }

    private void updateTheBirthDate(int day, int year) {
        birthdayField.click();
        calendarPage.dateSelection(day, year);
    }
}
