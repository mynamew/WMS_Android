package com.timi.sz.wms_android.http.api;


import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.bean.VersionBean;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.CreateInStockOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsOrdernoBean;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OutReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ProductionReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.instock.search.StockinMaterialBean;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.CommitMaterialScanToOredernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.MaterialBean;
import com.timi.sz.wms_android.bean.outstock.buy.OrderNoAddMaterial;
import com.timi.sz.wms_android.bean.outstock.buy.OrderNoBean;
import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.bean.quality.QualityListBean;
import com.timi.sz.wms_android.bean.stockin_work.LibraryAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanLocationResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanMaterialResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotOneSetpResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotScanResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeInResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.PointResult;
import com.timi.sz.wms_android.bean.stockin_work.query.StockQueryResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * retrofit 的网络请求api
 * author: timi
 * create at: 2017-08-15 09:58
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<LoginBean>> login(@Field("tenancyName") String tenancyName, @Field("usernameOrEmailAddress") String usernameOrEmailAddress, @Field("password") String password, @Field("deviceType") int deviceType, @Field("mac") String mac);

    /**
     * 获取版本
     */
    @FormUrlEncoded
    @POST("api/authority/GetAppVersion")
    Observable<CommonResult<VersionBean>> getAppVersion(@Field("tenancyName") String tenancyName, @Field("usernameOrEmailAddress") String usernameOrEmailAddress, @Field("password") String password, @Field("deviceType") int deviceType, @Field("mac") String mac);

    //下载更新
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

    /**
     * 获取用户信息
     */
    @FormUrlEncoded
    @POST("api/authority/GetUserInfo")
    Observable<CommonResult<UserInfoBean>> getUserInfo(@Field("userId") int userid, @Field("deviceType") int deviceType, @Field("mac") String mac);

    /********************入库 请求************************************************************/

    /**
     * 采购单查询
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/QueryPODataByInput")
    Observable<CommonResult<BuyOrdernoBean>> buyOrderNoQuery(@FieldMap Map<String, Object> params);

    /**
     * 采购单请点记录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/POGetReceiveRecord")
    Observable<CommonResult<List<StockinMaterialBean>>> buyOrderNoPointRecord(@FieldMap Map<String, Object> params);

    /**
     * 送货单查询
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/QueryASNDataByBarcode")
    Observable<CommonResult<SendOrdernoBean>> sendOrdernoQuery(@FieldMap Map<String, Object> params);

    /**
     * 送货单请点记录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/ASNGetReceiveRecord")
    Observable<CommonResult<List<StockinMaterialBean>>> sendOrderNoPointRecord(@FieldMap Map<String, Object> params);

    /**
     * 采购单物品清点修改
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/POUpdateReceiveRecord")
    Observable<CommonResult<Object>> updateMaterialPoint(@FieldMap Map<String, Object> params);

    /**
     * 采购单物品清点删除
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/PODeleteReceiveRecord")
    Observable<CommonResult<Object>> deleteMaterialPoint(@FieldMap Map<String, Object> params);

    /**
     * 采购单物品清点修改
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/ASNUpdateReceiveRecord")
    Observable<CommonResult<Object>> updateSendMaterialPoint(@FieldMap Map<String, Object> params);

    /**
     * 采购单物品清点删除
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/ASNDeleteReceiveRecord")
    Observable<CommonResult<Object>> deleteSendMaterialPoint(@FieldMap Map<String, Object> params);

    /**
     * 采购单物品清点保存
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/POSaveReceive")
    Observable<CommonResult<Integer>> saveMaterialPoint(@FieldMap Map<String, Object> params);

    /**
     * 送货单物品清点保存
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/ASNSaveReceive")
    Observable<CommonResult<Integer>> saveSendMaterialPoint(@FieldMap Map<String, Object> params);

    /**
     * 获取采购单物料清点的表体
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/GetPODetailsByCode")
    Observable<CommonResult<BuyOrdernoBean>> getPODetailsByCode(@FieldMap Map<String, Object> params);

    /**
     * 获取送货单物料清点的表体
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/GetASNDetailsByCode")
    Observable<CommonResult<SendOrdernoBean>> getASNDetailsByCode(@FieldMap Map<String, Object> params);

    /**
     * 物品清点提交
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/common/SubmitMakeOrAuditBill")
    Observable<CommonResult<Object>> commitMaterialPoint(@FieldMap Map<String, Object> params);


    /**
     * 物料扫码
     *
     * @param scamStr
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<MaterialBean>> materialScan(@Field("scamStr") String scamStr);

    /**
     * 搜索收货单的返回结果
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<ReceiveOrdernoBean>> searchReceiveGoodOrderno(@Field("orderno") String orderno);

    /**
     * 搜索产成品-审核的返回结果
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<FinishGoodsOrdernoBean>> searchFinishGoodsOrderno(@Field("orderno") String orderno);

    /**
     * 搜索产成品-生单的返回结果
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<FinishGoodsCreateBillBean>> searchFinishGoodsCreateBillOrderno(@Field("orderno") String orderno);

    /**
     * 其他入库—选单
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OtherAuditSelectOrdernoBean>> searchOtherAuditSelectOrderno(@Field("orderno") String orderno);

    /**
     * 委外退料—选单
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OutReturnMaterialBean>> searchOutReturnMaterialOrderno(@Field("orderno") String orderno);

    /**
     * 生产退料—选单
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<ProductionReturnMaterialBean>> searchProductionReturnMaterialOrderno(@Field("orderno") String orderno);

    /**
     * 销售退货—选单
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<SaleGoodsReturnBean>> searchSaleGoodsReturnOrderno(@Field("orderno") String orderno);


    /**
     * 物料扫码并入库上架
     *
     * @param locationCode
     * @param materialCode
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<MaterialScanPutAwayBean>> materialScanPutAawy(@Field("locationCode") String locationCode, @Field("materialCode") String materialCode, @Field("userId") int userId);

    /**
     * 验证库位码是否有效
     *
     * @param locationCode
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<VertifyLocationCodeBean>> vertifyLocationCode(@Field("locationCode") String locationCode);

    /**
     * 生成入库单
     *
     * @param locationCode
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<CreateInStockOrdernoBean>> createInStockOrderno(@Field("locationCode") String locationCode);

    /**************************************************************************************************************/
    /**************************************************************************************************************/
    /********************出库 请求************************************************************/

    /**
     * 搜索相关的请求
     */
    /**
     * 委外退料单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OutSourceFeedBean>> searchOutsourceFeed(@FieldMap Map<String, Object> params);

    /**
     * 委外发货-审核单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> searchOutsourceAudit(@Field("orderno") String orderno);

    /**
     * 委外发货-生单扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> searchOutsourceBill(@Field("orderno") String orderno);

    /**
     * 生产退料单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> searchProductionFeed(@Field("orderno") String orderno);

    /**
     * 生产领料-审核单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> searchProductionAudit(@Field("orderno") String orderno);

    /**
     * 生产领料-生单 单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> searchProductionBill(@Field("orderno") String orderno);

    /**
     * 调拨单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> searchPick(@Field("ordernoPick") String ordernoPick, @Field("ordernoSend") String ordernoSend, @Field("ordernoSale") String ordernoSale);

    /**
     * 采购退料-审核 单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> searchSaleAudit(@Field("orderno") String orderno);

    /**
     * 采购退料-生单 单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> searchSaleBill(@Field("orderno") String orderno);

    /**
     * 其他出库-审核 单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> searchOtherAudit(@Field("orderno") String orderno);

    /**
     * 其他出库-生单 单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> searchOtherBill(@Field("orderno") String orderno);

    /**
     * 采购退料单号扫码 请求
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoBean>> returnMaterialOrderNoScan(@Field("orderno") String orderno);

    /**
     * 采购退料单 添加物料的方法
     *
     * @param materialCode
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OrderNoAddMaterial>> returnMaterialOrderNoAddMaterial(@Field("materialCode") String materialCode);

    /**
     * 通过物料码获取未关闭的采购单 即退料单
     *
     * @param materialCode
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<BuyReturnMaterialOrdernoBean>> materialScanGetBuyRetrurnOrderNo(@Field("materialCode") String materialCode);

    /**
     * 提交物料扫码 结果到采购退料单
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<CommitMaterialScanToOredernoBean>> commitMaterialScanToOrederno(@Field("orderno") String orderno);


    /**====== 质量检验 ======**/
    /**
     * 获取质量检验的列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<QualityListBean>> getQualityList(@FieldMap Map<String, Object> params);
    /**************************************************************************************************************/
    /**************************************************************************************************************/
    /**======库内操作======**/

    /**======库内--库位移动======**/
    /**
     * 扫描库位码的返回
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<ScanLocationResult>> scanLibLocationCode(@FieldMap Map<String, Object> params);

    /**
     * 扫描库位码的返回
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<ScanMaterialResult>> scanMaterialCode(@FieldMap Map<String, Object> params);
    /**
     * 扫描库位码的返回
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<LibraryAdjustResult>> libraryAdjustResult(@FieldMap Map<String, Object> params);
    /**************************************************************************************************************/
    /**************************************************************************************************************/
    /**======库内--搜索======**/
    /**
     * 扫描调入查询
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<AllotScanResult>> queryAllotScan(@FieldMap Map<String, Object> params);
    /**
     * 一步调入查询
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<AllotOneSetpResult>> queryAllotOneStep(@FieldMap Map<String, Object> params);
    /**
     * 形态转换-出库 查询
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<FormChangeOutResult>> queryFormChangeOut(@FieldMap Map<String, Object> params);
    /**
     * 形态转换-出库 查询
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<FormChangeInResult>> queryFormChangeIn(@FieldMap Map<String, Object> params);
    /**
     * 库存查询 查询
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<StockQueryResult>> queryStockQuery(@FieldMap Map<String, Object> params);
    /**
     * 盘点查询
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<PointResult>> queryPoint(@FieldMap Map<String, Object> params);
    /************************************************************************************************************/
    /**************************************************************************************************************/

}
