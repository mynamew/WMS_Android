package com.timi.sz.wms_android.mvp.UI.quity.normal_review;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.bean.quality.mrp.MrpReviewData;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.text.NumberFormat;

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
    @BindView(R.id.tv_send_quality_num)
    TextView tvSendQualityNum;
    @BindView(R.id.tv_quality_num)
    TextView tvQualityNum;
    @BindView(R.id.tv_badbess_total_num)
    TextView tvBadbessTotalNum;
    @BindView(R.id.tv_badness_percent)
    TextView tvBadnessPercent;
    @BindView(R.id.tv_quality_date)
    TextView tvQualityDate;
    @BindView(R.id.tv_mrp_normal_checker)
    TextView tvMrpNormalChecker;
    @BindView(R.id.tv_select_mrpreview_result)
    TextView tvSelectMrpreviewResult;
    @BindView(R.id.iv_mrp_down)
    ImageView ivMrpDown;
    private MrpReviewData mrpReviewData;

    @Override
    public int setLayoutId() {
        return R.layout.activity_mrpnormal_review;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        mrpReviewData = new Gson().fromJson(getIntent().getStringExtra("NormalReviewDetail"), MrpReviewData.class);
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
            setTextViewText(tvReceiveNum, R.string.receive_pro_num, mrpReviewData.getReceiptCode());
            /**
             *质检日期
             */
            setTextViewText(tvQualityDate, R.string.tv_mrp_quality_date, mrpReviewData.getCheckDate());
            /**
             * 订单号
             */
            setTextViewText(tvOrderNum, R.string.order_no, mrpReviewData.getSourceBillCode());
            /**
             * 质检人
             */
            setTextViewText(tvMrpNormalChecker, R.string.mrp_normal_checker, mrpReviewData.getChecker());
            /**
             * 供应商
             */
            setTextViewText(tvSupplier, R.string.buy_from, mrpReviewData.getSupplierName());
            /**
             * 物品编码
             */
            setTextViewText(tvMaterialCode, R.string.material_code, mrpReviewData.getMaterialCode());
            /**
             * 物料名称
             */
            setTextViewText(tvMaterialName, R.string.material_name, mrpReviewData.getMaterialName());
            /**
             * 规格型号
             */
            setTextViewText(tvMaterialModel, R.string.material_model, mrpReviewData.getMaterialStandard());
            /**
             * 实收数
             */
            setTextViewText(tvReceiveNum, R.string.receive_num, mrpReviewData.getReceiveQty());
            /**
             * 送检数
             */
            setTextViewText(tvSendQualityNum, R.string.send_quality_num_tip, mrpReviewData.getSampleQty());
            /**
             * 合格数
             */
            setTextViewText(tvQualityNum, R.string.mrp_quality_num_tip, mrpReviewData.getPassQty());
            /**
             * 不良总数
             */
            setTextViewText(tvBadbessTotalNum, R.string.mrp_normal_badness_total_num, mrpReviewData.getNgQty());
            /**
             * 不良率
             */
            NumberFormat nFromat = NumberFormat.getPercentInstance();
            String rates = nFromat.format(mrpReviewData.getNgQty() / mrpReviewData.getReceiveQty());
            setTextViewText(tvBadnessPercent, R.string.mrp_normal_badness_percent, rates);
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


    @OnClick({R.id.tv_confirm_review, R.id.rl_select_review_result})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm_review://确认评审结果
                break;
            case R.id.rl_select_review_result://选择评审结果
                showSelectReviewResultPopWindow(view);
                break;
        }
    }

    /**
     * 弹出选择评审结果的选择
     */
    private void showSelectReviewResultPopWindow(View view) {
        PopupWindow popwindow = new PopupWindow(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop_mrp_review_result, null);
        popwindow.setContentView(inflate);
        popwindow.showAsDropDown(view);
    }
}
