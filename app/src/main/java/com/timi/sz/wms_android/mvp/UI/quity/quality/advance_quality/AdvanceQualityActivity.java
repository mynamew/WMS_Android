package com.timi.sz.wms_android.mvp.UI.quity.quality.advance_quality;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.CommitAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.UI.quity.reject.QualityRejectActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;
import com.timi.sz.wms_android.view.QualityResultView;
import com.timi.sz.wms_android.view.StandardLevelView;
import com.timi.sz.wms_android.view.WrapLayoutQualityResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @BindView(R.id.tv_sample_code)
    TextView tvSampleCode;
    @BindView(R.id.tv_sample_code2)
    TextView tvSampleCode2;
    @BindView(R.id.tv_quality)
    Button tvQuality;
    @BindView(R.id.tv_quality_result_tip)
    TextView tvQualityResultTip;
    @BindView(R.id.tv_refuse_receive_num)
    TextView tvRefuseReceiveNum;
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
    @BindView(R.id.stand_fatal_badness_num)
    StandardLevelView standFatalBadnessNum;
    @BindView(R.id.stand_serious_badness_num)
    StandardLevelView standSeriousBadnessNum;
    @BindView(R.id.stand_normal_badness_num)
    StandardLevelView standNormalBadnessNum;
    @BindView(R.id.stand_slight_badness_num)
    StandardLevelView standSlightBadnessNum;
    @BindView(R.id.stand_badness_total_num)
    StandardLevelView standBadnessTotalNum;
    @BindView(R.id.stand_badness_percent)
    StandardLevelView standBadnessPercent;
    //bundle
    private int receiptId;
    private int receiptDetailId;


    private GetAdvance2Data mData;
    /**
     * 检验项目的dialog
     */
    private MyDialog checkItemDialog;
    /**
     * 当前检验项目的位置
     */
    private int currentCheckposition = 0;

    /**
     * 检验的样品编码 从 1～服务器返回的样品数
     */
    private int sampleCode = 1;


    /**
     * 不良原因的字符串 链表
     */
    List<String> badnessReasons = new ArrayList<>();
    /**
     * 检验项目的链表
     */
    List<CommitAdvanceData.ItemDataBean> mSelectFaultData = new ArrayList<>();
    /**
     * 高检2提交的实体
     */
    private CommitAdvanceData mCommitAdvanceData = new CommitAdvanceData();
    /**
     * 检测项目的view
     */
    TextView tvCode, tvCheckItem, tvCheckMode, tvLimmitLow, tvLimmitHigh, tvSelectBadness;
    EditText etMeasure;
    RadioButton rdCheckQualitied, rdCheckUnQualitied;
    Button dialogBtnNext;
    /**
     * 每个sample code 所对应的数据，链表的目的：为了方便存储用户选择的不良原因，用于计算不良缺陷数
     * 1、checkitem的基础数据
     * 2、faultdata 即不良原因的链表
     */
    private SparseArray<List<GetAdvance2Data.CheckItemBean>> checkItemDatas = new SparseArray<>();
    /**
     * 样品质检的结果
     * 默认是合格
     */
    private int qcResult = 1;
    private int rejectNum = 0;

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
                showCheckItemDialog(true, position);
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

    @Override
    public void getAdvance2Data(GetAdvance2Data data) {
        mData = data;
        /**
         * 初始化数据
         */
        GetAdvance2Data.NormalSummaryBean normalSummary = data.getNormalSummary();
        GetAdvance2Data.AdvanceSummaryBean advanceSummary = data.getAdvanceSummary();
        /**
         * 设置相应的物料信息
         */
        setTextViewText(tvOrderno, R.string.item_arrive_orderno, normalSummary.getReceiptCode());
        setTextViewText(tvReceiveMaterialDate, R.string.receive_material_date, normalSummary.getReceiptDate());
        setTextViewText(tvOrderno, R.string.receive_pro_num, normalSummary.getReceiptCode());
        setTextViewText(tvSupplier, R.string.buy_from, normalSummary.getSupplierName());
        setTextViewText(tvMaterialCode, R.string.material_code, normalSummary.getMaterialCode());
        setTextViewText(tvMaterialName, R.string.material_name, normalSummary.getMaterialName());
        setTextViewText(tvMaterialModel, R.string.material_model, normalSummary.getMaterialStandard());
        setTextViewText(tvOrderNum, R.string.order_no, normalSummary.getSourceBillCode());
        setTextViewText(tvRefuseReceiveNum, R.string.reject_num_format, String.valueOf(0));
        /**
         * 设置质检操作的数据
         */
        setTextViewText(tvReceiveNum, R.string.receive_num, normalSummary.getReceiveQty());
        setTextViewText(tvSampleNum, R.string.mr_advance_sample_num, normalSummary.getSampleQty() + "(" + advanceSummary.getBeginQty() + "~" + advanceSummary.getEndQty() + ")");
        //不良数
        standFatalBadnessNum.setTextViewContent(advanceSummary.getFatalQty());
        standSeriousBadnessNum.setTextViewContent(advanceSummary.getSeriousQty());
        standNormalBadnessNum.setTextViewContent(advanceSummary.getCommonlyQty());
        standSlightBadnessNum.setTextViewContent(advanceSummary.getSlightQty());
        standBadnessTotalNum.setTextViewContent(0);
        standBadnessPercent.setTextViewContent("0 %");
        //AQL
        standAql.setTextViewContent(advanceSummary.getCurrentAQL());
        //试样半码
        standSampleHalfYard.setTextViewContent(advanceSummary.getSampleCode());
        //严格度
        standStrict.setTextViewContent(advanceSummary.getCurrentStrict());
        /**
         * 检验标准
         */
        //标准水平
        standQualityLevel.setTextViewContent(advanceSummary.getCurrentLevel());
        //AQL
        standAql.setTextViewContent(advanceSummary.getCurrentAQL());
        //试样半码
        standSampleHalfYard.setTextViewContent(advanceSummary.getSampleCode());
        //严格度
        standStrict.setTextViewContent(advanceSummary.getCurrentStrict());
        /**
         * 检验的条目
         */
        final List<GetAdvance2Data.CheckItemBean> checkItem = data.getCheckItem();
        if (null != checkItem && !checkItem.isEmpty()) {
            wrapQualityResult.setData(checkItem, this, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
        /**
         * 设置样品编码
         */
        if (null != mData.getCheckItemData() && !mData.getCheckItemData().isEmpty()) {
            sampleCode = mData.getCheckItemData().get(0).getSampleSeq()+1;
        }
        setTextViewText(tvSampleCode2, R.string.sample_code_format, String.valueOf(sampleCode));
        tvSampleCode.setText(String.valueOf(sampleCode));
    }

    /**
     * 是否显示过  当质检已经不合格的时候 弹出的用户选择的dialog
     */
    private boolean isShowChoose = false;

    @Override
    public void setAdvance2Data() {
        /**
         * 当样品检验为不合格的时候，并且没有弹出过不合格选择框的时候弹出
         */
        if (qcResult == 3 && !isShowChoose) {
            isShowChoose = true;
            showUnPassDialog();
            return;
        }
        /**
         * 判断是否自动显示检验的对话框
         */
        showCheckItemDialogJust();
    }

    /**
     * 判断是否自动显示检验的对话框
     */
    private void showCheckItemDialogJust() {
        /**
         * 如果是最后一个样品编码
         */
        if (sampleCode == mData.getNormalSummary().getSampleQty()) {
            tvSampleCode.setText(R.string.none);
            setTextViewText(tvSampleCode2, R.string.sample_code_format, R.string.none);
            wrapQualityResult.setVisibility(View.GONE);
        } else {
            /**
             * sampleCode 自增，currentCheckposition为0，
             */
            sampleCode += 1;
            currentCheckposition = 0;
            showCheckItemDialog(false, currentCheckposition);
            setTextViewText(tvSampleCode2, R.string.sample_code_format, String.valueOf(sampleCode));
            tvSampleCode.setText(String.valueOf(sampleCode));
        }
    }

    /**
     * 显示 不合格用户是否继续质检的dialog
     */
    private void showUnPassDialog() {
        final MyDialog myDialog = new MyDialog(this, R.layout.dialog_unpass_user_choose);
        myDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
                //质检确认
                /**
                 * 参数
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("mac", PackageUtils.getMac());
                params.put("ReceiptId", receiptId);
                params.put("ReceiptDetailId", receiptDetailId);
                getPresenter().submitFinish(params);
            }
        });
        /**
         * 当选择取消的时候，也就是i说用户选择继续进行质检
         */
        myDialog.setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
                dialog.dismiss();
                /**
                 * 判断是否自动显示检验的对话框
                 */
                showCheckItemDialogJust();
                /**
                 * 更改按钮状态设置成确定
                 */
                tvNext.setText(getString(R.string.confirm));
            }
        });
        /**
         * 当选择取消的时候，也就是i说用户选择继续进行质检
         */
        myDialog.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                /**
                 * 判断是否自动显示检验的对话框
                 */
                showCheckItemDialogJust();
                /**
                 * 更改按钮状态设置成确定
                 */
                tvNext.setText(getString(R.string.confirm));
            }
        });
        //设置不能被返回键取消
        myDialog.setCantCancelByBackPress();
        //不能点击外围取消
        myDialog.setCancelByOutside(false);
        myDialog.show();
    }

    @Override
    public void submitFinish() {
        /**
         * 发送质检成功的消息
         */
        BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_SUCCESS));
        /**
         *  如果质检合格的话 要对拒收数进行判断
         */
        switch (qcResult) {
            case 1://合格
                if (rejectNum > 0 && mData.getNormalSummary().isIsBarCode()) {//拒收数大于0  跳转到质检拒收并且有条码
                    /**
                     * 跳转到质检拒收
                     */
                    Intent intent = new Intent(AdvanceQualityActivity.this, QualityRejectActivity.class);
                    intent.putExtra("mData", new Gson().toJson(mData));
                    intent.putExtra("rejectNum", rejectNum);
                    startActivity(intent);
                } else {
                    tvNext.setText(getString(R.string.quality_complete));
                    ToastUtils.showShort(getString(R.string.normal_quality_tip));
                    onBackPressed();
                }
                break;
            case 2://待定  待定直接提示用户 质检结果为待定，并且关闭界面返回到清单的界面
                ToastUtils.showShort(getString(R.string.quality_wait_deal_tip));
                onBackPressed();
                break;
            case 3://不合格
                ToastUtils.showShort(getString(R.string.normal_unquality_tip));
                onBackPressed();
                break;
        }
    }


    @OnClick({R.id.tv_next, R.id.tv_quality})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                /**
                 * 当质检结果为合格的情况  则需要对样品的编码和样品数量做对比 ，用户是否完成了全部的样品检验
                 */
                if (qcResult == 1 || qcResult == 2) {
                    if (sampleCode == mData.getNormalSummary().getSampleQty()) {
                        //质检确认
                        /**
                         * 参数
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("mac", PackageUtils.getMac());
                        params.put("ReceiptId", receiptId);
                        params.put("ReceiptDetailId", receiptDetailId);
                        getPresenter().submitFinish(params);
                    } else {
                        ToastUtils.showShort("您的质检未完成，请继续检验剩余样品！");
                    }
                } else {//当质检不合格的时候点击确认按钮 则直接质检确认
                    //质检确认
                    /**
                     * 参数
                     */
                    Map<String, Object> params = new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("mac", PackageUtils.getMac());
                    params.put("ReceiptId", receiptId);
                    params.put("ReceiptDetailId", receiptDetailId);
                    getPresenter().submitFinish(params);
                }
                break;
            case R.id.tv_quality:
                /**
                 * 如果样品全部质检完成
                 */
                if (sampleCode == mData.getNormalSummary().getSampleQty()) {
                    ToastUtils.showShort(getString(R.string.quality_complete_tip));
                } else {
                    /**
                     * 显示检验项目的dialog
                     * 1、false 代表不是更改检验项目的结果
                     * 1、-1  代表更改项目的位置，由于不是更改检验项目的结果则传-1
                     */
                    showCheckItemDialog(false, -1);
                }
                break;
        }
    }

    /**
     * 检验项目的diaolog
     *
     * @param isUpdateResult 是否是更改检验结果
     * @param position       更改检验结果的位置
     */
    GetAdvance2Data.CheckItemBean checkItemBean;
    List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData;

    public void showCheckItemDialog(final boolean isUpdateResult, final int position) {
        /**
         * 存储样品编码对用的样品检查项目的数据
         * 1、为了存储相应的检验数据
         * 2、为了多条目的样品数据做准备
         *
         * 当点击检验的按钮时 进行添加存储的数据，
         * 如果samplecode的相应的数据已存在，则不进行添加
         */
        if (!isUpdateResult) {
            if (null == checkItemDatas.get(sampleCode)) {
                checkItemDatas.put(sampleCode, mData.getCheckItem());
            }
        }
        /**
         * 更改dialog 数据
         */
        updateDialogData(currentCheckposition);
        /**
         * 初始化dialog
         */
        if (null == checkItemDialog) {
            checkItemDialog = new MyDialog(AdvanceQualityActivity.this, R.layout.dialog_quality_check_item);
            /**
             * 初始化 各个控件
             */
            tvCode = checkItemDialog.getTextView(R.id.tv_sample_code);
            tvLimmitLow = checkItemDialog.getTextView(R.id.tv_limit_low);
            tvLimmitHigh = checkItemDialog.getTextView(R.id.tv_limit_hight);
            tvCheckItem = checkItemDialog.getTextView(R.id.tv_check_item);
            tvCheckMode = checkItemDialog.getTextView(R.id.tv_check_model);
            tvSelectBadness = checkItemDialog.getTextView(R.id.tv_select_result);
            etMeasure = checkItemDialog.getEdittext(R.id.et_measure_value);
            rdCheckQualitied = (RadioButton) checkItemDialog.getView(R.id.rd_qualified);
            rdCheckUnQualitied = (RadioButton) checkItemDialog.getView(R.id.rd_unqualified);
            dialogBtnNext = (Button) checkItemDialog.getView(R.id.btn_next);
            ivMrpDown = (ImageView) checkItemDialog.getView(R.id.iv_down);
            /**
             * 显示弹框
             */
            checkItemDialog.setViewListener(R.id.rl_select_badness_reason, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                   if(rdCheckUnQualitied.isChecked()){
                       if (null != selectReviewResultPopWindow && selectReviewResultPopWindow.isShowing()) {
                           selectReviewResultPopWindow.dismiss();
                       } else {
                           showSelectReviewResultPopWindow(checkItemDialog.getView(R.id.rl_select_badness_reason), isUpdateResult, position);
                       }
                   }
                }
            });
            /**
             * 确定的按钮的监听器
             * 用于点击计算 合格  不合格的判断
             */
            checkItemDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    /**
                     * 隐藏软键盘
                     */
                    InputMethodUtils.hide(AdvanceQualityActivity.this);
                    /**
                     * 测量值
                     */
                    String measureStr = etMeasure.getText().toString().trim();
                    if (TextUtils.isEmpty(measureStr)) {
                        ToastUtils.showShort(getString(R.string.please_input_measure_value));
                        return;
                    }
                    /**
                     * 输入的测量的数值
                     */
                    float measureValue = Float.parseFloat(measureStr);
                    /**
                     * 设置是否合格
                     */
                    if (measureValue >= checkItemBean.getLimitLow() && measureValue <= checkItemBean.getLimitHigh()) {
                        rdCheckQualitied.setChecked(true);
                        /**
                         * 设置当前检验的结果
                         */
                        setCheckItemResult(currentCheckposition, true);
                    } else {
                        rdCheckUnQualitied.setChecked(true);
                        setCheckItemResult(currentCheckposition, false);
                    }
                }
            });
            /**
             * 关闭dialog
             */
            checkItemDialog.setViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                }
            });
            checkItemDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if (null != selectReviewResultPopWindow && selectReviewResultPopWindow.isShowing()) {
                        selectReviewResultPopWindow.dismiss();
                    }
                }
            });
            checkItemDialog.setButtonListener(R.id.btn_next, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    String measureStr = etMeasure.getText().toString().trim();
                    /**
                     * 是自动判断 则强制要求输入测量值
                     * 认为判断   不强制要求
                     */
                    if (checkItemBean.getJudgeType() == 1) {//自动判断
                        if (TextUtils.isEmpty(measureStr)) {
                            ToastUtils.showShort(getString(R.string.please_input_measure_value));
                            return;
                        }
                    }
                    /**
                     * 如果 合格 和不合格的按钮都未被选中则证明未进行过检验  提示用户进行检验测量值
                     */
                    if (!rdCheckQualitied.isChecked() && !rdCheckUnQualitied.isChecked()) {
                        /**
                         * 是否是自动判断 影响其提示文字
                         */
                        if (checkItemBean.getJudgeType() == 1) {//自动判断
                            ToastUtils.showShort(getString(R.string.please_confirm_measure_value));
                        } else {
                            ToastUtils.showShort(getString(R.string.please_select_quality_result));
                        }
                        return;
                    }
                    /**
                     * 存储检验项目的结果
                     */
                    if (null == mCommitAdvanceData.getItemData()) {
                        mCommitAdvanceData.setItemData(new ArrayList<CommitAdvanceData.ItemDataBean>());
                    }
                    /**
                     * 清空链表
                     */
                    mCommitAdvanceData.getItemData().clear();
                    /**
                     * 设置链表中bean 的数据
                     */
                    List<GetAdvance2Data.CheckItemBean> checkItem = mData.getCheckItem();
                    GetAdvance2Data.CheckItemBean checkItemBean = checkItem.get(currentCheckposition);
                    CommitAdvanceData.ItemDataBean itemDataBean = new CommitAdvanceData.ItemDataBean();
                    itemDataBean.setCheckItemId(checkItemBean.getCheckItemId());
                    itemDataBean.setQCValue(TextUtils.isEmpty(measureStr) ? 0 : Integer.parseInt(measureStr));
                    itemDataBean.setQCResult(rdCheckQualitied.isChecked() ? 0 : 1);
                    itemDataBean.setRemark("");
                    if (rdCheckUnQualitied.isChecked()) {//不合格才有不良原因
                        /**
                         * 遍历链表 获取哪个不良原因被选中（不良数部位0  即是被选中）
                         */
                        List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemDatas.get(sampleCode).get(currentCheckposition).getFaultData();
                        int faultId = 0;
                        for (int i = 0; i < faultData.size(); i++) {
                            if (faultData.get(i).getFaultQty() != 0) {
                                faultId = faultData.get(i).getFaultId();
                                break;
                            }
                        }
                        /**
                         * 选择了不合格 但是没选择不良原因 则提示用户选择不良原因，并且返回
                         */
                        if(faultId==0){
                             ToastUtils.showShort("请选择不良原因！");
                            return;
                        }
                        itemDataBean.setFaultId(faultId);
                    } else {//合格 默认传0
                        itemDataBean.setFaultId(0);
                    }
                    mSelectFaultData.add(itemDataBean);
                    mCommitAdvanceData.setItemData(mSelectFaultData);
                    /**
                     * 如果有下一项则直接检查下一项， 否则直接关闭
                     */
                    if (currentCheckposition == mData.getCheckItem().size() - 1) {
                        LogUitls.e("当前选择的不合格的原因的数据--->", checkItemDatas.get(sampleCode).get(currentCheckposition).getFaultData());
                        checkItemDialog.dismiss();
                        /**
                         * 设置高级质检2的结果
                         */
                        mCommitAdvanceData.setUserId(SpUtils.getInstance().getUserId());
                        mCommitAdvanceData.setOrgId(SpUtils.getInstance().getOrgId());
                        mCommitAdvanceData.setMAC(PackageUtils.getMac());
                        mCommitAdvanceData.setReceiptId(receiptId);
                        mCommitAdvanceData.setReceiptDetailId(receiptDetailId);
                        GetAdvance2Data.AdvanceSummaryBean advanceSummary = mData.getAdvanceSummary();
                        mCommitAdvanceData.setCurrentStrict(advanceSummary.getCurrentStrict());
                        mCommitAdvanceData.setSampleQty(mData.getNormalSummary().getSampleQty());
                        mCommitAdvanceData.setCurrentLevel(advanceSummary.getCurrentLevel());
                        mCommitAdvanceData.setSampleCode(advanceSummary.getSampleCode());
                        mCommitAdvanceData.setBeginQty(advanceSummary.getBeginQty());
                        mCommitAdvanceData.setEndQty(advanceSummary.getBeginQty());
                        mCommitAdvanceData.setCurrentAQL(advanceSummary.getCurrentAQL());
                        mCommitAdvanceData.setAQLAcceptQty(advanceSummary.getAqlAcceptQty());
                        mCommitAdvanceData.setAQLRejectQty(advanceSummary.getAqlRejectQty());
                        mCommitAdvanceData.setQCQty(sampleCode);
                        mCommitAdvanceData.setRemark("");
                        //设置质检未完成
                        mCommitAdvanceData.setQCStatus(2);
                        /**
                         * 计算是否合格
                         */
                        boolean isPass = true;
                        /**
                         * 获取检验结果的view
                         * 如果有一个为不合格则证明 有不良数
                         */
                        SparseArray<QualityResultView> views = wrapQualityResult.getViews();
                        for (int i = 0; i < views.size(); i++) {
                            if (!wrapQualityResult.isPass(i)) {
                                isPass = false;
                            }
                        }
                        /**
                         * 不良缺陷的个数
                         */
                        int FatalQty = 0;//致命缺陷
                        int SeriousQty = 0;//严重缺陷
                        int CommonlyQty = 0;//一般缺陷
                        int SlightQty = 0;//轻微缺陷
                        /**
                         * 拒收的数量
                         */
                        rejectNum = 0;
                        /**
                         * 不良总数
                         */
                        int totalBadnessNum = 0;
                        /**
                         * 全部合格
                         */
                        if (isPass) {
                            //设置质检合格
                            qcResult = 1;
                        }
                        /**
                         * 不合格
                         * 1、进行拒收数量的验证：通过判断严重的AQL数、主要的AQL数、次要的AQL数是否在允收范围内验证，
                         */
                        else {
                            /**
                             * 计算所有的缺陷数
                             * 遍历链表
                             */
                            for (int key = 1; key <= sampleCode; key++) {
                                List<GetAdvance2Data.CheckItemBean> checkItemBeen = checkItemDatas.get(sampleCode);
                                for (int i = 0; i < checkItemBeen.size(); i++) {
                                    List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemBeen.get(i).getFaultData();
                                    GetAdvance2Data.CheckItemBean.FaultDataBean faultDataBean = faultData.get(i);
                                    switch (faultDataBean.getQC_DefectGrade()) {
                                        case "C"://致命缺陷
                                            FatalQty = FatalQty + faultDataBean.getFaultQty();
                                            break;
                                        case "B"://严重缺陷
                                            SeriousQty = SeriousQty + faultDataBean.getFaultQty();
                                            break;
                                        case "A"://一般缺陷
                                            CommonlyQty = CommonlyQty + faultDataBean.getFaultQty();
                                            break;
                                        case "S"://轻微缺陷
                                            SlightQty = SlightQty + faultDataBean.getFaultQty();
                                            break;
                                    }
                                    /**
                                     * 如果不良数的数量不为0 则设置到选择的不良原因的链表，用于提交数据
                                     */
                                    if (faultDataBean.getFaultQty() != 0) {
                                        /**
                                         * 如果不良原因的数量不为0 则需要再不良总数上+1
                                         */
                                        totalBadnessNum += 1;
                                    }
                                }
                                /**
                                 * 遍历不良原因的链表 ，如果有不良直接将拒收数+1 跳出循环
                                 */
                                for (int i = 0; i < checkItemBeen.size(); i++) {
                                    List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemBeen.get(i).getFaultData();
                                    GetAdvance2Data.CheckItemBean.FaultDataBean faultDataBean = faultData.get(i);
                                    if (faultDataBean.getFaultQty() != 0) {
                                        rejectNum += 1;
                                        break;
                                    }
                                }
                            }
                            //允许拒收数
                            int aqlRejectQty = mData.getAdvanceSummary().getAqlRejectQty();
                            int aqlAcceptQty = mData.getAdvanceSummary().getAqlAcceptQty();
                            //拒收数
                            /**
                             *  1、当拒收数 大于允许数并且小于拒收数的时候  qcResult = 2; 待定
                             *  2、当拒收数 大于等于拒收数                     qcResult = 3; 不合格
                             *  3、不同的qcResult 设置不同的质检结果（不是弹框的合不合格而是整个质检过程中的合不合格）
                             */
                            if (rejectNum > aqlAcceptQty && rejectNum < aqlRejectQty) {//待定的状态
                                qcResult = 2;
                                rdWaitDeal.setChecked(true);
                            } else if (rejectNum >= aqlRejectQty) {
                                qcResult = 3;
                                rdUnqualified.setChecked(true);
                            } else {
                                rdQualified.setChecked(true);
                            }
                            /**
                             * 设置拒收的数量
                             */
                            setTextViewText(tvRefuseReceiveNum, R.string.reject_num_format, String.valueOf(rejectNum));
                            /**
                             * 设置不良总数
                             */
                            standBadnessTotalNum.setTextViewContent(totalBadnessNum);
                            /**
                             * 设置不良缺陷数的设置
                             */
                            standFatalBadnessNum.setTextViewContent(FatalQty);
                            standSeriousBadnessNum.setTextViewContent(SeriousQty);
                            standNormalBadnessNum.setTextViewContent(CommonlyQty);
                            standSlightBadnessNum.setTextViewContent(SlightQty);
                        }
                        mCommitAdvanceData.setQCResult(qcResult);
                        mCommitAdvanceData.setNGQty(totalBadnessNum);
                        mCommitAdvanceData.setRejectQty(rejectNum);
                        /**
                         * 设置不良缺陷的个数
                         */
                        mCommitAdvanceData.setFatalQty(FatalQty);
                        mCommitAdvanceData.setSeriousQty(SeriousQty);
                        mCommitAdvanceData.setSlightQty(SlightQty);
                        mCommitAdvanceData.setCommonlyQty(CommonlyQty);
                        LogUitls.e("提交的数据-->", new Gson().toJson(mCommitAdvanceData));
                        getPresenter().setAdvance2Data(mCommitAdvanceData);
                    } else {
                        /**
                         * 更新位置
                         */
                        currentCheckposition += 1;
                        /**
                         * 更新数据
                         */
                        updateDialogData(currentCheckposition);
                        /**
                         *设置文字
                         */
                        updateDialogViewContent();
                    }
                }
            });

        }
        updateDialogViewContent();
        checkItemDialog.show();

    }

    /**
     * 更改dialog 的view 的内容
     */
    private void updateDialogViewContent() {
        tvCode.setText(String.valueOf(sampleCode));
        tvCheckItem.setText(checkItemBean.getCheckItemName());
        tvCheckMode.setText(checkItemBean.getJudgeType() == 1 ? "自动判断" : "认为判断");
        /**
         * 设置是否可点击
         */
        rdCheckQualitied.setClickable(checkItemBean.getJudgeType() != 1);
        rdCheckUnQualitied.setClickable(checkItemBean.getJudgeType() != 1);
        /**
         * 设置最高 和最低测量值
         */
        tvLimmitLow.setText(String.valueOf(checkItemBean.getLimitLow()));
        tvLimmitHigh.setText(String.valueOf(checkItemBean.getLimitHigh()));
        /**
         * 设置不良原因的文本
         */
        tvSelectBadness.setText(getString(R.string.please_select_badness_reason));
        /**
         * 重置测量值
         */
        etMeasure.setText("");
        rgQualified.clearCheck();
        /**
         * 设置hint
         */
        etMeasure.setHint(getString(R.string.please_input_measure_value));
        /**
         * 按钮的状态 是否显示为确定
         * 即当前检验的位置==检验项目的大小-1   currentCheckposition==mData.getCheckItem().size()-1
         */
        if (currentCheckposition == mData.getCheckItem().size() - 1) {
            dialogBtnNext.setText(getString(R.string.confirm));
        } else {
            dialogBtnNext.setText(getString(R.string.next_item));
        }
    }

    /**
     * 设置 dialog 的数据源
     *
     * @param currentCheckposition
     */
    private void updateDialogData(int currentCheckposition) {
        /**
         * 检测项目的实体
         */
        checkItemBean = mData.getCheckItem().get(currentCheckposition);
        /**
         * 不良原因的数据
         */
        faultData = checkItemBean.getFaultData();
        /**
         * 清空不良原因列表
         */
        badnessReasons.clear();
        /**
         * 设置所有的不良原因
         */
        for (int i = 0; i < faultData.size(); i++) {
            badnessReasons.add(faultData.get(i).getFaultName());
        }
    }

    /**
     * 设置高级检验的结果 也就是检验条目是否合格的显示
     *
     * @param currentCheckposition 当前检查的位置
     * @param isPass               是否合格
     */
    private void setCheckItemResult(int currentCheckposition, boolean isPass) {
        QualityResultView view = wrapQualityResult.getView(currentCheckposition);
        if (null != view) {//如果找到了view
            view.setQualityResult(isPass);
        }
    }

    /**
     * 选择不良原因的弹出框
     */
    PopupWindow selectReviewResultPopWindow;

    /**
     * 弹出选择评审结果的选择
     */
    private ImageView ivMrpDown;

    /**
     * @param view
     * @param isUpdateResult    是否是更改检查项目
     * @param checkItemposition 检查项目的位置（更改时点击）
     */
    private BaseRecyclerAdapter<GetAdvance2Data.CheckItemBean.FaultDataBean> baseRecyclerAdapter;

    private void showSelectReviewResultPopWindow(View view, final boolean isUpdateResult, final int checkItemposition) {
        selectReviewResultPopWindow=null;
        if (null == selectReviewResultPopWindow) {
            selectReviewResultPopWindow = new PopupWindow(this);
            View inflate = LayoutInflater.from(this).inflate(R.layout.popwindow_select_badness_reason, null);
            RecyclerView recyclerView = inflate.findViewById(R.id.rlv_select_badness_reason);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            baseRecyclerAdapter = new BaseRecyclerAdapter<GetAdvance2Data.CheckItemBean.FaultDataBean>(this, mData.getCheckItem().get(currentCheckposition).getFaultData()) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_select_badness;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, GetAdvance2Data.CheckItemBean.FaultDataBean item) {
                    holder.setTextView(R.id.tv_content, item.getFaultName());
                }
            };
            recyclerView.setAdapter(baseRecyclerAdapter);
            baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                    /**
                     * 是否是更改已经检测过的检查项目
                     */
                    if (!isUpdateResult) {
                        /**
                         *  对不良原因的数量 进行加1的操作
                         *  1、 samplecode  样品编码
                         *  2、currentCheckposition 当前检验项目的位置（也就是检验的项目）
                         *  3、设置 checkItemDatas的不良原因的数量t
                         */
                        List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemDatas.get(sampleCode).get(currentCheckposition).getFaultData();
                        GetAdvance2Data.CheckItemBean.FaultDataBean faultDataBean = faultData.get(pos);
                        faultDataBean.setFaultQty(faultDataBean.getFaultQty() + 1);
                        tvSelectBadness.setText(faultDataBean.getFaultName());
                    } else {
                        /**
                         * 如果是更改数据的话
                         */
                        List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemDatas.get(sampleCode).get(checkItemposition).getFaultData();
                        /**
                         * 对点击更改项目的faultdata进行清空（因为每一个检验的项目必然是只有一个或者0个不良数）
                         */
                        for (int j = 0; j < faultData.size(); j++) {
                            faultData.get(pos).setFaultQty(0);
                        }
                        /**
                         * 设置不良数
                         */
                        GetAdvance2Data.CheckItemBean.FaultDataBean faultDataBean = faultData.get(pos);
                        faultDataBean.setFaultQty(faultDataBean.getFaultQty() + 1);
                        tvSelectBadness.setText(faultDataBean.getFaultName());
                    }
                    selectReviewResultPopWindow.dismiss();
                }
            });
            selectReviewResultPopWindow.setContentView(inflate);
            selectReviewResultPopWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
            selectReviewResultPopWindow.setOutsideTouchable(false);
        }
        selectReviewResultPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Animation animation = AnimationUtils.loadAnimation(AdvanceQualityActivity.this, R.anim.rotation_up);
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
