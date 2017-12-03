package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_detail.FormChangeDetailActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    public int setLayoutId() {
        return R.layout.activity_form_change_outstock;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.outstock_scan_form_change));
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
                break;
        }
    }
}
