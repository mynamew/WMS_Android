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
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.mvp.UI.stock_in.point.StockInPointActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.PutAwayActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.CommonScanActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE;

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
                tvTitle.setText("入库-收货单");
                etSboInput.setHint("请输入收货单号或扫码查询");
                break;
            case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                tvSboTip.setText(String.format(getString(R.string.create_task_orderno),""));
                tvTitle.setText("产成品-选单");
                etSboInput.setHint("请输入产成品入库单单号或扫码查询");
                break;
            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                tvSboTip.setText("生产任务单");
                tvTitle.setText("产成品入库-生单");
                etSboInput.setHint("请输入生产任务单号或扫码查询");
                break;
            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单
                tvSboTip.setText("其他入库单");
                tvTitle.setText("其他入库-选单");
                etSboInput.setHint("请输入其他入库单号或扫码查询");
                break;
            case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）
                break;
            case Constants.OUT_RETURN_MATERAIL://委外退料
                tvSboTip.setText("委外退料单");
                tvTitle.setText("委外退料-选单");
                etSboInput.setHint("请输入委外退料单号或扫码查询");
                break;
            case Constants.CREATE_RETURN_MATERAIL://生产退料
                tvSboTip.setText("生产退料单");
                tvTitle.setText("生产退料-选单");
                etSboInput.setHint("请输入生产退料单号或扫码查询");
                break;
            case Constants.SALE_RETURN_MATERAIL://销售 退料
                tvSboTip.setText("销售退货单");
                tvTitle.setText("销售退货-选单");
                etSboInput.setHint("请输入销售退料单号或扫码查询");
                break;
        }
    }

    @Override
    public void initView() {
        etSboInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    InputMethodUtils.hide(SearchBuyOrderActivity.this);
                    String orderNum = etSboInput.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort("请输入单号");
                    } else {
                        /**
                         * 不同的intentcode  进行不同的跳转
                         */
                        Intent it = new Intent();
                        it.putExtra(Constants.CODE_STR, intentCode);
                        switch (intentCode) {
                            case Constants.BUY_ORDE_NUM://采购单
                                it.setClass(SearchBuyOrderActivity.this, StockInPointActivity.class);
                                break;
                            case Constants.BUY_SEND_NUM://送货单
                                it.setClass(SearchBuyOrderActivity.this, StockInPointActivity.class);
                                break;
                            case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）
                            case Constants.COME_MATERAIL_NUM://来料单
                            case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单
                            case Constants.OUT_RETURN_MATERAIL://委外退料
                            case Constants.CREATE_RETURN_MATERAIL://生产退料
                            case Constants.SALE_RETURN_MATERAIL://销售 退料
                                it.setClass(SearchBuyOrderActivity.this, PutAwayActivity.class);
                                break;
                        }
                        startActivity(it);
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
                        // TODO: 2017/8/18 请求单号接口返回信息  
                    }
                }
                break;
        }
    }

}
