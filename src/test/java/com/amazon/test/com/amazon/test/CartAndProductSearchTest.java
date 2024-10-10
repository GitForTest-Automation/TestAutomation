package com.amazon.test;

import com.amazon.PageObjectModel.ReusableMethods;
import com.amazon.PageObjectModel.TestCaseMethods;
import com.amazon.base.BaseBrowser;
import com.amazon.entity.*;
import com.amazon.enums.*;
import com.amazon.testdata.*;
import com.amazon.utils.LogUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.*;

/**
 * The Cart and Product search test
 */
@Listeners(testListeners.Listeners.class)
public class CartAndProductSearchTest extends BaseBrowser {

    /**
     * Add a product to cart
     * @param searchItem
     */
    @Test(priority = 1, dataProvider = "testCart",dataProviderClass = TestDataProvider.class,groups = {"Add_To_Cart"})
    public void verifyAddProductIntoCartSection(SearchBox searchItem) {

        testCaseId = "1";
        String productName = searchItem.getProductNames().get(0);
        TestCaseMethods.refreshPage();
        LogUtils.log(Steps.START,"Search the Product");
        TestCaseMethods.searchProductSearchBox(productName);
        LogUtils.log(Steps.START,"Click on the searched product");
        ReusableMethods.switchWin();
        LogUtils.log(Steps.START,"Click on Add to Cart");
        boolean isDisplay = TestCaseMethods.addProductIntoAddToCart();
        LogUtils.log(Steps.START,"Verify the Product is in Cart section");
        Assert.assertTrue(isDisplay, "Product should be added into Add to cart.");
        LogUtils.log(Steps.START,"Is the selected product displayed in Cart section : "+isDisplay);
        Assert.assertTrue(TestCaseMethods.validateAndNavigateToCartPage().contains(productName), "Product name should equal to add to cart product name");
    }

    /**
     * Add same product and check quantity
     * @param searchBox
     */
    @Test(priority = 2,dataProvider = "testCart",dataProviderClass = TestDataProvider.class, groups = {"Add_To_Cart"})
    public void verifyQtyIncreasedByAddingSameProductIntoCart(SearchBox searchBox) {

        testCaseId = "2";
        LogUtils.log(Steps.START,"Add same product into cart");
        TestCaseMethods.searchProductSearchBox(searchBox.getProductNames().get(0));
        LogUtils.log(Steps.START,"Click on the searched product");
        ReusableMethods.switchWin();
        LogUtils.log(Steps.START,"Click on Add to Cart");
        TestCaseMethods.addProductIntoAddToCart();
        TestCaseMethods.validateAndNavigateToCartPage();
        LogUtils.log(Steps.START,"Validate product already added to add to cart view is displayed");
        Assert.assertTrue(TestCaseMethods.getProductCountFromCart().equals("2"), "Product not added twice into cart.");
    }

    /**
     * Get the Total amount from the cart
     * @param searchBox
     */
    @Test(priority = 3,dataProvider = "testCart",dataProviderClass = TestDataProvider.class, groups = {"Add_To_Cart"})
    public void validateTotalAmountIsEqualToTheNoOfProducts(SearchBox searchBox) {

        try {
            testCaseId = "3";
            int firstProductPrice, secondProductPrice, total;
            LogUtils.log(Steps.START, "Check the Total amount of Added products");
            TestCaseMethods.refreshPage();
            LogUtils.log(Steps.START, "Add different product into cart");
            TestCaseMethods.searchProductSearchBox(searchBox.getProductNames().get(1));
            LogUtils.log(Steps.START, "Click on the searched product");
            ReusableMethods.switchWin();
            LogUtils.log(Steps.START, "Click on Add to Cart");
            TestCaseMethods.addProductIntoAddToCart();
            LogUtils.log(Steps.START, "Go to Cart page");
            TestCaseMethods.validateAndNavigateToCartPage();
            List<String> prices = TestCaseMethods.getPricesOfTheProductsInCartSection();
            firstProductPrice = Integer.parseInt(prices.get(0).replaceAll("[,]|\\.00", "").trim());
            secondProductPrice = Integer.parseInt(prices.get(1).replaceAll("[,]|\\.00", "").trim());
            total = firstProductPrice + secondProductPrice;
            LogUtils.log(Steps.START, "Validate total amount selected product is accurate cart page");
            Assert.assertNotEquals(TestCaseMethods.getPriceFromShoppingCart(), total, "Product should be added into Add to cart.");
        }finally {
            LogUtils.log(Steps.START,"Remove the added product");
            TestCaseMethods.removeProductInShoppingCart();
        }

    }

