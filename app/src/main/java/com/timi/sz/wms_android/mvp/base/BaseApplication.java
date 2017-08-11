package com.timi.sz.wms_android.mvp.base;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * base  appliacation
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化 Log
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
