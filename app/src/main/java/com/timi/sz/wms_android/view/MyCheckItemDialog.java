package com.timi.sz.wms_android.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.mvp.UI.quity.quality.advance_quality.Advance2Activity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.List;

/**
 * $dsc    检验项目
 * author: timi
 * create at: 2017-12-14 08:47
 */

public class MyCheckItemDialog {
    private TextView tvSampleCode, tvCheckItemName, tvCheckType, tvBadnessReason;
    private TextView tvLimitLow, tvLimitHigh, tvUnit;
    private EditText etMeasure;
    private Button btConfirm, btNext;
    private MyDialog mDialog;
    private LinearLayout llMeasureTip;
    private QualityResultView qualityResult;
    private LinearLayout rlSelectBadnessReason;
    private ImageView ivDown;
    private Context mContext;
    private RecyclerView rlvBadnessReason;

    private int faultId;//不良原因的id 值

    public MyCheckItemDialog(Context context) {
        mContext = context;
        mDialog = new MyDialog(context, R.layout.dialog_quality_check_item);
        //检验信息
        tvSampleCode = mDialog.findViewById(R.id.tv_sample_code);
        tvCheckItemName = mDialog.findViewById(R.id.tv_check_item);
        tvCheckType = mDialog.findViewById(R.id.tv_check_model);
        //不良原因
        tvBadnessReason = mDialog.findViewById(R.id.tv_select_result);
        //单位
        tvLimitLow = mDialog.findViewById(R.id.tv_limit_low);
        tvLimitHigh = mDialog.findViewById(R.id.tv_limit_hight);
        tvUnit = mDialog.findViewById(R.id.tv_unit);
        //输入框
        etMeasure = mDialog.findViewById(R.id.et_measure_value);
        //按钮
        btConfirm = mDialog.findViewById(R.id.btn_confirm);
        btNext = mDialog.findViewById(R.id.btn_next);
        llMeasureTip = mDialog.findViewById(R.id.ll_measure_tip);
        //检验结果
        qualityResult = mDialog.findViewById(R.id.quality_result_chech_item);
        //不良原因
        rlSelectBadnessReason = mDialog.findViewById(R.id.rl_select_badness_reason);
        //弹出不良原因列表
        ivDown = mDialog.findViewById(R.id.iv_down);
        //不良原因的列表
        rlvBadnessReason = mDialog.findViewById(R.id.rlv_badness_reason);
    }

