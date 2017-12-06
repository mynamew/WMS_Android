package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_detail.FormChangeDetailActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 形态转换出库
 * author: timi
 * create at: 2017/12/1 8:34
 */
public class FormChangeOutstockActivity extends BaseActivity<FormChangeOutstockView, FormChangeOutstockPresenter> implements FormChangeOutstockView {

    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_outstock_total_num)
    TextView tvOutstockTotalNum;
    @BindView(R.id.tv_wait_point_num)
    TextView tvWaitPointNum;
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.tv_scan_material_tip)
    TextView tvScanMaterialTip;
    @BindView(R.id.et_scan_material)
    EditText etScanMaterial;
    @BindView(R.id.iv_scan_material)
    ImageView ivScanMaterial;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_num)
    TextView tvMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_nmodel)
    TextView tvMaterialNmodel;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.ll_material_info)
    LinearLayout llMaterialInfo;

    private FormChangeOutResult formChangeOutResult;
    private int intentCode;
    private int ScanId;
    @Override
    public int setLayoutId() {
        return R.layout.activity_form_change_outstock;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.outstock_scan_form_change));
        formChangeOutResult=new Gson().fromJson(getIntent().getStringExtra(Constants.STOCK_IN_WORK_BEAN),FormChangeOutResult.class);
        intentCode=getIntent().getIntExtra(Constants.STOCK_IN_WORK_CODE_STR,0);
        ScanId=formChangeOutResult.getScanId();
    }

    @Override
    public void initView() {
        /**
         * 设置查看详情的点击事件
         */
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FormChangeOutstockActivity.this, FormChangeDetailActivity.class);
                startActivity(it);
            }
        });
        etScanMaterial.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(FormChangeOutstockActivity.this);
                    String result = etScanMaterial.getText().toString().trim();
                    if (TextUtils.isEmpty(result)) {
                        ToastUtils.showShort(getString(R.string.please_scan_material_code));
                    }
                    /**
                     * 物料扫码并上架的网络请求
                     */
                    Map<String, Object> params1 = new HashMap<>();
                    params1.put("UserId", SpUtils.getInstance().getUserId());
                    params1.put("OrgId", SpUtils.getInstance().getOrgId());
                    params1.put("MAC", PackageUtils.getMac());
                    params1.put("MAC", PackageUtils.getMac());
                    params1.put("MAC", PackageUtils.getMac());
                    params1.put("MAC", PackageUtils.getMac());
                    params1.put("MAC", PackageUtils.getMac());
                    params1.put("BillNo", result);
                    getPresenter().materialScanPutAawy(params1);
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public FormChangeOutstockPresenter createPresenter() {
        return new FormChangeOutstockPresenter(this);
    }

    @Override
    public FormChangeOutstockView createView() {
        return this;
    }

    /**
     * 设置 头部的信息
     *
     * @param orderno
     * @param date
     * @param outStockNum
     * @param waitCountNum
     * @param haveCountNum
     */
    public void setHeadContent(String orderno, String date, int outStockNum, int waitCountNum, int haveCountNum) {
        tvOrderno.setText(orderno);
        tvDate.setText(date);
        tvOutstockTotalNum.setText(String.valueOf(outStockNum));
        tvWaitPointNum.setText(String.valueOf(waitCountNum));
        tvHaveCountNum.setText(String.valueOf(haveCountNum));
    }

    @OnClick({R.id.btn_commit, R.id.iv_scan_material})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                break;
            case R.id.iv_scan_material:
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etScanMaterial.setText(result);
                        /**
                         * 物料扫码并上架的网络请求
                         */
                        Map<String, Object> params1 = new HashMap<>();
                        params1.put("UserId", SpUtils.getInstance().getUserId());
                        params1.put("OrgId", SpUtils.getInstance().getOrgId());
                        params1.put("MAC", PackageUtils.getMac());
                        params1.put("BillId", formChangeOutResult.getBillId());
                    }
                });
                break;
        }
    }


    @Override
    public void materialScanPutAawy(MaterialScanPutAwayBean bean) {

    }

    @Override
    public void createInStockOrderno() {

    }
}
