package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.material_repertory;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseFragment;
/** 
  * 物品库存
  * author: timi    
  * create at: 2017/11/30 16:33
  */  
public class MaterialRepertoryFragment extends BaseFragment<MaterialRepertoryView,MaterialRepertoryPresenter> implements MaterialRepertoryView
{

    @Override
    public MaterialRepertoryPresenter createPresenter() {
        return new MaterialRepertoryPresenter(getActivity());
    }

    @Override
    public MaterialRepertoryView createView() {
        return this;
    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_material_repertory;
    }
}
