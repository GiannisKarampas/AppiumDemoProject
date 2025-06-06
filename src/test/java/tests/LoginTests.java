package tests;

import base.BaseTest;
import net.bytebuddy.build.Plugin;
import org.pages.LoginPage;
import org.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.utils.ConfigReader;
import org.utils.CredentialsLoader;
import org.utils.UserCredentials;

import java.io.IOException;
import java.util.Map;

public class LoginTests extends BaseTest {
    Map<String, UserCredentials> users = CredentialsLoader.load("src/test/resources/loginUsers.json");

    public LoginTests() throws IOException {
    }

    @Test
    public void successfulLogin() {
        profilePage.navigateToProfilePage();
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        loginPage.login(username, password);
        profilePage.assertThatAvatarProfileComponentIsDisplayed();
    }

    @Test
    public void invalidLogin() {
        UserCredentials invalidUser = users.get("invalidUser");
        loginPage.login(invalidUser.getUsername(), invalidUser.getPassword());
        loginPage.getErrorMessage();
    }
}

