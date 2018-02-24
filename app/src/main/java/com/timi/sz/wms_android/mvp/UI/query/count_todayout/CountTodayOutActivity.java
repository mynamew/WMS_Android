package com.timi.sz.wms_android.mvp.UI.query.count_todayout;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
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
 * 今日出库 统计
 * author: timi
 * create at: 2018/2/1 16:09
 */
public class CountTodayOutActivity extends BaseActivity<CountTodayOutView, CountTodayOutPresenter> implements CountTodayOutView {


    @BindView(R.id.bar_char_todayout)
    BarChart barCharTodayout;

    @Override
    public int setLayoutId() {
        return R.layout.activity_count_today_out;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("今日出库统计");
    }

    @Override
    public void initView() {
        //设置条形数据
        barCharTodayout.setData(getBarData());
        barCharTodayout.setDrawBarShadow(false);
        //隐藏网格线
        XAxis xAxis = barCharTodayout.getXAxis();
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        barCharTodayout.getAxisLeft().setDrawAxisLine(false);
        //设置描述
        Description description=new Description();
        description.setText("今日出库统计");
        barCharTodayout.setDescription(description);
        //设置绘制bar的阴影
        barCharTodayout.setDrawBarShadow(true);
        //设置绘制的动画时间
        barCharTodayout.animateXY(3000, 3000);
    }
    /**
     * 获取柱状图数据
     * @return
     */
    private BarData getBarData() {
        int maxX = 10;
        //创建集合，存放每个柱子的数据
        List<BarEntry> list = new ArrayList<>();
        for (int i=0;i<maxX;i++){
            //一个BarEntry就是一个柱子的数据对象
            BarEntry barEntry = new BarEntry(i+5,i);
            list.add(barEntry);
        }
        //创建BarDateSet对象，其实就是一组柱形数据
        BarDataSet barSet = new BarDataSet(list,"今日出库");
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
    public CountTodayOutPresenter createPresenter() {
        return new CountTodayOutPresenter(this);
    }

    @Override
    public CountTodayOutView createView() {
        return this;
    }
}
