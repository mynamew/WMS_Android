package com.timi.sz.wms_android.mvp.UI.quity.advance_quality;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleHeaderAndFooterTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.StockQueryActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.excelview.DisplayUtil;
import com.timi.sz.wms_android.view.excelview.MyExcelView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 高级质检
 * author: timi
 * create at: 2017/10/12 13:38
 */
public class AdvanceQualityActivity extends BaseActivity<AdvanceQualityView, AdvanceQualityPresenter> implements AdvanceQualityView {
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_standard_level)
    TextView tvStandardLevel;
    @BindView(R.id.tv_sample_half_yard)
    TextView tvSampleHalfYard;
    @BindView(R.id.tv_check_method)
    TextView tvCheckMethod;
    @BindView(R.id.tv_aql_tip)
    TextView tvAqlTip;
    @BindView(R.id.tv_aql)
    TextView tvAql;
    @BindView(R.id.tv_stringency_tip)
    TextView tvStringencyTip;
    @BindView(R.id.tv_stringency)
    TextView tvStringency;
    @BindView(R.id.tv_recevie_pro_orderno)
    TextView tvRecevieProOrderno;
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_receive_material_date)
    TextView tvReceiveMaterialDate;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_supplier)
    TextView tvSupplier;
    @BindView(R.id.tv_real_receivce_num)
    TextView tvRealReceivceNum;
    @BindView(R.id.tv_sample_count)
    TextView tvSampleCount;
    @BindView(R.id.tv_serious_unqualitfied_tip)
    TextView tvSeriousUnqualitfiedTip;
    @BindView(R.id.tv_serious_unqualitfied)
    TextView tvSeriousUnqualitfied;
    @BindView(R.id.tv_badness_total_num_tip)
    TextView tvBadnessTotalNumTip;
    @BindView(R.id.tv_badness_total_num)
    TextView tvBadnessTotalNum;
    @BindView(R.id.tv_main_unqualitfied_tip)
    TextView tvMainUnqualitfiedTip;
    @BindView(R.id.tv_main_unqualitfied)
    TextView tvMainUnqualitfied;
    @BindView(R.id.tv_badness_percent_tip)
    TextView tvBadnessPercentTip;
    @BindView(R.id.tv_badness_percent)
    TextView tvBadnessPercent;
    @BindView(R.id.tv_monor_unqualitfied_tip)
    TextView tvMonorUnqualitfiedTip;
    @BindView(R.id.tv_monor_unqualitfied)
    TextView tvMonorUnqualitfied;
    @BindView(R.id.tv_sample_num_tip)
    TextView tvSampleNumTip;
    @BindView(R.id.tv_sample_num)
    TextView tvSampleNum;
    @BindView(R.id.tv_check)
    TextView tvCheck;
    @BindView(R.id.myexcel_advance_quality)
    MyExcelView myexcelAdvanceQuality;

    /**
     * 第一行
     */
    ArrayList<String> mfristData = new ArrayList<>();
    ArrayList<ArrayList<String>> mTableDatas = new ArrayList<>();
    /**
     * adapter
     */
    CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>> commonSimpleHeaderTypeAdapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_advance_quality;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        /**
         * 设置表头
         */
