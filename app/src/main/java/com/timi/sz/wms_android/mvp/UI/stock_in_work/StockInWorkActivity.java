package com.timi.sz.wms_android.mvp.UI.stock_in_work;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust.LibraryAdjustActivity;
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
            case R.id.tv_stockin_work_storage_location_change:
                intent.setClass(StockInWorkActivity.this, LibraryAdjustActivity.class);

                break;
            case R.id.tv_stockin_work_scan_in:
                break;
            case R.id.tv_stockin_work_one_step_in:
                break;
            case R.id.tv_stockin_work_group_change:
                break;
            case R.id.tv_stockin_work_out_stock:
                break;
            case R.id.tv_stockin_work_in_stock:
                break;
            case R.id.tv_stockin_work_query_repertory:
                break;
            case R.id.tv_stockin_work_count:
                break;
            case R.id.tv_stock_in_sale_return:
                break;
        }
        startActivity(intent);
    }
}
