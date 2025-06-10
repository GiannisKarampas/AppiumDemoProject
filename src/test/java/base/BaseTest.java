package base;

import io.appium.java_client.AppiumDriver;

import org.infrastructure.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.pages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.utils.ConfigReader;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected AppiumDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected PlannedRoundPage plannedRoundPage;
    protected ProfilePage profilePage;
    protected CalendarPage calendarPage;
    protected MyBagPage myBagPage;

    String platform = ConfigReader.getPlatform();
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void startAppiumServer() {
        log.info("Start Appium server");
        DriverManager.startAppiumServer();
    }

    @BeforeClass(alwaysRun = true)
    public void initPageObjects(ITestContext context) {
        log.info("Creating Appium driver for platform: {}", platform);
        driver = DriverManager.getDriver(platform);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        context.setAttribute("driver", driver);

        loginPage = PageObjectFactory.getLoginPage(driver);
        homePage = PageObjectFactory.getHomePage(driver);
        plannedRoundPage = PageObjectFactory.getPlannedRoundPage(driver);
        profilePage = PageObjectFactory.getProfilePage(driver);
        calendarPage = PageObjectFactory.getCalendarPage(driver);
        myBagPage = PageObjectFactory.getMyBagPage(driver);

        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        if (shouldLoginBeforeClass())
            loginPage.login(username, password);
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
        log.info("Test Suite Ended");
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        DriverManager.stopAppiumServer();
    }

    protected boolean shouldLoginBeforeClass() { return true; }
}
