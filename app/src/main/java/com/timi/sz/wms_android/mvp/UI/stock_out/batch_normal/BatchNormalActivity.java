package com.timi.sz.wms_android.mvp.UI.stock_out.batch_normal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
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
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.RequestGetMaterialLotBean;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.bean.outstock.outsource.common.DetailResultsBean;
import com.timi.sz.wms_android.bean.outstock.outsource.common.MaterialResultsBean;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.OutsourceAuditEvent;
import com.timi.sz.wms_android.http.message.event.SubmitBarcodeLotPickOutSplitEvent;
import com.timi.sz.wms_android.mvp.UI.stock_out.divide_print.SplitPrintActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_DETAIL_RESULTS_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_MATERIAL_RESULTS_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_REGIONID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_WAREHOUSEID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BARCODENO;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BATCh_AND_NORMAL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_CURRENT_QTY;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_DATECODE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_DESBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIALID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_ATTR;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_CODE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_MODEL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_NAME;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_OUT_QTY;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SRCBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SALE_CARTON_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SALE_IS_CARTON;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_FINISH_GOODS_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_APPLY_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_FEEDING;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PURCHASE_MATERIAL_RETURN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;

public class BatchNormalActivity extends BaseActivity<BatchNormalView, BatchNormalPresenter> implements BatchNormalView {


    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_mateZrial_attr)
    TextView tvMateZrialAttr;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_send_material_total_num_tip)
    TextView tvSendMaterialTotalNumTip;
    @BindView(R.id.tv_send_material_num)
    TextView tvSendMaterialNum;
    @BindView(R.id.tv_have_get_total_num)
    TextView tvHaveGetTotalNum;
    @BindView(R.id.tv_can_get_total_num_tip)
    TextView tvCanGetTotalNumTip;
    @BindView(R.id.tv_can_get_total_num)
    TextView tvCanGetTotalNum;
    @BindView(R.id.tv_send_material_lib_tip)
    TextView tvSendMaterialLibTip;
    @BindView(R.id.tv_send_material_lib)
    TextView tvSendMaterialLib;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.tv_controll_type)
    TextView tvControllType;
    @BindView(R.id.tv_carton_num)
    TextView tvCartonNum;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.ll_carton)
    LinearLayout llCarton;
    @BindView(R.id.tv_material_scan_tip)
    TextView tvMaterialScanTip;
    @BindView(R.id.et_material_scan)
    EditText etMaterialScan;
    @BindView(R.id.iv_material_scan)
    ImageView ivMaterialScan;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.btn_close)
    Button btnClose;


    /**
     * 传过来的实体类
     * 是否合并 影响到穿过来的实体信息
     */
    private MaterialResultsBean materialResultsBean;
    private DetailResultsBean detailResultsBean;
    /**
     * 订单的id
     */
    private int billId;

    /**
     * 扫描的id
     */
    private int scanId;

    /**
     * 已扫码的物品总数
     */
    private int scanQty = 0;
    /**
     * 表示物品的发料总数；
     */
    private int totalQty = 0;
    /**
     * 源单类型
     */
    private int srcBillType;
    /**
     * 目标单类型
     */
    private int destBillType;
    /**
     * 传递的code
     */
    private int intentCode;
    /**
     * 详情的id
     */
    private int detailId;
    /**
     * 区域的id
     */
    private int regionId;
    //是否装箱
    private boolean isCarton;
    //箱号
    private int cartonNum = 0;

    @Override
    public int setLayoutId() {
        return R.layout.activity_batch_normal;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT);
        isCarton = getIntent().getBooleanExtra(OUT_STOCK_SALE_IS_CARTON, false);
        cartonNum = getIntent().getIntExtra(OUT_STOCK_SALE_CARTON_NUM, 0);
        materialResultsBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_MATERIAL_RESULTS_BEAN), MaterialResultsBean.class);
        detailResultsBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_DETAIL_RESULTS_BEAN), DetailResultsBean.class);
        billId = getIntent().getIntExtra(OUT_STOCK_POINT_DETIAIL_BILLID, 0);
        scanId = getIntent().getIntExtra(OUT_STOCK_SCANID, 0);
        regionId = getIntent().getIntExtra(OUT_STOCK_POINT_REGIONID, 0);
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                setActivityTitle(getString(R.string.material_point_outsource_feed_title));
                srcBillType = 21;
                destBillType = 21;
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                setActivityTitle(getString(R.string.oursource_audit_maiterial_point_title));
                srcBillType = 20;
                destBillType = 20;
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
                setActivityTitle(getString(R.string.oursource_audit_maiterial_point_title));
                srcBillType = 20;
                destBillType = 20;
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                setActivityTitle(getString(R.string.oursource_allot_maiterial_point_title));
                srcBillType = 20;
                destBillType = 20;
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产补料
                setActivityTitle(getString(R.string.material_point_production_feed_title));
                srcBillType = 24;
                destBillType = 24;
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产审核
                setActivityTitle(getString(R.string.material_point_production_pick_title));
                srcBillType = 23;
                destBillType = 23;
                break;
            case STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请
                setActivityTitle(getString(R.string.material_point_production_pick_title));
                srcBillType = 31;
                destBillType = 31;
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
                setActivityTitle(getString(R.string.material_point_production_pick_title));
                srcBillType = 23;
                destBillType = 23;
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                setActivityTitle(getString(R.string.material_point_production_allot_title));
                srcBillType = 50;
                destBillType = 50;
                break;
            case STOCK_OUT_PICK://拣料
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                setActivityTitle(getString(R.string.material_point_sale_audit_title));
                srcBillType = 41;
                destBillType = 42;
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                setActivityTitle(getString(R.string.material_point_sale_bill_title));
                srcBillType = 42;
                destBillType = 42;
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                setActivityTitle(getString(R.string.material_point_other_audit_title));
                srcBillType = 51;
                destBillType = 52;
                break;
            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                setActivityTitle(getString(R.string.material_point_other_bill_title));
                srcBillType = 52;
                destBillType = 52;
                break;
            case STOCK_OUT_FINISH_GOODS_PICK://成品拣货
                setActivityTitle(getString(R.string.material_point_finish_goods_pick_title));
                srcBillType = 61;
                destBillType = 61;
                break;
            case STOCK_OUT_ALLOT_OUT_PICK://调拨调出
                setActivityTitle(getString(R.string.material_point_allot_out));
                srcBillType = 50;
                destBillType = 50;
                break;
            default:
                break;
        }

        BaseMessage.register(this);
    }

    @Override
    public void initView() {
        /**
         * 是否装箱
         */
        if (isCarton) {
            findViewById(R.id.ll_carton).setVisibility(View.VISIBLE);
            tvCartonNum.setText(String.valueOf(cartonNum));
        } else {
            findViewById(R.id.ll_carton).setVisibility(View.GONE);
        }
        /**
         * 判断materialResultsBean是否未null
         * 1、 为null  则传过来的是 detailResultsBean
         * 2、 不为null  则传过来的是  materialResultsBean
         */
        if (null == materialResultsBean) {
            setHeaderContent(detailResultsBean.getDetailId(), detailResultsBean.getMaterialId(), detailResultsBean.getMaterialName(), detailResultsBean.getMaterialCode(), detailResultsBean.getMaterialStandard(), detailResultsBean.getMaterialAttribute(), detailResultsBean.getWarehouseName(), detailResultsBean.getWipQty(), detailResultsBean.getQty());
        } else {
            setHeaderContent(materialResultsBean.getDetailId(), materialResultsBean.getMaterialId(), materialResultsBean.getMaterialName(), materialResultsBean.getMaterialCode(), materialResultsBean.getMaterialStandard(), materialResultsBean.getMaterialAttribute(), materialResultsBean.getWarehouseName(), materialResultsBean.getScanQty(), materialResultsBean.getQty());
        }
        setEdittextListener(etMaterialScan, Constants.REQUEST_SCAN_CODE_MATERIIAL,R.string.please_scan_material_code,0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 *   批次拣料的请求
                 */
                Map<String, Object> params = new HashMap<>();
                /**
                 * 是否 装箱
                 */
                if (isCarton) {
                    params.put("cartonNo", cartonNum);
                }
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", billId);
                params.put("SrcBillType", srcBillType);
                params.put("DestBillType", destBillType);
                params.put("ScanId", scanId);
                params.put("BarcodeNo", result);
                //判断 批次是否为空
                params.put("bCheckMode", true);
                getPresenter().submitBarcodeLotPickOut(params);
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public BatchNormalPresenter createPresenter() {
        return new BatchNormalPresenter(this);
    }

    @Override
    public BatchNormalView createView() {
        return this;
    }

    @Override
    public void submitBarcodeLotPickOut(SubmitBarcodeLotPickOutResult result) {

        /**
         * 是否需要拆分打码
         */
        if (result.getExceedQty() > 0) {
            Intent intent = new Intent(this, SplitPrintActivity.class);
            intent.putExtra(OUT_STOCK_PRINT_BILLID, billId);
            intent.putExtra(OUT_STOCK_PRINT_SRCBILLTYPE, srcBillType);
            intent.putExtra(OUT_STOCK_PRINT_BARCODENO, etMaterialScan.getText().toString().trim());
            intent.putExtra(OUT_STOCK_PRINT_DESBILLTYPE, destBillType);
            intent.putExtra(OUT_STOCK_PRINT_SCANID, result.getScanId());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_ATTR, result.getMaterialAttribute());
            intent.putExtra(OUT_STOCK_PRINT_MATERIALID, result.getMaterialId());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_NAME, result.getMaterialName());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_CODE, result.getMaterialCode());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_MODEL, result.getMaterialStandard());
            intent.putExtra(OUT_STOCK_PRINT_CURRENT_QTY, result.getBarcodeQty());
            intent.putExtra(OUT_STOCK_PRINT_OUT_QTY, result.getExceedQty());
            intent.putExtra(OUT_STOCK_PRINT_BATCh_AND_NORMAL, true);
            startActivity(intent);
        } else {
            /**
             * True:非管控模式，让前端提醒（没有提交动作）
             * False:表示提交成功
             */
            if (result.isIsNotAllowPickOut()) {
                ToastUtils.showShort("请确认物料条码是否正确！");
            } else {//提交成功
                ToastUtils.showShort(getString(R.string.commit_success));
                /**
                 * 发送时间  传递 scanid
                 */
                OutsourceAuditEvent outsourceAuditEvent = new OutsourceAuditEvent(OutsourceAuditEvent.OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS);
                outsourceAuditEvent.setScanId(result.getScanId());
                outsourceAuditEvent.setMaterialCode(result.getMaterialCode());
                outsourceAuditEvent.setBarcodeQty(result.getBarcodeQty());
                BaseMessage.post(outsourceAuditEvent);
                //设置scanid
                scanId = result.getScanId();
                /***
                 * 是否装箱
                 */
                if (isCarton) {
                    cartonNum = result.getCartonNo();
                    tvCartonNum.setText(String.valueOf(cartonNum));
                }
                /**
                 * 物料返回设置扫描的数量
                 */
                scanQty = scanQty + result.getBarcodeQty();
                //设置数量
                tvSendMaterialNum.setText("(" + result.getBarcodeQty() + ")" + scanQty + "/" + totalQty);
            }
        }
    }

    @Override
    public void setBarcodeSelect() {
        etMaterialScan.setFocusable(true);
        etMaterialScan.setFocusableInTouchMode(true);
        etMaterialScan.requestFocus();
        etMaterialScan.findFocus();
        Selection.selectAll(etMaterialScan.getText());
    }

    /**
     * 设置头部的数据
     *
     * @param materialName
     * @param materialCode
     * @param materialStandard
     * @param materialAttr
     * @param wareHouseName
     * @param scanQty
     * @param qty
     */
    public void setHeaderContent(int detailId, int materialId, String materialName, String materialCode, String materialStandard, String materialAttr, String wareHouseName, int scanQty, int qty) {
        /**
         * 设置物料的信息
         */
        tvMaterialName.setText(materialName);
        tvMaterialCode.setText(materialCode);
        tvMateZrialAttr.setText(materialAttr);
        tvMaterialModel.setText(materialStandard);
        tvSendMaterialLib.setText(wareHouseName);
        tvSendMaterialNum.setText("(0)" + scanQty + "/" + qty);
        /**
         * 设置 是否显示附加属性
         */
        setMaterialAttrStatus(findViewById(R.id.ll_material_attr));
        /**
         * 设置扫描数和总数
         */
        this.scanQty = scanQty;
        this.totalQty = qty;
        /**
         * detailId
         */
        this.detailId = detailId;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void submitBarcodeSplitSuccess(SubmitBarcodeLotPickOutSplitEvent event) {
        if (event.getEvent().equals(SubmitBarcodeLotPickOutSplitEvent.SUBMIT_BARCODE_SPLIT_SUCCESS)) {
            SubmitBarcodeLotPickOutSplitResult result = event.getResult();
            /**
             * 发送时间  传递 scanid
             */
            OutsourceAuditEvent outsourceAuditEvent = new OutsourceAuditEvent(OutsourceAuditEvent.OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS);
            outsourceAuditEvent.setScanId(result.getScanId());
            outsourceAuditEvent.setMaterialCode(result.getMaterialCode());
            outsourceAuditEvent.setBarcodeQty(result.getBarcodeQty());
            BaseMessage.post(outsourceAuditEvent);
            //设置scanid
            scanId = result.getScanId();
            /**
             * 物料返回设置扫描的数量
             */
            scanQty = scanQty + result.getBarcodeQty();
            //设置数量
            tvSendMaterialNum.setText("(" + result.getBarcodeQty() + ")" + scanQty + "/" + totalQty);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    /**
     * 点击  置零
     */
    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        cartonNum = 0;
    }

    @OnClick({R.id.iv_material_scan, R.id.btn_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_material_scan:
                scan(REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etMaterialScan.setText(result);
                        /**
                         *   批次拣料的请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        /**
                         * 是否 装箱
                         */
                        if (isCarton) {
                            params.put("cartonNo", cartonNum);
                        }
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", billId);
                        params.put("SrcBillType", srcBillType);
                        params.put("DestBillType", destBillType);
                        params.put("ScanId", scanId);
                        params.put("BarcodeNo", result);
                        params.put("bCheckMode", true);
                        getPresenter().submitBarcodeLotPickOut(params);
                    }
                });
                break;
            case R.id.btn_close:
                onBackPressed();
                break;
        }
    }
}