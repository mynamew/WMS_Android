package com.timi.sz.wms_android.mvp.UI.quity.reject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.BarcodeData;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.UI.stock_in.query.SearchBuyOrderActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

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


    //bundle
    private NormalQualityData mData;
    private int rejectNum;
    /**
     * 条码信息
     */
    private BarcodeData mBarData;

    /**
     * 包装码
     */
    private String barcodeStr;

    @Override
    public int setLayoutId() {
        return R.layout.activity_quality_reject;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        //bundle
        mData = new Gson().fromJson(getIntent().getStringExtra("mData"), NormalQualityData.class);
        rejectNum = getIntent().getIntExtra("rejectNum", 0);
        setActivityTitle(getString(R.string.quality_reject_title));
    }

    @Override
    public void initView() {
        etMinPackCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(QualityRejectActivity.this);
                    String orderNum = etMinPackCode.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort(getString(R.string.please_scan_assign_material_code));
                    }
                    if (orderNum.length() < 4) {
                        ToastUtils.showShort(getString(R.string.input_orderno_more_four));
                    } else {
                        barcodeStr = etMinPackCode.getText().toString();
                        /**
                         * 质检确认
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("mac", PackageUtils.getMac());
                        params.put("ReceiptId", mData.getNormalSummary().getReceiptId());
                        params.put("ReceiptDetailId", mData.getNormalSummary().getReceiptDetailId());
                        params.put("RejectQty", rejectNum);
                        params.put("BarcodeNo", barcodeStr);
                        getPresenter().submitFinish(params);
                    }
                }
                return false;
            }
        });
        llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 弹出设置拒收数量的dialog
                 */
                final MyDialog myDialog = new MyDialog(QualityRejectActivity.this, R.layout.dialog_quality_reject);
                NormalQualityData.NormalSummaryBean normalSummary = mData.getNormalSummary();
                /**
                 * 来源单号
                 */
                setTextViewText(myDialog.getTextView(R.id.tv_from_orderno), R.string.dialog_from_orderno, normalSummary.getReceiptCode());
                /**
                 * 制单日期
                 */
                setTextViewText(myDialog.getTextView(R.id.tv_create_orderno_date), R.string.create_orderno_date, normalSummary.getReceiptDate());
                /**
                 * 供应商
                 */
                setTextViewText(myDialog.getTextView(R.id.tv_supplier), R.string.buy_from, normalSummary.getSupplierName());
                /**
                 * 物料编码
                 */
                setTextViewText(myDialog.getTextView(R.id.tv_material_code), R.string.material_code, normalSummary.getMaterialCode());
                /**
                 * 物料名称
                 */
                setTextViewText(myDialog.getTextView(R.id.tv_material_name), R.string.material_name, normalSummary.getMaterialName());
                /**
                 * 附加属性
                 */
                setTextViewText(myDialog.getTextView(R.id.tv_material_attr), R.string.material_attr, (TextUtils.isEmpty(normalSummary.getMaterialStandard()) ? "无" : normalSummary.getMaterialStandard()));
                /**
                 * 包装数
                 */
                setTextViewText(myDialog.getTextView(R.id.tv_pack_num), R.string.pack_num, mBarData.getInitialQty());

                myDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        String rejectNumStr=myDialog.getEdittext(R.id.et_reject_num).getText().toString();
                        if(TextUtils.isEmpty(rejectNumStr)){
                            ToastUtils.showShort(getString(R.string.please_input_reject_num));
                            return;
                        }
                        /**
                         *提交质检拒收
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("mac", PackageUtils.getMac());
                        params.put("ReceiptId", mData.getNormalSummary().getReceiptId());
                        params.put("RejectQty", Integer.parseInt(rejectNumStr));
                        params.put("BarcodeNo", barcodeStr);
                        params.put("ReceiptDetailId", mData.getNormalSummary().getReceiptDetailId());
                        getPresenter().setBarcodeData(params);
                    }
                });
                myDialog.setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });
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
        mBarData = data;
        /**
         * 设置数据
         */
        llContent.setVisibility(View.VISIBLE);
        tvMaterialCode.setText(result);
        tvFirstPackNum.setText(String.valueOf(data.getInitialQty()));
        tvRealPackNum.setText(String.valueOf(data.getCurrentQty()));
        tvRejectNum.setText(String.valueOf(data.getRejectQty()));
    }

    @Override
    public void setBarcodeData(BarcodeData data) {

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
                params.put("ReceiptId", mData.getNormalSummary().getReceiptId());
                params.put("ReceiptDetailId", mData.getNormalSummary().getReceiptDetailId());
                getPresenter().submitFinish(params);
                break;
            case R.id.iv_scan://扫描包装码
                scan(Constants.REQUEST_SCAN_CODE_BARCODE, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        /**
                         * 设置条码
                         */
                        barcodeStr = result;
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
                        params.put("ReceiptId", mData.getNormalSummary().getReceiptId());
                        params.put("ReceiptDetailId", mData.getNormalSummary().getReceiptDetailId());
                        params.put("RejectQty", rejectNum);
                        params.put("BarcodeNo", result);
                        getPresenter().getBarcodeData(params, result);
                    }
                });
                break;
        }
    }
}
