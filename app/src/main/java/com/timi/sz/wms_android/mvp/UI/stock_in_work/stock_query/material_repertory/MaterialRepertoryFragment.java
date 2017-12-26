package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.material_repertory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Selection;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.stockin_work.stock_query.MaterialQueryResult;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.StockQueryEvent;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.StockQueryActivity;
import com.timi.sz.wms_android.mvp.base.BaseFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 物品库存
 * author: timi
 * create at: 2017/11/30 16:33
 */
public class MaterialRepertoryFragment extends BaseFragment<MaterialRepertoryView, MaterialRepertoryPresenter> implements MaterialRepertoryView {

    @BindView(R.id.tv_repertort_total_num)
    TextView tvRepertortTotalNum;
    @BindView(R.id.tv_putaway_material_code)
    TextView tvPutawayMaterialCode;
    @BindView(R.id.tv_putaway_material_name)
    TextView tvPutawayMaterialName;
    @BindView(R.id.tv_putaway_material_nmodel)
    TextView tvPutawayMaterialNmodel;
    @BindView(R.id.tv_putaway_material_attr)
    TextView tvPutawayMaterialAttr;
    @BindView(R.id.rlv_material_repertory)
    RecyclerView rlvMaterialRepertory;

    private BaseRecyclerAdapter<MaterialQueryResult.DetailResultsBean> adapter;
    private List<MaterialQueryResult.DetailResultsBean> mDatas;

    @Override
    public MaterialRepertoryPresenter createPresenter() {
        return new MaterialRepertoryPresenter(getActivity());
    }

    @Override
    public MaterialRepertoryView createView() {
        return this;
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        if (!TextUtils.isEmpty(((StockQueryActivity) getActivity()).getEdittextContent())) {
            Map<String, Object> params1 = new HashMap<>();
            params1.put("UserId", SpUtils.getInstance().getUserId());
            params1.put("OrgId", SpUtils.getInstance().getOrgId());
            params1.put("MAC", PackageUtils.getMac());
            params1.put("MaterialBarcode", ((StockQueryActivity) getActivity()).getEdittextContent());
            getPresenter().queryStockMaterial(params1);
        }
    }

    @Override
    public void initBundle() {
        BaseMessage.register(this);
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_material_repertory;
    }

    /**
     * 拿到 Activity 发送事件  获取输入/扫描的内容
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void queryStockContainer(StockQueryEvent event) {
        if (event.getEvent().equals(StockQueryEvent.STOCK_QUERY_MATERIAL_REPERTORY)) {

            Map<String, Object> params1 = new HashMap<>();
            params1.put("UserId", SpUtils.getInstance().getUserId());
            params1.put("OrgId", SpUtils.getInstance().getOrgId());
            params1.put("MAC", PackageUtils.getMac());
            params1.put("MaterialBarcode", event.inputContent);
            getPresenter().queryStockMaterial(params1);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    @Override
    public void queryStockMaterial(MaterialQueryResult result) {
        MaterialQueryResult.SummaryResultsBean summaryResults = result.getSummaryResults();
        tvRepertortTotalNum.setText(String.valueOf(summaryResults.getTotalQty()));
        tvPutawayMaterialAttr.setText(TextUtils.isEmpty(summaryResults.getMaterialAttribute()) ? getString(R.string.none) : summaryResults.getMaterialAttribute());
        tvPutawayMaterialName.setText(TextUtils.isEmpty(summaryResults.getMaterialName()) ? getString(R.string.none) : summaryResults.getMaterialName());
        tvPutawayMaterialCode.setText(TextUtils.isEmpty(summaryResults.getMaterialCode()) ? getString(R.string.none) : summaryResults.getMaterialCode());
        tvPutawayMaterialNmodel.setText(TextUtils.isEmpty(summaryResults.getMaterialStandard()) ? getString(R.string.none) : summaryResults.getMaterialStandard());
        //数据源
        mDatas.clear();
        mDatas.addAll(result.getDetailResults());
        //初始化adapter
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<MaterialQueryResult.DetailResultsBean>(getActivity(), mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_material_repertory;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, MaterialQueryResult.DetailResultsBean item) {
                    holder.setTextView(R.id.tv_batch, item.getLotNo());
                    holder.setTextView(R.id.tv_location_code, item.getLocationNo());
                    holder.setTextView(R.id.tv_material_num, item.getQty());
                }
            };
            rlvMaterialRepertory.setAdapter(adapter);
            rlvMaterialRepertory.setLayoutManager(new LinearLayoutManager(getActivity()));
            rlvMaterialRepertory.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setMaterialEdittextSelect() {
        BaseMessage.post(new StockQueryEvent(StockQueryEvent.STOCK_QUERY_EDITTEXT_SELECT));
    }
}
