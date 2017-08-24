package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.mvp.UI.stock_in.StockInActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 入库清点
 * author: timi
 * create at: 2017/8/18 20:58
 */
public class StockInPointActivity extends BaseActivity<StockInPointView, StockInPointPresenter> implements StockInPointView {

    @BindView(R.id.rd_stock_in_point)
    RadioButton rdStockInPoint;
    @BindView(R.id.rd_stock_in_record)
    RadioButton rdStockInRecord;
    @BindView(R.id.tv_sip_buy_num)
    TextView tvSipBuyNum;
    @BindView(R.id.tv_sip_buy_date)
    TextView tvSipBuyDate;
    @BindView(R.id.tv_sip_buy_from)
    TextView tvSipBuyFrom;
    @BindView(R.id.tv_sip_buyer)
    TextView tvSipBuyer;
    @BindView(R.id.tab_stockin_point)
    TabLayout tabStockinPoint;
    private String[] titles;
    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in_point;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("收货-物品清点");
        //设置默认物品清点
        rdStockInPoint.setChecked(true);
        //设置初始数据  从上个界面传过来
        tvSipBuyNum.setText(String.format(getString(R.string.sip_order_num), "P14342143"));
        tvSipBuyDate.setText(String.format(getString(R.string.sip_buy_date), "2017-8-22"));
        tvSipBuyFrom.setText(String.format(getString(R.string.sip_buy_from), "深圳超然科技股份有限公司"));
        tvSipBuyNum.setText(String.format(getString(R.string.sip_order_num), "邢力丰"));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        titles = new String[]{getString(R.string.goods_point), getString(R.string.point_record)};
        tabStockinPoint.addTab(tabStockinPoint.newTab().setText(titles[0]));
        tabStockinPoint.addTab(tabStockinPoint.newTab().setText(titles[1]));
        tabStockinPoint.setTabMode(TabLayout.MODE_FIXED);//设置标签的模式,默认系统模式
        tabStockinPoint.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ToastUtils.showShort(StockInPointActivity.this,"点击了"+tab.getText());
                if(tab.getText().equals(titles[0])){//物品清点

                }else{//清点记录

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                showGoodsPointDialog();
            }
        });
    }

    @Override
    public StockInPointPresenter createPresenter() {
        return new StockInPointPresenter(this);
    }

    @Override
    public StockInPointView createView() {
        return this;
    }

    /**
     * 显示物品清点的弹出框
     */
    private MyDialog mPointDialog = null;
    public void showGoodsPointDialog(){
        if (null == mPointDialog) {
            mPointDialog = new MyDialog(this, R.layout.dialog_stockin_point)
                    .setTextViewContent(R.id.tv_stockin_point_pronum, String.format(getString(R.string.stockin_point_pro_num),"9.05.0022"))
                    .setTextViewContent(R.id.tv_stockin_point_proname, String.format(getString(R.string.stockin_point_pro_name),"滑轨双孔梁496-蓝色"))
                    .setTextViewContent(R.id.tv_stockin_point_promodel,String.format(getString(R.string.stockin_point_promodel),"Slide Beam0824-496铝挤压加工"))
                    .setTextViewContent(R.id.tv_stockin_point_buynum, String.format(getString(R.string.stockin_point_buynum),"50"))
                    .setTextViewContent(R.id.tv_stockin_point_receive_pro_num, String.format(getString(R.string.stockin_point_receive_pro_num),"50"))
                    .setButtonListener(R.id.btn_stockin_point_cancel, getString(R.string.cancel), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.btn_stockin_point_save, getString(R.string.save), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            String pointNum = dialog.getEdittext(R.id.et_stockin_point_pro_point_num).getText().toString();
                            String spareNum = dialog.getEdittext(R.id.et_stockin_point_sparenum).getText().toString();
                            //判断是否输入了清点的数量
                            if (TextUtils.isEmpty(pointNum)) {
                                ToastUtils.showShort(StockInPointActivity.this, getString(R.string.please_input_point_num));
                                return;
                            }
                            //判断是否输入了备品的数量
                            if (TextUtils.isEmpty(spareNum)) {
                                ToastUtils.showShort(StockInPointActivity.this, getString(R.string.please_input_spare_num));
                                return;
                            }
                            // TODO: 2017/8/24 网络请求 保存清点信息
                            dialog.dismiss();
                        }
                    });
        }
        mPointDialog.show();
    }
}
