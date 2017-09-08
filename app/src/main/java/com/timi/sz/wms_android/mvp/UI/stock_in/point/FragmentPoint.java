package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rmondjone.locktableview.LockTableView;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.StockInPointEvent;
import com.timi.sz.wms_android.mvp.base.BaseFragment;
import com.timi.sz.wms_android.view.MyDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.timi.sz.wms_android.base.uils.Constants.BUY_ORDE_NUM;

/**
 * $dsc  物料清点
 * author: timi
 * create at: 2017-08-25 15:36
 */

public class FragmentPoint extends BaseFragment<FragmentPointView, FragmentPointPresenter> implements FragmentPointView {
    @BindView(R.id.tv_sip_buy_num)
    TextView tvSipBuyNum;
    @BindView(R.id.tv_sip_buyer)
    TextView tvSipBuyer;
    @BindView(R.id.tv_sip_buy_from)
    TextView tvSipBuyFrom;
    @BindView(R.id.tv_sip_buy_date)
    TextView tvSipBuyDate;
    @BindView(R.id.ll_point_content)
    LinearLayout llPointContent;

    private BuyOrdernoBean mBuyBean;
    private SendOrdernoBean mSendBean;
    //请点数
    private int pointNum;
    //备品数
    private int spareNum;
    //点击的item position
    private int curretnPosition;
    //tabs
    ArrayList<ArrayList<String>> mTableDatas;
    //code 码
    private int intentCode;

    @Override
    public FragmentPointPresenter createPresenter() {
        return new FragmentPointPresenter(getActivity());
    }

    @Override
    public FragmentPointView createView() {
        return this;
    }

