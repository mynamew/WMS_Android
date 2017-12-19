package com.timi.sz.wms_android.mvp.UI.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdInstockByInputResult;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdReturnByInputResult;
import com.timi.sz.wms_android.bean.list.RequestBuyInStockListBean;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.mvp.UI.stock_in.point.StockInPointActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.FinishedGoodsAuditPutAwayActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.OutMaterialReturnActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.ProductionMaterialReturnActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.batch_point_list.BatchPointListActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material.ScanReturnMaterialActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.normal_out_stock.NormalOutStockActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.BUY_ORDE_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.CREATE_PRO_CHECK_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.CREATE_PRO_CREATE_ORDER_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_ORDERNO;
import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_SUPPLIER;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_BUY_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_RETURN_MATERAIL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_SOURCE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_BUY_RETURN_ORDERNO_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_FINISH_GOODS_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_APPLY_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PURCHASE_MATERIAL_RETURN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;

/**
 * 采购单 委外单 列表
 * author: timi
 * create at: 2017/12/7 17:21
 */
public class BuyInStockListActivity extends BaseActivity<BuyInStockListView, BuyInStockListPresenter> implements BuyInStockListView {


    @BindView(R.id.tv_putaway_scan_location_tip)
    TextView tvPutawayScanLocationTip;
    @BindView(R.id.et_orderno)
    EditText etOrderno;
    @BindView(R.id.iv_putaway_scan_location)
    ImageView ivPutawayScanLocation;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.et_supplier)
    EditText etSupplier;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.rl_supplier)
    RelativeLayout rlSupplier;
    @BindView(R.id.tv_department_tip)
    TextView tvDepartmentTip;
    @BindView(R.id.et_department)
    EditText etDepartment;
    @BindView(R.id.rl_department)
    RelativeLayout rlDepartment;
    @BindView(R.id.tv_barcode_tip)
    TextView tvBarcodeTip;
    @BindView(R.id.et_barcode)
    EditText etBarcode;
    @BindView(R.id.rl_barcode)
    RelativeLayout rlBarcode;
    @BindView(R.id.tv_date_tip)
    TextView tvDateTip;
    @BindView(R.id.et_date)
    EditText etDate;
    @BindView(R.id.rl_date)
    RelativeLayout rlDate;
    private List<QueryPoListBean> mDatas;
    private List<View> views;
    private BaseRecyclerAdapter<QueryPoListBean> adapter;
    //单号   供应商
    public static final int ORDERNO_SUPPLIER = 0;
    //单号 供应商 日期
    public static final int ORDERNO_SUPPLIER_DATE = 1;
    //单号  日期 部门
    public static final int ORDERNO_DATE_DEPARTMENT = 2;
    //单号  条码
    public static final int ORDERNO_BARCODE = 3;
    //单号 日期 条码
    public static final int ORDERNO_DATE_BARCODE = 4;
    //单号 日期
    public static final int ORDERNO_DATE = 5;
    //单号
    public static final int ORDERNO = 6;
    //单号  部门
    public static final int ORDERNO_DEPARTMENT = 7;


    //当前的状态值
    private int currentUIState = ORDERNO;

    @Override
    public int setLayoutId() {
        return R.layout.activity_buy_in_stock_list;
    }

    private int intentCode = -1;

    @Override
    public void initBundle(Bundle savedInstanceState) {
        //获取跳转的code 值 用来显示不同的提示
        intentCode = getIntent().getIntExtra(Constants.CODE_STR, -1);
        //如果是出库
        if (intentCode == -1) {
            intentCode = getIntent().getIntExtra(Constants.STOCK_OUT_CODE_STR, -1);
        }
    }

    @Override
    public void initView() {
        mDatas = new ArrayList<>();
        views = new ArrayList<>();
        views.add(rlSupplier);
        views.add(rlDate);
        views.add(rlBarcode);
        views.add(rlDepartment);
        switch (intentCode) {
            /**
             * 入库
             */
            case BUY_ORDE_NUM:
                setActivityTitle(getString(R.string.buy_list_title));
                currentUIState = ORDERNO_SUPPLIER;
                break;
            case OUT_SOURCE:
                setActivityTitle(getString(R.string.out_source_list_title));
                currentUIState = ORDERNO_SUPPLIER;
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                setActivityTitle(getString(R.string.buy_return_orderno_list_title));
                currentUIState = ORDERNO_SUPPLIER;
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                setActivityTitle(R.string.outsource_allot_list);
                currentUIState = ORDERNO_SUPPLIER_DATE;
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外发料生单列表
                setActivityTitle(R.string.outsource_send_material_list);
                currentUIState = ORDERNO_SUPPLIER_DATE;
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外发料审核列表
                setActivityTitle(R.string.outsource_send_material_audit_list);
                currentUIState = ORDERNO_SUPPLIER;
                break;
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料列表
                setActivityTitle(R.string.outsource_feed_list);
                currentUIState = ORDERNO_SUPPLIER;
                break;
            case OUT_RETURN_MATERAIL://委外退料列表
                setActivityTitle(R.string.outsource_return_list);
                currentUIState = ORDERNO_SUPPLIER;
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产领料审核列表
                setActivityTitle(R.string.production_audit_list);
                currentUIState = ORDERNO_DEPARTMENT;
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产领料生单列表
                setActivityTitle(R.string.production_bill_list);
                currentUIState = ORDERNO_DATE_DEPARTMENT;
                break;
            case STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请单列表
                setActivityTitle(R.string.get_material_apply_list);
                currentUIState = ORDERNO_DATE_DEPARTMENT;
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨单列表
                setActivityTitle(R.string.production_allot_list);
                currentUIState = ORDERNO_DATE_DEPARTMENT;
                break;
            case Constants.CREATE_RETURN_MATERAIL://查询生产退料单列表
                setActivityTitle(R.string.production_return_list_title);
                currentUIState = ORDERNO_DEPARTMENT;
                break;
            case Constants.STOCK_OUT_PRODUCTION_FEEDING://查询生产补料单列表
                setActivityTitle(R.string.production_feed_list_title);
                currentUIState = ORDERNO_DEPARTMENT;
                break;
            case CREATE_PRO_CHECK_NUM://查询成品入库单列表（审核）
                setActivityTitle(R.string.production_instock_list_title);
                currentUIState = ORDERNO_DEPARTMENT;
                break;
            case CREATE_PRO_CREATE_ORDER_NUM://查询成品入库单列表（生单）
                setActivityTitle(R.string.production_instock_bill_list_title);
                currentUIState = ORDERNO_DATE_DEPARTMENT;
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://查询发货通知单列表（审核）
            case STOCK_OUT_FINISH_GOODS_PICK://查询发货通知单列表
                setActivityTitle(R.string.send_goods_message_list_title);
                currentUIState = ORDERNO_DATE;
                break;
            case STOCK_OUT_SELL_OUT_BILL://查询销售出库单列表（制单流程）列表(销售出库制单)
                setActivityTitle(R.string.sale_out_stock_list_title);
                currentUIState = ORDERNO;
                break;

        }
        //设置状态
        setUIState(currentUIState);
        /**
         * 输入单号
         */
        setEdittextListener(etOrderno, EDITTEXT_ORDERNO, R.string.please_input_orderno_or_scan, R.string.input_orderno_more_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                requestOrdernoData();
            }
        });
        /**
         * 输入供应商
         */
        setEdittextListener(etSupplier, EDITTEXT_SUPPLIER, R.string.please_input_supplier, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                requestOrdernoData();
            }
        });
    }

    @Override
    public void initData() {
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
                        etOrderno.setText(result);
                    }
                });
                break;
            case R.id.btn_search:
                String orderno = etOrderno.getText().toString();
                String supplier = etSupplier.getText().toString();
                String barcode = etBarcode.getText().toString();
                String date = etDate.getText().toString();
                String department = etDepartment.getText().toString();
                switch (currentUIState) {
                    case ORDERNO:
                        if (TextUtils.isEmpty(orderno)) {
                            ToastUtils.showShort(getString(R.string.please_input_orderno_or_scan));
                            return;
                        }
                        break;
                    case ORDERNO_DEPARTMENT:
                        if (TextUtils.isEmpty(orderno)&&TextUtils.isEmpty(department)) {
                            ToastUtils.showShort("请输入单号或部门至少一个！");
                            return;
                        }
                        break;
                    case ORDERNO_DATE:
                        if (TextUtils.isEmpty(orderno)&&TextUtils.isEmpty(date)) {
                            ToastUtils.showShort("请输入单号或日期至少一个！");
                            return;
                        }
                        break;
                    case ORDERNO_BARCODE:
                        if (TextUtils.isEmpty(orderno)&&TextUtils.isEmpty(barcode)) {
                            ToastUtils.showShort("请输入单号或条码至少一个！");
                            return;
                        }
                        break;
                    case ORDERNO_SUPPLIER:
                        if (TextUtils.isEmpty(orderno)&&TextUtils.isEmpty(supplier)) {
                            ToastUtils.showShort("请输入单号或供应少至少一个！");
                            return;
                        }
                        break;
                    case ORDERNO_DATE_BARCODE:
                        if (TextUtils.isEmpty(orderno)&&TextUtils.isEmpty(barcode)&&TextUtils.isEmpty(date)) {
                            ToastUtils.showShort("请输入单号或日期或条码至少一个！");
                            return;
                        }
                        break;
                    case ORDERNO_DATE_DEPARTMENT:
                        if (TextUtils.isEmpty(orderno)&&TextUtils.isEmpty(date)&&TextUtils.isEmpty(department)) {
                            ToastUtils.showShort("请输入单号或条码或部门至少一个");
                            return;
                        }
                        break;
                    case ORDERNO_SUPPLIER_DATE:
                        if (TextUtils.isEmpty(orderno)&&TextUtils.isEmpty(supplier)&&TextUtils.isEmpty(date)) {
                            ToastUtils.showShort("请输入单号或供应商或日期至少一个");
                            return;
                        }
                        break;
                }
                requestOrdernoData();
                break;
        }
    }
    /**
     * 发送请求
     */
    private void requestOrdernoData() {
        InputMethodUtils.hide(this);
        String orderno = etOrderno.getText().toString().trim();
        String supplier = etSupplier.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String department = etDepartment.getText().toString().trim();
        RequestBuyInStockListBean requestBuyInStockListBean = new RequestBuyInStockListBean();
        requestBuyInStockListBean.setBillNo(orderno);
        switch (intentCode) {
            /**
             * 入库
             */
            case BUY_ORDE_NUM:
            case OUT_SOURCE:
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                requestBuyInStockListBean.setSupplierName(supplier);
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                requestBuyInStockListBean.setSupplierName(supplier);
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外发料生单列表
                requestBuyInStockListBean.setSupplierName(supplier);
                requestBuyInStockListBean.setBillDate(date);
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外发料审核列表
                requestBuyInStockListBean.setSupplierName(supplier);
                break;
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料列表
                requestBuyInStockListBean.setSupplierName(supplier);
                break;
            case OUT_RETURN_MATERAIL://委外退料列表
                requestBuyInStockListBean.setSupplierName(supplier);
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产领料审核列表
                requestBuyInStockListBean.setDeptName(department);
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产领料生单列表
                requestBuyInStockListBean.setBillDate(date);
                requestBuyInStockListBean.setDeptName(department);
                break;
            case STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请单列表
                requestBuyInStockListBean.setDeptName(department);
                requestBuyInStockListBean.setBillDate(date);
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨单列表
                requestBuyInStockListBean.setBillDate(date);
                requestBuyInStockListBean.setDeptName(department);
                break;
            case Constants.STOCK_OUT_PRODUCTION_FEEDING://查询生产补料单列表
                requestBuyInStockListBean.setDeptName(department);
                break;
            case Constants.CREATE_RETURN_MATERAIL://查询生产退料单列表
                requestBuyInStockListBean.setDeptName(department);
                break;
            case CREATE_PRO_CHECK_NUM://查询成品入库单列表
                requestBuyInStockListBean.setSupplierName(supplier);
                break;
        }
        //请求
        requestBuyInStockListBean.setUserId(SpUtils.getInstance().getUserId());
        requestBuyInStockListBean.setOrgId(SpUtils.getInstance().getOrgId());
        requestBuyInStockListBean.setMAC(PackageUtils.getMac());
        getPresenter().queryPOList(requestBuyInStockListBean, intentCode);
    }

    @Override
    public void queryPOList(List<QueryPoListBean> datas) {
        if (null == datas || datas.isEmpty()) {
            ToastUtils.showShort(getString(R.string.dont_query_data_please_repeat_input));
            return;
        }

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

                    Map<String, Object> params = new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("MAC", PackageUtils.getMac());
                    switch (intentCode) {
                        case BUY_ORDE_NUM://采购单
                            params.put("BillCode", mDatas.get(pos).getBillCode());
                            params.put("BizType", 11);
                            params.put("ScanId", 0);
                            getPresenter().getPODataByCode(params);
                            break;
                        case OUT_SOURCE://委外单
                            params.put("BillCode", mDatas.get(pos).getBillCode());
                            params.put("BizType", 12);
                            params.put("ScanId", 0);
                            getPresenter().getPODataByCode(params);
                            break;
                        case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            getPresenter().getPurReturnData(params);
                            break;
                        case STOCK_OUT_OUTSOURCE_BILL://委外制单
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            params.put("DestBillType", 20);
                            getPresenter().getWWPickDataByOutSource(params);
                            break;
                        case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            params.put("DestBillType", 20);
                            getPresenter().getOutSourcePickData(params);
                            break;
                        case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            params.put("DestBillType", 50);
                            getPresenter().getWWPickDataByOutSource(params);
                            break;
                        case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            getPresenter().getOutSourceFeedData(params);
                            break;
                        case OUT_RETURN_MATERAIL://委外退料
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            getPresenter().getOutSourceReturnData(params);
                            break;
                        case STOCK_OUT_PRODUCTION_AUDIT://生产审核
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            getPresenter().getOutSourceReturnData(params);
                            break;
                        case STOCK_OUT_PRODUCTION_BILL://生产生单
                            params.put("DestBillType", 23);
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            getPresenter().getOutSourceReturnData(params);
                            break;
                        case STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            getPresenter().getOutSourceReturnData(params);
                            break;
                        case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            getPresenter().getOutSourceReturnData(params);
                            break;
                        case Constants.CREATE_RETURN_MATERAIL:
                            params.put("BillNo", mDatas.get(pos).getBillCode());
                            getPresenter().getPrdReturnData(params);
                            break;
                        case CREATE_PRO_CHECK_NUM://查询成品入库单列表
                            params.put("BillId", mDatas.get(pos).getBillId());
                            getPresenter().finishGoodInstock(params);
                            break;
                    }
                }
            });
        } else adapter.notifyDataSetChanged();
    }

    /**
     * 采购单
     *
     * @param bean
     */
    @Override
    public void BuyOrdernoBean(BuyOrdernoBean bean) {
        Intent it = new Intent(this, StockInPointActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_BUY_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    @Override
    public void getPurReturnData(BuyReturnMaterialByOrdernoData bean) {
        Intent intent = new Intent(this, ScanReturnMaterialActivity.class);
        intent.putExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN, new Gson().toJson(bean));
        startActivity(intent);
    }

    @Override
    public void getWWPickDataByOutSource(QueryWWPickDataByOutSourceResult bean) {
        QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResults = bean.getSummaryResults();
        /**
         * 一共分4 中情况
         * 1、不合并  非批次  拣货
         * 2、不合并   批次   拣货
         * 3、 合并   非批次  拣货
         * 4   合并    批次   拣货
         */
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (summaryResults.isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public void getOutSourcePickData(QueryOutSourcePickByInputResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        /**
         * 是否是批次拣货 ，如果是则跳转到 批次拣货的清单的界面 否则跳转到普通出库的界面
         */
        if (bean.getSummaryResults().isIsLotPick()) {
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        startActivity(intent);
    }

    @Override
    public void getOutSourceFeedData(QueryOutSourceFeedByInputResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 委外退料
     *
     * @param bean
     */
    @Override
    public void getOutSourceReturnData(QueryOutSourceReturnByInputResult bean) {
        Intent it = new Intent(this, OutMaterialReturnActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_OUT_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 领料申请
     *
     * @param bean
     */
    @Override
    public void getPrdPickApplyData(QueryProductPickByInputResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 生产审核
     *
     * @param bean
     */
    @Override
    public void getProductPickData(QueryWWPickDataByOutSourceResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 生产生单
     *
     * @param bean
     */
    @Override
    public void getPrdPickData(QueryWWPickDataByOutSourceResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public void getPrdInstockData(QueryPrdInstockByInputResult bean) {
        Intent it = new Intent(this, FinishedGoodsAuditPutAwayActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    @Override
    public void getPrdReturnData(QueryPrdReturnByInputResult bean) {
        Intent it = new Intent(this, ProductionMaterialReturnActivity.class);
        it.putExtra(Constants.CODE_STR, intentCode);
        it.putExtra(IN_STOCK_FINISH_BEAN, new Gson().toJson(bean));
        startActivity(it);
    }

    /**
     * 设置 UI状态
     *
     * @param states
     */
    public void setUIState(int states) {
        for (int i = 0; i < views.size(); i++) {
            views.get(i).setVisibility(View.GONE);
        }
        switch (states) {
            case ORDERNO://单号
                break;
            case ORDERNO_BARCODE://单号 条码
                rlBarcode.setVisibility(View.VISIBLE);
                break;
            case ORDERNO_DATE://单号 日期
                rlDate.setVisibility(View.VISIBLE);
                break;
            case ORDERNO_DEPARTMENT://单号 部门
                rlDepartment.setVisibility(View.VISIBLE);
                break;
            case ORDERNO_SUPPLIER://单号 供应商
                rlSupplier.setVisibility(View.VISIBLE);
                break;
            case ORDERNO_DATE_BARCODE://单号 日期 条码
                rlDate.setVisibility(View.VISIBLE);
                rlBarcode.setVisibility(View.VISIBLE);
                break;
            case ORDERNO_DATE_DEPARTMENT://单号 日期 部门
                rlDate.setVisibility(View.VISIBLE);
                rlDepartment.setVisibility(View.VISIBLE);
                break;
            case ORDERNO_SUPPLIER_DATE://单号 供应商  日期
                rlDate.setVisibility(View.VISIBLE);
                rlSupplier.setVisibility(View.VISIBLE);
                break;

        }
    }
}
