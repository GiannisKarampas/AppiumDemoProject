package tests;

import base.BaseTest;
import infrastructure.PageObjectFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utils.CredentialsLoader;
import org.utils.UserCredentials;

import java.io.IOException;
import java.util.Map;

public class PlannedRoundsTests extends BaseTest {
    private Map<String, UserCredentials> users;
    private UserCredentials validUser;

    public PlannedRoundsTests() throws IOException {
        users = CredentialsLoader.load("src/test/resources/loginUsers.json");
        validUser = users.get("validUser");
    }

    @BeforeClass(alwaysRun = true)
    public void loginPrecondition() {
        loginPage = PageObjectFactory.getLoginPage(driver);
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
