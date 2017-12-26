package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.pack_repertory;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.stockin_work.stock_query.QueryStockContainerResult;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.StockQueryEvent;
import com.timi.sz.wms_android.mvp.base.BaseFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 容器库存的碎片
 * author: timi
 * create at: 2017/11/30 16:35
 */
public class PackRepertoryFragment extends BaseFragment<PackRepertoryView, PackRepertoryPresenter> implements PackRepertoryView {

    @BindView(R.id.tv_material_type)
    TextView tvMaterialType;
    @BindView(R.id.tv_total_num)
    TextView tvTotalNum;
    @BindView(R.id.tv_pack_type)
    TextView tvPackType;
    @BindView(R.id.tv_pack_num)
    TextView tvPackNum;
    @BindView(R.id.rlv_pack_repertory)
    RecyclerView rlvPackRepertory;

    private BaseRecyclerAdapter<QueryStockContainerResult.DetailResultsBean> adapter;
    private List<QueryStockContainerResult.DetailResultsBean> mDatas;

    @Override
    public PackRepertoryPresenter createPresenter() {
        return new PackRepertoryPresenter(getActivity());
    }

    @Override
    public PackRepertoryView createView() {
        return this;
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
    }

    @Override
    public void initBundle() {
        BaseMessage.register(this);
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_pack_repertory;
    }

    @Override
    public void queryStockContainer(QueryStockContainerResult result) {

        QueryStockContainerResult.SummaryResultsBean summaryResults = result.getSummaryResults();
        //设置头部
        setHeaderContent(summaryResults.getMaterialCount(), summaryResults.getTotalCount(), summaryResults.getContainerType(), summaryResults.getContainerNo());
        //清空数据
        mDatas.clear();
        //加入数据
        mDatas.addAll(result.getDetailResults());
        /**
         * 初始化adapter
         */
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<QueryStockContainerResult.DetailResultsBean>(getActivity(), mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_pack_repertory;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, QueryStockContainerResult.DetailResultsBean item) {
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, position + 1);
                    holder.setTextView(R.id.tv_batch_code, item.getLotNo());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode());
                    holder.setTextView(R.id.tv_qty, item.getQty());
                    holder.setTextView(R.id.tv_material_name, item.getMaterialName());
                    holder.setTextView(R.id.tv_material_model, item.getMaterialStandard());

                }
            };
            rlvPackRepertory.setAdapter(adapter);
            rlvPackRepertory.setLayoutManager(new LinearLayoutManager(getActivity()));
            rlvPackRepertory.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setMaterialEdittextSelect() {
        BaseMessage.post(new StockQueryEvent(StockQueryEvent.STOCK_QUERY_EDITTEXT_SELECT));
    }

    /**
     * 设置头部的信息
     *
     * @param materialTypeNum 物项数量
     * @param totalNum        总数量
     * @param containerType   容器类型
     * @param containerCode   容器号
     */
    public void setHeaderContent(int materialTypeNum, int totalNum, String containerType, String containerCode) {
        tvMaterialType.setText(String.valueOf(materialTypeNum));
        tvTotalNum.setText(String.valueOf(totalNum));
        tvPackType.setText(containerType);
        tvPackNum.setText(containerCode);
    }

    /**
     * 拿到 Activity 发送事件  获取输入/扫描的内容
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void queryStockContainer(StockQueryEvent event) {
        if (event.getEvent().equals(StockQueryEvent.STOCK_QUERY_PACK_REPERTORY)) {
            Map<String, Object> params1 = new HashMap<>();
            params1.put("UserId", SpUtils.getInstance().getUserId());
            params1.put("OrgId", SpUtils.getInstance().getOrgId());
            params1.put("MAC", PackageUtils.getMac());
            params1.put("ContainerBarcode", event.inputContent);
            getPresenter().queryStockContainer(params1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }
}
