package com.example.administrator.testapplication.util;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/6/25.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
