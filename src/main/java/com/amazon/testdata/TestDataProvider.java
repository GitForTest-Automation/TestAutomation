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
}
