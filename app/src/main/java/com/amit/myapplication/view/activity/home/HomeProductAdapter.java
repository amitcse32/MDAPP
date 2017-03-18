package com.amit.myapplication.view.activity.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.amit.myapplication.R;
import com.amit.myapplication.modle.properties.products.ProductResult;

/**
 * Created by Mobile on 3/18/17.
 */

public class HomeProductAdapter extends BaseAdapter {

    Activity activity;
    ProductResult productResult;

    HomeProductAdapter(Activity activity, ProductResult productResult) {
        this.activity = activity;
        this.productResult = productResult;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_product, null);
        return view;


    }
}
