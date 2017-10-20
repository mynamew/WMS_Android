package com.timi.sz.wms_android.mvp.UI.quity.nomal_quality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.CommonSimpleTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvanceData;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.UI.quity.advance1_quality.Advance1QualityActivity;
import com.timi.sz.wms_android.mvp.UI.quity.reject.QualityRejectActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 普通质检
 * author: timi
 * create at: 2017/9/6 17:22
 */
public class NormalQualityActivity extends BaseActivity<NormalQualityView, NormalQualityPresenter> implements NormalQualityView {


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
    @BindView(R.id.et_spot_check_num)
    EditText etSpotCheckNum;
    @BindView(R.id.et_refuse_receive_num)
    TextView etRefuseReceiveNum;
    @BindView(R.id.tv_badness_total_num)
    TextView tvBadnessTotalNum;
    @BindView(R.id.et_badness_percent)
    TextView tvBadnessPercent;
    @BindView(R.id.rlv_quality)
    RecyclerView rlvQuality;
    @BindView(R.id.rd_qualified)
    RadioButton rdQualified;
    @BindView(R.id.rd_unqualified)
    RadioButton rdUnqualified;
    @BindView(R.id.rg_qualified)
    RadioGroup rgQualified;

    //bundle
    private int receiptId;
    private int receiptDetailId;
    //data
    /**
     * 保存的数据
     */
    private NormalQualityData mData;
    private List<NormalQualityData.FaultDataBean> mFaultData;
    private MyDialog faultDataDialog;

    /**
     * 不良原因
     */
    private List<FaultData> mSelectFaultData = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_normal_quality;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        BaseMessage.register(this);
        setActivityTitle(getString(R.string.normal_quality_title));
        receiptId = getIntent().getIntExtra("ReceiptId", -1);
        receiptDetailId = getIntent().getIntExtra("ReceiptDetailId", -1);
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
    public void initView() {
        /**
         * 默认选择合格
         */
        rdUnqualified.setChecked(false);

    }

