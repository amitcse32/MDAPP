package com.amit.myapplication.view.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.amit.myapplication.R;

/**
 * Created by Mobile on 3/2/17.
 */

public class BaseActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    public static int CAMERA_PERMISSION=101;
    public static int GALLERY_PERMISSION=102;
    public static int CAMERA_INTENT=103;
    public static int GALLERY_INTENT=104;

    public void startProgressDialog(String message)
    {
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }


    public void stopProgressDialog()
    {
        if(progressDialog!=null) {
            progressDialog.dismiss();
        }
    }


    public void selectImageOptionsDialog(final Activity activity)
    {

        String[] OPTIONS=getResources().getStringArray(R.array.imageSelectOptions);
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setItems(OPTIONS, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which==0)
                {
                    if(ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.CAMERA},
                                CAMERA_PERMISSION);

                    }
                    else {

                        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,CAMERA_INTENT);

                    }
                }
                else if(which==1)
                {
                    if(ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                GALLERY_PERMISSION);

                    }
                    else {
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent,GALLERY_INTENT);
                    }
                }


            }
        });
        dialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
