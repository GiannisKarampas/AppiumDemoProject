package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utils.ConfigReader;

import java.io.IOException;

public class MyBagTests extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void loadUserAndLogin() throws IOException {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        loginPage.login(username, password);
    }

    @Test
    public void addItemsInMyBag() {
        profilePage.navigateToProfilePage();
        profilePage.clickOnMyBag();
        myBagPage.editBagItems();
        myBagPage.assertItemsAdded();
    }

    @Test
    public void removeItemsFromMyBag() {
        profilePage.navigateToProfilePage();
        profilePage.clickOnMyBag();
        myBagPage.editBagItems();
        myBagPage.assertItemsRemovedFromMyBag();
    }
}
