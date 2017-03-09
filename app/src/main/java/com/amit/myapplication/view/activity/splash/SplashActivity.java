package com.amit.myapplication.view.activity.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.amit.myapplication.R;
import com.amit.myapplication.utils.customcontrols.dialogs.ApplicationDialogs;
import com.amit.myapplication.utils.customcontrols.dialogs.connectionutils.ConnectionUtils;
import com.amit.myapplication.utils.customcontrols.dialogs.sharedpref.MW_SharedPref;
import com.amit.myapplication.view.activity.home.HomeActivity;
import com.amit.myapplication.view.activity.login.LoginActivity;
import com.amit.myapplication.view.activity.profile.ProfileActivity;

public class SplashActivity extends AppCompatActivity {



    //Splash Time
    int SPLASH_TIME=3000;

    //Splash Screen Load Start
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
    }

    //Check internet connetion
    private void checkConnection()
    {


        //Show dialog if there is no internet connection
        ConnectionUtils connectionUtils=new ConnectionUtils();
        boolean value=connectionUtils.checkInternetConnection(this);
        if(value==true)
        {
            startSplash();
        }
        else
        {
            ApplicationDialogs applicationDialogs=new ApplicationDialogs();
            applicationDialogs.showMessageDialogWithFinish(this,getString(R.string.internetconnectionmessage));
        }

    }

    private void startSplash()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Navigate to Login or home screen


                MW_SharedPref sharedPref=new MW_SharedPref();
                if(sharedPref.getInt(SplashActivity.this,sharedPref.USER_ID)>0)
                {
                    Intent intent=new Intent(SplashActivity.this,ProfileActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },SPLASH_TIME);
    }

}
