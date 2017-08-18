package com.timi.sz.wms_android.mvp.UI.stock_in_work;

import android.os.Bundle;
import android.view.View;

import com.timi.sz.wms_android.R;
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

    /**
     * 点击事件
     * author: timi
     * create at: 2017/8/18 16:50
     */
    @OnClick({R.id.tv_siw_storage_location_change, R.id.tv_siw_scan_in, R.id.tv_siw_one_step_in, R.id.tv_siw_group_change, R.id.tv_siw_form_change_stock_out, R.id.tv_siw_form_change_stock_in, R.id.tv_siw_query_repertory, R.id.tv_siw_check, R.id.tv_stock_in_create_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_siw_storage_location_change:
                break;
            case R.id.tv_siw_scan_in:
                break;
            case R.id.tv_siw_one_step_in:
                break;
            case R.id.tv_siw_group_change:
                break;
            case R.id.tv_siw_form_change_stock_out:
                break;
            case R.id.tv_siw_form_change_stock_in:
                break;
            case R.id.tv_siw_query_repertory:
                break;
            case R.id.tv_siw_check:
                break;
            case R.id.tv_stock_in_create_order:
                break;
        }
    }
}
