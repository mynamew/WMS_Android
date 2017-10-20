package com.timi.sz.wms_android.mvp.UI.quity.advance_quality;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;
import com.timi.sz.wms_android.view.excelview.MyExcelView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 高级质检
 * author: timi
 * create at: 2017/10/12 13:38
 */
public class AdvanceQualityActivity extends BaseActivity<AdvanceQualityView, AdvanceQualityPresenter> implements AdvanceQualityView {
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_standard_level)
    TextView tvStandardLevel;
    @BindView(R.id.tv_sample_half_yard)
    TextView tvSampleHalfYard;
    @BindView(R.id.tv_check_method)
    TextView tvCheckMethod;
    @BindView(R.id.tv_aql_tip)
    TextView tvAqlTip;
    @BindView(R.id.tv_aql)
    TextView tvAql;
    @BindView(R.id.tv_stringency_tip)
    TextView tvStringencyTip;
    @BindView(R.id.tv_stringency)
    TextView tvStringency;
    @BindView(R.id.tv_recevie_pro_orderno)
    TextView tvRecevieProOrderno;
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_receive_material_date)
    TextView tvReceiveMaterialDate;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_supplier)
    TextView tvSupplier;
    @BindView(R.id.tv_real_receivce_num)
    TextView tvRealReceivceNum;
    @BindView(R.id.tv_sample_count)
    TextView tvSampleCount;
    @BindView(R.id.tv_serious_unqualitfied_tip)
    TextView tvSeriousUnqualitfiedTip;
    @BindView(R.id.tv_serious_unqualitfied)
    TextView tvSeriousUnqualitfied;
    @BindView(R.id.tv_badness_total_num_tip)
    TextView tvBadnessTotalNumTip;
    @BindView(R.id.tv_badness_total_num)
    TextView tvBadnessTotalNum;
    @BindView(R.id.tv_main_unqualitfied_tip)
    TextView tvMainUnqualitfiedTip;
    @BindView(R.id.tv_main_unqualitfied)
    TextView tvMainUnqualitfied;
    @BindView(R.id.tv_badness_percent_tip)
    TextView tvBadnessPercentTip;
    @BindView(R.id.tv_badness_percent)
    TextView tvBadnessPercent;
    @BindView(R.id.tv_monor_unqualitfied_tip)
    TextView tvMonorUnqualitfiedTip;
    @BindView(R.id.tv_monor_unqualitfied)
    TextView tvMonorUnqualitfied;
    @BindView(R.id.tv_sample_num_tip)
    TextView tvSampleNumTip;
    @BindView(R.id.tv_sample_num)
    TextView tvSampleNum;
    @BindView(R.id.tv_check)
    TextView tvCheck;
    @BindView(R.id.myexcel_advance_quality)
    MyExcelView myexcelAdvanceQuality;
    @BindView(R.id.rlv_quality_advance)
    RecyclerView rlvQualityAdvance;
    //bundle
    private int receiptId;
    private int receiptDetailId;


    private GetAdvance2Data mData;


    @Override
    public int setLayoutId() {
        return R.layout.activity_advance_quality;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        receiptId = getIntent().getIntExtra("ReceiptId", -1);
        receiptDetailId = getIntent().getIntExtra("ReceiptDetailId", -1);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        params.put("ReceiptId", receiptId);
        params.put("ReceiptDetailId", receiptDetailId);
        getPresenter().getAdvance2Data(params);
    }

    @Override
    public AdvanceQualityPresenter createPresenter() {
        return new AdvanceQualityPresenter(this);
    }

    @Override
    public AdvanceQualityView createView() {
        return this;
    }

    @OnClick({R.id.tv_next, R.id.tv_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                break;
            case R.id.tv_check:
                /**
                 * 弹出检验的弹出框
                 */
                break;
        }
    }

    @Override
    public void getAdvance2Data(GetAdvance2Data data) {
        mData = data;
        /**
         * 初始化数据
         */
        GetAdvance2Data.NormalSummaryBean normalSummary = data.getNormalSummary();
        /**
         * 设置相应的物料信息
         */
        setTextViewText(tvOrderno, R.string.receive_pro_num, normalSummary.getReceiptCode());
        setTextViewText(tvReceiveMaterialDate, R.string.receive_material_date, normalSummary.getReceiptDate());
        setTextViewText(tvRecevieProOrderno, R.string.receive_pro_num, normalSummary.getSourceBillCode());
        setTextViewText(tvSupplier, R.string.buy_from, normalSummary.getSupplierName());
        setTextViewText(tvMaterialCode, R.string.material_code, normalSummary.getMaterialCode());
        setTextViewText(tvMaterialName, R.string.material_name, normalSummary.getMaterialName());
        setTextViewText(tvMaterialModel, R.string.material_model, normalSummary.getMaterialStandard());
        setTextViewText(tvRealReceivceNum, R.string.receive_num, normalSummary.getReceiveQty());
        setTextViewText(tvSampleCount, R.string.sample_num, normalSummary.getSampleQty());
        /**
         * 检验标准
         */
        GetAdvance2Data.AdvanceSummaryBean advanceSummary = data.getAdvanceSummary();
        //标准水平
        setTextViewText(tvStandardLevel, R.string.standard_level, advanceSummary.getCurrentLevel());
        //AQL
        tvAql.setText(advanceSummary.getCurrentAQL() + "(" + advanceSummary.getRejectAQL() + ")");
        //试样半码
        setTextViewText(tvSampleHalfYard, R.string.sample_half_yard, advanceSummary.getSampleCode());
        //严格度
        tvStringency.setText(advanceSummary.getCurrentStrict());

    }
}
