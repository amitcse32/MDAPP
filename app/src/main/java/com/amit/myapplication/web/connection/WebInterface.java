package com.amit.myapplication.web.connection;

import com.amit.myapplication.modle.properties.login.LoginResultPrp;
import com.amit.myapplication.modle.properties.profile.ProfileResult;
import com.amit.myapplication.modle.properties.profileupdate.ProfileUpdateResult;
import com.amit.myapplication.modle.properties.register.RegisterResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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


    @GET("milkwala/ws/returnProfile.php")
    Call<ProfileResult> requestProfile(@Query("userId")String userID);

    @Multipart
    @POST("milkwala/ws/updateProfile.php")
    Call<ProfileUpdateResult> updateprofile(@Part("userId") RequestBody userId, @Part("userName") RequestBody userName, @Part("phone") RequestBody phone,
                                     @Part MultipartBody.Part image, @Part("address") RequestBody address, @Part("qrCode") RequestBody qrCode);


}
