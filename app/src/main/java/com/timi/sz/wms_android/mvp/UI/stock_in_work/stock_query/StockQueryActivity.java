package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleHeaderAndFooterTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyTabView;
import com.timi.sz.wms_android.view.excelview.DisplayUtil;
import com.timi.sz.wms_android.view.excelview.MyExcelView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库存查询
 */
public class StockQueryActivity extends BaseActivity<StockQueryView, StockQueryPresenter> implements StockQueryView {
    @BindView(R.id.et_stock_query)
    EditText etStockQuery;
    @BindView(R.id.iv_stock_query_del)
    ImageView ivStockQueryDel;
    @BindView(R.id.tv_goods_type)
    TextView tvGoodsType;
    @BindView(R.id.tv_goods_total_num)
    TextView tvGoodsTotalNum;
    @BindView(R.id.mytab_stock_query)
    MyTabView mytabStockQuery;
    @BindView(R.id.myexcel_stock_query)
    MyExcelView myexcelStockQuery;


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
        return R.layout.activity_stock_query;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.query_repertory_tip));
        setTextViewText(tvGoodsType, R.string.goods_type, "0");
        setTextViewText(tvGoodsTotalNum, R.string.goods_total_num, "0");
    }

    @Override
    public void initView() {
        mytabStockQuery.setTabOnclickListener(new MyTabView.TabClickListener() {
            @Override
            public void tab1Click(View view) {
                /**
                 * 设置物品种类是否可见
                 */
                tvGoodsType.setVisibility(View.VISIBLE);
                setTextViewText(tvGoodsType, R.string.goods_type, "0");
                setTextViewText(tvGoodsTotalNum, R.string.goods_total_num, "0");
            }

            @Override
            public void tab2Click(View view) {
                /**
                 * 设置物品种类是否可见
                 */
                tvGoodsType.setVisibility(View.GONE);
                setTextViewText(tvGoodsType, R.string.goods_type, "0");
                setTextViewText(tvGoodsTotalNum, R.string.goods_total_num, "0");
            }
        });
    }

    @Override
    public void initData() {
        /**
         * 设置表头
         */
        mfristData.add(getString(R.string.stock_query_batch));
        mfristData.add(getString(R.string.item_goods_name));
        mfristData.add(getString(R.string.item_goods_code));
        mfristData.add(getString(R.string.item_goods_model));
        mfristData.add(getString(R.string.item_goods_num));
        mfristData.add(getString(R.string.item_goods_current_status));
//        mTableDatas.add(mfristData);
        adapterInit();
    }

    /**
     * 初始化adapter
     */
    private void adapterInit() {
        commonSimpleHeaderTypeAdapter = null;
        /**
         * adapter  为空 则初始化
         */
        final ArrayList<Integer> allRowWidth = myexcelStockQuery.getAllRowWidth(mTableDatas, mfristData);
        commonSimpleHeaderTypeAdapter = new CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>>(mTableDatas) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_stock_query;
            }

            @Override
            public void convert(CommonViewHolder holder, ArrayList<String> strings, int position) {
                /**
                 * 设置第一行的颜色
                 */
                int[] ids = new int[]{R.id.tv_batch, R.id.tv_goods_name, R.id.tv_goods_code, R.id.tv_goods_model, R.id.tv_goods_num, R.id.tv_goods_current_status};
                for (int i = 0; i < ids.length; i++) {
                    TextView textView = holder.getTextView(ids[i]);
                    ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                    layoutParams.width = DisplayUtil.dip2px(StockQueryActivity.this, allRowWidth.get(i));
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
                    layoutParams.width = DisplayUtil.dip2px(StockQueryActivity.this, allRowWidth.get(i));
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
        myexcelStockQuery.loadData(commonSimpleHeaderTypeAdapter,mTableDatas);
    }

    @Override
    public StockQueryPresenter createPresenter() {
        return new StockQueryPresenter(this);
    }

    @Override
    public StockQueryView createView() {
        return this;
    }
    @OnClick(R.id.iv_stock_query_del)
    public void onViewClicked() {
    }
}
