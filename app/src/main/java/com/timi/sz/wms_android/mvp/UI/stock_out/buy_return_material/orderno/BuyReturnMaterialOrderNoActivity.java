package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.MaterialBean;
import com.timi.sz.wms_android.bean.outstock.buy.OrderNoBean;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_BUY_RETURN_ORDERNO_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;
/** 
  * 退料单
  * author: timi    
  * create at: 2017/9/1 15:40
  */  
public class BuyReturnMaterialOrderNoActivity extends BaseActivity<BuyReturnMaterialOrderNoView, BuyReturnMaterialOrderNoPresenter> implements BuyReturnMaterialOrderNoView,BaseActivity.ScanQRCodeResultListener {

    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_orderno_date)
    TextView tvOrdernoDate;
    @BindView(R.id.tv_return_material_num)
    TextView tvReturnMaterialNum;
    @BindView(R.id.tv_have_scan_num)
    TextView tvHaveScanNum;
    @BindView(R.id.tv_wait_scan_num)
    TextView tvWaitScanNum;
    @BindView(R.id.tv_material_scan)
    TextView tvReturnMaterialMaterialScan;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_num)
    TextView tvMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    private OrderNoBean orderBoBean = null;

    @Override
    public int setLayoutId() {
        return R.layout.activity_return_material_order_no;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.buy_return_material_orderno_title));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        orderBoBean = getIntent().getParcelableExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN);
        if (null != orderBoBean) {
            tvOrderno.setText(String.format(getString(R.string.order_no), orderBoBean.getOrderno()));
            tvOrdernoDate.setText(String.format(getString(R.string.orderno_date), orderBoBean.getDate()));
            tvHaveScanNum.setText(String.format(getString(R.string.have_scan_num)+ orderBoBean.getReturnHaveScanNum()));
            tvWaitScanNum.setText(String.format(getString(R.string.wait_scan_num)+ orderBoBean.getReturnWaitScanlNum()));
        }
    }

    @Override
    public BuyReturnMaterialOrderNoPresenter createPresenter() {
        return new BuyReturnMaterialOrderNoPresenter(this);
    }

    @Override
    public BuyReturnMaterialOrderNoView createView() {
        return this;
    }

    private MaterialBean currentBean = null;

    @Override
    public void materialScanResult(MaterialBean bean) {
        /**
         * 存储当前扫描的物料信息
         */
        currentBean = bean;
        /**
         * 扫描物料码 获取的数据
         */
        setTextViewText(tvMaterialCode, R.string.material_code, bean.getMaterialCode());
        setTextViewText(tvMaterialName, R.string.material_name, bean.getMaterialName());
        setTextViewText(tvMaterialModel, R.string.material_model, bean.getMaterialModel());
        setTextViewText(tvMaterialNum, R.string.material_num, bean.getMaterialBuyNum());

    }
    @Override
    public void orderNoAddMaterial() {
        /**
         * 提交审核的返回
         */
        orderBoBean.setReturnHaveScanNum(Integer.parseInt(orderBoBean.getReturnHaveScanNum()) + 1 + "");
        //设置文本
        setTextViewText(tvHaveScanNum,R.string.have_count_num,orderBoBean.getReturnHaveScanNum());

    }
    @OnClick({R.id.tv_material_scan, R.id.iv_material_scan, R.id.btn_commit_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_material_scan:
                /**
                 * 扫码
                 */
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL,this);
                break;
            case R.id.iv_material_scan:
                /**
                 * 扫码
                 */
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL,this);
                break;
            case R.id.btn_commit_check:
                if (Integer.parseInt(orderBoBean.getReturnHaveScanNum()) == Integer.parseInt(orderBoBean.getReturnTotalNum())) {
                    // TODO: 2017/8/29 需要斟酌文字
                    ToastUtils.showShort("已扫描完所有的物料");
                    return;
                }
                /**
                 * 网络请求 请求审核
                 */
                getPresenter().orderNoAddmaterialNetWork(currentBean.MaterialCode);
                break;
        }
    }

    /**
     * 扫描的返回
     * @param requestCode
     * @param result
     */
    @Override
    public void scanSuccess(int requestCode, String result) {
        LogUitls.e("物料码扫码--->", result);
        /**
         * 设置扫描返回结果
         */
        tvReturnMaterialMaterialScan.setText(result);
        /**
         * 扫物料码的网络请求
         */
        getPresenter().materialScanNetWork(result);
    }
}
