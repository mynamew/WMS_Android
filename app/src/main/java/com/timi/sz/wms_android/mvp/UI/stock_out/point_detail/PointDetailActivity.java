package com.timi.sz.wms_android.mvp.UI.stock_out.point_detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_POINT_DETAIL_BEAN;

public class PointDetailActivity extends BaseActivity<PointDetailView, PointDetailPresenter> implements PointDetailView {


    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_send_material_num)
    TextView tvSendMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_send_material_lib_name)
    TextView tvSendMaterialLibName;
    @BindView(R.id.tv_other_add_attr)
    TextView tvOtherAddAttr;
    @BindView(R.id.tv_controll_type)
    TextView tvControllType;
    @BindView(R.id.tv_stockout_tip)
    TextView tvStockoutTip;
    @BindView(R.id.et_material_code_input)
    EditText etMaterialCodeInput;
    @BindView(R.id.iv_material_code_scan)
    ImageView ivMaterialCodeScan;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.btn_point_close)
    Button btnPointClose;
    @Override
    public int setLayoutId() {
        return R.layout.activity_point_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        String marterialBeanStr = getIntent().getStringExtra(STOCK_OUT_POINT_DETAIL_BEAN);
        if (!TextUtils.isEmpty(marterialBeanStr)) {
            setTextViewText(tvMaterialCode, R.string.material_code, "WL20160001");
            setTextViewText(tvMaterialName, R.string.material_name, "滑轨双孔梁496-蓝色");
            setTextViewText(tvMaterialModel, R.string.material_code, "Slide Beam0824-496铝挤压加工");
            setTextViewText(tvSendMaterialNum, R.string.send_material_num, "(10) 20 / 50");
            setTextViewText(tvSendMaterialLibName, R.string.send_material_lib_name, "电子料仓");
            setTextViewText(tvOtherAddAttr, R.string.other_add_attr, "无");
            tvControllType.setText("先进先出 - 强制 (10)");
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public PointDetailPresenter createPresenter() {
        return new PointDetailPresenter(this);
    }

    @Override
    public PointDetailView createView() {
        return this;
    }

    @OnClick({R.id.iv_material_code_scan, R.id.btn_point_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_material_code_scan:
                break;
            case R.id.btn_point_close:
                break;
        }
    }

}
