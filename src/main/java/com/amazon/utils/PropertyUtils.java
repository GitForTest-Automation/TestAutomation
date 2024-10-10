package com.amazon.utils;

import com.amazon.enums.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyUtils {
    private static ResourceBundle file;

    static Properties acctProps = new Properties();

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

        ClassLoader classLoader = PropertyUtils.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream("Locators.properties");
        try {
            acctProps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acctProps;
    }

    public static String getValue(String key) throws IOException {

        String value = "";
        ClassLoader classLoader = PropertyUtils.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream("TestData.properties");
        acctProps.load(in);
        if (acctProps.getProperty(key) != null) {
            value = acctProps.getProperty(key);
        } else {
            LogUtils.log("The key " + key + " is not found in testData.properties", LogLevel.HIGH);
        }
        return value;
    }
}
