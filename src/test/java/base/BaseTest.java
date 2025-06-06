package base;

import infrastructure.PageObjectFactory;
import io.appium.java_client.AppiumDriver;

import org.infrastructure.DriverManager;
import org.pages.HomePage;
import org.pages.LoginPage;
import org.pages.PlannedRoundPage;
import org.pages.ProfilePage;
import org.testng.annotations.*;
import org.utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest {
    protected AppiumDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected PlannedRoundPage plannedRoundPage;
    protected ProfilePage profilePage;

    String platform = ConfigReader.getPlatform();

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        DriverManager.startAppiumServer();

        driver = DriverManager.getDriver(platform);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod(alwaysRun = true)
    public void initPageObjects() {
        loginPage = PageObjectFactory.getLoginPage(driver);
        homePage = PageObjectFactory.getHomePage(driver);
        plannedRoundPage = PageObjectFactory.getPlannedRoundPage(driver);
        profilePage = PageObjectFactory.getProfilePage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
        DriverManager.stopAppiumServer();
    }

//    @AfterTest(alwaysRun = true)
//    public void quit() {
//        if (DriverManager.getDriver(platform) != null) {
//            DriverManager.quitDriver();
//        }
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void tearDown() {
//        DriverManager.quitDriver();
//    }
//
//    @AfterSuite
//    public void stopAppiumServer() {
//        DriverManager.stopAppiumServer();
//    }
}
