package com.amit.myapplication.view.activity.login;

import com.amit.myapplication.modle.properties.login.LoginResultPrp;

/**
 * Created by Mobile on 3/2/17.
 */

public interface LoginView {

    void onLoginComplete(LoginResultPrp loginResult);

    void startProgress();
    void stopProgress();

    void showFeedbackMessage(String message);

    void onForgetPasswordComplete();
}
