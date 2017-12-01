package com.timi.sz.wms_android.http.api;


import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.bean.VersionBean;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.OrderDetailData;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdInstockByInputResult;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.StockinMaterialBean;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByMaterialCodeData;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
import com.timi.sz.wms_android.bean.outstock.detail.BillMaterialDetailResult;
import com.timi.sz.wms_android.bean.outstock.detail.MaterialDetailResult;
import com.timi.sz.wms_android.bean.outstock.other.QueryOtherOutStockByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.bean.outstock.outsource.GetOutSourcePickDetailResult;
import com.timi.sz.wms_android.bean.outstock.outsource.GetWWDetailPickDataResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.outsource.RequestGetMaterialLotBean;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.GetPrdFeedDetailResult;
import com.timi.sz.wms_android.bean.outstock.product.GetProductPickDetailResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.outstock.sale.QueryDNByInputForOutStockResult;
import com.timi.sz.wms_android.bean.outstock.sale.QuerySalesOutSotckByInputForOutStockResult;
import com.timi.sz.wms_android.bean.quality.BarcodeData;
import com.timi.sz.wms_android.bean.quality.GetAQLList;
import com.timi.sz.wms_android.bean.quality.QualityListBean;
import com.timi.sz.wms_android.bean.quality.adavance.CommitAdvance1Data;
import com.timi.sz.wms_android.bean.quality.adavance.CommitAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvanceData;
import com.timi.sz.wms_android.bean.quality.mrp.MrpReviewData;
import com.timi.sz.wms_android.bean.quality.normal.CommitNormalData;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.bean.quality.update_barcode.BarEditGetUnInstockBarcodeData;
import com.timi.sz.wms_android.bean.stockin_work.LibraryAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanLocationResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanMaterialResult;
import com.timi.sz.wms_android.bean.stockin_work.lib_adjust.LibAdjustDetail;
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
import retrofit2.http.Body;
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

/**
 * 关于Api Servaice  注解的解释：
 * 1、@Field 单个表单数据提交
 * 2、@FieldMap 用map的形式提交一系列表单数据
 * 3、@Body     用于提交实体转换成的json 对象的提交（为了处理类似链表形式的提交,
 * 链表形式的提交用@FieldMap是实现不了的"），
 */
public interface ApiService {
    /**
     * 登录
     *
     * @param tenancyName
     * @param usernameOrEmailAddress
     * @param password
     * @param deviceType
     * @param mac
     * @return
     */
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
     * 提交制单和审核
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/common/SubmitMakeOrAuditBill")
    Observable<CommonResult<Object>> submitMakeOrAuditBill(@FieldMap Map<String, Object> params);

