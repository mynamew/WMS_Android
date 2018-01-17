package com.timi.sz.wms_android.mvp.UI.stock_out.detail.return_detial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.stockin_work.ReturnDetailResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.FloatCircleButtonUpTopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;

public class ReturnDetailActivity extends BaseActivity<ReturnDetailView,ReturnDetailPresenter> implements ReturnDetailView {

    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.iv_show_more)
    ImageView ivShowMore;
    @BindView(R.id.rl_top_menu)
    RelativeLayout rlTopMenu;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.rlv_detial)
    RecyclerView rlvDetial;
    @BindView(R.id.fbtn_detail)
    FloatCircleButtonUpTopView fbtnDetail;

    private int billId;

    private BaseRecyclerAdapter<ReturnDetailResult> adapter;

    private List<ReturnDetailResult> mDatas = new ArrayList<>();
    private List<ReturnDetailResult> mOldDatas = new ArrayList<>();
    @Override
    public int setLayoutId() {
        return R.layout.activity_return_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        billId = getIntent().getIntExtra(OUT_STOCK_POINT_DETIAIL_BILLID, -1);
        setActivityTitle(getString(R.string.detail));
        tvTip.setText(R.string.return_material_order_detial);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillId", billId);
        getPresenter().getMakePurReturnScannedDetail(params);
    }

    @Override
    public ReturnDetailPresenter createPresenter() {
        return new ReturnDetailPresenter(this);
    }

    @Override
    public ReturnDetailView createView() {
        return this;
    }

    @Override
    public void getMakePurReturnScannedDetail(List<ReturnDetailResult> o) {
            mDatas.addAll(o);
            initAdapter();
    }
    /**
     * 初始化adapter
     */
    private void initAdapter() {

        if (null == adapter) {
            /**
             * 初始化adapter
             */
            adapter = new BaseRecyclerAdapter<ReturnDetailResult>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_instock_detail;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, ReturnDetailResult item) {
                    holder.getView(R.id.ll_wait_total).setVisibility(View.GONE);
                    holder.getView(R.id.ll_repetory_total).setVisibility(View.GONE);
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, position+1);
                    holder.setTextView(R.id.tv_scan_num, item.getScanQty());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode() + "  " + item.getMaterialName());//合格数？ 应退数
                    holder.setTextView(R.id.tv_material_model, item.getMaterialAttribute() + "  " + item.getMaterialStandard());
                }
            };
            rlvDetial.setAdapter(adapter);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rlvDetial.setLayoutManager(linearLayoutManager);
            rlvDetial.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
            rlvDetial.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                                rlvDetial.smoothScrollToPosition(0);
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
        } else
            adapter.notifyDataSetChanged();
    }
}
