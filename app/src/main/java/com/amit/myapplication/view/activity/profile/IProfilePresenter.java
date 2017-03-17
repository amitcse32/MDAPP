package com.amit.myapplication.view.activity.profile;

import android.net.Uri;


import com.amit.myapplication.modle.properties.profileupdate.ProfileBody;

import java.io.File;

/**
 * Created by Mobile on 3/9/17.
 */

public interface IProfilePresenter {

    void updateProfile(ProfileBody profileBody, Uri uri);
    void requestUserProfile(int userID);

}
