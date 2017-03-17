package com.amit.myapplication.view.activity.register;


import com.amit.myapplication.modle.properties.register.RegisterResponse;

/**
 * Created by Mobile on 3/6/17.
 */

public interface RegisterView {

    void onRegistrationComplete();
    void startProgress();
    void stopProgress();
    void showFeedbackMessage(String message);


}
