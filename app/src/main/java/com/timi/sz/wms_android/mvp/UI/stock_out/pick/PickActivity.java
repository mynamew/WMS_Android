package com.timi.sz.wms_android.mvp.UI.stock_out.pick;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.mvp.UI.stock_out.batch_point_list.BatchPointListActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.query.StockOutSearchActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_FINISH_GOODS_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_APPLY_BILL;

/**
 * 拣货
 * author: timi
 * create at: 2017/9/18 15:06
 */
public class PickActivity extends BaseActivity<PickView, PickPresenter> implements PickView {

    @BindView(R.id.tv_allot_orderno_tip)
    TextView tvAllotOrdernoTip;
    @BindView(R.id.et_allot_orderno)
    EditText etAllotOrderno;
    @BindView(R.id.tv_send_material_orderno_tip)
    TextView tvSendMaterialOrdernoTip;
    @BindView(R.id.et_send_material_orderno)
    EditText etSendMaterialOrderno;
    @BindView(R.id.tv_sell_outstock_orderno_tip)
    TextView tvSellOutstockOrdernoTip;
    @BindView(R.id.et_sell_outstock_orderno)
    EditText etSellOutstockOrderno;
    @BindView(R.id.iv_sotckout_scan)
    ImageView ivSotckoutScan;
    private int intentCode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_pick;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.stock_out_pick));
    }

    @Override
    public void initView() {
        /**
         *  调拨输入框qqZ
         */
        etAllotOrderno.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(PickActivity.this);
                    String orderNum = etAllotOrderno.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort(R.string.query_allot_scan_hint);
                    }
                    if (orderNum.length() < 4) {
                        ToastUtils.showShort(R.string.input_orderno_more_four);
                    } else {
                        /**
                         * 发起请求
                         */
                        //默认是 生产申请单
                        intentCode = STOCK_OUT_PRODUCTION_APPLY_BILL;
                        if (orderNum.contains("SC")) {//生产调拨
                            etAllotOrderno.setText(orderNum);
                            intentCode = Constants.STOCK_OUT_PRODUCTION_ALLOT;
                        } else if (orderNum.contains("WX")) {//委外调拨
                            intentCode = Constants.STOCK_OUT_OUTSOURCE_ALLOT;
                            etAllotOrderno.setText(orderNum);
                        } else {
                            etAllotOrderno.setText(orderNum);
                        }
                        requestManagerMethod(orderNum);
                    }
                }
                return false;
            }
        });
        /**
         *  成品拣货输入框qqZ
         */
        etSendMaterialOrderno.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(PickActivity.this);
                    String orderNum = etSendMaterialOrderno.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort(R.string.query_allot_scan_hint);
                    }
                    if (orderNum.length() < 4) {
                        ToastUtils.showShort(R.string.input_orderno_more_four);
                    } else {
                        intentCode = Constants.STOCK_OUT_FINISH_GOODS_PICK;
                        etSendMaterialOrderno.setText(orderNum);
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
    public PickPresenter createPresenter() {
        return new PickPresenter(this);
    }

    @Override
    public PickView createView() {
        return this;
    }

    @OnClick(R.id.iv_sotckout_scan)
    public void onViewClicked() {
        scan(Constant.REQUEST_SCAN_MODE_ALL_MODE, new ScanQRCodeResultListener() {
            @Override
            public void scanSuccess(int requestCode, String result) {
                //默认是 生产申请单
                intentCode = STOCK_OUT_PRODUCTION_APPLY_BILL;
                if(result.contains("MS")) {//成品拣货
                    intentCode = Constants.STOCK_OUT_FINISH_GOODS_PICK;
                    etSendMaterialOrderno.setText(result);
                } else {
                    etAllotOrderno.setText(result);
                }
                requestManagerMethod(result);
            }
        });
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
            case STOCK_OUT_FINISH_GOODS_PICK:// 成品拣货
                getPresenter().queryDNByInputForPick(params);
                break;
        }
    }

    @Override
    public void queryDNByInputForPick(QueryDNByInputForPickResult bean) {
        Intent intent = new Intent(this, BatchPointListActivity.class);
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        startActivity(intent);
    }

}
