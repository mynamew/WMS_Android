package com.timi.sz.wms_android.mvp.UI.stock_in.putaway;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
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
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.mvp.UI.stock_in.detail.StockInDetailActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_LIB_LOATION;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

/**
 * 销售退料
 * author: timi
 * create at: 2017/11/20 11:36
 */
public class SaleGoodsReturnActivity extends BaseActivity<PutAwayView, PutAwayPresenter> implements PutAwayView, BaseActivity.ScanQRCodeResultListener {

    @BindView(R.id.tv_receive_pro_num)
    TextView tvReceiveProNum;
    @BindView(R.id.tv_create_orderno_date)
    TextView tvCreateOrdernoDate;
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
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_num)
    TextView tvMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private SaleGoodsReturnBean saleGoodsReturnBean;
    /**
     * 默认是 入库来料单
     */
    private int intentCode = Constants.COME_MATERAIL_NUM;

    @Override
    public int setLayoutId() {
        return R.layout.activity_sale_goods_return;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        /**
         * 标题
         */
        setActivityTitle(getString(R.string.putaway_sale_return_material_tip));
        intentCode = getIntent().getIntExtra(Constants.CODE_STR, Constants.COME_MATERAIL_NUM);
        saleGoodsReturnBean = new Gson().fromJson(getIntent().getStringExtra(Constants.IN_STOCK_FINISH_SALE_BEAN), SaleGoodsReturnBean.class);
        ScanId = saleGoodsReturnBean.getScanId();
    }

    @Override
    public void initView() {
        etPutawayScanLocation.setFocusable(true);
        etPutawayScanLocation.setFocusableInTouchMode(true);
        etPutawayScanLocation.requestFocus();
        etPutawayScanLocation.findFocus();
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 查看详情
                 */
                Intent it = new Intent(SaleGoodsReturnActivity.this, StockInDetailActivity.class);
                it.putExtra(Constants.CODE_STR, intentCode);
                it.putExtra(Constants.STOCKIN_BILLID, saleGoodsReturnBean.getBillId());
                startActivity(it);
            }
        });
        setEdittextListener(etPutawayScanMaterial,REQUEST_SCAN_CODE_MATERIIAL,R.string.please_scan_material_code,0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 物料扫码并上架的网络请求
                 */
                Map<String, Object> params1 = new HashMap<>();
                params1.put("UserId", SpUtils.getInstance().getUserId());
                params1.put("OrgId", SpUtils.getInstance().getOrgId());
                params1.put("MAC", PackageUtils.getMac());
                params1.put("SrcBillType", 43);
                params1.put("DestBillType", 43);
                params1.put("ScanId", saleGoodsReturnBean.getScanId());
                params1.put("BinCode", locationCode);
                params1.put("BillId", saleGoodsReturnBean.getBillId());
                params1.put("BarcodeNo", result);
                getPresenter().materialScanNetWork(params1, result);
            }
        });
        setEdittextListener(etPutawayScanLocation,REQUEST_SCAN_CODE_LIB_LOATION,R.string.please_scan_lib_location_code,0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 保存库位码
                 */
                locationCode = result;
                /**
                 * 发起请求
                 */
                /**
                 * 判断库位码是否有效
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BinCode", result);
                getPresenter().vertifyLocationCode(params);
            }
        });
    }
    @Override
    public void setMaterialEdittextSelect() {
        etPutawayScanMaterial.setFocusable(true);
        etPutawayScanMaterial.setFocusableInTouchMode(true);
        etPutawayScanMaterial.requestFocus();
        etPutawayScanMaterial.findFocus();
        Selection.selectAll(etPutawayScanMaterial.getText());
    }

    @Override
    public void setLocationSelect() {
        etPutawayScanLocation.setFocusable(true);
        etPutawayScanLocation.setFocusableInTouchMode(true);
        etPutawayScanLocation.requestFocus();
        etPutawayScanLocation.findFocus();
        Selection.selectAll(etPutawayScanLocation.getText());
    }
    @Override
    public void initData() {
        /**
         * 收货单号
         */
        tvReceiveProNum.setText(saleGoodsReturnBean.getBillCode());

        /**
         * 退料总数
         */
        tvInStockTotalNum.setText(String.valueOf(saleGoodsReturnBean.getQty()));
        /**
         * 日期
         */
        tvCreateOrdernoDate.setText(saleGoodsReturnBean.getBillDate());
        /**
         * 待点总数
         */
        tvWaitCountNum.setText(String.valueOf(saleGoodsReturnBean.getWaitQty()));
        /**
         * 已点总数
         */
        tvHaveCountNum.setText(String.valueOf(saleGoodsReturnBean.getScanQty()));
    }

    @Override
    public PutAwayPresenter createPresenter() {
        return new PutAwayPresenter(this);
    }

    @Override
    public PutAwayView createView() {
        return this;
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
                if (!locationCodeIsUse) {
                    ToastUtils.showShort(getString(R.string.location_code_no_user));
                    return;
                }
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
            case R.id.btn_login://确认提交
                /**
                 * 生成入库单
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("ScanId", ScanId);
                params.put("SubmitType", 0);
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
        findViewById(R.id.layout_material_info).setVisibility(View.VISIBLE);
        /**
         * 扫码出来的数据
         */
        setTextViewContent(tvMaterialCode,bean.getMaterialCode());
        setTextViewContent(tvMaterialName,bean.getMaterialName());
        setTextViewContent(tvMaterialModel,bean.getMaterialStandard());
        setTextViewContent(tvMaterialAttr,bean.getMaterialAttribute());
        setTextViewContent(tvMaterialCode,bean.getMaterialCode());
        setTextViewContent(tvMaterialNum,bean.getBarcodeQty());
        /**
         * 设置附加属性
         */
        setMaterialAttrStatus(tvMaterialAttr);
        /**
         * 设置已点总数
         */
        tvHaveCountNum.setText(String.valueOf(bean.getTotalScanQty()));
        /**
         * 设置已入库总数
         */
        tvInStockTotalNum.setText(String.valueOf(bean.getTotalInstockQty()));
        /**
         * 设置扫码Id
         */
        ScanId = bean.getScanId();
    }

    private VertifyLocationCodeBean mVertifyLocationCodeBean;
    private boolean locationCodeIsUse = false;

    @Override
    public void vertifyLocationCode(VertifyLocationCodeBean bean) {
        locationCodeIsUse = true;
        ToastUtils.showShort(getString(R.string.location_code_is_visible));
        mVertifyLocationCodeBean = bean;
    }

    @Override
    public void createInStockOrderno() {
        ToastUtils.showShort(getString(R.string.create_instock_bill_success));
        onBackPressed();
    }

    /**
     * 扫码的返回方法
     *
     * @param requestCode
     * @param result
     */
    private int ScanId = 0;

    @Override
    public void scanSuccess(int requestCode, String result) {
        switch (requestCode) {
            case REQUEST_SCAN_CODE_MATERIIAL:
                LogUitls.d("物料码扫码--->", result);
                etPutawayScanMaterial.setText(result);
                /**
                 * 物料扫码并上架的网络请求
                 */
                Map<String, Object> params1 = new HashMap<>();
                params1.put("UserId", SpUtils.getInstance().getUserId());
                params1.put("OrgId", SpUtils.getInstance().getOrgId());
                params1.put("MAC", PackageUtils.getMac());
                params1.put("SrcBillType", 43);
                params1.put("DestBillType", 14);
                params1.put("ScanId", saleGoodsReturnBean.getScanId());
                params1.put("BinCode", locationCode);
                params1.put("BarcodeNo", result);
                getPresenter().materialScanNetWork(params1, result);
                break;
            case REQUEST_SCAN_CODE_LIB_LOATION:
                /**
                 * 重新扫描库位码的时候 将库位码是否有效的标识更改成false
                 */
                locationCodeIsUse = false;
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
                params.put("ScanId", ScanId);
                params.put("SubmitType", 1);
                getPresenter().vertifyLocationCode(params);
                break;
        }

    }
}
