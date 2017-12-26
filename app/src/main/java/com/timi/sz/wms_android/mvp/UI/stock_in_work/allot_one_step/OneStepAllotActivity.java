package com.timi.sz.wms_android.mvp.UI.stock_in_work.allot_one_step;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Selection;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.outstock.outsource.common.DetailResultsBean;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotOneSetpResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 一步调入
 * author: timi
 * create at: 2017/12/1 15:01
 */
public class OneStepAllotActivity extends BaseActivity<OneStepAllotView, OneStepAllotPresenter> implements OneStepAllotView {

    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.tv_putaway_scan_location_tip)
    TextView tvPutawayScanLocationTip;
    @BindView(R.id.et_goal_storage)
    EditText etGoalStorage;
    @BindView(R.id.iv_putaway_scan_location)
    ImageView ivPutawayScanLocation;
    @BindView(R.id.tv_line_num)
    TextView tvLineNum;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_allot_total_num)
    TextView tvAllotTotalNum;
    @BindView(R.id.tv_allot_out)
    TextView tvAllotOut;
    @BindView(R.id.tv_arrive_good_num)
    TextView tvArriveGoodNum;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.rlv_allot_one_step)
    RecyclerView rlvAllotOneStep;
    @BindView(R.id.tv_allot_out_total_num)
    TextView tvAllotOutTotalNum;
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_name)
    TextView tvName;
    private int intentCode;
    private AllotOneSetpResult bean;
    private BaseRecyclerAdapter<DetailResultsBean> detailResultsBeanBaseRecyclerAdapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_one_step_allot;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.siw_one_step_in));
        bean = new Gson().fromJson(getIntent().getStringExtra(Constants.STOCK_IN_WORK_BEAN), AllotOneSetpResult.class);
        intentCode = getIntent().getIntExtra(Constants.STOCK_IN_WORK_CODE_STR, 0);
    }

    private String locationCode;

    @Override
    public void initView() {
        setEdittextListener(etGoalStorage, Constants.REQUEST_SCAN_CODE_MATERIIAL, R.string.please_input_scan_lib_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 保存库位码
                 */
                locationCode = result;
                /**
                 * 发起请求 一步掉入
                 */

                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", bean.getSummaryResults().getBillId());
                params.put("LocationNo", result);
                getPresenter().submitTransferOneStep(params);
            }
        });
    }
    @Override
    public void initData() {
        AllotOneSetpResult.SummaryResultsBean summaryResults = bean.getSummaryResults();
        setHeaderContent(summaryResults.getBillCode(), summaryResults.getBillDate(), summaryResults.getWarehouseName(), summaryResults.getQty(), summaryResults.getScanQty());
        detailResultsBeanBaseRecyclerAdapter = new BaseRecyclerAdapter<DetailResultsBean>(this, bean.getDetailResults()) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.header_allot_one_step;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, DetailResultsBean item) {
                holder.setTextView(R.id.tv_line_num, item.getLine());
                holder.setTextView(R.id.tv_material_code, item.getMaterialCode());
                holder.setTextView(R.id.tv_material_name, item.getMaterialName());
                holder.setTextView(R.id.tv_allot_total_num, item.getQty());
                holder.setTextView(R.id.tv_allot_out, item.getScanQty());

            }
        };
        rlvAllotOneStep.setAdapter(detailResultsBeanBaseRecyclerAdapter);
        rlvAllotOneStep.setLayoutManager(new LinearLayoutManager(this));
        rlvAllotOneStep.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));
    }

    @Override
    public OneStepAllotPresenter createPresenter() {
        return new OneStepAllotPresenter(this);
    }

    @Override
    public OneStepAllotView createView() {
        return this;
    }
    @Override
    public void submitTransferOneStep(Object bean) {
        ToastUtils.showShort(getString(R.string.one_step_success));
        onBackPressed();
    }

    /**
     * 设置头部的内容
     *
     * @param orderno
     * @param date
     * @param stockName
     * @param allotNum
     * @param allotTotalNum
     */
    public void setHeaderContent(String orderno, String date, String stockName, int allotNum, int allotTotalNum) {
        tvOrderno.setText(orderno);
        tvDate.setText(date);
        tvName.setText(stockName);
        tvAllotOutTotalNum.setText(String.valueOf(allotNum));
        tvAllotTotalNum.setText(String.valueOf(allotTotalNum));
    }

    @OnClick(R.id.iv_putaway_scan_location)
    public void onViewClicked() {
        scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, new ScanQRCodeResultListener() {
            @Override
            public void scanSuccess(int requestCode, String result) {
                /**
                 * 重新扫描库位码的时候 将库位码是否有效的标识更改成false
                 */
                LogUitls.d("库位码扫码--->", result);
                //保存库位码
                locationCode = result;
                //设置库位码
                etGoalStorage.setText(locationCode);


                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", bean.getSummaryResults().getBillId());
                params.put("LocationNo", result);
                getPresenter().submitTransferOneStep(params);
            }
        });
    }

    @Override
    public void setLocationSelect() {
        etGoalStorage.setText(etGoalStorage.getText());
        etGoalStorage.setFocusable(true);
        etGoalStorage.setFocusableInTouchMode(true);
        etGoalStorage.requestFocus();
        etGoalStorage.findFocus();
        Selection.selectAll(etGoalStorage.getText());
    }
}
