package com.amit.myapplication.web.handler;

import com.amit.myapplication.modle.properties.profile.ProfileResult;

/**
 * Created by Mobile on 3/17/17.
 */

public interface ProfileReadHandler {


    void onProfileLoadComplete(ProfileResult profileResult);
    void onProfileLoadFail(String message);

}
