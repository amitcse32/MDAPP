package com.amit.myapplication.web.handler;

import com.amit.myapplication.modle.properties.register.RegisterBody;
import com.amit.myapplication.modle.properties.register.RegisterResponse;

/**
 * Created by Mobile on 3/16/17.
 */

public interface RegistrationHandler {

    void onRegistrationComplete(RegisterResponse registerResponse);
    void onRegistrationFail(String message);
}
