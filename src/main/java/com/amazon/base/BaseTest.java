package com.amazon.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseTest {

    public WebDriver getBrowser(String browserName) {

        WebDriver driver = null;
        switch (browserName) {
            case "chrome":
                if (driver == null) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--allow-running-insecure-content");
                    options.addArguments("--remote-allow-origins=*");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("Chrome");
                    capabilities.setVersion("ANY");
                    driver = new ChromeDriver(options);
                }
                break;
            case "firefox":
                if (driver == null) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                break;
            case "edge":
                if (driver == null) {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                }
        }

        return driver;
    }
}
