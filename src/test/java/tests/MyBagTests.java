package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class MyBagTests extends BaseTest {

    @Test
    @Description("Add items to my bag list")
    public void addItemsInMyBag() {
        profilePage.navigateToProfilePage();
        profilePage.clickOnMyBag();
        myBagPage.editBagItems();
        myBagPage.assertItemsAdded();
    }

    @Test
    @Description("Remove items from my bag list")
    public void removeItemsFromMyBag() {
        profilePage.navigateToProfilePage();
        profilePage.clickOnMyBag();
        myBagPage.editBagItems();
        myBagPage.assertItemsRemovedFromMyBag();
    }
}
