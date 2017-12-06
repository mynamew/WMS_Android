package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.StockQueryEvent;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.material_repertory.MaterialRepertoryFragment;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.pack_repertory.PackRepertoryFragment;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyTabView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库存查询
 */
public class StockQueryActivity extends BaseActivity<StockQueryView, StockQueryPresenter> implements StockQueryView {
    @BindView(R.id.et_stock_query)
    EditText etStockQuery;
    @BindView(R.id.mytab_stock_query)
    MyTabView mytabStockQuery;

    private PackRepertoryFragment mPackFragment;
    private MaterialRepertoryFragment mMaterialFragment;

    /**
     * 是否是 库存查询的标识
     */
    private boolean isPackQuery = true;

    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_query;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.query_repertory_tip));
    }


    @Override
    public void initView() {
        mytabStockQuery.setTabOnclickListener(new MyTabView.TabClickListener() {
            @Override
            public void tab1Click(View view) {
                isPackQuery = true;
                etStockQuery.setHint(R.string.please_input_query_pack_code);

                /**
                 * 设置物品种类是否可见
                 */
                changeFragment(0);

                if (TextUtils.isEmpty(etStockQuery.getText().toString().trim())) {
                    return;
                }

                StockQueryEvent stockQueryEvent = new StockQueryEvent(StockQueryEvent.STOCK_QUERY_PACK_REPERTORY);
                stockQueryEvent.inputContent = etStockQuery.getText().toString().trim();
                BaseMessage.post(stockQueryEvent);
            }

            @Override
            public void tab2Click(View view) {
                isPackQuery = false;
                etStockQuery.setHint(R.string.please_input_scan_query_material_code);
                /**
                 * 设置物品种类是否可见
                 */
                changeFragment(1);

                if (TextUtils.isEmpty(etStockQuery.getText().toString().trim())) {
                    return;
                }
                StockQueryEvent stockQueryEvent = new StockQueryEvent(StockQueryEvent.STOCK_QUERY_MATERIAL_REPERTORY);
                stockQueryEvent.inputContent = etStockQuery.getText().toString().trim();
                BaseMessage.post(stockQueryEvent);
            }
        });
        /**
         * 初始化
         */
        changeFragment(0);
        etStockQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(StockQueryActivity.this);
                    String orderNum = etStockQuery.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        if (isPackQuery) {
                            ToastUtils.showShort(R.string.please_input_query_pack_code);
                        } else {
                            ToastUtils.showShort(R.string.please_input_scan_query_material_code);
                        }
                        return false;
                    }
                    /**
                     * 发送事件获取数据
                     */
                    if (isPackQuery) {
                        StockQueryEvent stockQueryEvent = new StockQueryEvent(StockQueryEvent.STOCK_QUERY_PACK_REPERTORY);
                        stockQueryEvent.inputContent = orderNum;
                        BaseMessage.post(stockQueryEvent);
                    } else {
                        StockQueryEvent stockQueryEvent = new StockQueryEvent(StockQueryEvent.STOCK_QUERY_MATERIAL_REPERTORY);
                        stockQueryEvent.inputContent = orderNum;
                        BaseMessage.post(stockQueryEvent);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }


    @Override
    public StockQueryPresenter createPresenter() {
        return new StockQueryPresenter(this);
    }

    @Override
    public StockQueryView createView() {
        return this;
    }

    /**
     * 切换清点和清点记录的碎片
     *
     * @param index
     */
    public void changeFragment(int index) {
        FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
        if (mPackFragment != null) trans.hide(mPackFragment);
        if (mMaterialFragment != null) trans.hide(mMaterialFragment);
        if (index == 0) {
            if (mPackFragment == null) {
                mPackFragment = new PackRepertoryFragment();
                trans.add(R.id.fl_content, mPackFragment);
            } else {
                trans.show(mPackFragment);
            }
        } else {
            if (mMaterialFragment == null) {
                mMaterialFragment = new MaterialRepertoryFragment();
                trans.add(R.id.fl_content, mMaterialFragment);
            } else {
                trans.show(mMaterialFragment);
            }
        }
        try {
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.iv_stock_query_scan)
    public void onViewClicked() {
        /**
         * 跳转扫描的界面
         */
        if (isPackQuery) {
            scan(Constants.REQUEST_SCAN_CODE_CONTAINER, new ScanQRCodeResultListener() {
                @Override
                public void scanSuccess(int requestCode, String result) {
                    StockQueryEvent stockQueryEvent = new StockQueryEvent(StockQueryEvent.STOCK_QUERY_PACK_REPERTORY);
                    stockQueryEvent.inputContent = result;
                    BaseMessage.post(stockQueryEvent);
                }
            });
        } else {
            scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                @Override
                public void scanSuccess(int requestCode, String result) {
                    StockQueryEvent stockQueryEvent = new StockQueryEvent(StockQueryEvent.STOCK_QUERY_MATERIAL_REPERTORY);
                    stockQueryEvent.inputContent = result;
                    BaseMessage.post(stockQueryEvent);
                }
            });
        }
    }
}
