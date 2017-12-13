package com.timi.sz.wms_android.mvp.UI.quity.quality.advance_quality;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.timi.sz.wms_android.bean.quality.adavance.CommitAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.bean.quality.adavance.ListCheckItemBean;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;
import com.timi.sz.wms_android.view.QualityResultView;
import com.timi.sz.wms_android.view.StandardLevelView;

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
 * 高级质检
 * author: timi
 * create at: 2017/12/12 17:21
 */
public class Advance2Activity extends BaseActivity<AdvanceQualityView, AdvanceQualityPresenter> implements AdvanceQualityView {
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.quality_adavance2)
    QualityResultView qualityAdavance2;
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
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
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
    @BindView(R.id.tv_receive_num)
    TextView tvReceiveNum;
    @BindView(R.id.tv_sample_num)
    TextView tvSampleNum;
    @BindView(R.id.tv_refuse_receive_num)
    TextView tvRefuseReceiveNum;
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
    @BindView(R.id.tv_sample_code)
    TextView tvSampleCode;
    @BindView(R.id.tv_quality)
    Button tvQuality;
    @BindView(R.id.rlv_sample)
    RecyclerView rlvSample;

    //bundle
    private int receiptId;
    private int receiptDetailId;
    //检验项目
    //检验项目adapter的数据源
    private List<ListCheckItemBean> mCheckItemDatas;
    //检验项目adapter
    private BaseRecyclerAdapter<ListCheckItemBean> adapterCheckItem;
    //高级质检源数据
    private GetAdvance2Data mData;
    /**
     * 高检2提交的实体
     */
    private CommitAdvanceData mCommitAdvanceData = new CommitAdvanceData();
    /**
     * 样品编码
     */
    private int sampleCode = 1;
    /**
     * 当前检验项目的位置
     */
    private int currentCheckposition = 0;
    /**
     * 检验项目的dialog
     */
    private MyDialog checkItemDialog;
    /**
     * 检验项目的链表
     */
    List<CommitAdvanceData.ItemDataBean> mSelectFaultData = new ArrayList<>();
    /**
     * 每个sample code 所对应的数据，链表的目的：为了方便存储用户选择的不良原因，用于计算不良缺陷数
     * 1、checkitem的基础数据
     * 2、faultdata 即不良原因的链表
     */
    private SparseArray<List<GetAdvance2Data.CheckItemBean>> checkItemDatas = new SparseArray<>();
    /**
     * 检测项目的view
     */
    TextView tvCode, tvCheckItem, tvCheckMode, tvLimmitLow, tvLimmitHigh, tvSelectBadness, tvUnit;
    EditText etMeasure;
    RecyclerView rlvBadnessReason;
    LinearLayout llMeasureTip;
    QualityResultView qualityResultCheckItem;
    Button dialogBtnNext;
    /**
     * 弹出选择评审结果的选择
     */
    private ImageView ivMrpDown;

    @Override
    public int setLayoutId() {
        return R.layout.activity_advance2;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.advance_quality_title));
        receiptId = getIntent().getIntExtra("ReceiptId", -1);
        receiptDetailId = getIntent().getIntExtra("ReceiptDetailId", -1);
        BaseMessage.register(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mCheckItemDatas = new ArrayList<>();
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
        //源数据
        mData = data;

        GetAdvance2Data.NormalSummaryBean normalSummary = data.getNormalSummary();
        //设置物料信息
        setMaterialInfo(normalSummary.getReceiptCode(), normalSummary.getReceiptDate()
                , normalSummary.getSourceBillCode(), normalSummary.getSupplierName()
                , normalSummary.getMaterialCode(), normalSummary.getMaterialName(),
                normalSummary.getMaterialStandard(), normalSummary.getMaterialAttribute());
        //设置标准水平
        GetAdvance2Data.AdvanceSummaryBean advanceSummary = data.getAdvanceSummary();
        setStandardLevel(advanceSummary.getCurrentLevel(), advanceSummary.getCurrentAQL()
                , advanceSummary.getCurrentLevel(), advanceSummary.getCurrentStrict());
        //设置质检操作的数据
        setQualityWorkData(normalSummary.getReceiveQty(), normalSummary.getSampleQty()
                , advanceSummary.getBeginQty(), advanceSummary.getEndQty()
                , normalSummary.getRejectQty(), normalSummary.getNgQty()
                , advanceSummary.getFatalQty(), advanceSummary.getSeriousQty()
                , advanceSummary.getCommonlyQty(), advanceSummary.getSlightQty());
        //设置质检的项目列表
        List<GetAdvance2Data.CheckItemBean> checkItem = data.getCheckItem();
        List<GetAdvance2Data.CheckItemDataBean> checkItemData = data.getCheckItemData();
        //样品编码
        sampleCode = null == checkItemData ? 1 : checkItemData.get(0).getSampleSeq()+1;
        //设置样品编码 (若全部检验完毕 则将样品编号设为无)
        if (sampleCode == normalSummary.getSampleQty()) {
            setTextViewContent(tvSampleCode, getString(R.string.none));
        } else {
            setTextViewContent(tvSampleCode, sampleCode);
        }
        if (null != checkItemData && !checkItemData.isEmpty()) {
            int sampleSeq = checkItemData.get(0).getSampleSeq();
            for (int i = 0; i < checkItemData.size(); i++) {
                boolean currentCheckItemDataContainValue = false;
                if (sampleSeq == checkItemData.get(i).getSampleSeq()) {
                    if (mCheckItemDatas.isEmpty()) {
                        //设置currentCheckItemDataContainValue标识
                        currentCheckItemDataContainValue = true;
                        //初始化一个新的ListCheckItemBean对象
                        ListCheckItemBean bean = new ListCheckItemBean();
                        bean.setSampleSeq(checkItemData.get(i).getSampleSeq());
                        bean.setCheckItemBeen(new ArrayList<GetAdvance2Data.CheckItemDataBean>());
                        bean.getCheckItemBeen().add(checkItemData.get(i));
                        mCheckItemDatas.add(bean);
                    } else {
                        for (int j = 0; j < mCheckItemDatas.size(); j++) {
                            if (sampleSeq == mCheckItemDatas.get(j).getSampleSeq()) {
                                //设置currentCheckItemDataContainValue标识
                                currentCheckItemDataContainValue = true;
                                //相同的sampleCode 直接添加
                                mCheckItemDatas.get(j).getCheckItemBeen().add(checkItemData.get(i));
                            }
                        }
                    }
                }
                /**
                 * 当链表不为空 并且没有 sampleSeq的时候要添加新数据
                 */
                if (!currentCheckItemDataContainValue) {
                    //设置sampleSeq
                    sampleSeq = checkItemData.get(i).getSampleSeq();
                    //初始化一个新的ListCheckItemBean对象
                    ListCheckItemBean bean = new ListCheckItemBean();
                    bean.setSampleSeq(checkItemData.get(i).getSampleSeq());
                    bean.setCheckItemBeen(new ArrayList<GetAdvance2Data.CheckItemDataBean>());
                    bean.getCheckItemBeen().add(checkItemData.get(i));
                    mCheckItemDatas.add(bean);
                }
            }
        }
        //存储所有的样品检验项目
        //初始化  样品检验项目的适配器
        initCheckItemAdapter();
    }

    /**
     * 是否显示过  当质检已经不合格的时候 弹出的用户选择的dialog
     */
    private boolean isShowChoose = false;
    /**
     * 样品质检的结果
     * 默认是合格
     */
    private int qcResult = 1;
    private int rejectNum = 0;

    @Override
    public void setAdvance2Data() {
        LogUitls.e("提交高级质检成功");
        /**
         * 发送质检成功的消息
         */
        BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_SUCCESS));
        /**
         * 当样品检验为不合格的时候，并且没有弹出过不合格选择框的时候弹出
         */
        if (qcResult == 3 && !isShowChoose) {
            isShowChoose = true;
            showUnPassDialog();
            return;
        }
    }

    @Override
    public void submitFinish() {

    }

    /**
     * 设置物料的信息
     *
     * @param receiveOrderno 到货单号
     * @param receiveDate    到货日期
     * @param orderno        订单号
     * @param supplier       供应商
     * @param materialCode   物料编码
     * @param materialName   物料名称
     * @param materialMode   物料型号
     * @param materialAttr   附加属性
     */
    public void setMaterialInfo(String receiveOrderno, String receiveDate,
                                String orderno, String supplier, String materialCode,
                                String materialName, String materialMode,
                                String materialAttr) {

        tvReceiveNum.setText(receiveOrderno);
        tvReceiveMaterialDate.setText(receiveDate);
        tvOrderno.setText(orderno);
        tvSupplier.setText(supplier);
        tvMaterialCode.setText(materialCode);
        tvMaterialName.setText(materialName);
        tvMaterialModel.setText(materialMode);
        tvMaterialAttr.setText(materialAttr);
        setMaterialAttrStatus(tvMaterialAttr);
    }

    /**
     * 设置检验的标准水平等参数
     *
     * @param currentLevel  当前水平
     * @param currentAQL    当前AQL
     * @param sampleCode    样品编码
     * @param currentStrict 当前严格度
     */
    public void setStandardLevel(String currentLevel, String currentAQL, String sampleCode, String currentStrict) {
        //标准水平
        standQualityLevel.setTextViewContent(currentLevel);
        //AQL
        standAql.setTextViewContent(currentAQL);
        //试样半码
        standSampleHalfYard.setTextViewContent(sampleCode);
        //严格度
        standStrict.setTextViewContent(currentStrict);
    }

    /**
     * 设置质检操作的数据
     *
     * @param realReceiveQty 实收数
     * @param sampleQty      样品书
     * @param rejectQty      拒收数
     * @param fatalQty       致命缺陷数
     * @param seriousQty     严重缺陷数
     * @param commonlyQty    一般缺陷数
     * @param slightQty      轻微缺陷数
     * @param totalBadQty    不良总数
     */
    public void setQualityWorkData(int realReceiveQty, int sampleQty, int beginQty, int endQty, int rejectQty, int totalBadQty, int fatalQty, int seriousQty, int commonlyQty, int slightQty) {
        /**
         * 设置不良缺陷数的设置
         */
        standFatalBadnessNum.setTextViewContent(fatalQty);
        standSeriousBadnessNum.setTextViewContent(seriousQty);
        standNormalBadnessNum.setTextViewContent(commonlyQty);
        standSlightBadnessNum.setTextViewContent(slightQty);
        /**
         * 设置质检操作的数据
         */
        setTextViewText(tvReceiveNum, R.string.receive_num, realReceiveQty);
        setTextViewText(tvSampleNum, R.string.mr_advance_sample_num, sampleQty + "(" + beginQty + "~" + endQty + ")");
        setTextViewText(tvRefuseReceiveNum, R.string.reject_num_format, rejectQty);
        /**
         * 不良总数 和不良率
         */
        standBadnessTotalNum.setTextViewContent(totalBadQty);
        //不良总数
        double dTotalBadnessNum = (double) totalBadQty;
        //抽样数
        double dReceiveNum = (double) sampleQty;
        //转换成百分比
        NumberFormat nFromat = NumberFormat.getPercentInstance();
        String rates = nFromat.format(dTotalBadnessNum / dReceiveNum);
        standBadnessPercent.setTextViewContent(rates);
    }

    /**
     * 初始化检验项目的adapter
     */
    public void initCheckItemAdapter() {
        adapterCheckItem = new BaseRecyclerAdapter<ListCheckItemBean>(this, mCheckItemDatas) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_advance_sample;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, ListCheckItemBean item) {
                //样品编号
                holder.setTextView(R.id.tv_sample_code, item.getSampleSeq());
                //检验项目
                RecyclerView rlvQualityItem = (RecyclerView) holder.getView(R.id.rlv_qualit_item);
                BaseRecyclerAdapter<GetAdvance2Data.CheckItemDataBean> adapter = new BaseRecyclerAdapter<GetAdvance2Data.CheckItemDataBean>(Advance2Activity.this, item.getCheckItemBeen()) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
                        return R.layout.view_quality_item_result;
                    }

                    @Override
                    protected void bindData(RecyclerViewHolder holder, int position, GetAdvance2Data.CheckItemDataBean item) {
                        List<GetAdvance2Data.CheckItemBean> checkItem = mData.getCheckItem();
                        for (int i = 0; i < checkItem.size(); i++) {
                            if (item.getCheckItemId() == checkItem.get(i).getCheckItemId()) {
                                holder.setTextView(R.id.tv_title, "#" + checkItem.get(i).getCheckItemName());
                                holder.getImageView(R.id.iv_content).setSelected(item.getQcResult() == 0);
                            }
                        }
                    }
                };
                rlvQualityItem.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Advance2Activity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rlvQualityItem.setLayoutManager(linearLayoutManager);
                rlvQualityItem.addItemDecoration(new DividerItemDecoration(Advance2Activity.this, DividerItemDecoration.HORIZONTAL_LIST, R.drawable.item_badness_divider));
            }
        };
        rlvSample.setAdapter(adapterCheckItem);
        setRecycleViewScrollSmooth(rlvSample, new LinearLayoutManager(this));
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

    /**
     * 显示 不合格用户是否继续质检的dialog
     */
    private void showUnPassDialog() {
        final MyDialog myDialog = new MyDialog(this, R.layout.dialog_unpass_user_choose);
        myDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
                InputMethodUtils.hide(Advance2Activity.this);
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
            /**
             * sampleCode 自增，currentCheckposition为0，
             */
            sampleCode += 1;
            currentCheckposition = 0;
            showCheckItemDialog();
        }
    }

    /**
     * 检验项目的bean
     */
    GetAdvance2Data.CheckItemBean checkItemBean;
    List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData;

    /**
     * 检验项目的diaolog
     */
    public void showCheckItemDialog() {
        /**
         * 更改dialog 数据
         */
        updateDialogData();

        /**
         * 初始化dialog
         */
        if (null == checkItemDialog) {
            checkItemDialog = new MyDialog(Advance2Activity.this, R.layout.dialog_quality_check_item);
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
            llMeasureTip = (LinearLayout) checkItemDialog.getView(R.id.ll_measure_tip);
            rlvBadnessReason = (RecyclerView) checkItemDialog.getView(R.id.rlv_badness_reason);
            qualityResultCheckItem = (QualityResultView) checkItemDialog.getView(R.id.quality_result_chech_item);
            tvUnit = (TextView) checkItemDialog.getView(R.id.tv_unit);
            etMeasure = checkItemDialog.getEdittext(R.id.et_measure_value);
            dialogBtnNext = (Button) checkItemDialog.getView(R.id.btn_next);
            ivMrpDown = (ImageView) checkItemDialog.getView(R.id.iv_down);
            /**
             * 确定的按钮的监听器
             * 用于点击计算 合格  不合格的判断
             */
            checkItemDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    /**
                     * 隐藏输入框
                     */
                    InputMethodUtils.hide(Advance2Activity.this);
                    /**
                     * 隐藏 不良原因
                     */
                    hideBadnessReason();
                    /**
                     * 隐藏软键盘
                     */
                    InputMethodUtils.hide(Advance2Activity.this);
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
                        qualityResultCheckItem.setQualityResult(QualityResultView.QUALITY_RESULT_SUCCESS);
                    } else {
                        qualityResultCheckItem.setQualityResult(QualityResultView.QUALITY_RESULT_FAIL);
                    }
                }
            });
            /**
             * 显示弹框
             */
            checkItemDialog.setViewListener(R.id.rl_select_badness_reason, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    InputMethodUtils.hide(Advance2Activity.this);
                    /**
                     * 不合格才能显示不良原因
                     */
                    if (qualityResultCheckItem.getQcResult() == 3) {
                        /**
                         * 不良原因是否显示
                         */
                        if (rlvBadnessReason.getVisibility() == View.VISIBLE) {
                            hideBadnessReason();
                        } else {
                            showBadnessReasonRecycleView();
                        }
                    }
                }
            });
            /**
             * 内容的点击事件
             */
            checkItemDialog
                    .setContentViewListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            /**
                             * 隐藏输入框
                             */
                            InputMethodUtils.hide(Advance2Activity.this);
                            /**
                             * 隐藏 不良原因
                             */
                            hideBadnessReason();
                        }
                    });
            /**
             * 关闭dialog
             */
            checkItemDialog.setViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    InputMethodUtils.hide(Advance2Activity.this);
                    hideBadnessReason();
                    dialog.dismiss();
                }
            });
            checkItemDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    InputMethodUtils.hide(Advance2Activity.this);
                    hideBadnessReason();
                }
            });
            checkItemDialog.setButtonListener(R.id.btn_next, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    InputMethodUtils.hide(Advance2Activity.this);
                    hideBadnessReason();
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
                     * 如果 qualityResultCheckItem的质检结果为-1 则证明没有检验项目没有检验完成 提示用户进行检验测量值
                     */
                    if (qualityResultCheckItem.getQcResult() == -1) {
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
                    itemDataBean.setQCValue(measureStr);
                    itemDataBean.setQCResult(qualityResultCheckItem.getQcResult());
                    itemDataBean.setRemark("");
                    if (qualityResultCheckItem.getQcResult() == 3) {//不合格才有不良原因
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
                        if (faultId == 0) {
                            ToastUtils.showShort(getString(R.string.please_select_badness_reason));
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
//                        SparseArray<CheckQualityItemResultView> views = wrapQualityResult.getViews();
//                        for (int i = 0; i < views.size(); i++) {
//                            if (!wrapQualityResult.isPass(i)) {
//                                isPass = false;
//                            }
//                        }
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
                                qualityAdavance2.setQualityResult(QualityResultView.QUALITY_RESULT_WAIT_DEAL);
                            } else if (rejectNum >= aqlRejectQty) {
                                qcResult = 3;
                                qualityAdavance2.setQualityResult(QualityResultView.QUALITY_RESULT_FAIL);
                            } else {
                                qualityAdavance2.setQualityResult(QualityResultView.QUALITY_RESULT_SUCCESS);
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
                        updateDialogData();
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
     * 更改dialog  内容
     **/
    private void updateDialogViewContent() {
        tvCode.setText(String.valueOf(sampleCode));
        tvCheckItem.setText(checkItemBean.getCheckItemName());
        tvCheckMode.setText(checkItemBean.getJudgeType() == 1 ? getString(R.string.auto_judge) : getString(R.string.person_judge));
        llMeasureTip.setVisibility(checkItemBean.getJudgeType() == 1 ? View.VISIBLE : View.GONE);
        //检验项目结果是否可点击
        qualityResultCheckItem.setQualityResultCanClick(checkItemBean.getJudgeType() == 2);
        /**
         * 单位 （是否存在单位 进行判断）
         */
        if (!TextUtils.isEmpty(checkItemBean.getUnit())) {
            tvUnit.setText("(" + checkItemBean.getUnit() + ")");
        } else {
            tvUnit.setVisibility(View.GONE);
        }
        /**
         * 设置是否可点击
         */
        qualityResultCheckItem.clearBtnsStatus();
        /**
         * 不是修改 正常进行下一个检验项目 需要重新设置数据
         */
        etMeasure.setText("");
        etMeasure.setHint(R.string.please_input_measure_value);
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
     */
    private void updateDialogData() {
        if (null == checkItemDatas.get(sampleCode)||checkItemDatas.get(sampleCode).size()<mData.getCheckItem().size()) {
            checkItemDatas.put(sampleCode, mData.getCheckItem());
        }
        /**
         * 检测项目的实体
         */
        checkItemBean = mData.getCheckItem().get(currentCheckposition);
        /**
         * 不良原因的数据
         */
        faultData = checkItemBean.getFaultData();
    }

    /**
     * @param view
     * @param isUpdateResult    是否是更改检查项目
     * @param checkItemposition 检查项目的位置（更改时点击）
     */
    private BaseRecyclerAdapter<GetAdvance2Data.CheckItemBean.FaultDataBean> baseRecyclerAdapter;

    /**
     * 显示 不良原因
     */
    public void showBadnessReasonRecycleView() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotation_down);
        animation.setFillAfter(true);
        ivMrpDown.startAnimation(animation);
        rlvBadnessReason.setVisibility(View.VISIBLE);
        List<GetAdvance2Data.CheckItemBean.FaultDataBean> selecReviewFaultData = mData.getCheckItem().get(currentCheckposition).getFaultData();
        baseRecyclerAdapter = new BaseRecyclerAdapter<GetAdvance2Data.CheckItemBean.FaultDataBean>(this, selecReviewFaultData) {
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
        rlvBadnessReason.setLayoutManager(new LinearLayoutManager(Advance2Activity.this));
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
                List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemDatas.get(sampleCode).get(currentCheckposition).getFaultData();
                GetAdvance2Data.CheckItemBean.FaultDataBean faultDataBean = faultData.get(pos);
                faultDataBean.setFaultQty(faultDataBean.getFaultQty() + 1);
                tvSelectBadness.setText(faultDataBean.getFaultName());

                /**
                 * 隐藏不良原因
                 */
                hideBadnessReason();
            }
        });
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
            Animation animation = AnimationUtils.loadAnimation(Advance2Activity.this, R.anim.rotation_up);
            animation.setFillAfter(true);
            ivMrpDown.startAnimation(animation);
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
                     */
                    showCheckItemDialog();
                }
                break;
        }
    }
}
