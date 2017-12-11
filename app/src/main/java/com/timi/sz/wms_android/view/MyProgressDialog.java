package com.timi.sz.wms_android.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.http.HttpManager;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 11:21
 */

public class MyProgressDialog  extends MyDialog {
    private static volatile MyProgressDialog instance = null;

    private static MyProgressDialog getInstance(Context context) {
        if (null == instance) {
            synchronized (HttpManager.class) {
                if (null == instance) {
                    instance = new MyProgressDialog(context, R.layout.dialog_progress);
//                    instance.setCantCancelByBackPress();
                }
            }
        }
        return instance;
    }

    private MyProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 显示进度条
     */
    public static void showProgressDialog(Activity context) {
        if (null == instance) {
            getInstance(context);
        }
        /**
         * 解决切换 activity dialog 崩溃
         */
        if(!instance.getContext().equals(context)){
            instance=null;
            getInstance(context);
        }
        if(!instance.isShowing()){
            instance.show();
            LogUitls.e("显示Dialog--->",context.getPackageName());
        }
    }

    /**
     * 隐藏进度条
     */
    public static void hideProgressDialog() {
        if(null!=instance&&instance.isShowing()){
            LogUitls.e("隐藏Dialog--->",instance.getContext().getPackageName());
            instance.dismiss();
            instance=null;
        }
    }
}