    /**
     * 搜索收货单的返回结果（来料入库）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PurInstock/QueryReceiptDataByInput")
    Observable<CommonResult<ReceiveOrdernoBean>> searchReceiveGoodOrderno(@FieldMap Map<String, Object> params);

    /**
     * 搜索产成品-审核的返回结果
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdInstock/QueryPrdInstockByInput")
    Observable<CommonResult<QueryPrdInstockByInputResult>> searchFinishGoodsOrderno(@FieldMap Map<String, Object> params);

    /**
     * 搜索产成品-审核的返回结果
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdInstock/GetPrdInstockDetail")
    Observable<CommonResult<List<OrderDetailData>>> getPrdInstockDetail(@FieldMap Map<String, Object> params);

    /**
     * 搜索产成品-生单的返回结果(生产工单单末尾号查询)
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdInstock/QueryWorkOrderByInput")
    Observable<CommonResult<FinishGoodsCreateBillBean>> searchFinishGoodsCreateBillOrderno(@FieldMap Map<String, Object> params);

    /**
     * 搜索产成品-生单的明细的返回结果(生产工单单末尾号查询)
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdInstock/GetWorkOrderDetail")
    Observable<CommonResult<List<OrderDetailData>>> getWorkOrderDetail(@FieldMap Map<String, Object> params);

    /**
     * 其他入库—审核
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/OtherInstock/QueryOtherInstockByInput")
    Observable<CommonResult<OtherAuditSelectOrdernoBean>> searchOtherAuditSelectOrderno(@FieldMap Map<String, Object> params);
    /**====== 成品拣货 ======**/
    /**
     * 发货通知单末尾号查询
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/SalesOutStock/QueryDNByInputForPick")
    Observable<CommonResult<QueryDNByInputForPickResult>> queryDNByInputForPick(@FieldMap Map<String, Object> orderno);

    /**
     * 获取发货单明细数据
     *
     * @param orderno
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/SalesOutStock/GetDNDetail")
    Observable<CommonResult<List<OrderDetailData>>> getDNDetail(@Field("orderno") String orderno);
    /**====== 委外退料 ======**/
    /**
     * 委外退料—选单
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/WWReturn/QueryOutSourceReturnByInput")
    Observable<CommonResult<QueryOutSourceReturnByInputResult>> queryOutSourceReturnByInput(@FieldMap Map<String, Object> params);

    /**
     * 委外退料—获取明细数据
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/WWReturn/GetOutSourceReturnDetail")
    Observable<CommonResult<List<OrderDetailData>>> getOutSourceReturnDetail(@FieldMap Map<String, Object> params);


    /**====== 生产退料 ======**/
    /**
     * 生产退料—选单
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdReturn/QueryPrdReturnByInput")
    Observable<CommonResult<QueryPrdReturnByInputResult>> queryPrdReturnByInput(@FieldMap Map<String, Object> params);

    /**
     * 生产退料—获取明细数据
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdReturn/GetPrdReturnDetail")
    Observable<CommonResult<List<OrderDetailData>>> getPrdReturnDetail(@FieldMap Map<String, Object> params);
    /**====== 销售退料 ======**/
    /**
     * 销售退货
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/SalesReturn/QuerySalesReturnByInput")
    Observable<CommonResult<SaleGoodsReturnBean>> querySalesReturnByInput(@FieldMap Map<String, Object> params);

    /**
     * 销售退料（审核）—获取明细数据
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/SalesReturn/GetSalesReturnDetail")
    Observable<CommonResult<List<OrderDetailData>>> getSalesReturnDetail(@FieldMap Map<String, Object> params);


    /**
     * 提交条码入库
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/common/SubmitBarcodeInstock")
    Observable<CommonResult<MaterialScanPutAwayBean>> materialScanPutAawy(@FieldMap Map<String, Object> params);

    /**
     * 验证库位码是否有效
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/common/VerifyBinCode")
    Observable<CommonResult<VertifyLocationCodeBean>> vertifyLocationCode(@FieldMap Map<String, Object> params);

    /**
     * 提交制单和审核生成入库单
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/common/SubmitMakeOrAuditBill")
    Observable<CommonResult<Object>> createInStockOrderno(@FieldMap Map<String, Object> params);

    /**
     * 获取入库明细
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PurInstock/GetReceiptDetail")
    Observable<CommonResult<List<OrderDetailData>>> getReceiptDetail(@FieldMap Map<String, Object> params);

    /**************************************************************************************************************/
    /**************************************************************************************************************/
    /********************出库 请求************************************************************/
    /**====== 委外发料-审核 ======**/
    /**
     * 根据委外发料单末尾号查询未审核的委外发料单
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/WWPick/QueryOutSourcePickByInput")
    Observable<CommonResult<QueryOutSourcePickByInputResult>> queryOutSourcePickByInput(@FieldMap Map<String, Object> params);

    /**
     * 获取委外发料单明细的发料数据（审核流程）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/WWPick/GetOutSourcePickDetail")
    Observable<CommonResult<List<MaterialDetailResult>>> getOutSourcePickDetail(@FieldMap Map<String, Object> params);

    /**====== 委外发料-制单 ======**/

