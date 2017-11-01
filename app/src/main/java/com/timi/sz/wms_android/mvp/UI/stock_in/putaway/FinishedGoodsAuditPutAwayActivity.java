package com.timi.sz.wms_android.mvp.UI.stock_in.putaway;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsOrdernoBean;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_LIB_LOATION;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

/**
 * 产成品（根据产成品入库单 入库上架）
 */
public class FinishedGoodsAuditPutAwayActivity extends BaseActivity<PutAwayView, PutAwayPresenter> implements PutAwayView, BaseActivity.ScanQRCodeResultListener {
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
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
    @BindView(R.id.tv_putaway_material_code)
    TextView tvPutawayMaterialCode;
    @BindView(R.id.tv_putaway_material_num)
    TextView tvPutawayMaterialNum;
    @BindView(R.id.tv_putaway_material_name)
    TextView tvPutawayMaterialName;
    @BindView(R.id.tv_putaway_material_nmodel)
    TextView tvPutawayMaterialNmodel;
    private FinishGoodsOrdernoBean mFinishBean;

    @Override
    public int setLayoutId() {
        return R.layout.activity_finished_goods_audit_put_away;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        /**
         * 标题
         */
        setActivityTitle(getString(R.string.finish_goods_in_stock_title));
        mFinishBean = new Gson().fromJson(getIntent().getStringExtra(Constants.IN_STOCK_FINISH_BEAN), FinishGoodsOrdernoBean.class);

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        /**
         * 收货单号
         */
        tvReceiveProNum.setText(mFinishBean.getReceiptCode());

        /**
         * 已入库总数
         */
        tvInStockTotalNum.setText(String.valueOf(mFinishBean.getInstockQty()));
        /**
         * 日期
         */
        tvCreateOrdernoDate.setText(mFinishBean.getReceipDate());
        /**
         * 待点总数
         */
        tvWaitCountNum.setText(String.valueOf(mFinishBean.getWaitQty()));
        /**
         * 已点总数
         */
        tvHaveCountNum.setText(String.valueOf(mFinishBean.getScanQty()));
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

    @Override
    public void vertifyLocationCode(VertifyLocationCodeBean bean) {

    }

    @Override
    public void createInStockOrderno( ) {
            ToastUtils.showShort("生成入库单成功");
            onBackPressed();
    }

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

    /**
     * 库位码
     */
    private String locationCode = "";

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


}
