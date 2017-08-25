package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;

/**
 * 入库清点
 * author: timi
 * create at: 2017/8/18 20:58
 */
public class StockInPointActivity extends BaseActivity<StockInPointView, StockInPointPresenter> implements StockInPointView {
    @BindView(R.id.tab_stockin_point)
    TabLayout tabStockinPoint;
    private String[] titles;
    private Fragment mPointFragment;
    private Fragment mPointRecordFragment;
    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in_point;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("收货-物品清点");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        titles = new String[]{getString(R.string.goods_point), getString(R.string.point_record)};
        tabStockinPoint.addTab(tabStockinPoint.newTab().setText(titles[0]));
        tabStockinPoint.addTab(tabStockinPoint.newTab().setText(titles[1]));
        tabStockinPoint.setTabMode(TabLayout.MODE_FIXED);//设置标签的模式,默认系统模式
        tabStockinPoint.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ToastUtils.showShort(StockInPointActivity.this, "点击了" + tab.getText());
                if (tab.getText().equals(titles[0])) {//物品清点
                    changeFragment(0);
                } else {//清点记录
                    changeFragment(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public StockInPointPresenter createPresenter() {
        return new StockInPointPresenter(this);
    }

    @Override
    public StockInPointView createView() {
        return this;
    }

    /**
     * 切换清点和清点记录的碎片
      * @param index
     */
    public void changeFragment(int index){
        FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
        if (mPointFragment != null) trans.hide(mPointFragment);
        if (mPointRecordFragment != null) trans.hide(mPointRecordFragment);
        if(index==0){
            if (mPointFragment == null) {
                mPointFragment = new FragmentPoint();
                trans.add(R.id.fl_stockin_point_content, mPointFragment);
            } else {
                trans.show(mPointFragment);
            }
        }else{
            if (mPointRecordFragment == null) {
                mPointRecordFragment = new FragmentPointRecord();
                trans.add(R.id.fl_stockin_point_content, mPointRecordFragment);
            } else {
                trans.show(mPointRecordFragment);
            }
        }
        try {
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
