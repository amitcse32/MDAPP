package com.amit.myapplication.view.activity.register;

import com.amit.myapplication.modle.properties.login.LoginResultPrp;
import com.amit.myapplication.modle.properties.login.register.RegisterResponse;

/**
 * Created by Mobile on 3/6/17.
 */

public interface RegisterView {

    void onRegistrationComplete(RegisterResponse response);

    void startProgress();
    void stopProgress();

    void showFeedbackMessage(String message);


}
