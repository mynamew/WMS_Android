package com.timi.sz.wms_android.mvp.UI.quity.advance1_quality;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvanceData;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.mvp.UI.quity.nomal_quality.NormalQualityActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 高级质检1
 * author: timi
 * create at: 2017/10/13 9:51
 */
public class Advance1QualityActivity extends BaseActivity<Advance1QualityView, Advance1QualityPresenter> implements Advance1QualityView {

    @BindView(R.id.tv_next)
    TextView tvNext;
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
    @BindView(R.id.tv_spot_check_num)
    TextView tvSpotCheckNum;
    @BindView(R.id.et_spot_check_num)
    TextView etSpotCheckNum;
    @BindView(R.id.tv_refuse_receive_num)
    TextView tvRefuseReceiveNum;
    @BindView(R.id.et_refuse_receive_num)
    EditText etRefuseReceiveNum;
    @BindView(R.id.ll_spot_check)
    LinearLayout llSpotCheck;
    @BindView(R.id.tv_badness_total_num)
    TextView tvBadnessTotalNum;
    @BindView(R.id.et_badness_num)
    TextView etBadnessNum;
    @BindView(R.id.tv_badness_percent)
    TextView tvBadnessPercent;
    @BindView(R.id.et_badness_percent)
    TextView etBadnessPercent;
    @BindView(R.id.ll_badness)
    LinearLayout llBadness;
    @BindView(R.id.tv_badness_code)
    TextView tvBadnessCode;
    @BindView(R.id.tv_badness_reason)
    TextView tvBadnessReason;
    @BindView(R.id.tv_badness_num)
    TextView tvBadnessNum;
    @BindView(R.id.rlv_quality)
    RecyclerView rlvQuality;
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

    //bundle
    private int receiptId;
    private int receiptDetailId;
    /**
     * 保存实体
     */
    private List<GetAdvanceData.FaultDataBean> mFaultData;
    private MyDialog faultDataDialog;

