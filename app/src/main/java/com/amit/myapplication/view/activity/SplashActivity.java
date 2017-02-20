package com.amit.myapplication.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.amit.myapplication.R;
import com.amit.myapplication.utils.customcontrols.dialogs.ApplicationDialogs;
import com.amit.myapplication.utils.customcontrols.dialogs.connectionutils.ConnectionUtils;

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

                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        },SPLASH_TIME);
    }

}
