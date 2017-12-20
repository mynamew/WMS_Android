package com.timi.sz.wms_android.mvp.UI.stock_out;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.mvp.UI.list.BuyInStockListActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.StockInActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.BuyReturnMaterialActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.other.scan.OtherOutStockScanActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.pick.PickActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.query.StockOutSearchActivity;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_FINISH_GOODS_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_APPLY_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_FEEDING;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PURCHASE_MATERIAL_RETURN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SALE_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SEND_OUT_PICK;

/**
 * 出库作业
 * author: timi
 * create at: 2017/8/17 14:42
 */
public class StockOutActivity extends BaseNoMvpActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_out;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.home_out_lib));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    /**
     * 点击事件
     * author: timi
     * create at: 2017/8/18 13:48
     */
    @OnClick({R.id.tv_stock_out_out_add_materail,
            R.id.tv_stock_out_apply_bill,
            R.id.tv_stock_out_out_check,
            R.id.tv_stock_out_out_create_order,
            R.id.tv_stock_out_out_allot,
            R.id.tv_stock_out_create_add_materail,
            R.id.tv_stock_out_create_allot,
            R.id.tv_stock_out_create_check,
            R.id.tv_stock_out_create_create_order,
            R.id.tv_stock_out_sale_trans,
            R.id.tv_stock_out_sale_check,
            R.id.tv_stock_out_sale_create_order,
            R.id.tv_stock_out_other_buy_return,
            R.id.tv_stock_out_other_check,
            R.id.tv_stock_out_other_create_order,
            R.id.activity_stock_out})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_stock_out_out_add_materail:// 委外补料

                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT);
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                break;
            case R.id.tv_stock_out_out_check://委外审核
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_AUDIT);
                break;
            case R.id.tv_stock_out_out_allot://委外调拨
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_ALLOT);
                break;
            case R.id.tv_stock_out_out_create_order://委外生单
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_BILL);
                break;
            case R.id.tv_stock_out_create_add_materail://生产 补料
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_PRODUCTION_FEEDING);
                break;
            case R.id.tv_stock_out_create_check://生产审核
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_PRODUCTION_AUDIT);
                break;
            case R.id.tv_stock_out_create_create_order://生产生单
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_PRODUCTION_BILL);
                break;
            case R.id.tv_stock_out_create_allot://生产调拨
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_PRODUCTION_ALLOT);
                break;
            case R.id.tv_stock_out_pick_send://发货拣货
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_SEND_OUT_PICK);
                break;
            case R.id.tv_stock_out_pick_sale://销售拣货
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_SALE_OUT_PICK);
                break;
            case R.id.tv_stock_out_sale_trans://调拨拣货
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_PRODUCTION_ALLOT);
                break;
            case R.id.tv_stock_out_sale_check://销售审核
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_SELL_OUT_AUDIT);
                break;
            case R.id.tv_stock_out_sale_create_order://销售生单
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_SELL_OUT_BILL);
                break;
            case R.id.tv_stock_out_other_buy_return://采购退料
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(this, BuyReturnMaterialActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_PURCHASE_MATERIAL_RETURN);
                break;
            case R.id.tv_stock_out_other_check://其他审核
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OTHER_OUT_AUDIT);
                break;
            case R.id.tv_stock_out_other_create_order://其他生单
                intent.setClass(StockOutActivity.this, OtherOutStockScanActivity.class);
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OTHER_OUT_BILL);
                break;
            case R.id.tv_stock_out_apply_bill://申请生单
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_PRODUCTION_APPLY_BILL);
                break;
//            case R.id.tv_stock_out_finish_goods_pick://产成品 拣货
//                /**
//                 * 如果是无纸化作业
//                 */
//                if (SpUtils.getInstance().getIsBillList()) {
//                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
//                } else {
//                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
//                }
//                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_FINISH_GOODS_PICK);
//                break;
            case R.id.activity_stock_out:
                break;
        }
        startActivity(intent);
    }
}
