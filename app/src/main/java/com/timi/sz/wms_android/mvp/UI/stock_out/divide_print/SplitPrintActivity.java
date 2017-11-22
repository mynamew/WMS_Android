package com.timi.sz.wms_android.mvp.UI.stock_out.divide_print;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.bluetooth.BlueToothInfo;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.SubmitBarcodeLotPickOutSplitEvent;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BARCODENO;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_DATECODE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_DESBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIALID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_ATTR;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SRCBILLTYPE;

/**
 * 拆分打码的界面
 * author: timi
 * create at: 2017/9/18 13:46
 */
public class SplitPrintActivity extends BaseActivity<SplitPrintView, SplitPrintPresenter> implements SplitPrintView {


    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.iv_print)
    ImageView ivPrint;
    @BindView(R.id.tv_barcode)
    TextView tvBarcode;
    @BindView(R.id.tv_lib_code)
    TextView tvLibCode;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.tv_batch)
    TextView tvBatch;
    @BindView(R.id.tv_current_num)
    TextView tvCurrentNum;
    @BindView(R.id.et_divide_num)
    EditText etDivideNum;
    @BindView(R.id.rlv_print)
    RecyclerView rlvPrint;
    @BindView(R.id.ll_print_device)
    LinearLayout llPrintDevice;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    private BaseRecyclerAdapter<BlueToothInfo> adapter;
    private List<BlueToothInfo> mDatas = new ArrayList<>();

    //bundle
    private int billId;
    private int srcBillType;
    private int destBillType;
    private int scanId;
    private String barcode;
    private String dateCode;
    private int materialId;
    private String materialAttribute;

    @Override
    public int setLayoutId() {
        return R.layout.activity_split_print;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        billId = getIntent().getIntExtra(OUT_STOCK_PRINT_BILLID, 0);
        srcBillType = getIntent().getIntExtra(OUT_STOCK_PRINT_SRCBILLTYPE, 0);
        destBillType = getIntent().getIntExtra(OUT_STOCK_PRINT_DESBILLTYPE, 0);
        scanId = getIntent().getIntExtra(OUT_STOCK_PRINT_SCANID, 0);
        barcode = getIntent().getStringExtra(OUT_STOCK_PRINT_BARCODENO);
        billId = getIntent().getIntExtra(OUT_STOCK_PRINT_BILLID, 0);
        materialId = getIntent().getIntExtra(OUT_STOCK_PRINT_MATERIALID, 0);
        materialAttribute = getIntent().getStringExtra(OUT_STOCK_PRINT_MATERIAL_ATTR);
        dateCode = getIntent().getStringExtra(OUT_STOCK_PRINT_DATECODE);
        setActivityTitle(getString(R.string.divide_barcode_tip));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public SplitPrintPresenter createPresenter() {
        return new SplitPrintPresenter(this);
    }

    @Override
    public SplitPrintView createView() {
        return this;
    }

    @Override
    public void submitBarcodeLotPickOutSplit(SubmitBarcodeLotPickOutSplitResult result) {
        ToastUtils.showShort(getString(R.string.submit_barcode_split_outstock_success));
        SubmitBarcodeLotPickOutSplitEvent event = new SubmitBarcodeLotPickOutSplitEvent(SubmitBarcodeLotPickOutSplitEvent.SUBMIT_BARCODE_SPLIT_SUCCESS);
        event.setResult(result);
        BaseMessage.post(event);
        /**
         * 如果选中 则打印条码 并且提交条码拆分 否则直接关闭
         */
        if (ivPrint.isSelected()) {
            printBarCode(result);
        }else {
            onBackPressed();
        }

    }

    @OnClick({R.id.rl_show_print, R.id.btn_commit, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_show_print:
                ivPrint.setSelected(!ivPrint.isSelected());
                llPrintDevice.setVisibility(ivPrint.isSelected() ? View.VISIBLE : View.GONE);
                /**
                 * 打印是否选中
                 */
                if (ivPrint.isSelected()) {
                    btnCommit.setText(R.string.print_and_commit);
                } else {
                    btnCommit.setText(R.string.btn_commit);
                }
                if (null == adapter) {
                    for (int i = 0; i < 10; i++) {
                        mDatas.add(new BlueToothInfo(i + "AASDSD", i % 2));
                    }
                    adapter = new BaseRecyclerAdapter<BlueToothInfo>(SplitPrintActivity.this, mDatas) {
                        @Override
                        protected int getItemLayoutId(int viewType) {
                            return R.layout.item_bluetooth_info;
                        }

                        @Override
                        protected void bindData(RecyclerViewHolder holder, int position, BlueToothInfo item) {
                            holder.setTextView(R.id.tv_bluetooth_name, item.name);
                            holder.setTextView(R.id.tv_states, item.states == 0 ? getString(R.string.have_connected_tip) : "");
                        }
                    };
                    rlvPrint.setAdapter(adapter);
                    rlvPrint.setLayoutManager(new LinearLayoutManager(SplitPrintActivity.this));
                } else {
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.btn_commit:

                /**
                 * 获取拆分数量
                 */
                int splitQty = Integer.parseInt(etDivideNum.getText().toString().trim());
                /**
                 * 拆分请求
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", billId);
                params.put("SrcBillType", srcBillType);
                params.put("DestBillType", destBillType);
                params.put("ScanId", scanId);
                params.put("BarcodeNo", barcode);
                params.put("SplitQty", splitQty);
                params.put("DateCode", dateCode);
                params.put("bCheckMode", false);
                params.put("MaterialId", materialId);
                params.put("MaterialAttribute", materialAttribute);
                getPresenter().submitBarcodeLotPickOutSplit(params);
                break;
            case R.id.btn_cancel:
                onBackPressed();
                break;
        }
    }

    /**
     * 打印条码
     * @param result
     */
    public void printBarCode(SubmitBarcodeLotPickOutSplitResult result) {

    }
}
