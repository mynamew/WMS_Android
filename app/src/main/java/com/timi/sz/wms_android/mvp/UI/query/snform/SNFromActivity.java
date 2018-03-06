package com.timi.sz.wms_android.mvp.UI.query.snform;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.query.request.SNRequsetBean;
import com.timi.sz.wms_android.bean.query.response.QueryBarcodeTracesResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * SN追溯查询
 * author: timi
 * create at: 2018/1/31 10:18
 */
public class SNFromActivity extends BaseActivity<SNFromView, SNFromPresenter> implements SNFromView {


    @BindView(R.id.tv_putaway_scan_location_tip)
    TextView tvPutawayScanLocationTip;
    @BindView(R.id.et_putaway_scan_location)
    EditText etPutawayScanLocation;
    @BindView(R.id.iv_putaway_scan_location)
    ImageView ivPutawayScanLocation;
    @BindView(R.id.tv_barcode_state)
    TextView tvBarcodeState;
    @BindView(R.id.tv_current_state)
    TextView tvCurrentState;
    @BindView(R.id.tv_in_pack)
    TextView tvInPack;
    @BindView(R.id.tv_code_type)
    TextView tvCodeType;
    @BindView(R.id.tv_in_lib)
    TextView tvInLib;
    @BindView(R.id.tv_in_lib_location)
    TextView tvInLibLocation;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_batch_date)
    TextView tvBatchDate;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.rlv_snfrom)
    RecyclerView rlvSnfrom;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;

    List<QueryBarcodeTracesResult.BarcodeTraacesDetailBean> mDatas = new ArrayList<>();
    BaseRecyclerAdapter<QueryBarcodeTracesResult.BarcodeTraacesDetailBean> adapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_snfrom;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("SN追溯查询");
    }

    @Override
    public void initView() {
        setEdittextListener(etPutawayScanLocation, Constants.EDITTEXT_BARCODE, R.string.scan_material_code, -1, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                etPutawayScanLocation.setText(result);
                SNRequsetBean bean = new SNRequsetBean();
                bean.setMAC(PackageUtils.getMac());
                bean.setOrgId(SpUtils.getInstance().getOrgId());
                bean.setUserId(SpUtils.getInstance().getUserId());
                bean.setBarCode(result);
                getPresenter().queryBarcodeTraces(bean);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public SNFromPresenter createPresenter() {
        return new SNFromPresenter(this);
    }

    @Override
    public SNFromView createView() {
        return this;
    }

    @Override
    public void queryBarcodeTraces(QueryBarcodeTracesResult o) {
        String barcodeSate = "";
        switch (o.getBarCodeStatus()) {
            case 0:
                barcodeSate = "未入库";
                break;
            case 1:
                barcodeSate = "在库";
                break;
            case 2:
                barcodeSate = "已出库";
                break;
            case 9:
                barcodeSate = "异常";
                break;
            default:
                barcodeSate = "异常";
                break;
        }
        //条码状态
        setTextViewContent(tvBarcodeState, barcodeSate);
        //当前数量
        setTextViewContent(tvCurrentState, o.getCurrentQty());
        //所在容器
        setTextViewContent(tvInPack, o.getParentBoxBarCode());
        //条码类型
        setTextViewContent(tvCodeType, o.getBarCodeType() == 0 ? "SN 条码" : "容器条码");
        //所在仓库
        setTextViewContent(tvInLib, o.getWareHouseName());
        //所在库位
        setTextViewContent(tvInLibLocation, o.getLocationCode());
        //物料代码
        setTextViewContent(tvMaterialCode, o.getMaterialCode());
        //物料名称
        setTextViewContent(tvMaterialName, o.getMaterialName());
        //批次日期
        setTextViewContent(tvBatchDate, o.getDateCode());
        //附属属性
        setTextViewContent(tvMaterialAttr, o.getMaterialAttribute());

        mDatas.addAll(o.getBarcodeTraacesDetail());
        LogUitls.e(mDatas.size());
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<QueryBarcodeTracesResult.BarcodeTraacesDetailBean>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_sn_from;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, QueryBarcodeTracesResult.BarcodeTraacesDetailBean item) {
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, position + 1);
                    setTextViewContent(holder.getTextView(R.id.tv_work_date), item.getCreationTime());
                    String inOutType = "";
                    switch (item.getInOutType()) {
                        case 0:
                            inOutType = "生成";
                            break;
                        case 1:
                            inOutType = "入库";
                            break;
                        case 2:
                            inOutType = "出库";
                            break;
                        case 7:
                            inOutType = "导入";
                            break;
                        case 8:
                            inOutType = "库内移动";
                            break;
                        case 9:
                            inOutType = "异常出库";
                            break;
                    }
                    setTextViewContent(holder.getTextView(R.id.in_out_type), inOutType);
                    setTextViewContent(holder.getTextView(R.id.tv_work_date), item.getCreationTime());
                    setTextViewContent(holder.getTextView(R.id.tv_orderno_code), item.getBillCode());
                    setTextViewContent(holder.getTextView(R.id.tv_from_orderno), item.getSourceBillType());
                    setTextViewContent(holder.getTextView(R.id.tv_to_orderno), item.getTargetBillType());
                    setTextViewContent(holder.getTextView(R.id.tv_orderno_code), item.getBillCode());
                    setTextViewContent(holder.getTextView(R.id.tv_location), item.getLocationCode());
                    setTextViewContent(holder.getTextView(R.id.tv_qty), item.getQty());
                }
            };
            rlvSnfrom.setAdapter(adapter);
            rlvSnfrom.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST,R.drawable.item_point_divider));
            rlvSnfrom.setLayoutManager(new LinearLayoutManager(this));
        } else adapter.notifyDataSetChanged();
    }

}
