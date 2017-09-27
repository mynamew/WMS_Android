package com.timi.sz.wms_android.view.excelview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;
import com.timi.sz.wms_android.base.adapter.CommonSimpleTypeAdapter;

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

    private CommonSimpleTypeAdapter<ArrayList<String>> adapter;
    private boolean isLoadMore, isRefresh;//刷新，加载更多的标识

    private final int NORMAL = 0;//普通加载
    private final int REFRESH = 1;//刷新
    private final int LOADMORE = 2;//加载更多

    ArrayList<ArrayList<String>> tabDatas = new ArrayList<>();
    ArrayList<Integer> mAllWidth = new ArrayList<>();

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
     * @param mTabDatas
     * @param mTabNewDatas
     * @param mFirstTabDatas
     * @param adapter
     */
    public void loadData(ArrayList<ArrayList<String>> mTabDatas, ArrayList<ArrayList<String>> mTabNewDatas, ArrayList<String> mFirstTabDatas, CommonSimpleTypeAdapter<ArrayList<String>> adapter) {
        switch (getCurrentStatus()) {
            case REFRESH:
                mTabDatas.clear();
                mTabDatas.add(mFirstTabDatas);
                mTabDatas.addAll(mTabNewDatas);
                this.adapter.notifyDataSetChanged();
                isRefresh = false;
                break;
            case LOADMORE:
                mTabDatas.addAll(mTabNewDatas);
                this.adapter.notifyDataSetChanged();
                isLoadMore = false;
                break;
            case NORMAL:
                this.adapter = adapter;
                rlvExcel.setAdapter(adapter);
                rlvExcel.setLayoutManager(new FixedGridLayoutManager());
                //
                mTabDatas.add(mFirstTabDatas);
                mTabDatas.addAll(mTabNewDatas);
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
     * @param mTabNewDatas
     * @param mFirstTabDatas
     * @return
     */
    public ArrayList<Integer> getAllRowWidth(ArrayList<ArrayList<String>> mTabDatas, ArrayList<ArrayList<String>> mTabNewDatas, ArrayList<String> mFirstTabDatas) {
        tabDatas.addAll(mTabDatas);
        /**
         * 如果 mTabDatas是空
         * 则证明是第一次进入加载数据，
         * 则需要将mFirstDatas加入到mTabDatas中去
         * */
        if (mTabDatas.isEmpty()) {
            tabDatas.add(mFirstTabDatas);
        }
        tabDatas.addAll(mTabNewDatas);
        mAllWidth = new MeasureExcelViewUtils(mContext).getExcelViewWidth(tabDatas).getAllRowWidth();
        return mAllWidth;
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
