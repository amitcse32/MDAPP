package com.amit.myapplication.view.activity.register;

import android.app.Activity;

import com.amit.myapplication.R;
import com.amit.myapplication.modle.networkconnection.IBaseUrl;
import com.amit.myapplication.modle.networkconnection.WebInterface;
import com.amit.myapplication.modle.properties.login.LoginResultPrp;
import com.amit.myapplication.modle.properties.login.register.RegisterBody;
import com.amit.myapplication.modle.properties.login.register.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mobile on 3/6/17.
 */

public class RegisterPrester implements IRegisterPresenter,IBaseUrl {

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
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        final Call<RegisterResponse> result=retrofit.create(WebInterface.class).requestRegister(registerBody.getEmail(),registerBody.getPassword(),registerBody.getUserName(),registerBody.getMobileNumber(),registerBody.getDeviceToken());
        result.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                registerView.stopProgress();


                if(response.body().getResult().getStatus()>0) {
                    registerView.onRegistrationComplete(response.body());
                }
                else if(response.body().getResult().getStatus()==-2)
                {
                    registerView.showFeedbackMessage(activity.getString(R.string.thisemailidallreadyexist));
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerView.stopProgress();
                registerView.showFeedbackMessage(activity.getString(R.string.wrongusernamepassword));
            }
        });
    }


}
