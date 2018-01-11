package com.timi.sz.wms_android.mvp.UI.stock_in_work.pack_query;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Selection;
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
import com.timi.sz.wms_android.bean.stockin_work.stock_query.QueryStockContainerResult;
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
 * 库存查询
 * author: timi
 * create at: 2018/1/9 10:45
 */
public class PackQueryActivity extends BaseActivity<PackRepertoryView, PackRepertoryPresenter> implements PackRepertoryView {


    @BindView(R.id.tv_pack_code_tip)
    TextView tvPackCodeTip;
    @BindView(R.id.et_pack_code)
    EditText etPackCode;
    @BindView(R.id.iv_pack_scan)
    ImageView ivPackScan;
    @BindView(R.id.tv_material_type)
    TextView tvMaterialType;
    @BindView(R.id.tv_total_num)
    TextView tvTotalNum;
    @BindView(R.id.tv_pack_type)
    TextView tvPackType;
    @BindView(R.id.tv_pack_num)
    TextView tvPackNum;
    @BindView(R.id.rlv_pack_repertory)
    RecyclerView rlvPackRepertory;
    private BaseRecyclerAdapter<QueryStockContainerResult.DetailResultsBean> adapter;
    private List<QueryStockContainerResult.DetailResultsBean> mDatas;

    @Override
    public int setLayoutId() {
        return R.layout.activity_pack_query;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.container_repertory);
    }

    @Override
    public void initView() {
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
                params1.put("ContainerBarcode", result);
                getPresenter().queryStockContainer(params1);
            }
        });
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();

    }

    @Override
    public PackRepertoryPresenter createPresenter() {
        return new PackRepertoryPresenter(this);
    }

    @Override
    public PackRepertoryView createView() {
        return this;
    }

    @Override
    public void queryStockContainer(QueryStockContainerResult result) {
        QueryStockContainerResult.SummaryResultsBean summaryResults = result.getSummaryResults();
        //设置头部
        setHeaderContent(summaryResults.getMaterialCount(), summaryResults.getTotalCount(), summaryResults.getContainerType(), summaryResults.getContainerNo());
        //清空数据
        mDatas.clear();
        //加入数据
        mDatas.addAll(result.getDetailResults());
        /**
         * 初始化adapter
         */
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<QueryStockContainerResult.DetailResultsBean>(PackQueryActivity.this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_pack_repertory;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, QueryStockContainerResult.DetailResultsBean item) {
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, position + 1);
                    holder.setTextView(R.id.tv_batch_code, item.getLotNo());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode());
                    holder.setTextView(R.id.tv_qty, item.getQty());
                    holder.setTextView(R.id.tv_material_name, item.getMaterialName());
                    holder.setTextView(R.id.tv_material_model, item.getMaterialStandard());

                }
            };
            rlvPackRepertory.setAdapter(adapter);
            rlvPackRepertory.setLayoutManager(new LinearLayoutManager(PackQueryActivity.this));
            rlvPackRepertory.addItemDecoration(new DividerItemDecoration(PackQueryActivity.this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setMaterialEdittextSelect() {
        etPackCode.setText(etPackCode.getText());
        etPackCode.setFocusable(true);
        etPackCode.setFocusableInTouchMode(true);
        etPackCode.requestFocus();
        etPackCode.findFocus();
        Selection.selectAll(etPackCode.getText());
    }

    /**
     * 设置头部的信息
     *
     * @param materialTypeNum 物项数量
     * @param totalNum        总数量
     * @param containerType   容器类型
     * @param containerCode   容器号
     */
    public void setHeaderContent(int materialTypeNum, int totalNum, String containerType, String containerCode) {
        tvMaterialType.setText(String.valueOf(materialTypeNum));
        tvTotalNum.setText(String.valueOf(totalNum));
        tvPackType.setText(containerType);
        tvPackNum.setText(containerCode);
    }

    @OnClick(R.id.iv_pack_scan)
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
                params1.put("ContainerBarcode", result);
                getPresenter().queryStockContainer(params1);
            }
        });
    }
}
