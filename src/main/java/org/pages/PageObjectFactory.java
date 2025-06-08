package org.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class PageObjectFactory {

    public static HomePage getHomePage(AppiumDriver driver) {
        HomePage homeScreen = new HomePage(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), homeScreen);
        return homeScreen;
    }

    public static LoginPage getLoginPage(AppiumDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), loginPage);
        return loginPage;
    }

    public static PlannedRoundPage getPlannedRoundPage(AppiumDriver driver) {
        PlannedRoundPage plannedRoundPage = new PlannedRoundPage(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), plannedRoundPage);
        return plannedRoundPage;
    }

    public static ProfilePage getProfilePage(AppiumDriver driver) {
        ProfilePage profilePage = new ProfilePage(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), profilePage);
        return profilePage;
    }

    public static CalendarPage getCalendarPage(AppiumDriver driver) {
        CalendarPage calendarPage = new CalendarPage(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), calendarPage);
        return calendarPage;
    }

    public static MyBagPage getMyBagPage(AppiumDriver driver) {
        MyBagPage myBagPage = new MyBagPage(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), myBagPage);
        return myBagPage;
    }
}
