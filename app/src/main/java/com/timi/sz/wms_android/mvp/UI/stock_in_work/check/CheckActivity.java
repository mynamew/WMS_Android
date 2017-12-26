package com.timi.sz.wms_android.mvp.UI.stock_in_work.check;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.MaterialDataBean;
import com.timi.sz.wms_android.bean.stockin_work.check.RequestSubmitCheckDataBean;
import com.timi.sz.wms_android.bean.stockin_work.check.SubmitCheckDataResult;
import com.timi.sz.wms_android.bean.stockin_work.query.PointResult;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.check_detail.CheckDetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.check_record.CheckRecordActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_IN_WORK_CODE_STR;

/**
 * 盘点
 * author: timi
 * create at: 2017/12/10 13:58
 */
public class CheckActivity extends BaseActivity<CheckView, CheckPresenter> implements CheckView {

    @BindView(R.id.tv_check_orderno)
    TextView tvCheckOrderno;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_outstock_total_num)
    TextView tvOutstockTotalNum;
    @BindView(R.id.tv_create_orderno_person)
    TextView tvCreateOrdernoPerson;
    @BindView(R.id.tv_item_total_num)
    TextView tvItemTotalNum;
    @BindView(R.id.tv_send_material_total_num)
    TextView tvSendMaterialTotalNum;
    @BindView(R.id.tv_wait_point_num)
    TextView tvWaitPointNum;
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.tv_material_code_tip)
    TextView tvMaterialCodeTip;
    @BindView(R.id.et_material_code)
    EditText etMaterialCode;
    @BindView(R.id.iv_can_material_code)
    ImageView ivCanMaterialCode;
    @BindView(R.id.tv_check_num_tip)
    TextView tvCheckNumTip;
    @BindView(R.id.et_check_num)
    EditText etCheckNum;
    @BindView(R.id.btn_confirm_qty)
    Button btnConfirmQty;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_num)
    TextView tvMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.tv_check_num)
    TextView tvCheckNum;
    private PointResult pointBean;

    @Override
    public int setLayoutId() {
        return R.layout.activity_check;
    }

    private int intentCode;

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.siw_check));
        intentCode = getIntent().getIntExtra(STOCK_IN_WORK_CODE_STR, 0);
        pointBean = new Gson().fromJson(getIntent().getStringExtra(Constants.STOCK_IN_WORK_BEAN), PointResult.class);
    }

    @Override
    public void initView() {

        setHeaderContent(pointBean.getBillCode(), pointBean.getBillDate(), pointBean.getCheckTypeName(), pointBean.getCreaterName(), pointBean.getItems(), pointBean.getQty(), pointBean.getWaitQty(), pointBean.getScanQty());
        setEdittextListener(etMaterialCode, REQUEST_SCAN_CODE_MATERIIAL, R.string.scan_material_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", pointBean.getBillId());
                params.put("BarcodeNo", result);
                getPresenter().getMaterialData(params);
            }
        });
    }

    /**
     * 设置头部的信息
     *
     * @param billCode
     * @param billDate
     * @param checkTypeName
     * @param createrName
     * @param items
     * @param qty
     * @param waitQty
     * @param scanQty
     */
    private void setHeaderContent(String billCode, String billDate, String checkTypeName, String createrName, int items, int qty, int waitQty, int scanQty) {
        tvCheckOrderno.setText(billCode);
        setTextViewContent(tvCheckOrderno, billCode);
        setTextViewContent(tvDate, billDate);
        setTextViewContent(tvOutstockTotalNum, checkTypeName);
        setTextViewContent(tvCreateOrdernoPerson, createrName);
        setTextViewContent(tvItemTotalNum, items);
        setTextViewContent(tvSendMaterialTotalNum, qty);
        setTextViewContent(tvWaitPointNum, waitQty);
        setTextViewContent(tvHaveCountNum, scanQty);
    }

    @Override
    public void initData() {

    }

    @Override
    public CheckPresenter createPresenter() {
        return new CheckPresenter(this);
    }

    @Override
    public CheckView createView() {
        return this;
    }


    @OnClick({R.id.btn_look_record, R.id.iv_can_material_code, R.id.btn_look_detail, R.id.btn_confirm_qty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_look_record:
                Intent intent = new Intent(CheckActivity.this, CheckRecordActivity.class);
                intent.putExtra(STOCK_IN_WORK_CODE_STR, intentCode);
                intent.putExtra(Constants.STOCK_IN_WORK_BILLID, pointBean.getBillId());
                startActivity(intent);
                break;
            case R.id.btn_look_detail:
                /**
                 * 查看详情
                 */
                Intent it = new Intent(CheckActivity.this, CheckDetailActivity.class);
                it.putExtra(STOCK_IN_WORK_CODE_STR, intentCode);
                it.putExtra(Constants.STOCK_IN_WORK_BILLID, pointBean.getBillId());
                startActivity(it);
                break;
            case R.id.btn_confirm_qty:
                String materialCode = etMaterialCode.getText().toString().trim();
                if (TextUtils.isEmpty(materialCode)) {
                    ToastUtils.showShort(getString(R.string.scan_material_code));
                    return;
                }
                String checkNumStr = etCheckNum.getText().toString().trim();
                if (TextUtils.isEmpty(checkNumStr)) {
                    ToastUtils.showShort(getString(R.string.please_input_check_num));
                    return;
                }
                //盘点数量
                int checkQty = Integer.parseInt(checkNumStr);
                if (checkQty <= 0) {
                    ToastUtils.showShort(R.string.check_qty_no_less_zero);
                    return;
                }

                RequestSubmitCheckDataBean bean = new RequestSubmitCheckDataBean();
                bean.setBillId(pointBean.getBillId());
                bean.setCheckQty(Integer.parseInt(checkNumStr));
                bean.setMAC(PackageUtils.getMac());
                bean.setOrgId(SpUtils.getInstance().getOrgId());
                bean.setUserId(SpUtils.getInstance().getUserId());
                String attr = tvMaterialAttr.getText().toString().trim();
                bean.setMaterialAttribute(attr.equals(getString(R.string.none)) ? "" : attr);
                bean.setMaterialCode(tvMaterialCode.getText().toString().trim());
                getPresenter().submitCheckData(bean);
                break;
            case R.id.iv_can_material_code:
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etMaterialCode.setText(result);
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", pointBean.getBillId());
                        params.put("BarcodeNo", result);
                        getPresenter().getMaterialData(params);
                    }
                });
                break;
        }
    }

    @Override
    public void getMaterialData(MaterialDataBean bean) {
        ToastUtils.showShort(getString(R.string.get_material_info_success));
        findViewById(R.id.ll_material_info).setVisibility(View.VISIBLE);
        /**
         * 设置物料信息
         */
        setTextViewContent(tvMaterialName, bean.getMaterialName());
        setTextViewContent(tvMaterialCode, bean.getMaterialCode());
        setTextViewContent(tvMaterialModel, bean.getMaterialStandard());
        setTextViewContent(tvMaterialAttr, bean.getMaterialAttribute());
        setTextViewContent(tvMaterialNum, bean.getQty());
        setTextViewContent(tvCheckNum, bean.getCheckQty());
        //设置物料的附加属性
        setMaterialAttrStatus(tvMaterialAttr);

    }

    @Override
    public void submitCheckData(SubmitCheckDataResult bean) {
        ToastUtils.showShort(getString(R.string.commit_check_data_success));
        tvCheckNum.setText(etCheckNum.getText().toString());
        setTextViewContent(tvWaitPointNum, pointBean.getQty() - bean.getTotalScanQty());
        setTextViewContent(tvHaveCountNum, bean.getTotalScanQty());
    }


}
