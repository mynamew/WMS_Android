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
//    public static final String BASE_URL = "http://192.168.0.39:8066/";
    //    public static final String BASE_URL = "http://192.168.18.117:8066/";
    //超时时间   2分钟
    public static int DEFAULT_TIMEOUT = 2;

    /***********
     * PDA  配置
     *********************************************************************************************/
    public static final String IS_GIVE_GOOD = "is_give_goods";//是否有备品
    public static final String IS_MATERAIL_ATTR = "is_material_attr";//是否有附加属性
    public static final String IS_BILL_LIST = "is_bill_list";//是否无纸化作业（即无条码，直接通过单据列表找到单号等数据）
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
    //组织id
    public static final String ORGANAZATION_ID = "ORGANAZATION_ID";
    //用户权限组织
    public static final String USER_ROOT = "USER_ROOT";
    //登录的id
    public static final String CUSER_ID = "USER_ID";
    //租户信息
    public static final String TENANCY_NAME = "TENANCY_NAME";
    //用户所有的信息
    public static final String USER_INFO = "USER_INFO";
    //是否是 debug的状态
    public static final boolean IS_DEBUG = isApkDebugable(BaseApplication.getMApplicationContext());
    /***********
     * 扫码相关
     *********************************************************************************************/
    public static final int REQUEST_CODE = 1001;//主页跳转到扫码
    public static final int REQUEST_SCAN_CODE_MATERIIAL = 1002;//物料条码 扫码
    public static final int REQUEST_SCAN_CODE_LIB_LOATION = 1003;//库位码  扫码
    public static final int REQUEST_SCAN_CODE_RETURN_MATERIAL = 1004;//退料单号  扫码
    public static final int REQUEST_SCAN_CODE_BARCODE = 1005;//条码
    public static final int REQUEST_SCAN_CODE_CONTAINER = 1006;//容器
    public static final int REQUEST_SCAN_CODE_ORDERNO = 1007;//单号
    /***********
     * 蓝牙相关
     *********************************************************************************************/

    public static final int REQUEST_CODE_BLUETOOTH_ON = 1008;//打开蓝牙
    public static final int REQUEST_CODE_OPEN_GPS = 1009;//打开GPS
    public static final int REQUEST_CODE_PERMISSION_LOCATION = 1010;//定位
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
     * SharePerference 软件更新相关
     *********************************************************************************************/
    public static final String APK_NAME = "wms.apk";      //apk 名称
    public static final String IS_HAVE_DOWNLOAD_NEW = "is_have_download_new";//是否下载了最新版本的安装包

    /**
     * 权限配置  当前入口I是否存在
     */
    public static  final String PERMISSION_STOCKIN="Pages.WPDA.InStock";//入库作业
    public static  final String PERMISSION_STOCKIN_BUY="Pages.WPDA.InStock.ReceiveByOrder";//采购到货
    public static  final String PERMISSION_STOCKIN_SEND="Pages.WPDA.InStock.ReceiveByDelivery";//送货单收货
    public static  final String PERMISSION_STOCKIN_PUTAWAY="Pages.WPDA.PurInstock";//来料入库
    public static  final String PERMISSION_STOCKIN_OTHER_PUTAWAY="Pages.WPDA.OtherInstock";//其他入库

    public static  final String PERMISSION_QUALITY="Pages.WPDA.IQC";//来料质检
    public static  final String PERMISSION_BarcodeEdit="Pages.WPDA.BarcodeEdit";//无质检条码修改

    public static  final String PERMISSION_STOCKOUT="Pages.WPDA.OutStock";//出库作业
    public static  final String PERMISSION_STOCKOUT_PURRETURN="Pages.WPDA.PurReturn";//采购退料
    public static  final String PERMISSION_STOCKOUT_OUT_FEED="Pages.WPDA.WWFeed";    //委外补料
    public static  final String PERMISSION_STOCKOUT_OUT_SEND="Pages.WPDA.WWPick";    //委外发料
    public static  final String PERMISSION_STOCKOUT_OUT_ALLOT="Pages.WPDA.WPDA_WWTransfer";//委外调拨
    public static  final String PERMISSION_STOCKOUT_PRODUCTION_PICK="Pages.WPDA.PrdPick";//生产领料
    public static  final String PERMISSION_STOCKOUT_PRODUCTION_ALLOT="Pages.WPDA.PrdTransfer";//生产调拨
    public static  final String PERMISSION_STOCKOUT_PRODUCTION_FEED="Pages.WPDA.PrdFeed";//生产补料
    public static  final String PERMISSION_STOCKOUT_SALE="Pages.WPDA.SalesOutStock";//销售出库
    public static  final String PERMISSION_STOCKOUT_OTHER="Pages.WPDA.OtherOutStock";//其他出库

    public static  final String PERMISSION_STOCKIN_WORK_ALLOT="Pages.WPDA.Transfer";//库内调拨
    public static  final String PERMISSION_STOCKIN_WORK_STOCK_QUERY="Pages.WPDA.QueryStock";//库存查询
    public static  final String PERMISSION_STOCKIN_WORK_FORM_CHANGE="Pages.WPDA.MatConvert";//形态转换
    public static  final String PERMISSION_STOCKIN_WORK_LIB_ADJUST="Pages.WPDA.StockAdjust";//库位调整

    /***********
     * 跳转 传递的相关字段
     *********************************************************************************************/
    /***********
     * 跳转到登录界面 是否显示服务配置的弹框（ 可能是来自于服务配置按钮的点击）
     *********************************************************************************************/
    public static final String IS_NEED_SHOW_SHOW_SERVER_SET = "isNeedShowServerSet.apk";

    /***********
     * 入库跳转到单据详情的BillId
     *********************************************************************************************/
   public static  final String STOCKIN_BILLID="stockin_billid";
    /***********
     * 入库跳转到查询界面的不同code码
     *********************************************************************************************/
    public static final String CODE_STR = "stockin_code";//入库的code码的key
    public static final String ORDER_NO = "order_no";//单号
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
    public static final int OUT_SOURCE = 5011;//委外订单
    /***********
     * 入库跳转到查询界面的不同实体的key
     *********************************************************************************************/
    //单号(传递到详情界面)
    public static final String IN_STOCK_ORDERNO = "inStockOrderno";
    //采购单
    public static final String IN_STOCK_BUY_BEAN = "in_stock_buy_orderno";
    //送货单
    public static final String IN_STOCK_SEND_BEAN = "in_stock_send_orderno";
    //收货单
    public static final String IN_STOCK_RECEIVE_BEAN = "in_stock_receive_orderno";
    //产成品 审核
    public static final String IN_STOCK_FINISH_BEAN = "in_stock_finish_orderno";
    //产成品 生单
    public static final String IN_STOCK_FINISH_CREATE_BEAN = "in_stock_craate_orderno";
    //其他 选单
    public static final String IN_STOCK_FINISH_OTHER_BEAN = "in_stock_other_orderno";
    //委外退料
    public static final String IN_STOCK_FINISH_OUT_BEAN = "in_stock_out_orderno";
    //生产退料
    public static final String IN_STOCK_FINISH_PRODUCTION_BEAN = "in_stock_production_orderno";
    //销售退料
    public static final String IN_STOCK_FINISH_SALE_BEAN = "in_stock_sale_orderno";


    /***********
     * 出库跳转的key
     *********************************************************************************************/

    /**
     * 采购退料 退料单的key
     */
    public static final String OUT_STOCK_BUY_RETURN_ORDERNO_BEAN = "out_stock_buy_return_orderno_bean";

    /**
     * 搜索 跳转的intent code
     */
    public static final String STOCK_OUT_CODE_STR = "stockout_code";//出库的code码的key

    public static final int STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT = 5020;//委外补料
    public static final int STOCK_OUT_OUTSOURCE_AUDIT = 5021;//委外发料-委外发料单
    public static final int STOCK_OUT_OUTSOURCE_BILL = 5022;//委外发料-委外订单
    public static final int STOCK_OUT_OUTSOURCE_ALLOT = 5023;//委外调拨


    public static final int STOCK_OUT_PRODUCTION_FEEDING = 5024;//生产补料
    public static final int STOCK_OUT_PRODUCTION_AUDIT = 5025;//生产领料-审核
    public static final int STOCK_OUT_PRODUCTION_BILL = 5026;//生产领料-生单
    public static final int STOCK_OUT_PRODUCTION_ALLOT = 5027;//生产调拨

    public static final int STOCK_OUT_PICK = 5028;//调拨
    public static final int STOCK_OUT_SELL_OUT_AUDIT = 5029;//销售出库-审核
    public static final int STOCK_OUT_SELL_OUT_BILL = 5030;//销售出库-生单

    public static final int STOCK_OUT_PURCHASE_MATERIAL_RETURN = 5031;//采购退料
    public static final int STOCK_OUT_OTHER_OUT_AUDIT = 5032;//其他出库-审核
    public static final int STOCK_OUT_OTHER_OUT_BILL = 5033;//其他出库-生单
    public static final int STOCK_OUT_PRODUCTION_APPLY_BILL = 5034;//生产领料申请单-生单
    public static final int STOCK_OUT_FINISH_GOODS_PICK = 5035;//产成品拣货


    public static final int STOCK_OUT_ALLOT_OUT_PICK = 5036;//调拨拣货
    public static final int STOCK_OUT_SEND_OUT_PICK = 5037;//发货拣货
    public static final int STOCK_OUT_SALE_OUT_PICK = 5038;//销售拣货

    public static final int STOCK_OUT_ALLOT_OUT= 5039;//调拨调出
    /**
     * 出库搜索 跳转的传递对象的key
     */
    //委外补料传递实体的key

    public static final String STOCK_OUT_BEAN = "stock_out_bean";//委外传递实体的key
    public static final String STOCK_OUT_DETAIL_BEAN = "stock_out_detail_bean";//出库传递不合并时的明细实体
    public static final String STOCK_OUT_OUTSOURCE_FEED_BEAN = "stock_out_outsource_feed_bean";//委外补料传递实体的key
    public static final String STOCK_OUT_OUTSOURCE_AUDIT_BEAN = "stock_out_outsource_audit_bean";//委外发料单-审核传递实体的key
    public static final String STOCK_OUT_OUTSOURCE_BILL_BEAN = "stock_out_outsource_bill_bean";//委外发料单-生单传递实体的key
    public static final String STOCK_OUT_PRODUCT_FEED_BEAN = "stock_out_production_feed_bean";//生产补料单传递实体的key
    public static final String STOCK_OUT_PRODUCT_AUDI_BEAN = "stock_out_production_audit_bean";//生产领料-审核传递实体的key
    public static final String STOCK_OUT_PRODUCT_BILL_BEAN = "stock_out_production_bill_bean";//生产领料-生单传递实体的key
    public static final String STOCK_OUT_PICK_BEAN = "stock_out_pick_bean";//调拨传递实体的key
    public static final String STOCK_OUT_SELL_AUDRI_BEAN = "stock_out_sell_audit_bean";//销售出库-审核传递实体的key
    public static final String STOCK_OUT_SELL_BILL_BEAN = "stock_out_sell_bill_bean";//销售出库-生单传递实体的key
    public static final String STOCK_OUT_OTHER_AUDIT_BEAN = "stock_out_other_audit_bean";//其他-审核传递实体的key
    public static final String STOCK_OUT_OTHER_BILL_BEAN = "stock_out_other_bill_bean";//其他-生单传递实体的key
    public static final String STOCK_OUT_POINT_DETAIL_BEAN = "stock_out_point_detail_bean";//清点详情传递实体的key
    /**
     * 出库-跳转到物料清点详情
     */
    public static final String OUT_STOCK_POINT_DETIAIL_BILLID = "out_stock_out_point_detial_id";//跳转到清点详情的billid
    public static final String OUT_STOCK_POINT_DETIAILId = "out_stock_out_point_detialid";//清点详情跳转到单据详情的id
    public static final String OUT_STOCK_POINT_WAREHOUSEID = "out_stock_out_point_warehouseid";//清点详情跳转到单据详情的仓库id
    public static final String OUT_STOCK_POINT_REGIONID = "out_stock_out_point_regionid";//清点详情跳转到单据详情的区域id

    //拆分打码 界面的传值
    public static final String OUT_STOCK_PRINT_SRCBILLTYPE = "out_stock_print_srcbilltype";//出库打印 的来源单据
    public static final String OUT_STOCK_PRINT_BILLID = "out_stock_print_billid";//出库打印 的billid
    public static final String OUT_STOCK_PRINT_SCANID = "out_stock_print_scanid";//出库打印 的scabid
    public static final String OUT_STOCK_PRINT_BARCODENO = "out_stock_print_barcode";//出库打印 的barcode
    public static final String OUT_STOCK_PRINT_DESBILLTYPE = "out_stock_print_desbilltype";//出库打印目的单据
    public static final String OUT_STOCK_PRINT_DATECODE = "out_stock_print_datecode";//出库打印的批次code
    public static final String OUT_STOCK_PRINT_LIB_CODE = "out_stock_print_datecode";//出库打印的库位码
    public static final String OUT_STOCK_PRINT_MATERIAL_ATTR = "out_stock_print_material_attr";//出库打印的物料附加属性
    public static final String OUT_STOCK_PRINT_MATERIALID = "out_stock_print_materialid";//出库打印的物料id
    public static final String OUT_STOCK_PRINT_MATERIAL_NAME = "out_stock_print_material_name";//出库打印的物料name
    public static final String OUT_STOCK_PRINT_MATERIAL_CODE = "out_stock_print_material_code";//出库打印的物料code
    public static final String OUT_STOCK_PRINT_MATERIAL_MODEL = "out_stock_print_material_model";//出库打印的物料model
    public static final String OUT_STOCK_PRINT_CURRENT_QTY = "out_stock_print_current_qty";//出库打印的当前的数量
    public static final String OUT_STOCK_PRINT_OUT_QTY = "out_stock_print_out_qty";//出库打印的超出的数量
    public static final String OUT_STOCK_PRINT_NORMAL = "out_stock_print_normal";//是否是普通出库的拆分条码
    public static final String OUT_STOCK_PRINT_BATCh_AND_NORMAL = "out_stock_print_batch_and_normal";//是否是批次和普通物料混合的普通出库的拆分条码
    public static final String OUT_STOCK_PRINT_BATCh_DETAILID = "out_stock_print_batch_detailid";//明细传值的id

    public static final String OUT_STOCK_SCANID = "out_stock_out_point_scan_id";//跳转到清点详情的scanid
    public static final String OUT_STOCK_MATERIAL_RESULTS_BEAN = "out_stock_material_results_bean";//跳转到物料请点详情的实体(MaterialResultsBean)
    public static final String OUT_STOCK_DETAIL_RESULTS_BEAN = "out_stock_details_results_bean";//跳转到物料请点详情的实体(DetailResultsBean)
    public static final String OUT_STOCK_SALE_IS_CARTON = "out_stock_sale_is_carton";//跳转到物料请点是否装箱
    public static final String OUT_STOCK_SALE_CARTON_NUM = "out_stock_sale_carton_num";//跳转到物料请点箱号
    /**
     * 出库跳转到 委外生单/调拨 生产生单/调拨 的明细的key  因为在这个界面来源可能来自于普通出库也可能来自于批次出库需要做判断
     */
    public static final String OUT_STOCK_TO_DETAIL_FORM_NORMAL= "out_stock_to_detial_form_nomal";//跳转到明细OutsourceBillDetailActivity界面
    /***********
     * 库内作业跳转的key
     *********************************************************************************************/
    /**
     * 搜索 跳转的intent code
     */
    public static final String STOCK_IN_WORK_CODE_STR = "stockin_work_code";//库内作业的code码的key
    public static final String STOCK_IN_WORK_BEAN = "stockin_work_bean";//库内传递实体的key
    public static final String STOCK_IN_WORK_BILLID = "stockin_work_billid";//库内传递 billid
    public static final String STOCK_IN_WORK_BILLCODE = "stockin_work_billcode";//库内传递 billcode

    public static final int STOCK_IN_WORK_LIBRARY_ADJUST = 5201;//库位调整
    public static final int STOCK_IN_WORK_ALLOT_SCAN = 5202;//扫描调入
    public static final int STOCK_IN_WORK_ALLOT_ONE_STEP = 5203;//一步调入
    public static final int STOCK_IN_WORK_PACK_ADJUST = 5204;//容器调整


    public static final int STOCK_IN_WORK_FORM_CHANGE_OUT = 5205;//形态转换-出库
    public static final int STOCK_IN_WORK_FORM_CHANGE_IN = 5206;//形态转换-入库

    public static final int STOCK_IN_WORK_STOCK_QUERY = 5207;//库存呢查询
    public static final int STOCK_IN_WORK_POINT = 5208;//盘点
    public static final int STOCK_IN_ALLOT_OUT = 5209;//调拨调出

    /***********
     * Edittext 的code码
     *********************************************************************************************/
    public static final int EDITTEXT_ORDERNO = 5301;//单号
    public static final int EDITTEXT_SUPPLIER = 5302;//供应商
    public static final int EDITTEXT_LIB = 5303;//库位码
    public static final int EDITTEXT_MATERIAL = 5304;//物料码
    public static final int EDITTEXT_CONTAINER = 5305;//容器码
    public static final int EDITTEXT_BARCODE = 5306;//最小包装码

}
