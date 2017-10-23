package com.timi.sz.wms_android.mvp.UI.quity.advance_quality;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;
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
     * 选择的不良原因的集合
     */
    private List<FaultData> mSelectFaultData = new ArrayList<>();

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
    TextView tvCode, tvCheckItem, tvCheckMode;
    EditText etMeasure;
    RadioButton rdCheckQualitied, rdCheckUnQualitied;
    Button dialogBtnNext;

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

    @Override
    public void setAdvance2Data() {
        sampleCode += 1;
        currentCheckposition+=1;
        showCheckItemDialog(false,currentCheckposition);
    }

    @Override
    public void submitFinish() {

    }


    @OnClick({R.id.tv_next, R.id.tv_quality})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                break;
            case R.id.tv_quality:
                showCheckItemDialog(false, -1);
                break;
        }
    }

    public void showCheckItemDialog(final boolean isUpdateResult, final int position) {
        /**
         * 检测项目的实体
         */
        final GetAdvance2Data.CheckItemBean checkItemBean = mData.getCheckItem().get(currentCheckposition);
        /**
         * 不良原因的数据
         */
        final List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemBean.getFaultData();
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
        /**
         * 初始化dialog
         */
        if (null == checkItemDialog) {
            checkItemDialog = new MyDialog(AdvanceQualityActivity.this, R.layout.dialog_quality_check_item);
            /**
             * 初始化 各个控件
             */
            tvCode = checkItemDialog.getTextView(R.id.tv_sample_code);
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
            spBadnessReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    /**
                     * 对于 是否是更改已经检测过的检查项目
                     * 1、如果是检测过 则对点击的position 的mSelectFaultData数据进行处理
                     * 2、为检测过 则直接加入到mSelectData
                     */
                    if (!isUpdateResult) {
                        /**
                         *  对不良原因的数量 进行加1的操作
                         */
                        mSelectFaultData.add(new FaultData(faultData.get(i).getFaultId(), faultData.get(i).getFaultQty() + 1));
                    } else {
                        /**
                         * 由于不良数已经加1了 所以直接更改id即可
                         */
                        for (int j = 0; j < mSelectFaultData.size(); j++) {
                            /**
                             * 如果id相等 则更改其id
                             */
                            if (mSelectFaultData.get(i).FaultId == faultData.get(position).getFaultId()) {
                                /**
                                 * 将用户选择的id替换掉已经存储的id
                                 */
                                mSelectFaultData.get(i).FaultId = faultData.get(i).getFaultId();
                            }
                        }
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
                    int measureValue = Integer.parseInt(measureStr);
                    /**
                     * 设置是否合格
                     */
                    if (measureValue >= checkItemBean.getLimitLow() && measureValue <= checkItemBean.getLimitHigh()) {
                        rdCheckQualitied.setChecked(true);
                    } else {
                        rdCheckUnQualitied.setChecked(true);
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
                    /**
                     * 如果有下一项则直接检查下一项， 否则直接关闭
                     */
                    if (currentCheckposition != mData.getCheckItem().size() - 1) {
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
                        /**
                         * 计算不良原因
                         */
                        getPresenter().setAdvance2Data(params);
                    } else {
                        tvCode.setText(String.valueOf(sampleCode));
                        tvCheckItem.setText(checkItemBean.getCheckItemName());
                        tvCheckMode.setText(checkItemBean.getJudgeType() == 1 ? "自动判断" : "认为判断");
                        badnessAdapter.notifyDataSetChanged();
                    }
                }
            });

        }
        tvCode.setText(String.valueOf(sampleCode));
        tvCheckItem.setText(checkItemBean.getCheckItemName());
        tvCheckMode.setText(checkItemBean.getJudgeType() == 1 ? "自动判断" : "认为判断");
        badnessAdapter.notifyDataSetChanged();
        etMeasure.setHint(getString(R.string.please_input_measure_value));
        checkItemDialog.show();

    }

    /**
     * 质检 检查项目的结果
     */
    class QualityResult {
        Long CheckItemId;
        String QCValue;
        String QCResult;
        Long FaultIdl;
        String Remark;

        public QualityResult(Long checkItemId, String QCValue, String QCResult, Long faultIdl, String remark) {
            CheckItemId = checkItemId;
            this.QCValue = QCValue;
            this.QCResult = QCResult;
            FaultIdl = faultIdl;
            Remark = remark;
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
