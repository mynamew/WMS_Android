package com.timi.sz.wms_android.mvp.UI.quity.mrp.advance_review;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * mrp  高级评审
 * author: timi
 * create at: 2017/10/18 11:15
 */
public class MRPAdvanceReviewActivity extends BaseActivity<MRPAdvanceView, MRPAdvancePresenter> implements MRPAdvanceView {

    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_quality_date)
    TextView tvQualityDate;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_mrp_normal_checker)
    TextView tvMrpNormalChecker;
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
    @BindView(R.id.tv_send_quality_num)
    TextView tvSendQualityNum;
    @BindView(R.id.tv_serious_num)
    TextView tvSeriousNum;
    @BindView(R.id.tv_main_num)
    TextView tvMainNum;
    @BindView(R.id.tv_second_num)
    TextView tvSecondNum;
    @BindView(R.id.tv_quality_method)
    TextView tvQualityMethod;
    @BindView(R.id.tv_standard_level)
    TextView tvStandardLevel;
    @BindView(R.id.tv_mrp_aql)
    TextView tvMrpAql;
    @BindView(R.id.tv_quality_total_num)
    TextView tvQualityTotalNum;
    @BindView(R.id.tv_badbess_total_num)
    TextView tvBadbessTotalNum;
    @BindView(R.id.tv_sample_half_yard)
    TextView tvSampleHalfYard;
    @BindView(R.id.tv_badness_percent)
    TextView tvBadnessPercent;
    @BindView(R.id.tv_select_mrpreview_result)
    TextView tvSelectMrpreviewResult;
    @BindView(R.id.iv_mrp_down)
    ImageView ivMrpDown;
    @BindView(R.id.rl_select_review_result)
    RelativeLayout rlSelectReviewResult;
    @BindView(R.id.et_pick_num)
    EditText etPickNum;
    @BindView(R.id.et_mrp_normal_mark)
    EditText etMrpNormalMark;
    @BindView(R.id.tv_confirm_review)
    TextView tvConfirmReview;

    @Override
    public int setLayoutId() {
        return R.layout.activity_mrpadvance_review;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public MRPAdvancePresenter createPresenter() {
        return new MRPAdvancePresenter(this);
    }

    @Override
    public MRPAdvanceView createView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
