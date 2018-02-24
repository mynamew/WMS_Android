package com.timi.sz.wms_android.mvp.UI.query.count_todayin;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 今日入库统计
 * author: timi
 * create at: 2018/2/1 10:04
 */
public class CountTodayinActivity extends BaseActivity<CountTodayinView, CountTodayinPresenter> implements CountTodayinView {


    @BindView(R.id.bar_char_todayin)
    BarChart barCharTodayin;

    @Override
    public int setLayoutId() {
        return R.layout.activity_count_todayin;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("今日入库统计");

    }

    @Override
    public void initView() {
        //设置条形数据
        barCharTodayin.setData(getBarData());
        barCharTodayin.setDrawBarShadow(false);
//        //隐藏网格线
//        XAxis xAxis = barCharTodayin.getXAxis();
//        xAxis.setDrawAxisLine(true);
//        xAxis.setDrawGridLines(false);
//        barCharTodayin.getAxisLeft().setDrawAxisLine(false);
        //设置描述
        Description description = new Description();
        description.setText("今日入库统计");
        barCharTodayin.setDescription(description);
        barCharTodayin.setDrawGridBackground(false);
        //设置无边框
        barCharTodayin.setDrawBorders(false);
        //设置绘制bar的阴影
        barCharTodayin.setDrawBarShadow(false);
        //设置绘制的动画时间
        barCharTodayin.animateXY(3000, 3000);

    }

    /**
     * 获取柱状图数据
     *
     * @return
     */
    private BarData getBarData() {
        int maxX = 10;
        //创建集合，存放每个柱子的数据
        List<BarEntry> list = new ArrayList<>();
        //一个BarEntry就是一个柱子的数据对象
        BarEntry barEntry = new BarEntry(1, 1);
        list.add(barEntry);
        //创建BarDateSet对象，其实就是一组柱形数据
        BarDataSet barSet = new BarDataSet(list, "今日入库");

        //设置柱形的颜色
        barSet.setColor(getColor(R.color.login_txt_color));
        barSet.setBarShadowColor(getColor(R.color.white));
        //设置是否显示柱子上面的数值
        barSet.setDrawValues(true);
        //柱子上面的数值的文字的大小
        barSet.setValueTextSize(14);
        barSet.setBarBorderColor(getColor(R.color.login_txt_color));
        //创建集合，存放所有组的柱形数据
        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barSet);
        BarData barData = new BarData(dataSets);
        return barData;
    }

    @Override
    public void initData() {

    }

    @Override
    public CountTodayinPresenter createPresenter() {
        return new CountTodayinPresenter(this);
    }

    @Override
    public CountTodayinView createView() {
        return this;
    }

}
