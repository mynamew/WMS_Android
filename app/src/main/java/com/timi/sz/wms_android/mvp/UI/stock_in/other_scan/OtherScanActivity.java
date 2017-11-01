package com.timi.sz.wms_android.mvp.UI.stock_in.other_scan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
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
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_LIB_LOATION;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

public class OtherScanActivity extends BaseActivity<OtherScanView, OtherScanPresenter> implements OtherScanView, BaseActivity.ScanQRCodeResultListener {
    @BindView(R.id.tv_have_scan_num)
    TextView tvHaveScanNum;
    @BindView(R.id.tv_scan_location_tip)
    TextView tvScanLocationTip;
    @BindView(R.id.tv_scan_location)
    TextView tvScanLocation;
    @BindView(R.id.iv_can_location)
    ImageView ivCanLocation;
    @BindView(R.id.tv_can_material_tip)
    TextView tvCanMaterialTip;
    @BindView(R.id.tv_scan_material)
    TextView tvScanMaterial;
    @BindView(R.id.iv_putaway_scan_material)
    ImageView ivPutawayScanMaterial;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_num)
    TextView tvMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_nmodel)
    TextView tvMaterialNmodel;
    /**
     * 库位码
     */
    private String locationCode = "";
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
        setActivityTitle("其他入库—扫描入库");
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

    }

    @Override
    public void initData() {
        setTextViewText(tvMaterialCode, R.string.material_code, "");
        setTextViewText(tvMaterialName, R.string.material_name, "");
        setTextViewText(tvMaterialNmodel, R.string.material_model, "");
        setTextViewText(tvMaterialNum, R.string.material_num, "");
    }

    @Override
    public OtherScanPresenter createPresenter() {
        return new OtherScanPresenter(this);
    }

    @Override
    public OtherScanView createView() {
        return this;
    }


    @OnClick({R.id.btn_create_other_instock_bill, R.id.tv_scan_location, R.id.iv_can_location, R.id.tv_scan_material, R.id.iv_putaway_scan_material})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_scan_location:
            case R.id.iv_can_location:
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, this);
                break;
            case R.id.tv_scan_material:
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
                String materialCode = tvScanMaterial.getText().toString();
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
                break;
        }
    }

    @Override
    public void materialScanResult(MaterialScanPutAwayBean bean) {
        ToastUtils.showShort(getString(R.string.material_scan_putaway_success));
        /**
         * 扫码出来的数据
         */
        setTextViewText(tvMaterialCode, R.string.material_code, bean.getMaterialCode());
        setTextViewText(tvMaterialName, R.string.material_name, bean.getMaterialName());
        setTextViewText(tvMaterialNmodel, R.string.material_model, bean.getMaterialModel());
        setTextViewText(tvMaterialNum, R.string.material_num, bean.getMaterialBuyNum());
        /**
         * 已扫的总数
         */
        int haveScanNum = Integer.parseInt(tvHaveScanNum.getText().toString()) + 1;
        tvHaveScanNum.setText(haveScanNum + "");
    }

    @Override
    public void vertifyLocationCode(VertifyLocationCodeBean bean) {

    }

    @Override
    public void createInStockOrderno() {
        ToastUtils.showShort("生成入库单成功");
        //如果成功  入库单号存储起来
        onBackPressed();
    }

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
                tvScanMaterial.setText(result);
                /**
                 * 物料扫码并上架的网络请求
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillNo", result);
                getPresenter().materialScanNetWork(params);
                break;
            case REQUEST_SCAN_CODE_LIB_LOATION:
                LogUitls.d("库位码扫码--->", result);
                //保存库位码
                locationCode = result;
                //设置库位码
                tvScanLocation.setText(locationCode);
                Map<String, Object> params1 = new HashMap<>();
                params1.put("UserId", SpUtils.getInstance().getUserId());
                params1.put("OrgId", SpUtils.getInstance().getOrgId());
                params1.put("MAC", PackageUtils.getMac());
                params1.put("BillNo", result);
                //判断库位码是否有效
                getPresenter().vertifyLocationCode(params1);
                break;
        }
    }
}
