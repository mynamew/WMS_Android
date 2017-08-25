package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rmondjone.locktableview.LockTableView;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.mvp.base.BaseFragment;
import com.timi.sz.wms_android.view.MyDialog;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 15:36
 */

public class FragmentPoint extends BaseFragment<FragmentPointView,FragmentPointPresenter> implements FragmentPointView {
    @BindView(R.id.tv_sip_buy_num)
    TextView tvSipBuyNum;
    @BindView(R.id.tv_sip_buyer)
    TextView tvSipBuyer;
    @BindView(R.id.tv_sip_buy_from)
    TextView tvSipBuyFrom;
    @BindView(R.id.tv_sip_buy_date)
    TextView tvSipBuyDate;
    @BindView(R.id.ll_point_content)
    LinearLayout llPointContent;
    @Override
    public FragmentPointPresenter createPresenter() {
        return new FragmentPointPresenter(getActivity());
    }

    @Override
    public FragmentPointView createView() {
        return this;
    }

    @Override
    public void initData() {
        //设置初始数据  从上个界面传过来
        tvSipBuyNum.setText(String.format(getString(R.string.sip_order_num), "P14342143"));
        tvSipBuyDate.setText(String.format(getString(R.string.sip_buy_date), "2017-8-22"));
        tvSipBuyFrom.setText(String.format(getString(R.string.sip_buy_from), "深圳超然科技股份有限公司"));
        tvSipBuyer.setText(String.format(getString(R.string.sip_buyer), "邢力丰"));
        //构造假数据
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> mfristData = new ArrayList<String>();
        mfristData.add("行号");
        mfristData.add("物品编码");
        mfristData.add("采购数");
        mfristData.add("到货数");
        mfristData.add("入库数");
        mfristData.add("请点数");
        mfristData.add("备品数");
        mTableDatas.add(mfristData);
        for (int i = 0; i < 99; i++) {
            ArrayList<String> mRowDatas = new ArrayList<String>();
            mRowDatas.add("" + i);
            for (int j = 0; j < 6; j++) {
                mRowDatas.add("数据" + j);
            }
            mTableDatas.add(mRowDatas);
        }
        LockTableView mLockTableView = new LockTableView(getActivity(), llPointContent, mTableDatas);
        Log.e("表格加载开始", "当前线程：" + Thread.currentThread());
        mLockTableView.setLockFristColumn(false) //是否锁定第一列
                .setLockFristRow(true) //是否锁定第一行
                .setMaxColumnWidth(100) //列最大宽度
                .setMinColumnWidth(10) //列最小宽度
                .setMinRowHeight(5)//行最小高度
                .setMaxRowHeight(20)//行最大高度
                .setTextViewSize(12) //单元格字体大小
                .setFristRowBackGroudColor(R.color.table_head)//表头背景色
                .setTableHeadTextColor(R.color.beijin)//表头字体颜色
                .setTableContentTextColor(R.color.border_color)//单元格字体颜色
                .setNullableString("N/A") //空值替换值
                .setTableViewListener(new LockTableView.OnTableViewListener() {
                    @Override
                    public void onTableViewScrollChange(int x, int y) {
                        Log.e("滚动值", "[" + x + "]" + "[" + y + "]");
                    }

                    @Override
                    public void onTabViewClickListener(int position) {
                        showGoodsPointDialog();
                    }
                })//设置滚动回调监听
                .show(); //显示表格,此方法必须调用
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_stockin_point;
    }


    /**
     * 显示物品清点的弹出框
     */
    private MyDialog mPointDialog = null;

    public void showGoodsPointDialog() {
        if (null == mPointDialog) {
            mPointDialog = new MyDialog(getActivity(), R.layout.dialog_stockin_point)
                    .setTextViewContent(R.id.tv_stockin_point_pronum, String.format(getString(R.string.stockin_point_pro_num), "9.05.0022"))
                    .setTextViewContent(R.id.tv_stockin_point_proname, String.format(getString(R.string.stockin_point_pro_name), "滑轨双孔梁496-蓝色"))
                    .setTextViewContent(R.id.tv_stockin_point_promodel, String.format(getString(R.string.stockin_point_promodel), "Slide Beam0824-496铝挤压加工"))
                    .setTextViewContent(R.id.tv_stockin_point_buynum, String.format(getString(R.string.stockin_point_buynum), "50"))
                    .setTextViewContent(R.id.tv_stockin_point_receive_pro_num, String.format(getString(R.string.stockin_point_receive_pro_num), "50"))
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
                                ToastUtils.showShort(getActivity(), getString(R.string.please_input_point_num));
                                return;
                            }
                            //判断是否输入了备品的数量
                            if (TextUtils.isEmpty(spareNum)) {
                                ToastUtils.showShort(getActivity(), getString(R.string.please_input_spare_num));
                                return;
                            }
                            // TODO: 2017/8/24 网络请求 保存清点信息
                            dialog.dismiss();
                        }
                    }).setAnimation(R.style.popWindow_animation_push);
        }
        mPointDialog.show();
    }

}
