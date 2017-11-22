package com.timi.sz.wms_android.mvp.UI.stock_out.batch_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_DETAIL_RESULTS_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_MATERIAL_RESULTS_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_REGIONID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_WAREHOUSEID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BARCODENO;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_DATECODE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_DESBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIALID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_ATTR;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SRCBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_FEEDING;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PURCHASE_MATERIAL_RETURN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;

/**
 * 批次清点的界面
 * author: timi
 * create at: 2017/11/19 10:09
 */
public class BatchPointActivity extends BaseActivity<BatchPointView, BatchPointPresenter> implements BatchPointView {

    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_strict_name_tip)
    TextView tvStrictNameTip;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
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
    @BindView(R.id.tv_material_scan_tip)
    TextView tvMaterialScanTip;
    @BindView(R.id.et_material_scan)
    EditText etMaterialScan;
    @BindView(R.id.iv_material_scan)
    ImageView ivMaterialScan;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.tv_bathch_num)
    TextView tvBathchNum;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.rlv_orderno_info)
    RecyclerView rlvOrdernoInfo;
    @BindView(R.id.btn_close)
    Button btnClose;
    /**
     * 传递的code
     */
    private int intentCode;
    private BaseRecyclerAdapter<GetMaterialLotData.LotDetailBean> adapter;//适配器
    private List<GetMaterialLotData.LotDetailBean> mDatas = new ArrayList<>();//列表的数据源
    private GetMaterialLotData mData;//获取到的批次拣货信息
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
     * 详情的id
     */
    private int detailId;
    /**
     * 仓库id
     */
    private int warehouseId;
    /**
     * 区域的id
     */
    private int regionId;
    /**
     * 物料信息 ：物料id code 附加属性
     */
    private int materialId;
    private String materialCode;
    private String materialAttribute;


    @Override
    public int setLayoutId() {
        return R.layout.activity_batch_point;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT);
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
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                break;
            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                break;
            default:
                break;
        }

        BaseMessage.register(this);

        materialResultsBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_MATERIAL_RESULTS_BEAN), MaterialResultsBean.class);
        detailResultsBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_DETAIL_RESULTS_BEAN), DetailResultsBean.class);
        billId = getIntent().getIntExtra(OUT_STOCK_POINT_DETIAIL_BILLID, 0);
        scanId = getIntent().getIntExtra(OUT_STOCK_SCANID, 0);
        warehouseId = getIntent().getIntExtra(OUT_STOCK_POINT_WAREHOUSEID, 0);
        regionId = getIntent().getIntExtra(OUT_STOCK_POINT_REGIONID, 0);
    }

    @Override
    public void initView() {
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
        /**
         * 输入框 的监听事件
         */
        etMaterialScan.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    /**
                     * 输入的内容
                     */
                    String inputStr = etMaterialScan.getText().toString().trim();
                    if (TextUtils.isEmpty(inputStr)) {
                        ToastUtils.showShort(getString(R.string.please_scan_material_code));
                    } else {
                        /**
                         *   批次拣料的请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", billId);
                        params.put("SrcBillType", srcBillType);
                        params.put("DestBillType", destBillType);
                        params.put("ScanId", scanId);
                        params.put("BarcodeNo", inputStr);
                        params.put("DateCode", mData.getLotDetail().get(0).getDateCode());
                        params.put("bCheckMode", true);
                        params.put("MaterialId", null==detailResultsBean?detailResultsBean.getMaterialId():materialResultsBean.getMaterialId());
                        params.put("MaterialAttribute", null==detailResultsBean?detailResultsBean.getMaterialAttribute():materialResultsBean.getMaterialAttribute());
                        getPresenter().submitBarcodeLotPickOut(params);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
        RequestGetMaterialLotBean bean = new RequestGetMaterialLotBean();
        bean.setMAC(PackageUtils.getMac());
        bean.setUserId(SpUtils.getInstance().getUserId());
        bean.setOrgId(SpUtils.getInstance().getOrgId());
        bean.setBillId(billId);
        bean.setSrcBillType(srcBillType);
        bean.setDestBillType(destBillType);
        bean.setWarehouseId(warehouseId);
        bean.setMaterialId(materialId);
        bean.setMaterialCode(materialCode);
        bean.setMaterialAttribute(materialAttribute);
        LogUitls.e("上传的参数---->", new Gson().toJson(bean));
        showProgressDialog();
        getPresenter().getMaterialLotData(bean);
    }

    @Override
    public BatchPointPresenter createPresenter() {
        return new BatchPointPresenter(this);
    }

    @Override
    public BatchPointView createView() {
        return this;
    }

    @Override
    public void submitBarcodeLotPickOutSplit(SubmitBarcodeLotPickOutSplitResult result) {

    }

    @Override
    public void submitBarcodeLotPickOut(SubmitBarcodeLotPickOutResult result) {

        /**
         * 是否需要拆分打码
         */
        if (result.getExceedQty() > 0) {
            Intent intent = new Intent(this, SplitPrintActivity.class);
            intent.putExtra(OUT_STOCK_PRINT_BILLID,billId);
            intent.putExtra(OUT_STOCK_PRINT_SRCBILLTYPE,srcBillType);
            intent.putExtra(OUT_STOCK_PRINT_BARCODENO,etMaterialScan.getText().toString().trim());
            intent.putExtra(OUT_STOCK_PRINT_DESBILLTYPE,destBillType);
            intent.putExtra(OUT_STOCK_PRINT_DATECODE,mData.getLotDetail().get(0).getDateCode());
            intent.putExtra(OUT_STOCK_PRINT_BILLID,billId);
            intent.putExtra(OUT_STOCK_PRINT_SCANID,scanId);
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_ATTR,materialAttribute);
            intent.putExtra(OUT_STOCK_PRINT_MATERIALID,materialId);
            startActivity(intent);
        } else {
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
            /**
             * True:非管控模式，让前端提醒（没有提交动作）
             * False:表示提交成功
             */
            if (result.isIsNotAllowPickOut()) {
                /**
                 * 显示提醒的对话框
                 */
                new MyDialog(this, R.layout.dialog_batch_tip).setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        dialog.dismiss();
                        /**
                         *   批次拣料的请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", billId);
                        params.put("SrcBillType", srcBillType);
                        params.put("DestBillType", destBillType);
                        params.put("ScanId", scanId);
                        params.put("BarcodeNo", etMaterialScan.getText().toString());
                        params.put("DateCode", mData.getLotDetail().get(0).getDateCode());
                        params.put("bCheckMode", false);
                        getPresenter().submitBarcodeLotPickOut(params);
                    }
                }).setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
            } else {//提交成功
                ToastUtils.showShort(getString(R.string.commit_success));
                /**
                 * 提交成功后 对批次信息进行修改
                 * 1、用于显示批次信息中的已点数是否发生了更改
                 */
                RequestGetMaterialLotBean bean = new RequestGetMaterialLotBean();
                bean.setMAC(PackageUtils.getMac());
                bean.setUserId(SpUtils.getInstance().getUserId());
                bean.setOrgId(SpUtils.getInstance().getOrgId());
                bean.setBillId(detailId);
                bean.setSrcBillType(srcBillType);
                bean.setDestBillType(destBillType);
                bean.setWarehouseId(warehouseId);
                bean.setMaterialId(materialId);
                bean.setMaterialCode(materialCode);
                bean.setMaterialAttribute(materialAttribute);
                LogUitls.e("上传的参数---->", new Gson().toJson(bean));
                showProgressDialog();
                getPresenter().getMaterialLotData(bean);
            }
        }
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
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", billId);
                        params.put("SrcBillType", srcBillType);
                        params.put("DestBillType", destBillType);
                        params.put("ScanId", scanId);
                        params.put("BarcodeNo", result);
                        params.put("DateCode", mData.getLotDetail().get(0).getDateCode());
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

    @Override
    public void getMaterialLotDataHttpSubscriber(GetMaterialLotData data) {
        mDatas.clear();
        /**
         * 存储数据
         */
        mData = data;
        mDatas.addAll(data.getLotDetail());
        switch (data.getControlType()) {
            case 0://非管控
                tvControllType.setText(R.string.no_controll_tip);
                break;
            case 1://非强制管控 （出库非制定批次则提醒）
                tvControllType.setText(getString(R.string.first_in_first_out_dont_constranct) + data.getFifoIntervalDays() + ")");
                break;
            case 2://强制管控（必须扫描指定的批次出库）
                tvControllType.setText(getString(R.string.first_in_first_out_constranct) + data.getFifoIntervalDays() + ")");
                break;
            default:
                tvControllType.setText(R.string.no_controll_tip);
                break;
        }
        initAdapter();
    }

    private void initAdapter() {
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<GetMaterialLotData.LotDetailBean>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_batch;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, GetMaterialLotData.LotDetailBean item) {
                    holder.setTextView(R.id.tv_line_num, position + 1);
                    holder.setTextView(R.id.tv_libcode, item.getLocationCode());
                    holder.setTextView(R.id.tv_bathch_num, item.getDateCode());
                    holder.setTextView(R.id.tv_have_count_num, item.getLotScanQty());
                    holder.setTextView(R.id.tv_can_user_lib, item.getLotUseQty());
                    holder.setTextView(R.id.tv_useful_life, TextUtils.isEmpty(item.getPeriod()) ? getString(R.string.none) : item.getPeriod());
                }
            };
            rlvOrdernoInfo.setAdapter(adapter);
            rlvOrdernoInfo.setLayoutManager(new LinearLayoutManager(this));
            rlvOrdernoInfo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));

        } else
            adapter.notifyDataSetChanged();
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
        tvMaterialAttr.setText(TextUtils.isEmpty(materialAttr) ? getString(R.string.none) : materialAttr);
        tvMaterialModel.setText(TextUtils.isEmpty(materialStandard) ? getString(R.string.none) : materialStandard);
        tvSendMaterialLib.setText(TextUtils.isEmpty(wareHouseName) ? getString(R.string.none) : wareHouseName);
        tvSendMaterialNum.setText("(0)" + scanQty + "/" + qty);
        /**
         * 设置扫描数和总数
         */
        this.scanQty = scanQty;
        this.totalQty = qty;
        /**
         * detailId
         */
        this.detailId = detailId;
        /**
         * 附加属性
         */
        this.materialAttribute = materialAttr;
        /**
         * 物料code
         */
        this.materialCode = materialCode;
        /**
         * 物料id
         */
        this.materialId = materialId;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void submitBarcodeSplitSuccess(SubmitBarcodeLotPickOutSplitEvent event){
        if(event.getEvent().equals(SubmitBarcodeLotPickOutSplitEvent.SUBMIT_BARCODE_SPLIT_SUCCESS)){
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

            /**
             * 提交成功后 对批次信息进行修改
             * 1、用于显示批次信息中的已点数是否发生了更改
             */
            RequestGetMaterialLotBean bean = new RequestGetMaterialLotBean();
            bean.setMAC(PackageUtils.getMac());
            bean.setUserId(SpUtils.getInstance().getUserId());
            bean.setOrgId(SpUtils.getInstance().getOrgId());
            bean.setBillId(detailId);
            bean.setSrcBillType(srcBillType);
            bean.setDestBillType(destBillType);
            bean.setWarehouseId(warehouseId);
            bean.setMaterialId(materialId);
            bean.setMaterialCode(materialCode);
            bean.setMaterialAttribute(materialAttribute);
            LogUitls.e("上传的参数---->", new Gson().toJson(bean));
            showProgressDialog();
            getPresenter().getMaterialLotData(bean);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }
}
