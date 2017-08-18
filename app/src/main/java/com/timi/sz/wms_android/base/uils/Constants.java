package com.timi.sz.wms_android.base.uils;

import com.timi.sz.wms_android.mvp.base.BaseApplication;

import static com.timi.sz.wms_android.base.uils.PackageUtils.isApkDebugable;

/**
 * 需要的静态变量
 */

public class Constants {
    //base url
    public static final String BASE_URL = "http://222.92.132.196:9000/index.asmx/";
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
    //用户名
    public static final String USER_NAME = "USER_NAME";
    //用户 真实姓名
    public static final String USER_TRUE_NAME = "USER_TRUE_NAME";
    //用户 部门信息
    public static final String USER_DEPARTMENT = "USER_DEPARTMENT";
    //密码
    public static final String USER_PSW = "USER_PSW";
    //记录密码
    public static final String REMENBER_PSW = "REMENBER_PSW";
    //是否是第一次登录
    public static final String IS_FIRST_LOG = "IS_FIRST_LOG";
    //登录的id
    public static final String CUSER_ID = "CUSER_ID";
    //是否是 debug的状态
    public static final boolean IS_DEBUG = isApkDebugable(BaseApplication.getMApplicationContext());
    /***********
     * 扫码相关
     *********************************************************************************************/
    public static final int REQUEST_CODE = 1001;//主页跳转到扫码

}
