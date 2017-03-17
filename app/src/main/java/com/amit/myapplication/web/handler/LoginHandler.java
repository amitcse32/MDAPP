package com.amit.myapplication.web.handler;

import com.amit.myapplication.modle.properties.login.LoginResultPrp;

/**
 * Created by Mobile on 3/16/17.
 */

public interface LoginHandler {

    void loginSuccess(LoginResultPrp loginResultPrp);
    void loginFail(String message);

}
