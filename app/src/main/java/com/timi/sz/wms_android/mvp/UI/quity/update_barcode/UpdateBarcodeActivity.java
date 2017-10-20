package com.timi.sz.wms_android.mvp.UI.quity.update_barcode;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.bean.quality.BarcodeData;
import com.timi.sz.wms_android.mvp.UI.quity.reject.QualityRejectPresenter;
import com.timi.sz.wms_android.mvp.UI.quity.reject.QualityRejectView;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateBarcodeActivity extends BaseActivity<QualityRejectView, QualityRejectPresenter> implements QualityRejectView {


    @BindView(R.id.tv_min_pack_code)
    TextView tvMinPackCode;
    @BindView(R.id.et_barcode)
    EditText etBarcode;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.ll_min_pack_code)
    RelativeLayout llMinPackCode;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_first_pack_num)
    TextView tvFirstPackNum;
    @BindView(R.id.tv_real_pack_num)
    TextView tvRealPackNum;
    @BindView(R.id.tv_reject_num)
    TextView tvRejectNum;
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    @Override
    public int setLayoutId() {
        return R.layout.activity_update_barcode2;
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
    public QualityRejectPresenter createPresenter() {
        return new QualityRejectPresenter(this);
    }

    @Override
    public QualityRejectView createView() {
        return this;
    }

    @Override
    public void getBarcodeData(BarcodeData data, String result) {

    }

    @Override
    public void setBarcodeData(BarcodeData data) {

    }

    @Override
    public void submitFinish() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
