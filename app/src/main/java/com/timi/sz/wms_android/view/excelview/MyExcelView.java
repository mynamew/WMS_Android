package com.timi.sz.wms_android.view.excelview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;
import com.timi.sz.wms_android.base.adapter.CommonSimpleHeaderAndFooterTypeAdapter;
import com.timi.sz.wms_android.base.uils.LogUitls;

import java.util.ArrayList;

/**
 * $dsc  自定义的表格（对刷新 recycleview 进行封装）
 * author: timi
 * create at: 2017-09-25 15:10
 */

public class MyExcelView extends PullToRefreshRecyclerView {
    private Context mContext;
    private PullToRefreshRecyclerView refreshExcel;
    private RecyclerView rlvExcel;

    private CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>> adapter;
    private boolean isLoadMore, isRefresh;//刷新，加载更多的标识

    private final int NORMAL = 0;//普通加载
    private final int REFRESH = 1;//刷新
    private final int LOADMORE = 2;//加载更多


    public MyExcelView(Context context) {
        super(context);
        initView(context);
    }

    public MyExcelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        rlvExcel = this.getRefreshableView();
        rlvExcel.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    /**
     * 设置刷新的监听器
     *
     * @param refreshListener
     */
    public void setRefreshListener(final OnRefreshListener refreshListener) {
        if (null != refreshListener) {
            this.setRefreshing(true);
            this.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                    isRefresh = true;
                    refreshListener.onRefresh();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                    isLoadMore = true;
                    refreshListener.onLoadMore();
                }
            });
        }
    }

    /**
     * 显示加载中 刷新
     */
    public void showRefreshing() {
        this.setRefreshing();
    }

    /**
     * 停止刷新
     * 1、需要将 加载状态complete
     * 2、将 刷新和加载更多的标识 置为false
     * 3、需要在上拉刷新 和加载更多 数据回调方法之后执行，否则会影响上啦加载和下拉刷新的逻辑
     */
    public void finishRefresh() {
        this.onRefreshComplete();
        isLoadMore = false;
        isRefresh = false;
    }

    /**
     * 刷新 加载更多的监听器
     */
    public interface OnRefreshListener {
        void onRefresh();

        void onLoadMore();
    }

    /**
     * 加载数据的方法
     *
     * @param adapter
     */
    public void loadData(CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>> adapter) {
        switch (getCurrentStatus()) {
            case REFRESH:
                this.adapter.notifyDataSetChanged();
                break;
            case LOADMORE:
                this.adapter.notifyDataSetChanged();
                break;
            case NORMAL:
                if (null == this.adapter) {
                    this.adapter = adapter;
                    rlvExcel.setAdapter(adapter);
                    rlvExcel.setLayoutManager(new FixedGridLayoutManager());
                }
                this.adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    /**
     * 获取 tab 宽度的方法
     *
     * @param mTabDatas
     * @param mFirstTabDatas
     * @return
     */
    public ArrayList<Integer> getAllRowWidth(ArrayList<ArrayList<String>> mTabDatas, ArrayList<String> mFirstTabDatas) {
        /**
         * 对 数据进行处理 拿出来每列的文本的最大值
         */
        /**
         * 最大的宽度的集合
         */
        ArrayList<Integer> allRowWidth = new ArrayList<>();
        /**
         * 所有的数据
         */
        ArrayList<ArrayList<String>> measureTabDatas = new ArrayList<>();
        /**
         * 将数据重新整理，每列作为一个 arraylist，
         */
        for (int i = 0; i < mFirstTabDatas.size(); i++) {
            ArrayList<String> tab = new ArrayList<>();
            tab.add(mFirstTabDatas.get(i));
            /**
             * 获取每行的第i列的数据
             */
            for (int j = 0; j < mTabDatas.size(); j++) {
                ArrayList<String> strings = mTabDatas.get(j);
                LogUitls.e("每行的数据-->", strings);
                tab.add(null == strings.get(i) ? "" : strings.get(i));
            }
            /**
             * 添加 每列的数据
             */
            measureTabDatas.add(tab);
        }
        /**
         * 获取每列的文本的最大宽度
         */
        for (int i = 0; i < mFirstTabDatas.size(); i++) {
            int maxWidth = 0;
            for (int j = 0; j < measureTabDatas.get(i).size(); j++) {

                /**
                 * 设置文本 获取文本框的宽度
                 */
                TextView textView = new TextView(mContext);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                //每一个文本
                String text = measureTabDatas.get(i).get(j);
                textView.setText(text);
                textView.setGravity(Gravity.CENTER);
                //设置布局
                LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setPadding(10, 10, 10, 10);
                textView.setLayoutParams(textViewParams);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                int width = DisplayUtil.px2dip(mContext, layoutParams.leftMargin) +
                        DisplayUtil.px2dip(mContext, layoutParams.rightMargin) + textView.getPaddingLeft() + textView.getPaddingRight() +
                        getTextViewWidth(textView, text);
                /**
                 * 存储 文本框的宽度
                 */
                if (width > maxWidth) {
                    maxWidth = width;
                }

            }
            allRowWidth.add(maxWidth);
        }
        LogUitls.e("文本的宽度---->", allRowWidth);
        return allRowWidth;
    }

    /**
     * 根据文字计算textview的高度
     *
     * @param view
     * @param text
     * @return
     */
    private int getTextViewWidth(TextView view, String text) {
        if (view != null) {
            TextPaint paint = view.getPaint();
            return DisplayUtil.px2dip(mContext, (int) paint.measureText(text));
        }
        return 0;
    }

    /**
     * 获取当前状态的方法
     *
     * @return
     */
    private int getCurrentStatus() {
        if (isLoadMore) {
            return LOADMORE;
        }
        if (isRefresh) {
            return REFRESH;
        }
        return NORMAL;
    }

    /**
     * 是否是刷新
     *
     * @return
     */
    public boolean isExcelRefresh() {
        return isRefresh;
    }
}
