package com.timi.sz.wms_android.base.uils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * 切换语言的工具类
 * author: timi
 * create at: 2017-08-22 13:55
 */
public class LanguageUtils {
    /**
     * 根据用户设置的语言类型  显示不同的语言
     * author: timi
     * create at: 2017/8/22 11:08
     */
    public static  void switchAppLanguage(Context context) {
        String spLanguage = SpUtils.getInstance().getString(Constants.LOCALE_LAUGUAGE);
        Resources resources = context.getApplicationContext().getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        switch (spLanguage) {
            case "zh-CN":
                config.locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case "en":
                config.locale = Locale.ENGLISH;
                break;
            case "zh-TW":
                config.locale = Locale.TRADITIONAL_CHINESE;
                break;
            default:
                break;
        }
        resources.updateConfiguration(config, dm);
    }
}
