package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.bean.outstock.MaterialBean;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

public class ScanReturnMaterialActivity extends BaseActivity<ScanReturnMaterialView, ScanReturnMaterialPresenter> implements ScanReturnMaterialView {
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_orderno_date)
    TextView tvOrdernoDate;
    @BindView(R.id.tv_material_from)
    TextView tvMaterialFrom;
    @BindView(R.id.tv_material_buyer)
    TextView tvMaterialBuyer;
    @BindView(R.id.tv_instock_num)
    TextView tvInstockNum;
    @BindView(R.id.tv_buy_num)
    TextView tvBuyNum;
    @BindView(R.id.tv_material_scan_tip)
    TextView tvMaterialScanTip;
    @BindView(R.id.tv_material_scan)
    TextView tvMaterialScan;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_have_return_num)
    TextView tvHaveReturnNum;

    @Override
    public int setLayoutId() {
        return R.layout.activity_scan_return_material;
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
        MaterialBean bean = getIntent().getParcelableExtra("MaterialBean");
        /**
         * 上个界面传过来的数据
         */
        setTextViewText(tvOrderno,R.string.order_no, "P123432");
        setTextViewText(tvOrdernoDate,R.string.buy_date, "P123432");
        setTextViewText(tvMaterialFrom,R.string.order_no, "P123432");
        setTextViewText(tvMaterialBuyer,R.string.order_no, "P123432");
        setTextViewText(tvMaterialBuyer,R.string.order_no, "P123432");
        setTextViewText(tvBuyNum,R.string.order_no, "P123432");
        setTextViewText(tvInstockNum,R.string.buy_num, "0");
        /**
         * 扫码出来的数据
         */
        setTextViewText(tvMaterialCode,R.string.material_code,bean.getMaterialCode());
        setTextViewText(tvMaterialName,R.string.material_name,bean.getMaterialName());
        setTextViewText(tvMaterialModel,R.string.material_model,bean.getMaterialModel());
    }

    @Override
    public ScanReturnMaterialPresenter createPresenter() {
        return new ScanReturnMaterialPresenter(this);
    }

    @Override
    public ScanReturnMaterialView createView() {
        return this;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_SCAN_CODE_MATERIIAL:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        LogUitls.d("物料码扫码--->", bundle.getString("result"));
                        /**
                         * 设置扫描返回结果
                         */
                        tvMaterialScan.setText(bundle.getString("result"));
                        // TODO: 2017/8/29 进行网络请求 扫描结果进行保存 对物料信息保存到上面的textview上
                        /**
                         * 设置已退数量
                         */
                        tvHaveReturnNum.setText(String.format(getString(R.string.have_return_num)+ "1"));
                    }
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_material_scan, R.id.iv_material_scan, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_material_scan:
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION);
                break;
            case R.id.iv_material_scan:
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION);
                break;
            case R.id.btn_commit://退料出库扫描 提交清点
                break;
        }
    }
}
