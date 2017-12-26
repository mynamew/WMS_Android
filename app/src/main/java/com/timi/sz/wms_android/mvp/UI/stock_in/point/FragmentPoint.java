package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
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

import static com.timi.sz.wms_android.base.uils.Constants.BUY_ORDE_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_SOURCE;

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
    @BindView(R.id.header_buy)
    View headerBuy;
    @BindView(R.id.header_send)
    View headerSend;
    @BindView(R.id.tv_buy_spare_num)
    TextView tvBuySpareNum;
    @BindView(R.id.tv_send_spare_num)
    TextView tvSendSpareNum;

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
    private List<BuyOrdernoBean.DetailResultsBean> mDatas;
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
        mDatas = new ArrayList<>();
        //判断code  是送货单还是采购单
        Intent it = ((StockInPointActivity) getActivity()).getIntentCode();
        intentCode = it.getIntExtra(Constants.CODE_STR, Constants.COME_MATERAIL_NUM);
        if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
            mBuyBean = new Gson().fromJson(it.getStringExtra(Constants.IN_STOCK_BUY_BEAN), BuyOrdernoBean.class);
            /**
             * 设置头部
             */
            headerBuy.setVisibility(View.VISIBLE);
            headerSend.setVisibility(View.GONE);
            /**
             * 设置数据源
             */
            mDatas.addAll(mBuyBean.getDetailResults());

        } else {//送货单
            mSendBean = new Gson().fromJson(it.getStringExtra(Constants.IN_STOCK_SEND_BEAN), SendOrdernoBean.class);
            /**
             * 设置头部
             */
            headerBuy.setVisibility(View.GONE);
            headerSend.setVisibility(View.VISIBLE);
            /**
             * 设置数据源
             */
            mDatas.addAll(mSendBean.getDetailResults());
        }
        /**
         * 设置备品状态
         */
        setSpareGoodsStatus(tvBuySpareNum);
        setSpareGoodsStatus(tvSendSpareNum);
        /**
         * 标题的数据
         */
        if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
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
            adapter = new BaseRecyclerAdapter<BuyOrdernoBean.DetailResultsBean>(getActivity(), mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
                        return R.layout.item_point;
                    } else {
                        return R.layout.item_point_send;
                    }
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, BuyOrdernoBean.DetailResultsBean item) {
                    holder.setTextView(R.id.tv_line_num, item.getPoLine());
                    holder.setTextView(R.id.tv_point_num, item.getCountQty());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode());
                    if (intentCode == BUY_ORDE_NUM||intentCode==OUT_SOURCE) {
                        holder.setTextView(R.id.tv_arrive_good_num, item.getArrivalQty());
                        holder.setTextView(R.id.tv_buy_num, item.getPoQty());
                        holder.setTextView(R.id.tv_instock_num, item.getInStockQty());
                    } else {
                        holder.setTextView(R.id.tv_arrive_good_num, item.getDnQty());
                        holder.setTextView(R.id.tv_buy_num, item.getRecvQty());
                        holder.setTextView(R.id.tv_instock_num, item.getInStockQty());
                    }

                    holder.setTextView(R.id.tv_spare_num, item.getGiveQty());
                    holder.setTextView(R.id.tv_material_attr, item.getMaterialName() + (TextUtils.isEmpty(item.getMaterialAttribute()) ? "" : item.getMaterialAttribute()));
                    /**
                     * 设置备品的状态
                     */
                    setSpareGoodsStatus(holder.getTextView(R.id.tv_spare_num));
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
    public void initBundle() {
        BaseMessage.register(this);

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
                             * 是否有备品 如果没有则直接判断 请点数
                             * 否则判断是否备品数和请点数都为0
                             */
                            if (!SpUtils.getInstance().getIsGiveGoods()) {
                                if (pointNum == 0) {
                                    ToastUtils.showShort(getActivity(), getString(R.string.point_num_no_zero));
                                    return;
                                }
                            } else {
                                /**
                                 * 备品数和清点数同时为0 的时候
                                 */
                                if (pointNum == 0 && spareNum == 0) {
                                    ToastUtils.showShort(getActivity(), getString(R.string.point_num_and_spare_num_no_zero));
                                    return;
                                }
                            }

                            int receiveNum = intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE ? mBuyBean.getDetailResults().get(position).getPoQty() : mSendBean.getDetailResults().get(position).getPoQty();
                            if (pointNum > receiveNum) {
                                if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
                                    ToastUtils.showShort(getActivity(), getString(R.string.point_num_no_more_buy_num));
                                } else {
                                    ToastUtils.showShort(getActivity(), getString(R.string.point_count_no_more_send_goods_qty));
                                }
                                return;
                            }
                            Map<String, Object> params = new HashMap<>();
                            params.put("UserId", SpUtils.getInstance().getUserId());
                            params.put("MAC", PackageUtils.getMac());
                            params.put("OrgId", SpUtils.getInstance().getOrgId());
                            params.put("CountQty", pointNum);
                            params.put("GiveQty", spareNum);
                            if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {//采购单
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
            if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {//采购单
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
                    .setTextViewContent(R.id.tv_buy_num, detailResultsBean.getPoQty());
            //采购 送货 不同的设置
            mPointDialog.setTextViewContent(R.id.tv_buy_tip, ((intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) ? getString(R.string.item_buy_num) : getString(R.string.item_send_num)));
            mPointDialog.setTextViewContent(R.id.tv_arrive_tip, ((intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) ? getString(R.string.item_arrive_goods_num) : getString(R.string.item_have_receive_num)));
            mPointDialog.setTextViewContent(R.id.tv_arrive_goods_num, (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) ? detailResultsBean.getArrivalQty() : detailResultsBean.getRecvQty());
            mPointDialog.setTextViewContent(R.id.tv_buy_num, (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) ? detailResultsBean.getPoQty() : detailResultsBean.getDnQty());
        }
        /**
         * 是否有备品 通过PDA 参数获取到
         * 如果无备品 则直接隐藏被备品
         */
        setSpareGoodsStatus(mPointDialog.getView(R.id.ll_spare_num));
        mPointDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mPointDialog = null;
            }
        });
        //设置请点数被选中
        Selection.selectAll(mPointDialog.getEdittext(R.id.et_stockin_point_pro_point_num).getText());
        mPointDialog.show();
    }

    /**
     * 保存物料清点成功
     */
    @Override
    public void savePointMaterial(Integer result) {

        ToastUtils.showShort(getString(R.string.material_point_success));
        /**
         * 采购单 送货单 scanid
         */
        if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
            mBuyBean.getSummaryResults().setReceiveId(result);
        } else {
            mSendBean.getSummaryResults().setReceiveId(result);
        }
        /**
         * 获取表体数据
         */
        requestTableDetail();
    }

    /**
     * 获取表体数据
     */
    private void requestTableDetail() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("MAC", PackageUtils.getMac());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
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

    @Override
    public void saveSendPointMaterial(Integer result) {
        /**
         * 更新清点记录 设置receive id
         */
        StockInPointEvent stockInPointEvent = new StockInPointEvent(StockInPointEvent.MATERIAL_POINT_RECORD_UPDATE);
        stockInPointEvent.receiveId = result;
        BaseMessage.post(stockInPointEvent);

        ToastUtils.showShort(getString(R.string.material_point_success));
        /**
         * 采购单 送货单 scanid
         */
        if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
            mBuyBean.getSummaryResults().setReceiveId(result);
        } else {
            mSendBean.getSummaryResults().setReceiveId(result);
        }
        /**
         * 获取表体数据
         */
        requestTableDetail();
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
        mDatas.clear();
        mDatas.addAll(bean.getDetailResults());
        adapter.notifyDataSetChanged();
        /**
         * 更新清点记录
         */
        StockInPointEvent stockInPointEvent = new StockInPointEvent(StockInPointEvent.MATERIAL_POINT_RECORD_UPDATE);
        if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
            stockInPointEvent.receiveId=mBuyBean.getSummaryResults().getReceiveId();
        } else {
            stockInPointEvent.receiveId=mSendBean.getSummaryResults().getReceiveId();
        }
        BaseMessage.post(stockInPointEvent);

    }

    @Override
    public void getSendPODetailsByCode(SendOrdernoBean bean) {
        LogUitls.e("更新了送货单清点的表体");
        mDatas.clear();
        mDatas.addAll(bean.getDetailResults());
        adapter.notifyDataSetChanged();
        /**
         * 更新清点记录
         */
        StockInPointEvent stockInPointEvent = new StockInPointEvent(StockInPointEvent.MATERIAL_POINT_RECORD_UPDATE);
        if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
            stockInPointEvent.receiveId=mBuyBean.getSummaryResults().getReceiveId();
        } else {
            stockInPointEvent.receiveId=mSendBean.getSummaryResults().getReceiveId();
        }
        BaseMessage.post(stockInPointEvent);
    }

    /**
     * 返回receive id
     *
     * @return
     */
    public int getReceiveId() {
        if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
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
            requestTableDetail();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    @OnClick(R.id.btn_point_commit)
    public void onViewClicked() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("MAC", PackageUtils.getMac());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        if (intentCode == BUY_ORDE_NUM || intentCode == OUT_SOURCE) {
            params.put("ScanId", mBuyBean.getSummaryResults().getReceiveId());
        } else {
            params.put("ScanId", mSendBean.getSummaryResults().getReceiveId());
        }
        params.put("SubmitType", 0);
        getPresenter().commitMaterialPoint(params);
    }
}
