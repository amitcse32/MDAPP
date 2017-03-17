package com.amit.myapplication.view.activity.profile;


import com.amit.myapplication.modle.properties.profile.ProfileResult;
import com.amit.myapplication.modle.properties.profileupdate.ProfileUpdateResult;

/**
 * Created by Mobile on 3/9/17.
 */

public interface ProfileView {


    void loadProfile(ProfileResult profileResult);
    void onProfileUpdateComplete();

    void startProgress();
    void stopProgress();

    void showFeedbackMessage(String message);




}
