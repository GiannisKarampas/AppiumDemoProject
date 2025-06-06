package org.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.base.BasePage;

import java.time.Duration;

public class LoginPage extends BasePage {
    @AndroidFindBy(id = "dk.TrackMan.Range:id/signInButton")
    private WebElement firstSignInButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    private WebElement usernameField;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    private WebElement passwordField;

    @AndroidFindBy(xpath = "(//android.widget.Button[@text='SIGN IN'])[1]")
    private WebElement signIn;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id=\"account-signin-form\"]/android.view.View[1]/android.widget.TextView[2]")
    private WebElement errorMessage;

    ProfilePage profilePage = new ProfilePage(driver);


    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        profilePage.navigateToProfilePage();
        safeClick(firstSignInButton, 5);
        safeSendKeys(usernameField, username, 5);
        safeSendKeys(passwordField, password, 5);
        safeClick(signIn, 5);
    }

    public void getErrorMessage() {
        String error = wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
        System.out.println(error);
    }
}
