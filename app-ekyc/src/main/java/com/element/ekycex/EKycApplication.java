package com.element.ekycex;

import android.app.Application;

import com.element.camera.ElementEKycCloudSDK;

public class EKycApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ElementEKycCloudSDK.initSDK(getApplicationContext());
    }
}
