package infrastructure;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.pages.HomePage;
import org.pages.LoginPage;
import org.pages.PlannedRoundPage;
import org.pages.ProfilePage;

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
}
