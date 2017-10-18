package com.timi.sz.wms_android.mvp.UI.quity.mrp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.quality.mrp.MrpReviewData;
import com.timi.sz.wms_android.mvp.UI.quity.normal_review.MRPNormalReviewActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MRPReviewActivity extends BaseActivity<MRPReviewView, MRPReviewPresenter> implements MRPReviewView {

    @BindView(R.id.rlv_mrp)
    RecyclerView rlvMrp;
    private BaseRecyclerAdapter<MrpReviewData> adapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_mrpreview;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.mrp_title));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        getPresenter().getMRPReviewData(params);
    }

    @Override
    public MRPReviewPresenter createPresenter() {
        return new MRPReviewPresenter(this);
    }

    @Override
    public MRPReviewView createView() {
        return this;
    }

    @Override
    public void getMrpReviewData(final List<MrpReviewData> datas) {
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<MrpReviewData>(this, datas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_mrp;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, MrpReviewData item) {
                    /**
                     * 物品编码
                     */
                    holder.setTextView(R.id.tv_mrp_material_code, item.getMaterialCode());
                    /**
                     * 实收数
                     */
                    holder.setTextView(R.id.tv_mrp_receive_num, item.getReceiveQty());
                    /**
                     * 抽检数
                     */
                    holder.setTextView(R.id.tv_mrp_sample_num, item.getSampleQty());
                    /**
                     * 合格数
                     */
                    holder.setTextView(R.id.tv_mrp_quality_num, item.getPassQty());
                    /**
                     * 不良率
                     */
                    NumberFormat nFromat = NumberFormat.getPercentInstance();
                    String rates = nFromat.format(item.getNgQty() / item.getReceiveQty());
                    holder.setTextView(R.id.tv_mrp_badness_percen, rates);
                    /**
                     * 物品名称
                     */
                    holder.setTextView(R.id.tv_mrp_material_name, item.getMaterialName());
                    /**
                     * 质检日期
                     */
                    setTextViewText(holder.getTextView(R.id.tv_mrp_quality_date), R.string.tv_mrp_quality_date, item.getReceiptDate());
                    /**
                     * 供应商
                     */
                    holder.setTextView(R.id.tv_mrp_supplier, item.getSupplierName());
                    /**
                     * 单号
                     */
                    setTextViewText(holder.getTextView(R.id.tv_mrp_arrive_orderno), R.string.item_arrive_orderno, item.getReceiptCode());
                    /**
                     * 底部的分割线
                     */
                    if (position == datas.size() - 1) {
                        holder.getView(R.id.view_divide_bottom).setVisibility(View.VISIBLE);
                    } else {
                        holder.getView(R.id.view_divide_bottom).setVisibility(View.GONE);

                    }
                }
            };
            rlvMrp.setAdapter(adapter);
            rlvMrp.setLayoutManager(new LinearLayoutManager(this));
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                    Intent intent = new Intent(MRPReviewActivity.this, MRPNormalReviewActivity.class);
                    intent.putExtra("NormalReviewDetail", new Gson().toJson(datas.get(pos)));
                    startActivity(intent);
                }
            });
        }
    }
}
