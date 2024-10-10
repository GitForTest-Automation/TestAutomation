package com.amazon.testdata;

import com.amazon.entity.*;
import com.amazon.utils.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

public class TestDataProvider {

    @DataProvider(name = "testCart")
    public static Object[][] testCart() throws IOException {
        SearchBox searchBox1 = JsonUtil.getObject(PropertyUtils.getValue("Product.Details"), SearchBox.class);
        return new Object[][]{{searchBox1}};
    }

    @DataProvider(name = "userCredential")
    public Object[][] getPropertyFileData() {
        return new Object[][]{{PropertyUtils.get("username"), PropertyUtils.get("phoneNumber")}};
    }
}
