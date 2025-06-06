# Appium Automation Skeleton with Dynamic Platform Support

This repository demonstrates how to automate the TrackMan Golf app on **Android** (and is extensible to iOS) using Appium with Java, TestNG, and the Page Object Model. You can dynamically switch between platforms (Android or iOS) without modifying code––just pass a system property or activate a Maven profile.

## Project Structure

```
TrackmanKarampatzakis/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │          ├── config/
│   │          │   ├── ConfigManager.java
│   │          │   └── DriverCapabilities.java
│   │          ├── driver/
│   │          │   ├── DriverManager.java
│   │          │   ├── DriverFactory.java
│   │          │   └── MobileDriverType.java
│   │          ├── pages/
│   │          │   ├── base/
│   │          │   │   ├── BasePage.java
│   │          │   │   └── PageFactory.java
│   │          │   ├── android/
│   │          │   │   ├── LoginPage.java
│   │          │   │   ├── HomePage.java
│   │          │   │   └── ProfilePage.java
│   │          │   │   └── PlannedRoundPage.java
│   │          ├── utils/
│   │          │   ├── ConfigReader.java
│   ├── test/
│   │   ├── java/
│   │   ├── base/
│   │   │   └── BaseTest.java
│   │   ├── infrastructure/
│   │   │   └── PageObjectFactory.java
│   │   ├── tests/
│   │   │    ├── PlannedRounds.java
│   │   │    ├── HomeTests.java
│   │   └── resources/
├── pom.xml
└── README.md
```

## Prerequisites

- **Java 11+** installed
- **Maven** installed and configured
- **Android SDK** installed (for emulators or physical devices)
- **Appium server** installed and running
- **Emulator**: Android (15.0)

## Dynamic Platform Configuration

You can select the platform (**Android** or **iOS**) at runtime using one of these two approaches:

### 1) System Property Override

By default, `config.properties` contains a `platform` entry set to `Android`. To override this at runtime, pass a `-Dplatform` system property when executing Maven or TestNG. For example:

```bash
# Run tests on Android (default, no -D required):
mvn clean test

# Run tests on iOS:
mvn clean test -Dplatform=iOS
```
The code in ConfigReader checks if a system property platform is provided. If so, it uses that value; otherwise, it falls back to config.properties.

### 2) Maven Profiles

Two Maven profiles are defined in pom.xml: android and ios. Each profile injects default properties for that platform.

```bash
# To build/run on Android (default profile):
mvn clean test -Pandroid

# To build/run on iOS:
mvn clean test -Pios
```

## Configuration (config.properties)
```
# Default platform
platform=Android

# Android-specific defaults
android.platformName=Android
android.platformVersion=15.0
android.deviceName=Pixel 9 Pro XL API 35
android.automationName=UiAutomator2
android.appPackage=dk.TrackMan.Range
android.appActivity=crc64268e1ed28150a02c.MainActivity
android.noReset=true
android.fullReset=false

# iOS-specific defaults
ios.platformName=iOS
ios.platformVersion=
ios.deviceName=
ios.automationName=
ios.bundleId=
ios.app=

# Appium server URL
appiumServerURL=http://localhost:4723/wd/hub

# Credentials
username=g.karampas91@gmail.com
password=TestAccount123
playerName=GiannisKaras
```

## Extending the Code for Both Platforms
* DriverFactory checks the resolved platform (Android or iOS) and builds the correct DesiredCapabilities.
* DriverManager calls DriverFactory.createDriver(platform).
* Page Objects remain the same if element locators are identical; otherwise, you can subclass or add conditional logic in the page classes.