    public MyCheckItemDialog updateInitData(List<GetAdvance2Data.CheckItemBean> checkItemBeans, final GetAdvance2Data.CheckItemDataBean listCheckItemBean, final CheckItemDialogListener listener) {
        //检验项目的实体
        GetAdvance2Data.CheckItemBean checkItemBean = null;
        //获取检验项目的实体
        for (int i = 0; i < checkItemBeans.size(); i++) {
            if (checkItemBeans.get(i).getCheckItemId() == listCheckItemBean.getCheckItemId()) {
                checkItemBean = checkItemBeans.get(i);
                break;
            }
        }
        //样品编码
        tvSampleCode.setText(String.valueOf(listCheckItemBean.getSampleSeq()));
        //检验名称
        tvCheckItemName.setText(checkItemBean.getCheckItemName());
        //检验方式
        tvCheckType.setText(checkItemBean.getJudgeType() == 1 ? mContext.getString(R.string.auto_judge) : mContext.getString(R.string.person_judge));
        qualityResult.setQualityResultCanClick(checkItemBean.getJudgeType() != 1);
        llMeasureTip.setVisibility(checkItemBean.getJudgeType() == 1 ? View.VISIBLE : View.GONE);
        //是否显示确认按钮
        btConfirm.setVisibility(checkItemBean.getJudgeType() == 1 ? View.VISIBLE : View.GONE);
        //设置修改
        btNext.setText(R.string.update);
        //设置单位 上限 和下限的值
        /**
         * 单位 （是否存在单位 进行判断）
         */
        if (!TextUtils.isEmpty(checkItemBean.getUnit())) {
            tvUnit.setText("(" + checkItemBean.getUnit() + ")");
        } else {
            tvUnit.setVisibility(View.GONE);
        }
        /**
         * 设置最高 和最低测量值
         */
        tvLimitLow.setText(String.valueOf(checkItemBean.getLimitLow()));
        tvLimitHigh.setText(String.valueOf(checkItemBean.getLimitHigh()));
        /**
         * 设置质检结果  0 合格 1 不合格
         */
        qualityResult.setQualityResult(listCheckItemBean.getQcResult() == 0 ? QualityResultView.QUALITY_RESULT_SUCCESS : QualityResultView.QUALITY_RESULT_FAIL);
        /**
         * 设置测量值
         */
        etMeasure.setText(listCheckItemBean.getQcValue());
        /**
         * 设置不良原因
         */
        List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemBean.getFaultData();
        for (int i = 0; i < faultData.size(); i++) {
            if (faultData.get(i).getFaultId() == listCheckItemBean.getFaultId()) {
                tvBadnessReason.setText(faultData.get(i).getFaultName());
            }
        }
        /**
         * 关闭弹窗
         */
        mDialog.setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
                dismissMyCheckItemDialog();
            }
        });
        mDialog.setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
                dismissMyCheckItemDialog();
            }
        });
        //确认
        final GetAdvance2Data.CheckItemBean finalCheckItemBean2 = checkItemBean;
        mDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
                /**
                 * 隐藏输入框
                 */
                InputMethodUtils.hide((Activity) mContext);
                /**
                 * 隐藏 不良原因
                 */
                hideBadnessReason();
                /**
                 * 测量值
                 */
                String measureStr = etMeasure.getText().toString().trim();
                if (TextUtils.isEmpty(measureStr)) {
                    ToastUtils.showShort(mContext.getString(R.string.please_input_measure_value));
                    return;
                }
                /**
                 * 输入的测量的数值
                 */
                float measureValue = 0;
                try {
                    measureValue = Float.parseFloat(measureStr);
                } catch (Exception e) {
                    ToastUtils.showShort(mContext.getString(R.string.please_input_right_value));
                    return;
                } finally {

                }
                /**
                 * 设置是否合格
                 */
                if (measureValue >= finalCheckItemBean2.getLimitLow() && measureValue <= finalCheckItemBean2.getLimitHigh()) {
                    qualityResult.setQualityResult(QualityResultView.QUALITY_RESULT_SUCCESS);
                    mDialog.getView(R.id.rl_select_badness_reason).setVisibility(View.GONE);
                    faultId = 0;

                } else {
                    qualityResult.setQualityResult(QualityResultView.QUALITY_RESULT_FAIL);
                    mDialog.getView(R.id.rl_select_badness_reason).setVisibility(View.VISIBLE);
                }
                listener.conftimClick();
            }
        });
        //设置不良原因的id
       faultId= listCheckItemBean.getFaultId();
        //下一步
        mDialog.setButtonListener(R.id.btn_next, null, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
                /**
                 * 隐藏输入框
                 */
                InputMethodUtils.hide((Activity) mContext);
                /**
                 * 隐藏 不良原因
                 */
                hideBadnessReason();
                /**
                 * 测量值
                 */
                String measureStr = etMeasure.getText().toString().trim();
                if (TextUtils.isEmpty(measureStr)) {
                    measureStr = "";
                }
                /**
                 * 选择了不合格 但是没选择不良原因 则提示用户选择不良原因，并且返回
                 */
                if (qualityResult.getQcResult()==QualityResultView.QUALITY_RESULT_FAIL&&faultId == 0) {
                    ToastUtils.showShort(mContext.getString(R.string.please_select_badness_reason));
                    return;
                }
                dialog.dismiss();
                listener.updateClick(measureStr, qualityResult.getQcResult(), listCheckItemBean.getCheckItemId(), faultId);
            }
        });
        /**
         * 显示/隐藏 不良原因
         */
        final GetAdvance2Data.CheckItemBean finalCheckItemBean = checkItemBean;
        final GetAdvance2Data.CheckItemBean finalCheckItemBean1 = checkItemBean;
        mDialog.setViewListener(R.id.rl_select_badness_reason, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
             if(rlvBadnessReason.getVisibility()==View.VISIBLE){
                 hideBadnessReason();
             }else {
                 Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotation_down);
                 animation.setFillAfter(true);
                 ivDown.startAnimation(animation);
                 rlvBadnessReason.setVisibility(View.VISIBLE);
                 List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData1 = finalCheckItemBean.getFaultData();
                 BaseRecyclerAdapter<GetAdvance2Data.CheckItemBean.FaultDataBean> baseRecyclerAdapter = new BaseRecyclerAdapter<GetAdvance2Data.CheckItemBean.FaultDataBean>(mContext, faultData1) {
                     @Override
                     protected int getItemLayoutId(int viewType) {
                         return R.layout.item_select_badness;
                     }

                     @Override
                     protected void bindData(RecyclerViewHolder holder, int position, GetAdvance2Data.CheckItemBean.FaultDataBean item) {
                         holder.setTextView(R.id.tv_content, item.getFaultName());
                     }
                 };
                 rlvBadnessReason.setAdapter(baseRecyclerAdapter);
                 rlvBadnessReason.setLayoutManager(new LinearLayoutManager(mContext));
                 baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                     @Override
                     public void onItemClick(View itemView, int pos) {
                         /**
                          * 是否是更改已经检测过的检查项目
                          */
                         /**
                          *  对不良原因的数量 进行加1的操作
                          *  1、 samplecode  样品编码
                          *  2、currentCheckposition 当前检验项目的位置（也就是检验的项目）
                          *  3、设置 checkItemDatas的不良原因的数量t
                          */
                         List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData2 = finalCheckItemBean1.getFaultData();
                         GetAdvance2Data.CheckItemBean.FaultDataBean faultDataBean = faultData2.get(pos);
                         faultDataBean.setFaultQty(faultDataBean.getFaultQty() + 1);
                         tvBadnessReason.setText(faultDataBean.getFaultName());
                         faultId = faultDataBean.getFaultId();
                         /**
                          * 隐藏不良原因
                          */
                         hideBadnessReason();
                     }
                 });
             }
            }
        });
        return this;
    }

    /**
     * 隐藏不良原因
     */
    public void hideBadnessReason() {
        /**
         * 增加判断 防止多次旋转
         **/
        if (rlvBadnessReason.getVisibility() == View.VISIBLE) {
            rlvBadnessReason.setVisibility(View.GONE);
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotation_up);
            animation.setFillAfter(true);
            ivDown.startAnimation(animation);
        }
    }

    /**
     * 隐藏 弹窗
     */
    public void dismissMyCheckItemDialog() {
        mDialog.dismiss();
        InputMethodUtils.hide(BaseActivity.getCurrentActivty());
    }

    /**
     * 显示弹出框
     */
    public void showMyCheckItenDialog() {
        mDialog.show();
    }

    /**
     * 检验项目的接口
     */
    public interface CheckItemDialogListener {
        void conftimClick();

        void updateClick(String measureValue, int qcResult, int checkItemId, int faultId);
    }
}
