package com.amazon.test;

import com.amazon.PageObjectModel.*;
import com.amazon.base.*;
import com.amazon.enums.*;
import com.amazon.utils.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.net.*;
import java.util.*;

public class BrokenLinkTest extends BaseBrowser {

    @Test(priority = 1)
    public void findBrokenLink(){
        LogUtils.log(Steps.START,"Get the links");
        TestCaseMethods.refreshPage();
        LogUtils.log(Steps.START,"Get the href link by anchor tag");
        List<WebElement> links = TestCaseMethods.getLinksFromHomePage();
        Set<String> brokenLinks = checkBrokenLinks(links);
        for (String link:brokenLinks) {
            LogUtils.log(Steps.START,"The links are : "+link);
        }
    }

    public static Set<String> checkBrokenLinks(List<WebElement> links){
        Set<String> brokenLinks = new HashSet<>();

        for (WebElement link : links) {
            String url = link.getAttribute("href");

            if (url != null && !url.isEmpty()) {
                if (isLinkBroken(url)) {
                    brokenLinks.add(url);
                }
            } else {
                LogUtils.log("URL is either not configured for anchor tag or it is empty",LogLevel.LOW);
            }
        }

        return brokenLinks;
    }

    public static boolean isLinkBroken(String urlLink){
        try {
            URL url = new URL(urlLink);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            return httpURLConnection.getResponseCode() >= 400;

        } catch (Exception e) {
            return true;
        }
    }



}
