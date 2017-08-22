package com.timi.sz.wms_android.base.uils;

import com.timi.sz.wms_android.mvp.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

import static com.timi.sz.wms_android.base.uils.PackageUtils.isApkDebugable;

/**
 * 需要的静态变量
 */

public class Constants {
    //base url
    public static final String BASE_URL = "http://v.juhe.cn/weixin/";
    //    public static final String BASE_URL = "http://v.juhe.cn/weixin/";
    //超时时间   5s
    public static int DEFAULT_TIMEOUT = 5000;
    //网络请求成功的code码
    public static int SUCCESS_CODE = 0;
    /**********
     * 状态栏颜色
     *********************************************************************************************/
    public static final String StatusColorStr = "#c6ae75";
    /**********
     * SharePerference 相关
     *********************************************************************************************/
    //记录密码
    public static final String REMENBER_PSW = "REMENBER_PSW";
    //是否是第一次登录
    public static final String IS_FIRST_LOG = "IS_FIRST_LOG";
    //用户名
    public static final String USER_NAME = "USER_NAME";
    //用户编号
    public static final String USER_NUM = "USER_NUM";
    //用户密码
    public static final String USER_PSW = "USER_PSW";
    //用户部门
    public static final String USER_DEPART = "USER_DEPART";
    //用户性别
    public static final String USER_SEX = "USER_SEX";
    //用户手机
    public static final String USER_TEL = "USER_TEL";
    //用户所属组织
    public static final String USER_FROM = "USER_FROM";
    //用户权限组织
    public static final String USER_ROOT = "USER_ROOT";
    //登录的id
    public static final String CUSER_ID = "USER_ID";
    //是否是 debug的状态
    public static final boolean IS_DEBUG = isApkDebugable(BaseApplication.getMApplicationContext());
    /***********
     * 扫码相关
     *********************************************************************************************/
    public static final int REQUEST_CODE = 1001;//主页跳转到扫码
    /***********
     * token   key /  value的前缀
     *********************************************************************************************/
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_VALUE = "Bearer ";
    /***********
     * 系统设置
     *********************************************************************************************/
    public static final String LOCALE_LAUGUAGE = "LOCALE_LAUGUAGE";// 0 简体中文 1 繁体中文 2 英文
    /***********
     * sp 存储用户选的baseurl
     *********************************************************************************************/
    public static final String SP_BASE_URL = "SP_BASE_URL";
    /***********
     * 用户所有的服务器url
     *********************************************************************************************/
    public static final Map<String, String> SERVER_URLS = new HashMap<>();

    static {
        SERVER_URLS.put("百度服务器", "www.baidu.com");
        SERVER_URLS.put("天猫服务器", "www.tianmao.com");
        SERVER_URLS.put("淘宝服务器", "www.taobao.com");
        SERVER_URLS.put("谷歌服务器", "www.google.com");
        SERVER_URLS.put("火狐服务器", "www.huhu.com");
    }
}
