package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rmondjone.locktableview.LockTableView;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.PointMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.mvp.base.BaseFragment;
import com.timi.sz.wms_android.qrcode.utils.Constant;
import com.timi.sz.wms_android.view.MyDialog;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * $dsc  物料清点
 * author: timi
 * create at: 2017-08-25 15:36
 */

public class FragmentPoint extends BaseFragment<FragmentPointView, FragmentPointPresenter> implements FragmentPointView {
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

    private BuyOrdernoBean mBuyBean;
    private SendOrdernoBean mSendBean;

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
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_stockin_point;
    }

    @Override
    public void onResume() {
        super.onResume();
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

        Intent it = ((StockInPointActivity) getActivity()).getIntentCode();
        int intentCode = it.getIntExtra(Constants.CODE_STR, Constants.COME_MATERAIL_NUM);
        if (intentCode == Constants.BUY_ORDE_NUM) {
            mBuyBean = it.getParcelableExtra(Constants.IN_STOCK_BUY_BEAN);
            /**
             * 设置初始数据
             */
            setTextViewText(tvSipBuyNum, R.string.order_no, mBuyBean.num);
            setTextViewText(tvSipBuyDate, R.string.buy_date, mBuyBean.date);
            setTextViewText(tvSipBuyFrom, R.string.buy_from, mBuyBean.from);
            setTextViewText(tvSipBuyer, R.string.buyer, mBuyBean.buyer);
            /**
             * 存储下方列表的数据
             */
            for (int i = 0; i < mBuyBean.data.size(); i++) {
                ArrayList<String> mRowDatas = new ArrayList<String>();
                BuyOrdernoBean.MarterialBean marterialBean = mBuyBean.data.get(i);
                mRowDatas.add(marterialBean.materialCode);
                mRowDatas.add(marterialBean.buyNum);
                mRowDatas.add(marterialBean.arriveNum+"");
                mRowDatas.add(marterialBean.inStockNum);
                mRowDatas.add(marterialBean.pointNum);
                mRowDatas.add(marterialBean.spareNum);
                mTableDatas.add(mRowDatas);
            }
        } else {
            mSendBean = it.getParcelableExtra(Constants.IN_STOCK_SEND_BEAN);
            /**
             * 设置初始数据
             */
            setTextViewText(tvSipBuyNum, R.string.order_no, mSendBean.num);
            setTextViewText(tvSipBuyDate, R.string.buy_date, mSendBean.date);
            setTextViewText(tvSipBuyFrom, R.string.buy_from, mSendBean.from);
            setTextViewText(tvSipBuyer, R.string.buyer, mSendBean.buyer);
            /**
             * 存储下方列表的数据
             */
            for (int i = 0; i < mSendBean.data.size(); i++) {
                ArrayList<String> mRowDatas = new ArrayList<String>();
                SendOrdernoBean.MarterialBean marterialBean = mSendBean.data.get(i);
                mRowDatas.add(marterialBean.materialCode);
                mRowDatas.add(marterialBean.buyNum);
                mRowDatas.add(marterialBean.arriveNum);
                mRowDatas.add(marterialBean.inStockNum);
                mRowDatas.add(marterialBean.pointNum);
                mRowDatas.add(marterialBean.spareNum);
                mTableDatas.add(mRowDatas);
            }
        }
        LockTableView mLockTableView = new LockTableView(getActivity(), llPointContent, mTableDatas);
        Log.e("表格加载开始", "当前线程：" + Thread.currentThread());
        mLockTableView.setLockFristColumn(true) //是否锁定第一列
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
                        showGoodsPointDialog(position);
                    }
                })//设置滚动回调监听
                .show(); //显示表格,此方法必须调用
    }

    /**
     * 显示物品清点的弹出框
     */
    private MyDialog mPointDialog = null;

    public void showGoodsPointDialog(final int position) {
        if (null == mPointDialog) {
            mPointDialog = new MyDialog(getActivity(), R.layout.dialog_stockin_point)
                    .setTextViewContent(R.id.tv_stockin_point_pronum, String.format(getString(R.string.material_code), "9.05.0022"))
                    .setTextViewContent(R.id.tv_stockin_point_proname, String.format(getString(R.string.material_name), "滑轨双孔梁496-蓝色"))
                    .setTextViewContent(R.id.tv_stockin_point_promodel, String.format(getString(R.string.material_model), "Slide Beam0824-496铝挤压加工"))
                    .setTextViewContent(R.id.tv_stockin_point_buynum, String.format(getString(R.string.buy_num), "50"))
                    .setTextViewContent(R.id.tv_stockin_point_receive_pro_num, String.format(getString(R.string.arrive_good_num), "50"))
                    .setButtonListener(R.id.btn_stockin_point_cancel, getString(R.string.cancel), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.btn_stockin_point_save, getString(R.string.save), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            String pointNumStr = dialog.getEdittext(R.id.et_stockin_point_pro_point_num).getText().toString();
                            String spareNumStr = dialog.getEdittext(R.id.et_stockin_point_sparenum).getText().toString();
                            //判断是否输入了清点的数量
                            if (TextUtils.isEmpty(pointNumStr)) {
                                ToastUtils.showShort(getActivity(), getString(R.string.please_input_point_num));
                                return;
                            }
                            //判断是否输入了备品的数量
                            if (TextUtils.isEmpty(spareNumStr)) {
                                ToastUtils.showShort(getActivity(), getString(R.string.please_input_spare_num));
                                return;
                            }
                            /**
                             * 清点的数量 不能大于到货数
                             */
                            int pointNum = Integer.parseInt(pointNumStr);
                            int spareNum = Integer.parseInt(spareNumStr);
                            // TODO: 2017/8/24 网络请求 保存清点信息
                            getPresenter().savePointMaterial(orderNo, pointNum, spareNum);
                            dialog.dismiss();
                        }
                    }).setAnimation(R.style.popWindow_animation_push);
        }
        mPointDialog.show();
    }

    /**
     * 订单号
     */
    private String orderNo = "";

    private BuyOrdernoBean mBuyOrderBean = null;

    @Override
    public void buyOrdernoQuery(BuyOrdernoBean bean) {
        llPointContent.removeAllViews();
        //设置初始数据  从上个界面传过来
        setTextViewText(tvSipBuyNum, R.string.order_no, bean.num);
        setTextViewText(tvSipBuyDate, R.string.buy_date, bean.date);
        setTextViewText(tvSipBuyFrom, R.string.buy_from, bean.from);
        setTextViewText(tvSipBuyer, R.string.buyer, bean.buyer);
        /**
         * 设置订单号 为了保存物料使用
         */
        orderNo = bean.num;
        /**
         * 采购单的实体
         */
        mBuyOrderBean = bean;
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
        for (int i = 0; i < bean.data.size(); i++) {
            ArrayList<String> mRowDatas = new ArrayList<String>();
            BuyOrdernoBean.MarterialBean marterialBean = bean.data.get(i);
            mRowDatas.add(marterialBean.materialCode);
            mRowDatas.add(marterialBean.buyNum);
            mRowDatas.add(marterialBean.arriveNum + "");
            mRowDatas.add(marterialBean.inStockNum);
            mRowDatas.add(marterialBean.pointNum);
            mRowDatas.add(marterialBean.spareNum);
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
                        showGoodsPointDialog(position);
                    }
                })//设置滚动回调监听
                .show(); //显示表格,此方法必须调用
    }


    @Override
    public void sendOrdernoQuery(SendOrdernoBean bean) {
        llPointContent.removeAllViews();
    }

    /**
     * 保存物料清点成功
     *
     * @param bean
     */
    @Override
    public void savePointMaterial(PointMaterialBean bean) {
        if (bean.isSuccess) {
            ToastUtils.showShort("物料请点保存成功");
        }
    }
}