    /**
     * 质检结果  默认是合格的
     * 1-质检合格  2-待定  3-不合格
     */
    private int QCResult = 1;
    /**
     * 质检的状态
     * 0 : 未质检 2：质检未完成 3:质检已完成
     */
    private int QCStatus = 0;
    private GetAdvanceData data;
    /**
     * 不良原因
     */
    private List<FaultData> mSelectFaultData = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_advance1_quality;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.quality_advance1_title));
        receiptId = getIntent().getIntExtra("ReceiptId", -1);
        receiptDetailId = getIntent().getIntExtra("ReceiptDetailId", -1);
    }

    @Override
    public void initView() {
        /**
         * 设置从质检清单获取到的数据，设置到当前界面
         */
        setTextViewText(tvOrderno, R.string.receive_pro_num, "");
        setTextViewText(tvReceiveMaterialDate, R.string.receive_material_date, "");
        setTextViewText(tvOrderNum, R.string.order_no, "");
        setTextViewText(tvSupplier, R.string.buy_from, "");
        setTextViewText(tvMaterialCode, R.string.material_code, "");
        setTextViewText(tvMaterialName, R.string.material_name, "");
        setTextViewText(tvMaterialModel, R.string.material_model, "");
        setTextViewText(tvReceiveNum, R.string.receive_num, "");

    }

    @Override
    public void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        params.put("ReceiptId", receiptId);
        params.put("ReceiptDetailId", receiptDetailId);
        getPresenter().getAdvance1Data(params);
    }

    @Override
    public Advance1QualityPresenter createPresenter() {
        return new Advance1QualityPresenter(this);
    }

    @Override
    public Advance1QualityView createView() {
        return this;
    }


    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        params.put("ReceiptId", receiptId);
        params.put("ReceiptDetailId", receiptDetailId);
        /**
         * 判断是否输入了 拒收的数量
         */
        String badnessStr = etRefuseReceiveNum.getText().toString();
        if (TextUtils.isEmpty(badnessStr)) {
            ToastUtils.showShort(getString(R.string.please_input_reject_num));
            return;
        }
        /**
         * 不良总数
         */
        String badnessNum = tvBadnessTotalNum.getText().toString();
        /**
         * 不良率
         */
        String badnessPercent = tvBadnessPercent.getText().toString();
        GetAdvanceData.NormalSummaryBean normalSummary = data.getNormalSummary();
        params.put("SampleQty", normalSummary.getSampleQty());
        params.put("NGQty", Integer.parseInt(badnessNum));
        params.put("QCStatus", QCStatus);
        params.put("QCResult", QCResult);
        params.put("Remark", "");
        GetAdvanceData.AdvanceSummaryBean advanceSummary = data.getAdvanceSummary();
        params.put("CurrentStrict", advanceSummary.getCurrentStrict());
        params.put("CurrentLevel", advanceSummary.getCurrentLevel());
        params.put("SampleCode", advanceSummary.getSampleCode());
        params.put("BeginQty", advanceSummary.getBeginQty());
        params.put("EndQty", advanceSummary.getEndQty());
        params.put("CurrentAQL", advanceSummary.getCurrentAQL());
        params.put("AQLAcceptQty", advanceSummary.getAcceptAQL());
        params.put("AQLRejectQty", advanceSummary.getRejectAQL());
        /**
         * 计算缺陷的个数
         */
        int FatalQty = 0;//致命缺陷
        int SeriousQty = 0;//严重缺陷
        int CommonlyQty = 0;//一般缺陷
        int SlightQty = 0;//轻微缺陷
        /**
         * 根据选择了的不良原因 设置其缺陷的个数
         */
        for (int i = 0; i < mFaultData.size(); i++) {
            GetAdvanceData.FaultDataBean faultDataBean = mFaultData.get(i);
            switch (faultDataBean.getqC_DefectGrade()) {
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
        }
        params.put("FatalQty", FatalQty);
        params.put("SeriousQty", SeriousQty);
        params.put("CommonlyQty", CommonlyQty);
        params.put("SlightQty", SlightQty);
        /**
         * 设置  不良原因的链表
         */
        mSelectFaultData.clear();
        for (int i = 0; i < mFaultData.size(); i++) {
            mSelectFaultData.add(new FaultData(mFaultData.get(i).getFaultId(), mFaultData.get(i).getFaultQty()));
        }
        params.put("FaultData", mSelectFaultData);
    }

    @Override
    public void getAdvance1Data(final GetAdvanceData data) {
        GetAdvanceData.NormalSummaryBean normalSummary = data.getNormalSummary();
        if (null != normalSummary) {
            /**
             * 抽样数
             */
            tvSpotCheckNum.setText(String.valueOf(normalSummary.getSampleQty()));
            /**
             * 保存实体数据
             */
            this.data = data;
            /**
             * 设置相应的物料信息
             */
            setTextViewText(tvOrderno, R.string.receive_pro_num, normalSummary.getReceiptCode());
            setTextViewText(tvReceiveMaterialDate, R.string.receive_material_date, normalSummary.getReceiptDate());
            setTextViewText(tvOrderNum, R.string.order_no, normalSummary.getSourceBillCode());
            setTextViewText(tvSupplier, R.string.buy_from, normalSummary.getSupplierName());
            setTextViewText(tvMaterialCode, R.string.material_code, normalSummary.getMaterialCode());
            setTextViewText(tvMaterialName, R.string.material_name, normalSummary.getMaterialName());
            setTextViewText(tvMaterialModel, R.string.material_model, normalSummary.getMaterialStandard());
            setTextViewText(tvReceiveNum, R.string.receive_num, normalSummary.getReceiveQty());
            /**
             * 不良原因
             */
            final List<GetAdvanceData.FaultDataBean> faultData = data.getFaultData();
            if (null != faultData) {
                mFaultData = faultData;
                final CommonSimpleTypeAdapter<GetAdvanceData.FaultDataBean> commonSimpleTypeAdapter = new CommonSimpleTypeAdapter<GetAdvanceData.FaultDataBean>(mFaultData) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.item_normal_quality;
                    }

                    @Override
                    public void convert(CommonViewHolder holder, GetAdvanceData.FaultDataBean data, int position) {
                        holder.getTextView(R.id.tv_badness_code).setText(data.getFaultCode());
                        holder.getTextView(R.id.tv_badness_reason).setText(data.getFaultName());
                        holder.getTextView(R.id.tv_badness_num).setText(String.valueOf(data.getFaultQty()));
                    }
                };
                rlvQuality.setLayoutManager(new LinearLayoutManager(this));
                rlvQuality.setAdapter(commonSimpleTypeAdapter);
                /**
                 * 点击不良原因的条目，弹出提示框 输入相应的不良数量
                 */
                commonSimpleTypeAdapter.setOnItemClickListener(R.id.ll_content, new CommonSimpleTypeAdapter.ItemClickListener() {
                    @Override
                    public void onItemClicked(View view, final int position) {
                        if (null == faultDataDialog) {
                            faultDataDialog = new MyDialog(Advance1QualityActivity.this, R.layout.dialog_quality_faultdata);
                        }
                        /**
                         * 确定
                         */
                        faultDataDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                /**
                                 * 不良数
                                 */
                                String badnessNumStr = dialog.getEdittext(R.id.et_badness_num).getText().toString();
                                if (TextUtils.isEmpty(badnessNumStr)) {
                                    ToastUtils.showShort(getString(R.string.please_input_badness_num));
                                    return;
                                }
                                /**
                                 * 设置文本的不良总数
                                 */
                                int totalBadnessNum = 0;
                                /**
                                 * 计算获取所有的不良数的和
                                 */
                                for (int i = 0; i < mFaultData.size(); i++) {
                                    totalBadnessNum = totalBadnessNum + mFaultData.get(i).getFaultQty();
                                }
                                /**
                                 * 当不良总数大于实收数 时提示用户
                                 */
                                if(totalBadnessNum>data.getNormalSummary().getReceiveQty()){
                                    ToastUtils.showShort("不良总数不能大于实收数，请重新输入");
                                    return;
                                }
                                tvBadnessTotalNum.setText(String.valueOf(totalBadnessNum));
                                /**
                                 * 设置原数据
                                 */
                                mFaultData.get(position).setFaultQty(Integer.parseInt(badnessNumStr));

                                commonSimpleTypeAdapter.notifyDataSetChanged();
                                /**
                                 * 通过不良数 获取是否合格
                                 */
                                GetAdvanceData.AdvanceSummaryBean advanceSummary = data.getAdvanceSummary();
                                if (totalBadnessNum <= advanceSummary.getRejectAQL()) {//不良数小于接受数
                                    /**
                                     * 合格
                                     */
                                    rdQualified.setChecked(true);
                                    QCResult = 1;
                                    QCStatus = 2;
                                } else if (totalBadnessNum >= advanceSummary.getRejectAQL()) {//不合格
                                    /**
                                     * 不合格
                                     */
                                    rdUnqualified.setChecked(true);
                                    QCResult = 3;
                                    QCStatus = 2;
                                } else if (totalBadnessNum >= advanceSummary.getRejectAQL() && totalBadnessNum <= advanceSummary.getAcceptAQL()) {
                                    /**
                                     * 待定
                                     */
                                    rdWaitDeal.setChecked(true);
                                    QCResult = 2;
                                    QCStatus = 2;
                                }

                                /**
                                 * 计算文本的不良率
                                 */
                                //不良总数
                                double dTotalBadnessNum = (double) totalBadnessNum;
                                //实收数
                                double dReceiveNum = (double) data.getNormalSummary().getReceiveQty();
                                //转换成百分比
                                NumberFormat nFromat = NumberFormat.getPercentInstance();
                                String rates = nFromat.format(dTotalBadnessNum / dReceiveNum);
                                tvBadnessPercent.setText(rates);
                                //dismiss
                                dialog.dismiss();
                            }
                        });
                        /**
                         * 取消
                         */
                        faultDataDialog.setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                dialog.dismiss();
                            }
                        });
                        /**
                         * 设置不良原因
                         */
                        setTextViewText(faultDataDialog.getTextView(R.id.tv_badness_reason), R.string.badness_reason_tip, mFaultData.get(position).getFaultName());
                        faultDataDialog.show();
                    }
                });
            }
        }
    }

    /**
     * 选择不良原因的实体类
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
