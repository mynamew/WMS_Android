package com.timi.sz.wms_android.base.uils;

import com.orhanobut.logger.Logger;

/**
 * 封装 Logger 的工具类
 * author: timi
 * create at: 2017-08-17 09:29
 */
public class LogUitls {
    public static void e(String key, Object ob) {
        LoggerLog(key, 0, ob);
    }

    public static void d(String key, Object ob) {
        LoggerLog(key, 1, ob);
    }

    public static void i(String key, Object ob) {
        LoggerLog(key, 2, ob);
    }

    public static void v(String key, Object ob) {
        LoggerLog(key, 3, ob);
    }

    public static void e(Object ob) {
        LoggerLog(0, ob);
    }

    public static void d(Object ob) {
        LoggerLog(1, ob);
    }

    public static void i(Object ob) {
        LoggerLog(2, ob);
    }

    public static void v(Object ob) {
        LoggerLog(3, ob);
    }

    public static void LoggerLog(String key, int type, Object ob) {
        if (Constants.IS_DEBUG)
            switch (type) {
                case 0:
                    Logger.e(key + ob);
                    break;
                case 1:
                    Logger.d(key + ob);
                    break;
                case 2:
                    Logger.i(key + ob);
                    break;
                case 3:
                    Logger.v(key + ob);
                    break;
            }
    }

    public static void LoggerLog(int type, Object ob) {
        if (Constants.IS_DEBUG)
            switch (type) {
                case 0:
                    Logger.e("" + ob);
                    break;
                case 1:
                    Logger.d("" + ob);
                    break;
                case 2:
                    Logger.i("" + ob);
                    break;
                case 3:
                    Logger.v("" + ob);
                    break;
            }
    }
}
