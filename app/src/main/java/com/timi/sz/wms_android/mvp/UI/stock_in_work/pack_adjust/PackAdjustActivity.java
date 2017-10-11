package com.timi.sz.wms_android.mvp.UI.stock_in_work.pack_adjust;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.LibraryAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanLocationResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanMaterialResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *   容器转换
 */
public class PackAdjustActivity extends BaseActivity<PackAdjustView,PackAdjustPresenter> implements PackAdjustView {
    @BindView(R.id.tv_scan_location)
    TextView tvScanLocation;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_code_type)
    TextView tvCodeType;
    @BindView(R.id.tv_goods_code)
    TextView tvGoodsCode;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_model)
    TextView tvGoodsModel;
    @Override
    public int setLayoutId() {
        return R.layout.activity_pack_adjust;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

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
    public void scanLibLocatonCode(ScanLocationResult result) {

    }

    @Override
    public void scanMaterialCode(ScanMaterialResult result) {

    }

    @Override
    public void libraryAdjust(LibraryAdjustResult result) {

    }
    @OnClick({R.id.iv_title_detail, R.id.iv_can_location,R.id.tv_scan_location,R.id.tv_material_code, R.id.iv_can_material_code, R.id.btn_confirm_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_detail://查看库位调整的详情
                break;
            case R.id.iv_can_location://扫描库位码
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
            case R.id.tv_material_code://扫描物料码
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
                    ToastUtils.showShort("请扫描物料条码");
                    return;
                }
                String locationCode = tvScanLocation.getText().toString();
                if (TextUtils.isEmpty(locationCode)) {
                    ToastUtils.showShort("请扫描库位码");
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

}
