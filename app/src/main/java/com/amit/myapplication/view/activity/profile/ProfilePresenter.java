package com.amit.myapplication.view.activity.profile;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.amit.myapplication.R;
import com.amit.myapplication.web.connection.IBaseUrl;
import com.amit.myapplication.web.connection.WebInterface;
import com.amit.myapplication.modle.properties.profile.ProfileResult;
import com.amit.myapplication.modle.properties.profileupdate.ProfileBody;
import com.amit.myapplication.modle.properties.profileupdate.ProfileUpdateResult;
import com.amit.myapplication.web.connection.WebRequestHandler;
import com.amit.myapplication.web.handler.ProfileReadHandler;
import com.amit.myapplication.web.handler.ProfileUpdateHandler;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mobile on 3/9/17.
 */

public class ProfilePresenter implements IProfilePresenter, ProfileReadHandler,ProfileUpdateHandler{

    Activity activity;
    ProfileView view;

    ProfilePresenter(Activity activity,ProfileView profileView)
    {
        this.activity=activity;
        this.view=profileView;
    }



    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = activity.getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    @Override
    public void updateProfile(final ProfileBody profileBody, Uri uri) {


        if(getRealPathFromURI(uri)!=null) {
            view.startProgress();
            File image=new File(getRealPathFromURI(uri));
            WebRequestHandler webRequestHandler=new WebRequestHandler();
            webRequestHandler.requestUpdateProfile(image,profileBody,this);

        }
    }

    @Override
    public void requestUserProfile(int userID) {

        view.startProgress();
        WebRequestHandler webRequestHandler=new WebRequestHandler();
        webRequestHandler.requestUserProfile(userID+"",this);





    }

    @Override
    public void onProfileLoadComplete(ProfileResult profileResult) {

        view.stopProgress();

        if(profileResult!=null)
        {
            if(profileResult.getResult().getStatus()==1)
            {
                view.loadProfile(profileResult);
            }
            else
            {
                view.showFeedbackMessage(activity.getString(R.string.unabletoreadyourprofile));
            }
        }
        else
        {
            view.showFeedbackMessage(activity.getString(R.string.somethingwentwrongTryAgain));
        }
    }

    @Override
    public void onProfileLoadFail(String message) {
        view.stopProgress();
        if(message!=null)
        {
            view.showFeedbackMessage(message);

        }
        else
        {
            view.showFeedbackMessage(activity.getString(R.string.somethingwentwrongTryAgain));
        }
    }

    @Override
    public void onProfileUpdate(ProfileUpdateResult profileUpdateResult) {

        view.stopProgress();
        if(profileUpdateResult!=null)
        {
            if(profileUpdateResult.getResult().getStatus()==1)
            {
                view.showFeedbackMessage(activity.getString(R.string.profileupdated));

            }
            else
            {
                view.showFeedbackMessage(activity.getString(R.string.unbaletoupdateyourprofile));

            }

        }
        else
        {
            view.showFeedbackMessage(activity.getString(R.string.somethingwentwrongTryAgain));
        }
    }

    @Override
    public void onProfileUpdateFail(String message) {
        view.stopProgress();
        if(message!=null)
        {
            view.showFeedbackMessage(message);

        }
        else
        {
            view.showFeedbackMessage(activity.getString(R.string.somethingwentwrongTryAgain));
        }
    }
}
