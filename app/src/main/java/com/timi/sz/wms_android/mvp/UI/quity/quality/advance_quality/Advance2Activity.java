package com.timi.sz.wms_android.mvp.UI.quity.quality.advance_quality;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.StringUils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.RequestUpdateCheckitemBean;
import com.timi.sz.wms_android.bean.quality.adavance.CommitAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.bean.quality.adavance.IQCGetAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.ListCheckItemBean;
import com.timi.sz.wms_android.bean.quality.adavance.UpdateCheckItemResult;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.UI.quity.reject.QualityRejectActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyCheckItemDialog;
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

import static com.timi.sz.wms_android.view.QualityResultView.QUALITY_RESULT_FAIL;
import static com.timi.sz.wms_android.view.QualityResultView.QUALITY_RESULT_SUCCESS;
import static com.timi.sz.wms_android.view.QualityResultView.QUALITY_RESULT_WAIT_DEAL;

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
    @BindView(R.id.tv_aql_accept)
    TextView tvAQLAccept;
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
     * 初始样品编码
     */
    private int oldSampleCode = 1;
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
        //设置质检结果
        switch (normalSummary.getQcResult()) {
            case 1://合格
                qualityAdavance2.setQualityResult(QUALITY_RESULT_SUCCESS);
                break;
            case 2://待定
                qualityAdavance2.setQualityResult(QUALITY_RESULT_WAIT_DEAL);
                break;
            case 3://不合格
                qualityAdavance2.setQualityResult(QUALITY_RESULT_FAIL);
                break;
        }
        //设置物料信息
        setMaterialInfo(normalSummary.getReceiptCode(), normalSummary.getReceiptDate()
                , normalSummary.getSourceBillCode(), normalSummary.getSupplierName()
                , normalSummary.getMaterialCode(), normalSummary.getMaterialName(),
                normalSummary.getMaterialStandard(), normalSummary.getMaterialAttribute());
        //设置标准水平
        GetAdvance2Data.AdvanceSummaryBean advanceSummary = data.getAdvanceSummary();
        setStandardLevel(advanceSummary.getCurrentLevel(), advanceSummary.getCurrentAQL()
                , advanceSummary.getSampleCode(), advanceSummary.getCurrentStrict());
        //设置质检操作的数据
        setQualityWorkData(normalSummary.getReceiveQty(), normalSummary.getSampleQty()
                , advanceSummary.getBeginQty(), advanceSummary.getEndQty()
                , normalSummary.getRejectQty(), normalSummary.getNgQty()
                , advanceSummary.getFatalQty(), advanceSummary.getSeriousQty()
                , advanceSummary.getCommonlyQty(), advanceSummary.getSlightQty());
        //aql 数
        setTextViewText(tvAQLAccept, R.string.mrp_aql_tip, advanceSummary.getAqlAcceptQty() + "~" + advanceSummary.getAqlRejectQty());

        //样品编码
        sampleCode = (advanceSummary.getQcQty() + 1);
        oldSampleCode = (advanceSummary.getQcQty() + 1);
        if (advanceSummary.getQcQty() == normalSummary.getSampleQty()) {
            tvSampleCode.setText(R.string.none);
        } else {
            setTextViewContent(tvSampleCode, sampleCode);
        }
        //获取样品数据
        //数据
        List<GetAdvance2Data.CheckItemDataBean> checkItemData = mData.getCheckItemData();
        /**
         * 对源数据进行处 理
         */
        if (null != checkItemData && !checkItemData.isEmpty()) {
            for (int i = 0; i < checkItemData.size(); i++) {
                boolean isHaveData = false;
                for (int j = 0; j < mCheckItemDatas.size(); j++) {
                    if (mCheckItemDatas.get(j).getSampleSeq() == checkItemData.get(i).getSampleSeq()) {
                        //相同的sampleCode 直接添加
                        mCheckItemDatas.get(j).getCheckItemBeen().add(checkItemData.get(i));
                        isHaveData = true;
                    }
                }
                if (!isHaveData) {
                    //初始化一个新的ListCheckItemBean对象
                    ListCheckItemBean bean = new ListCheckItemBean();
                    bean.setSampleSeq(checkItemData.get(i).getSampleSeq());
                    bean.setCheckItemBeen(new ArrayList<GetAdvance2Data.CheckItemDataBean>());
                    bean.getCheckItemBeen().add(checkItemData.get(i));
                    mCheckItemDatas.add(bean);
                }
            }
        }
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
         * 提交质检成功后 清空选择的链表（即提交时的数据链表）
         */
        mSelectFaultData.clear();
