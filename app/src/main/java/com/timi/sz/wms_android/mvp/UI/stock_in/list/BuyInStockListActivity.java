package com.timi.sz.wms_android.mvp.UI.stock_in.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.list.QueryPoListBean;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.list.RequestBuyInStockListBean;
import com.timi.sz.wms_android.mvp.UI.stock_in.point.StockInPointActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.BUY_ORDE_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_ORDERNO;
import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_SUPPLIER;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_BUY_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_SOURCE;

/**
  * 采购单 委外单 列表
  * author: timi    
  * create at: 2017/12/7 17:21
  */  
public class BuyInStockListActivity extends BaseActivity<BuyInStockListView, BuyInStockListPresenter> implements BuyInStockListView {
    @BindView(R.id.tv_putaway_scan_location_tip)
    TextView tvPutawayScanLocationTip;
    @BindView(R.id.et_putaway_scan_location)
    EditText etPutawayScanLocation;
    @BindView(R.id.iv_putaway_scan_location)
    ImageView ivPutawayScanLocation;
    @BindView(R.id.tv_putaway_scan_material_tip)
    TextView tvPutawayScanMaterialTip;
    @BindView(R.id.et_putaway_scan_material)
    EditText etPutawayScanMaterial;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;

    private List<QueryPoListBean> mDatas;
    private BaseRecyclerAdapter<QueryPoListBean> adapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_buy_in_stock_list;
    }

    private int intentCode;

    @Override
    public void initBundle(Bundle savedInstanceState) {
        //获取跳转的code 值 用来显示不同的提示
        intentCode = getIntent().getIntExtra(Constants.CODE_STR, Constants.BUY_ORDE_NUM);
    }

    @Override
    public void initView() {
        switch (intentCode){
            case BUY_ORDE_NUM:
                setActivityTitle(getString(R.string.buy_list_title));
                break;
            case  OUT_SOURCE:
                setActivityTitle(getString(R.string.out_source_list_title));
                break;
        }
        /**
         * 输入单号
         */
        setEdittextListener(etPutawayScanLocation, EDITTEXT_ORDERNO, R.string.please_input_orderno_or_scan, R.string.input_orderno_more_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {

            }
        });
        /**
         * 输入供应商
         */
        setEdittextListener(etPutawayScanMaterial, EDITTEXT_SUPPLIER, R.string.please_input_supplier, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {

            }
        });
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
    }

    @Override
    public BuyInStockListPresenter createPresenter() {
        return new BuyInStockListPresenter(this);
    }

    @Override
    public BuyInStockListView createView() {
        return this;
    }

    @OnClick({R.id.iv_putaway_scan_location, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_putaway_scan_location:
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etPutawayScanLocation.setText(result);
                    }
                });
                break;
            case R.id.btn_search:
                InputMethodUtils.hide(this);
                String orderno = etPutawayScanLocation.getText().toString().trim();
                String supplier = etPutawayScanMaterial.getText().toString().trim();
                /**
                 * 增加判断  点击搜索的时候不能 供应商和单号都为空
                 */
                if (TextUtils.isEmpty(supplier) && TextUtils.isEmpty(orderno)) {
                    if (TextUtils.isEmpty(orderno)) {
                        ToastUtils.showShort(getString(R.string.please_input_orderno_or_scan));
                        return;
                    }
                    if (TextUtils.isEmpty(supplier)) {
                        ToastUtils.showShort(getString(R.string.please_input_supplier));
                        return;
                    }
                }
                RequestBuyInStockListBean requestBuyInStockListBean = new RequestBuyInStockListBean();
                requestBuyInStockListBean.setBillNo(orderno);
                requestBuyInStockListBean.setSupplierName(supplier);
                requestBuyInStockListBean.setUserId(SpUtils.getInstance().getUserId());
                requestBuyInStockListBean.setOrgId(SpUtils.getInstance().getOrgId());
                requestBuyInStockListBean.setMAC(PackageUtils.getMac());
                getPresenter().queryPOList(requestBuyInStockListBean,intentCode);
                break;
        }
    }

    @Override
    public void queryPOList(List<QueryPoListBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<QueryPoListBean>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_buy_list;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, QueryPoListBean item) {
                    holder.setTextView(R.id.tv_orderno, item.getBillCode());
                    holder.setTextView(R.id.tv_supplier, item.getSupplierName());
                    holder.setTextView(R.id.tv_date, item.getBillDate());
                }
            };
            rlvList.setAdapter(adapter);
            rlvList.setLayoutManager(new LinearLayoutManager(this));
            rlvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {

                    Map<String,Object> params=new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("MAC", PackageUtils.getMac());
                    params.put("BillCode", mDatas.get(pos).getBillCode());
                    /**
                     * 设置 BizType  采购单 11  委外单 12
                     */
                    if(intentCode==BUY_ORDE_NUM){
                        params.put("BizType",11);
                    }else {
                        params.put("BizType",12);
                    }
                    params.put("ScanId",0);
                    getPresenter().getPODataByCode(params);

                }
            });
        } else adapter.notifyDataSetChanged();
    }

    @Override
    public void BuyOrdernoBean(BuyOrdernoBean bean) {
        Intent it = new Intent(this, StockInPointActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_BUY_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }
}
