package com.timi.sz.wms_android.mvp.UI.quity.reject;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleHeaderAndFooterTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.excelview.MyExcelView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 质检拒收
 */
public class QualityRejectActivity extends BaseActivity<QualityRejectView, QualityRejectPresenter> implements QualityRejectView {
    @BindView(R.id.et_min_pack_code)
    EditText etMinPackCode;
    @BindView(R.id.myexcel_quality_reject)
    MyExcelView myexcelQualityReject;
    @BindView(R.id.refresh_quality_reject)
    PullToRefreshRecyclerView refreshQualityReject;


    /**
     * 第一行
     */
    ArrayList<String> mfristData = new ArrayList<>();
    ArrayList<ArrayList<String>> mTableDatas = new ArrayList<>();
    /**
     * adapter
     */
    CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>> commonSimpleHeaderTypeAdapter;
    private RecyclerView refreshableView;

    @Override
    public int setLayoutId() {
        return R.layout.activity_quality_reject;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("质检拒收");
        refreshableView = refreshQualityReject.getRefreshableView();
        /**
         * 刷新的监听方法
         */
        refreshQualityReject.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

            }
        });
        /**
         * 设置表头
         */
        mfristData.add("条码");
        mfristData.add("初始包装数");
        mfristData.add("实际包装数");
        mfristData.add("拒收数");
        for (int i = 0; i < 10; i++) {
            mTableDatas.add(mfristData);
        }
        adapterInit();

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public QualityRejectPresenter createPresenter() {
        return new QualityRejectPresenter(this);
    }

    @Override
    public QualityRejectView createView() {
        return this;
    }

    @OnClick(R.id.tv_quality_complete)
    public void onViewClicked() {
    }

    /**
     * 初始化adapter
     */
    private void adapterInit() {
        commonSimpleHeaderTypeAdapter = null;
        /**
         * adapter  为空 则初始化
         */
        final ArrayList<Integer> allRowWidth = myexcelQualityReject.getAllRowWidth(mTableDatas, mfristData);
        commonSimpleHeaderTypeAdapter = new CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>>(mTableDatas) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_quality_reject;
            }

            @Override
            public void convert(CommonViewHolder holder, ArrayList<String> strings, int position) {
                /**
                 * 设置第一行的颜色
                 */
                int[] ids = new int[]{R.id.tv_material_code, R.id.tv_first_pack_num, R.id.tv_real_pack_num, R.id.tv_reject_num};
                for (int i = 0; i < ids.length; i++) {
                    TextView textView = holder.getTextView(ids[i]);
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
                return R.layout.item_quality_reject;
            }

            @Override
            protected void bindHeader(CommonViewHolder holder, int position) {
                /**
                 * 设置第一行的颜色
                 */
                int[] ids = new int[]{R.id.tv_material_code, R.id.tv_first_pack_num, R.id.tv_real_pack_num, R.id.tv_reject_num};
                for (int i = 0; i < ids.length; i++) {
                    TextView textView = holder.getTextView(ids[i]);
                    textView.setTextColor(getResources().getColor(R.color.white));
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
        refreshableView.setAdapter(commonSimpleHeaderTypeAdapter);
        refreshableView.setLayoutManager(new LinearLayoutManager(this));
    }

}
