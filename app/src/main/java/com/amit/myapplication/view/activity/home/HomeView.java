package com.amit.myapplication.view.activity.home;

import com.amit.myapplication.modle.properties.products.ProductResult;

/**
 * Created by Mobile on 3/18/17.
 */

public interface HomeView {


    void showProgress();
    void hideProgress();
    void loadProducts(ProductResult productResult);
    void showFeedbackMessage(String message);
    void productListEmpty();

}