    /**
     * 获取委外发料单明细的发料数据（制单流程）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/WWPick/QueryWWPickDataByOutSource")
    Observable<CommonResult<QueryWWPickDataByOutSourceResult>> queryWWPickDataByOutSource(@FieldMap Map<String, Object> params);

    /**
     * 获取委外订单明细的发料数据（制单流程）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/WWPick/GetWWDetailPickData")
    Observable<CommonResult<BillMaterialDetailResult>> getWWDetailPickData(@FieldMap Map<String, Object> params);


    /**====== 委外补料 ======**/

    /**
     * 委外补料单末尾号查询（审核流程）
     * 根据委外补料单末尾号查询未审核的委外补料单
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/WWFeed/QueryOutSourceFeedByInput")
    Observable<CommonResult<QueryOutSourceFeedByInputResult>> queryOutSourceFeedByInput(@FieldMap Map<String, Object> params);

    /**
     * 获得委外补料单的发料明细。
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/api/services/wpda/WWFeed/GetOutSourceFeedDetail")
    Observable<CommonResult<List<MaterialDetailResult>>> getOutSourceFeedDetail(@FieldMap Map<String, Object> params);
    /**
     * 提交条码出库(批次拣货)。
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/common/SubmitBarcodeLotPickOut")
    Observable<CommonResult<SubmitBarcodeLotPickOutResult>> submitBarcodeLotPickOut(@FieldMap Map<String, Object> params);

    /**
     * 提交条码拆分出库(批次拣货)
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/common/SubmitBarcodeLotPickOutSplit")
    Observable<CommonResult<SubmitBarcodeLotPickOutSplitResult>> submitBarcodeLotPickOutSplit(@FieldMap Map<String, Object> params);

    /**
     * 获得物料批次拣货信息
     *
     * @param bean
     * @return
     */
    @POST("api/services/wpda/common/GetMaterialLotData")
    Observable<CommonResult<GetMaterialLotData>> getMaterialLotData(@Body RequestGetMaterialLotBean bean);

    /**
     * 指定批次异常
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/common/SetMaterialLotData")
    Observable<CommonResult<GetMaterialLotData>> setMaterialLotData(@FieldMap Map<String, Object> params);


    /*****************************************采购退料******************************************************************/
    /**
     * 通过物料码获取未关闭的采购单
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PurReturn/QueryAuditPOBillForPurReturn ")
    Observable<CommonResult<BuyReturnMaterialByMaterialCodeData>> materialScanGetBuyRetrurnOrderNo(@FieldMap Map<String, Object> params);

    /**
     * 提交条码出库(普通)
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/common/SubmitBarcodeOutAudit")
    Observable<CommonResult<SubmitBarcodeOutAuditData>> submitBarcodeOutAudit(@FieldMap Map<String, Object> params);

    /**
     * 提交退料条码（制单）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PurReturn/SubmitBarcodePurReturn")
    Observable<CommonResult<SubmitBarcodePurReturnData>> submitBarcodePurReturn(@FieldMap Map<String, Object> params);


    /**
     * 通过退料单的末尾号查询（审核流程）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PurReturn/QueryPurReturnDataByInput")
    Observable<CommonResult<BuyReturnMaterialByOrdernoData>> returnMaterialOrderNoScan(@FieldMap Map<String, Object> params);

    /**
     * 获取采购退料单明细（审核流程）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PurReturn/GetPurReturnDetail")
    Observable<CommonResult<SubmitBarcodePurReturnData>> getPurReturnDetail(@FieldMap Map<String, Object> params);

    /**
     * 提交条码拆分出库（普通）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/common/SubmitBarcodeOutSplitAudit")
    Observable<CommonResult<SubmitBarcodeOutSplitAuditData>> submitBarcodeOutSplitAudit(@FieldMap Map<String, Object> params);


    /**====== 生产领料-制单 ======**/
    /**
     * 生产订单末尾号查询（制单流程）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdPick/QueryPrdPickDataByMO")
    Observable<CommonResult<QueryWWPickDataByOutSourceResult>> queryPrdPickDataByMO(@FieldMap Map<String, Object> params);

    /**
     * 获取生产订单明细的发料数据（制单流程）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdPick/GetPrdDetailPickData")
    Observable<CommonResult<BillMaterialDetailResult>> getPrdDetailPickData(@FieldMap Map<String, Object> params);
    /**====== 生产领料-审核 ======**/
    /**
     * 生产订单末尾号查询（审核流程）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdPick/QueryProductPickByInput")
    Observable<CommonResult<QueryProductPickByInputResult>> queryProductPickByInput(@FieldMap Map<String, Object> params);

    /**
     * 获取生产领料单明细的发料数据（审核流程）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdPick/GetProductPickDetail")
    Observable<CommonResult<List<MaterialDetailResult>>> getProductPickDetail(@FieldMap Map<String, Object> params);


    /**====== 领料申请 ======**/
    /**
     * 领料申请单末尾号查询（审核流程）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdPick/QueryPrdPickApplyByInput")
    Observable<CommonResult<QueryProductPickByInputResult>> queryPrdPickApplyByInput(@FieldMap Map<String, Object> params);

    /**
     * 获取领料申请单明细的发料数据（领料申请）
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdPick/GetPrdPickApplyDetail")
    Observable<CommonResult<List<MaterialDetailResult>>> getPrdPickApplyDetail(@FieldMap Map<String, Object> params);


    /**======生产补料 ======**/

