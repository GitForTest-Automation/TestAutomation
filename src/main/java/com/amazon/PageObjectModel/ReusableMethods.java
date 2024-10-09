package com.amazon.PageObjectModel;

import com.amazon.base.BaseBrowser;
import com.amazon.utils.PropertyUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class ReusableMethods extends BaseBrowser {

    public static void myWait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clickXp(Properties proper, String locator) {
        driver.findElement(By.xpath(proper.getProperty(locator))).click();
    }

    public static void clickId(Properties proper, String locator) {
        driver.findElement(By.id(proper.getProperty(locator))).click();
    }

    public static void sendXp(Properties proper, String locator, String quan) {
        driver.findElement(By.xpath(proper.getProperty(locator))).sendKeys(quan);
    }

    public static void clearXp(Properties proper, String locator) {
        driver.findElement(By.xpath(proper.getProperty(locator))).clear();
    }

    public static void sendId(Properties proper, String locator, String quan) {
        driver.findElement(By.id(proper.getProperty(locator))).sendKeys(quan);
    }

    public static Boolean isDisplay(Properties proper, String locator) {
        try {
            WebElement element = driver.findElement(By.xpath(proper.getProperty(locator)));
            new WebDriverWait(driver, Duration.ofMinutes(5)).until(ExpectedConditions.visibilityOfAllElements(element));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void selectDropDown(WebElement element, String text) {

        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public static WebElement getElementId(Properties prop, String locators) {

        WebElement expElement = driver.findElement(By.id(prop.getProperty(locators)));
        return expElement;
    }

    public static String getTextXp(Properties proper, String locator) {
        return driver.findElement(By.xpath(proper.getProperty(locator))).getText();
    }

    public static void captureScreen(WebDriver driver, String fileName) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
        String name = dateTime.format(formatter);
        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("./Screenshot/ " + name + "/" + fileName);
            FileUtils.copyFile(source, destFile);
        } catch (IOException e) {

            System.out.println("Failed to capture screenshot: ");
        }
    }

    public static List<WebElement> getElements(Properties proper, String locator) {
        try {
            List<WebElement> elements = driver.findElements(By.xpath(proper.getProperty(locator)));
            new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(PropertyUtils.getWait()))).until(ExpectedConditions.visibilityOf(elements.get(0)));
            elements.get(0).isDisplayed();
            return elements;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static void switchWin() {
        driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public static void clickRandomProduct(Properties proper, String locator) {

        List<WebElement> elements = driver.findElements(By.xpath(proper.getProperty(locator)));
        elements.get(new Random().nextInt(elements.size())).click();
    }

    public static void clickXp(Properties proper, String catQtyBeg, String item, String catQtyEnd) {
        driver.findElement(By.xpath(proper.getProperty(catQtyBeg) + item + proper.getProperty(catQtyEnd))).click();
    }

    public static void scrollDownPixel(Integer pix) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pix + ")", "");
    }

    public static void xpExecutorScrollToView(Properties proper, String subCategory) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", (driver.findElement(By.xpath(proper.getProperty(subCategory)))));
    }

    public static void refresh() {
        driver.navigate().refresh();
    }

    public static boolean isDisplay(Properties proper, String catQtyBeg, String item, String catQtyEnd) {

        try {
            WebElement element = driver.findElement(By.xpath(proper.getProperty(catQtyBeg) + item + proper.getProperty(catQtyEnd)));
            new WebDriverWait(driver, Duration.ofMinutes(Integer.parseInt(PropertyUtils.getWait()))).until(ExpectedConditions.visibilityOfAllElements(element));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
