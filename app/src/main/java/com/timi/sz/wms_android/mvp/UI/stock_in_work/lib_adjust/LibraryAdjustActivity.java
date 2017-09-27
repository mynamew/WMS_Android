package com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 库位调整模块
 * author: timi
 * create at: 2017/9/22 10:25
 */
public class LibraryAdjustActivity extends BaseActivity<LibraryAdjustView, LibraryAdjustPresenter> implements LibraryAdjustView {
    @BindView(R.id.tv_scan_location)
    TextView tvScanLocation;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_code_type)
    TextView tvCodeType;
    @BindView(R.id.tv_goods_code)
    TextView tvGoodsCode;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_model)
    TextView tvGoodsModel;

    @Override
    public int setLayoutId() {
        return R.layout.activity_library_adjust;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public LibraryAdjustPresenter createPresenter() {
        return new LibraryAdjustPresenter(this);
    }

    @Override
    public LibraryAdjustView createView() {
        return this;
    }
    @OnClick({R.id.iv_title_detail, R.id.iv_can_location, R.id.iv_can_material_code, R.id.btn_confirm_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_detail://查看库位调整的详情
                break;
            case R.id.iv_can_location://扫描库位码
                break;
            case R.id.tv_scan_location://扫描库位码
                break;
            case R.id.tv_material_code://扫描物料码
                break;
            case R.id.iv_can_material_code://扫描物料码
                break;
            case R.id.btn_confirm_commit://确认提交
                break;
        }
    }

}
