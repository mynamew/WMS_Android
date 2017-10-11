package com.timi.sz.wms_android.mvp.UI.stock_in_work.query;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

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
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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
        etQueryInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(StockInWorkQueryActivity.this);
                    String orderNum = etQueryInput.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort("请输入单号");
                    }
                    if (orderNum.length() < 4) {
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
        switch (intentCode) {
            case Constants.STOCK_IN_WORK_ALLOT_SCAN://扫描调入
            case Constants.STOCK_IN_WORK_ALLOT_ONE_STEP://一步调入
                setActivityTitle(getString(R.string.query_allot_scan_title));
                tvQueryTip.setText(R.string.query_allot_scan_tip);
                break;
            case Constants.STOCK_IN_WORK_FORM_CHANGE_OUT://形态转换-出库
            case Constants.STOCK_IN_WORK_FORM_CHANGE_IN://形态转换-入库
                setActivityTitle(getString(R.string.query_form_change_title));
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

    }

    @Override
    public void queryAllotOneStep(AllotOneSetpResult result) {

    }

    @Override
    public void queryFormChangeOut(FormChangeOutResult result) {

    }

    @Override
    public void queryFormChangeIn(FormChangeInResult result) {

    }
    @Override
    public void queryPoint(PointResult result) {

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
     * @param orderNum
     */
    private void requestManagerMethod(String orderNum) {
        Map<String,Object> params=new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId",SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillNo",orderNum);
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
