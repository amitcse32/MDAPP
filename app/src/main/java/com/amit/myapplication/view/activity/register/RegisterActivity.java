package com.amit.myapplication.view.activity.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.amit.myapplication.R;
import com.amit.myapplication.modle.properties.login.register.RegisterBody;
import com.amit.myapplication.modle.properties.login.register.RegisterResponse;
import com.amit.myapplication.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView{


    @BindView(R.id.editTextEmail)
    EditText editTextEmail;

    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @BindView(R.id.editTextMobileNumber)
    EditText editTextMobileNumber;

    @BindView(R.id.editTextUserName)
    EditText editTextUserName;

    IRegisterPresenter registerPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerPresenter=new RegisterPrester(this,this);
    }



    @OnClick(R.id.buttonSignUp)
    public void onSignUpClick()
    {
        RegisterBody registerBody=new RegisterBody();
        registerBody.setEmail(editTextEmail.getText().toString());
        registerBody.setMobileNumber(editTextMobileNumber.getText().toString());
        registerBody.setUserName(editTextUserName.getText().toString());
        registerBody.setPassword(editTextPassword.getText().toString());
        registerBody.setDeviceToken("dsfa");
        registerPresenter.requestRegister(registerBody);
    }

    @Override
    public void onRegistrationComplete(RegisterResponse response) {
        Toast.makeText(this,getString(R.string.RegistarationSucessful),Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void startProgress() {
        startProgressDialog(getString(R.string.registrationProcess));
    }

    @Override
    public void stopProgress() {
        stopProgressDialog();
    }

    @Override
    public void showFeedbackMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
