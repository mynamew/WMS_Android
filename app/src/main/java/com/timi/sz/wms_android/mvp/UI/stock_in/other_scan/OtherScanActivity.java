package com.timi.sz.wms_android.mvp.UI.stock_in.other_scan;

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

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
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
 * 入库 其他扫描
 * author: timi
 * create at: 2017/11/29 14:57
 */
public class OtherScanActivity extends BaseActivity<OtherScanView, OtherScanPresenter> implements OtherScanView, BaseActivity.ScanQRCodeResultListener {
    @BindView(R.id.tv_have_scan_num)
    TextView tvHaveScanNum;
    @BindView(R.id.tv_scan_location_tip)
    TextView tvScanLocationTip;
    @BindView(R.id.et_scan_location)
    EditText etScanLocation;
    @BindView(R.id.iv_can_location)
    ImageView ivCanLocation;
    @BindView(R.id.tv_can_material_tip)
    TextView tvCanMaterialTip;
    @BindView(R.id.et_scan_material)
    EditText etScanMaterial;
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
    @BindView(R.id.tv_putaway_material_attr)
    TextView tvPutawayMaterialAttr;
    @BindView(R.id.btn_create_other_instock_bill)
    Button btnCreateOtherInstockBill;
    /**
     * 入库单 单号
     */
    private String inStockOrderno = "";

    @Override
    public int setLayoutId() {
        return R.layout.activity_other_scan;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        final int intentCode = getIntent().getIntExtra(Constants.CODE_STR, Constants.COME_MATERAIL_NUM);
        setActivityTitle("其他入库(扫描入库)");
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 查看详情
                 */
                Intent it = new Intent(OtherScanActivity.this, StockInDetailActivity.class);
                it.putExtra(Constants.CODE_STR, intentCode);
                it.putExtra(Constants.IN_STOCK_ORDERNO, inStockOrderno);
                startActivity(it);
            }
        });
    }

    @Override
    public void initView() {
        etScanMaterial.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(OtherScanActivity.this);
                    String orderNum = etScanMaterial.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort(getString(R.string.please_scan_material_code));
                        return false;
                    }
                    /**
                     * 对库位码进行判断
                     */
                    if (TextUtils.isEmpty(locationCode)) {
                        ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                        return false;
                    }
                    if (!locationCodeIsUse) {
                        ToastUtils.showShort(getString(R.string.location_code_no_user));
                        return false;
                    }
                    /**
                     * 物料扫码并上架的网络请求
                     */
                    Map<String, Object> params1 = new HashMap<>();
                    params1.put("UserId", SpUtils.getInstance().getUserId());
                    params1.put("OrgId", SpUtils.getInstance().getOrgId());
                    params1.put("MAC", PackageUtils.getMac());
                    params1.put("SrcBillType", 0);
                    params1.put("DestBillType", 51);
                    params1.put("ScanId", ScanId);
                    params1.put("BillId", 0);
                    params1.put("BinCode", locationCode);
                    params1.put("BarcodeNo", orderNum);
                    getPresenter().materialScanNetWork(params1);
                }
                return false;
            }
        });
        etScanLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(OtherScanActivity.this);
                    String orderNum = etScanLocation.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                        return false;
                    }
                    /**
                     * 保存库位码
                     */
                    locationCode = orderNum;
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
                    params.put("BinCode", orderNum);
                    getPresenter().vertifyLocationCode(params);
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public OtherScanPresenter createPresenter() {
        return new OtherScanPresenter(this);
    }

    @Override
    public OtherScanView createView() {
        return this;
    }


    @OnClick({R.id.btn_create_other_instock_bill, R.id.iv_can_location, R.id.iv_putaway_scan_material})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_can_location:
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, this);
                break;
            case R.id.iv_putaway_scan_material:
                if (TextUtils.isEmpty(locationCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                    return;
                }
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
            case R.id.btn_create_other_instock_bill://生成入库单
                if (TextUtils.isEmpty(locationCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                    return;
                }
                if (!locationCodeIsUse) {
                    ToastUtils.showShort(getString(R.string.location_code_no_user));
                    return;
                }
                String materialCode = etScanLocation.getText().toString();
                if (TextUtils.isEmpty(materialCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_material_code));
                    return;
                }
                if (ScanId == 0) {//scanid 为0  证明未扫过条码或者条码已经入库 或者出库过了
                    ToastUtils.showShort(getString(R.string.please_inpiut_or_scan_visible_material_code));
                    return;
                }
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
                break;
        }
    }

    @Override
    public void materialScanResult(MaterialScanPutAwayBean bean) {
        ToastUtils.showShort(getString(R.string.material_scan_putaway_success));
        /**
         * 扫码出来的数据
         */
        tvPutawayMaterialCode.setText(bean.getMaterialCode());
        tvPutawayMaterialName.setText(bean.getMaterialName());
        tvPutawayMaterialNmodel.setText(bean.getMaterialStandard());
        tvPutawayMaterialNum.setText(String.valueOf(bean.getBarcodeQty()));
        tvPutawayMaterialAttr.setText(TextUtils.isEmpty(bean.getMaterialAttribute()) ? getString(R.string.none) : bean.getMaterialAttribute());
        /**
         * 已扫的总数
         */
        int haveScanNum = Integer.parseInt(tvHaveScanNum.getText().toString()) + bean.getBarcodeQty();
        tvHaveScanNum.setText(String.valueOf(haveScanNum));
        ScanId=bean.getScanId();
    }

    /**
     * 库位码扫码的输入
     */
    private VertifyLocationCodeBean mVertifyLocationCodeBean;
    /**
     * 库位码
     */
    private String locationCode = "";
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
     * 扫描的Id  默认是0  当提交物料扫码入库后 会返回sanid
     */
    private int ScanId = 0;

    /**
     * 扫码返回 请求
     *
     * @param requestCode
     * @param result
     */
    @Override
    public void scanSuccess(int requestCode, String result) {
        switch (requestCode) {
            case REQUEST_SCAN_CODE_MATERIIAL:
                LogUitls.d("物料码扫码--->", result);
                etScanMaterial.setText(result);
                /**
                 * 物料扫码并上架的网络请求
                 */
                /**
                 * 物料扫码并上架的网络请求
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", 0);
                params.put("SrcBillType", 0);
                params.put("DestBillType", 51);
                params.put("ScanId", ScanId);
                params.put("BinCode", locationCode);
                params.put("BarcodeNo", result);
                getPresenter().materialScanNetWork(params);
                break;
            case REQUEST_SCAN_CODE_LIB_LOATION:
                LogUitls.d("库位码扫码--->", result);
                //保存库位码
                locationCode = result;
                //设置库位码
                etScanLocation.setText(locationCode);
                Map<String, Object> params1 = new HashMap<>();
                params1.put("UserId", SpUtils.getInstance().getUserId());
                params1.put("OrgId", SpUtils.getInstance().getOrgId());
                params1.put("MAC", PackageUtils.getMac());
                params1.put("BinCode", locationCode);
                getPresenter().vertifyLocationCode(params1);
                //判断库位码是否有效
                break;
        }
    }

}
