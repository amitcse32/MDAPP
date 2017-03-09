package com.amit.myapplication.view.activity.profile;

import android.app.Activity;

import com.amit.myapplication.R;

/**
 * Created by Mobile on 3/9/17.
 */

public class ProfilePresenter implements IProfilePresenter {

    Activity activity;
    ProfileView view;

    ProfilePresenter(Activity activity,ProfileView profileView)
    {
        this.activity=activity;
        this.view=profileView;
    }


    @Override
    public void loadCities() {

        String[] cities=activity.getResources().getStringArray(R.array.cities);
        view.loadCities(cities);


    }

    @Override
    public void loadStates() {
        String[] states=activity.getResources().getStringArray(R.array.states);
        view.loadStates(states);
    }
}
