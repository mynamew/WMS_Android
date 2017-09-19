package com.timi.sz.wms_android.mvp.UI.stock_out.pick;

import android.os.Bundle;
import android.widget.EditText;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 拣货
 * author: timi
 * create at: 2017/9/18 15:06
 */
public class PickActivity extends BaseActivity<PickView, PickPresenter> implements PickView {
    @BindView(R.id.et_send_order)
    EditText etSendOrder;
    @BindView(R.id.et_sell_order)
    EditText etSellOrder;
    @BindView(R.id.et_allot_roder)
    EditText etAllotRoder;

    @Override
    public int setLayoutId() {
        return R.layout.activity_pick;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("拣货—选单");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public PickPresenter createPresenter() {
        return new PickPresenter(this);
    }

    @Override
    public PickView createView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
