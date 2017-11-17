package com.timi.sz.wms_android.mvp.UI.stock_out.outsource_feed;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_SUMMARY_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_OUT_SOURCE_FEED_MATERIAL_POINT_BEAN;

/**
 * 委外补料 的物品 清点详情 * author: timi
 * create at: 2017/11/14 13:49
 */
public class OutSourceFeedMaterialPointActivity extends BaseActivity<OutSourceFeedMaterialPointView, OutSourceFeedMaterialPointPresenter> implements OutSourceFeedMaterialPointView {

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
    @BindView(R.id.tv_controll_type)
    TextView tvControllType;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.rlv_orderno_info)
    RecyclerView rlvOrdernoInfo;
    @BindView(R.id.btn_close)
    Button btnClose;
    @BindView(R.id.tv_material_scan_tip)
    TextView tvMaterialScanTip;
    @BindView(R.id.et_material_scan)
    EditText etMaterialScan;
    @BindView(R.id.iv_material_scan)
    ImageView ivMaterialScan;

    @Override
    public int setLayoutId() {
        return R.layout.activity_out_source_feed_material_point;
    }

    private QueryOutSourceFeedByInputResult.DetailResultsBean detailResultsBean;
    private QueryOutSourceFeedByInputResult.SummaryResultsBean summaryResultsBean;

    @Override
    public void initBundle(Bundle savedInstanceState) {
        detailResultsBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_OUT_SOURCE_FEED_MATERIAL_POINT_BEAN), QueryOutSourceFeedByInputResult.DetailResultsBean.class);
        summaryResultsBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_SUMMARY_BEAN), QueryOutSourceFeedByInputResult.SummaryResultsBean.class);
    }

    @Override
    public void initView() {
        tvMaterialName.setText(detailResultsBean.getMaterialName());
        tvMaterialCode.setText(detailResultsBean.getMaterialCode());
        tvMaterialAttr.setText(TextUtils.isEmpty(detailResultsBean.getMaterialAttribute()) ? getString(R.string.none) : detailResultsBean.getMaterialAttribute());
        tvMaterialModel.setText(detailResultsBean.getMaterialStandard());
        tvSendMaterialLib.setText(detailResultsBean.getWarehouseName());
        tvSendMaterialNum.setText("(0)" + detailResultsBean.getScanQty() + "/" + detailResultsBean.getQty());
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
                        params.put("BillId", summaryResultsBean.getBillId());
                        params.put("SrcBillType", 12);
                        params.put("DestBillType", 20);
                        params.put("ScanId", summaryResultsBean.getScanId());
                        params.put("BarcodeNo", inputStr);
//                        params.put("DateCode", mData.getLotDetail().get(0).getDateCode());
//                        params.put("bCheckMode", true);
//                        getPresenter().submitBarcodeLotPickOut(params);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public OutSourceFeedMaterialPointPresenter createPresenter() {
        return new OutSourceFeedMaterialPointPresenter(this);
    }

    @Override
    public OutSourceFeedMaterialPointView createView() {
        return this;
    }

    @OnClick(R.id.btn_close)
    public void onViewClicked() {
        onBackPressed();
    }
}