    /**
     * 生产补料单末尾号查询
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdFeed/QueryPrdFeedByInput")
    Observable<CommonResult<QueryPrdFeedByInputResult>> queryPrdFeedByInput(@FieldMap Map<String, Object> params);

    /**
     * 生产补料单末尾号查询
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/PrdFeed/GetPrdFeedDetail")
    Observable<CommonResult<List<MaterialDetailResult>>> getPrdFeedDetail(@FieldMap Map<String, Object> params);

    /**====== 销售出库 ======**/
    /**
     * 销售出库 审核
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/SalesOutStock/QueryDNByInputForOutStock")
    Observable<CommonResult<QueryDNByInputForOutStockResult>> queryDNByInputForOutStock(@FieldMap Map<String, Object> params);

    /**
     * 销售出库 审核  详情
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/SalesOutStock/GetDNDetail")
    Observable<CommonResult<List<MaterialDetailResult>>> getDNDetail(@FieldMap Map<String, Object> params);


    /**====== 销售出库 ======**/
    /**
     * 销售出库 生单
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/SalesOutStock/QuerySalesOutSotckByInputForOutStock")
    Observable<CommonResult<QuerySalesOutSotckByInputForOutStockResult>> querySalesOutSotckByInputForOutStock(@FieldMap Map<String, Object> params);

    /**
     * 销售出库 生单  详情
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/SalesOutStock/GetSalesOutStockDetailForOutStock")
    Observable<CommonResult<List<MaterialDetailResult>>> getSalesOutStockDetailForOutStock(@FieldMap Map<String, Object> params);

    /**====== 其他出库 ======**/
    /**
     * 其他出库 生单
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/OtherOutStock/QueryOtherOutStockByInput")
    Observable<CommonResult<QueryOtherOutStockByInputResult>> queryOtherOutStockByInput(@FieldMap Map<String, Object> params);

    /**
     * 其他出库 生单  详情
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/OtherOutStock/GetOtherOutStockDetail")
    Observable<CommonResult<List<MaterialDetailResult>>> getOtherOutStockDetail(@FieldMap Map<String, Object> params);

    /**************************************************************************************************************/
    /**************************************************************************************************************/
    /**====== 质量检验 ======**/
    /**************************************************************************************************************/
    /**====== 质检列表======**/
    /**
     * 获取质量检验的列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCGetDefaultReceiptForIQC")
    Observable<CommonResult<List<QualityListBean>>> getQualityList(@FieldMap Map<String, Object> params);

    /**
     * 查询来料质检列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCQueryReceiptForIQC")
    Observable<CommonResult<List<QualityListBean>>> queryReceiptForIQC(@FieldMap Map<String, Object> params);

    /**
     * 获取AQL的列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCGetAQLList")
    Observable<CommonResult<GetAQLList>> getAQLList(@FieldMap Map<String, Object> params);

    /**
     * 设置AQL的参数
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCSetAQLValue ")
    Observable<CommonResult<Object>> setAQLValue(@FieldMap Map<String, Object> params);

    /**
     * 免检
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCSubmitExemption")
    Observable<CommonResult<Object>> submitExemption(@FieldMap Map<String, Object> params);
    /**====== 普通质检  高级质检======**/
    /**
     * 获取普通质检
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCGetNormalData")
    Observable<CommonResult<NormalQualityData>> getNormalQualityData(@FieldMap Map<String, Object> params);

    /**
     * 提交普通质检
     *
     * @return
     */
    @POST("api/services/wpda/IQC/IQCSetNormalData")
    Observable<CommonResult<Object>> setNormalQualityData(@Body CommitNormalData params);