    /**
     * Validate The Language change
     */
    @Test(priority = 4)
    public void validateLanguageChangesFunctionality() {

        testCaseId = "4";
        LogUtils.log(Steps.START,"Open the amazon browser, Select language");
        TestCaseMethods.refreshPage();
        LogUtils.log(Steps.START,"Select the language and click save");
        TestCaseMethods.selectChangeLanguage("தமிழ்");
        boolean languageChangesDisplayed = TestCaseMethods.isLanguageChangesDisplayed();
        LogUtils.log(Steps.START,"Validate the language changes display");
        Assert.assertTrue(languageChangesDisplayed, "Language changes should be reflected");
    }

    /**
     * Validate the Amazon logo
     * @param searchBox
     */
    @Test(priority = 5,dataProvider = "testCart",dataProviderClass = TestDataProvider.class)
    public void validateBackToTopAndAmazonLogoFunctionality(SearchBox searchBox) {

        testCaseId = "6";
        LogUtils.log(Steps.START,"Open the amazon browser, Select product and scroll down bottom of the page");
        TestCaseMethods.refreshPage();
        TestCaseMethods.searchProductSearchBox(searchBox.getProductNames().get(2));
        ReusableMethods.switchWin();
        LogUtils.log(Steps.START,"Validate Back to top and amazon logo functionality");
        Assert.assertTrue(TestCaseMethods.isBacKToTopButtonDisplayed(), "Back to top button should be display");
        TestCaseMethods.clickBackToTopButton();
        Assert.assertTrue(TestCaseMethods.clickTitleLogoVerifyHomePage(), "Title logo should be navigate to home page");
    }

    /**
     * Validate update the location field
     */
    @Test(priority = 6)
    public void validateLocationFieldIsUpdated() {

        testCaseId = "7";
        LogUtils.log(Steps.START,"Open the amazon browser, Update pincode");
        String pinCode = "636807";
        TestCaseMethods.refreshPage();
        boolean location = TestCaseMethods.updateLocation(pinCode);
        LogUtils.log(Steps.START,"Validate location field is updated");
        Assert.assertTrue(location, "Mis-Match with actual and expected address");
    }

    /**
     * Validate the Email field
     * @param email
     * @param phone
     */
    @Test(dataProviderClass = TestCaseMethods.class, dataProvider = "userCredential", priority = 7)
    public void validateEmailField(String email, String phone) {

        testCaseId = "5";
        LogUtils.log(Steps.START,"Open the amazon browser, Navigate to signin page and enter invalidate email and phone number");
        TestCaseMethods.refreshPage();
        TestCaseMethods.navigateToSignInPage();
        TestCaseMethods.enterEmailOrMobile(email);
        LogUtils.log(Steps.START,"Validate error message for invalid email or phone");
        Assert.assertTrue(TestCaseMethods.getInvalidErrorMessage().equals("We cannot find an account with that email address"), "Error message should be displayed");
        TestCaseMethods.enterEmailOrMobile(phone);
        Assert.assertTrue(TestCaseMethods.getInvalidErrorMessage().equals("We cannot find an account with that mobile number"), "Error message should be displayed");
    }
}
