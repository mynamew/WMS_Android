package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.pack_repertory;

import android.support.v4.app.Fragment;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseFragment;
/** 
  * 容器库存的碎片
  * author: timi    
  * create at: 2017/11/30 16:35
  */  
public class PackRepertoryFragment extends BaseFragment<PackRepertoryView, PackRepertoryPresenter> implements PackRepertoryView {

    @Override
    public PackRepertoryPresenter createPresenter() {
        return new PackRepertoryPresenter(getActivity());
    }

    @Override
    public PackRepertoryView createView() {
        return this;
    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_pack_repertory;
    }
}
