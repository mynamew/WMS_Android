package com.timi.sz.wms_android.mvp.UI.quity.quality.nomal_quality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.timi.sz.wms_android.bean.quality.normal.CommitNormalData;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
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
    EditText etRefuseReceiveNum;
    @BindView(R.id.tv_badness_total_num)
    TextView tvBadnessTotalNum;
    @BindView(R.id.et_badness_percent)
    TextView tvBadnessPercent;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
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
    private MyDialog faultDataDialog;
    /**
     * 实例化提交的实体
     */
    CommitNormalData commitNormalData = new CommitNormalData();

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

    }

    @Override
    public void initView() {
        etSpotCheckNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sampleNumStr = editable.toString();
                /**
                 * 空判断
                 */
                if (TextUtils.isEmpty(sampleNumStr)) {
                    return;
                }
                int sampleNum = Integer.parseInt(sampleNumStr);
                /**
                 *当 抽检数大于实收数的时候提示
                 */
                if (sampleNum > mData.getNormalSummary().getReceiveQty()) {
                    ToastUtils.showShort(getString(R.string.sampleqty_more_receiveqty_please_repeat_input));
                    etSpotCheckNum.setText("0");
                    etSpotCheckNum.setSelection(1);
                }
            }
        });
        /**
         * 拒收输入的判断
         *
         */
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
         * 不良总数
         */
        String badnessNum = tvBadnessTotalNum.getText().toString();
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
         *当 拒收数大于不良总数的时候提示
         */
        if (Integer.parseInt(refuseReceiveNum) > Integer.parseInt(badnessNum)) {
            ToastUtils.showShort(getString(R.string.refusenum_no_more_badness_num_repeat_input));
            return;
        }
        /**
         * 普通质检 需要用户选择质检结果
         */
        if (!rdQualified.isChecked() && !rdUnqualified.isChecked()) {
            ToastUtils.showShort(getString(R.string.please_select_quality_is_not_quality));
            return;
        }

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
        commitNormalData.setMAC(PackageUtils.getMac());
        commitNormalData.setOrgId(SpUtils.getInstance().getOrgId());
        commitNormalData.setUserId(SpUtils.getInstance().getUserId());
        commitNormalData.setReceiptId(receiptId);
        commitNormalData.setReceiptDetailId(receiptDetailId);
        commitNormalData.setSampleQty(Integer.parseInt(spotCheckNum));
        commitNormalData.setNGQty(Integer.parseInt(badnessNum));
        commitNormalData.setRejectQty(Integer.parseInt(refuseReceiveNum));
        /**
         * 1  合格
         * 3  不合格
         */
        commitNormalData.setQCResult(isQualified ? 1 : 3);
        /**
         * 更改质检状态 质检未完成
         */
        commitNormalData.setQCStatus(2);
        commitNormalData.setRemark("");
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
        /**
         * 原数据 获取不良缺陷数
         */
        List<NormalQualityData.FaultDataBean> faultData = mData.getFaultData();
        for (int i = 0; i < faultData.size(); i++) {
            NormalQualityData.FaultDataBean faultDataBean = faultData.get(i);
            switch (faultData.get(i).getQC_DefectGrade()) {
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
        commitNormalData.setFatalQty(FatalQty);
        commitNormalData.setSeriousQty(SeriousQty);
        commitNormalData.setCommonlyQty(CommonlyQty);
        commitNormalData.setSlightQty(SlightQty);

        getPresenter().setNormalQualityData(commitNormalData);
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
                    rdQualified.setChecked(true);
                } else {
                    //质检不合格
                    rdUnqualified.setChecked(true);
                }
            }
        }
        /**
         * 不良原因
         */
        final List<NormalQualityData.FaultDataBean> faultData = result.getFaultData();
        /**
         * 设置提交的数据的不良原因的数据
         */
        commitNormalData.setFaultData(new ArrayList<CommitNormalData.FaultDataBean>());
        int totalBadnessQty = 0;
        for (int i = 0; i < faultData.size(); i++) {
            CommitNormalData.FaultDataBean faultDataBean = new CommitNormalData.FaultDataBean();
            faultDataBean.setFaultId(faultData.get(i).getFaultId());
            faultDataBean.setFaultQty(faultData.get(i).getFaultQty());
            totalBadnessQty = totalBadnessQty + faultData.get(i).getFaultQty();
            commitNormalData.getFaultData().add(faultDataBean);
        }
        /**
         * 不良数
         */
        tvBadnessTotalNum.setText(String.valueOf(totalBadnessQty));
        //抽样数  不良率
        if (normalSummary.getSampleQty() > 0) {
            etSpotCheckNum.setText(String.valueOf(normalSummary.getSampleQty()));
            /**
             * 计算文本的不良率
             */
            //不良总数
            double dTotalBadnessNum = (double) totalBadnessQty;
            //抽样数
            double dReceiveNum = Double.parseDouble(etSpotCheckNum.getText().toString().trim());
            //转换成百分比
            NumberFormat nFromat = NumberFormat.getPercentInstance();
            String rates = nFromat.format(dTotalBadnessNum / dReceiveNum);
            tvBadnessPercent.setText(rates);
        }
        //拒收数
        if (normalSummary.getNgQty() > 0) {
            etRefuseReceiveNum.setText(String.valueOf(normalSummary.getNgQty()));
        }
        /**
         * adapter初始化
         */
        if (null != faultData) {
            final BaseRecyclerAdapter<NormalQualityData.FaultDataBean> adapter = new BaseRecyclerAdapter<NormalQualityData.FaultDataBean>(this, faultData) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_normal_quality;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, NormalQualityData.FaultDataBean item) {
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
                    /**
                     * 未输入抽样数就进行不良数的输入
                     */
                    if (TextUtils.isEmpty(etSpotCheckNum.getText().toString())) {
                        ToastUtils.showShort(getString(R.string.please_input_spot_check_num));
                        return;
                    }
                    if (null == faultDataDialog) {
                        faultDataDialog = new MyDialog(NormalQualityActivity.this, R.layout.dialog_quality_faultdata);
                    }
                    /**
                     * 确定
                     */
                    faultDataDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            InputMethodUtils.hide(NormalQualityActivity.this);
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
                            List<CommitNormalData.FaultDataBean> commintFaultData = commitNormalData.getFaultData();
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
//                            /**
//                             * 当不良总数大于实收数 时提示用户
//                             */
//                            String inputSampleNumStr = etSpotCheckNum.getText().toString();
//                            if (totalBadnessNum > Integer.parseInt(inputSampleNumStr)) {
//                                ToastUtils.showShort(getString(R.string.badness_num_no_more_sample_qty));
//                                return;
//                            }
                            adapter.notifyDataSetChanged();

                            /**
                             * 设置文本的不良总数
                             */
                            tvBadnessTotalNum.setText(String.valueOf(totalBadnessNum));
                            /**
                             * 计算文本的不良率
                             */
                            //不良总数
                            double dTotalBadnessNum = (double) totalBadnessNum;
                            //抽样数
                            double dReceiveNum = Double.parseDouble(etSpotCheckNum.getText().toString().trim());
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
                            InputMethodUtils.hide(NormalQualityActivity.this);
                            dialog.dismiss();
                        }
                    });
                    /**
                     * 关闭dialog
                     */
                    faultDataDialog.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            InputMethodUtils.hide(NormalQualityActivity.this);
                            faultDataDialog.dismiss();
                        }
                    });
                    /**
                     * 设置不良原因
                     */
                    setTextViewText(faultDataDialog.getTextView(R.id.tv_badness_reason), R.string.badness_reason_tip, faultData.get(position).getFaultName());
                    faultDataDialog.show();
                }

            });
        }
    }

    @Override
    public void setNormalQualityData() {
        boolean isQualified=true;
        if(rdUnqualified.isChecked()){
            isQualified=false;
        }
        int rejectNum=0;
        rejectNum=Integer.parseInt(etRefuseReceiveNum.getText().toString());
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
            if (rejectNum > 0 && mData.getNormalSummary().isIsBarCode()) {//拒收数大于0  跳转到质检拒收并且有条码
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
        /**
         * 发送质检成功的消息
         */
        LogUitls.e("质检确认------>", "成功");
        ToastUtils.showShort(getString(R.string.quality_confirm_complete));
        BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_CONFRIM));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void closNormalQuality(QualityEvent event) {
        if (event.getEvent().equals(QualityEvent.QUALITY_CONFRIM)) {
            finish();
        }
        /**
         * 用于 更改了质检结果 更新质检来源数据
         */
        else if(event.getEvent().equals(QualityEvent.QUALITY_REJECT_SUCCESS)){
            NormalQualityData.BarcodeDataBean newBarDataBean = event.getNewBarDataBean();
            if(null!=newBarDataBean){
                List<NormalQualityData.BarcodeDataBean> barcodeData = mData.getBarcodeData();
                //是否为空的怕地暖
                if(null==barcodeData){
                    barcodeData=new ArrayList<>();
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
