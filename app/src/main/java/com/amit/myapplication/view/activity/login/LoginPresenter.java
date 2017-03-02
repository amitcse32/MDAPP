package com.amit.myapplication.view.activity.login;

import android.app.Activity;

import com.amit.myapplication.R;
import com.amit.myapplication.modle.networkconnection.IBaseUrl;
import com.amit.myapplication.modle.networkconnection.WebInterface;
import com.amit.myapplication.modle.properties.login.LoginResultPrp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mobile on 3/2/17.
 */

public class LoginPresenter implements ILoginPresenter ,IBaseUrl {

    LoginView loginView;
    Activity activity;
    LoginPresenter(LoginView loginView, Activity activity)
    {
        this.loginView=loginView;
        this.activity=activity;
    }


    @Override
    public void requestLogin(String email, String password) {


            if(checkFieldEmpty(email,password))
            {
                if(isEmailValid(email))
                {
                    makeLoginRequest(email,password);
                }
            }

    }

    @Override
    public void requestForgotPassword(String email) {
        if(checkFieldEmpty(email,"PASSWORD"))
        {
            if(isEmailValid(email))
            {
                makeForgetPasswordRequest(email);
            }
        }
    }


    private boolean checkFieldEmpty(String email,String password)
    {
        if(email.trim().length()==0)
        {
            loginView.showFeedbackMessage(activity.getString(R.string.emailempty));
            return false;
        }
        else if(password.trim().length()==0)
        {
            loginView.showFeedbackMessage(activity.getString(R.string.passwordEmpty));

            return  false;
        }
        else
        {
            return  true;
        }
    }


    private boolean isEmailValid(String email)
    {
        return  true;
    }


    private void makeLoginRequest(String email,String password)
    {
        loginView.startProgress();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        final Call<LoginResultPrp> result=retrofit.create(WebInterface.class).requestLogin(email,password);
        result.enqueue(new Callback<LoginResultPrp>() {
            @Override
            public void onResponse(Call<LoginResultPrp> call, Response<LoginResultPrp> response) {
                loginView.stopProgress();
                loginView.onLoginComplete(response.body());
            }

            @Override
            public void onFailure(Call<LoginResultPrp> call, Throwable t) {
                loginView.stopProgress();
                loginView.showFeedbackMessage(activity.getString(R.string.wrongusernamepassword));
            }
        });
    }


    private void makeForgetPasswordRequest(String email)
    {
        loginView.startProgress();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        final Call<ResponseBody> result=retrofit.create(WebInterface.class).requestForgetPassword(email);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loginView.stopProgress();
                loginView.onForgetPasswordComplete();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loginView.stopProgress();
                loginView.showFeedbackMessage(activity.getString(R.string.pleaseentervalidemail));
            }
        });
    }
}
