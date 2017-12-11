package com.timi.sz.wms_android.mvp.UI.stock_in_work.query;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotOneSetpResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotScanResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeInResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.PointResult;
import com.timi.sz.wms_android.bean.stockin_work.query.StockQueryResult;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.allot_one_step.OneStepAllotActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.allot_scan.AllotScanActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.check.CheckActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_instock.FormChangeInstockActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock.FormChangeOutstockActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_ORDERNO;

/**
 * 库内作业 查询的界面
 * author: timi
 * create at: 2017/9/22 13:19
 */
public class StockInWorkQueryActivity extends BaseActivity<StockInWorkQueryView, StockInWorkQueryPresenter> implements StockInWorkQueryView {

    @BindView(R.id.tv_query_tip)
    TextView tvQueryTip;
    @BindView(R.id.et_query_input)
    EditText etQueryInput;
    private int intentCode = Constants.STOCK_IN_WORK_LIBRARY_ADJUST;//默认是库位调整

    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in_work_search;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(Constants.STOCK_IN_WORK_CODE_STR, Constants.STOCK_IN_WORK_LIBRARY_ADJUST);
    }

    @Override
    public void initView() {
        /**
         * 设置输入框的监听
         */
        setEdittextListener(etQueryInput, EDITTEXT_ORDERNO, R.string.please_input_orderno_or_scan, R.string.input_orderno_more_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                requestManagerMethod(result);
            }
        });
    }

    @Override
    public void initData() {
        switch (intentCode) {
            case Constants.STOCK_IN_WORK_ALLOT_SCAN://扫描调入
            case Constants.STOCK_IN_WORK_ALLOT_ONE_STEP://一步调入
                setActivityTitle(getString(R.string.query_allot_scan_title));
                tvQueryTip.setText(R.string.query_allot_scan_tip);
                break;
            case Constants.STOCK_IN_WORK_FORM_CHANGE_OUT://形态转换-出库
                setActivityTitle(getString(R.string.query_out_stock_form_change_title));
                tvQueryTip.setText(R.string.query_form_change_tip);
                break;
            case Constants.STOCK_IN_WORK_FORM_CHANGE_IN://形态转换-入库
                setActivityTitle(getString(R.string.query_in_stock_form_change_title));
                tvQueryTip.setText(R.string.query_form_change_tip);
                break;
            case Constants.STOCK_IN_WORK_POINT://盘点
                setActivityTitle(getString(R.string.query_point_title));
                tvQueryTip.setText(R.string.query_point_tip);
                break;
        }
    }

    @Override
    public StockInWorkQueryPresenter createPresenter() {
        return new StockInWorkQueryPresenter(this);
    }

    @Override
    public StockInWorkQueryView createView() {
        return this;
    }

    @Override
    public void queryAllotScan(AllotScanResult result) {
        Intent intent = new Intent(this, AllotScanActivity.class);
        intent.putExtra(Constants.STOCK_IN_WORK_BEAN, new Gson().toJson(result));
        intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR, intentCode);
        startActivity(intent);
    }

    @Override
    public void queryAllotOneStep(AllotOneSetpResult result) {
        Intent intent = new Intent(this, OneStepAllotActivity.class);
        intent.putExtra(Constants.STOCK_IN_WORK_BEAN, new Gson().toJson(result));
        intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR, intentCode);
        startActivity(intent);
    }

    @Override
    public void queryFormChangeOut(FormChangeOutResult result) {
        Intent intent = new Intent(this, FormChangeOutstockActivity.class);
        intent.putExtra(Constants.STOCK_IN_WORK_BEAN, new Gson().toJson(result));
        intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR, intentCode);
        startActivity(intent);
    }

    @Override
    public void queryFormChangeIn(FormChangeInResult result) {
        Intent intent = new Intent(this, FormChangeInstockActivity.class);
        intent.putExtra(Constants.STOCK_IN_WORK_BEAN, new Gson().toJson(result));
        intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR, intentCode);
        startActivity(intent);
    }

    @Override
    public void queryPoint(PointResult result) {
        Intent intent = new Intent(this, CheckActivity.class);
        intent.putExtra(Constants.STOCK_IN_WORK_BEAN, new Gson().toJson(result));
        intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR, intentCode);
        startActivity(intent);
    }

    @OnClick({R.id.iv_sbo_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sbo_scan://扫码
                scan(Constant.REQUEST_SCAN_MODE_ALL_MODE, new ScanQRCodeResultListener() {

                    @Override
                    public void scanSuccess(int RequestCode, String result) {
                        etQueryInput.setText(result);
                        requestManagerMethod(result);
                    }
                });
                break;
        }
    }

    /**
     * 发起请求的方法
     *
     * @param orderNum
     */
    private void requestManagerMethod(String orderNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillNo", orderNum);
        /**
         * 不同的intentcode  请求不同
         */
        switch (intentCode) {
            case Constants.STOCK_IN_WORK_ALLOT_SCAN://扫描调入
                getPresenter().queryAllotScan(params);
                break;
            case Constants.STOCK_IN_WORK_ALLOT_ONE_STEP://一步调入
                getPresenter().queryAllotOneStep(params);
                break;
            case Constants.STOCK_IN_WORK_FORM_CHANGE_OUT://形态转换-出库
                getPresenter().queryFormChangeOut(params);
                break;
            case Constants.STOCK_IN_WORK_FORM_CHANGE_IN://形态转换-入库
                getPresenter().queryFormChangeIn(params);
                break;
            case Constants.STOCK_IN_WORK_POINT://盘点
                getPresenter().queryPoint(params);
                break;
        }


    }

}
