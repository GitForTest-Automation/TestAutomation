package com.amazon.test;

import com.amazon.PageObjectModel.*;
import com.amazon.base.*;
import com.amazon.entity.*;
import com.amazon.enums.*;
import com.amazon.testdata.*;
import com.amazon.utils.*;
import org.testng.*;
import org.testng.annotations.*;

/**
 *  The RemoveProductFromCartTest
 */
@Listeners(testListeners.Listeners.class)
public class RemoveProductFromCartTest extends BaseBrowser {

    /**
     * Remove the product from cart
     * @param searchBox
     */
    @Test(priority = 1,groups = {"Add_To_Cart"},dataProvider = "testCart",dataProviderClass = TestDataProvider.class)
    public void validateRemovingProductFromCartSection(SearchBox searchBox) {

        testCaseId = "8";
        LogUtils.log(Steps.START,"Add product into cart and remove the product from add to cart page");
        TestCaseMethods.refreshPage();
        LogUtils.log(Steps.START,"Search the product");
        TestCaseMethods.searchProductSearchBox(searchBox.getProductNames().get(1));
        ReusableMethods.switchWin();
        LogUtils.log(Steps.START,"Add the product into cart");
        TestCaseMethods.addProductIntoAddToCart();
        LogUtils.log(Steps.START,"Go to Cart section");
        TestCaseMethods.validateAndNavigateToCartPage();
        LogUtils.log(Steps.START,"Remove the added product");
        TestCaseMethods.removeProductInShoppingCart();
        LogUtils.log(Steps.START,"Validate cart page is empty");
        Assert.assertTrue(TestCaseMethods.isCartEmpty(), "Cart page should be empty");
    }
}
