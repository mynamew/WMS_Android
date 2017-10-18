package com.timi.sz.wms_android.mvp.UI.quity.normal_review;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.mrp.MrpReviewData;
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
    @BindView(R.id.et_pick_num)
    EditText etPickNum;
    @BindView(R.id.et_mrp_normal_mark)
    EditText etMrpNormalMark;
    private MrpReviewData mrpReviewData;
    /**
     * 评审结果  默认值是-1 未做任何操作
     */
    private int qcReviewResult = -1;

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
                if (qcReviewResult == -1) {//提示
                    ToastUtils.showShort(getString(R.string.please_select_mrp_review_result));
                    return;
                }
                String pickNum = etPickNum.getText().toString();
                if (TextUtils.isEmpty(pickNum)) {
                    ToastUtils.showShort(getString(R.string.please_input_pick_num));
                    return;
                }
                String mark = etMrpNormalMark.getText().toString();
                /**
                 * 提交请求
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("mac", PackageUtils.getMac());
                params.put("Remark", mark);
                params.put("ReviewQty", Integer.parseInt(pickNum));
                params.put("QCReview", qcReviewResult);
                params.put("QCId", mrpReviewData.getQCId());
                getPresenter().setMRPReviewData(params);
                break;
            case R.id.rl_select_review_result://选择评审结果
                if (null != selectReviewResultPopWindow && selectReviewResultPopWindow.isShowing()) {
                    selectReviewResultPopWindow.dismiss();
                } else {
                    showSelectReviewResultPopWindow(view);
                }
                break;
        }
    }

    @Override
    public void setMrpReviewData() {
        onBackPressed();
    }

    PopupWindow selectReviewResultPopWindow;

    /**
     * 弹出选择评审结果的选择
     */
    private void showSelectReviewResultPopWindow(View view) {
        if (null == selectReviewResultPopWindow) {
            selectReviewResultPopWindow = new PopupWindow(this);
            View inflate = LayoutInflater.from(this).inflate(R.layout.pop_mrp_review_result, null);
            /**
             * 点击事件
             */
            inflate.findViewById(R.id.tv_pick).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /**
                     * 设置文本
                     */
                    tvSelectMrpreviewResult.setText(getString(R.string.quality_pick));
                    /**
                     * 结果的状态值
                     */
                    qcReviewResult = 1;
                    /**
                     * 小时
                     */
                    selectReviewResultPopWindow.dismiss();
                }
            });
            inflate.findViewById(R.id.tv_special_get).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvSelectMrpreviewResult.setText(getString(R.string.quality_special_get));
                    qcReviewResult = 2;
                    selectReviewResultPopWindow.dismiss();
                }
            });
            inflate.findViewById(R.id.tv_all_return).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvSelectMrpreviewResult.setText(getString(R.string.quality_all_return));
                    qcReviewResult = 3;
                    selectReviewResultPopWindow.dismiss();
                }
            });
            inflate.findViewById(R.id.tv_right_get).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvSelectMrpreviewResult.setText(getString(R.string.quality_right_get));
                    qcReviewResult = 4;
                    selectReviewResultPopWindow.dismiss();
                }
            });
            selectReviewResultPopWindow.setContentView(inflate);
            selectReviewResultPopWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
            selectReviewResultPopWindow.setOutsideTouchable(true);
        }
        selectReviewResultPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Animation animation = AnimationUtils.loadAnimation(MRPNormalReviewActivity.this, R.anim.rotation_up);
                animation.setFillAfter(true);
                ivMrpDown.startAnimation(animation);
            }
        });
        selectReviewResultPopWindow.showAsDropDown(view);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotation_down);
        animation.setFillAfter(true);
        ivMrpDown.startAnimation(animation);
    }


}
