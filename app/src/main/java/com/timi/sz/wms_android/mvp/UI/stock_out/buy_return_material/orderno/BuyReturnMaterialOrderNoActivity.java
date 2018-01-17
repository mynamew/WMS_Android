package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.mvp.UI.stock_in.detail.StockInDetailActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_BUY_RETURN_ORDERNO_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;

/**
 * 通过退料单单号  查询获取到的退料单信息，  进行退料操作
 * author: timi
 * create at: 2017/9/1 15:40
 */
public class BuyReturnMaterialOrderNoActivity extends BaseActivity<BuyReturnMaterialOrderNoView, BuyReturnMaterialOrderNoPresenter> implements BuyReturnMaterialOrderNoView, BaseActivity.ScanQRCodeResultListener {


    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_return_orderno)
    TextView tvReturnOrderno;
    @BindView(R.id.tv_create_orderno_date)
    TextView tvCreateOrdernoDate;
    @BindView(R.id.tv_return_material_total_num)
    TextView tvReturnMaterialTotalNum;
    @BindView(R.id.tv_have_scan_total_num)
    TextView tvHaveScanTotalNum;
    @BindView(R.id.tv_wait_scan_total_num)
    TextView tvWaitScanTotalNum;
    @BindView(R.id.tv_putaway_scan_material_tip)
    TextView tvPutawayScanMaterialTip;
    @BindView(R.id.et_material_scan)
    EditText etMaterialScan;
    @BindView(R.id.iv_material_scan)
    ImageView ivMaterialScan;
    @BindView(R.id.tv_return_num)
    TextView tvReturnNum;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.btn_commit_check)
    Button btnCommitCheck;
    @BindView(R.id.tv_supplier)
    TextView tvSupplier;
    @BindView(R.id.tv_buyer)
    TextView tvBuyer;
    private BuyReturnMaterialByOrdernoData orderBoBean = null;
    private int intentCode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_return_material_order_no;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.buy_return_material_scan));
        intentCode = getIntent().getIntExtra(Constants.STOCK_OUT_CODE_STR, -1);
    }

    @Override
    public void initView() {
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 查看详情
                 */
                Intent it = new Intent(BuyReturnMaterialOrderNoActivity.this, StockInDetailActivity.class);
                it.putExtra(Constants.STOCKIN_BILLID, orderBoBean.getPurReturnId());
                it.putExtra(STOCK_OUT_CODE_STR, intentCode);
                startActivity(it);
            }
        });
        setEdittextListener(etMaterialScan, Constants.REQUEST_SCAN_CODE_MATERIIAL, R.string.please_input_return_matrial_code_or_scan, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                if (orderBoBean.getWaitQty() == 0) {
                    ToastUtils.showShort(getString(R.string.have_scan_all_materail));
                    setBarcodeSelect();
                } else {
                    /**
                     * 扫物料码的网络请求
                     */
                    Map<String, Object> params = new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("MAC", PackageUtils.getMac());
                    params.put("BillId", orderBoBean.getPurReturnId());
                    params.put("SrcBillType", 15);
                    params.put("DestBillType", 15);
                    params.put("BarcodeNo", result);
                    params.put("ScanId", orderBoBean.getScanId());
                    getPresenter().submitBarcodeOutAudit(params);
                }
            }
        });

    }

    @Override
    public void initData() {
        orderBoBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN), BuyReturnMaterialByOrdernoData.class);
        if (null != orderBoBean) {
            tvReturnOrderno.setText(orderBoBean.getPurReturnCode());
            tvCreateOrdernoDate.setText(orderBoBean.getPurReturnDate());
            tvHaveScanTotalNum.setText(String.valueOf(orderBoBean.getScanQty()));
            tvWaitScanTotalNum.setText(String.valueOf(orderBoBean.getWaitQty()));
            tvReturnMaterialTotalNum.setText(String.valueOf(orderBoBean.getPurReturnQty()));
            tvSupplier.setText(orderBoBean.getSupplierName());
            tvBuyer.setText(orderBoBean.getCreaterName());
        }
    }

    @Override
    public BuyReturnMaterialOrderNoPresenter createPresenter() {
        return new BuyReturnMaterialOrderNoPresenter(this);
    }

    @Override
    public BuyReturnMaterialOrderNoView createView() {
        return this;
    }

    @OnClick({R.id.iv_material_scan, R.id.btn_commit_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_material_scan:
                if (orderBoBean.getWaitQty() == 0) {
                    ToastUtils.showShort(getString(R.string.have_scan_all_materail));
                    return;
                }
                /**
                 * 扫码
                 */
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
            case R.id.btn_commit_check:
                /**
                 * 网络请求 请求审核
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("ScanId", orderBoBean.getScanId());
                params.put("SubmitType", 1);
                getPresenter().submitMakeOrAuditBill(params);
                break;
        }
    }

    /**
     * 扫描的返回
     *
     * @param requestCode
     * @param result
     */
    @Override
    public void scanSuccess(int requestCode, String result) {
        LogUitls.e("物料码扫码--->", result);
        /**
         * 设置扫描返回结果
         */
        etMaterialScan.setText(result);
        /**
         * 扫物料码的网络请求
         */
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillId", orderBoBean.getPurReturnId());
        params.put("SrcBillType", 15);
        params.put("DestBillType", 15);
        params.put("ScanId", orderBoBean.getScanId());
        params.put("BarcodeNo", result);
        getPresenter().submitBarcodeOutAudit(params);
    }

    @Override
    public void submitBarcodeOutAudit(SubmitBarcodeOutAuditData data) {
        orderBoBean.setScanId(data.getScanId());
        tvMaterialName.setText(data.getMaterialName());
        tvMaterialCode.setText(data.getMaterialCode());
        tvMaterialAttr.setText(TextUtils.isEmpty(data.getMaterialAttribute()) ? getString(R.string.none) : data.getMaterialAttribute());
        tvMaterialModel.setText(data.getMaterialStandard());

        orderBoBean.setScanId(data.getScanId());
        /***
         * 设置退了数量，  包含三个数字， （10）20/38
         *  1、 10 ：当前扫码的物料的数量
         *  2、 20：当前物料所需要退料的数量
         *  3、38：退料的总数量
         */

        /**
         * 如果此数量大于0，表示数量超出，PDA要跳到拆分界面进行条码拆分。
         */
        if (data.getExceedQty() > 0) {
            showDivideBarcodeDialog(data);
        } else {
            setTextViewContent(tvHaveScanTotalNum, data.getTotalScanQty());
            setTextViewContent(tvWaitScanTotalNum, orderBoBean.getPurReturnQty() - data.getTotalScanQty());
            tvReturnNum.setText("(" + data.getBarcodeQty() + ")" + data.getTotalScanQty() + "/" + orderBoBean.getPurReturnQty());
        }
    }

    /**
     * 显示条码拆分的弹出框
     */
    private MyDialog divideBarcodeDialog;

    private void showDivideBarcodeDialog(SubmitBarcodeOutAuditData data) {
        if (null == divideBarcodeDialog) {
            divideBarcodeDialog = new MyDialog(this, R.layout.dialog_divide_barcode);
            divideBarcodeDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                    int SplitQty = Integer.parseInt(divideBarcodeDialog.getEdittext(R.id.et_divide_num).getText().toString());
                    String BarcodeNo = etMaterialScan.getText().toString().trim();
                    Map<String, Object> params = new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("MAC", PackageUtils.getMac());
                    params.put("BillId", orderBoBean.getPurReturnId());
                    params.put("SrcBillType", 15);
                    params.put("DestBillType", 15);
                    params.put("ScanId", orderBoBean.getScanId());
                    params.put("BarcodeNo", BarcodeNo);
                    params.put("SplitQty", SplitQty);
                    getPresenter().submitBarcodeOutSplitAudit(params);
                }
            });
            divideBarcodeDialog.setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                }
            });
            divideBarcodeDialog.setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                }
            });
        }
        divideBarcodeDialog.setTextViewContent(R.id.tv_divide_barcode, data.getMaterialCode());
        divideBarcodeDialog.setTextViewContent(R.id.tv_barcode_contain_total_num, data.getBarcodeQty());
        divideBarcodeDialog.setTextViewContent(R.id.tv_should_return_num, data.getBarcodeQty() - data.getExceedQty());
        EditText edittext = divideBarcodeDialog.getEdittext(R.id.et_divide_num);
        divideBarcodeDialog.setTextViewContent(R.id.et_divide_num, data.getBarcodeQty() - data.getExceedQty());
        Selection.selectAll(edittext.getText());
        divideBarcodeDialog.show();
    }

    @Override
    public void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data) {
        ToastUtils.showShort(getString(R.string.barcode_divie_in_stock_success));
        orderBoBean.setScanId(data.getScanId());
        tvMaterialName.setText(data.getMaterialName());
        tvMaterialCode.setText(data.getMaterialCode());
        tvMaterialAttr.setText(TextUtils.isEmpty(data.getMaterialAttribute()) ? getString(R.string.none) : data.getMaterialAttribute());
        tvMaterialModel.setText(data.getMaterialStandard());

        orderBoBean.setScanId(data.getScanId());
        /***
         * 设置退了数量，  包含三个数字， （10）20/38
         *  1、 10 ：当前扫码的物料的数量
         *  2、 20：当前物料所需要退料的数量
         *  3、38：退料的总数量
         */
        tvReturnNum.setText("(" + data.getBarcodeQty() + ")" + data.getTotalScanQty() + "/" + orderBoBean.getPurReturnQty());
        setTextViewContent(tvHaveScanTotalNum, data.getTotalScanQty());
        setTextViewContent(tvWaitScanTotalNum, orderBoBean.getPurReturnQty() - data.getTotalScanQty());

    }

    @Override
    public void submitMakeOrAuditBill() {
        ToastUtils.showShort(getString(R.string.commit_check_success));
        onBackPressed();
    }

    @Override
    public void setBarcodeSelect() {
        etMaterialScan.setText(etMaterialScan.getText());
        etMaterialScan.setFocusable(true);
        etMaterialScan.setFocusableInTouchMode(true);
        etMaterialScan.requestFocus();
        etMaterialScan.findFocus();
        Selection.selectAll(etMaterialScan.getText());
    }

}
