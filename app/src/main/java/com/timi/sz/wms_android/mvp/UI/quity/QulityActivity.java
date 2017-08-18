package com.timi.sz.wms_android.mvp.UI.quity;

import android.os.Bundle;
import android.view.View;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 质量作业
 * author: timi
 * create at: 2017/8/17 14:50
 */
public class QulityActivity extends BaseNoMvpActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_quity;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("质量作业");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.tv_qulity_inspection, R.id.tv_qulity_mrp_inspection, R.id.tv_qulity_update_qr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_qulity_inspection://品质检验
                break;
            case R.id.tv_qulity_mrp_inspection://mrp 评审
                break;
            case R.id.tv_qulity_update_qr://条码修改
                break;
        }
    }
}
