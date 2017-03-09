package com.amit.myapplication.view.activity.profile;

import com.amit.myapplication.modle.properties.login.profile.ProfileResult;

/**
 * Created by Mobile on 3/9/17.
 */

public interface ProfileView {


    void loadProfile(ProfileResult profileResult);
    void updateProfileResult();

    void startProgress();
    void stopProgress();

    void loadCities(String[] cities);
    void loadStates(String[] states);



}
