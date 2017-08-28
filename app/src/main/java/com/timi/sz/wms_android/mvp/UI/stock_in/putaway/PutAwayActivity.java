package com.timi.sz.wms_android.mvp.UI.stock_in.putaway;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.CommonScanActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_LIB_LOATION;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

public class PutAwayActivity extends BaseActivity<PutAwayView, PutAwayPresenter> implements PutAwayView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_receive_pro_num)
    TextView tvReceiveProNum;
    @BindView(R.id.tv_create_orderno_date)
    TextView tvCreateordernoDate;
    @BindView(R.id.tv_arrive_pro_total_num)
    TextView tvArriveProTotalNum;
    @BindView(R.id.tv_right_pro_num)
    TextView tvRightProNum;
    @BindView(R.id.tv_in_stock_num)
    TextView tvInStockNum;
    @BindView(R.id.tv_can_in_stock_num)
    TextView tvCanInStockNum;
    @BindView(R.id.tv_wait_count_num)
    TextView tvWaitCountNum;
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.tv_putaway_material_code)
    TextView tvPutawayMaterialCode;
    @BindView(R.id.tv_putaway_material_num)
    TextView tvPutawayMaterialNum;
    @BindView(R.id.tv_putaway_material_name)
    TextView tvPutawayMaterialName;
    @BindView(R.id.tv_putaway_material_nmodel)
    TextView tvPutawayMaterialNmodel;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.ll_putaway_arrive)
    LinearLayout llPutawayArrive;
    @BindView(R.id.ll_putaway_in_stock)
    LinearLayout llPutawayInStock;
    @BindView(R.id.tv_in_stock_total_num)
    TextView tvInStockTotalNum;
    @BindView(R.id.ll_putaway_total_instock_num)
    LinearLayout llPutawayTotalInstockNum;
    @BindView(R.id.tv_putaway_orderno_num)
    TextView tvPutawayordernoNum;
    @BindView(R.id.ll_putaway_date)
    LinearLayout llPutawayDate;
    @BindView(R.id.ll_putaway_total_num)
    LinearLayout llPutawayTotalNum;
    @BindView(R.id.tv_putaway_have_scan_num)
    TextView tvPutawayHaveScanNum;
    @BindView(R.id.tv_putaway_scan_location)
    TextView tvPutawayScanLocation;
    @BindView(R.id.iv_putaway_scan_location)
    ImageView ivPutawayScanLocation;
    @BindView(R.id.tv_putaway_scan_material)
    TextView tvPutawayScanMaterial;
    @BindView(R.id.iv_putaway_scan_material)
    ImageView ivPutawayScanMaterial;
    /**
     * 默认是 入库来料单
     */
    private int intentCode = Constants.COME_MATERAIL_NUM;

    @Override
    public int setLayoutId() {
        return R.layout.activity_put_away;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(Constants.CODE_STR, Constants.COME_MATERAIL_NUM);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public PutAwayPresenter createPresenter() {
        return new PutAwayPresenter(this);
    }

    @Override
    public PutAwayView createView() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    /**
     * 设置数据
     */
    private void setData() {
        llPutawayArrive.setVisibility(View.GONE);
        llPutawayInStock.setVisibility(View.GONE);
        llPutawayTotalInstockNum.setVisibility(View.GONE);
        switch (intentCode) {
            case Constants.BUY_ORDE_NUM://采购单
                break;
            case Constants.BUY_SEND_NUM://送货单
                break;
            case Constants.COME_MATERAIL_NUM://来料单
                llPutawayArrive.setVisibility(View.VISIBLE);
                llPutawayInStock.setVisibility(View.VISIBLE);
                /**
                 * 标题
                 */
                tvTitle.setText(R.string.in_stock_num_title);
                /**
                 * 收货单号
                 */
                tvReceiveProNum.setText(String.format(getString(R.string.in_stock_num), "P12345432422"));
                /**
                 * 到货数
                 */
                tvArriveProTotalNum.setText(String.format(getString(R.string.arrive_pro_total_num), "1000"));
                /**
                 * 合格总数
                 */
                tvRightProNum.setText(String.format(getString(R.string.right_pro_num), "800"));
                /**
                 * 已入库总数
                 */
                tvInStockNum.setText(String.format(getString(R.string.in_stock_num), "300"));
                /**
                 * 可入库总数
                 */
                tvCanInStockNum.setText(String.format(getString(R.string.can_in_stock_num), "700"));
                /**
                 * 设置按钮的文字
                 */
                btnLogin.setText(R.string.putaway_create_instock_orderno);
                break;
            case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(R.string.product_warehous_warrent_num_title);
                /**
                 * 产成品入库单：
                 */
                tvReceiveProNum.setText(String.format(getString(R.string.product_warehous_warrent_num), "P12345432422"));
                /**
                 * 待点总数
                 */
                tvInStockTotalNum.setText(String.format(getString(R.string.in_stock_total_num), "1200"));
                break;
            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(R.string.create_task_orderno_title);
                /**
                 * 生产任务单：
                 */
                tvReceiveProNum.setText(String.format(getString(R.string.create_task_orderno), "P12345432422"));
                /**
                 * 待点总数
                 */
                tvInStockTotalNum.setText(String.format(getString(R.string.in_stock_total_num), "1200"));
                /**
                 * 已入库总数
                 */
                tvInStockNum.setText(String.format(getString(R.string.in_stock_num), "300"));
                /**
                 * 可入库总数
                 */
                tvCanInStockNum.setText(String.format(getString(R.string.can_in_stock_num), "700"));
                /**
                 * 单据总数
                 */
                tvPutawayordernoNum.setVisibility(View.VISIBLE);
                tvPutawayordernoNum.setText(String.format(getString(R.string.putaway_orderno_num), "100"));
                /**
                 * 设置按钮的文字
                 */
                btnLogin.setText(R.string.putaway_create_orderno);
                break;
            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单
                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(R.string.other_instock_orderno_title);
                /**
                 * 其他入库单：
                 */
                tvReceiveProNum.setText(String.format(getString(R.string.other_instock_orderno), "P12345432422"));
                /**
                 * 待点总数
                 */
                tvInStockTotalNum.setText(String.format(getString(R.string.in_stock_total_num), "1200"));

                break;
            case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）
                tvTitle.setText(getString(R.string.other_scan_title));
                findViewById(R.id.view_line).setVisibility(View.GONE);
                /**
                 * 全部设置成不可见
                 */
                llPutawayTotalInstockNum.setVisibility(View.GONE);
                llPutawayInStock.setVisibility(View.GONE);
                llPutawayArrive.setVisibility(View.GONE);
                llPutawayDate.setVisibility(View.GONE);
                llPutawayTotalNum.setVisibility(View.GONE);
                /**
                 * 已扫的总数
                 */
                tvPutawayHaveScanNum.setVisibility(View.VISIBLE);
                tvPutawayHaveScanNum.setText(String.format(getString(R.string.putaway_have_scan_num), "200"));
                break;
            case Constants.OUT_RETURN_MATERAIL://委外退料
                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.out_return_material_title));
                /**
                 * 委外退料单：
                 */
                tvReceiveProNum.setText(String.format(getString(R.string.out_return_material_orderno), "P12345432422"));
                /**
                 * 待点总数
                 */
                tvInStockTotalNum.setText(String.format(getString(R.string.in_stock_total_num), "1200"));

                break;
            case Constants.CREATE_RETURN_MATERAIL://生产退料
                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.create_return_material_title));
                /**
                 * 生产退料单号：
                 */
                tvReceiveProNum.setText(String.format(getString(R.string.create_return_material_orderno), "P12345432422"));
                /**
                 * 待点总数
                 */
                tvInStockTotalNum.setText(String.format(getString(R.string.in_stock_total_num), "1200"));
                break;
            case Constants.SALE_RETURN_MATERAIL://销售 退料
                llPutawayTotalInstockNum.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.sale_return_material_title));
                /**
                 * 生产退料单：
                 */
                tvReceiveProNum.setText(String.format(getString(R.string.sale_return_material_orderno), "P12345432422"));
                /**
                 * 待点总数
                 */
                tvInStockTotalNum.setText(String.format(getString(R.string.in_stock_total_num), "1200"));

                break;
        }
        /**
         * 日期
         */
        tvCreateordernoDate.setText(String.format(getString(R.string.create_orderno_date), "2017-8-29"));
        /**
         * 待点总数
         */
        tvWaitCountNum.setText(String.format(getString(R.string.wait_count_num), "1000"));
        /**
         * 已点总数
         */
        tvHaveCountNum.setText(String.format(getString(R.string.have_count_num), "200"));
        /**
         * 将底部的物料信息置空
         */
        tvPutawayMaterialCode.setText(String.format(getString(R.string.putaway_material_code), ""));
        tvPutawayMaterialName.setText(String.format(getString(R.string.putaway_material_name), ""));
        tvPutawayMaterialNmodel.setText(String.format(getString(R.string.putaway_material_nmodel), ""));
        tvPutawayMaterialNum.setText(String.format(getString(R.string.putaway_material_num), ""));

    }

    @OnClick({ R.id.tv_putaway_scan_location, R.id.iv_putaway_scan_location, R.id.tv_putaway_scan_material, R.id.iv_putaway_scan_material, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_putaway_scan_location://目的库位码
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION);
                break;
            case R.id.iv_putaway_scan_location://目的库位码
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION);
                break;
            case R.id.tv_putaway_scan_material://物料码
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL);
                break;
            case R.id.iv_putaway_scan_material://物料码
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL);
                break;
            case R.id.btn_login://确认提交
                /**
                 * 不同的code  调用不同的接口进行提交操作
                 */
                switch (intentCode) {
                    case Constants.BUY_ORDE_NUM://采购单
                        break;
                    case Constants.BUY_SEND_NUM://送货单
                        break;
                    case Constants.COME_MATERAIL_NUM://来料单
                        break;
                    case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                        break;
                    case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                        break;
                    case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单
                        break;
                    case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）
                        break;
                    case Constants.OUT_RETURN_MATERAIL://委外退料
                        break;
                    case Constants.CREATE_RETURN_MATERAIL://生产退料
                        break;
                    case Constants.SALE_RETURN_MATERAIL://销售 退料
                        break;
                }
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
                        tvPutawayScanMaterial.setText(bundle.getString("result"));
                    }
                }
                break;
            case REQUEST_SCAN_CODE_LIB_LOATION:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        LogUitls.d("库位码扫码--->", bundle.getString("result"));
                        tvPutawayScanLocation.setText(bundle.getString("result"));
                    }
                }
                break;
        }
    }
    public void  scan(int requestCode){
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
            startActivityForResult(intent, requestCode);
        }
    }
}
