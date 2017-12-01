package com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust_detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.bean.stockin_work.lib_adjust.LibAdjustDetail;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.FloatCircleButtonUpTopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * 库存调整详情
 * author: timi
 * create at: 2017/11/24 16:29
 */
public class LibAdjustDetailActivity extends BaseActivity<LibAdjustDetailView, LibAdjustDetailPresenter> implements LibAdjustDetailView {


    @BindView(R.id.iv_show_more)
    ImageView ivShowMore;
    @BindView(R.id.rl_top_menu)
    RelativeLayout rlTopMenu;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.rlv_un_instock)
    RecyclerView rlvUnInstock;
    @BindView(R.id.fbtn_detail)
    FloatCircleButtonUpTopView fbtnDetail;


    private BaseRecyclerAdapter<LibAdjustDetail> adapter;
    private List<LibAdjustDetail> mDatas = new ArrayList<>();
    private List<LibAdjustDetail> mOldDatas = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_lib_adjust_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.lib_adjust_detail_title));

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        for (int i = 0; i < 20; i++) {
            LibAdjustDetail detail = new LibAdjustDetail(i, "来源库" + i, "目的库" + i, "物料编码-" + i, "物料名称" + i, "物料型号-" + i, "物料附加属性-" + i);
            mDatas.add(detail);
        }
        initAdapter();
    }

    @Override
    public LibAdjustDetailPresenter createPresenter() {
        return new LibAdjustDetailPresenter(this);
    }

    @Override
    public LibAdjustDetailView createView() {
        return this;
    }

    @Override
    public void getDetail(List<LibAdjustDetail> datas) {
        mDatas.clear();
        mOldDatas.clear();
        mOldDatas.addAll(datas);
        dealData();
        initAdapter();
    }

    private void initAdapter() {
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<LibAdjustDetail>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_lib_adjust_detail;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, LibAdjustDetail item) {
                    holder.setTextView(R.id.tv_lib_from, item.formLib);
                    holder.setTextView(R.id.tv_lib_to, item.toLib);
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, item.line);
                    holder.setTextView(R.id.tv_material_code, item.materailCode + item.materialName);
                    holder.setTextView(R.id.tv_material_model, item.materialModel + item.materialAttr);
                }
            };
            rlvUnInstock.setAdapter(adapter);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rlvUnInstock.setLayoutManager(linearLayoutManager);
            rlvUnInstock.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
            rlvUnInstock.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == SCROLL_STATE_IDLE) {//停止滑动
                        /**
                         * 设置点击时间
                         */
                        fbtnDetail.showUpTop(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rlvUnInstock.smoothScrollToPosition(0);
                            }
                        });
                    } else {
                        /**
                         * 设置当前的位置
                         */
                        int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                        fbtnDetail.showCurrentItem(lastCompletelyVisibleItemPosition, mDatas.size());
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    /**
                     * 设置当前的位置
                     */
                    int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    fbtnDetail.showCurrentItem(lastCompletelyVisibleItemPosition, mDatas.size());

                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.iv_show_more)
    public void onViewClicked() {
        ivShowMore.setSelected(!ivShowMore.isSelected());
        dealData();
        adapter.notifyDataSetChanged();
    }

    /**
     * 对数据源做处理，
     */
    private void dealData() {
        mDatas.clear();
        for (int i = 0; i < mOldDatas.size(); i++) {
            if (!ivShowMore.isSelected()) {//如果是选中 则是显示所有的
                if (mOldDatas.get(i).getWaitQty() > 0) {
                    mDatas.add(mOldDatas.get(i));
                }
            } else
                mDatas.add(mOldDatas.get(i));
        }
    }
}
