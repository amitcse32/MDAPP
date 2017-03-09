package com.amit.myapplication.view.activity.profile;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.amit.myapplication.R;
import com.amit.myapplication.modle.properties.login.profile.ProfileResult;
import com.amit.myapplication.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity implements ProfileView {



    @BindView(R.id.textViewCity)
    TextView textViewCity;

    @BindView(R.id.textViewState)
    TextView textViewState;




    //Data Variables
    String[] ARRAY_CITY;
    String[] ARRAY_STATES;


    IProfilePresenter profilePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        profilePresenter=new ProfilePresenter(this,this);
        profilePresenter.loadCities();
        profilePresenter.loadStates();


    }

    @Override
    public void loadProfile(ProfileResult profileResult) {

    }

    @Override
    public void updateProfileResult() {

    }

    @Override
    public void startProgress() {

        startProgressDialog(getString(R.string.loadingPleasewait));
    }

    @Override
    public void stopProgress() {
        stopProgressDialog();
    }

    @Override
    public void loadCities(String[] cities) {


        ARRAY_CITY=cities;


    }

    @Override
    public void loadStates(String[] states) {

        ARRAY_STATES=states;
    }


    @OnClick(R.id.textViewCity)
    public void onCityClick()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setItems(ARRAY_CITY, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textViewCity.setText(ARRAY_CITY[which]);
            }
        });
        dialog.show();
    }

    @OnClick(R.id.textViewState)
    public void onStateClick()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setItems(ARRAY_STATES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textViewState.setText(ARRAY_STATES[which]);
            }
        });
        dialog.show();
    }

}
