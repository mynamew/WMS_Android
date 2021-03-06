package com.timi.sz.wms_android.mvp.UI.quity.quality.advance1_quality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.CommitAdvance1Data;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvanceData;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.UI.quity.reject.QualityRejectActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;
import com.timi.sz.wms_android.view.QualityResultView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_receive_num)
    TextView tvReceiveNum;
    @BindView(R.id.tv_spot_check_num)
    TextView tvSpotCheckNum;
    @BindView(R.id.tv_refuse_receive_num)
    TextView tvRefuseReceiveNum;
    @BindView(R.id.et_refuse_receive_num)
    EditText etRefuseReceiveNum;
    @BindView(R.id.ll_spot_check)
    LinearLayout llSpotCheck;
    @BindView(R.id.tv_badness_total_num_tip)
    TextView tvBadnessTotalNumTip;
    @BindView(R.id.tv_badness_total_num)
    TextView tvBadnessTotalNum;
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
    @BindView(R.id.quality_adavance1)
    QualityResultView qualityAdavance1;
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
    private GetAdvanceData mData;
    /**
     * 不良原因
     */
    private List<CommitAdvance1Data.FaultDataBean> mSelectFaultData = new ArrayList<>();
    private CommitAdvance1Data commitAdvance1Data;

    /**
     * 提交高级质检1 的实体
     *
     * @return
     */
    @Override
    public int setLayoutId() {
        return R.layout.activity_advance1_quality;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.quality_advance1_title));
        receiptId = getIntent().getIntExtra("ReceiptId", -1);
        receiptDetailId = getIntent().getIntExtra("ReceiptDetailId", -1);
        BaseMessage.register(this);
    }

    @Override
    public void initView() {


        etRefuseReceiveNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                /**
                 *由拒收数输入变化而根据  不良总数去对质检结果进行改变
                 */
                String totalBadnessNumStr = tvBadnessTotalNum.getText().toString();
                /**
                 * 不良数的空判断
                 */
                if (TextUtils.isEmpty(totalBadnessNumStr)) {
                    return;
                }
                int totalBadnessNum = Integer.parseInt(totalBadnessNumStr);
                String rejectNumStr = editable.toString();
                /**
                 * 拒收数的空判断
                 */
                if (TextUtils.isEmpty(rejectNumStr)) {
                    return;
                }
                int rejectNum = Integer.parseInt(rejectNumStr);
                /**
                 *当 拒收数大于不良总数的时候提示
                 */
                if (rejectNum > totalBadnessNum) {
                    ToastUtils.showShort(getString(R.string.refusenum_no_more_badness_num_repeat_input));
                    /**
                     * 设置文本 设置光标
                     */
                    etRefuseReceiveNum.setText("0");
                    etRefuseReceiveNum.setSelection(1);
                    return;
                }
                /**
                 *当 拒收数大于实收数的时候提示
                 */
                if (rejectNum > mData.getNormalSummary().getReceiveQty()) {
                    ToastUtils.showShort(getString(R.string.rejectnum_more_receiveqty_please_repeat_input));
                    return;
                }
            }
        });
    }

    @Override
    public void initData() {
        commitAdvance1Data = new CommitAdvance1Data();
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
        /**
         * 不良总数
         */
        String badnessNum = tvBadnessTotalNum.getText().toString();
        /**
         * 判断是否输入了 拒收的数量
         */
        String refuseReceiveNum = etRefuseReceiveNum.getText().toString();
        if (TextUtils.isEmpty(refuseReceiveNum)) {
            ToastUtils.showShort(getString(R.string.please_input_reject_num));
            return;
        }
        /**
         *当 拒收数大于实收数的时候提示
         */
        if (Integer.parseInt(refuseReceiveNum) > mData.getNormalSummary().getReceiveQty()) {
            ToastUtils.showShort(getString(R.string.rejectnum_more_receiveqty_please_repeat_input));
            return;
        }
        /**
         *当 拒收数大于不良总数的时候提示
         */
        if (Integer.parseInt(refuseReceiveNum) > Integer.parseInt(badnessNum)) {
            ToastUtils.showShort(getString(R.string.refusenum_no_more_badness_num_repeat_input));
            return;
        }
        /**
         * 设置质检结果
         */
        switch (qualityAdavance1.getQcResult()) {
            case QualityResultView.QUALITY_RESULT_SUCCESS:
                QCResult=1;
                break;
            case QualityResultView.QUALITY_RESULT_FAIL:
                QCResult=3;
                break;
            case QualityResultView.QUALITY_RESULT_WAIT_DEAL:
                QCResult=2;
                break;
        }


        commitAdvance1Data.setMAC(PackageUtils.getMac());
        commitAdvance1Data.setOrgId(SpUtils.getInstance().getOrgId());
        commitAdvance1Data.setUserId(SpUtils.getInstance().getUserId());

        commitAdvance1Data.setReceiptId(receiptId);
        commitAdvance1Data.setReceiptDetailId(receiptDetailId);

        commitAdvance1Data.setSampleQty(mData.getNormalSummary().getSampleQty());
        commitAdvance1Data.setNGQty(Integer.parseInt(badnessNum));
        commitAdvance1Data.setRejectQty(Integer.parseInt(refuseReceiveNum));
        commitAdvance1Data.setQCResult(QCResult);
        commitAdvance1Data.setQCStatus(2);
        commitAdvance1Data.setRemark("");

        GetAdvanceData.AdvanceSummaryBean advanceSummary = mData.getAdvanceSummary();
        commitAdvance1Data.setSampleCode(advanceSummary.getSampleCode());
        commitAdvance1Data.setCurrentLevel(advanceSummary.getCurrentLevel());
        commitAdvance1Data.setCurrentStrict(advanceSummary.getCurrentStrict());
        commitAdvance1Data.setBeginQty(advanceSummary.getBeginQty());
        commitAdvance1Data.setEndQty(advanceSummary.getEndQty());
        commitAdvance1Data.setCurrentAQL(advanceSummary.getCurrentAQL());
        commitAdvance1Data.setAQLAcceptQty(advanceSummary.getAcceptAQL());
        commitAdvance1Data.setAQLRejectQty(advanceSummary.getRejectAQL());
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
        commitAdvance1Data.setFatalQty(FatalQty);
        commitAdvance1Data.setSeriousQty(SeriousQty);
        commitAdvance1Data.setCommonlyQty(CommonlyQty);
        commitAdvance1Data.setSlightQty(SlightQty);
        getPresenter().setAdvance1Data(commitAdvance1Data);
    }

    @Override
    public void getAdvance1Data(final GetAdvanceData data) {
        /**
         * 保存实体数据
         */
        this.mData = data;

        GetAdvanceData.NormalSummaryBean normalSummary = data.getNormalSummary();
        if (null != normalSummary) {
            /**
             * 抽样数
             */
            tvSpotCheckNum.setText(String.valueOf(normalSummary.getSampleQty()));

            /**
             * 设置相应的物料信息
             */
            setTextViewContent(tvOrderno, normalSummary.getReceiptCode());
            setTextViewContent(tvReceiveMaterialDate, normalSummary.getReceiptDate());
            setTextViewContent(tvOrderNum, normalSummary.getSourceBillCode());
            setTextViewContent(tvSupplier, normalSummary.getSupplierName());
            setTextViewContent(tvMaterialCode, normalSummary.getMaterialCode());
            setTextViewContent(tvMaterialName, normalSummary.getMaterialName());
            setTextViewContent(tvMaterialModel, normalSummary.getMaterialStandard());
            setTextViewContent(tvReceiveNum, normalSummary.getReceiveQty());
            setTextViewContent(tvMaterialAttr, normalSummary.getMaterialAttribute());
            setMaterialAttrStatus(tvMaterialAttr);
            /**
             * 对质检结果进行判断  设置是否合格，通过 质检状态和质检结果共同判断
             */
            //质检未完成
            if (normalSummary.getQcStatus() == 2) {
                //质检合格
                if (normalSummary.getQcResult() == 1) {
                    qualityAdavance1.setQualityResult(QualityResultView.QUALITY_RESULT_SUCCESS);
                } else if (normalSummary.getQcResult() == 3) {
                    //质检不合格
                    qualityAdavance1.setQualityResult(QualityResultView.QUALITY_RESULT_FAIL);
                } else {
                    //质检不合格
                    qualityAdavance1.setQualityResult(QualityResultView.QUALITY_RESULT_FAIL);
                }
            }
            /**
             * 不良原因
             */
            final List<GetAdvanceData.FaultDataBean> faultData = data.getFaultData();
            /**
             * 设置提交的数据的不良原因的数据
             */
            commitAdvance1Data.setFaultData(new ArrayList<CommitAdvance1Data.FaultDataBean>());
            int totalBadnessQty = 0;
            for (int i = 0; i < faultData.size(); i++) {
                CommitAdvance1Data.FaultDataBean faultDataBean = new CommitAdvance1Data.FaultDataBean();
                faultDataBean.setFaultId(faultData.get(i).getFaultId());
                faultDataBean.setFaultQty(faultData.get(i).getFaultQty());
                totalBadnessQty = totalBadnessQty + faultData.get(i).getFaultQty();
                commitAdvance1Data.getFaultData().add(faultDataBean);
            }
            /**
             * 不良数
             */
            tvBadnessTotalNum.setText(String.valueOf(totalBadnessQty));
            //抽样数
            if (normalSummary.getSampleQty() > 0) {
                tvSpotCheckNum.setText(String.valueOf(normalSummary.getSampleQty()));
            }
            //拒收数
            if (normalSummary.getNgQty() > 0) {
                etRefuseReceiveNum.setText(String.valueOf(normalSummary.getNgQty()));
            }
            /**
             * 计算文本的不良率
             */
            //不良总数
            double dTotalBadnessNum = (double) totalBadnessQty;
            //抽样数
            double dReceiveNum = Double.parseDouble(tvSpotCheckNum.getText().toString().trim());
            //转换成百分比
            NumberFormat nFromat = NumberFormat.getPercentInstance();
            String rates = nFromat.format(dTotalBadnessNum / dReceiveNum);
            etBadnessPercent.setText(rates);
            if (null != faultData) {
                mFaultData = faultData;
                final BaseRecyclerAdapter<GetAdvanceData.FaultDataBean> adapter = new BaseRecyclerAdapter<GetAdvanceData.FaultDataBean>(this, faultData) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
                        return R.layout.item_normal_quality;
                    }

                    @Override
                    protected void bindData(RecyclerViewHolder holder, int position, GetAdvanceData.FaultDataBean item) {
                        /**
                         * 为了item的点击效果
                         */
//                    holder.getView(R.id.ll_content).setOnClickListener(null);
                        /**
                         * 设置数据
                         */
                        holder.getTextView(R.id.tv_badness_code).setText(item.getFaultCode());
                        holder.getTextView(R.id.tv_badness_reason).setText(item.getFaultName());
                        holder.getTextView(R.id.tv_badness_num).setText(item.getFaultQty() + "");
                    }
                };
                rlvQuality.setLayoutManager(new LinearLayoutManager(this));
                rlvQuality.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));
                rlvQuality.setAdapter(adapter);
                /**
                 * 点击不良原因的条目，弹出提示框 输入相应的不良数量
                 */
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, final int position) {
                        if (null == faultDataDialog) {
                            faultDataDialog = new MyDialog(Advance1QualityActivity.this, R.layout.dialog_quality_faultdata);
                        }
                        /**
                         * 确定
                         */
                        faultDataDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                InputMethodUtils.hide(Advance1QualityActivity.this);
                                /**
                                 * 不良数
                                 */
                                String badnessNumStr = dialog.getEdittext(R.id.et_badness_num).getText().toString();
                                if (TextUtils.isEmpty(badnessNumStr)) {
                                    ToastUtils.showShort(getString(R.string.please_input_badness_num));
                                    return;
                                }
                                int totalBadnessNum = 0;
                                /**
                                 * 计算获取所有的不良数的和
                                 */
                                List<CommitAdvance1Data.FaultDataBean> commintFaultData = commitAdvance1Data.getFaultData();
                                /**
                                 * 设置 提交数据 和源数据的不良数量
                                 */
                                commintFaultData.get(position).setFaultQty(Integer.parseInt(badnessNumStr));
                                faultData.get(position).setFaultQty(Integer.parseInt(badnessNumStr));

                                /**
                                 *  遍历提交的选择链表 ，计算不良数
                                 */
                                for (int i = 0; i < commintFaultData.size(); i++) {
                                    totalBadnessNum = totalBadnessNum + commintFaultData.get(i).getFaultQty();
                                }
//                                /**
//                                 * 当不良总数大于实收数 时提示用户
//                                 */
//                                String inputSampleNumStr = tvSpotCheckNum.getText().toString();
//                                if (totalBadnessNum > Integer.parseInt(inputSampleNumStr)) {
//                                    ToastUtils.showShort(getString(R.string.badness_num_no_more_sample_qty));
//                                    return;
//                                }
                                //刷新
                                adapter.notifyDataSetChanged();

                                /**
                                 * 设置文本的不良总数
                                 */
                                tvBadnessTotalNum.setText(String.valueOf(totalBadnessNum));
                                /**
                                 * 对不良数进行判断 设置质检结果
                                 */
                                GetAdvanceData.AdvanceSummaryBean advanceSummary = mData.getAdvanceSummary();
                                if (totalBadnessNum <= advanceSummary.getAcceptAQL()) {//不良数小于接受数
                                    /**
                                     * 合格
                                     */
                                    qualityAdavance1.setQualityResult(QualityResultView.QUALITY_RESULT_SUCCESS);
                                    QCResult = 1;
                                    QCStatus = 2;
                                } else if (totalBadnessNum >= advanceSummary.getRejectAQL()) {//不合格
                                    /**
                                     * 不合格
                                     */
                                    qualityAdavance1.setQualityResult(QualityResultView.QUALITY_RESULT_FAIL);
                                    QCResult = 3;
                                    QCStatus = 2;
                                } else if (totalBadnessNum > advanceSummary.getRejectAQL() && totalBadnessNum < advanceSummary.getAcceptAQL()) {
                                    /**
                                     * 待定
                                     */
                                    qualityAdavance1.setQualityResult(QualityResultView.QUALITY_RESULT_WAIT_DEAL);                                    QCResult = 2;
                                    QCStatus = 2;
                                }
                                /**
                                 * 计算文本的不良率
                                 */
                                //不良总数
                                double dTotalBadnessNum = (double) totalBadnessNum;
                                //实收数
                                double dReceiveNum = (double) mData.getNormalSummary().getSampleQty();
                                //转换成百分比
                                NumberFormat nFromat = NumberFormat.getPercentInstance();
                                String rates = nFromat.format(dTotalBadnessNum / dReceiveNum);
                                etBadnessPercent.setText(rates);
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
                         * 关闭
                         */
                        faultDataDialog.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                faultDataDialog.dismiss();
                            }
                        });
                        /**
                         * 设置不良原因
                         */
                        setTextViewText(faultDataDialog.getTextView(R.id.tv_badness_reason), R.string.badness_reason_tip, mFaultData.get(position).getFaultName());
                        Selection.selectAll(faultDataDialog.getEdittext(R.id.et_badness_num).getText());
                        faultDataDialog.show();
                    }
                });
            }
        }
    }

    @Override
    public void setAdvance1Data() {
        int rejectNum = Integer.parseInt(etRefuseReceiveNum.getText().toString());
        int isQualified = 1;
        /**
         * 设置质检结果
         */
        switch (qualityAdavance1.getQcResult()) {
            case QualityResultView.QUALITY_RESULT_SUCCESS:
                isQualified=1;
                break;
            case QualityResultView.QUALITY_RESULT_FAIL:
                isQualified=3;
                break;
            case QualityResultView.QUALITY_RESULT_WAIT_DEAL:
                isQualified=2;
                break;
        }
        LogUitls.e("质检提交------>", "成功");
        /**
         * 发送质检成功的消息
         */
        BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_SUCCESS));
        /**
         * 参数
         */
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        params.put("ReceiptId", receiptId);
        params.put("ReceiptDetailId", receiptDetailId);
        /**
         * 设置按钮文字
         */
        switch (isQualified) {
            case 1://合格
                if (rejectNum > 0 && mData.getNormalSummary().isIsBarCode()) {//拒收数大于0  跳转到质检拒收并且有条码
                    /**
                     * 跳转到质检拒收
                     */
                    Intent intent = new Intent(Advance1QualityActivity.this, QualityRejectActivity.class);
                    intent.putExtra("mData", new Gson().toJson(mData));
                    intent.putExtra("rejectNum", rejectNum);
                    startActivity(intent);
                } else {
                    tvNext.setText(getString(R.string.quality_complete));
                    ToastUtils.showShort(getString(R.string.normal_quality_tip));
                    getPresenter().submitFinish(params);
                }
                break;
            case 2://待定  待定直接提示用户 质检结果为待定，并且关闭界面返回到清单的界面
                ToastUtils.showShort(getString(R.string.quality_wait_deal_tip));
                onBackPressed();
                break;
            case 3://不合格
                ToastUtils.showShort(getString(R.string.normal_unquality_tip));
                getPresenter().submitFinish(params);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    @Override
    public void submitFinish() {
        ToastUtils.showShort("质检确认完成！");
        /**
         * 关闭当前界面
         */
        BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_CONFRIM));
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void closNormalQuality(QualityEvent event) {
        if (event.getEvent().equals(QualityEvent.QUALITY_CONFRIM)) {
            LogUitls.e("关闭高级质检1的界面");
            onBackPressed();
        }/**
         * 用于 更改了质检结果 更新质检来源数据
         */
        else if (event.getEvent().equals(QualityEvent.QUALITY_REJECT_SUCCESS)) {
            NormalQualityData.BarcodeDataBean newBarDataBean = event.getNewBarDataBean();
            if (null != newBarDataBean) {
                List<NormalQualityData.BarcodeDataBean> barcodeData = mData.getBarcodeData();
                //是否为空的判断
                if (null == barcodeData) {
                    barcodeData = new ArrayList<>();
                }
                //当前扫描的barcode是否包含在链表中
                boolean isContainCurrentBarcode = false;
                /**
                 * 设置数据
                 */
                for (int i = 0; i < barcodeData.size(); i++) {
                    if (newBarDataBean.getBarcodeNo().equals(barcodeData.get(i).getBarcodeNo())) {
                        barcodeData.get(i).setBarcodeNo(newBarDataBean.getBarcodeNo());
                        barcodeData.get(i).setCurrentQty(newBarDataBean.getCurrentQty());
                        barcodeData.get(i).setPackQty(newBarDataBean.getCurrentQty());
                        barcodeData.get(i).setRejectQty(newBarDataBean.getRejectQty());
                        isContainCurrentBarcode = true;
                    }
                }
                /**
                 * 是否插入数据
                 */
                /**
                 * 如果不包含则加入链表并刷新adapter
                 */
                if (!isContainCurrentBarcode) {
                    barcodeData.add(newBarDataBean);
                }
                //设置barcodedata
                mData.setBarcodeData(barcodeData);
            }
        }
    }
}
