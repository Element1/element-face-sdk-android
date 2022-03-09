package com.element.facex;

import android.app.Application;

import com.element.camera.ElementFaceSDK;

public class FaceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ElementFaceSDK.initSDK(FaceApplication.this);
    }
}
