package com.timi.sz.wms_android.mvp.UI.stock_in_work;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust.LibraryAdjustActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.query.StockInWorkQueryActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.StockQueryActivity;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库内作业
 * author: timi
 * create at: 2017/8/17 14:48
 */
public class StockInWorkActivity extends BaseNoMvpActivity {


    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in_work;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.home_lib_in_deal));

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_stockin_work_storage_location_change, R.id.tv_stockin_work_scan_in, R.id.tv_stockin_work_one_step_in, R.id.tv_stockin_work_group_change, R.id.tv_stockin_work_out_stock, R.id.tv_stockin_work_in_stock, R.id.tv_stockin_work_query_repertory, R.id.tv_stockin_work_count, R.id.tv_stock_in_sale_return})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_stockin_work_storage_location_change://库位调整
                intent.setClass(StockInWorkActivity.this, LibraryAdjustActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_LIBRARY_ADJUST);
                break;
            case R.id.tv_stockin_work_scan_in://扫描调入
                intent.setClass(StockInWorkActivity.this, StockInWorkQueryActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_ALLOT_SCAN);
                break;
            case R.id.tv_stockin_work_one_step_in://一步调入
                intent.setClass(StockInWorkActivity.this, StockInWorkQueryActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_ALLOT_ONE_STEP);

                break;
            case R.id.tv_stockin_work_group_change://容器调整
                intent.setClass(StockInWorkActivity.this, LibraryAdjustActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_PACK_ADJUST);
                break;
            case R.id.tv_stockin_work_out_stock://形态转换 出库
                intent.setClass(StockInWorkActivity.this, StockInWorkQueryActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_FORM_CHANGE_OUT);
                break;
            case R.id.tv_stockin_work_in_stock://形态转换入库
                intent.setClass(StockInWorkActivity.this, StockInWorkQueryActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_FORM_CHANGE_IN);
                break;
            case R.id.tv_stockin_work_query_repertory://库存查询
                intent.setClass(StockInWorkActivity.this, StockQueryActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_STOCK_QUERY);
                break;
            case R.id.tv_stockin_work_count://盘点
                intent.setClass(StockInWorkActivity.this, StockInWorkQueryActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_POINT);//盘点
                break;
            case R.id.tv_stock_in_sale_return://
                break;
        }
        startActivity(intent);
    }
}
