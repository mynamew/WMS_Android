package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.scan_return_material;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

public class ScanReturnMaterialActivity extends BaseActivity<ScanReturnMaterialView, ScanReturnMaterialPresenter> implements ScanReturnMaterialView {
    @BindView(R.id.tv_scan_return_orderno)
    TextView tvScanReturnOrderno;
    @BindView(R.id.tv_scan_return_orderno_date)
    TextView tvScanReturnOrdernoDate;
    @BindView(R.id.ll_putaway_date)
    LinearLayout llPutawayDate;
    @BindView(R.id.tv_scan_return_orderno_from)
    TextView tvScanReturnOrdernoFrom;
    @BindView(R.id.tv_scan_return_orderno_buyer)
    TextView tvScanReturnOrdernoBuyer;
    @BindView(R.id.ll_putaway_arrive)
    LinearLayout llPutawayArrive;
    @BindView(R.id.tv_scan_return_orderno_pro_code)
    TextView tvScanReturnOrdernoProCode;
    @BindView(R.id.tv_scan_return_orderno_pro_name)
    TextView tvScanReturnOrdernoProName;
    @BindView(R.id.ll_putaway_in_stock)
    LinearLayout llPutawayInStock;
    @BindView(R.id.tv_scan_return_orderno_pro_model)
    TextView tvScanReturnOrdernoProModel;
    @BindView(R.id.tv_scan_return_orderno_pro_count)
    TextView tvScanReturnOrdernoProCount;
    @BindView(R.id.ll_putaway_total_num)
    LinearLayout llPutawayTotalNum;
    @BindView(R.id.tv_putaway_scan_material_tip)
    TextView tvPutawayScanMaterialTip;
    @BindView(R.id.tv_scan_return_material_material)
    TextView tvScanReturnMaterialMaterial;
    @BindView(R.id.iv_scan_return_material_scan)
    ImageView ivScanReturnMaterialScan;
    @BindView(R.id.tv_scan_return_orderno_have_returnnum)
    TextView tvScanReturnOrdernoHaveReturnnum;

    @Override
    public int setLayoutId() {
        return R.layout.activity_scan_return_material;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.buy_return_material_scan));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        /**
         * 上个界面传过来的数据
         */
        tvScanReturnOrderno.setText(String.format(getString(R.string.sip_order_num), "P123432"));
        tvScanReturnOrdernoDate.setText(String.format(getString(R.string.scan_return_orderno_date), "2017-8-12"));
        tvScanReturnOrdernoFrom.setText(String.format(getString(R.string.sip_buy_from), "深圳超然科技股份有限公司"));
        tvScanReturnOrdernoBuyer.setText(String.format(getString(R.string.sip_buyer), "邢力丰"));
        /**
         * 扫码出来的数据
         */
        tvScanReturnOrdernoProCode.setText(String.format(getString(R.string.stockin_point_pro_num), ""));
        tvScanReturnOrdernoProName.setText(String.format(getString(R.string.stockin_point_pro_name), ""));
        tvScanReturnOrdernoProModel.setText(String.format(getString(R.string.stockin_point_promodel), ""));
        tvScanReturnOrdernoProCount.setText(String.format(getString(R.string.stockin_point_buynum), ""));
        tvScanReturnOrdernoHaveReturnnum.setText(String.format(getString(R.string.scan_return_orderno_have_returnnum), "0"));
    }

    @Override
    public ScanReturnMaterialPresenter createPresenter() {
        return null;
    }

    @Override
    public ScanReturnMaterialView createView() {
        return null;
    }

    @OnClick({R.id.tv_scan_return_material_material, R.id.iv_scan_return_material_scan, R.id.btn_scan_material_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_scan_return_material_material://扫描
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION);
                break;
            case R.id.iv_scan_return_material_scan://扫描
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION);
                break;
            case R.id.btn_scan_material_commit://退料出库扫描 提交清点
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
                        LogUitls.d("物料码扫码--->", bundle.getString("result"));
                        /**
                         * 设置扫描返回结果
                         */
                        tvScanReturnMaterialMaterial.setText(bundle.getString("result"));
                        // TODO: 2017/8/29 进行网络请求 扫描结果进行保存 对物料信息保存到上面的textview上
                        /**
                         * 设置已退数量
                         */
                        tvScanReturnOrdernoHaveReturnnum.setText(String.format(getString(R.string.scan_return_orderno_have_returnnum), "1"));
                    }
                }
                break;
        }
    }
}
