package com.timi.sz.wms_android.mvp.UI.stock_in.query;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdInstockByInputResult;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.mvp.UI.stock_in.point.StockInPointActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.FinishedGoodsAuditPutAwayActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.FinishedGoodsCreateBillPutAwayActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.OtherAuditActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.OutMaterialReturnActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.PutAwayActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.SaleGoodsReturnActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_ORDERNO;
import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_SUPPLIER;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_BUY_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_CREATE_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_OTHER_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_PRODUCTION_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_SALE_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_RECEIVE_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_SEND_BEAN;

/**
 * 查找订单
 */
public class SearchBuyOrderActivity extends BaseActivity<SearchBuyOrderView, SearchBuyOrderPresenter> implements SearchBuyOrderView {
    @BindView(R.id.tv_sbo_tip)
    TextView tvSboTip;
    @BindView(R.id.et_sbo_input)
    EditText etSboInput;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_sbo_scan)
    ImageView ivSboScan;
    @BindView(R.id.activity_search_buy_order)
    LinearLayout activitySearchBuyOrder;

    int intentCode = Constants.BUY_ORDE_NUM;//默认的额跳转code值
    @BindView(R.id.tv_query_title)
    TextView tvQueryTitle;

    @Override
    public int setLayoutId() {
        return R.layout.activity_search_buy_order;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("订单查询");
        //获取跳转的code 值 用来显示不同的提示
        intentCode = getIntent().getIntExtra(Constants.CODE_STR, Constants.BUY_ORDE_NUM);
        switch (intentCode) {
            case Constants.BUY_ORDE_NUM://采购单
                tvSboTip.setText(R.string.orderno);
                tvTitle.setText(R.string.receive_goods_query);
                tvQueryTitle.setText(R.string.receive_goods_query);
                etSboInput.setHint(R.string.please_input_orderno_or_scan);
                break;
            case Constants.BUY_SEND_NUM://送货单
                tvSboTip.setText(R.string.orderno);
                tvTitle.setText(R.string.receive_goods_orderno_query);
                tvQueryTitle.setText(R.string.receive_goods_orderno_query);
                etSboInput.setHint(R.string.please_input_send_orderno_or_scan);
                break;
            case Constants.COME_MATERAIL_NUM://来料单
                tvSboTip.setText(R.string.orderno);
                tvTitle.setText(R.string.in_stock_receivce_goods_orderno);
                tvQueryTitle.setText(R.string.stock_in_come_pro_in);
                etSboInput.setHint(R.string.please_input_orderno_or_scan);
                break;
            case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                tvSboTip.setText(R.string.product_orderno_tip);
                tvTitle.setText(R.string.produce_create_order);
                tvQueryTitle.setText(R.string.finish_goods_in_stock_title);
                etSboInput.setHint(R.string.please_input_orderno_or_scan);
                break;
            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                tvSboTip.setText(R.string.product_orderno_tip);
                tvTitle.setText(R.string.finish_goods_in_stock_title);
                tvQueryTitle.setText(R.string.finish_goods_in_stock_title);
                etSboInput.setHint(R.string.please_input_orderno_or_scan);
                break;
            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单
                tvQueryTitle.setText(R.string.putaway_other_tip);
                tvSboTip.setText(R.string.other_in_stock_orderno);
                tvTitle.setText(R.string.other_select_order_title);
                etSboInput.setHint(R.string.please_input_orderno_or_scan);
                break;
            case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）
                break;
            case Constants.OUT_RETURN_MATERAIL://委外退料
                tvSboTip.setText(R.string.out_return_order);
                tvQueryTitle.setText(R.string.putaway_out_return_material_tip);
                tvTitle.setText(R.string.out_return_material_select_orderno);
                etSboInput.setHint(R.string.please_input_orderno_or_scan);
                break;
            case Constants.CREATE_RETURN_MATERAIL://生产退料
                tvSboTip.setText(R.string.create_return_orderno);
                tvTitle.setText(R.string.create_return_orderno_select_orderno);
                tvQueryTitle.setText(R.string.putaway_production_return_material_tip);
                etSboInput.setHint(R.string.please_input_orderno_or_scan);
                break;
            case Constants.SALE_RETURN_MATERAIL://销售 退料
                tvSboTip.setText(R.string.sale_return_material_tip);
                tvQueryTitle.setText(R.string.putaway_sale_return_material_tip);
                tvTitle.setText(R.string.sale_return_material_select_orderno);
                etSboInput.setHint(R.string.please_input_orderno_or_scan);
                break;
        }
    }

    @Override
    public void initView() {
        /**
         * 设置输入框的监听
         */
        setEdittextListener(etSboInput, EDITTEXT_ORDERNO, R.string.please_input_orderno_or_scan, R.string.input_orderno_more_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                requestManagerMethod(result);
            }
        });
    }
    @Override
    public void initData() {

    }

    @Override
    public SearchBuyOrderPresenter createPresenter() {
        return new SearchBuyOrderPresenter(this);
    }

    @Override
    public SearchBuyOrderView createView() {
        return this;
    }

    @OnClick({R.id.iv_sbo_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sbo_scan://扫码
                scan(Constant.REQUEST_SCAN_MODE_ALL_MODE, new ScanQRCodeResultListener() {

                    @Override
                    public void scanSuccess(int RequestCode, String result) {
                        etSboInput.setText(result);
                        requestManagerMethod(result);
                    }
                });
                break;
        }
    }


    /**
     * 采购单跳转
     *
     * @param bean
     */
    @Override
    public void buyOrdernoQuery(BuyOrdernoBean bean) {
        Intent it = new Intent(this, StockInPointActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_BUY_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 收货单跳转
     *
     * @param bean
     */
    @Override
    public void sendOrdernoQuery(SendOrdernoBean bean) {
        Intent it = new Intent(this, StockInPointActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_SEND_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 收货单跳转
     *
     * @param bean
     */
    @Override
    public void searchReceiveGoodOrderno(ReceiveOrdernoBean bean) {
        Intent it = new Intent(this, PutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_RECEIVE_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 产成品- 审核
     *
     * @param bean
     */
    @Override
    public void searchFinishGoodsOrderno(QueryPrdInstockByInputResult bean) {
        Intent it = new Intent(this, FinishedGoodsAuditPutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 产成品  生单
     *
     * @param bean
     */
    @Override
    public void searchFinishGoodsCreateBillOrderno(FinishGoodsCreateBillBean bean) {
        Intent it = new Intent(this, FinishedGoodsCreateBillPutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_CREATE_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 其他 选单
     *
     * @param bean
     */
    @Override
    public void searchOtherAuditSelectOrderno(OtherAuditSelectOrdernoBean bean) {
        Intent it = new Intent(this, OtherAuditActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_OTHER_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 委外退料
     *
     * @param bean
     */
    @Override
    public void searchOutReturnMaterialOrderno(QueryOutSourceReturnByInputResult bean) {
        Intent it = new Intent(this, OutMaterialReturnActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_OUT_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 生产
     *
     * @param bean
     */
    @Override
    public void searchProductionReturnMaterialOrderno(QueryPrdReturnByInputResult bean) {
        Intent it = new Intent(this, PutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_PRODUCTION_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 销售
     *
     * @param bean
     */
    @Override
    public void searchSaleGoodsReturnOrderno(SaleGoodsReturnBean bean) {
        Intent it = new Intent(this, SaleGoodsReturnActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_SALE_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 当输入的单号 查询有误的时候，直接将edittext内容选中
     */
    @Override
    public void errorSetEdittextSelect() {
        Selection.selectAll(etSboInput.getText());
    }

    /**
     * 根据 intentcode 发起不同的请求
     *
     * @param orderNum
     */
    public void requestManagerMethod(String orderNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillNo", orderNum);
        /**
         * 不同的intentcode  请求不同
         */
        switch (intentCode) {
            case Constants.BUY_ORDE_NUM://采购单
                getPresenter().buyOrdernoQuery(params);
                break;
            case Constants.BUY_SEND_NUM://送货单
                getPresenter().sendOrdernoQuery(params);
                break;
            case Constants.COME_MATERAIL_NUM://来料单
                getPresenter().searchReceiveGoodOrderno(params);
                break;
            case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                getPresenter().searchFinishGoodsOrderno(params);
                break;
            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                getPresenter().searchFinishGoodsCreateBillOrderno(params);
                break;
            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 审核
                getPresenter().searchOtherAuditSelectOrderno(params);
                break;
            case Constants.OUT_RETURN_MATERAIL://委外退料
                getPresenter().searchOutReturnMaterialOrderno(params);
                break;
            case Constants.CREATE_RETURN_MATERAIL://生产退料
                getPresenter().searchProductionReturnMaterialOrderno(params);
                break;
            case Constants.SALE_RETURN_MATERAIL://销售 退料
                getPresenter().searchSaleGoodsReturnOrderno(params);
                break;
        }
    }

}
