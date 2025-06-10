package org.infrastructure;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.utils.ConfigReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class DriverFactory {
    public static AppiumDriver getDriver(String platform) {
        String appiumUrl = ConfigReader.getProperty("appium.url");

        if (platform.equalsIgnoreCase("iOS")) {
            XCUITestOptions options = new XCUITestOptions()
                    .setPlatformName(ConfigReader.getProperty("ios.platformName"))
                    .setPlatformVersion(ConfigReader.getProperty("ios.platformVersion"))
                    .setDeviceName(ConfigReader.getProperty("ios.deviceName"))
                    .setAutomationName(ConfigReader.getProperty("ios.automationName"))
                    .setBundleId(ConfigReader.getProperty("ios.bundleId"))
                    .setApp(ConfigReader.getProperty("ios.app"))
                    .setWdaLaunchTimeout(Duration.ofSeconds(20));

            try {
                return new IOSDriver(new URI(appiumUrl).toURL(), options);
            } catch (MalformedURLException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else if (platform.equalsIgnoreCase("Android")) {
            File appFile = new File(System.getProperty("user.dir"), ConfigReader.getProperty("android.app"));
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName(ConfigReader.getProperty("android.platformName"))
                    .setPlatformVersion(ConfigReader.getProperty("android.platformVersion"))
                    .setDeviceName(ConfigReader.getProperty("android.deviceName"))
                    .setAutomationName(ConfigReader.getProperty("android.automationName"))
                    .setAppPackage(ConfigReader.getProperty("android.appPackage"))
                    .setAppActivity(ConfigReader.getProperty("android.appActivity"))
                    .setApp(appFile.getPath())
                    .autoGrantPermissions();

            try {
                return new AndroidDriver(new URI(appiumUrl).toURL(), options);
            } catch (MalformedURLException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Invalid platform: " + platform);
        }
    }
}
