package com.amit.myapplication.modle.networkconnection;

import com.amit.myapplication.modle.properties.login.LoginResultPrp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mobile on 3/2/17.
 */

public interface WebInterface {

    @FormUrlEncoded
    @POST("milkwala/ws/login.php")
    Call<LoginResultPrp> requestLogin(@Field("email")String email,@Field("password") String password);


    @FormUrlEncoded
    @POST("milkwala/ws/forgetPassword.php")
    Call<ResponseBody> requestForgetPassword(@Field("email")String email);



}
