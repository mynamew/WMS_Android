package com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.stockin_work.LibraryAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanMaterialResult;
import com.timi.sz.wms_android.bean.stockin_work.lib_adjust.StockAdjustResult;
import com.timi.sz.wms_android.mvp.UI.stock_in.other_scan.OtherScanActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust_detail.LibAdjustDetailActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库位调整模块
 * author: timi
 * create at: 2017/9/22 10:25
 */
public class LibraryAdjustActivity extends BaseActivity<LibraryAdjustView, LibraryAdjustPresenter> implements LibraryAdjustView {


    @BindView(R.id.et_material_code)
    EditText etMaterialCode;
    @BindView(R.id.et_scan_location)
    EditText etScanLocation;
    @BindView(R.id.tv_barcode)
    TextView tvBarcode;
    @BindView(R.id.tv_barcode_type)
    TextView tvBarcodeType;
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
    @BindView(R.id.ll_scan_info)
    LinearLayout llScanInfo;

    @Override
    public int setLayoutId() {
        return R.layout.activity_library_adjust;

    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
      setActivityTitle(getString(R.string.siw_storage_location_change));
    }

    @Override
    public void initView() {
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 查看详情
                 */
                Intent it = new Intent(LibraryAdjustActivity.this, LibAdjustDetailActivity.class);
                startActivity(it);
            }
        });
        etMaterialCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(LibraryAdjustActivity.this);
                    String materialCode = etMaterialCode.getText().toString().trim();
                    if (TextUtils.isEmpty(materialCode)) {
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
                    params1.put("BinCode", locationCode);
                    getPresenter().scanMaterialCode(params1);
                }
                return false;
            }
        });
        etScanLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(LibraryAdjustActivity.this);
                    String locationCodeStr = etScanLocation.getText().toString().trim();
                    if (TextUtils.isEmpty(locationCodeStr)) {
                        ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                    }
                    /**
                     * 保存库位码
                     */
                    locationCode = locationCodeStr;
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
                    params.put("BinCode", locationCode);
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
    public LibraryAdjustPresenter createPresenter() {
        return new LibraryAdjustPresenter(this);
    }

    @Override
    public LibraryAdjustView createView() {
        return this;
    }

    /**
     * 扫描的id
     */
    private int ScanId = 0;

    @OnClick({R.id.iv_scan_location, R.id.iv_can_material_code, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan_location://扫描库位码
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {

                        if (TextUtils.isEmpty(result)) {
                            return;
                        }
                        /**
                         *  设置物料码
                         */
                        etScanLocation.setText(result);
                        locationCode=result;
                        /**
                         * 请求库位信息
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("BinCode", result);
                        getPresenter().vertifyLocationCode(params);
                    }
                });
                break;
            case R.id.iv_can_material_code://扫描物料码
                /**
                 * 对库位码进行判断
                 */
                if (TextUtils.isEmpty(locationCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                    return;
                }
                if (!locationCodeIsUse) {
                    ToastUtils.showShort(getString(R.string.location_code_no_user));
                    return;
                }
                /**
                 * 调扫描的方法
                 */
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        if (TextUtils.isEmpty(result)) {
                            return;
                        }
                        /**
                         *  设置物料码
                         */
                        tvMaterialCode.setText(result);
                        locationCode=result;
                        /**
                         * 请求物料信息
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("materialCode", result);
                        getPresenter().scanMaterialCode(params);
                    }
                });
                break;
            case R.id.btn_commit://确认提交
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
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("MAC", PackageUtils.getMac());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("materialCode", materialCode);
                params.put("libcode", locationCode);
                getPresenter().libraryAdjustResult(params);
                break;
        }
    }

    /**
     * 库位码
     */
    private String locationCode = "";
    /**
     * 库位码的标识
     */
    private boolean locationCodeIsUse = false;

    /**
     * 库位码的实体
     */
    private VertifyLocationCodeBean mVertifyLocationCodeBean;

    @Override
    public void vertifyLocationCode(VertifyLocationCodeBean bean) {
        locationCodeIsUse = true;
        ToastUtils.showShort(getString(R.string.location_code_is_visible));
        mVertifyLocationCodeBean = bean;
    }

    @Override
    public void scanMaterialCode(StockAdjustResult bean) {
        ToastUtils.showShort(getString(R.string.material_scan_putaway_success));
        /**
         * 扫码出来的数据
         */
        tvMaterialCode.setText(bean.getMaterialCode());
        tvMaterialName.setText(bean.getMaterialName());
        tvMaterialModel.setText(bean.getMaterialStandard());
        tvMaterialNum.setText(String.valueOf(bean.getQty()));
        /**
         * 设置附加属性
         */
        tvMaterialAttr.setText(bean.getMaterialAttribute());
        setMaterialAttrStatus(tvMaterialAttr);
    }

    @Override
    public void libraryAdjust(LibraryAdjustResult result) {

    }
}