    /**
     * 获得质检条码信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCGetBarcodeData")
    Observable<CommonResult<BarcodeData>> getBarcodeData(@FieldMap Map<String, Object> params);

    /**
     * 质检拒收
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCSetBarcodeReject")
    Observable<CommonResult<BarcodeData>> setBarcodeReject(@FieldMap Map<String, Object> params);

    /**
     * 质检确认
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCSubmitFinish")
    Observable<CommonResult<Object>> submitFinish(@FieldMap Map<String, Object> params);

    /**
     * 获取高级质检1 的数据
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCGetAdvanceData")
    Observable<CommonResult<GetAdvanceData>> getAdvanceData(@FieldMap Map<String, Object> params);

    /**
     * 获取高级质检2的数据
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCGetAdvanceData")
    Observable<CommonResult<GetAdvance2Data>> getAdvance2Data(@FieldMap Map<String, Object> params);

    /**
     * 提交高级质检 1的数据
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCSetAdvance1Data")
    Observable<CommonResult<Object>> setAdvance1Data(@Body CommitAdvance1Data params);

    /**
     * 提交高级质检2 的数据
     *
     * @return
     */
    @POST("api/services/wpda/IQC/IQCSetAdvance2Data")
    Observable<CommonResult<Object>> setAdvance2Data(@Body CommitAdvanceData data);
    /**======MRP评审=====**/
    /**
     * 查询MRP评审信息列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCGetMRPReviewData")
    Observable<CommonResult<List<MrpReviewData>>> getMRPReviewData(@FieldMap Map<String, Object> params);

    /**
     * 提交评审结果
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/IQC/IQCSetMRPReviewData")
    Observable<CommonResult<Object>> setMRPReviewData(@FieldMap Map<String, Object> params);

    /**
     * 查询未入库的条码信息
     */
    @FormUrlEncoded
    @POST("api/services/wpda/BarcodeEdit/BarEditGetUnInstockBarcodeData")
    Observable<CommonResult<BarEditGetUnInstockBarcodeData>> barEditGetUnInstockBarcodeData(@FieldMap Map<String, Object> params);

    /**
     * 修改未入库的条码信息
     */
    @FormUrlEncoded
    @POST("api/services/wpda/BarcodeEdit/BarEditSetBarcodeQty")
    Observable<CommonResult<Object>> barEditSetBarcodeQty(@FieldMap Map<String, Object> params);
    /**************************************************************************************************************/
    /**************************************************************************************************************/
    /**======库内操作======**/

    /**======库内--库位移动======**/
    /**
     * 扫描物料码 的返回
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<ScanMaterialResult>> scanMaterialCode(@FieldMap Map<String, Object> params);

    /**
     * 提交库内调整
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<LibraryAdjustResult>> libraryAdjustResult(@FieldMap Map<String, Object> params);
    /**
     * 库内调整详情的请求
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<List<LibAdjustDetail>>> libraryAdjustDetail(@FieldMap Map<String, Object> params);
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
