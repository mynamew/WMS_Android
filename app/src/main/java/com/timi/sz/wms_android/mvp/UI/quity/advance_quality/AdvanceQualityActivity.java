package com.timi.sz.wms_android.mvp.UI.quity.advance_quality;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.QualityResultView;
import com.timi.sz.wms_android.view.StandardLevelView;
import com.timi.sz.wms_android.view.WrapLayoutQualityResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 高级质检
 * author: timi
 * create at: 2017/10/12 13:38
 */
public class AdvanceQualityActivity extends BaseActivity<AdvanceQualityView, AdvanceQualityPresenter> implements AdvanceQualityView {
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.stand_quality_level)
    StandardLevelView standQualityLevel;
    @BindView(R.id.stand_aql)
    StandardLevelView standAql;
    @BindView(R.id.stand_sample_half_yard)
    StandardLevelView standSampleHalfYard;
    @BindView(R.id.stand_strict)
    StandardLevelView standStrict;
    @BindView(R.id.stand_check_method)
    StandardLevelView standCheckMethod;
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_receive_material_date)
    TextView tvReceiveMaterialDate;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_supplier)
    TextView tvSupplier;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_receive_num)
    TextView tvReceiveNum;
    @BindView(R.id.tv_sample_num)
    TextView tvSampleNum;
    @BindView(R.id.tv_serious_badness_num)
    StandardLevelView tvSeriousBadnessNum;
    @BindView(R.id.tv_main_badness_num)
    StandardLevelView tvMainBadnessNum;
    @BindView(R.id.tv_minor_badness_num)
    StandardLevelView tvMinorBadnessNum;
    @BindView(R.id.tv_badness_total_num)
    StandardLevelView tvBadnessTotalNum;
    @BindView(R.id.tv_badness_percent)
    StandardLevelView tvBadnessPercent;
    @BindView(R.id.tv_sample_code)
    TextView tvSampleCode;
    @BindView(R.id.tv_quality)
    Button tvQuality;
    @BindView(R.id.tv_quality_result_tip)
    TextView tvQualityResultTip;
    @BindView(R.id.rd_qualified)
    RadioButton rdQualified;
    @BindView(R.id.rd_wait_deal)
    RadioButton rdWaitDeal;
    @BindView(R.id.rd_unqualified)
    RadioButton rdUnqualified;
    @BindView(R.id.rg_qualified)
    RadioGroup rgQualified;
    @BindView(R.id.wrap_quality_result)
    WrapLayoutQualityResult wrapQualityResult;
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
        setActivityTitle(getString(R.string.advance_quality_title));
        receiptId = getIntent().getIntExtra("ReceiptId", -1);
        receiptDetailId = getIntent().getIntExtra("ReceiptDetailId", -1);
    }

    @Override
    public void initView() {
        /**
         * 点击事件
         */
        wrapQualityResult.setMarkClickListener(new WrapLayoutQualityResult.MarkClickListener() {
            @Override
            public void clickMark(int position) {
                // TODO: 2017/10/21 做点击事件
            }
        });
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

    private BaseRecyclerAdapter<GetAdvance2Data.CheckItemBean> adapterQualityResult;

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
        setTextViewText(tvOrderno, R.string.receive_pro_num, normalSummary.getSourceBillCode());
        setTextViewText(tvSupplier, R.string.buy_from, normalSummary.getSupplierName());
        setTextViewText(tvMaterialCode, R.string.material_code, normalSummary.getMaterialCode());
        setTextViewText(tvMaterialName, R.string.material_name, normalSummary.getMaterialName());
        setTextViewText(tvMaterialModel, R.string.material_model, normalSummary.getMaterialStandard());
        setTextViewText(tvReceiveNum, R.string.receive_num, normalSummary.getReceiveQty());
        setTextViewText(tvSampleNum, R.string.sample_num, normalSummary.getSampleQty());
        /**
         * 检验标准
         */
        GetAdvance2Data.AdvanceSummaryBean advanceSummary = data.getAdvanceSummary();
        //标准水平
        standQualityLevel.setTextViewContent(advanceSummary.getCurrentLevel());
        //AQL
        standAql.setTextViewContent(advanceSummary.getRejectAQL());
        //试样半码
        standSampleHalfYard.setTextViewContent(advanceSummary.getSampleCode());
        //严格度
        standStrict.setTextViewContent(advanceSummary.getCurrentStrict());
        /**
         * 检验的条目
         */
        final List<GetAdvance2Data.CheckItemBean> checkItem = data.getCheckItem();

        if (null != checkItem && checkItem.isEmpty()) {
            wrapQualityResult.setData(checkItem, this, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

    }

    @OnClick({R.id.tv_next, R.id.tv_quality})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                break;
            case R.id.tv_quality:
                break;
        }
    }

    class QualityResult {
        int checkItemId;
        String checkItemCode;
        String checkItemName;
        int judgeType;
        String unit;
        double limitLow;
        double limitHigh;
        double stardard;
        String remark;
        List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData;
        boolean isQuality;

        public QualityResult(int checkItemId, String checkItemCode, String checkItemName, int judgeType, String unit, double limitLow, double limitHigh, double stardard, String remark, List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData, boolean isQuality) {
            this.checkItemId = checkItemId;
            this.checkItemCode = checkItemCode;
            this.checkItemName = checkItemName;
            this.judgeType = judgeType;
            this.unit = unit;
            this.limitLow = limitLow;
            this.limitHigh = limitHigh;
            this.stardard = stardard;
            this.remark = remark;
            this.faultData = faultData;
            this.isQuality = isQuality;
        }
    }
}
