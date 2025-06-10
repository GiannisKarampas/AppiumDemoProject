package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.*;

public class PlannedRoundsTests extends BaseTest {
    @Test(priority = 1)
    @Description("Add a new planned round and verify it appears")
    public void testAddPlannedRound() {
        homePage.navigateToHomePage();
        homePage.navigateToPlannedRounds();
        plannedRoundPage.createPlannedRound();
        plannedRoundPage.confirmCourseIsCreated();
    }

    @Test(priority = 2)
    @Description("Delete a planned round")
    public void testDeletePlannedRound() {
        homePage.navigateToHomePage();
        homePage.navigateToPlannedRounds();
        plannedRoundPage.deleteRoundCourse();
    }
}
