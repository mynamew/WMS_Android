package com.timi.sz.wms_android.mvp.UI.stock_out;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByMaterialCodeData;
import com.timi.sz.wms_android.mvp.UI.list.BuyInStockListActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.BuyReturnMaterialActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material.ScanReturnMaterialActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.other.scan.OtherOutStockScanActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.query.StockOutSearchActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_BUY_RETURN_ORDERNO_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.PERMISSION_STOCKOUT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
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
public class StockOutActivity extends BaseActivity<StockOutView, StockOutPresenter> implements StockOutView {
    @BindView(R.id.tv_stock_out_out_check)
    TextView tvStockOutOutCheck;
    @BindView(R.id.tv_stock_out_out_create_order)
    TextView tvStockOutOutCreateOrder;
    @BindView(R.id.tv_stock_out_out_allot)
    TextView tvStockOutOutAllot;
    @BindView(R.id.tv_stock_out_out_add_materail)
    TextView tvStockOutOutAddMaterail;
    @BindView(R.id.tv_stock_out_create_check)
    TextView tvStockOutCreateCheck;
    @BindView(R.id.tv_stock_out_create_create_order)
    TextView tvStockOutCreateCreateOrder;
    @BindView(R.id.tv_stock_out_apply_bill)
    TextView tvStockOutApplyBill;
    @BindView(R.id.tv_stock_out_create_allot)
    TextView tvStockOutCreateAllot;
    @BindView(R.id.tv_stock_out_create_add_materail)
    TextView tvStockOutCreateAddMaterail;
    @BindView(R.id.tv_stock_out_sale_check)
    TextView tvStockOutSaleCheck;
    @BindView(R.id.tv_stock_out_sale_create_order)
    TextView tvStockOutSaleCreateOrder;
    @BindView(R.id.tv_stock_out_pick_send)
    TextView tvStockOutPickSend;
    @BindView(R.id.tv_stock_out_pick_sale)
    TextView tvStockOutPickSale;
    @BindView(R.id.tv_stock_out_sale_trans)
    TextView tvStockOutSaleTrans;
    @BindView(R.id.tv_stock_out_other_check)
    TextView tvStockOutOtherCheck;
    @BindView(R.id.tv_stock_out_other_create_order)
    TextView tvStockOutOtherCreateOrder;
    @BindView(R.id.tv_stock_out_other_buy_return)
    TextView tvStockOutOtherBuyReturn;
    @BindView(R.id.tv_stock_out_other_buy_return_bill)
    TextView tvStockOutOtherBuyReturnBill;
    @BindView(R.id.tv_allot_outstock)
    TextView tvAllotOutstock;
    @BindView(R.id.activity_stock_out)
    LinearLayout activityStockOut;
    @BindView(R.id.card_out)
    CardView cardOut;
    @BindView(R.id.card_production)
    CardView cardProduction;
    @BindView(R.id.card_sale)
    CardView cardSale;
    @BindView(R.id.card_synthesize)
    CardView cardSynthesize;

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
        /**
         * 获取当前用户的权限，设置显示那些入口
         */
        LoginBean loginBean = new Gson().fromJson(SpUtils.getInstance().getString(Constants.USER_INFO), LoginBean.class);
        LoginBean.GrantPermissionBean grantPermission = loginBean.getGrantPermission();
        List<LoginBean.GrantPermissionBean.ChildPermissionsBeanX> childPermissions = grantPermission.getChildPermissions();
        for (int i = 0; i < childPermissions.size(); i++) {
            if (childPermissions.get(i).getPermissionCode().equals(PERMISSION_STOCKOUT)) {
                List<LoginBean.GrantPermissionBean.ChildPermissionsBeanX.ChildPermissionsBean> childPermissionsMenu = childPermissions.get(i).getChildPermissions();
                for (int j = 0; j < childPermissionsMenu.size(); j++) {
                    //采购退料
                    if (childPermissionsMenu.get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKOUT_PURRETURN)) {
                        //设置采购退料 invisble
                        tvStockOutOtherBuyReturn.setVisibility(View.VISIBLE);
                        tvStockOutOtherBuyReturnBill.setVisibility(View.VISIBLE);
                    }
                    //委外发料
                    if (childPermissionsMenu.get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKOUT_OUT_SEND)) {
                        tvStockOutOutCheck.setVisibility(View.VISIBLE);
                        tvStockOutOutCreateOrder.setVisibility(View.VISIBLE);
                    }
                    //委外调拨
                    if (childPermissionsMenu.get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKOUT_OUT_ALLOT)) {
                        tvStockOutOutAllot.setVisibility(View.VISIBLE);
                    }
                    //委外补料
                    if (childPermissionsMenu.
                            get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKOUT_OUT_FEED)) {
                        tvStockOutOutAddMaterail.setVisibility(View.VISIBLE);
                    }
                    //生产领料
                    if (childPermissionsMenu.
                            get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKOUT_PRODUCTION_PICK)) {
                        tvStockOutCreateCheck.setVisibility(View.VISIBLE);
                        tvStockOutCreateCreateOrder.setVisibility(View.VISIBLE);
                    }
                    //生产补料
                    if (childPermissionsMenu.
                            get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKOUT_PRODUCTION_FEED)) {
                        tvStockOutCreateAddMaterail.setVisibility(View.VISIBLE);
                    }
                    //生产调拨
                    if (childPermissionsMenu.
                            get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKOUT_PRODUCTION_ALLOT)) {
                        tvStockOutCreateAllot.setVisibility(View.VISIBLE);
                    }
                    //销售出库
                    if (childPermissionsMenu.
                            get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKOUT_SALE)) {
                        tvStockOutSaleCheck.setVisibility(View.VISIBLE);
                        tvStockOutSaleCreateOrder.setVisibility(View.VISIBLE);
                    }
                    //其他出库
                    if (childPermissionsMenu.
                            get(j).getPermissionCode().equals(Constants.PERMISSION_STOCKOUT_OTHER)) {
                        tvStockOutOtherCheck.setVisibility(View.VISIBLE);
                        tvStockOutOtherCreateOrder.setVisibility(View.VISIBLE);
                    }

                }
            }
        }
        //判断是否显示 当前的菜单，也就是 最外围的标题和cardview
        //委外的cardview
        if (tvStockOutOutCheck.getVisibility()==View.INVISIBLE
                &&tvStockOutOutCreateOrder.getVisibility()==View.INVISIBLE
                &&tvStockOutOutAllot.getVisibility()==View.INVISIBLE
                &&tvStockOutOutAddMaterail.getVisibility()==View.INVISIBLE) {
               cardOut.setVisibility(View.INVISIBLE);
        }
        //生产的线性布局，因为有个申请生单
        if (tvStockOutCreateCheck.getVisibility()==View.INVISIBLE
                &&tvStockOutCreateCreateOrder.getVisibility()==View.INVISIBLE
                &&tvStockOutCreateAllot.getVisibility()==View.INVISIBLE
                &&tvStockOutCreateAddMaterail.getVisibility()==View.INVISIBLE) {
            //设置生产的linearlayout
            findViewById(R.id.ll_production).setVisibility(View.INVISIBLE);
        }
        //综合的cardview
        if (tvStockOutOtherCheck.getVisibility()==View.INVISIBLE
                &&tvStockOutOtherCreateOrder.getVisibility()==View.INVISIBLE
                &&tvStockOutOtherBuyReturn.getVisibility()==View.INVISIBLE
                &&tvStockOutOtherBuyReturnBill.getVisibility()==View.INVISIBLE) {
            cardSynthesize.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public StockOutPresenter createPresenter() {
        return new StockOutPresenter(this);
    }

    @Override
    public StockOutView createView() {
        return this;
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
            R.id.activity_stock_out,
            R.id.tv_stock_out_pick_send,
            R.id.tv_stock_out_pick_sale,
            R.id.tv_allot_outstock,
            R.id.tv_stock_out_other_buy_return_bill})
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
                intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_ALLOT_OUT_PICK);
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
                    intent.putExtra("isCreateBill", false);
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
            case R.id.tv_stock_out_other_buy_return_bill://采购退料 制单
                /**
                 * 扫物料码的网络请求
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                getPresenter().buyReturnMaterialByMaterialCodeData(params);
                return;
            case R.id.tv_allot_outstock://调拨调出
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(StockOutActivity.this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockOutActivity.this, StockOutSearchActivity.class);
                }
                intent.putExtra(Constants.STOCK_OUT_CODE_STR, Constants.STOCK_OUT_ALLOT_OUT);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void buyReturnMaterialByMaterialCodeData(BuyReturnMaterialByMaterialCodeData
                                                            materialBean) {
        Intent intent = new Intent();
        if (0 != materialBean.getScanId()) {
            intent.setClass(this, ScanReturnMaterialActivity.class);
            intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_PURCHASE_MATERIAL_RETURN);
            intent.putExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN, new Gson().toJson(materialBean));
        } else {
            intent.setClass(this, BuyReturnMaterialActivity.class);
            intent.putExtra("isCreateBill", true);
            intent.putExtra(STOCK_OUT_CODE_STR, STOCK_OUT_PURCHASE_MATERIAL_RETURN);
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
