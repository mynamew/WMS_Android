package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Selection;
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

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_ORDERNO;

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
        BaseMessage.register(this);
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
        /**
         * 设置输入框的监听
         */
        setEdittextListener(etStockQuery, EDITTEXT_ORDERNO, isPackQuery ? R.string.please_input_scan_query_material_code : 0, R.string.input_orderno_more_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 发送事件获取数据
                 */
                if (isPackQuery) {
                    StockQueryEvent stockQueryEvent = new StockQueryEvent(StockQueryEvent.STOCK_QUERY_PACK_REPERTORY);
                    stockQueryEvent.inputContent = result;
                    BaseMessage.post(stockQueryEvent);
                } else {
                    StockQueryEvent stockQueryEvent = new StockQueryEvent(StockQueryEvent.STOCK_QUERY_MATERIAL_REPERTORY);
                    stockQueryEvent.inputContent = result;
                    BaseMessage.post(stockQueryEvent);
                }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
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
                    etStockQuery.setText(result);
                    StockQueryEvent stockQueryEvent = new StockQueryEvent(StockQueryEvent.STOCK_QUERY_PACK_REPERTORY);
                    stockQueryEvent.inputContent = result;
                    BaseMessage.post(stockQueryEvent);
                }
            });
        } else {
            scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                @Override
                public void scanSuccess(int requestCode, String result) {
                    etStockQuery.setText(result);
                    StockQueryEvent stockQueryEvent = new StockQueryEvent(StockQueryEvent.STOCK_QUERY_MATERIAL_REPERTORY);
                    stockQueryEvent.inputContent = result;
                    BaseMessage.post(stockQueryEvent);
                }
            });
        }
    }

    /**
     * 设置输入框 选中
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setEdittextSelect(StockQueryEvent event) {
        if (event.getEvent() == StockQueryEvent.STOCK_QUERY_EDITTEXT_SELECT) {
            etStockQuery.setFocusable(true);
            etStockQuery.setFocusableInTouchMode(true);
            etStockQuery.requestFocus();
            etStockQuery.findFocus();
            Selection.selectAll(etStockQuery.getText());
        }
    }

    /**
     * 获取输入框内容
     * @return
     */
    public String getEdittextContent() {
        return etStockQuery.getText().toString().trim();
    }
}
