package com.timi.sz.wms_android.mvp.UI.stock_in.query;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OutReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ProductionReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.mvp.UI.stock_in.point.StockInPointActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.PutAwayActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.CommonScanActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_BUY_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_CREATE_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_OTHER_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_PRODUCTION_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_SALE_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_RECEIVE_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_SEND_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.ORDER_NO;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE;

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
                etSboInput.setHint(R.string.please_input_orderno_or_scan);
                break;
            case Constants.BUY_SEND_NUM://送货单
                tvSboTip.setText(R.string.orderno);
                tvTitle.setText(R.string.receive_goods_orderno_query);
                etSboInput.setHint(R.string.please_input_send_orderno_or_scan);
                break;
            case Constants.COME_MATERAIL_NUM://来料单
                tvSboTip.setText(R.string.orderno);
                tvTitle.setText(R.string.in_stock_receivce_goods_orderno);
                etSboInput.setHint(R.string.please_input_receivce_orderno_or_scan);
                break;
            case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                tvSboTip.setText(String.format(getString(R.string.create_task_orderno), ""));
                tvTitle.setText(R.string.produce_create_order);
                etSboInput.setHint(R.string.please_input_create_product_orderno_or_scan);
                break;
            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                tvSboTip.setText(R.string.create_task_orderno_tip);
                tvTitle.setText(R.string.product_in_stock_create_orderno);
                etSboInput.setHint(R.string.please_input_create_task_num_or_scan);
                break;
            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单
                tvSboTip.setText(R.string.other_in_stock_orderno);
                tvTitle.setText(R.string.other_select_order_title);
                etSboInput.setHint(R.string.please_input_other_lin_num_or_scan);
                break;
            case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）
                break;
            case Constants.OUT_RETURN_MATERAIL://委外退料
                tvSboTip.setText(R.string.out_return_order);
                tvTitle.setText(R.string.out_return_material_select_orderno);
                etSboInput.setHint(R.string.pealse_out_return_material_select_orderno_or_scan);
                break;
            case Constants.CREATE_RETURN_MATERAIL://生产退料
                tvSboTip.setText(R.string.create_return_orderno);
                tvTitle.setText(R.string.create_return_orderno_select_orderno);
                etSboInput.setHint(R.string.please_create_return_material_num_or_scan);
                break;
            case Constants.SALE_RETURN_MATERAIL://销售 退料
                tvSboTip.setText(R.string.sale_return_material_tip);
                tvTitle.setText(R.string.sale_return_material_select_orderno);
                etSboInput.setHint(R.string.please_input_sale_return_material_orderno_or_scan);
                break;
        }
    }

    @Override
    public void initView() {
        etSboInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(SearchBuyOrderActivity.this);
                    String orderNum = etSboInput.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort("请输入单号");
                    }
                    if (orderNum.length() <= 4) {
                        ToastUtils.showShort("输入查询单号位数必须是4位以上");
                    } else {
                        /**
                         * 发起请求
                         */
                        requestManagerMethod(orderNum);
                    }
                }
                return false;
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
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 60);
                } else {
                    //权限已经被授予，在这里直接写要执行的相应方法即可
                    Intent intent = new Intent(this, CommonScanActivity.class);

                    String pointMsg = getResources().getString(R.string.scan_point_title);
                    Bundle bundle = new Bundle();
                    bundle.putString("pointMsg", pointMsg);
                    intent.putExtras(bundle);

                    intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        etSboInput.setText(bundle.getString("result"));
                        requestManagerMethod(bundle.getString("result"));
                    }
                }
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
        it.putExtra(IN_STOCK_BUY_BEAN, bean);
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
        it.putExtra(IN_STOCK_SEND_BEAN, bean);
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
        it.putExtra(IN_STOCK_RECEIVE_BEAN, bean);
        startActivity(it);
    }

    /**
     * 产成品- 审核
     *
     * @param bean
     */
    @Override
    public void searchFinishGoodsOrderno(FinishGoodsOrdernoBean bean) {
        Intent it = new Intent(this, PutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_BEAN, bean);
        startActivity(it);
    }

    /**
     * 产成品  生单
     *
     * @param bean
     */
    @Override
    public void searchFinishGoodsCreateBillOrderno(FinishGoodsCreateBillBean bean) {
        Intent it = new Intent(this, PutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_CREATE_BEAN, bean);
        startActivity(it);
    }

    /**
     * 其他 选单
     *
     * @param bean
     */
    @Override
    public void searchOtherAuditSelectOrderno(OtherAuditSelectOrdernoBean bean) {
        Intent it = new Intent(this, PutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_OTHER_BEAN, bean);
        startActivity(it);
    }

    /**
     * 委外
     *
     * @param bean
     */
    @Override
    public void searchOutReturnMaterialOrderno(OutReturnMaterialBean bean) {
        Intent it = new Intent(this, PutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_OUT_BEAN, bean);
        startActivity(it);
    }

    /**
     * 生产
     *
     * @param bean
     */
    @Override
    public void searchProductionReturnMaterialOrderno(ProductionReturnMaterialBean bean) {
        Intent it = new Intent(this, PutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_PRODUCTION_BEAN, bean);
        startActivity(it);
    }

    /**
     * 销售
     *
     * @param bean
     */
    @Override
    public void searchSaleGoodsReturnOrderno(SaleGoodsReturnBean bean) {
        Intent it = new Intent(this, PutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_SALE_BEAN, bean);
        startActivity(it);
    }

    /**
     * 根据 intentcode 发起不同的请求
     *
     * @param orderNum
     */
    public void requestManagerMethod(String orderNum) {
        /**
         * 不同的intentcode  请求不同
         */
        switch (intentCode) {
            case Constants.BUY_ORDE_NUM://采购单
                getPresenter().buyOrdernoQuery(orderNum);
                break;
            case Constants.BUY_SEND_NUM://送货单
                getPresenter().sendOrdernoQuery(orderNum);
                break;
            case Constants.COME_MATERAIL_NUM://来料单
                getPresenter().searchReceiveGoodOrderno(orderNum);
                break;
            case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                getPresenter().searchFinishGoodsOrderno(orderNum);
                break;
            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                getPresenter().searchFinishGoodsCreateBillOrderno(orderNum);
                break;
            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 生单
                getPresenter().searchOtherAuditSelectOrderno(orderNum);
                break;
            case Constants.OUT_RETURN_MATERAIL://委外退料
                getPresenter().searchOutReturnMaterialOrderno(orderNum);
                break;
            case Constants.CREATE_RETURN_MATERAIL://生产退料
                getPresenter().searchProductionReturnMaterialOrderno(orderNum);
                break;
            case Constants.SALE_RETURN_MATERAIL://销售 退料
                getPresenter().searchSaleGoodsReturnOrderno(orderNum);
                break;
        }
    }
}
