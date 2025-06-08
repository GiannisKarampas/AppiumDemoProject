package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import org.utils.JsonLoader;
import org.utils.UserCredentials;

import java.io.IOException;
import java.util.Map;

public class LoginTests extends BaseTest {
    Map<String, UserCredentials> users = JsonLoader.load("src/test/resources/loginUsers.json", String.class, UserCredentials.class);

    public LoginTests() throws IOException {
    }

    @Test
    public void invalidLogin() {
        UserCredentials invalidUser = users.get("invalidUser");
        loginPage.login(invalidUser.getUsername(), invalidUser.getPassword());
        loginPage.getErrorMessage();
    }
}

