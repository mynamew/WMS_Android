package com.timi.sz.wms_android.mvp.UI.stock_in.query;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.CommonScanActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE;

public class SearchBuyOrderActivity extends BaseActivity<SearchBuyOrderView, SearchBuyOrderPresenter> implements SearchBuyOrderView {
    @BindView(R.id.tv_sbo_tip)
    TextView tvSboTip;
    @BindView(R.id.et_sbo_input)
    EditText etSboInput;

    @Override
    public int setLayoutId() {
        return R.layout.activity_search_buy_order;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("订单查询");
    }

    @Override
    public void initView() {
        etSboInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP){
                    InputMethodUtils.hide(SearchBuyOrderActivity.this);
                    //// TODO: 2017/8/18 请求单号接口返回信息 
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
