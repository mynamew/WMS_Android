package com.timi.sz.wms_android.mvp.UI.stock_out.other.scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.mvp.UI.stock_out.detail.DetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.normal_out_stock.NormalOutStockActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_REGIONID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_WAREHOUSEID;

/**
 * 其他出库  生单
 * author: timi
 * create at: 2017/11/29 11:14
 */
public class OtherOutStockScanActivity extends BaseActivity<OtherOutStockScanView, OtherOutStockScanPresenter> implements OtherOutStockScanView {
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.tv_material_tip)
    TextView tvMaterialTip;
    @BindView(R.id.tv_material_scan)
    TextView tvMaterialScan;
    @BindView(R.id.iv_scan_return_material_scan)
    ImageView ivScanReturnMaterialScan;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_num)
    TextView tvMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_nmodel)
    TextView tvMaterialNmodel;
    @BindView(R.id.btn_create_other_out_stock_orderno)
    Button btnCreateOtherOutStockOrderno;
    private int intentCode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_other_out_stock_scan;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.other_out_stock_scan_title));
        intentCode = getIntent().getIntExtra(Constants.STOCK_OUT_CODE_STR, 0);
    }

    @Override
    public void initView() {
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(OtherOutStockScanActivity.this, DetailActivity.class);

//                /**
//                 * 查看详情
//                 */
//                it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
//                it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
//                it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                it.putExtra(Constants.STOCK_OUT_CODE_STR, intentCode);
                startActivity(it);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public OtherOutStockScanPresenter createPresenter() {
        return new OtherOutStockScanPresenter(this);
    }

    @Override
    public OtherOutStockScanView createView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
