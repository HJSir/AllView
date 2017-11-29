package com.example.lenovo.mainui.dropmenu;

import android.app.Application;

/**
 * Created by vonchenchen on 2016/4/16 0016.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MyApplication getApplication() {
        return mInstance;
    }
}
