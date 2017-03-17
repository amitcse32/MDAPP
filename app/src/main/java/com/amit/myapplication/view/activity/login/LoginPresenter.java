package com.amit.myapplication.view.activity.login;

import android.app.Activity;

import com.amit.myapplication.R;
import com.amit.myapplication.utils.customcontrols.dialogs.sharedpref.MW_SharedPref;
import com.amit.myapplication.web.connection.IBaseUrl;
import com.amit.myapplication.web.connection.WebInterface;
import com.amit.myapplication.modle.properties.login.LoginResultPrp;
import com.amit.myapplication.web.connection.WebRequestHandler;
import com.amit.myapplication.web.handler.LoginHandler;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mobile on 3/2/17.
 */

public class LoginPresenter implements ILoginPresenter, LoginHandler {

    LoginView loginView;
    Activity activity;

    LoginPresenter(LoginView loginView, Activity activity) {
        this.loginView = loginView;
        this.activity = activity;
    }

    @Override
    public void requestLogin(String email, String password) {
        if (checkFieldEmpty(email, password)) {
            if (isEmailValid(email)) {
                makeLoginRequest(email, password);
            }
        }
    }

    private boolean checkFieldEmpty(String email, String password) {
        if (email.trim().length() == 0) {
            loginView.showFeedbackMessage(activity.getString(R.string.emailempty));
            return false;
        } else if (password.trim().length() == 0) {
            loginView.showFeedbackMessage(activity.getString(R.string.passwordEmpty));
            return false;
        } else {
            return true;
        }
    }


    private boolean isEmailValid(String email) {
        return true;
    }


    private void makeLoginRequest(String email, String password) {
        loginView.startProgress();
        WebRequestHandler webRequestHandler = new WebRequestHandler();
        webRequestHandler.requestLogin(email, password, this);

    }


    @Override
    public void loginSuccess(LoginResultPrp loginResultPrp) {
        loginView.stopProgress();
        if (loginResultPrp.getResult() != null) {
            if (loginResultPrp.getResult().getStatus() == 1) {
                //Save Value to shared preferences

                MW_SharedPref sharedPref=new MW_SharedPref();
                sharedPref.setInt(activity,sharedPref.USER_ID,loginResultPrp.getResult().getId());

                loginView.onLoginSuccess();
            } else {
                //Show user wrong message
                loginView.showFeedbackMessage(activity.getString(R.string.wrongusernamepassword));
            }
        } else {
            //Retruend value is null so cant define anything here
            loginView.showFeedbackMessage(activity.getString(R.string.somethingwentwrongTryAgain));
        }
    }

    @Override
    public void loginFail(String message) {
        loginView.stopProgress();

        if(message!=null)
        {
            loginView.showFeedbackMessage(message);
        }
        else
        {
            loginView.showFeedbackMessage(activity.getString(R.string.somethingwentwrongTryAgain));

        }

    }
}
