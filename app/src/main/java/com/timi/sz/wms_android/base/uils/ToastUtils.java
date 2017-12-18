package com.timi.sz.wms_android.base.uils;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.mvp.base.BaseApplication;

/**
 * Toast 工具类
 * author: timi
 * create at: 2017/8/15 18:32
 */
public class ToastUtils {
    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {

        public void run() {

            mToast.cancel();

        }

    };

    public static void showLong(Object o) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getMApplicationContext(), o.toString(), Toast.LENGTH_LONG);

        } else {
            mToast.setText(o.toString());
        }
        mToast.show();
    }

    public static void showLong(Context context, Object o) {
        if (mToast == null) {
            mToast = Toast.makeText(context, o.toString(), Toast.LENGTH_LONG);

        } else {
            mToast.setText(o.toString());
        }
        mToast.show();
    }

    public static void showShort(Object o) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getMApplicationContext(), o.toString(), Toast.LENGTH_SHORT);

        } else {
            mToast.setText(o.toString());
        }
        mToast.show();
    }
    public static void showShort(@StringRes int strId) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getMApplicationContext(), BaseActivity.getCurrentActivty().getString(strId), Toast.LENGTH_SHORT);

        } else {
            mToast.setText(BaseActivity.getCurrentActivty().getString(strId));
        }
        mToast.show();
    }
    public static void showShort(Context context, Object o) {
        if (mToast == null) {
            mToast = Toast.makeText(context, o.toString(), Toast.LENGTH_SHORT);

        } else {
            mToast.setText(o.toString());
        }
        mToast.show();
    }

    public static void showToast(Context mContext, int resId, int duration) {

        showToast(mContext, mContext.getResources().getString(resId), duration);
    }

    public static void showToast(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);

        if (mToast != null)

            mToast.setText(text);

        else

            mToast = Toast.makeText(mContext, text, duration);

        mHandler.postDelayed(r, duration);

        mToast.show();

    }
}
