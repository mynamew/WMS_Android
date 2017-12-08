package com.timi.sz.wms_android.mvp.UI.quity.update_barcode;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.update_barcode.BarEditGetUnInstockBarcodeData;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_ORDERNO;

/**
  * 条码更改
  * author: timi    
  * create at: 2017/11/29 14:58
  */  
public class UpdateBarcodeActivity extends BaseActivity<UpdateBarcodeView, UpdateBarcodePresenter> implements UpdateBarcodeView {


    @BindView(R.id.tv_min_pack_code)
    TextView tvMinPackCode;
    @BindView(R.id.et_min_pack_code)
    EditText etMinPackCode;
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
    /**
     * 当前的条码
     */
    private String currentBarCode = "";

    @Override
    public int setLayoutId() {
        return R.layout.activity_update_barcode2;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.update_barcode_title));

    }

    @Override
    public void initView() {
        /**
         * 设置输入框的监听
         */
        setEdittextListener(etMinPackCode, EDITTEXT_ORDERNO, R.string.please_input_or_scan_need_update_barcode, R.string.input_orderno_more_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 发起请求
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BarcodeNo", result);
                getPresenter().barEditGetUnInstockBarcodeData(params, result);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public UpdateBarcodePresenter createPresenter() {
        return new UpdateBarcodePresenter(this);
    }

    @Override
    public UpdateBarcodeView createView() {
        return this;
    }


    @Override
    public void barEditGetUnInstockBarcodeData(BarEditGetUnInstockBarcodeData data, String result) {
        llContent.setVisibility(View.VISIBLE);
        tvMaterialCode.setText(data.getMaterialCode());
        tvFirstPackNum.setText(String.valueOf(data.getInitialQty()));
        tvRealPackNum.setText(String.valueOf(data.getCurrentQty()));
        tvRejectNum.setText(String.valueOf(data.getPackQty() - data.getCurrentQty()));
        initUpdateBarcodeDialog(data, result);
    }

    @Override
    public void barEditSetUnInstockBarcodeData(int packQty, int rejectNum) {
        ToastUtils.showShort(getString(R.string.update_barcode_success));
        tvRealPackNum.setText(String.valueOf(packQty));
        tvRejectNum.setText(String.valueOf(rejectNum));
    }

    @OnClick({R.id.ll_content, R.id.iv_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_content:
                updateBarcodeDialog.show();
                break;
            case R.id.iv_scan:
                scan(Constants.REQUEST_SCAN_CODE_BARCODE, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etMinPackCode.setText(result);
                        /**
                         *  发起请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BarcodeNo", result);
                        getPresenter().barEditGetUnInstockBarcodeData(params, result);
                    }
                });
                break;
        }
    }

    private MyDialog updateBarcodeDialog;

    /**
     * 显示更改条码的弹框
     *
     * @param data
     * @param result
     */
    private void initUpdateBarcodeDialog(final BarEditGetUnInstockBarcodeData data, final String result) {
        if (null == updateBarcodeDialog) {
            updateBarcodeDialog = new MyDialog(this, R.layout.dialog_update_barcode);
            setTextViewText(updateBarcodeDialog.getTextView(R.id.tv_from_orderno), R.string.dialog_from_orderno, data.getSourceBillCode());
            setTextViewText(updateBarcodeDialog.getTextView(R.id.tv_material_code), R.string.material_code, data.getMaterialCode());
            setTextViewText(updateBarcodeDialog.getTextView(R.id.tv_material_name), R.string.material_name, data.getMaterialName());
            setTextViewText(updateBarcodeDialog.getTextView(R.id.tv_material_model), R.string.material_model, data.getMaterialStandard());
            setTextViewText(updateBarcodeDialog.getTextView(R.id.tv_real_pack_num), R.string.real_pack_num, data.getCurrentQty());
            setTextViewText(updateBarcodeDialog.getTextView(R.id.tv_material_attr), R.string.material_attr, TextUtils.isEmpty(data.getMaterialAttribute()) ? getString(R.string.none) : data.getMaterialAttribute());
            updateBarcodeDialog.setViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                }
            });
            updateBarcodeDialog.setViewListener(R.id.btn_cancel, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                }
            });
            updateBarcodeDialog.setButtonListener(R.id.btn_commit, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    String rejectNumStr = updateBarcodeDialog.getEdittext(R.id.et_reject_num).getText().toString().trim();
                    /**
                     * 请输入数量
                     */
                    if (TextUtils.isEmpty(rejectNumStr)) {
                        ToastUtils.showShort(getString(R.string.please_input_reject_num));
                        return;
                    }
                    /**
                     * 如果拒收数量大于实际包装数
                     */
                    if (Integer.parseInt(rejectNumStr) > data.getCurrentQty()) {
                        ToastUtils.showShort(getString(R.string.reject_num_more_current_pack_num));
                        return;
                    }
                    dialog.dismiss();
                    /**
                     * 发起请求
                     */
                    Map<String, Object> params = new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("MAC", PackageUtils.getMac());
                    params.put("BarcodeNo", result);
                    params.put("UpdateQty", Integer.parseInt(rejectNumStr));
                    getPresenter().barEditSetUnInstockBarcodeData(params, data.getPackQty(), Integer.parseInt(rejectNumStr));
                }
            });
        }
    }
}
