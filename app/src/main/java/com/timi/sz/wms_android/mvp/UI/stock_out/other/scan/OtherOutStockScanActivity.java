package com.timi.sz.wms_android.mvp.UI.stock_out.other.scan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.StockOutSubmitScanMaterialEvent;
import com.timi.sz.wms_android.mvp.UI.stock_in.detail.StockInDetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.other_scan.OtherScanActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.detail.DetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.divide_print.SplitPrintActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BARCODENO;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BATCh_AND_NORMAL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BATCh_DETAILID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_CURRENT_QTY;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_DESBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIALID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_ATTR;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_CODE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_MODEL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_NAME;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_NORMAL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_OUT_QTY;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SRCBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

/**
 * 其他出库  生单
 * author: timi
 * create at: 2017/11/29 11:14
 */
public class OtherOutStockScanActivity extends BaseActivity<OtherOutStockScanView, OtherOutStockScanPresenter> implements OtherOutStockScanView {
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.tv_putaway_scan_material_tip)
    TextView tvPutawayScanMaterialTip;
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
    @BindView(R.id.ll_material_attr)
    LinearLayout llMaterialAttr;
    @BindView(R.id.ll_material_info)
    LinearLayout llMaterialInfo;
    @BindView(R.id.btn_create_other_out_stock_orderno)
    Button btnCreateOtherOutStockOrderno;
    private int intentCode;
    private int scanId = 0;

    @Override
    public int setLayoutId() {
        return R.layout.activity_other_out_stock_scan;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.other_out_stock_scan_title));
        intentCode = getIntent().getIntExtra(Constants.STOCK_OUT_CODE_STR, 0);
    }

    @Override
    public void initView() {
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 查看详情
                 */
                Intent it = new Intent(OtherOutStockScanActivity.this, DetailActivity.class);
                it.putExtra(Constants.CODE_STR, intentCode);
                it.putExtra(Constants.STOCKIN_BILLID, scanId);
                startActivity(it);
            }
        });
        /***
         * 扫描物料
         */
        setEdittextListener(etScanMaterial, Constants.REQUEST_SCAN_CODE_MATERIIAL, R.string.please_input_material_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 *   批次拣料的请求
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", 0);
                params.put("SrcBillType", 0);
                params.put("DestBillType", 52);
                params.put("ScanId", scanId);
                params.put("BarcodeNo", result);
                //判断 批次是否为空
                getPresenter().submitBarcodeLotPickOut(params);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public OtherOutStockScanPresenter createPresenter() {
        return new OtherOutStockScanPresenter(this);
    }

    @Override
    public OtherOutStockScanView createView() {
        return this;
    }

    @OnClick({R.id.iv_scan_material, R.id.btn_create_other_out_stock_orderno})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan_material:
                scan(REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etScanMaterial.setText(result);
                        /**
                         *   批次拣料的请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", 0);
                        params.put("SrcBillType", 0);
                        params.put("DestBillType", 52);
                        params.put("ScanId", scanId);
                        params.put("BarcodeNo", result);
                        //判断 批次是否为空
                        getPresenter().submitBarcodeLotPickOut(params);
                    }
                });
                break;
            case R.id.btn_create_other_out_stock_orderno:
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("MAC", PackageUtils.getMac());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("ScanId", scanId);
                params.put("SubmitType", 0);
                getPresenter().submitMakeOrAuditBill(params);
                break;
        }
    }

    @Override
    public void submitBarcodeLotPickOut(SubmitBarcodeOutAuditData result) {
        findViewById(R.id.ll_material_info).setVisibility(View.VISIBLE);
        /**
         * 设置物料的信息
         */
        tvMaterialName.setText(result.getMaterialName());
        tvMaterialCode.setText(result.getMaterialCode());
        tvMaterialAttr.setText(result.getMaterialAttribute());
        tvMaterialNmodel.setText(result.getMaterialStandard());
//        /**
//         * 是否需要拆分打码
//         */
//        if (result.getExceedQty() > 0) {
//            Intent intent = new Intent(this, SplitPrintActivity.class);
//            intent.putExtra(OUT_STOCK_PRINT_BILLID, 0);
//            intent.putExtra(OUT_STOCK_PRINT_SRCBILLTYPE, 0);
//            intent.putExtra(OUT_STOCK_PRINT_BARCODENO, etScanMaterial.getText().toString().trim());
//            intent.putExtra(OUT_STOCK_PRINT_DESBILLTYPE, 52);
//            intent.putExtra(OUT_STOCK_PRINT_SCANID, result.getScanId());
//            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_ATTR, result.getMaterialAttribute());
//            intent.putExtra(OUT_STOCK_PRINT_MATERIALID, result.getMaterialId());
//            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_NAME, result.getMaterialName());
//            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_CODE, result.getMaterialCode());
//            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_MODEL, result.getMaterialStandard());
//            intent.putExtra(OUT_STOCK_PRINT_CURRENT_QTY, result.getBarcodeQty());
//            intent.putExtra(OUT_STOCK_PRINT_OUT_QTY, result.getExceedQty());
//            intent.putExtra(OUT_STOCK_PRINT_NORMAL, true);
//            startActivity(intent);
//        } else {
        /**
         * True:非管控模式，让前端提醒（没有提交动作）
         * False:表示提交成功
         */

        ToastUtils.showShort(getString(R.string.commit_success));
        //设置scanid
        scanId = result.getScanId();
        //设置数量
        tvMaterialNum.setText(result.getBarcodeQty() + "/" + result.getTotalScanQty());
        //待点  和  已点数量
        setTextViewContent(tvHaveCountNum, result.getTotalScanQty());

//        }
    }

    @Override
    public void submitMakeOrAuditBill() {
        ToastUtils.showShort(getString(R.string.commit_success));
        onBackPressed();
    }

    @Override
    public void setBarcodeSelect() {
        etScanMaterial.setFocusable(true);
        etScanMaterial.setFocusableInTouchMode(true);
        etScanMaterial.requestFocus();
        etScanMaterial.findFocus();
        Selection.selectAll(etScanMaterial.getText());
    }
}
