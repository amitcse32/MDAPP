package com.amit.myapplication.view.activity.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amit.myapplication.R;
import com.amit.myapplication.web.connection.IBaseUrl;
import com.amit.myapplication.modle.properties.profile.ProfileResult;
import com.amit.myapplication.modle.properties.profileupdate.ProfileBody;
import com.amit.myapplication.modle.properties.profileupdate.ProfileUpdateResult;
import com.amit.myapplication.utils.customcontrols.dialogs.sharedpref.MW_SharedPref;
import com.amit.myapplication.view.activity.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity implements ProfileView,IBaseUrl {




    @BindView(R.id.editTextAddress)
    EditText editTextAddress;

    @BindView(R.id.editTextUserName)
    EditText editTextUserName;

    @BindView(R.id.editTextPhone)
    EditText editTextPhone;

    @BindView(R.id.imageViewUserImage)
    CircleImageView imageViewUserImage;


    Uri SELECTED_PIC_URI;









    IProfilePresenter profilePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        profilePresenter=new ProfilePresenter(this,this);





        MW_SharedPref sharedPref=new MW_SharedPref();

        profilePresenter.requestUserProfile(sharedPref.getInt(this,sharedPref.USER_ID));


    }






    @OnClick(R.id.buttonUpdateProfile)
    public void updateProfileClick()
    {
        ProfileBody profileBody=new ProfileBody();
        profileBody.setAddress(editTextAddress.getText().toString());
        profileBody.setQrCode("fasdfasdfasdfasdfasdf");

        MW_SharedPref sharedPref=new MW_SharedPref();
        profileBody.setUserId(sharedPref.getInt(this,sharedPref.USER_ID));
        profileBody.setUserName(editTextUserName.getText().toString());
        profileBody.setPhone(editTextPhone.getText().toString());

        profilePresenter.updateProfile(profileBody,SELECTED_PIC_URI);


    }



    @Override
    public void loadProfile(ProfileResult profileResult) {

        if(profileResult!=null)
        {
            editTextAddress.setText(profileResult.getResult().getData().getAddress());
            editTextPhone.setText(profileResult.getResult().getData().getPhone());
            editTextUserName.setText(profileResult.getResult().getData().getUserName());

            Picasso.with(this).load(USER_IMAGE_BASE_URL+profileResult.getResult().getData().getImage()).into(imageViewUserImage);
        }
        else
        {
            Toast.makeText(this,"Profile loading fail",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onProfileUpdateComplete() {



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


    @OnClick(R.id.imageViewUserImage)
    public void onUserImageClick()
    {
        selectImageOptionsDialog(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==BaseActivity.CAMERA_PERMISSION)
        {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,getString(R.string.pleaseenablecameraPermission),Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode==BaseActivity.GALLERY_PERMISSION)
        {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,getString(R.string.pleaseenableReadExternalStoragePermission),Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==RESULT_OK) {
            if (requestCode == BaseActivity.CAMERA_INTENT) {

            } else if (requestCode == BaseActivity.GALLERY_INTENT) {
                  SELECTED_PIC_URI = data.getData();

                 imageViewUserImage.setImageURI(SELECTED_PIC_URI);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
