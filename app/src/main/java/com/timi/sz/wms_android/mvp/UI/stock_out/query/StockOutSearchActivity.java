package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.mvp.UI.stock_in.point.StockInPointActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.query.SearchBuyOrderActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.CommonScanActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_FEEDING;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;

/**
 * 出库搜索界面
 * author: timi
 * create at: 2017/9/4 8:51
 */
public class StockOutSearchActivity extends BaseActivity<StockOutSearchView, StockOutSearchPresenter> implements StockOutSearchView {
    @BindView(R.id.tv_stockout_tip)
    TextView tvStockoutTip;
    @BindView(R.id.et_stockout_input)
    EditText etStockoutInput;
    @BindView(R.id.iv_sotckout_scan)
    ImageView ivSotckoutScan;
    private int intentCode = 5020;// code，码 默认是委外退料单

    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_out_search;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT);
    }

    @Override
    public void initView() {
        /**
         *设置title 和 text
         */
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                setActivityTitle(getString(R.string.stock_out_outsource_feed_title));
                tvStockoutTip.setText(R.string.stock_out_outsource_feed_tip);
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                setActivityTitle(getString(R.string.stock_out_outsource_audit_title));
                tvStockoutTip.setText(R.string.stock_out_outsource_audit_tip);
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外 生单
                setActivityTitle(getString(R.string.stock_out_outsource_audit_title));
                tvStockoutTip.setText(R.string.stock_out_outsource_bill_tip);
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产 补料
                setActivityTitle(getString(R.string.stock_out_production_feed_title));
                tvStockoutTip.setText(R.string.stock_out_production_feed_tip);
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产 审核
                setActivityTitle(getString(R.string.stock_out_production_feed_title));
                tvStockoutTip.setText(R.string.stock_out_production_feed_tip);
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产 生单
                setActivityTitle(getString(R.string.stock_out_production_feed_title));
                tvStockoutTip.setText(R.string.stock_out_production_feed_tip);
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售 审核
                setActivityTitle(getString(R.string.stock_out_production_feed_title));
                tvStockoutTip.setText(R.string.stock_out_production_feed_tip);
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售 生单
                setActivityTitle(getString(R.string.stock_out_production_feed_title));
                tvStockoutTip.setText(R.string.stock_out_production_feed_tip);
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT:// 其他 审核
                setActivityTitle(getString(R.string.stock_out_production_feed_title));
                tvStockoutTip.setText(R.string.stock_out_production_feed_tip);
                break;
        }
        /**
         *  输入框
         */
        etStockoutInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(StockOutSearchActivity.this);
                    String orderNum = etStockoutInput.getText().toString().trim();
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
    public StockOutSearchPresenter createPresenter() {
        return new StockOutSearchPresenter(this);
    }

    @Override
    public StockOutSearchView createView() {
        return this;
    }


    @OnClick(R.id.iv_sotckout_scan)
    public void onViewClicked() {
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        etStockoutInput.setText(bundle.getString("result"));
                        requestManagerMethod(bundle.getString("result"));
                    }
                }
                break;
        }
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
            case Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外退料
                getPresenter().searchOutsourceFeed(orderNum);
                break;
            case Constants.STOCK_OUT_OUTSOURCE_AUDIT://委外发货-审核
                break;
            case Constants.STOCK_OUT_OUTSOURCE_BILL:////委外发货-生单
                break;
            case Constants.STOCK_OUT_PRODUCTION_FEEDING://生产补料
                break;
            case Constants.STOCK_OUT_PRODUCTION_AUDIT://生产领料-审核
                break;
            case Constants.STOCK_OUT_PRODUCTION_BILL://生产领料-生单
                break;
            case Constants.STOCK_OUT_SELL_OUT_AUDIT://销售领料-审核
                break;
            case Constants.STOCK_OUT_SELL_OUT_BILL://销售领料-生单
                break;
            case Constants.STOCK_OUT_OTHER_OUT_AUDIT://其他出库-审核
                break;
            case Constants.STOCK_OUT_OTHER_OUT_BILL://其他出库-生单
                break;
        }
    }

    /**
     * 委外补料的请求 回调
     * @param bean
     */
    @Override
    public void searchOutsourceFeed(OutSourceFeedBean bean) {

    }
}