    @Override
    public void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        params.put("ReceiptId", receiptId);
        params.put("ReceiptDetailId", receiptDetailId);
        getPresenter().getNormalQualityData(params);
    }

    @Override
    public NormalQualityPresenter createPresenter() {
        return new NormalQualityPresenter(this);
    }

    @Override
    public NormalQualityView createView() {
        return this;
    }

    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        /**
         * 抽检数
         */
        String spotCheckNum = etSpotCheckNum.getText().toString();
        if (TextUtils.isEmpty(spotCheckNum)) {
            ToastUtils.showShort(getString(R.string.please_input_spot_check_num));
            return;
        }
        /**
         *当 抽检数大于实收数的时候提示
         */
        if (Integer.parseInt(spotCheckNum) > mData.getNormalSummary().getReceiveQty()) {
            ToastUtils.showShort(getString(R.string.sampleqty_more_receiveqty_please_repeat_input));
            return;
        }
        /**
         * 拒收数
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
         * 不良总数
         */
        String badnessNum = tvBadnessTotalNum.getText().toString();
        /**
         * 不良率
         */
        String badnessPercent = tvBadnessPercent.getText().toString();
        /**
         * 是否合格
         */
        boolean isQualified = rdQualified.isChecked();
        /**
         * 网络请求 进行下一步
         */
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        params.put("ReceiptId", receiptId);
        params.put("ReceiptDetailId", receiptDetailId);
        params.put("SampleQty", Integer.parseInt(spotCheckNum));
        params.put("NGQty", Integer.parseInt(badnessNum));
        LogUitls.e("NGQty--->", Integer.parseInt(badnessNum));
        /**
         */
        params.put("RejectQty", Integer.parseInt(refuseReceiveNum));
        /**
         * 更改质检状态 质检未完成
         */
        params.put("QCStatus", 2);
        /**
         * 1  合格
         * 3  不合格
         */
        params.put("QCResult", isQualified ? 1 : 3);
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
            NormalQualityData.FaultDataBean faultDataBean = mFaultData.get(i);
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
        }
        params.put("Remark", "");
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
        LogUitls.e("params--->", params);
        getPresenter().setNormalQualityData(params, isQualified, Integer.parseInt(refuseReceiveNum));
    }

    @Override
    public void getNormalQualityData(NormalQualityData result) {
        mData = result;
        /**
         * 设置从质检清单获取到的数据，设置到当前界面
         */
        NormalQualityData.NormalSummaryBean normalSummary = result.getNormalSummary();
        if (null != normalSummary) {
            /**
             * 保存实体数据
             */
            setTextViewText(tvOrderno, R.string.receive_pro_num, normalSummary.getReceiptCode());
            setTextViewText(tvReceiveMaterialDate, R.string.receive_material_date, normalSummary.getReceiptDate());
            setTextViewText(tvOrderNum, R.string.order_no, normalSummary.getSourceBillCode());
            setTextViewText(tvSupplier, R.string.buy_from, normalSummary.getSupplierName());
            setTextViewText(tvMaterialCode, R.string.material_code, normalSummary.getMaterialCode());
            setTextViewText(tvMaterialName, R.string.material_name, normalSummary.getMaterialName());
            setTextViewText(tvMaterialModel, R.string.material_model, normalSummary.getMaterialStandard());
            setTextViewText(tvReceiveNum, R.string.receive_num, normalSummary.getReceiveQty());
        }
        /**
         * 不良原因
         */
        final List<NormalQualityData.FaultDataBean> faultData = result.getFaultData();
        if (null != faultData) {
            mFaultData = faultData;
            final BaseRecyclerAdapter<NormalQualityData.FaultDataBean> adapter = new BaseRecyclerAdapter<NormalQualityData.FaultDataBean>(this,faultData) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_normal_quality;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, NormalQualityData.FaultDataBean item) {
                    holder.getTextView(R.id.tv_badness_code).setText(item.getFaultCode());
                    holder.getTextView(R.id.tv_badness_reason).setText(item.getFaultName());
                    holder.getTextView(R.id.tv_badness_num).setText(item.getFaultQty() + "");
                }
            };
            rlvQuality.setLayoutManager(new LinearLayoutManager(this));
            rlvQuality.setAdapter(adapter);
            /**
             * 点击不良原因的条目，弹出提示框 输入相应的不良数量
             */
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, final int position) {
                    if (null == faultDataDialog) {
                        faultDataDialog = new MyDialog(NormalQualityActivity.this, R.layout.dialog_quality_faultdata);
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
                            int totalBadnessNum = 0;
                            /**
                             * 计算获取所有的不良数的和
                             */
                            for (int i = 0; i < mFaultData.size(); i++) {
                                totalBadnessNum = totalBadnessNum + mFaultData.get(i).getFaultQty();
                            }
                            /**
                             * 计算不良总数  不良总数=不良总数-原来position位置的不良数+现在更改position位置的不良数
                             */
                            totalBadnessNum = totalBadnessNum - mFaultData.get(position).getFaultQty() + Integer.parseInt(badnessNumStr);
                            if (totalBadnessNum > mData.getNormalSummary().getReceiveQty()) {
                                ToastUtils.showShort("不良总数不能大于实收数，请重新输入");
                                return;
                            }
                            /**
                             * 设置原数据
                             */
                            mFaultData.get(position).setFaultQty(Integer.parseInt(badnessNumStr));
                            adapter.notifyDataSetChanged();
                            /**
                             * 当不良总数大于实收数 时提示用户
                             */
                            /**
                             * 设置文本的不良总数
                             */
                            tvBadnessTotalNum.setText(String.valueOf(totalBadnessNum));
                            /**
                             * 计算文本的不良率
                             */
                            //不良总数
                            double dTotalBadnessNum = (double) totalBadnessNum;
                            //实收数
                            double dReceiveNum = (double) mData.getNormalSummary().getReceiveQty();
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

    @Override
    public void setNormalQualityData(boolean isQualified, int rejectNum) {
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
        if (isQualified) {//合格
            if (rejectNum > 0&&mData.getNormalSummary().isIsBarCode()) {//拒收数大于0  跳转到质检拒收并且有条码
                /**
                 * 跳转到质检拒收
                 */
                Intent intent = new Intent(NormalQualityActivity.this, QualityRejectActivity.class);
                intent.putExtra("mData", new Gson().toJson(mData));
                intent.putExtra("rejectNum", rejectNum);
                startActivity(intent);
            } else {
                tvNext.setText(getString(R.string.quality_complete));
                ToastUtils.showShort(getString(R.string.normal_quality_tip));
                getPresenter().submitFinish(params);
            }
        } else {//不合格
            ToastUtils.showShort(getString(R.string.normal_unquality_tip));
            getPresenter().submitFinish(params);
        }
    }

    @Override
    public void submitFinish() {
        LogUitls.e("质检确认------>", "成功");
        ToastUtils.showShort("质检确认完成！");
        /**
         * 关闭当前界面
         */
        onBackPressed();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void closNormalQuality(QualityEvent event) {
        if (event.getEvent().equals(QualityEvent.QUALITY_CONFRIM)) {
            onBackPressed();
        }
    }
}
