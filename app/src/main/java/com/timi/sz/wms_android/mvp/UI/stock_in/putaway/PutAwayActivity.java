package com.timi.sz.wms_android.mvp.UI.stock_in.putaway;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OutReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ProductionReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.mvp.UI.stock_in.detail.StockInDetailActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_PRODUCTION_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_SALE_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.ORDER_NO;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_LIB_LOATION;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

/**
 * 入库上架
 * author: timi
 * create at: 2017/8/31 9:14
 */
public class PutAwayActivity extends BaseActivity<PutAwayView, PutAwayPresenter> implements PutAwayView, BaseActivity.ScanQRCodeResultListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_receive_pro_num)
    TextView tvReceiveProNum;
    @BindView(R.id.tv_create_orderno_date)
    TextView tvCreateordernoDate;
    @BindView(R.id.tv_arrive_pro_total_num)
    TextView tvArriveProTotalNum;
    @BindView(R.id.tv_right_pro_num)
    TextView tvRightProNum;
    @BindView(R.id.tv_in_stock_num)
    TextView tvInStockNum;
    @BindView(R.id.tv_can_in_stock_num)
    TextView tvCanInStockNum;
    @BindView(R.id.tv_wait_count_num)
    TextView tvWaitCountNum;
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.tv_putaway_material_code)
    TextView tvPutawayMaterialCode;
    @BindView(R.id.tv_putaway_material_num)
    TextView tvPutawayMaterialNum;
    @BindView(R.id.tv_putaway_material_name)
    TextView tvPutawayMaterialName;
    @BindView(R.id.tv_putaway_material_nmodel)
    TextView tvPutawayMaterialNmodel;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.ll_putaway_arrive)
    LinearLayout llPutawayArrive;
    @BindView(R.id.ll_putaway_in_stock)
    LinearLayout llPutawayInStock;
    @BindView(R.id.tv_in_stock_total_num)
    TextView tvInStockTotalNum;
    @BindView(R.id.ll_putaway_total_instock_num)
    LinearLayout llPutawayTotalInstockNum;
    @BindView(R.id.tv_putaway_orderno_num)
    TextView tvPutawayordernoNum;
    @BindView(R.id.ll_putaway_date)
    LinearLayout llPutawayDate;
    @BindView(R.id.ll_putaway_total_num)
    LinearLayout llPutawayTotalNum;
    @BindView(R.id.tv_putaway_scan_location)
    TextView tvPutawayScanLocation;
    @BindView(R.id.iv_putaway_scan_location)
    ImageView ivPutawayScanLocation;
    @BindView(R.id.tv_putaway_scan_material)
    TextView tvPutawayScanMaterial;
    @BindView(R.id.iv_putaway_scan_material)
    ImageView ivPutawayScanMaterial;
    /**
     * 默认是 入库来料单
     */
    private int intentCode = Constants.COME_MATERAIL_NUM;
    /**
     * 单号
     */
    private String orderNo = "";

    @Override
    public int setLayoutId() {
        return R.layout.activity_put_away;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(Constants.CODE_STR, Constants.COME_MATERAIL_NUM);
        orderNo = getIntent().getStringExtra(ORDER_NO);
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 查看详情
                 */
                Intent it = new Intent(PutAwayActivity.this, StockInDetailActivity.class);
                it.putExtra(Constants.CODE_STR, intentCode);
                startActivity(it);
            }
        });
    }

    @Override
    public void initView() {

    }

    /****************一大堆 接受数据的实体，太不是人了************************************************/
    //收货单实体
    private ReceiveOrdernoBean mReceiveBean;
    //产成品 选单实体
    private FinishGoodsOrdernoBean mFinishBean;
    //产成品 生单实体
    private FinishGoodsCreateBillBean mFinishCreateBean;
    //其他   选单实体
    private OtherAuditSelectOrdernoBean mOtherBean;
    //委外 实体
    private OutReturnMaterialBean mOutBean;
    //生产 实体
    private ProductionReturnMaterialBean mProductBean;
    //销售 实体
    private SaleGoodsReturnBean mSaleBean;

    @Override
    public void initData() {
        setData();
    }

    @Override
    public PutAwayPresenter createPresenter() {
        return new PutAwayPresenter(this);
    }

    @Override
    public PutAwayView createView() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 设置数据
     */
    private void setData() {
        llPutawayArrive.setVisibility(View.GONE);
        llPutawayInStock.setVisibility(View.GONE);
        llPutawayTotalInstockNum.setVisibility(View.GONE);
        switch (intentCode) {
            case Constants.BUY_ORDE_NUM://采购单
                break;
            case Constants.BUY_SEND_NUM://送货单
                break;
            case Constants.COME_MATERAIL_NUM://来料单
                /**
                 * 来料单 实体
                 */
                mReceiveBean = getIntent().getParcelableExtra(Constants.IN_STOCK_RECEIVE_BEAN);

                llPutawayArrive.setVisibility(View.VISIBLE);
                llPutawayInStock.setVisibility(View.VISIBLE);
                /**
                 * 标题
                 */
                tvTitle.setText(R.string.in_stock_num_title);
                /**
                 * 收货单号
                 */
                setTextViewText(tvReceiveProNum, R.string.receive_pro_num, mReceiveBean.orderNo);
                /**
                 * 到货数
                 */
                setTextViewText(tvArriveProTotalNum, R.string.arrive_pro_total_num, mReceiveBean.totalNum);
                /**
                 * 合格总数
                 */
                setTextViewText(tvRightProNum, R.string.right_pro_num, mReceiveBean.qualifiedNum);
                /**
                 * 已入库总数
                 */
                setTextViewText(tvInStockNum, R.string.in_stock_num, mReceiveBean.inStockNum);
                /**
                 * 可入库总数
                 */
                setTextViewText(tvCanInStockNum, R.string.can_in_stock_num, mReceiveBean.canInStockNum);
                /**
                 * 日期
                 */
                setTextViewText(tvCreateordernoDate, R.string.create_orderno_date, mReceiveBean.date);
                /**
                 * 待点总数
                 */
                setTextViewText(tvWaitCountNum, R.string.wait_count_num, mReceiveBean.waitPointNum);
                /**
                 * 已点总数
                 */
                setTextViewText(tvHaveCountNum, R.string.have_count_num, mReceiveBean.havePointNum);
                /**
                 * 设置按钮的文字
                 */
                btnLogin.setText(R.string.create_instock_orderno);
                break;
            case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                /**
                 * 产成品 审核 实体
                 */
                mFinishBean = getIntent().getParcelableExtra(Constants.IN_STOCK_FINISH_BEAN);
                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(R.string.product_warehous_warrent_num_title);
                /**
                 * 产成品入库单：
                 */
                setTextViewText(tvReceiveProNum, R.string.product_warehous_warrent_num, mFinishBean.orderNo);
                /**
                 * 待点总数
                 */
                setTextViewText(tvInStockTotalNum, R.string.in_stock_total_num, mFinishBean.inStockNum);
                /**
                 * 日期
                 */
                setTextViewText(tvCreateordernoDate, R.string.create_orderno_date, mFinishBean.date);
                /**
                 * 待点总数
                 */
                setTextViewText(tvWaitCountNum, R.string.wait_count_num, mFinishBean.waitPointNum);
                /**
                 * 已点总数
                 */
                setTextViewText(tvHaveCountNum, R.string.have_count_num, mFinishBean.havePointNum);

                break;
            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                /**
                 * 产成品 生单 实体
                 */
                mFinishCreateBean = getIntent().getParcelableExtra(Constants.IN_STOCK_FINISH_CREATE_BEAN);

                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(R.string.create_task_orderno_title);
                /**
                 * 生产任务单：
                 */
                setTextViewText(tvReceiveProNum, R.string.create_task_orderno, mFinishCreateBean.orderNo);
                /**
                 * 待点总数
                 */
                /**
                 * 已入库总数
                 */
                setTextViewText(tvInStockNum, R.string.in_stock_num, mFinishCreateBean.haveInStockNum);
                /**
                 * 可入库总数
                 */
                setTextViewText(tvCanInStockNum, R.string.can_in_stock_num, mFinishCreateBean.canInStockNum);
                /**
                 * 单据总数
                 */
                tvPutawayordernoNum.setVisibility(View.VISIBLE);
                setTextViewText(tvPutawayordernoNum, R.string.orderno_num, mFinishCreateBean.ordernoNum);
                /**
                 * 日期
                 */
                setTextViewText(tvCreateordernoDate, R.string.create_orderno_date, mFinishCreateBean.date);
                /**
                 * 待点总数
                 */
                setTextViewText(tvWaitCountNum, R.string.wait_count_num, mFinishCreateBean.waitPointNum);
                /**
                 * 已点总数
                 */
                setTextViewText(tvHaveCountNum, R.string.have_count_num, mFinishCreateBean.havePointNum);
                /**
                 * 设置按钮的文字
                 */
                btnLogin.setText(R.string.create_product_orderno);
                break;
            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单
                /**
                 * 其他 选单 实体
                 */
                mOtherBean = getIntent().getParcelableExtra(Constants.IN_STOCK_FINISH_OTHER_BEAN);


                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(R.string.other_instock_orderno_title);
                /**
                 * 其他入库单：
                 */
                setTextViewText(tvReceiveProNum, R.string.other_instock_orderno, mOtherBean.orderNo);
                /**
                 * 日期
                 */
                setTextViewText(tvCreateordernoDate, R.string.create_orderno_date, mOtherBean.date);
                /**
                 * 待点总数
                 */
                setTextViewText(tvWaitCountNum, R.string.wait_count_num, mOtherBean.waitPointNum);
                /**
                 * 已点总数
                 */
                setTextViewText(tvHaveCountNum, R.string.have_count_num, mOtherBean.havePointNum);
                /**
                 * 入库总数
                 */
                setTextViewText(tvInStockTotalNum, R.string.in_stock_total_num, mOtherBean.inStockNum);
                break;
            case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）

                break;
            case Constants.OUT_RETURN_MATERAIL://委外退料
                /**
                 * 委外退料 的实体
                 */
                mOutBean = getIntent().getParcelableExtra(IN_STOCK_FINISH_OUT_BEAN);

                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.out_return_material_title));
                /**
                 * 委外退料单：
                 */
                setTextViewText(tvReceiveProNum, R.string.out_return_material_orderno, mOutBean.orderNo);
                /**
                 * 日期
                 */
                setTextViewText(tvCreateordernoDate, R.string.create_orderno_date, mOutBean.date);
                /**
                 * 待点总数
                 */
                setTextViewText(tvWaitCountNum, R.string.wait_count_num, mOutBean.waitPointNum);
                /**
                 * 已点总数
                 */
                setTextViewText(tvHaveCountNum, R.string.have_count_num, mOutBean.havePointNum);
                /**
                 * 入库总数
                 */
                setTextViewText(tvInStockTotalNum, R.string.in_stock_total_num, mOutBean.inStockNum);
                break;
            case Constants.CREATE_RETURN_MATERAIL://生产退料
                /**
                 * 生产退料 的实体
                 */
                mProductBean = getIntent().getParcelableExtra(IN_STOCK_FINISH_PRODUCTION_BEAN);

                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.create_return_material_title));
                /**
                 * 生产退料单号：
                 */
                setTextViewText(tvReceiveProNum, R.string.create_return_material_orderno, mProductBean.orderNo);
                /**
                 * 日期
                 */
                setTextViewText(tvCreateordernoDate, R.string.create_orderno_date, mProductBean.date);
                /**
                 * 待点总数
                 */
                setTextViewText(tvWaitCountNum, R.string.wait_count_num, mProductBean.waitPointNum);
                /**
                 * 已点总数
                 */
                setTextViewText(tvHaveCountNum, R.string.have_count_num, mProductBean.havePointNum);
                /**
                 * 入库总数
                 */
                setTextViewText(tvInStockTotalNum, R.string.in_stock_total_num, mProductBean.inStockNum);
                break;
            case Constants.SALE_RETURN_MATERAIL://销售 退料
                /**
                 * 销售 退料 的实体
                 */
                mSaleBean = getIntent().getParcelableExtra(IN_STOCK_FINISH_SALE_BEAN);

                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.sale_return_material_title));
                /**
                 * 生产退料单：
                 */
                setTextViewText(tvReceiveProNum, R.string.sale_return_material_orderno, mSaleBean.orderNo);
                /**
                 * 日期
                 */
                setTextViewText(tvCreateordernoDate, R.string.create_orderno_date, mSaleBean.date);
                /**
                 * 待点总数
                 */
                setTextViewText(tvWaitCountNum, R.string.wait_count_num, mSaleBean.waitPointNum);
                /**
                 * 已点总数
                 */
                setTextViewText(tvHaveCountNum, R.string.have_count_num, mSaleBean.havePointNum);
                /**
                 * 入库总数
                 */
                setTextViewText(tvInStockTotalNum, R.string.in_stock_total_num, mSaleBean.inStockNum);
                break;
        }
        /**
         * 将底部的物料信息置空
         */
        setTextViewText(tvPutawayMaterialCode, R.string.material_code, "");
        setTextViewText(tvPutawayMaterialName, R.string.material_name, "");
        setTextViewText(tvPutawayMaterialNmodel, R.string.material_model, "");
        setTextViewText(tvPutawayMaterialNum, R.string.material_num, "");
    }

    @OnClick({R.id.tv_putaway_scan_location, R.id.iv_putaway_scan_location, R.id.tv_putaway_scan_material, R.id.iv_putaway_scan_material, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_putaway_scan_location://目的库位码
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, this);
                break;
            case R.id.iv_putaway_scan_location://目的库位码
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, this);
                break;
            case R.id.tv_putaway_scan_material://物料码
                if (TextUtils.isEmpty(locationCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                    return;
                }
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
            case R.id.iv_putaway_scan_material://物料码
                if (TextUtils.isEmpty(locationCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                    return;
                }
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
            case R.id.btn_login://确认提交
                /**
                 * 不同的code  调用不同的接口进行提交操作
                 */
                switch (intentCode) {
                    case Constants.BUY_ORDE_NUM://采购单
                        break;
                    case Constants.BUY_SEND_NUM://送货单
                        break;
                    case Constants.COME_MATERAIL_NUM://来料单
                        if (TextUtils.isEmpty(locationCode)) {
                            ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                            return;
                        }
                        String materialCode = tvPutawayScanMaterial.getText().toString();
                        if (TextUtils.isEmpty(materialCode)) {
                            ToastUtils.showShort(getString(R.string.please_scan_material_code));
                            return;
                        }
                        /**
                         * 生成入库单
                         */
                        getPresenter().createInSockOrderno(locationCode);
                        break;
                    case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                        break;
                    case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                        break;
                    case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单
                        break;
                    case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）
                        break;
                    case Constants.OUT_RETURN_MATERAIL://委外退料
                        break;
                    case Constants.CREATE_RETURN_MATERAIL://生产退料
                        break;
                    case Constants.SALE_RETURN_MATERAIL://销售 退料
                        break;
                }
                break;
        }
    }

    /**
     * 库位码
     */
    private String locationCode = "";

    @Override
    public void searchReceiveGoodOrderno(ReceiveOrdernoBean bean) {
        /**
         * 收货单号
         */
        setTextViewText(tvReceiveProNum, R.string.receive_pro_num, bean.getOrderNo());
        /**
         * 到货数
         */
        setTextViewText(tvArriveProTotalNum, R.string.arrive_pro_total_num, bean.getTotalNum());
        /**
         * 合格总数
         */
        setTextViewText(tvRightProNum, R.string.right_pro_num, bean.getQualifiedNum());
        /**
         * 已入库总数
         */
        setTextViewText(tvInStockNum, R.string.in_stock_num, bean.getHavePointNum());
        /**
         * 可入库总数
         */
        setTextViewText(tvCanInStockNum, R.string.can_in_stock_num, bean.getCanInStockNum());
        /**
         * 日期
         */
        setTextViewText(tvCreateordernoDate, R.string.create_orderno_date, bean.getDate());
        /**
         * 待点总数
         */
        setTextViewText(tvWaitCountNum, R.string.wait_count_num, bean.getWaitPointNum());
        /**
         * 已点总数
         */
        setTextViewText(tvHaveCountNum, R.string.have_count_num, bean.getHavePointNum());
    }

    @Override
    public void searchFinishGoodsOrderno(FinishGoodsOrdernoBean bean) {
        /**
         * 产成品入库单：
         */
        setTextViewText(tvReceiveProNum, R.string.product_warehous_warrent_num, bean.getOrderNo());
        /**
         * 待点总数
         */
        setTextViewText(tvInStockTotalNum, R.string.in_stock_total_num, bean.getInStockNum());
        /**
         * 日期
         */
        setTextViewText(tvCreateordernoDate, R.string.create_orderno_date, bean.getDate());
        /**
         * 待点总数
         */
        setTextViewText(tvWaitCountNum, R.string.wait_count_num, bean.getWaitPointNum());
        /**
         * 已点总数
         */
        setTextViewText(tvHaveCountNum, R.string.have_count_num, bean.getHavePointNum());
    }

    @Override
    public void materialScanResult(MaterialScanPutAwayBean bean) {
        ToastUtils.showShort(getString(R.string.material_scan_putaway_success));
        /**
         * 扫码出来的数据
         */
        setTextViewText(tvPutawayMaterialCode, R.string.material_code, bean.getMaterialCode());
        setTextViewText(tvPutawayMaterialName, R.string.material_name, bean.getMaterialName());
        setTextViewText(tvPutawayMaterialNmodel, R.string.material_model, bean.getMaterialModel());
        setTextViewText(tvPutawayMaterialNum, R.string.material_num, bean.getMaterialBuyNum());

    }

    @Override
    public void vertifyLocationCode(VertifyLocationCodeBean bean) {
        /**
         * 库位码无效 清除
         */
        if (!bean.isTure) {
            ToastUtils.showShort(getString(R.string.location_code_no_user));
            //保存库位码
            locationCode = "";
            //设置库位码
            tvPutawayScanLocation.setText(getString(R.string.please_scan_lib_location_code));
        }
    }

    @Override
    public void createInStockOrderno(boolean isCreateSuccess) {
        if (isCreateSuccess) {
            ToastUtils.showShort("生成入库单成功");
            onBackPressed();
        } else {
            ToastUtils.showShort("生成入库单失败");
            onBackPressed();
        }
    }

    /**
     * 扫码的返回方法
     *
     * @param requestCode
     * @param result
     */
    @Override
    public void scanSuccess(int requestCode, String result) {
        switch (requestCode) {
            case REQUEST_SCAN_CODE_MATERIIAL:
                if (requestCode == RESULT_OK) {
                    LogUitls.d("物料码扫码--->", result);
                    tvPutawayScanMaterial.setText(result);
                    /**
                     * 物料扫码并上架的网络请求
                     */
                    getPresenter().materialScanNetWork(locationCode, result);
                }
                break;
            case REQUEST_SCAN_CODE_LIB_LOATION:
                LogUitls.d("库位码扫码--->", result);
                //保存库位码
                locationCode = result;
                //设置库位码
                tvPutawayScanLocation.setText(locationCode);
                /**
                 * 判断库位码是否有效
                 */
                getPresenter().vertifyLocationCode(locationCode);
                break;
        }

    }

}
