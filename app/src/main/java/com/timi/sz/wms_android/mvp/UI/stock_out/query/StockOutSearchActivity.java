package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_FEEDING;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;

/**
 * 出库搜索界面
 * author: timi
 * create at: 2017/9/4 8:51
 */
public class StockOutSearchActivity extends BaseActivity<StockOutSearchView, StockOutSearchPresenter> implements StockOutSearchView{
    @BindView(R.id.tv_stockout_tip)
    TextView tvStockoutTip;
    @BindView(R.id.et_stockout_input)
    EditText etStockoutInput;
    @BindView(R.id.iv_sotckout_scan)
    ImageView ivSotckoutScan;
    private int intentCode = 5020;// code，码 默认是委外退料单

    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_out_search;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT);
    }

    @Override
    public void initView() {
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                setActivityTitle(getString(R.string.stock_out_outsource_feed_title));
                tvStockoutTip.setText(R.string.stock_out_outsource_feed_tip);
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外 生单
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产 补料
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产 审核
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产 生单
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售 审核
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售 生单
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT:// 其他 审核
                break;
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public StockOutSearchPresenter createPresenter() {
        return new StockOutSearchPresenter(this);
    }

    @Override
    public StockOutSearchView createView() {
        return this;
    }



    @OnClick(R.id.iv_sotckout_scan)
    public void onViewClicked() {
    }
}
