package com.timi.sz.wms_android.base;

import android.support.multidex.MultiDexApplication;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.http.HttpManager;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by timi on 2017/5/13.
 * ainm :  Application 基类
 */

public class SuperApplication extends MultiDexApplication {
    static SuperApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        HttpManager.init(this);//不做任何操作仅仅是缓存一下Application引用
        Logger.addLogAdapter(new AndroidLogAdapter());
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }
    static public SuperApplication getInstance() {
        return instance;
    }
}
