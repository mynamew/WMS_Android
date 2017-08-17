package com.timi.sz.wms_android.mvp.base;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cn.jpush.android.api.JPushInterface;

import static com.timi.sz.wms_android.base.uils.Constants.IS_DEBUG;

/**
 * base  appliacation
 */

public class BaseApplication extends Application {
    public static  Application mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        //初始化 Log
        Logger.addLogAdapter(new AndroidLogAdapter());
        JPushInterface.init(this);
        JPushInterface.setDebugMode(IS_DEBUG);
        Logger.d("程序是否是 debug状态--->"+IS_DEBUG);
    }

    /**
     * 获取 application context
     * @return
     */
    public static Context getMApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
