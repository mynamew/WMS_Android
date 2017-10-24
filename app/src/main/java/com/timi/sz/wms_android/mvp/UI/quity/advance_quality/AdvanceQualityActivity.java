package com.timi.sz.wms_android.mvp.UI.quity.advance_quality;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.UI.quity.advance1_quality.Advance1QualityActivity;
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
    @BindView(R.id.et_refuse_receive_num)
    EditText etRejectNum;
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
     * 不练原因的适配器
     */
    ArrayAdapter<String> badnessAdapter;
    /**
     * 检测项目的view
     */
    TextView tvCode, tvCheckItem, tvCheckMode, tvLimmitLow, tvLimmitHigh;
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
        setTextViewText(tvOrderno, R.string.receive_pro_num, normalSummary.getReceiptCode());
        setTextViewText(tvReceiveMaterialDate, R.string.receive_material_date, normalSummary.getReceiptDate());
        setTextViewText(tvOrderno, R.string.receive_pro_num, normalSummary.getReceiptCode());
        setTextViewText(tvSupplier, R.string.buy_from, normalSummary.getSupplierName());
        setTextViewText(tvMaterialCode, R.string.material_code, normalSummary.getMaterialCode());
        setTextViewText(tvMaterialName, R.string.material_name, normalSummary.getMaterialName());
        setTextViewText(tvMaterialModel, R.string.material_model, normalSummary.getMaterialStandard());
        setTextViewText(tvOrderNum, R.string.order_no, normalSummary.getSourceBillCode());
        setTextViewText(tvSampleCode2, R.string.sample_code_format, String.valueOf(sampleCode));
        tvSampleCode.setText(String.valueOf(sampleCode));
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
        } else {
            sampleCode += 1;
            currentCheckposition = 0;
            showCheckItemDialog(false, currentCheckposition);
        }
    }

    /**
     * 显示 不合格用户是否继续质检的dialog
     */
    private void showUnPassDialog() {
        MyDialog myDialog = new MyDialog(this, R.layout.dialog_unpass_user_choose);
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
        int rejectNum = Integer.parseInt(tvRefuseReceiveNum.getText().toString().trim());
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
                 * 存储样品编码对用的样品检查项目的数据
                 * 1、为了存储相应的检验数据
                 * 2、为了多条目的样品数据做准备
                 *
                 * 当点击检验的按钮时 进行添加存储的数据，
                 * 如果samplecode的相应的数据已存在，则不进行添加
                 */
                if (null == checkItemDatas.get(sampleCode)) {
                    checkItemDatas.put(sampleCode, mData.getCheckItem());
                }
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
            etMeasure = checkItemDialog.getEdittext(R.id.et_measure_value);
            rdCheckQualitied = (RadioButton) checkItemDialog.getView(R.id.rd_qualified);
            rdCheckUnQualitied = (RadioButton) checkItemDialog.getView(R.id.rd_unqualified);
            dialogBtnNext = (Button) checkItemDialog.getView(R.id.btn_next);
            Spinner spBadnessReason = (Spinner) checkItemDialog.getView(R.id.spinner_badness_reason);
            badnessAdapter = new ArrayAdapter<>(AdvanceQualityActivity.this, android.R.layout.simple_list_item_1, badnessReasons);
            badnessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spBadnessReason.setAdapter(badnessAdapter);
            /**
             * 选择的监听器
             */
            spBadnessReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    /**
                     *  是否是更改已经检测过的检查项目
                     *
                     */
                    if (!isUpdateResult) {
                        /**
                         *  对不良原因的数量 进行加1的操作
                         *  1、 samplecode  样品编码
                         *  2、currentCheckposition 当前检验项目的位置（也就是检验的项目）
                         *  3、设置 checkItemDatas的不良原因的数量t
                         */
                        List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemDatas.get(sampleCode).get(currentCheckposition).getFaultData();
                        GetAdvance2Data.CheckItemBean.FaultDataBean faultDataBean = faultData.get(i);
                        faultDataBean.setFaultQty(faultDataBean.getFaultQty() + 1);
                    } else {
                        /**
                         * 如果是更改数据的话
                         */
                        List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemDatas.get(sampleCode).get(position).getFaultData();
                        /**
                         * 对点击更改项目的faultdata进行清空（因为每一个检验的项目必然是只有一个或者0个不良数）
                         */
                        for (int j = 0; j < faultData.size(); j++) {
                            faultData.get(i).setFaultQty(0);
                        }
                        /**
                         * 设置不良数
                         */
                        GetAdvance2Data.CheckItemBean.FaultDataBean faultDataBean = faultData.get(i);
                        faultDataBean.setFaultQty(faultDataBean.getFaultQty() + 1);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            /**
             * 确定的按钮的监听器
             * 用于点击计算 合格  不合格的判断
             */
            checkItemDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
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
                     * 如果有下一项则直接检查下一项， 否则直接关闭
                     */
                    if (currentCheckposition == mData.getCheckItem().size() - 1) {
                        checkItemDialog.dismiss();
                        /**
                         * 设置高级质检2的结果
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("mac", PackageUtils.getMac());
                        params.put("ReceiptId", receiptId);
                        params.put("ReceiptDetailId", receiptDetailId);
                        params.put("Remark", "");
                        GetAdvance2Data.AdvanceSummaryBean advanceSummary = mData.getAdvanceSummary();
                        params.put("CurrentStrict", advanceSummary.getCurrentStrict());
                        params.put("CurrentLevel", advanceSummary.getCurrentLevel());
                        params.put("SampleCode", advanceSummary.getSampleCode());
                        params.put("BeginQty", advanceSummary.getBeginQty());
                        params.put("EndQty", advanceSummary.getEndQty());
                        params.put("CurrentAQL", advanceSummary.getCurrentAQL());
                        params.put("AQLAcceptQty", advanceSummary.getAqlAcceptQty());
                        params.put("AQLRejectQty", advanceSummary.getAqlRejectQty());
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
                        //设置质检未完成
                        params.put("QCStatus", 2);
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
                        int rejectNum = 0;
                        /**
                         * 不良总数
                         */
                        int totalBadnessNum = 0;
                        /**
                         * 不良原因的链表
                         */
                        List<FaultData> mSelectFaultData = new ArrayList<FaultData>();
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
                            for (int key = 1; key < sampleCode; key++) {
                                List<GetAdvance2Data.CheckItemBean> checkItemBeen = checkItemDatas.get(key);
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
                                        mSelectFaultData.add(new FaultData(faultDataBean.getFaultId(), faultDataBean.getFaultQty()));
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
                            //允许拒收数
                            int aqlRejectQty = mData.getAdvanceSummary().getAqlRejectQty();
                            int aqlAcceptQty = mData.getAdvanceSummary().getAqlAcceptQty();
                            //拒收数
                            /**
                             *  1、当拒收数 大于等于允许数并且小于拒收数的时候  qcResult = 2; 待定
                             *  2、当拒收数 大于等于拒收数                     qcResult = 2; 不合格
                             */
                            if (rejectNum >= aqlAcceptQty && rejectNum < aqlRejectQty) {//待定的状态
                                qcResult = 2;
                            } else if (rejectNum >= aqlRejectQty) {
                                qcResult = 3;
                            }
                        }
                        params.put("QCResult", qcResult);
                        /**
                         * 设置不良缺陷的个数
                         */
                        params.put("FatalQty", FatalQty);
                        params.put("SeriousQty", SeriousQty);
                        params.put("CommonlyQty", CommonlyQty);
                        params.put("SlightQty", SlightQty);

                        /**
                         * 设置 不良原因的链表
                         */
                        params.put("FaultData", mSelectFaultData);
                        getPresenter().setAdvance2Data(params);
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
         * 刷新
         */
        badnessAdapter.notifyDataSetChanged();
        /**
         * 重置测量值
         */
        etMeasure.setText("");
        /**
         * 重置选择
         */
        rdCheckUnQualitied.setChecked(false);
        rdCheckUnQualitied.setChecked(false);
        /**
         * 设置hint
         */
        etMeasure.setHint(getString(R.string.please_input_measure_value));
        /**
         * 按钮的状态 是否显示为确定
         * 即当前检验的位置==检验项目的大小-1   currentCheckposition==mData.getCheckItem().size()-1
         */
        if (currentCheckposition == mData.getCheckItem().size() - 1) {
            tvNext.setText(getString(R.string.confirm));
        } else {
            tvNext.setText(getString(R.string.next_item));
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
     * 不良原因
     */
    class FaultData {
        int FaultId;
        int FaultQty;

        public FaultData(int faultId, int faultQty) {
            FaultId = faultId;
            FaultQty = faultQty;
        }
    }
}
