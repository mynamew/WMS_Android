package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.CommonSimpleHeaderAndFooterTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.StockInPointEvent;
import com.timi.sz.wms_android.mvp.UI.quity.quality.QualityCheckActivity;
import com.timi.sz.wms_android.mvp.base.BaseFragment;
import com.timi.sz.wms_android.view.MyDialog;
import com.timi.sz.wms_android.view.excelview.DisplayUtil;
import com.timi.sz.wms_android.view.excelview.MyExcelView;

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
    @BindView(R.id.rlv_point)
    RecyclerView rlvPoint;

    private BuyOrdernoBean mBuyBean;
    private SendOrdernoBean mSendBean;
    //请点数
    private int pointNum;
    //备品数
    private int spareNum;
    //点击的item position
    private int curretnPosition;
    /**
     * adapter
     */
    BaseRecyclerAdapter<BuyOrdernoBean.DetailResultsBean> adapter;
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
        //判断code  是送货单还是采购单
        Intent it = ((StockInPointActivity) getActivity()).getIntentCode();
        intentCode = it.getIntExtra(Constants.CODE_STR, Constants.COME_MATERAIL_NUM);
        if (intentCode == BUY_ORDE_NUM) {
            mBuyBean = new Gson().fromJson(it.getStringExtra(Constants.IN_STOCK_BUY_BEAN), BuyOrdernoBean.class);
        } else {//送货单
            mSendBean = new Gson().fromJson(it.getStringExtra(Constants.IN_STOCK_SEND_BEAN), SendOrdernoBean.class);
        }

        /**
         * 标题的数据
         */
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
        } else {//送货单
            /**
             * 设置初始数据
             */
            SendOrdernoBean.SummaryResultsBean summaryResults = mSendBean.getSummaryResults();
            if (null != summaryResults) {
                setTextViewText(tvSipBuyNum, R.string.order_no, summaryResults.getAsnCode());
                setTextViewText(tvSipBuyDate, R.string.buy_date, summaryResults.getAsnDate());
                setTextViewText(tvSipBuyFrom, R.string.buy_from, summaryResults.getSupplierName());
                setTextViewText(tvSipBuyer, R.string.buyer, summaryResults.getCreaterName());
            }
        }
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<BuyOrdernoBean.DetailResultsBean>(getActivity(), intentCode == BUY_ORDE_NUM ? mBuyBean.getDetailResults() : mSendBean.getDetailResults()) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    if (intentCode == BUY_ORDE_NUM) {
                        return R.layout.item_point;
                    } else {
                        return R.layout.item_point_send;
                    }
                }

                @Override
                protected int getHeaderLayoutId() {
                    if (intentCode == BUY_ORDE_NUM) {
                        return R.layout.header_point_buy;
                    } else {
                        return R.layout.header_point_send;
                    }

                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, BuyOrdernoBean.DetailResultsBean item) {
                    holder.setTextView(R.id.tv_line_num, item.getPoLine());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode());
                    holder.setTextView(R.id.tv_arrive_good_num, item.getArrivalQty());
                    holder.setTextView(R.id.tv_buy_num, item.getPoQty());
                    holder.setTextView(R.id.tv_instock_num, item.getInStockQty());
                    holder.setTextView(R.id.tv_point_num, item.getCountQty());
                    holder.setTextView(R.id.tv_spare_num, item.getGiveQty());

                }
            };
        }
        rlvPoint.setAdapter(adapter);
        rlvPoint.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlvPoint.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                showGoodsPointDialog(pos);
            }
        });
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
                    .setButtonListener(R.id.btn_cancel, getString(R.string.cancel), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.btn_commit, getString(R.string.save), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            String pointNumStr = dialog.getEdittext(R.id.et_stockin_point_pro_point_num).getText().toString();
                            String spareNumStr = dialog.getEdittext(R.id.et_stockin_point_sparenum).getText().toString();
                            /**
                             * 请点数 和备品数
                             */
                            pointNum = TextUtils.isEmpty(pointNumStr) ? 0 : Integer.parseInt(pointNumStr);
                            spareNum = TextUtils.isEmpty(spareNumStr) ? 0 : Integer.parseInt(spareNumStr);
                            /**
                             * 备品数和清点数同时为0 的时候
                             */
                            if (pointNum == 0 && spareNum == 0) {
                                ToastUtils.showShort(getActivity(), getString(R.string.point_num_and_spare_num_no_zero));
                                return;
                            }
                            int receiveNum = intentCode == BUY_ORDE_NUM ? mBuyBean.getDetailResults().get(position).getPoQty() : mSendBean.getDetailResults().get(position).getPoQty();
                            if (pointNum > receiveNum) {
                                ToastUtils.showShort(getActivity(), getString(R.string.point_num_no_more_buy_num));
                                return;
                            }
                            Map<String, Object> params = new HashMap<>();
                            params.put("UserId", SpUtils.getInstance().getUserId());
                            params.put("MAC", PackageUtils.getMac());
                            params.put("OrgId", SpUtils.getInstance().getOrgId());
                            params.put("CountQty", pointNum);
                            params.put("GiveQty", spareNum);
                            if (intentCode == BUY_ORDE_NUM) {//采购单
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
                            } else {//送货单
                                //获取数据 显示dialog
                                List<BuyOrdernoBean.DetailResultsBean> detailResults = mSendBean.getDetailResults();
                                final BuyOrdernoBean.DetailResultsBean detailResultsBean = detailResults.get(position);
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
            mPointDialog.setViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                }
            });
            BuyOrdernoBean.DetailResultsBean detailResultsBean = null;
            if (intentCode == BUY_ORDE_NUM) {//采购单
                //获取数据 显示dialog
                List<BuyOrdernoBean.DetailResultsBean> detailResults = mBuyBean.getDetailResults();
                detailResultsBean = detailResults.get(position);


            } else {//送货单
                //获取数据 显示dialog
                List<BuyOrdernoBean.DetailResultsBean> detailResults = mSendBean.getDetailResults();
                detailResultsBean = detailResults.get(position);
            }
            mPointDialog.setTextViewContent(R.id.tv_material_code, detailResultsBean.getMaterialCode())
                    .setTextViewContent(R.id.tv_material_name, detailResultsBean.getMaterialName())
                    .setTextViewContent(R.id.tv_material_model, detailResultsBean.getMaterialStandard())
                    .setTextViewContent(R.id.tv_buy_num, detailResultsBean.getPoQty())
                    .setTextViewContent(R.id.tv_arrive_goods_num, detailResultsBean.getArrivalQty());
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
        adapter.notifyDataSetChanged();
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
        BuyOrdernoBean.DetailResultsBean detailResultsBean = mSendBean.getDetailResults().get(curretnPosition);
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
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getSendPODetailsByCode(SendOrdernoBean bean) {
        LogUitls.e("更新了送货单清点的表体");
        mSendBean.setDetailResults(bean.getDetailResults());
        adapter.notifyDataSetChanged();
    }

    /**
     * 返回receive id
     *
     * @return
     */
    public int getReceiveId() {
        if (intentCode == BUY_ORDE_NUM) {
            return mBuyBean.getSummaryResults().getReceiveId();
        } else {
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
            if (intentCode == BUY_ORDE_NUM) {
                params.put("BillCode", mBuyBean.getSummaryResults().getPoCode());
                params.put("BizType", mBuyBean.getSummaryResults().getBizType());
                params.put("ScanId", mBuyBean.getSummaryResults().getReceiveId());
                getPresenter().getPODetailsByCode(params);
            } else {
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
        if (intentCode == BUY_ORDE_NUM) {
            params.put("ScanId", mBuyBean.getSummaryResults().getReceiveId());
        } else {
            params.put("ScanId", mSendBean.getSummaryResults().getReceiveId());
        }
        params.put("SubmitType", 0);
        getPresenter().commitMaterialPoint(params);
    }
}
