package com.amit.myapplication.view.activity.register;

import android.app.Activity;

import com.amit.myapplication.R;
import com.amit.myapplication.web.connection.IBaseUrl;
import com.amit.myapplication.web.connection.WebInterface;
import com.amit.myapplication.modle.properties.register.RegisterBody;
import com.amit.myapplication.modle.properties.register.RegisterResponse;
import com.amit.myapplication.web.connection.WebRequestHandler;
import com.amit.myapplication.web.handler.RegistrationHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mobile on 3/6/17.
 */

public class RegisterPrester implements IRegisterPresenter,RegistrationHandler {

    Activity activity;
    RegisterView registerView;

    public RegisterPrester(Activity activity,RegisterView registerView) {
        this.activity = activity;
        this.registerView=registerView;
    }

    @Override
    public void requestRegister(RegisterBody registerBody) {

        if(!isFieldEmpty(registerBody.getUserName()))
        {
            registerView.showFeedbackMessage(activity.getString(R.string.usernameempty));
        }
        else if(!isFieldEmpty(registerBody.getEmail()))
        {
            registerView.showFeedbackMessage(activity.getString(R.string.emailempty));
        }
        else if(!isFieldEmpty(registerBody.getMobileNumber()))
        {
            registerView.showFeedbackMessage(activity.getString(R.string.phoneEmpty));
        }
        else if(!isFieldEmpty(registerBody.getPassword()))
        {
            registerView.showFeedbackMessage(activity.getString(R.string.passwordEmpty));
        }
        else
        {
                makeRegisterRequest(registerBody);
        }

    }


    private boolean isFieldEmpty(String value)
    {
        if(value.trim().length()==0)
        {
            return false;
        }
        return  true;
    }


    private void makeRegisterRequest(final RegisterBody registerBody)
    {
        registerView.startProgress();
        WebRequestHandler webRequestHandler=new WebRequestHandler();
        webRequestHandler.requestRegister(registerBody,this);

    }


    @Override
    public void onRegistrationComplete(RegisterResponse registerResponse) {

        if(registerResponse!=null)
        {
            if(registerResponse.getResult().getStatus()==1)
            {
                registerView.onRegistrationComplete();
            }
            else
            {
                registerView.showFeedbackMessage(activity.getString(R.string.thisemailidallreadyexist));
            }
        }
        else
        {
            registerView.showFeedbackMessage(activity.getString(R.string.somethingwentwrongTryAgain));

        }

    }

    @Override
    public void onRegistrationFail(String message) {

    }
}
