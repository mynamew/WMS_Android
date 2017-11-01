package com.timi.sz.wms_android.mvp.UI.stock_in.putaway;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
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
import com.timi.sz.wms_android.mvp.UI.stock_in.query.SearchBuyOrderActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.ORDER_NO;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_LIB_LOATION;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

/**
 * 来料入库：入库上架
 * author: timi
 * create at: 2017/8/31 9:14
 */
public class PutAwayActivity extends BaseActivity<PutAwayView, PutAwayPresenter> implements PutAwayView, BaseActivity.ScanQRCodeResultListener {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_receive_pro_num)
    TextView tvReceiveProNum;
    @BindView(R.id.tv_create_orderno_date)
    TextView tvCreateOrdernoDate;
    @BindView(R.id.tv_arrive_pro_total_num)
    TextView tvArriveProTotalNum;
    @BindView(R.id.tv_right_pro_num)
    TextView tvRightProNum;
    @BindView(R.id.tv_wait_count_num)
    TextView tvWaitCountNum;
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.tv_in_stock_total_num)
    TextView tvInStockTotalNum;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_putaway_scan_location_tip)
    TextView tvPutawayScanLocationTip;
    @BindView(R.id.et_putaway_scan_location)
    EditText etPutawayScanLocation;
    @BindView(R.id.iv_putaway_scan_location)
    ImageView ivPutawayScanLocation;
    @BindView(R.id.tv_putaway_scan_material_tip)
    TextView tvPutawayScanMaterialTip;
    @BindView(R.id.et_putaway_scan_material)
    EditText etPutawayScanMaterial;
    @BindView(R.id.iv_putaway_scan_material)
    ImageView ivPutawayScanMaterial;
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
    /**
     * 默认是 入库来料单
     */
    private int intentCode = Constants.COME_MATERAIL_NUM;

    @Override
    public int setLayoutId() {
        return R.layout.activity_put_away;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(Constants.CODE_STR, Constants.COME_MATERAIL_NUM);
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
        etPutawayScanMaterial.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(PutAwayActivity.this);
                    String orderNum = etPutawayScanMaterial.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort(getString(R.string.please_scan_material_code));
                    }
                    if (orderNum.length() < 4) {
                        ToastUtils.showShort(getString(R.string.input_orderno_more_four));
                    } else {
                        /**
                         * 发起请求
                         */
                        scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, PutAwayActivity.this);
                    }
                }
                return false;
            }
        });
        etPutawayScanLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(PutAwayActivity.this);
                    String orderNum = etPutawayScanLocation.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                    }
                    if (orderNum.length() < 4) {
                        ToastUtils.showShort(getString(R.string.input_orderno_more_four));
                    } else {
                        /**
                         * 发起请求
                         */
                        scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, PutAwayActivity.this);
                    }
                }
                return false;
            }
        });
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
        /**
         * 来料单 实体
         */
        mReceiveBean = new Gson().fromJson(getIntent().getStringExtra(Constants.IN_STOCK_RECEIVE_BEAN), ReceiveOrdernoBean.class);
        /**
         * 标题
         */
        setActivityTitle(getString(R.string.in_stock_num_title));
        /**
         * 收货单号
         */
        tvReceiveProNum.setText(mReceiveBean.getReceiptCode());
        /**
         * 到货数
         */
        tvArriveProTotalNum.setText(String.valueOf(mReceiveBean.getReceiptQty()));
        /**
         * 合格总数
         */
        tvRightProNum.setText(String.valueOf(mReceiveBean.getPassQty()));
        /**
         * 已入库总数
         */
        tvInStockTotalNum.setText(String.valueOf(mReceiveBean.getInstockQty()));
        /**
         * 日期
         */
        tvCreateOrdernoDate.setText(mReceiveBean.getReceipDate());
        /**
         * 待点总数
         */
        tvWaitCountNum.setText(String.valueOf(mReceiveBean.getWaitQty()));
        /**
         * 已点总数
         */
        tvHaveCountNum.setText(String.valueOf(mReceiveBean.getScanQty()));
        /**
         * 设置按钮的文字
         */
        btnLogin.setText(R.string.create_instock_orderno);

    }

    @OnClick({R.id.iv_putaway_scan_location, R.id.iv_putaway_scan_material, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_putaway_scan_location://目的库位码
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, this);
                break;
            case R.id.iv_putaway_scan_material://物料码
                if (TextUtils.isEmpty(locationCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                    return;
                }
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
            case R.id.btn_login://确认提交
                if (TextUtils.isEmpty(locationCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                    return;
                }
                String materialCode = etPutawayScanMaterial.getText().toString();
                if (TextUtils.isEmpty(materialCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_material_code));
                    return;
                }
                /**
                 * 生成入库单
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillNo", locationCode);
                getPresenter().createInSockOrderno(params);
        }
    }

    /**
     * 库位码
     */
    private String locationCode = "";

    @Override
    public void materialScanResult(MaterialScanPutAwayBean bean) {
        ToastUtils.showShort(getString(R.string.material_scan_putaway_success));
        /**
         * 扫码出来的数据
         */
        tvPutawayMaterialCode.setText(bean.getMaterialCode());
        tvPutawayMaterialName.setText(bean.getMaterialName());
        tvPutawayMaterialNmodel.setText(bean.getMaterialModel());
        tvPutawayMaterialNum.setText(bean.getMaterialBuyNum());

    }

    private VertifyLocationCodeBean mVertifyLocationCodeBean;

    @Override
    public void vertifyLocationCode(VertifyLocationCodeBean bean) {
        ToastUtils.showShort("库位码有效！");
        mVertifyLocationCodeBean = bean;
    }

    @Override
    public void createInStockOrderno() {
        ToastUtils.showShort("生成入库单成功");
        onBackPressed();
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
                    etPutawayScanMaterial.setText(result);
                    /**
                     * 物料扫码并上架的网络请求
                     */
                    Map<String, Object> params = new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("MAC", PackageUtils.getMac());
                    params.put("BillNo", locationCode);
                    getPresenter().materialScanNetWork(params, result);
                }
                break;
            case REQUEST_SCAN_CODE_LIB_LOATION:
                LogUitls.d("库位码扫码--->", result);
                //保存库位码
                locationCode = result;
                //设置库位码
                etPutawayScanLocation.setText(locationCode);
                /**
                 * 判断库位码是否有效
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BinCode", locationCode);
                getPresenter().vertifyLocationCode(params);
                break;
        }

    }
}
