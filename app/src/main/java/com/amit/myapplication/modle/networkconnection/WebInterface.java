package com.amit.myapplication.modle.networkconnection;

import com.amit.myapplication.modle.properties.login.LoginResultPrp;
import com.amit.myapplication.modle.properties.login.profile.ProfileResult;
import com.amit.myapplication.modle.properties.login.register.RegisterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @FormUrlEncoded
    @POST("milkwala/ws/register.php")
    Call<RegisterResponse> requestRegister(@Field("email")String email, @Field("password") String password, @Field("userName")String username, @Field("phone") String phone, @Field("deviceToken")String deviceToken);


    @GET("milkwala/ws/returnProfile.php?userId={userID}")
    Call<ProfileResult> requestProfile(@Query("userID")String userID);



}
