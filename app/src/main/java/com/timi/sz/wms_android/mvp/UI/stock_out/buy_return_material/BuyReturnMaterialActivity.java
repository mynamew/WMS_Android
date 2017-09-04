package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.OrderNoBean;
import com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material.ScanReturnMaterialActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno.BuyReturnMaterialOrderNoActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_BUY_RETURN_ORDERNO_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_RETURN_MATERIAL;

public class BuyReturnMaterialActivity extends BaseActivity<BuyReturnMaterialView, BuyReturnMaterialPresenter> implements BuyReturnMaterialView {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.tv_buy_return_material_tip)
    TextView tvBuyReturnMaterialTip;
    @BindView(R.id.tv_putaway_scan_material_tip)
    TextView tvPutawayScanMaterialTip;
    @BindView(R.id.tv_putaway_scan_material)
    TextView tvPutawayScanMaterial;
    @BindView(R.id.iv_putaway_scan_material)
    ImageView ivPutawayScanMaterial;
    @BindView(R.id.tv_buy_return_material_orderno)
    TextView tvBuyReturnMaterialOrderno;
    @BindView(R.id.et_buy_return_material_orderno)
    EditText etBuyReturnMaterialOrderno;
    @BindView(R.id.iv_buy_return_material_orderno)
    ImageView ivBuyReturnMaterialOrderno;

    @Override
    public int setLayoutId() {
        return R.layout.activity_buy_return_material;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.out_stock_buy_return_material));
    }

    @Override
    public void initView() {
          etBuyReturnMaterialOrderno.setOnEditorActionListener(new TextView.OnEditorActionListener() {
              @Override
              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                  if(actionId== EditorInfo.IME_ACTION_SEARCH){
                      /**
                       * 输入的内容
                       */
                      String inputStr = etBuyReturnMaterialOrderno.getText().toString().trim();
                      if(TextUtils.isEmpty(inputStr)){
                          ToastUtils.showShort(getString(R.string.please_input_return_material_orderno_or_scan));
                      }else {
                          /**
                           * 退料单号的 网络请求
                           */
                          getPresenter().returnMaterialScanNetWork(inputStr);
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
    public BuyReturnMaterialPresenter createPresenter() {
        return new BuyReturnMaterialPresenter(this);
    }

    @Override
    public BuyReturnMaterialView createView() {
        return this;
    }

    @OnClick({R.id.tv_putaway_scan_material, R.id.iv_putaway_scan_material, R.id.et_buy_return_material_orderno, R.id.iv_buy_return_material_orderno})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_putaway_scan_material://扫物料码的点击事件
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL);
                break;
            case R.id.iv_putaway_scan_material://扫物料码的点击事件
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL);
                break;
            case R.id.et_buy_return_material_orderno://退料单号
                break;
            case R.id.iv_buy_return_material_orderno://退料单号
                scan(REQUEST_SCAN_CODE_RETURN_MATERIAL);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_SCAN_CODE_MATERIIAL:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        LogUitls.e("物料码扫码--->", bundle.getString("result"));
                        /**
                         * 设置扫描返回结果
                         */
                        tvPutawayScanMaterial.setText(bundle.getString("result"));
                        /**
                         * 扫物料码的网络请求
                         */
                         getPresenter().materialScanNetWork(bundle.getString("result"));
                    }
                }
                break;
            case REQUEST_SCAN_CODE_RETURN_MATERIAL:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        LogUitls.e("物料码扫码--->", bundle.getString("result"));
                        /**
                         * 设置扫描返回结果
                         */
                        etBuyReturnMaterialOrderno.setText(bundle.getString("result"));
                        /**
                         * 退料单号的 网络请求
                         */
                        getPresenter().returnMaterialScanNetWork(bundle.getString("result"));
                    }
                }
                break;
        }
    }
    @Override
    public void materialScanResult(BuyReturnMaterialOrdernoBean bean) {
        Intent intent=new Intent(this,ScanReturnMaterialActivity.class);
        intent.putExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN,bean);
        startActivity(intent);
    }

    @Override
    public void ReturnMaterialOrderNoScanResult(OrderNoBean bean) {
        Intent intent=new Intent(this,BuyReturnMaterialOrderNoActivity.class);
        intent.putExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN,bean);
        startActivity(intent);
    }
}
