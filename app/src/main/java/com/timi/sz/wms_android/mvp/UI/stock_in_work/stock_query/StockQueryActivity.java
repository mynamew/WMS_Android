package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleHeaderAndFooterTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.mvp.UI.stock_in.point.FragmentPoint;
import com.timi.sz.wms_android.mvp.UI.stock_in.point.FragmentPointRecord;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.material_repertory.MaterialRepertoryFragment;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.pack_repertory.PackRepertoryFragment;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyTabView;
import com.timi.sz.wms_android.view.excelview.DisplayUtil;
import com.timi.sz.wms_android.view.excelview.MyExcelView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库存查询
 */
public class StockQueryActivity extends BaseActivity<StockQueryView, StockQueryPresenter> implements StockQueryView {
    @BindView(R.id.et_stock_query)
    EditText etStockQuery;
    @BindView(R.id.mytab_stock_query)
    MyTabView mytabStockQuery;

    private PackRepertoryFragment mPackFragment;
    private MaterialRepertoryFragment mMaterialFragment;
    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_query;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.query_repertory_tip));
    }

    @Override
    public void initView() {
        mytabStockQuery.setTabOnclickListener(new MyTabView.TabClickListener() {
            @Override
            public void tab1Click(View view) {
                /**
                 * 设置物品种类是否可见
                 */
                changeFragment(0);
            }

            @Override
            public void tab2Click(View view) {
                /**
                 * 设置物品种类是否可见
                 */
                changeFragment(1);
            }
        });
        /**
         * 初始化
         */
        changeFragment(0);
    }

    @Override
    public void initData() {

    }


    @Override
    public StockQueryPresenter createPresenter() {
        return new StockQueryPresenter(this);
    }

    @Override
    public StockQueryView createView() {
        return this;
    }

    /**
     * 切换清点和清点记录的碎片
     * @param index
     */
    public void changeFragment(int index){
        FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
        if (mPackFragment != null) trans.hide(mPackFragment);
        if (mMaterialFragment != null) trans.hide(mMaterialFragment);
        if(index==0){
            if (mPackFragment == null) {
                mPackFragment = new PackRepertoryFragment();
                trans.add(R.id.fl_content, mPackFragment);
            } else {
                trans.show(mPackFragment);
            }
        }else{
            if (mMaterialFragment == null) {
                mMaterialFragment = new MaterialRepertoryFragment();
                trans.add(R.id.fl_content, mMaterialFragment);
            } else {
                trans.show(mMaterialFragment);
            }
        }
        try {
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
