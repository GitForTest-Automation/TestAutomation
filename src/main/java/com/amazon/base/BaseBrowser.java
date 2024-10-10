package com.amazon.base;

import com.amazon.PageObjectModel.*;
import com.amazon.enums.*;
import com.amazon.utils.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;
import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;

import java.util.*;

import static java.util.concurrent.TimeUnit.*;

public class BaseBrowser extends BaseTest {
    protected static WebDriver driver;
    protected static Properties locatorProp;
    protected static int defaultWait;
    protected String testCaseId;
    public static ExtentReports extentReports;
    public static ExtentHtmlReporter htmlReport;
    public ExtentTest testCase;

    @BeforeSuite(alwaysRun = true)
    public void setup(final ITestContext context){

    }

    @BeforeTest
    public void setupSelenium(final ITestContext context){
        driver = getBrowser(PropertyUtils.getBrowserName());
        extentReports = new ExtentReports();
        htmlReport = new ExtentHtmlReporter("Create_Test_Results/Test-Report.html");
        extentReports.attachReporter(htmlReport);
        locatorProp = new PropertyUtils().locatorProperties();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, SECONDS);
        driver.get(PropertyUtils.getUrl());
        defaultWait = Integer.parseInt(PropertyUtils.getWait());
        LogUtils.log("Test data Properties file is loaded ",LogLevel.LOW);
    }

    @BeforeMethod
    public static void launchBrowser() {
        LogUtils.log("Browser launched successfully",LogLevel.LOW);
    }

    @AfterMethod
    public void addResult(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String filename = result.getMethod().getMethodName() + "__" + result.getThrowable().toString().split(":")[0].replaceAll("org.openqa.selenium", "") + ".png";
            ReusableMethods.captureScreen(driver, filename);
        }
        testCase = extentReports.createTest(result.getMethod().getMethodName());
        extentReports.flush();

    }

    @AfterTest(alwaysRun = true)
    public void afterTest(final ITestContext context){

        LogUtils.log("After Test.", LogLevel.MEDIUM);
        driver.quit();

    }

    @AfterSuite(alwaysRun = true)
    public void cease() {

    }
}
