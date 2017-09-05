package com.timi.sz.wms_android.http.api;


import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.bean.VersionBean;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.CreateInStockOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsOrdernoBean;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.PointMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OutReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ProductionReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.CommitMaterialScanToOredernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.MaterialBean;
import com.timi.sz.wms_android.bean.outstock.buy.OrderNoAddMaterial;
import com.timi.sz.wms_android.bean.outstock.buy.OrderNoBean;
import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;

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
    Observable<CommonResult<BuyOrdernoBean>> buyOrderNoQuery(@Field("OrgId") int OrgId,@Field("UserId") int UserId,@Field("MAC") String mac,@Field("BillNo") String BillNo);

    /**
     * 采购单请点记录
     *
     * @param orderNo 单号
     * @param userId  id
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<BuyOrdernoBean>> buyOrderNoPointRecord(@Field("orderNo") String orderNo, @Field("userId") int userId);

    /**
     * 送货单查询
     *
     * @param scamStr
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<SendOrdernoBean>> sendOrdernoQuery(@Field("scamStr") String scamStr);

    /**
     * 物品清点保存
     *
     * @param orderNo  单号
     * @param pointNum 请点数
     * @param spareNum 备品数
     * @return
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<PointMaterialBean>> saveMaterialPoint(@Field("orderNo") String orderNo, @Field("pointNum") int pointNum, @Field("spareNum") int spareNum);
    /**
     * 物品清点保存
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/wpda/po/POSaveReceive")
    Observable<CommonResult<Integer>> saveMaterialPoint(@FieldMap Map<String,Object> params);

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


    /********************出库 请求************************************************************/

    /**
     * 搜索相关的请求
     */
    /**
     * 委外退料单号扫码 请求
     */
    @FormUrlEncoded
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<OutSourceFeedBean>> searchOutsourceFeed(@Field("orderno") String orderno);
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
    Observable<CommonResult<OrderNoBean>> searchPick(@Field("ordernoPick") String ordernoPick,@Field("ordernoSend") String ordernoSend,@Field("ordernoSale") String ordernoSale);
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

}
