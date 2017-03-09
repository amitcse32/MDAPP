package com.amit.myapplication.view.activity.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.amit.myapplication.R;
import com.amit.myapplication.modle.properties.login.LoginResultPrp;
import com.amit.myapplication.utils.customcontrols.dialogs.sharedpref.MW_SharedPref;
import com.amit.myapplication.view.activity.BaseActivity;
import com.amit.myapplication.view.activity.home.HomeActivity;
import com.amit.myapplication.view.activity.profile.ProfileActivity;
import com.amit.myapplication.view.activity.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView{



    @BindView(R.id.editTextEmail)
    EditText editTextEmail;

    @BindView(R.id.editTextPassword)
    EditText editTextPassword;


    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter=new LoginPresenter(this,this);
    }



    @OnClick(R.id.buttonLogin)
    public void onLoginButtonClick()
    {
        loginPresenter.requestLogin(editTextEmail.getText().toString(),editTextPassword.getText().toString());
    }


    @OnClick(R.id.textViewForgotPassword)
    public void onForgotPasswordClick()
    {
        loginPresenter.requestForgotPassword(editTextEmail.getText().toString());
    }

    @OnClick(R.id.textViewRegister)
    public void onRegisterClick()
    {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void onLoginComplete(LoginResultPrp loginResult) {

        if(loginResult.getResult().getStatus()==1)
        {

            MW_SharedPref sharedPref=new MW_SharedPref();
            sharedPref.setInt(this,sharedPref.USER_ID,loginResult.getResult().getId());

            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(this,getString(R.string.wrongusernamepassword),Toast.LENGTH_SHORT).show();
        }


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
    public void showFeedbackMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onForgetPasswordComplete() {

    }
}
