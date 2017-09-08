package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
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
    private FragmentPoint mPointFragment;
    private FragmentPointRecord mPointRecordFragment;
    private int intentCode;
    //采购单 实体
    private BuyOrdernoBean mBuyBean;
    //发货单实体
    private SendOrdernoBean mSendBean;
    //receive Id
    public int receiveId=0;
    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in_point;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("收货-物品清点");
        intentCode = getIntent().getIntExtra(Constants.CODE_STR, Constants.BUY_ORDE_NUM);
        if (intentCode == Constants.BUY_ORDE_NUM)
            mBuyBean=getIntent().getParcelableExtra(Constants.IN_STOCK_BUY_BEAN);
        else
            mSendBean=getIntent().getParcelableExtra(Constants.IN_STOCK_SEND_BEAN);
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
        //初始化 加载fragment
        changeFragment(0);
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
            /**
             * 获取 清点界面的receive Ids
             */
            receiveId=mPointFragment.getReceiveId();


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

    /**
     * 返回当前的Intent
     * @return
     */
    public Intent getIntentCode(){
        return getIntent();
    }
    /**
     * 返回接受的receiveId
     * @return
     */
    public int getReceiveId(){
        return receiveId;
    }
}
