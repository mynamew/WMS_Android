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
    public static final String BASE_URL = "http://szjuqent.imwork.net:83/";
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
     * SharePerference　存储用户信息 相关
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
    public static final int REQUEST_SCAN_CODE_MATERIIAL = 1002;//物料条码 扫码
    public static final int REQUEST_SCAN_CODE_LIB_LOATION= 1003;//库位码  扫码
    /***********
     * SharePerference存储token   key /  value的前缀
     *********************************************************************************************/
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_VALUE = "Bearer ";
    /***********
     *SharePerference　存储 应用语言设置 中文简体（zh-CN）、中文繁体（zh-TW）、English（en）
     *********************************************************************************************/
    public static final String LOCALE_LAUGUAGE = "Abp.Localization.CultureName";
    /***********
     * SharePerference存储用户选的baseurl
     *********************************************************************************************/
    public static final String SP_BASE_URL = "SP_BASE_URL";
    /***********
     * SharePerference 存储用户的租户
     *********************************************************************************************/
    public static final String TENANCY_NAME = "TENANCY_NAME";
    /***********
     * SharePerference 软件更新相关
     *********************************************************************************************/
    public static final String APK_NAME = "wms.apk";      //apk 名称
    public static final String IS_HAVE_DOWNLOAD_NEW = "is_have_download_new";//是否下载了最新版本的安装包


    /***********
     * 跳转 传递的相关字段
     *********************************************************************************************/
    /***********
     * 跳转到登录界面 是否显示服务配置的弹框（ 可能是来自于服务配置按钮的点击）
     *********************************************************************************************/
    public static final String IS_NEED_SHOW_SHOW_SERVER_SET = "isNeedShowServerSet.apk";


    /***********
     * 入库跳转到查询界面的不同code码
     *********************************************************************************************/
    public static final String CODE_STR="stockin_code";//入库的code码的key
    public static final int BUY_ORDE_NUM = 5001;//采购单
    public static final int BUY_SEND_NUM = 5002;//送货单
    public static final int COME_MATERAIL_NUM = 5003;//来料单
    public static final int CREATE_PRO_CHECK_NUM = 5004;//产成品-审核单
    public static final int CREATE_PRO_CREATE_ORDER_NUM = 5005;//产成品-生单
    public static final int OTHER_IN_STOCK_SELECT_ORDERNO = 5006;//其他入库-选单
    public static final int OTHER_IN_STOCK_SCAN = 5007;//其他入库-扫描
    public static final int OUT_RETURN_MATERAIL = 5008;//委外退料-选单
    public static final int CREATE_RETURN_MATERAIL = 5009;//生产退料-选单
    public static final int SALE_RETURN_MATERAIL = 5010;//销售退料-扫描

}
