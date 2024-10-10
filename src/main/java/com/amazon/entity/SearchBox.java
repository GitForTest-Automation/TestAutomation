package com.amazon.entity;

import java.util.*;

public class SearchBox {

    /**
     * Instantiates a new search box.
     */
    public SearchBox(){
        super();
    }

    /** List of Products */
    private List<String> productNames;

    /**
     * Sets the List of products
     * @param productNames
     */
    public void setProductNames(List<String> productNames) {
        this.productNames = productNames;
    }

    /**
     * Gets the List of products
     * @return
     */
    public List<String> getProductNames() {
        return productNames;
    }
}
