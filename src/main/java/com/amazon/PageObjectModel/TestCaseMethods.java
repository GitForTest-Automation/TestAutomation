package com.amazon.PageObjectModel;

import com.amazon.base.BaseBrowser;
import com.amazon.utils.PropertyUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import java.util.*;

import static com.amazon.utils.PropertyUtils.*;

public class TestCaseMethods extends BaseBrowser {


    public static void searchProductSearchBox(String product) {

        ReusableMethods.sendId(locatorProp, "amazon.searchBox.id", product);
        ReusableMethods.clickId(locatorProp, "amazon.searchBtn.id");
        ReusableMethods.isDisplay(locatorProp, "amazon.searchResult.xp");
        ReusableMethods.clickXp(locatorProp, "amazon.searchResult.xp");
    }

    public static void navigateToCartFromDashboard(){
        ReusableMethods.clickXp(locatorProp, "amazon.Cart");
    }

    public static boolean addProductIntoAddToCart() {

        ReusableMethods.clickXp(locatorProp, "amazon.addToCartBtn.xp");
        return ReusableMethods.isDisplay(locatorProp, "amazon.addToCartMessage.xp");
    }

    public static String validateAndNavigateToCartPage() {

        ReusableMethods.clickXp(locatorProp, "amazon.addToCart.CartBtn.xp");
        return ReusableMethods.getTextXp(locatorProp, "amazon.addToCartPage.productName.xp");
    }

    public static String getProductCountFromCart(){

        return ReusableMethods.getTextXp(locatorProp,"amazon.productQty.xp");
    }

    public static void refreshPage() {

        if (!ReusableMethods.isDisplay(locatorProp, "amazon.title.xp")) {
            String getText = ReusableMethods.getTextXp(locatorProp, "amazon.captchaPage.xp");
            if (getText.equals("Enter the characters you see below")) {
                driver.navigate().refresh();
                refreshPage();
            }
        }
    }

    public static void selectDropDownOptions(String option) {

        WebElement element = ReusableMethods.getElementId(locatorProp, "amazon.dropDown.id");
        element.click();
        ReusableMethods.selectDropDown(element, option);
        ReusableMethods.clickId(locatorProp, "amazon.searchBtn.id");
    }

    public static void selectSpecificProduct(String productSection) {

        List<WebElement> elements = ReusableMethods.getElements(locatorProp, "amazon.contentHeader.xp");
        for (WebElement element : elements) {
            if (element.getText().equals(productSection)) {
                element.click();
                break;
            }
        }
    }

    public static void selectRandomProductMobileSection() {

        ReusableMethods.clickXp(locatorProp, "amazon.mobileSection.xp");
        ReusableMethods.clickRandomProduct(locatorProp, "amazon.mobile.randomProduct.xp");
        ReusableMethods.clickRandomProduct(locatorProp, "amazon.mobileProduct.xp");
    }

    public static boolean addProductIntoCart() {

        ReusableMethods.clickXp(locatorProp, "amazon.addToCartBtn.xp");
        if (ReusableMethods.isDisplay(locatorProp, "amazon.addToCartDisplayMessage.xp")) {
            return ReusableMethods.isDisplay(locatorProp, "amazon.addToCartDisplayMessage.xp");
        } else {
            return ReusableMethods.isDisplay(locatorProp, "amazon.addToCartMessage.xp");
        }
    }

    public static Integer addProductIntoCartAndGetPrice() {

        String price = ReusableMethods.getTextXp(locatorProp, "amazon.productPrice.xp").replaceAll(",", "");
        ReusableMethods.clickXp(locatorProp, "amazon.addToCartBtn.xp");
        return Integer.parseInt(price);
    }

    public static List<String> getPricesOfTheProductsInCartSection(){
        List<String> price1 = new ArrayList<>();
        List<WebElement> prices = ReusableMethods.getElements(locatorProp,"amazon.getProductPrice.cart");
        for (WebElement price : prices) {
            price1.add(price.getText().trim());
        }
        return price1;
    }

    public static void selectProductInAddToCartPage() {

        ReusableMethods.clickXp(locatorProp, "amazon.addToCartPage.selectProduct.xp");
    }

    public static int getPriceFromShoppingCart() {

        String text = ReusableMethods.getTextXp(locatorProp, "amazon.shoppingCartTotal.xp").replaceAll("[,]|\\.00", "").trim();
        return Integer.parseInt(text);
    }

    public static void removeProductInShoppingCart() {

        ReusableMethods.clickXp(locatorProp, "amazon.shoppingCart.xp");
    }

    public static boolean isCartEmpty() {

        return ReusableMethods.isDisplay(locatorProp, "amazon.cartEmpty.xp");
    }

    public static void selectChangeLanguage(String language) {

        ReusableMethods.clickXp(locatorProp, "amazon.languageOption.xp");
        List<WebElement> elements = ReusableMethods.getElements(locatorProp, "amazon.AllLanguageOptions.xp");
        for (WebElement element : elements) {
            if (element.getText().contains(language)) {
                ReusableMethods.clickXp(locatorProp, "amazon.selectLan.beg.xp", language, "amazon.selectLan.end.xp");
                break;
            }
        }
    }

    public static boolean isLanguageChangesDisplayed() {

        if (ReusableMethods.isDisplay(locatorProp, "amazon.langChanges.xp")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isBacKToTopButtonDisplayed() {

        ReusableMethods.xpExecutorScrollToView(locatorProp, "amazon.backToTopBtn.xp");
        Boolean displayed = ReusableMethods.isDisplay(locatorProp, "amazon.backToTopBtn.xp");
        return displayed;
    }

    public static boolean clickBackToTopButton() {

        ReusableMethods.clickXp(locatorProp, "amazon.backToTopBtn.xp");
        return ReusableMethods.isDisplay(locatorProp, "amazon.title.xp");
    }

    public static boolean clickTitleLogoVerifyHomePage() {

        ReusableMethods.clickXp(locatorProp, "amazon.title.xp");
        return ReusableMethods.isDisplay(locatorProp, "amazon.signIn.xp");
    }

    public static boolean updateLocation(String pinCode) {

        ReusableMethods.clickId(locatorProp, "amazon.addressField.id");
        ReusableMethods.isDisplay(locatorProp, "amazon.pinCodeField.xp");
        ReusableMethods.sendXp(locatorProp, "amazon.pinCodeField.xp", pinCode);
        ReusableMethods.clickXp(locatorProp, "amazon.applyBtn.xp");
        ReusableMethods.myWait(getMyWait());
        ReusableMethods.refresh();
        return ReusableMethods.isDisplay(locatorProp, "amazon.address.beg.xp", pinCode, "amazon.address.end.xp");
    }

    @DataProvider(name = "userCredential")
    public Object[][] getPropertyFileData() {

        return new Object[][]{{PropertyUtils.get("username"), PropertyUtils.get("phoneNumber")}};
    }

    public static boolean navigateToSignInPage() {

        ReusableMethods.clickXp(locatorProp, "amazon.signIn.xp");
        return ReusableMethods.isDisplay(locatorProp, "amazon.SignInPage.xp");
    }

    public static void enterEmailOrMobile(String username) {

        ReusableMethods.clearXp(locatorProp, "amazon.emailInput.xp");
        ReusableMethods.sendXp(locatorProp, "amazon.emailInput.xp", username);
        ReusableMethods.clickXp(locatorProp, "amazon.continueBtn.xp");
    }

    public static String getInvalidErrorMessage() {
        return ReusableMethods.getTextXp(locatorProp, "amazon.errorMsg.xp");
    }
}
