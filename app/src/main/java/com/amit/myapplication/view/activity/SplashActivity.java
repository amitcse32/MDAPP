package com.amit.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.amit.myapplication.R;
import com.amit.myapplication.utils.customcontrols.dialogs.ApplicationDialogs;

public class SplashActivity extends AppCompatActivity {



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
        ApplicationDialogs applicationDialogs=new ApplicationDialogs();
        applicationDialogs.showMessageDialogWithFinish(this,getString(R.string.internetconnectionmessage));

    }

}
