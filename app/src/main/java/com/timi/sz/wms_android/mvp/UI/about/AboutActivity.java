package com.timi.sz.wms_android.mvp.UI.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

public class AboutActivity extends BaseNoMvpActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("关于");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
