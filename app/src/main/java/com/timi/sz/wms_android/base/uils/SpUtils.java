package com.timi.sz.wms_android.base.uils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.mvp.base.BaseApplication;

import static com.timi.sz.wms_android.base.uils.Constants.CUSER_ID;
import static com.timi.sz.wms_android.base.uils.Constants.ORGANAZATION_ID;


/**
 * sharepreference 的工具类
 * author: timi
 * create at: 2017-08-15 18:12
 */
public class SpUtils {
    //shaeperference
    private SharedPreferences mPreference = null;
    //实例
    //shareperference的名字
    private String SHAREPERFERECE_NAME = "config";
    //editor
    private SharedPreferences.Editor editor = null;

    private SpUtils() {

    }

    private static class LazyHolder {
        private static final SpUtils INSTANCE = new SpUtils();
    }

    public static final SpUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * 获取shareperference实例
     *
     * @return
     */
    public SharedPreferences getSharedPreferences() {
        if (null == mPreference) {
            mPreference = BaseApplication.getMApplicationContext().getSharedPreferences(SHAREPERFERECE_NAME, Context.MODE_PRIVATE);
            editor = mPreference.edit();
        }
        return mPreference;
    }

    /**
     * 存入 字符串
     *
     * @param key
     * @param value
     */
    public SpUtils putString(String key, String value) {
        getSharedPreferences();
        editor.putString(key, value).commit();
        return this;
    }

    /**
     * 获取字符串的value
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        getSharedPreferences();
        return mPreference.getString(key, "");
    }

    /**
     * 存入 整形
     *
     * @param key
     * @param value
     */
    public SpUtils putInt(String key, int value) {
        getSharedPreferences();
        editor.putInt(key, value).commit();
        return this;
    }

    /**
     * 获取整形的value
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        getSharedPreferences();
        return mPreference.getInt(key, 0);
    }

    /**
     * 存储 float 类型
     *
     * @param key
     * @param value
     */
    public SpUtils putFloat(String key, float value) {
        getSharedPreferences();
        editor.putFloat(key, value).commit();
        return this;
    }

    /**
     * 获取 float 类型value
     *
     * @param key
     * @return
     */
    public float getFloat(String key) {
        getSharedPreferences();
        return mPreference.getFloat(key, 0);
    }

    /**
     * 存储布尔类型数据
     *
     * @param key
     * @param value
     */
    public SpUtils putBoolean(String key, boolean value) {
        getSharedPreferences();
        editor.putBoolean(key, value).commit();
//        Logger.d("存入的boolean 型数据--->"+mPreference.getBoolean(key,false));
        return this;
    }

    /**
     * 获取 布尔类型value
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        getSharedPreferences();
        return mPreference.getBoolean(key, false);
    }

    /**
     * 获取 用户id
     * author: timi
     * create at: 2017/8/18 9:23
     */
    public int getUserId() {
        getSharedPreferences();
        return mPreference.getInt(CUSER_ID, -1);
    }

    /**
     * 存入 用户id
     * author: timi
     * create at: 2017/8/18 9:56
     */
    public void putUserid(int userid) {
        getSharedPreferences();
        editor.putInt(CUSER_ID, userid).commit();
    }
    /**
     * 获取 组织id
     * author: timi
     * create at: 2017/8/18 9:23
     */
    public int getOrgId() {
        getSharedPreferences();
        return mPreference.getInt(ORGANAZATION_ID, -1);
    }

    /**
     * 存入 组织id
     * author: timi
     * create at: 2017/8/18 9:56
     */
    public void putOrgId(int userid) {
        getSharedPreferences();
        editor.putInt(ORGANAZATION_ID, userid).commit();
    }

    /**
     * 存入token
     *
     * @param value
     */
    public SpUtils putAuthorization(String value) {
        getSharedPreferences();
        editor.putString(Constants.AUTHORIZATION, value).commit();
        return this;
    }

    /**
     * 获取字符串的value
     *
     * @return
     */
    public String getAuthorization() {
        getSharedPreferences();
        String authorization = mPreference.getString(Constants.AUTHORIZATION, "");
        if (TextUtils.isEmpty(authorization)) {
            return "";
        }
        return Constants.AUTHORIZATION_VALUE + authorization;
    }

    /**
     * 存入token
     *
     * @param value
     */
    public SpUtils putBaseUrl(String value) {
        getSharedPreferences();
        editor.putString(Constants.SP_BASE_URL, value).commit();
        return this;
    }

    /**
     * 获取字符串的value
     *
     * @return
     */
    public String getBaseUrl() {
        getSharedPreferences();
        return mPreference.getString(Constants.SP_BASE_URL, "");
    }

    /**
     * 存入tenancyName
     *
     * @param value
     */
    public SpUtils puttenancyName(String value) {
        getSharedPreferences();
        editor.putString(Constants.TENANCY_NAME, value).commit();
        return this;
    }

    /**
     * tenancyName
     *
     * @return
     */
    public String gettenancyName() {
        getSharedPreferences();
        return mPreference.getString(Constants.TENANCY_NAME, "");
    }

    /**
     * 存入token
     *
     * @param value
     */
    public SpUtils putLocaleLanguage(String value) {
        getSharedPreferences();
        editor.putString(Constants.LOCALE_LAUGUAGE, value).commit();
        return this;
    }

    /**
     * 获取字符串的value
     *
     * @return
     */
    public String getLocaleLanguage() {
        getSharedPreferences();
        return mPreference.getString(Constants.LOCALE_LAUGUAGE, "");
    }
    /**
     * 存入用户名
     *
     * @param value
     */
    public SpUtils putUserName(String value) {
        getSharedPreferences();
        editor.putString(Constants.USER_NAME, value).commit();
        return this;
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public String getUserName() {
        getSharedPreferences();
        return mPreference.getString(Constants.USER_NAME, "");
    }
    /**
     * 存入用户名
     *
     * @param value
     */
    public SpUtils putPassword(String value) {
        getSharedPreferences();
        editor.putString(Constants.USER_PSW, value).commit();
        return this;
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public String getPassword() {
        getSharedPreferences();
        return mPreference.getString(Constants.USER_PSW, "");
    }
}
