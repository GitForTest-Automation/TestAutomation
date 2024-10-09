package com.amazon.PageObjectModel;

import com.amazon.base.BaseBrowser;
import com.amazon.utils.PropertyUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import java.util.*;

import static com.amazon.utils.PropertyUtils.getMyWait;

public class TestCaseMethods extends BaseBrowser {


    public void searchProductSearchBox(String product) {

        ReusableMethods.sendId(locatorProp, "amazon.searchBox.id", product);
        ReusableMethods.clickId(locatorProp, "amazon.searchBtn.id");
        ReusableMethods.isDisplay(locatorProp, "amazon.searchResult.xp");
        ReusableMethods.clickXp(locatorProp, "amazon.searchResult.xp");
    }

    public boolean addProductIntoAddToCart() {

        ReusableMethods.clickXp(locatorProp, "amazon.addToCartBtn.xp");
        return ReusableMethods.isDisplay(locatorProp, "amazon.addToCartMessage.xp");
    }

    public String validateAndNavigateToCartPage() {

        ReusableMethods.clickXp(locatorProp, "amazon.addToCart.CartBtn.xp");
        return ReusableMethods.getTextXp(locatorProp, "amazon.addToCartPage.productName.xp");
    }

    public void refreshPage() {

        if (!ReusableMethods.isDisplay(locatorProp, "amazon.title.xp")) {
            String getText = ReusableMethods.getTextXp(locatorProp, "amazon.captchaPage.xp");
            if (getText.equals("Enter the characters you see below")) {
                driver.navigate().refresh();
                refreshPage();
            }
        }
    }

    public void selectDropDownOptions(String option) {

        WebElement element = ReusableMethods.getElementId(locatorProp, "amazon.dropDown.id");
        element.click();
        ReusableMethods.selectDropDown(element, option);
        ReusableMethods.clickId(locatorProp, "amazon.searchBtn.id");
    }

    public void selectSpecificProduct(String productSection) {

        List<WebElement> elements = ReusableMethods.getElements(locatorProp, "amazon.contentHeader.xp");
        for (WebElement element : elements) {
            if (element.getText().equals(productSection)) {
                element.click();
                break;
            }
        }
    }

    public void selectRandomProductMobileSection() {

        ReusableMethods.clickXp(locatorProp, "amazon.mobileSection.xp");
        ReusableMethods.clickRandomProduct(locatorProp, "amazon.mobile.randomProduct.xp");
        ReusableMethods.clickRandomProduct(locatorProp, "amazon.mobileProduct.xp");
    }

    public boolean addProductIntoCart() {

        ReusableMethods.clickXp(locatorProp, "amazon.addToCartBtn.xp");
        if (ReusableMethods.isDisplay(locatorProp, "amazon.addToCartDisplayMessage.xp")) {
            return ReusableMethods.isDisplay(locatorProp, "amazon.addToCartDisplayMessage.xp");
        } else {
            return ReusableMethods.isDisplay(locatorProp, "amazon.addToCartMessage.xp");
        }
    }

    public Integer addProductIntoCartAndGetPrice() {

        String price = ReusableMethods.getTextXp(locatorProp, "amazon.productPrice.xp").replaceAll(",", "");
        ReusableMethods.clickXp(locatorProp, "amazon.addToCartBtn.xp");
        return Integer.parseInt(price);
    }

    public void selectProductInAddToCartPage() {

        ReusableMethods.clickXp(locatorProp, "amazon.addToCartPage.selectProduct.xp");
    }

    public int getPriceFromShoppingCart() {

        String text = ReusableMethods.getTextXp(locatorProp, "amazon.shoppingCartTotal.xp").replaceAll("[,]|\\.00", "").trim();
        return Integer.parseInt(text);
    }

    public void removeProductInShoppingCart() {

        ReusableMethods.clickXp(locatorProp, "amazon.shoppingCart.xp");
    }

    public boolean isCartEmpty() {

        return ReusableMethods.isDisplay(locatorProp, "amazon.cartEmpty.xp");
    }

    public void selectChangeLanguage(String language) {

        ReusableMethods.clickXp(locatorProp, "amazon.languageOption.xp");
        List<WebElement> elements = ReusableMethods.getElements(locatorProp, "amazon.AllLanguageOptions.xp");
        for (WebElement element : elements) {
            if (element.getText().contains(language)) {
                ReusableMethods.clickXp(locatorProp, "amazon.selectLan.beg.xp", language, "amazon.selectLan.end.xp");
                break;
            }
        }
    }

    public boolean isLanguageChangesDisplayed() {

        if (ReusableMethods.isDisplay(locatorProp, "amazon.langChanges.xp")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isBacKToTopButtonDisplayed() {

        ReusableMethods.xpExecutorScrollToView(locatorProp, "amazon.backToTopBtn.xp");
        Boolean displayed = ReusableMethods.isDisplay(locatorProp, "amazon.backToTopBtn.xp");
        return displayed;
    }

    public boolean clickBackToTopButton() {

        ReusableMethods.clickXp(locatorProp, "amazon.backToTopBtn.xp");
        return ReusableMethods.isDisplay(locatorProp, "amazon.title.xp");
    }

    public boolean clickTitleLogoVerifyHomePage() {

        ReusableMethods.clickXp(locatorProp, "amazon.title.xp");
        return ReusableMethods.isDisplay(locatorProp, "amazon.signIn.xp");
    }

    public boolean updateLocation(String pinCode) {

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

    public boolean navigateToSignInPage() {

        ReusableMethods.clickXp(locatorProp, "amazon.signIn.xp");
        return ReusableMethods.isDisplay(locatorProp, "amazon.SignInPage.xp");
    }

    public void enterEmailOrMobile(String username) {

        ReusableMethods.clearXp(locatorProp, "amazon.emailInput.xp");
        ReusableMethods.sendXp(locatorProp, "amazon.emailInput.xp", username);
        ReusableMethods.clickXp(locatorProp, "amazon.continueBtn.xp");
    }

    public String getInvalidErrorMessage() {
        return ReusableMethods.getTextXp(locatorProp, "amazon.errorMsg.xp");
    }
}
