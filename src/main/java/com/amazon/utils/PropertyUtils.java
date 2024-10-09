package com.amazon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyUtils {
    private static ResourceBundle file;

    static {
        file = ResourceBundle.getBundle("TestData");
    }

    public static String getBrowserName() {
        return get("browser").toLowerCase();
    }

    public static String getUrl() {
        return get("url").toLowerCase();
    }

    public static String getWait() {
        return get("wait").toLowerCase();
    }

    public static int getMyWait() {
        return Integer.parseInt(get("myWait"));
    }

    public static String getTestRailUserName() {
        return get("testRailUserName").toLowerCase();
    }

    public static String getTestRailPassword() {
        return get("testRailPassword");
    }

    public static String getTestRailUrl() {
        return get("testRailUrl").toLowerCase();
    }

    public static String get(String key) {

        String property = System.getProperty(key);
        return property != null && !property.isEmpty() ? property : file.getString(key);
    }

    public static Properties locatorProperties() {

        Properties acctProps = new Properties();
        ClassLoader classLoader = PropertyUtils.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream("Locators.properties");
        try {
            acctProps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acctProps;
    }
}
