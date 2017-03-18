package com.amit.myapplication.view.activity.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.amit.myapplication.R;
import com.amit.myapplication.modle.properties.products.ProductResult;
import com.amit.myapplication.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeView {


    @BindView(R.id.listViewProducts)
    ListView listViewProducts;

    IHomePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter=new HomePresenter(this,this);

        presenter.requestProducts();





    }

    @Override
    public void showProgress() {
        startProgressDialog(getString(R.string.loadingProducts));
    }

    @Override
    public void hideProgress() {
        stopProgressDialog();
    }

    @Override
    public void loadProducts(ProductResult productResult) {

        HomeProductAdapter adapter=new HomeProductAdapter(this,productResult);
        listViewProducts.setAdapter(adapter);


    }

    @Override
    public void showFeedbackMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void productListEmpty() {

        listViewProducts.setVisibility(View.GONE);

    }
}