    @Override
    public void initData() {
        BaseMessage.register(this);
        mTableDatas = new ArrayList<ArrayList<String>>();
        Intent it = ((StockInPointActivity) getActivity()).getIntentCode();
        intentCode = it.getIntExtra(Constants.CODE_STR, Constants.COME_MATERAIL_NUM);
        if (intentCode == BUY_ORDE_NUM) {
            mBuyBean = new Gson().fromJson(it.getStringExtra(Constants.IN_STOCK_BUY_BEAN), BuyOrdernoBean.class);
        } else {//送货单
            mSendBean = new Gson().fromJson(it.getStringExtra(Constants.IN_STOCK_SEND_BEAN), SendOrdernoBean.class);

        }
        /**
         * 显示表体
         */
        showExcelDialog();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_stockin_point;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 显示物品清点的弹出框
     */
    private MyDialog mPointDialog = null;

    public void showGoodsPointDialog(final int position) {
        //存储点击位置
        curretnPosition = position;
      if (null == mPointDialog) {
            mPointDialog = new MyDialog(getActivity(), R.layout.dialog_stockin_point)
                    .setButtonListener(R.id.btn_stockin_point_cancel, getString(R.string.cancel), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.btn_stockin_point_save, getString(R.string.save), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            String pointNumStr = dialog.getEdittext(R.id.et_stockin_point_pro_point_num).getText().toString();
                            String spareNumStr = dialog.getEdittext(R.id.et_stockin_point_sparenum).getText().toString();
                            /**
                             * 请点数 和备品数
                             */
                            pointNum = TextUtils.isEmpty(pointNumStr) ? 0 : Integer.parseInt(pointNumStr);
                            spareNum = TextUtils.isEmpty(spareNumStr) ? 0 : Integer.parseInt(spareNumStr);
                            if (pointNum == 0 && spareNum == 0) {
                                ToastUtils.showShort(getActivity(), "请点数和备品数不能同时为0");
                                return;
                            }

                            Map<String, Object> params = new HashMap<>();
                            params.put("UserId", SpUtils.getInstance().getUserId());
                            params.put("MAC", PackageUtils.getMac());
                            params.put("OrgId", SpUtils.getInstance().getOrgId());
                            params.put("CountQty", pointNum);
                            params.put("GiveQty", spareNum);
                            if (intentCode ==BUY_ORDE_NUM) {//采购单
                                //获取数据 显示dialog
                                BuyOrdernoBean.SummaryResultsBean summaryResults = mBuyBean.getSummaryResults();
                                List<BuyOrdernoBean.DetailResultsBean> detailResults = mBuyBean.getDetailResults();
                                final BuyOrdernoBean.DetailResultsBean detailResultsBean = detailResults.get(position);
                                params.put("BizType", summaryResults.getBizType());
                                params.put("BillId", summaryResults.getId());
                                params.put("BillCode", summaryResults.getPoCode());
                                params.put("DetailId", detailResultsBean.getId());
                                params.put("ReceiveId", summaryResults.getReceiveId());
                                getPresenter().savePointMaterial(params);
                            }else{//送货单
                                //获取数据 显示dialog
                                List<SendOrdernoBean.DetailResultsBean> detailResults = mSendBean.getDetailResults();
                                final SendOrdernoBean.DetailResultsBean detailResultsBean = detailResults.get(position);
                                SendOrdernoBean.SummaryResultsBean summaryResults = mSendBean.getSummaryResults();
                                params.put("BizType", summaryResults.getBizType());
                                params.put("BillId", summaryResults.getId());
                                params.put("BillCode", summaryResults.getAsnCode());
                                params.put("DetailId", detailResultsBean.getId());
                                params.put("ReceiveId", summaryResults.getReceiveId());
                                getPresenter().saveSendMaterialPoint(params);
                            }
                            dialog.dismiss();
                        }
                    }).setAnimation(R.style.popWindow_animation_push);
            if (intentCode ==BUY_ORDE_NUM) {//采购单
                //获取数据 显示dialog
                List<BuyOrdernoBean.DetailResultsBean> detailResults = mBuyBean.getDetailResults();
                final BuyOrdernoBean.DetailResultsBean detailResultsBean = detailResults.get(position);

                mPointDialog.setTextViewContent(R.id.tv_stockin_point_pronum, String.format(getString(R.string.material_code), detailResultsBean.getMaterialCode()))
                        .setTextViewContent(R.id.tv_stockin_point_proname, String.format(getString(R.string.material_name), detailResultsBean.getMaterialName()))
                        .setTextViewContent(R.id.tv_stockin_point_promodel, String.format(getString(R.string.material_model), detailResultsBean.getMaterialStandard()))
                        .setTextViewContent(R.id.tv_stockin_point_buynum, String.format(getString(R.string.buy_num), detailResultsBean.getPoQty() + ""))
                        .setTextViewContent(R.id.tv_stockin_point_receive_pro_num, String.format(getString(R.string.arrive_good_num), detailResultsBean.getArrivalQty() + ""));

            }else{//送货单
                //获取数据 显示dialog
                List<SendOrdernoBean.DetailResultsBean> detailResults = mSendBean.getDetailResults();
                final SendOrdernoBean.DetailResultsBean detailResultsBean = detailResults.get(position);
                mPointDialog.setTextViewContent(R.id.tv_stockin_point_pronum, String.format(getString(R.string.material_code), detailResultsBean.getMaterialCode()))
                        .setTextViewContent(R.id.tv_stockin_point_proname, String.format(getString(R.string.material_name), detailResultsBean.getMaterialName()))
                        .setTextViewContent(R.id.tv_stockin_point_promodel, String.format(getString(R.string.material_model), detailResultsBean.getMaterialStandard()))
                        .setTextViewContent(R.id.tv_stockin_point_buynum, String.format(getString(R.string.send_goods_num), detailResultsBean.getPoQty() + ""))
                        .setTextViewContent(R.id.tv_stockin_point_receive_pro_num, String.format(getString(R.string.have_receive_num), detailResultsBean.getArrivalQty() + ""));


            }
        }
        mPointDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mPointDialog = null;
            }
        });
        mPointDialog.show();
    }

    /**
     * 保存物料清点成功
     */
    @Override
    public void savePointMaterial(Integer result) {
        /**
         * 更新清点记录 设置receive id
         */
        StockInPointEvent stockInPointEvent = new StockInPointEvent(StockInPointEvent.MATERIAL_POINT_RECORD_UPDATE);
        stockInPointEvent.receiveId = result;
        BaseMessage.post(stockInPointEvent);

        ToastUtils.showShort("物料请点保存成功");
        /**
         * 第一次设置接受id
         */
        mBuyBean.getSummaryResults().setReceiveId(result);
        BuyOrdernoBean.DetailResultsBean detailResultsBean = mBuyBean.getDetailResults().get(curretnPosition);
        /**
         * 设置清点的数量
         */
        int lastCountNum = detailResultsBean.getCountQty();
        if (pointNum > 0) {
            detailResultsBean.setCountQty(lastCountNum + pointNum);
        }
        /**
         * 设置备品的数量
         */
        int lastSpareNum = detailResultsBean.getGiveQty();
        if (spareNum > 0) {
            detailResultsBean.setGiveQty(lastSpareNum + spareNum);
        }
        showExcelDialog();
    }

    @Override
    public void saveSendPointMaterial(Integer result) {
        /**
         * 更新清点记录 设置receive id
         */
        StockInPointEvent stockInPointEvent = new StockInPointEvent(StockInPointEvent.MATERIAL_POINT_RECORD_UPDATE);
        stockInPointEvent.receiveId = result;
        BaseMessage.post(stockInPointEvent);

        ToastUtils.showShort("物料清点保存成功");
        /**
         * 第一次设置接受id
         */
        mSendBean.getSummaryResults().setReceiveId(result);
        SendOrdernoBean.DetailResultsBean detailResultsBean = mSendBean.getDetailResults().get(curretnPosition);
        /**
         * 设置清点的数量
         */
        int lastCountNum = detailResultsBean.getCountQty();
        if (pointNum > 0) {
            detailResultsBean.setCountQty(lastCountNum + pointNum);
        }
        /**
         * 设置备品的数量
         */
        int lastSpareNum = detailResultsBean.getGiveQty();
        if (spareNum > 0) {
            detailResultsBean.setGiveQty(lastSpareNum + spareNum);
        }
        showExcelDialog();
    }

    /**
     * 提交清点
     */
    @Override
    public void commitPointMaterial() {
        ToastUtils.showShort("提交清点成功");
        getActivity().finish();
    }

    /**
     * 获取物料清点的表体
     *
     * @param bean
     */
    @Override
    public void getPODetailsByCode(BuyOrdernoBean bean) {
        LogUitls.e("更新了清点的表体");
        mBuyBean.setDetailResults(bean.getDetailResults());
        showExcelDialog();
    }

    @Override
    public void getSendPODetailsByCode(SendOrdernoBean bean) {
        LogUitls.e("更新了送货单清点的表体");
        mSendBean.setDetailResults(bean.getDetailResults());
        showExcelDialog();
    }

    /**
     * 展示表体
     */
    public void showExcelDialog() {
        llPointContent.removeAllViews();
        mTableDatas.clear();
        ArrayList<String> mfristData = new ArrayList<String>();
        mfristData.add("行号");
        mfristData.add("物品编码");
        if (intentCode == BUY_ORDE_NUM) {//如果是采购单
            mfristData.add("采购数");
            mfristData.add("到货数");
            mfristData.add("入库数");
            mfristData.add("清点数");
            mfristData.add("备品数");
        } else {
            mfristData.add("送货数");
            mfristData.add("已收数");
            mfristData.add("清点数");
            mfristData.add("备品数");
            mfristData.add("来源单据");
            mfristData.add("采购数");
            mfristData.add("到货数");
            mfristData.add("入库数");
        }
        mTableDatas.add(mfristData);
        if (intentCode == BUY_ORDE_NUM) {
            BuyOrdernoBean.SummaryResultsBean summaryResults = mBuyBean.getSummaryResults();
            /**
             * 设置初始数据
             */
            if (null != summaryResults) {
                setTextViewText(tvSipBuyNum, R.string.order_no, summaryResults.getPoCode());
                setTextViewText(tvSipBuyDate, R.string.buy_date, summaryResults.getPoDate());
                setTextViewText(tvSipBuyFrom, R.string.buy_from, summaryResults.getSupplierName());
                setTextViewText(tvSipBuyer, R.string.buyer, ((null == summaryResults.getPurEmployeeName()) ? "" : summaryResults.getPurEmployeeName()));
            }
            /**
             * 存储下方列表的数据
             */
            for (int i = 0; i < mBuyBean.getDetailResults().size(); i++) {
                ArrayList<String> mRowDatas = new ArrayList<String>();
                BuyOrdernoBean.DetailResultsBean detailResultsBean = mBuyBean.getDetailResults().get(i);
                mRowDatas.add(i + 1 + "");
                mRowDatas.add(detailResultsBean.getMaterialCode());
                mRowDatas.add(detailResultsBean.getPoQty() + "");
                mRowDatas.add(detailResultsBean.getArrivalQty() + "");
                mRowDatas.add(detailResultsBean.getInStockQty() + "");
                mRowDatas.add(detailResultsBean.getCountQty() + "");
                mRowDatas.add(detailResultsBean.getGiveQty() + "");
                mTableDatas.add(mRowDatas);
            }
        } else {//送货单
            /**
             * 设置初始数据
             */
            SendOrdernoBean.SummaryResultsBean summaryResults = mSendBean.getSummaryResults();
            setTextViewText(tvSipBuyNum, R.string.order_no, summaryResults.getAsnCode());
            setTextViewText(tvSipBuyDate, R.string.buy_date, summaryResults.getAsnDate());
            setTextViewText(tvSipBuyFrom, R.string.buy_from, summaryResults.getSupplierName());
            setTextViewText(tvSipBuyer, R.string.buyer, summaryResults.getCreaterName());
            /**
             * 存储下方列表的数据
             */
            List<SendOrdernoBean.DetailResultsBean> detailResults = mSendBean.getDetailResults();
            for (int i = 0; i < detailResults.size(); i++) {
                ArrayList<String> mRowDatas = new ArrayList<String>();
                SendOrdernoBean.DetailResultsBean detailResultsBean = detailResults.get(i);
                //行号
                mRowDatas.add(detailResultsBean.getPoLine() + "");
                //物料码
                mRowDatas.add(detailResultsBean.getMaterialCode());
                //送货数
                mRowDatas.add(detailResultsBean.getDnQty() + "");
                //已收数量
                mRowDatas.add(detailResultsBean.getRecvQty() + "");
                //清点数量
                mRowDatas.add(detailResultsBean.getCountQty() + "");
                //备品数
                mRowDatas.add(detailResultsBean.getGiveQty() + "");
                //采购数
                mRowDatas.add(detailResultsBean.getPoQty() + "");
                //到货数
                mRowDatas.add(detailResultsBean.getArrivalQty() + "");
                //入库数
                mRowDatas.add(detailResultsBean.getInStockQty() + "");

                mTableDatas.add(mRowDatas);
            }
        }
        LockTableView mLockTableView = new LockTableView(getActivity(), llPointContent, mTableDatas);
        Log.e("表格加载开始", "当前线程：" + Thread.currentThread());
        mLockTableView.setLockFristColumn(false) //是否锁定第一列
                .setLockFristRow(true) //是否锁定第一行
                .setMaxColumnWidth(100) //列最大宽度
                .setMinColumnWidth(10) //列最小宽度
                .setMinRowHeight(5)//行最小高度
                .setMaxRowHeight(20)//行最大高度
                .setTextViewSize(12) //单元格字体大小
                .setFristRowBackGroudColor(R.color.table_head)//表头背景色
                .setTableHeadTextColor(R.color.beijin)//表头字体颜色
                .setTableContentTextColor(R.color.border_color)//单元格字体颜色
                .setNullableString("0") //空值替换值
                .setTableViewListener(new LockTableView.OnTableViewListener() {
                    @Override
                    public void onTableViewScrollChange(int x, int y) {
                    }

                    @Override
                    public void onTabViewClickListener(int position) {
                        showGoodsPointDialog(position);
                    }
                })//设置滚动回调监听
                .show(); //显示表格,此方法必须调用
    }

    /**
     * 返回receive id
     *
     * @return
     */
    public int getReceiveId() {
        if(intentCode==BUY_ORDE_NUM){
            return mBuyBean.getSummaryResults().getReceiveId();
        }else {
            return mSendBean.getSummaryResults().getReceiveId();
        }
    }

    /**
     * 当清点记录 做修改和 删除时 更新物料清点的v表体
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshPointMaterialPoDetails(StockInPointEvent event) {
        if (event.getEvent().equals(StockInPointEvent.MATERIAL_POINT_UPDATE)) {
            Map<String, Object> params = new HashMap<>();
            params.put("UserId", SpUtils.getInstance().getUserId());
            params.put("MAC", PackageUtils.getMac());
            params.put("OrgId", SpUtils.getInstance().getOrgId());
            if(intentCode==BUY_ORDE_NUM){
                params.put("BillCode", mBuyBean.getSummaryResults().getPoCode());
                params.put("BizType", mBuyBean.getSummaryResults().getBizType());
                params.put("ScanId", mBuyBean.getSummaryResults().getReceiveId());
                getPresenter().getPODetailsByCode(params);
            }else {
                params.put("BillCode", mSendBean.getSummaryResults().getAsnCode());
                params.put("BizType", mSendBean.getSummaryResults().getBizType());
                params.put("ScanId", mSendBean.getSummaryResults().getReceiveId());
                getPresenter().getASNDetailsByCode(params);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.btn_point_commit)
    public void onViewClicked() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("MAC", PackageUtils.getMac());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("ScanId", mBuyBean.getSummaryResults().getReceiveId());
        params.put("SubmitType", 0);
        getPresenter().commitMaterialPoint(params);
    }
}
