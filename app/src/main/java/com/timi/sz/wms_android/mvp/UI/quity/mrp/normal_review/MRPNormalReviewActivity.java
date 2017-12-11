package com.timi.sz.wms_android.mvp.UI.quity.mrp.normal_review;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.mrp.MrpReviewData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.MrpEvent;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * mrp  普通评审详情
 * author: timi
 * create at: 2017/10/17 16:54
 */
public class MRPNormalReviewActivity extends BaseActivity<MRPNormalReviewView, MRPNormalReviewPresenter> implements MRPNormalReviewView {

    @BindView(R.id.tv_confirm_review)
    TextView tvConfirmReview;
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_receive_date)
    TextView tvReceiveDate;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_supplier)
    TextView tvSupplier;
    @BindView(R.id.tv_checker)
    TextView tvChecker;
    @BindView(R.id.tv_check_date)
    TextView tvCheckDate;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_receive_num)
    TextView tvReceiveNum;
    @BindView(R.id.tv_send_check_num)
    TextView tvSendCheckNum;
    @BindView(R.id.tv_pass_num)
    TextView tvPassNum;
    @BindView(R.id.tv_badness_total_num)
    TextView tvBadnessTotalNum;
    @BindView(R.id.tv_badness_percent)
    TextView tvBadnessPercent;
    @BindView(R.id.rd_pick)
    RadioButton rdPick;
    @BindView(R.id.rd_quality_special_get)
    RadioButton rdQualitySpecialGet;
    @BindView(R.id.rd_quality_all_return)
    RadioButton rdQualityAllReturn;
    @BindView(R.id.rd_quality_right_get)
    RadioButton rdQualityRightGet;
    @BindView(R.id.rg_qualified)
    RadioGroup rgQualified;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.tv_qctype_tip)
    TextView tvQctypeTip;
    @BindView(R.id.et_pick_num)
    EditText etPickNum;
    @BindView(R.id.et_mrp_normal_mark)
    EditText etMrpNormalMark;
    @BindView(R.id.tv_fatal_badness_num)
    TextView tvFatalBadnessNum;
    @BindView(R.id.tv_serious_badness_num)
    TextView tvSeriousBadnessNum;
    @BindView(R.id.tv_common_badness_num)
    TextView tvCommonBadnessNum;
    @BindView(R.id.tv_slight_badness_num)
    TextView tvSlightBadnessNum;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.tv_standard_level)
    TextView tvStandardLevel;
    @BindView(R.id.tv_check_method)
    TextView tvCheckMethod;
    @BindView(R.id.tv_mrp_sample_half_yard)
    TextView tvMrpSampleHalfYard;
    @BindView(R.id.tv_aql)
    TextView tvAQL;
    @BindView(R.id.layout_advance_work)
    LinearLayout layoutAdvanceWork;
    private MrpReviewData mrpReviewData;
    /**
     * 评审结果  默认值是-1 未做任何操作
     */
    private int qcReviewResult = -1;
    /**
     * 1：普捡  2：高检1  3：高检2
     */
    private int qcType = 1;

    @Override
    public int setLayoutId() {
        return R.layout.activity_mrpnormal_review;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        mrpReviewData = new Gson().fromJson(getIntent().getStringExtra("ReviewDetail"), MrpReviewData.class);
        qcType = getIntent().getIntExtra("QcType", 1);
    }

    @Override
    public void initView() {
        /**
         * 设置物料信息
         */
        if (null != mrpReviewData) {
            /**
             * 到货单号
             */
            tvReceiveNum.setText(mrpReviewData.getReceiptCode());
            /**
             *质检日期
             */
            tvCheckDate.setText(mrpReviewData.getCheckDate());
            /**
             * 订单号
             */
            tvOrderNum.setText(mrpReviewData.getSourceBillCode());
            /**
             * 到货日期
             */
            tvReceiveDate.setText(mrpReviewData.getReceiptDate());
            /**
             * 质检人
             */
            tvChecker.setText(mrpReviewData.getChecker());
            /**
             * 供应商
             */
            tvSupplier.setText(mrpReviewData.getSupplierName());
            /**
             * 物品编码
             */
            tvMaterialCode.setText(mrpReviewData.getMaterialCode());
            /**
             * 物料名称
             */
            tvMaterialName.setText(mrpReviewData.getMaterialName());
            /**
             * 规格型号
             */
            tvMaterialModel.setText(mrpReviewData.getMaterialStandard());
            /**
             * 实收数
             */
            tvReceiveNum.setText(String.valueOf(mrpReviewData.getReceiveQty()));
            /**
             * 送检数
             */
            tvSendCheckNum.setText(String.valueOf(mrpReviewData.getSampleQty()));
            /**
             * 合格数
             */
            tvPassNum.setText(String.valueOf(mrpReviewData.getPassQty()));
            /**
             * 不良总数
             */
            tvBadnessTotalNum.setText(String.valueOf(mrpReviewData.getNgQty()));
            /**
             * 不良率
             */
            NumberFormat nFromat = NumberFormat.getPercentInstance();
            String rates = nFromat.format((Double.parseDouble(String.valueOf(mrpReviewData.getNgQty())) / Double.parseDouble(String.valueOf(mrpReviewData.getSampleQty()))));
            tvBadnessPercent.setText(rates);
            /**
             * 设置标题和普捡 高检1 文本
             */
            switch (qcType) {
                case 1:
                    tvQctypeTip.setText(getString(R.string.mrp_normal_review_work_tip));
                    setActivityTitle(getString(R.string.mrp_normal_check_title));
                    layoutAdvanceWork.setVisibility(View.GONE);
                    break;
                case 2:
                    tvQctypeTip.setText(R.string.mrp_reveiw_advance1);
                    setActivityTitle(getString(R.string.mrp_review_advance1_title));
                    layoutAdvanceWork.setVisibility(View.GONE);
                    break;
                case 3:
                    tvQctypeTip.setText(R.string.mrp_review_advance2_tip);
                    setActivityTitle(getString(R.string.mrp_review_advance2_title));
                    layoutAdvanceWork.setVisibility(View.VISIBLE);
                    tvFatalBadnessNum.setText(String.valueOf(mrpReviewData.getFatalQty()));
                    tvSeriousBadnessNum.setText(String.valueOf(mrpReviewData.getSeriousQty()));
                    tvCommonBadnessNum.setText(String.valueOf(mrpReviewData.getCommonlyQty()));
                    tvSlightBadnessNum.setText(String.valueOf(mrpReviewData.getSlightQty()));
                    tvStandardLevel.setText(String.valueOf(mrpReviewData.getCurrentLevel()));
                    tvMrpSampleHalfYard.setText(mrpReviewData.getSampleCode());
                    tvAQL.setText(mrpReviewData.getCurrentAQL());
                    //严格度
                    tvCheckMethod.setText(mrpReviewData.getCurrentStrict());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public MRPNormalReviewPresenter createPresenter() {
        return new MRPNormalReviewPresenter(this);
    }

    @Override
    public MRPNormalReviewView createView() {
        return this;
    }


    @OnClick({R.id.tv_confirm_review,})
    public void onViewClicked(View view) {
        InputMethodUtils.hide(this);
        switch (view.getId()) {
            case R.id.tv_confirm_review://确认评审结果
                /**
                 * 评审结果
                 */
                if (rdPick.isChecked()) {//挑选
                    qcReviewResult = 1;
                }
                if (rdQualitySpecialGet.isChecked()) {//特采
                    qcReviewResult = 2;
                }
                if (rdQualityAllReturn.isChecked()) {//全退
                    qcReviewResult = 3;
                }
                if (rdQualityRightGet.isChecked()) {//正采
                    qcReviewResult = 4;
                }
                if (qcReviewResult == -1) {//提示
                    ToastUtils.showShort(getString(R.string.please_select_mrp_review_result));
                    return;
                }
                String pickNumStr = etPickNum.getText().toString();
                if (TextUtils.isEmpty(pickNumStr)) {
                    ToastUtils.showShort(getString(R.string.please_input_pick_num));
                    return;
                }
                int pickNum = Integer.parseInt(pickNumStr);
                if (pickNum > mrpReviewData.getReceiveQty()) {//挑选或特采数量大于实收数
                    ToastUtils.showShort(getString(R.string.mrp_more_receive_num_tip));
                    return;
                }
                /**
                 * 备注
                 */
                String mark = etMrpNormalMark.getText().toString();
                /**
                 * 提交请求
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("mac", PackageUtils.getMac());
                params.put("Remark", mark);
                params.put("ReviewQty", pickNum);
                params.put("QCReview", qcReviewResult);
                params.put("QCId", mrpReviewData.getQcId());
                getPresenter().setMRPReviewData(params);
                break;
        }
    }

    @Override
    public void setMrpReviewData() {
        BaseMessage.post(new MrpEvent(MrpEvent.MRP_REVIEW_SUCCESS));
        onBackPressed();
    }
}
