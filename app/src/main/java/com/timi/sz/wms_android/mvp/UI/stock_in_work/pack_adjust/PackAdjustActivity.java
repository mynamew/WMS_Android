package com.timi.sz.wms_android.mvp.UI.stock_in_work.pack_adjust;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.stockin_work.ContainerAdjustResult;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust_detail.LibAdjustDetailActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 容器转换
 */
public class PackAdjustActivity extends BaseActivity<PackAdjustView, PackAdjustPresenter> implements PackAdjustView {


    @BindView(R.id.et_old_pack_code)
    EditText etOldPackCode;
    @BindView(R.id.iv_old_pack)
    ImageView ivOldPack;
    @BindView(R.id.et_dest_pack_code_code)
    EditText etDestPackCodeCode;
    @BindView(R.id.iv_can_material_code)
    ImageView ivCanMaterialCode;
    @BindView(R.id.tv_old_pack_code)
    TextView tvOldPackCode;
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
        return R.layout.activity_pack_adjust;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.siw_group_change);
    }

    @Override
    public void initView() {
        setEdittextListener(etDestPackCodeCode, Constants.REQUEST_SCAN_CODE_MATERIIAL, R.string.please_input_dest_pack_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 物料扫码并上架的网络请求
                 */
                Map<String, Object> params1 = new HashMap<>();
                params1.put("UserId", SpUtils.getInstance().getUserId());
                params1.put("OrgId", SpUtils.getInstance().getOrgId());
                params1.put("MAC", PackageUtils.getMac());
                params1.put("SrcContainer", etOldPackCode.getText().toString().trim());
                params1.put("DestContainer", etDestPackCodeCode.getText().toString().trim());
                getPresenter().containnerAdjust(params1);
            }
        });
        setEdittextListener(etOldPackCode, Constants.REQUEST_SCAN_CODE_LIB_LOATION, R.string.please_input_old_pack_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                etOldPackCode.setText(result);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public PackAdjustPresenter createPresenter() {
        return new PackAdjustPresenter(this);
    }

    @Override
    public PackAdjustView createView() {
        return this;
    }

    @Override
    public void containnerAdjust(ContainerAdjustResult o) {
        ToastUtils.showShort(R.string.pack_adjust_success);
        llScanInfo.setVisibility(View.VISIBLE);
        setTextViewContent(tvMaterialAttr, o.getMaterialAttribute());
        setTextViewContent(tvMaterialCode, o.getMaterialCode());
        setTextViewContent(tvMaterialModel, o.getMaterialStandard());
        setTextViewContent(tvMaterialNum, o.getQty());
        setTextViewContent(tvMaterialName, o.getMaterialName());
        setTextViewContent(tvOldPackCode, o.getSrcBarcode());
        setTextViewContent(tvBarcodeType, o.getDestContainer());
    }


    @OnClick({R.id.iv_old_pack, R.id.iv_can_material_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_old_pack:
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etOldPackCode.setText(result);
                    }
                });
                break;
            case R.id.iv_can_material_code:
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etDestPackCodeCode.setText(result);
                        /**
                         * 物料扫码并上架的网络请求
                         */
                        Map<String, Object> params1 = new HashMap<>();
                        params1.put("UserId", SpUtils.getInstance().getUserId());
                        params1.put("OrgId", SpUtils.getInstance().getOrgId());
                        params1.put("MAC", PackageUtils.getMac());
                        params1.put("SrcContainer", etOldPackCode.getText().toString().trim());
                        params1.put("DestContainer", etDestPackCodeCode.getText().toString().trim());
                        getPresenter().containnerAdjust(params1);
                    }
                });
                break;
        }
    }
    @Override
    public void setOldPackSelect() {
        etOldPackCode.setText(etOldPackCode.getText());
        etOldPackCode.setFocusable(true);
        etOldPackCode.setFocusableInTouchMode(true);
        etOldPackCode.requestFocus();
        etOldPackCode.findFocus();
        Selection.selectAll(etOldPackCode.getText());
    }
}
