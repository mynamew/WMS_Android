package com.timi.sz.wms_android.mvp.UI.stock_in_work.goods_query;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Selection;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.stockin_work.stock_query.MaterialQueryResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_LIB_LOATION;

/**
 * 物品查询
 * author: timi
 * create at: 2018/1/9 10:43
 */
public class GoodsQueryActivity extends BaseActivity<MaterialRepertoryView, MaterialRepertoryPresenter> implements MaterialRepertoryView {

    @BindView(R.id.tv_pack_code_tip)
    TextView tvPackCodeTip;
    @BindView(R.id.et_pack_code)
    EditText etPackCode;
    @BindView(R.id.iv_goods_scan)
    ImageView ivGoodsScan;
    @BindView(R.id.tv_repertort_total_num)
    TextView tvRepertortTotalNum;
    @BindView(R.id.tv_putaway_material_code)
    TextView tvPutawayMaterialCode;
    @BindView(R.id.tv_putaway_material_name)
    TextView tvPutawayMaterialName;
    @BindView(R.id.tv_putaway_material_nmodel)
    TextView tvPutawayMaterialNmodel;
    @BindView(R.id.tv_putaway_material_attr)
    TextView tvPutawayMaterialAttr;
    @BindView(R.id.rlv_material_repertory)
    RecyclerView rlvMaterialRepertory;
    private BaseRecyclerAdapter<MaterialQueryResult.DetailResultsBean> adapter;
    private List<MaterialQueryResult.DetailResultsBean> mDatas;

    @Override
    public int setLayoutId() {
        return R.layout.activity_goods_query;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.goods_repertory);
    }

    @Override
    public void initView() {
        mDatas = new ArrayList<>();
        setEdittextListener(etPackCode, REQUEST_SCAN_CODE_LIB_LOATION, R.string.please_input_query_pack_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 发起请求
                 */
                Map<String, Object> params1 = new HashMap<>();
                params1.put("UserId", SpUtils.getInstance().getUserId());
                params1.put("OrgId", SpUtils.getInstance().getOrgId());
                params1.put("MAC", PackageUtils.getMac());
                params1.put("MaterialBarcode", result);
                getPresenter().queryStockMaterial(params1);
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public MaterialRepertoryPresenter createPresenter() {
        return new MaterialRepertoryPresenter(this);
    }

    @Override
    public MaterialRepertoryView createView() {
        return this;
    }

    @Override
    public void queryStockMaterial(MaterialQueryResult result) {
        final MaterialQueryResult.SummaryResultsBean summaryResults = result.getSummaryResults();
        tvRepertortTotalNum.setText(String.valueOf(summaryResults.getTotalQty()));
        tvPutawayMaterialAttr.setText(TextUtils.isEmpty(summaryResults.getMaterialAttribute()) ? getString(R.string.none) : summaryResults.getMaterialAttribute());
        tvPutawayMaterialName.setText(TextUtils.isEmpty(summaryResults.getMaterialName()) ? getString(R.string.none) : summaryResults.getMaterialName());
        tvPutawayMaterialCode.setText(TextUtils.isEmpty(summaryResults.getMaterialCode()) ? getString(R.string.none) : summaryResults.getMaterialCode());
        tvPutawayMaterialNmodel.setText(TextUtils.isEmpty(summaryResults.getMaterialStandard()) ? getString(R.string.none) : summaryResults.getMaterialStandard());
        //数据源
        mDatas.clear();
        mDatas.addAll(result.getDetailResults());
        //初始化adapter
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<MaterialQueryResult.DetailResultsBean>(GoodsQueryActivity.this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_material_repertory;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, MaterialQueryResult.DetailResultsBean item) {
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, position + 1);
                    holder.setTextView(R.id.tv_batch, item.getLotNo());
                    holder.setTextView(R.id.tv_location_code, item.getLocationNo());
                    holder.setTextView(R.id.tv_material_num, item.getQty());
                }
            };
            rlvMaterialRepertory.setAdapter(adapter);
            rlvMaterialRepertory.setLayoutManager(new LinearLayoutManager(GoodsQueryActivity.this));
            rlvMaterialRepertory.addItemDecoration(new DividerItemDecoration(GoodsQueryActivity.this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setMaterialEdittextSelect() {
        etPackCode.setFocusable(true);
        etPackCode.setFocusableInTouchMode(true);
        etPackCode.requestFocus();
        etPackCode.findFocus();
        Selection.selectAll(etPackCode.getText());
    }

    @OnClick(R.id.iv_goods_scan)
    public void onViewClicked() {
        scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
            @Override
            public void scanSuccess(int requestCode, String result) {
                etPackCode.setText(result);
                /**
                 * 发起请求
                 */
                Map<String, Object> params1 = new HashMap<>();
                params1.put("UserId", SpUtils.getInstance().getUserId());
                params1.put("OrgId", SpUtils.getInstance().getOrgId());
                params1.put("MAC", PackageUtils.getMac());
                params1.put("MaterialBarcode", result);
                getPresenter().queryStockMaterial(params1);
            }
        });
    }
}
