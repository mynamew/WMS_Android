package com.timi.sz.wms_android.mvp.UI.query.todayin;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.query.request.RequestBean;
import com.timi.sz.wms_android.bean.query.response.StockSummeryResult;
import com.timi.sz.wms_android.bean.query.response.TodayInStockDetailResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 今日入库
 */
public class TodayInActivity extends BaseActivity<TodayInView, TodayInPresenter> implements TodayInView {
    @BindView(R.id.ll_today_instock)
    LinearLayout llTodayInstock;
    @BindView(R.id.divider_vertical1)
    View dividerVertical1;
    @BindView(R.id.ll_today_outstock)
    LinearLayout llTodayOutstock;
    @BindView(R.id.divider_horizontal)
    View dividerHorizontal;
    @BindView(R.id.tv_todayin_pen_qty)
    TextView tvTodayinPenQty;
    @BindView(R.id.rl_today_in)
    RelativeLayout rlTodayIn;
    @BindView(R.id.tv_todayin_total_qty)
    TextView tvTodayinTotalQty;
    @BindView(R.id.rl_today_out)
    RelativeLayout rlTodayOut;
    @BindView(R.id.ll_today_num)
    LinearLayout llTodayNum;
    @BindView(R.id.rlc_todayin)
    RecyclerView rlcTodayin;

    private BaseRecyclerAdapter<TodayInStockDetailResult>  adapter;
    private List<TodayInStockDetailResult> mData;
    @Override
    public int setLayoutId() {
        return R.layout.activity_today_in;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("今日入库");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mData=new ArrayList<>();
        RequestBean requestBean = new RequestBean();
        requestBean.setMAC( PackageUtils.getMac());
        requestBean.setOrgId( SpUtils.getInstance().getOrgId());
        requestBean.setUserId( SpUtils.getInstance().getOrgId());
        getPresenter().getTodayInSummeryTotal(requestBean);
    }

    @Override
    public TodayInPresenter createPresenter() {
        return new TodayInPresenter(this);
    }

    @Override
    public TodayInView createView() {
        return this;
    }

    @Override
    public void getTodayInSummeryTotal(StockSummeryResult o) {
        setTextViewContent(tvTodayinTotalQty,o.getInstockCount());
        RequestBean requestBean = new RequestBean();
        requestBean.setMAC( PackageUtils.getMac());
        requestBean.setOrgId( SpUtils.getInstance().getOrgId());
        requestBean.setUserId( SpUtils.getInstance().getOrgId());
        getPresenter().getTodayInSummeryDetail(requestBean);
    }

    @Override
    public void getTodayInSummeryDetail(List<TodayInStockDetailResult> o) {
           if(null!=o){
               mData.addAll(o) ;
           }
           if(null==adapter){
               adapter=new BaseRecyclerAdapter<TodayInStockDetailResult>(this,mData) {
                   @Override
                   protected int getItemLayoutId(int viewType) {
                       return R.layout.item_todayin;
                   }

                   @Override
                   protected void bindData(RecyclerViewHolder holder, int position, TodayInStockDetailResult item) {
                       holder.setTextView(R.id.tv_line_num,"#"+item.getLine());
                       holder.setTextView(R.id.tv_material_code,item.getMatetialCode());
                       holder.setTextView(R.id.tv_material_num,item.getQty());
                       holder.setTextView(R.id.tv_material_name,item.getMaterialName()+" "+item.getMaterialAttribute());
                       holder.setTextView(R.id.tv_supplier,item.getMaterialStandard());
                   }
               };
               rlcTodayin.setAdapter(adapter);
               rlcTodayin.setLayoutManager(new LinearLayoutManager(this));
           }else adapter.notifyDataSetChanged();
    }

}
