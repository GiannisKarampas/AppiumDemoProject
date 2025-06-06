package base;

import com.google.common.collect.ImmutableMap;
import infrastructure.PageObjectFactory;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.infrastructure.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.pages.HomePage;
import org.pages.LoginPage;
import org.pages.PlannedRoundPage;
import org.pages.ProfilePage;
import org.testng.annotations.*;
import org.utils.ConfigReader;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected AppiumDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected PlannedRoundPage plannedRoundPage;
    protected ProfilePage profilePage;

    String platform = ConfigReader.getPlatform();

    @BeforeSuite(alwaysRun = true)
    public void startAppiumServer() {
        DriverManager.startAppiumServer();
    }

    @BeforeClass(alwaysRun = true)
    public void initPageObjects() {
        driver = DriverManager.getDriver(platform);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = PageObjectFactory.getLoginPage(driver);
        homePage = PageObjectFactory.getHomePage(driver);
        plannedRoundPage = PageObjectFactory.getPlannedRoundPage(driver);
        profilePage = PageObjectFactory.getProfilePage(driver);
    }

    @BeforeMethod
    public void preSetUp() {
        Map<String, Object> args = new HashMap<>();
        args.put("intent", "dk.TrackMan.Range/crc64268e1ed28150a02c.MainActivity");
        driver.executeScript("mobile: startActivity", args);
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
        DriverManager.stopAppiumServer();
    }
}
