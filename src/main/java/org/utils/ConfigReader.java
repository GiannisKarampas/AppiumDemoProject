package org.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Error: config.properties file not found in classpath.");
            } else {
                properties.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties.");
        }
    }

    public static String getProperty(String key) {
        String prop = System.getProperty(key);
        if (prop != null && !prop.isEmpty()) {
            return prop;
        }
        return properties.getProperty(key);
    }

    public static String getPlatform() {
        String platform = System.getProperty("platform");
        if (platform != null && platform.isEmpty()) {
            return platform;
        }
        return properties.getProperty("platform");
    }
}