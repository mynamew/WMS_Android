package com.timi.sz.wms_android.mvp.UI.stock_in;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.mvp.UI.list.BuyInStockListActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.other_scan.OtherScanActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.query.SearchBuyOrderActivity;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.PERMISSION_STOCKIN;

/**
 * 入库作业的界面
 * author: timi
 * create at: 2017/8/17 10:11
 */
public class StockInActivity extends BaseNoMvpActivity {

    @BindView(R.id.nescroll_stock_in)
    NestedScrollView nescrollStockIn;
    @BindView(R.id.tv_stock_in_outsource)
    TextView tvStockInOutsource;
    @BindView(R.id.tv_stockin_buy_order)
    TextView tvStockinBuyOrder;
    @BindView(R.id.tv_stock_in_send_order)
    TextView tvStockInSendOrder;
    @BindView(R.id.tv_stockin_inlib)
    TextView tvStockinInlib;
    @BindView(R.id.tv_stock_in_check)
    TextView tvStockInCheck;
    @BindView(R.id.tv_stock_in_create_order)
    TextView tvStockInCreateOrder;
    @BindView(R.id.tv_stockin_other_inlib_check)
    TextView tvStockinOtherInlibCheck;
    @BindView(R.id.tv_stockin_other_inlib_create_order)
    TextView tvStockinOtherInlibCreateOrder;
    @BindView(R.id.tv_stockin_out_return)
    TextView tvStockinOutReturn;
    @BindView(R.id.tv_stock_in_produce_return)
    TextView tvStockInProduceReturn;
    @BindView(R.id.tv_stock_in_sale_return)
    TextView tvStockInSaleReturn;
    @BindView(R.id.activity_stock_in)
    LinearLayout activityStockIn;

    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.home_in_lib));
    }

    @Override
    public void initView() {
        if (SpUtils.getInstance().getIsBillList()) {
            tvStockInOutsource.setVisibility(View.VISIBLE);
        }
        /**
         * 获取当前用户的权限，设置显示那些入口
         */
        LoginBean loginBean = new Gson().fromJson(SpUtils.getInstance().getString(Constants.USER_INFO), LoginBean.class);
        LoginBean.GrantPermissionBean grantPermission = loginBean.getGrantPermission();
        List<LoginBean.GrantPermissionBean.ChildPermissionsBeanX> childPermissions = grantPermission.getChildPermissions();
        for (int i = 0; i < childPermissions.size(); i++) {
            if(childPermissions.get(i).getPermissionCode().equals(PERMISSION_STOCKIN)){
                List<LoginBean.GrantPermissionBean.ChildPermissionsBeanX.ChildPermissionsBean> childPermissionsMenu = childPermissions.get(i).getChildPermissions();
                for (int j = 0; j <childPermissionsMenu.size() ; j++) {
                    //采购单
                    if (childPermissionsMenu.get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKIN_BUY)) {
                        tvStockinBuyOrder.setVisibility(View.VISIBLE);
                    }
                    //送货单
                    if (childPermissionsMenu.get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKIN_SEND)) {
                        tvStockInSendOrder.setVisibility(View.VISIBLE);
                    }
                    //来料入库
                    if (childPermissionsMenu.get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKIN_PUTAWAY)) {
                        tvStockinInlib.setVisibility(View.VISIBLE);
                    }
                    //其他入库
                    if (childPermissionsMenu.
                            get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKIN_OTHER_PUTAWAY)) {
                        tvStockinOtherInlibCheck.setVisibility(View.VISIBLE);
                        tvStockinOtherInlibCreateOrder.setVisibility(View.VISIBLE);
                    }
                }
            }

        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_stockin_buy_order, R.id.tv_stock_in_outsource, R.id.tv_stock_in_send_order, R.id.tv_stockin_inlib, R.id.tv_stock_in_check, R.id.tv_stock_in_create_order, R.id.tv_stockin_other_inlib_check, R.id.tv_stockin_other_inlib_create_order, R.id.tv_stockin_out_return, R.id.tv_stock_in_produce_return, R.id.tv_stock_in_sale_return})
    public void onViewClicked(View view) {
        Intent it = new Intent();
        it.setClass(StockInActivity.this, SearchBuyOrderActivity.class);
        switch (view.getId()) {
            case R.id.tv_stockin_buy_order://采购单
                it.putExtra(CODE_STR, Constants.BUY_ORDE_NUM);
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    it.setClass(StockInActivity.this, BuyInStockListActivity.class);
                }
                break;
            case R.id.tv_stock_in_outsource://委外订单
                it.putExtra(CODE_STR, Constants.OUT_SOURCE);
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    it.setClass(StockInActivity.this, BuyInStockListActivity.class);
                }
                break;
            case R.id.tv_stock_in_send_order://送货单
                it.putExtra(CODE_STR, Constants.BUY_SEND_NUM);

                break;
            case R.id.tv_stockin_inlib://来料入库
                it.putExtra(CODE_STR, Constants.COME_MATERAIL_NUM);
                break;
            case R.id.tv_stock_in_check://产成品入库审核
                it.putExtra(CODE_STR, Constants.CREATE_PRO_CHECK_NUM);
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    it.setClass(StockInActivity.this, BuyInStockListActivity.class);
                }
                break;
            case R.id.tv_stock_in_create_order://产成品入库生单
                it.putExtra(CODE_STR, Constants.CREATE_PRO_CREATE_ORDER_NUM);
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    it.setClass(StockInActivity.this, BuyInStockListActivity.class);
                }
                break;
            case R.id.tv_stockin_other_inlib_check://其他入入库选单
                it.putExtra(CODE_STR, Constants.OTHER_IN_STOCK_SELECT_ORDERNO);
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    it.setClass(StockInActivity.this, BuyInStockListActivity.class);
                }
                break;
            case R.id.tv_stockin_other_inlib_create_order://其他入库扫描
                it.putExtra(CODE_STR, Constants.OTHER_IN_STOCK_SCAN);
                it.setClass(this, OtherScanActivity.class);
                break;
            case R.id.tv_stockin_out_return://委外退料
                it.putExtra(CODE_STR, Constants.OUT_RETURN_MATERAIL);
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    it.setClass(StockInActivity.this, BuyInStockListActivity.class);
                }
                break;
            case R.id.tv_stock_in_produce_return://生产退料
                it.putExtra(CODE_STR, Constants.CREATE_RETURN_MATERAIL);
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    it.setClass(StockInActivity.this, BuyInStockListActivity.class);
                }
                break;
            case R.id.tv_stock_in_sale_return://销售退料
                it.putExtra(CODE_STR, Constants.SALE_RETURN_MATERAIL);
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    it.setClass(StockInActivity.this, BuyInStockListActivity.class);
                }
                break;
        }
        startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
