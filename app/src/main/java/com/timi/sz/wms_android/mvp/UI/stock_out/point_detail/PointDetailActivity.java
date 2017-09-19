package com.timi.sz.wms_android.mvp.UI.stock_out.point_detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.mvp.UI.stock_out.divide_print.SplitPrintActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.query.StockOutSearchActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.CommonScanActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE;
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

        /**
         *  输入框
         */
        etMaterialCodeInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(PointDetailActivity.this);
                    String orderNum = etMaterialCodeInput.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort("请输入单号");
                    }
                    /**
                     * 发起请求
                     */
                    startActivity(new Intent(PointDetailActivity.this, SplitPrintActivity.class));
                }
                return false;
            }
        });
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
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 60);
                } else {
                    //权限已经被授予，在这里直接写要执行的相应方法即可
                    Intent intent = new Intent(this, CommonScanActivity.class);

                    String pointMsg = getResources().getString(R.string.scan_point_title);
                    Bundle bundle = new Bundle();
                    bundle.putString("pointMsg", pointMsg);
                    intent.putExtras(bundle);

                    intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
            case R.id.btn_point_close:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        etMaterialCodeInput.setText(bundle.getString("result"));
                        // TODO: 2017/9/18 发送请求
                    }
                }
                break;
        }
    }
}
