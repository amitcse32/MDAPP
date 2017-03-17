package com.amit.myapplication.web.handler;

import com.amit.myapplication.modle.properties.profileupdate.ProfileUpdateResult;

/**
 * Created by Mobile on 3/16/17.
 */

public interface ProfileUpdateHandler {

    void onProfileUpdate(ProfileUpdateResult profileUpdateResult);
    void onProfileUpdateFail(String message);
}
