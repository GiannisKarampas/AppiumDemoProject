package tests;

import base.BaseTest;
import io.qameta.allure.Description;
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
        expected = ProfileDataGenerator.generateRandomProfile();
        String JSON_PATH = "src/test/resources/generated_profile.json";
        JsonWriter.writeToJson(expected, JSON_PATH);
    }

    @Test
    @Description("Update profile data")
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

        profilePage.assertDataUpdated(updateData);
    }
}
