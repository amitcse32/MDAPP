package com.amit.myapplication.web.handler;

import com.amit.myapplication.modle.properties.products.ProductResult;

/**
 * Created by Mobile on 3/18/17.
 */

public interface HomeHandler  {

    void onProductLoadComplete(ProductResult productResult);
    void onProductLoadFail(String message);
}
