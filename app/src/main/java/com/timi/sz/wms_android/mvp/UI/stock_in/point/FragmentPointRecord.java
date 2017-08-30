package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;

import com.rmondjone.locktableview.LockTableView;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.mvp.base.BaseFragment;
import com.timi.sz.wms_android.view.MyDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 15:50
 */

public class FragmentPointRecord extends BaseFragment<FragmentPointRecordView,FragmentPointRecordPresenter> implements FragmentPointRecordView{
    @BindView(R.id.ll_stockin_point_record)
    LinearLayout llStockinPointRecord;

    @Override
    public FragmentPointRecordPresenter createPresenter() {
        return new FragmentPointRecordPresenter(getActivity());
    }

    @Override
    public FragmentPointRecordView createView() {
        return this;
    }

    @Override
    public void initData() {
        //构造假数据
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> mfristData = new ArrayList<String>();
        mfristData.add("序号");
        mfristData.add("物品编码");
        mfristData.add("物品名称");
        mfristData.add("请点数");
        mfristData.add("备品数");
        mfristData.add("清点日期");
        mTableDatas.add(mfristData);
        for (int i = 0; i < 20; i++) {
            ArrayList<String> mRowDatas = new ArrayList<String>();
            mRowDatas.add("" + i);
            for (int j = 0; j < 5; j++) {
                mRowDatas.add("数据" + j);
            }
            mTableDatas.add(mRowDatas);
        }
        LockTableView mLockTableView = new LockTableView(getActivity(), llStockinPointRecord, mTableDatas);
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
                        showGoodsPointRecordDialog();
                    }
                })//设置滚动回调监听
                .show(); //显示表格,此方法必须调用
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_stockin_point_record;
    }

    /**
     * 显示物品清点的弹出框
     */
    private MyDialog mPointRecordDialog = null;

    public void showGoodsPointRecordDialog() {
        if (null == mPointRecordDialog) {
            mPointRecordDialog = new MyDialog(getActivity(), R.layout.dialog_stockin_record)
                    .setTextViewContent(R.id.tv_stockin_point_record_pronum, String.format(getString(R.string.material_code), "9.05.0022"))
                    .setTextViewContent(R.id.tv_stockin_point_record_proname, String.format(getString(R.string.material_name), "滑轨双孔梁496-蓝色"))
                    .setTextViewContent(R.id.tv_stockin_point_record_promodel, String.format(getString(R.string.material_model), "Slide Beam0824-496铝挤压加工"))
                    .setButtonListener(R.id.btn_stockin_point_record_delete, getString(R.string.delete), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.btn_stockin_point_record_update, getString(R.string.update), new MyDialog.DialogClickListener() {
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
        mPointRecordDialog.show();
    }
}
