package com.timi.sz.wms_android.mvp.UI.quity.reject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.BarcodeData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 质检拒收
 */
public class QualityRejectActivity extends BaseActivity<QualityRejectView, QualityRejectPresenter> implements QualityRejectView {
    @BindView(R.id.et_min_pack_code)
    EditText etMinPackCode;
    @BindView(R.id.tv_quality_complete)
    TextView tvQualityComplete;
    @BindView(R.id.tv_min_pack_code)
    TextView tvMinPackCode;
    @BindView(R.id.ll_min_pack_code)
    LinearLayout llMinPackCode;
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


    //bundle
    private int receiptId;
    private int receiptDetailId;
    private int rejectNum;


    @Override
    public int setLayoutId() {
        return R.layout.activity_quality_reject;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        //bundle
        receiptId = getIntent().getIntExtra("ReceiptId", -1);
        receiptDetailId = getIntent().getIntExtra("ReceiptDetailId", -1);
        rejectNum = getIntent().getIntExtra("rejectNum", -1);
        setActivityTitle(getString(R.string.quality_reject_title));
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
        llContent.setVisibility(View.VISIBLE);
        tvMaterialCode.setText(result);
        tvFirstPackNum.setText(String.valueOf(data.getInitialQty()));
        tvRealPackNum.setText(String.valueOf(data.getCurrentQty()));
        tvRejectNum.setText(String.valueOf(data.getRejectQty()));
    }

    @Override
    public void submitFinish() {
        BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_SUCCESS));
        BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_CONFRIM));
        onBackPressed();
    }


    @OnClick({R.id.tv_quality_complete, R.id.iv_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_quality_complete://质检完成
                String barCode = tvMaterialCode.getText().toString();
                if (TextUtils.isEmpty(barCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_assign_material_code));
                    return;
                }
                /**
                 * 质检确认
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("mac", PackageUtils.getMac());
                params.put("ReceiptId", receiptId);
                params.put("ReceiptDetailId", receiptDetailId);
                getPresenter().submitFinish(params);
                break;
            case R.id.iv_scan://扫描包装码
                scan(Constants.REQUEST_SCAN_CODE_BARCODE, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        /**
                         * 设置包装码
                         */
                        etMinPackCode.setText(result);
                        /**
                         * 设置拒收数量 获取信息
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("mac", PackageUtils.getMac());
                        params.put("ReceiptId", receiptId);
                        params.put("ReceiptDetailId", receiptDetailId);
                        params.put("RejectQty", rejectNum);
                        params.put("BarcodeNo", result);
                        getPresenter().getBarcodeData(params, result);
                    }
                });
                break;
        }
    }
}
