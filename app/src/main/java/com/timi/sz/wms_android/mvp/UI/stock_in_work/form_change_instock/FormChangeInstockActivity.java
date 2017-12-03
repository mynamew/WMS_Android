package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_instock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_detail.FormChangeDetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock.FormChangeOutstockActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 形态转换 入库上架
 * author: timi
 * create at: 2017/12/1 9:01
 */
public class FormChangeInstockActivity extends BaseActivity<FormChangeInstockView, FormChangeInstockPresenter> implements FormChangeInstockView {


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
    @BindView(R.id.tv_putaway_scan_location_tip)
    TextView tvPutawayScanLocationTip;
    @BindView(R.id.et_scan_location)
    EditText etScanLocation;
    @BindView(R.id.iv_scan_location)
    ImageView ivScanLocation;
    @BindView(R.id.tv_material_code_tip)
    TextView tvMaterialCodeTip;
    @BindView(R.id.et_material_code)
    EditText etMaterialCode;
    @BindView(R.id.iv_can_material_code)
    ImageView ivCanMaterialCode;
    @BindView(R.id.tv_material_num)
    TextView tvMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;

    @Override
    public int setLayoutId() {
        return R.layout.activity_form_change_instock;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.instock_putaway_form_change));
    }

    @Override
    public void initView() {
        /**
         * 设置查看详情的点击事件
         */
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FormChangeInstockActivity.this, FormChangeDetailActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public FormChangeInstockPresenter createPresenter() {
        return new FormChangeInstockPresenter(this);
    }

    @Override
    public FormChangeInstockView createView() {
        return this;
    }



    @OnClick({R.id.btn_commit, R.id.iv_scan_location, R.id.iv_can_material_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                break;
            case R.id.iv_scan_location:
                break;
            case R.id.iv_can_material_code:
                break;
        }
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
}
