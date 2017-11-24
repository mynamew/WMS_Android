package com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.LibraryAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanLocationResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanMaterialResult;
import com.timi.sz.wms_android.mvp.UI.stock_in.detail.StockInDetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.PutAwayActivity;
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


    @BindView(R.id.tv_location_code_tip)
    TextView tvLocationCodeTip;
    @BindView(R.id.tv_scan_location)
    EditText tvScanLocation;
    @BindView(R.id.iv_can_location)
    ImageView ivCanLocation;
    @BindView(R.id.rl_location_code)
    RelativeLayout rlLocationCode;
    @BindView(R.id.tv_material_code_tip)
    TextView tvMaterialCodeTip;
    @BindView(R.id.et_material_code)
    EditText etMaterialCode;
    @BindView(R.id.iv_can_material_code)
    ImageView ivCanMaterialCode;
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
    @BindView(R.id.btn_commit)
    Button btnCommit;

    @Override
    public int setLayoutId() {
        return R.layout.activity_library_adjust;

    }

    @Override
    public void initBundle(Bundle savedInstanceState) {

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

    @OnClick({ R.id.iv_can_location, R.id.tv_scan_location, R.id.tv_material_code, R.id.iv_can_material_code, R.id.btn_confirm_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_scan_location://扫描库位码
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {

                        if (TextUtils.isEmpty(result)) {
                            return;
                        }
                        /**
                         *  设置物料码
                         */
                        tvScanLocation.setText(result);
                        /**
                         * 请求库位信息
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("libcode", result);
                        getPresenter().scanLibLocationCode(params);
                    }
                });
                break;
            case R.id.iv_can_material_code://扫描物料码
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
            case R.id.btn_confirm_commit://确认提交
                String materialCode = tvMaterialCode.getText().toString();
                if (TextUtils.isEmpty(materialCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_material_code));
                    return;
                }
                String locationCode = tvScanLocation.getText().toString();
                if (TextUtils.isEmpty(locationCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
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

    @Override
    public void scanLibLocatonCode(ScanLocationResult result) {

    }

    @Override
    public void scanMaterialCode(ScanMaterialResult result) {

    }

    @Override
    public void libraryAdjust(LibraryAdjustResult result) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
