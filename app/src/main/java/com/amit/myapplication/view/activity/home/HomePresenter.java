package com.amit.myapplication.view.activity.home;

import android.app.Activity;

import com.amit.myapplication.R;
import com.amit.myapplication.modle.properties.products.ProductResult;
import com.amit.myapplication.web.connection.WebRequestHandler;
import com.amit.myapplication.web.handler.HomeHandler;

/**
 * Created by Mobile on 3/18/17.
 */

public class HomePresenter implements IHomePresenter,HomeHandler {


    Activity activity;
    HomeView homeView;

    HomePresenter(Activity activity,HomeView view)
    {
        this.homeView=view;
        this.activity=activity;
     }

    @Override
    public void requestProducts() {

        homeView.showProgress();
        WebRequestHandler handler=new WebRequestHandler();
        handler.requestProducts(this);


    }

    @Override
    public void onProductLoadComplete(ProductResult productResult) {
        homeView.hideProgress();
        if(productResult!=null)
        {
            if(productResult.getResult().size()>0) {
                homeView.loadProducts(productResult);
            }
            else
            {
                homeView.productListEmpty();
            }

        }
        else
        {
            homeView.showFeedbackMessage(activity.getString(R.string.somethingwentwrongTryAgain));

        }
    }

    @Override
    public void onProductLoadFail(String message) {
        homeView.hideProgress();

        if(message!=null)
        {
            homeView.showFeedbackMessage(message);
        }
        else
        {
            homeView.showFeedbackMessage(activity.getString(R.string.somethingwentwrongTryAgain));

        }
    }
}
