package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utils.*;

import java.io.IOException;
import java.util.Map;

public class ProfileUpdatesTests extends BaseTest {
    private Map<String, UserCredentials> users;
    private UserCredentials validUser;
    Profile expected;


    @BeforeClass(alwaysRun = true)
    public void loadUserAndLogin() throws IOException {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        loginPage.login(username, password);


        expected = ProfileDataGenerator.generateRandomProfile();
        String JSON_PATH = "src/test/resources/generated_profile.json";
        JsonWriter.writeToJson(expected, JSON_PATH);
    }

    @Test
    public void testUpdateProfileFields() {
        profilePage.navigateToProfilePage();
        profilePage.assertThatAvatarProfileComponentIsDisplayed();
        profilePage.clickOnProfile();

        Profile currentData = profilePage.getProfileData();
        System.out.println("Current data: " + currentData.toString());

        profilePage.updateProfileData(expected);
        profilePage.assertThatAvatarProfileComponentIsDisplayed();
        profilePage.assertProfileHomeNameUpdated(expected);
        profilePage.clickOnProfile();

        Profile updateData = profilePage.getProfileData();
        System.out.println("Updated data: " + updateData.toString());

        profilePage.assertDataUpdated(updateData);
    }
}
