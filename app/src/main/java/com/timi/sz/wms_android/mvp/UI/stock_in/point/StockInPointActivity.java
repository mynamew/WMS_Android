package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.timi.sz.wms_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 入库清点
 * author: timi
 * create at: 2017/8/18 20:58
 */
public class StockInPointActivity extends AppCompatActivity {

    @BindView(R.id.rd_stock_in_point)
    RadioButton rdStockInPoint;
    @BindView(R.id.rd_stock_in_record)
    RadioButton rdStockInRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_in_point);
        ButterKnife.bind(this);
        rdStockInPoint.setChecked(true);
    }

    @OnClick({R.id.rd_stock_in_point, R.id.rd_stock_in_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rd_stock_in_point://物品清点
                break;
            case R.id.rd_stock_in_record://清点记录
                break;
        }
    }
}
