package tests;

import base.BaseTest;
import org.testng.annotations.*;
import org.utils.JsonLoader;
import org.utils.UserCredentials;

import java.io.IOException;
import java.util.Map;

public class PlannedRoundsTests extends BaseTest {
    private Map<String, UserCredentials> users;
    private UserCredentials validUser;

    @BeforeClass(alwaysRun = true)
    public void loadUserAndLogin() throws IOException {
        users = JsonLoader.load("src/test/resources/loginUsers.json",String.class, UserCredentials.class);
        validUser = users.get("validUser");
        loginPage.login(validUser.getUsername(), validUser.getPassword());
    }

    @Test(description = "Add a new planned round and verify it appears", priority = 1)
    public void testAddPlannedRound() {
        homePage.navigateToHomePage();
        homePage.navigateToPlannedRounds();
        plannedRoundPage.createPlannedRound();
        plannedRoundPage.confirmCourseIsCreated();
    }

    @Test(description = "Delete a planned round", priority = 2)
    public void testDeletePlannedRound() {
        homePage.navigateToHomePage();
        homePage.navigateToPlannedRounds();
        plannedRoundPage.deleteRoundCourse();
    }
}
