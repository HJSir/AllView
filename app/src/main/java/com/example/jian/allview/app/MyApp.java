package com.example.jian.allview.app;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by jian on 2017/9/17.
 */

public class MyApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        //**************************************相关第三方SDK的初始化等操作*************************************************
        Fresco.initialize(this);
    }
}