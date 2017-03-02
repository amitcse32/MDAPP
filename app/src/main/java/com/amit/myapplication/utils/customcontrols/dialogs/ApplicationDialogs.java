package com.amit.myapplication.utils.customcontrols.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.amit.myapplication.R;

/**
 * Created by Mobile on 2/18/17.
 */

public class ApplicationDialogs {



    // Show dialog and close on press of OK

    public void showMessageDialogWithFinish(final Activity activity, String message) {

        AlertDialog.Builder dialog=new AlertDialog.Builder(activity);
        dialog.setCancelable(false);
        dialog.setMessage(message);
        dialog.setTitle(activity.getString(R.string.feedback));
        dialog.setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            activity.finish();
            }
        });
        dialog.show();

    }


    // Show dialog and keep activity running on press of ok
    public void showMessageDialog(Activity activity,String message)
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(activity);
        dialog.setCancelable(false);
        dialog.setMessage(message);
        dialog.setTitle(activity.getString(R.string.feedback));
        dialog.setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
