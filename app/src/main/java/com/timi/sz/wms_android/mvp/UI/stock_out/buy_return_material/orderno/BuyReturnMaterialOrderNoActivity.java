package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.MaterialBean;
import com.timi.sz.wms_android.bean.outstock.buy.OrderNoBean;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_BUY_RETURN_ORDERNO_BEAN;

/**
 * 退料单
 * author: timi
 * create at: 2017/9/1 15:40
 */
public class BuyReturnMaterialOrderNoActivity extends BaseActivity<BuyReturnMaterialOrderNoView, BuyReturnMaterialOrderNoPresenter> implements BuyReturnMaterialOrderNoView, BaseActivity.ScanQRCodeResultListener {

    @BindView(R.id.tv_return_orderno)
    TextView tvReturnOrderno;
    @BindView(R.id.tv_create_orderno_date)
    TextView tvCreateOrdernoDate;
    @BindView(R.id.tv_return_material_total_num)
    TextView tvReturnMaterialTotalNum;
    @BindView(R.id.tv_have_scan_total_num)
    TextView tvHaveScanTotalNum;
    @BindView(R.id.tv_wait_scan_total_num)
    TextView tvWaitScanTotalNum;
    @BindView(R.id.tv_putaway_scan_material_tip)
    TextView tvPutawayScanMaterialTip;
    @BindView(R.id.et_material_scan)
    EditText etMaterialScan;
    @BindView(R.id.iv_material_scan)
    ImageView ivMaterialScan;
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
    @BindView(R.id.btn_commit_check)
    Button btnCommitCheck;
    private OrderNoBean orderBoBean = null;

    @Override
    public int setLayoutId() {
        return R.layout.activity_return_material_order_no;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.buy_return_material_scan));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        orderBoBean = getIntent().getParcelableExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN);
        if (null != orderBoBean) {
            tvReturnOrderno.setText(String.format(getString(R.string.order_no), orderBoBean.getOrderno()));
            tvCreateOrdernoDate.setText(String.format(getString(R.string.orderno_date), orderBoBean.getDate()));
            tvHaveScanTotalNum.setText(String.format(getString(R.string.have_scan_num) + orderBoBean.getReturnHaveScanNum()));
            tvWaitScanTotalNum.setText(String.format(getString(R.string.wait_scan_num) + orderBoBean.getReturnWaitScanlNum()));
        }
    }

    @Override
    public BuyReturnMaterialOrderNoPresenter createPresenter() {
        return new BuyReturnMaterialOrderNoPresenter(this);
    }

    @Override
    public BuyReturnMaterialOrderNoView createView() {
        return this;
    }
    @OnClick({R.id.tv_material_scan, R.id.iv_material_scan, R.id.btn_commit_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_material_scan:
                /**
                 * 扫码
                 */
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
            case R.id.iv_material_scan:
                /**
                 * 扫码
                 */
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
            case R.id.btn_commit_check:
                if (Integer.parseInt(orderBoBean.getReturnHaveScanNum()) == Integer.parseInt(orderBoBean.getReturnTotalNum())) {
                    // TODO: 2017/8/29 需要斟酌文字
                    ToastUtils.showShort("已扫描完所有的物料");
                    return;
                }
                /**
                 * 网络请求 请求审核
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                getPresenter().submitBarcodePurReturn(params);
                break;
        }
    }

    /**
     * 扫描的返回
     *
     * @param requestCode
     * @param result
     */
    @Override
    public void scanSuccess(int requestCode, String result) {
        LogUitls.e("物料码扫码--->", result);
        /**
         * 设置扫描返回结果
         */
        etMaterialScan.setText(result);
        /**
         * 扫物料码的网络请求
         */
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());

        getPresenter().materialScan(params);
    }

    @Override
    public void materialScan(SubmitBarcodeOutAuditData bean) {

    }

    @Override
    public void submitBarcodePurReturn(SubmitBarcodePurReturnData bean) {

    }
}
