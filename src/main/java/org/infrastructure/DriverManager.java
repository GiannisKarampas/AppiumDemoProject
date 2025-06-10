package org.infrastructure;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class DriverManager {
    private static AppiumDriver driver;
    private static AppiumDriverLocalService service;

    public static void startAppiumServer() {
        if (service == null || !service.isRunning()) {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withIPAddress("127.0.0.1");
            builder.usingPort(4723);
            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info");

            service = builder.build();
            service.start();
        }
    }

    public static void stopAppiumServer() {
        if (service.isRunning() && service != null) {
            service.stop();
        }
    }

    public static AppiumDriver getDriver(String platform){
        if (driver == null) {
            driver = DriverFactory.getDriver(platform);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