//        /**
//         * 参数
//         */
//        Map<String, Object> params = new HashMap<>();
//        params.put("UserId", SpUtils.getInstance().getUserId());
//        params.put("OrgId", SpUtils.getInstance().getOrgId());
//        params.put("mac", PackageUtils.getMac());
//        params.put("ReceiptId", receiptId);
//        params.put("ReceiptDetailId", receiptDetailId);
//        getPresenter().getAdvanceCheckItemData(params);
        /**
         * 高检2 数据处理
         */
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
        showCheckItemDialogJust();
    }

    @Override
    public void submitFinish() {

        /**
         *  如果质检合格的话 要对拒收数进行判断
         */
        switch (qcResult) {
            case 1://合格
                if (rejectNum > 0 && mData.getNormalSummary().isIsBarCode()) {//拒收数大于0  跳转到质检拒收并且有条码
                    /**
                     * 跳转到质检拒收
                     */
                    Intent intent = new Intent(Advance2Activity.this, QualityRejectActivity.class);
                    intent.putExtra("mData", new Gson().toJson(mData));
                    intent.putExtra("rejectNum", rejectNum);
                    startActivity(intent);
                } else {
                    tvNext.setText(getString(R.string.quality_complete));
                    ToastUtils.showShort(getString(R.string.normal_quality_tip));
                    /**
                     * 发送质检成功的消息
                     */
                    BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_CONFRIM));
                }
                break;
            case 2://待定  待定直接提示用户 质检结果为待定，并且关闭界面返回到清单的界面
                ToastUtils.showShort(getString(R.string.quality_wait_deal_tip));
                /**
                 * 发送质检成功的消息
                 */
                BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_CONFRIM));
                break;
            case 3://不合格
                ToastUtils.showShort(getString(R.string.normal_unquality_tip));
                /**
                 * 发送质检成功的消息
                 */
                BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_CONFRIM));
                break;
        }
    }

    @Override
    public void updateCheckItemData(UpdateCheckItemResult result) {
        ToastUtils.showShort(getString(R.string.update_check_item_result_success));
        //设置不良数
        GetAdvance2Data.AdvanceSummaryBean advanceSummary = mData.getAdvanceSummary();
        advanceSummary.setFatalQty(result.getFatalQty());
        advanceSummary.setCommonlyQty(result.getCommonlyQty());
        advanceSummary.setSlightQty(result.getSlightQty());
        advanceSummary.setSeriousQty(result.getSeriousQty());
        //设置拒收数
        GetAdvance2Data.NormalSummaryBean normalSummary = mData.getNormalSummary();
        normalSummary.setNgQty(result.getNgQty());
        //设置文本
        standFatalBadnessNum.setTextViewContent(result.getFatalQty());
        standSeriousBadnessNum.setTextViewContent(result.getSeriousQty());
        standNormalBadnessNum.setTextViewContent(result.getCommonlyQty());
        standSlightBadnessNum.setTextViewContent(result.getSlightQty());
        standBadnessTotalNum.setTextViewContent(result.getNgQty());
        //计算不良率
        //不良总数
        double dTotalBadnessNum = (double) result.getNgQty();
        //抽样数
        double dReceiveNum = (double) normalSummary.getSampleQty();
        //转换成百分比
        NumberFormat nFromat = NumberFormat.getPercentInstance();
        String rates = nFromat.format(dTotalBadnessNum / dReceiveNum);
        standBadnessPercent.setTextViewContent(rates);
        //拒收数
        normalSummary.setReceiveQty(result.getRejectQty());
        setTextViewText(tvRefuseReceiveNum, R.string.reject_num_format, result.getRejectQty());
        /**
         * 参数
         */
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        params.put("ReceiptId", receiptId);
        params.put("ReceiptDetailId", receiptDetailId);
        getPresenter().getAdvanceCheckItemData(params);
    }

    @Override
    public void getAdvanceCheckItemData(IQCGetAdvanceData o) {

        //清空链表
        mCheckItemDatas.clear();

        // 设置样品编码 (若全部检验完毕 则将样品编号设为无)
        if (o.getQcQty() == mData.getNormalSummary().getSampleQty()) {
            setTextViewContent(tvSampleCode, getString(R.string.none));
        } else {
            setTextViewContent(tvSampleCode, sampleCode);
        }
        //数据
        List<GetAdvance2Data.CheckItemDataBean> checkItemData = o.getCheckItemData();
        /**
         * 对源数据进行处 理
         */
        if (null != checkItemData && !checkItemData.isEmpty()) {
            for (int i = 0; i < checkItemData.size(); i++) {
                boolean isHaveData = false;
                for (int j = 0; j < mCheckItemDatas.size(); j++) {
                    if (mCheckItemDatas.get(j).getSampleSeq() == checkItemData.get(i).getSampleSeq()) {
                        //相同的sampleCode 直接添加
                        mCheckItemDatas.get(j).getCheckItemBeen().add(checkItemData.get(i));
                        isHaveData = true;
                    }
                }
                if (!isHaveData) {
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

        tvOrderno.setText(receiveOrderno);
        tvReceiveMaterialDate.setText(receiveDate);
        tvOrderNum.setText(orderno);
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
            protected void bindData(RecyclerViewHolder holder, int position, final ListCheckItemBean item) {
                //样品编号
                holder.setTextView(R.id.tv_sample_code, "#" + item.getSampleSeq());
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
                                holder.setTextView(R.id.tv_title, checkItem.get(i).getCheckItemName());
                                holder.getImageView(R.id.iv_content).setSelected(item.getQcResult() == 0);
                            }
                        }
                    }
                };
                rlvQualityItem.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int pos) {
                        showUpdateCheckItemDialog(item.getCheckItemBeen().get(pos));
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Advance2Activity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rlvQualityItem.setLayoutManager(linearLayoutManager);
//                rlvQualityItem.addItemDecoration(new DividerItemDecoration(Advance2Activity.this, DividerItemDecoration.HORIZONTAL_LIST, R.drawable.item_badness_divider));
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
        sampleCode += 1;
        /**
         * 如果是最后一个样品编码
         */
        if (sampleCode >mData.getNormalSummary().getSampleQty()) {
            tvSampleCode.setText(R.string.none);
        } else {
            /**
             * sampleCode 自增，currentCheckposition为0，
             */
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
                    float measureValue = 0;
                    try {
                        measureValue = Float.parseFloat(measureStr);
                    } catch (Exception e) {
                        ToastUtils.showShort(getString(R.string.please_input_right_value));
                        return;
                    } finally {

                    }
                    /**
                     * 设置是否合格
                     */
                    if (measureValue >= checkItemBean.getLimitLow() && measureValue <= checkItemBean.getLimitHigh()) {
                        qualityResultCheckItem.setQualityResult(QualityResultView.QUALITY_RESULT_SUCCESS);
                        checkItemDialog.getView(R.id.rl_select_badness_reason).setVisibility(View.GONE);

                    } else {
                        qualityResultCheckItem.setQualityResult(QualityResultView.QUALITY_RESULT_FAIL);
                        checkItemDialog.getView(R.id.rl_select_badness_reason).setVisibility(View.VISIBLE);

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
                    if (qualityResultCheckItem.getQcResult() == QualityResultView.QUALITY_RESULT_FAIL) {
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
            /**
             * 消失 置为空
             */
            checkItemDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    checkItemDialog = null;
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
                     * 当检验新的样品的时候，清空数据
                     */
                    if (currentCheckposition == 0 && null != mCommitAdvanceData.getItemData()) {
                        mCommitAdvanceData.getItemData().clear();
                    }
                    /**
                     * 如果 qualityResultCheckItem的质检结果为QUALITY_RESULT_DEFAULT 则证明没有检验项目没有检验完成 提示用户进行检验测量值
                     */
                    if (qualityResultCheckItem.getQcResult() == QualityResultView.QUALITY_RESULT_DEFAULT) {
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
                     * 设置链表中bean 的数据
                     */
                    List<GetAdvance2Data.CheckItemBean> checkItem = mData.getCheckItem();
                    GetAdvance2Data.CheckItemBean checkItemBean = checkItem.get(currentCheckposition);
                    CommitAdvanceData.ItemDataBean itemDataBean = new CommitAdvanceData.ItemDataBean();
                    itemDataBean.setCheckItemId(checkItemBean.getCheckItemId());
                    itemDataBean.setQCValue(measureStr);
                    itemDataBean.setQCResult(qualityResultCheckItem.getQcResult());
                    itemDataBean.setRemark("");
                    if (qualityResultCheckItem.getQcResult() == QualityResultView.QUALITY_RESULT_FAIL) {//不合格才有不良原因
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
                    //存入新的质检项目
                    boolean isHaveInsertNewData = false;
                    for (int i = 0; i < mCheckItemDatas.size(); i++) {
                        //判断是否存在 如果存在则直接进行添加
                        if (sampleCode == mCheckItemDatas.get(i).getSampleSeq()) {
                            isHaveInsertNewData = true;
                            mCheckItemDatas.get(i).getCheckItemBeen().add(insertOrUpdateCheckIteamData(itemDataBean));
                        }
                    }
                    /**
                     * 如果检验项目的列表中 不存在则新建链表  加入 样品编码，然后直接插入数据
                     */
                    if (!isHaveInsertNewData) {
                        ListCheckItemBean insertNewListItemBean = new ListCheckItemBean();
                        insertNewListItemBean.setSampleSeq(sampleCode);
                        insertNewListItemBean.setCheckItemBeen(new ArrayList<GetAdvance2Data.CheckItemDataBean>());
                        insertNewListItemBean.getCheckItemBeen().add(insertOrUpdateCheckIteamData(itemDataBean));
                        mCheckItemDatas.add(insertNewListItemBean);
                    }
                    //adapterCheckItem更新
                    adapterCheckItem.notifyDataSetChanged();
                    //设置提交的数据
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
                        for (int i = 0; i < mCheckItemDatas.size(); i++) {
                            List<GetAdvance2Data.CheckItemDataBean> checkItemBeen = mCheckItemDatas.get(i).getCheckItemBeen();
                            for (int j = 0; j < checkItemBeen.size(); j++) {
                                if (checkItemBeen.get(j).getQcResult() != 0) {
                                    isPass = false;
                                }
                            }
                        }
                        /**
                         * 不良缺陷的个数
                         */
                        int FatalQty = mData.getAdvanceSummary().getFatalQty();//致命缺陷
                        int SeriousQty = mData.getAdvanceSummary().getSeriousQty();//严重缺陷
                        int CommonlyQty = mData.getAdvanceSummary().getCommonlyQty();//一般缺陷
                        int SlightQty = mData.getAdvanceSummary().getSlightQty();//轻微缺陷
                        /**
                         * 拒收的数量(拿到上次质检的拒收数)
                         */
                        rejectNum = mData.getNormalSummary().getRejectQty();
                        /**
                         * 不良总数（拿到上次的不良数）
                         */
                        int totalBadnessNum = mData.getNormalSummary().getNgQty();
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
                            for (int key = oldSampleCode; key <= sampleCode; key++) {

                                /**
                                 * 是否当前样品 被拒收
                                 */
                                boolean isHaveReject = false;

                                List<GetAdvance2Data.CheckItemBean> checkItemBeen = checkItemDatas.get(sampleCode);
                                for (int i = 0; i < checkItemBeen.size(); i++) {
                                    List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemBeen.get(i).getFaultData();
                                    for (int j = 0; j < faultData.size(); j++) {
                                        GetAdvance2Data.CheckItemBean.FaultDataBean faultDataBean = faultData.get(j);
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
                                            case "Z"://轻微缺陷
                                                SlightQty = SlightQty + faultDataBean.getFaultQty();
                                                break;
                                        }
                                        /**
                                         * 如果不良数的数量不为0 则设置到选择的不良原因的链表，用于提交数据
                                         */
                                        if (faultDataBean.getFaultQty() != 0) {
                                            //设置当前样品被拒收
                                            isHaveReject = true;
                                        }
                                    }
                                }
                                /**
                                 * 如果当前被拒收 则增加拒收数
                                 */
                                if (isHaveReject) {
                                    rejectNum += 1;
                                }
                            }
                            //计算不良总数
                            totalBadnessNum = FatalQty + SeriousQty + CommonlyQty + SlightQty;
                            //允许拒收数
                            int aqlRejectQty = mData.getAdvanceSummary().getAqlRejectQty();
                            int aqlAcceptQty = mData.getAdvanceSummary().getAqlAcceptQty();
                            //拒收数
                            /**
                             *  1、当拒收数 大于允许数并且小于拒收数的时候  qcResult = 2; 待定
                             *  2、当拒收数 大于等于拒收数                     qcResult = 3; 不合格
                             *  3、不同的qcResult 设置不同的质检结果（不是弹框的合不合格而是整个质检过程中的合不合格）
                             */
                            if (totalBadnessNum > aqlAcceptQty && totalBadnessNum < aqlRejectQty) {//待定的状态
                                qcResult = 2;
                                qualityAdavance2.setQualityResult(QualityResultView.QUALITY_RESULT_WAIT_DEAL);
                            } else if (totalBadnessNum >= aqlRejectQty) {
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
                    } else

                    {
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
        tvSampleCode.setText(String.valueOf(sampleCode));
        tvCode.setText(String.valueOf(sampleCode));
        tvCheckItem.setText(checkItemBean.getCheckItemName());
        tvCheckMode.setText(checkItemBean.getJudgeType() == 1 ? getString(R.string.auto_judge) : getString(R.string.person_judge));
        llMeasureTip.setVisibility(checkItemBean.getJudgeType() == 1 ? View.VISIBLE : View.GONE);
        checkItemDialog.getView(R.id.rl_select_badness_reason).setVisibility(View.VISIBLE);
        checkItemDialog.getView(R.id.btn_confirm).setVisibility(checkItemBean.getJudgeType() == 1 ? View.VISIBLE : View.GONE);
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
        if (null == checkItemDatas.get(sampleCode)) {
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
                faultDataBean.setFaultQty(1);
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
                    if (sampleCode > mData.getNormalSummary().getSampleQty()) {
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
                if (null == mData.getCheckItem()) {
                    ToastUtils.showShort(getString(R.string.current_material_no_checkitem));
                    return;
                }
                /**
                 * 如果样品全部质检完成
                 */
                if (sampleCode >mData.getNormalSummary().getSampleQty()) {
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

    /**
     * 插入 或者修改 检验项目
     *
     * @param itemDataBean
     * @return
     */
    public GetAdvance2Data.CheckItemDataBean insertOrUpdateCheckIteamData(CommitAdvanceData.ItemDataBean itemDataBean) {
        GetAdvance2Data.CheckItemDataBean checkItemDataBean = new GetAdvance2Data.CheckItemDataBean();
        checkItemDataBean.setSampleSeq(sampleCode);
        checkItemDataBean.setQcResult(itemDataBean.getQCResult());
        checkItemDataBean.setQcValue(itemDataBean.getQCValue());
        checkItemDataBean.setRemark(itemDataBean.getRemark());
        checkItemDataBean.setFaultId(itemDataBean.getFaultId());
        checkItemDataBean.setCheckItemId(itemDataBean.getCheckItemId());
        return checkItemDataBean;
    }

    /**
     * 显示更改项目的弹窗
     *
     * @param listCheckItemBean
     */
    private void showUpdateCheckItemDialog(final GetAdvance2Data.CheckItemDataBean listCheckItemBean) {
        new MyCheckItemDialog(this).updateInitData(mData.getCheckItem(), listCheckItemBean, new MyCheckItemDialog.CheckItemDialogListener() {
            @Override
            public void conftimClick() {

            }

            @Override
            public void updateClick(String measureValue, int qcResult, int checkItemId, int faultId) {
                //如果相等  直接更改 不去请求
                if (sampleCode == listCheckItemBean.getSampleSeq()) {
                    for (int i = 0; i < mCheckItemDatas.size(); i++) {
                        if (listCheckItemBean.getSampleSeq() == mCheckItemDatas.get(i).getSampleSeq()) {
                            List<GetAdvance2Data.CheckItemDataBean> checkItemBeen = mCheckItemDatas.get(i).getCheckItemBeen();
                            for (int j = 0; j < checkItemBeen.size(); j++) {
                                /**
                                 * 如果找到了相应的checkitemdata 数据直接更改 并且 更新adapter
                                 */
                                if (checkItemBeen.get(j).getCheckItemId() == checkItemId) {
                                    GetAdvance2Data.CheckItemDataBean checkItemDataBean = checkItemBeen.get(j);
                                    checkItemDataBean.setQcResult(qcResult);
                                    checkItemDataBean.setQcValue(measureValue);
                                    checkItemDataBean.setFaultId(faultId);
                                }
                            }
                        }
                    }
                    updateCommitData(sampleCode,measureValue,qcResult,checkItemId,faultId);
                    //刷新适配器
                    adapterCheckItem.notifyDataSetChanged();
                } else {
                    //现更改数据
                    updateCommitData(sampleCode,measureValue,qcResult,checkItemId,faultId);
                    //网络请求
                    RequestUpdateCheckitemBean bean=new RequestUpdateCheckitemBean();
                    bean.setMAC(PackageUtils.getMac());
                    bean.setQCQty(listCheckItemBean.getSampleSeq());
                    bean.setOrgId(SpUtils.getInstance().getOrgId());
                    bean.setUserId( SpUtils.getInstance().getUserId());
                    bean.setReceiptId(receiptId);
                    bean.setReceiptDetailId(receiptDetailId);

                    RequestUpdateCheckitemBean.ItemDataBean itemBean=new RequestUpdateCheckitemBean.ItemDataBean();
                    itemBean.setRemark(listCheckItemBean.getRemark());
                    itemBean.setCheckItemId(listCheckItemBean.getCheckItemId());
                    itemBean.setQCResult(qcResult);
                    itemBean.setQCValue(measureValue);

                    bean.setItemData(itemBean);


                    getPresenter().updateCheckItemData(bean);
                }
            }

        }).showMyCheckItenDialog();
    }

    /**
     * 更改提交的数据（即做质检时需要提交的数据）
     */
    public void  updateCommitData(int sampleCode, String measureValue, int qcResult, int checkItemId, int faultId){
        if(null!=checkItemDatas.get(sampleCode)){
            List<GetAdvance2Data.CheckItemBean> checkItemBeen = checkItemDatas.get(sampleCode);
            for (int i = 0; i <checkItemBeen.size() ; i++) {
                if(checkItemBeen.get(i).getCheckItemId()==checkItemId){
                    GetAdvance2Data.CheckItemBean checkItemBean = checkItemBeen.get(i);
                    List<GetAdvance2Data.CheckItemBean.FaultDataBean> faultData = checkItemBean.getFaultData();
                    for (int j = 0; j < faultData.size(); j++) {
                        if(faultData.get(j).getFaultId()==faultId){
                            faultData.get(j).setFaultQty(1);
                        }else {
                            faultData.get(j).setFaultQty(0);
                        }
                    }
                }
            }
            /**
             * 设置选择的数据
             */
            for (int i = 0; i <mSelectFaultData.size() ; i++) {
                if (mSelectFaultData.get(i).getCheckItemId()==checkItemId) {
                    mSelectFaultData.get(i).setFaultId(faultId);
                    mSelectFaultData.get(i).setQCResult(qcResult);
                    mSelectFaultData.get(i).setQCValue(measureValue);
                }
            }
        }
    }
}