//        mfristData.add("样品编号");
//        mfristData.add("长度");
//        mfristData.add("宽度");
//        mfristData.add("温度");
//        mfristData.add("电子性能");
        /**
         * 设置表头
         */
        mfristData.add(getString(R.string.stock_query_batch));
        mfristData.add(getString(R.string.item_goods_name));
        mfristData.add(getString(R.string.item_goods_code));
        mfristData.add(getString(R.string.item_goods_model));
        mfristData.add(getString(R.string.item_goods_num));
        mfristData.add(getString(R.string.item_goods_current_status));
    }

    @Override
    public void initView() {
        myexcelAdvanceQuality.setRefreshListener(new MyExcelView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO: 2017/10/12 刷新的网络请求
                for (int i = 0; i < 10; i++) {
                    mTableDatas.add(mfristData);
                }
                adapterInit();
            }

            @Override
            public void onLoadMore() {
                // TODO: 2017/10/12  加载更多的网络请求
            }
        });
    }

    @Override
    public void initData() {


        adapterInit();
    }

    @Override
    public AdvanceQualityPresenter createPresenter() {
        return new AdvanceQualityPresenter(this);
    }

    @Override
    public AdvanceQualityView createView() {
        return this;
    }

    @OnClick({R.id.tv_next, R.id.tv_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                break;
            case R.id.tv_check:
                break;
        }
    }

    /**
     * 初始化adapter
     */
    private void adapterInit() {
        commonSimpleHeaderTypeAdapter = null;
        /**
         * adapter  为空 则初始化
         */
        final ArrayList<Integer> allRowWidth = myexcelAdvanceQuality.getAllRowWidth(mTableDatas, mfristData);
        commonSimpleHeaderTypeAdapter = new CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>>(mTableDatas) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_stock_query;
            }

            @Override
            public void convert(CommonViewHolder holder, ArrayList<String> strings, int position) {

//                for (int i = 0; i < strings.size(); i++) {
//                    TextView textView = new TextView(AdvanceQualityActivity.this);
//                    textView.setText(strings.get(i));
//                    //设置布局
//                    textView.setPadding(10, 10, 10, 10);
//                    ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
//                    layoutParams.width = DisplayUtil.dip2px(AdvanceQualityActivity.this, allRowWidth.get(i));
//                    textView.setLayoutParams(layoutParams);
//                    View view = new View(AdvanceQualityActivity.this);
//                    ViewGroup.LayoutParams viewLayoutParams = textView.getLayoutParams();
//                    viewLayoutParams.width = DisplayUtil.dip2px(AdvanceQualityActivity.this, 2);
//                    viewLayoutParams.height=DisplayUtil.dip2px(AdvanceQualityActivity.this, 80);
//                    textView.setLayoutParams(layoutParams);
//                    ((LinearLayout) holder.getView(R.id.ll_content)).addView(view);
//
//                }
                /**
                 * 设置第一行的颜色
                 */
                int[] ids = new int[]{R.id.tv_batch, R.id.tv_goods_name, R.id.tv_goods_code, R.id.tv_goods_model, R.id.tv_goods_num, R.id.tv_goods_current_status};
                for (int i = 0; i < ids.length; i++) {
                    TextView textView = holder.getTextView(ids[i]);
                    ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                    layoutParams.width = DisplayUtil.dip2px(AdvanceQualityActivity.this, allRowWidth.get(i));
                    textView.setLayoutParams(layoutParams);
                    textView.setPadding(20, 20, 20, 20);
                    textView.setText(strings.get(i));
                }
                /**
                 * 设置底边分割线
                 */
                if (position == 0) {
                    holder.getView(R.id.divide_bottom).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.divide_bottom).setVisibility(View.GONE);
                }
            }

            @Override
            protected int getHeaderLayoutId() {
                return R.layout.header_stock_query;
            }

            @Override
            protected void bindHeader(CommonViewHolder holder, int position) {

                /**
                 * 设置第一行的颜色
                 */
                int[] ids = new int[]{R.id.tv_batch, R.id.tv_goods_name, R.id.tv_goods_code, R.id.tv_goods_model, R.id.tv_goods_num, R.id.tv_goods_current_status};
                for (int i = 0; i < ids.length; i++) {
                    TextView textView = holder.getTextView(ids[i]);
                    ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                    layoutParams.width = DisplayUtil.dip2px(AdvanceQualityActivity.this, allRowWidth.get(i));
                    textView.setLayoutParams(layoutParams);
                    textView.setPadding(20, 20, 20, 20);
                    textView.setText(mfristData.get(i));
                }
                /**
                 * 设置底边分割线
                 */
                if (position == 0) {
                    holder.getView(R.id.divide_bottom).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.divide_bottom).setVisibility(View.GONE);

                }
                holder.getView(R.id.ll_content).setBackgroundColor(getResources().getColor(R.color.beijin));
            }

        };
        myexcelAdvanceQuality.loadData(commonSimpleHeaderTypeAdapter, mTableDatas);
    }
}
