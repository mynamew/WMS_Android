package com.timi.sz.wms_android.mvp.UI.quity.quality;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.rmondjone.locktableview.LockTableView;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.bean.quality.QualityListBean;
import com.timi.sz.wms_android.mvp.UI.quity.nomal_quality.NormalQualityActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class QualityCheckActivity extends BaseActivity<QualityCheckView, QualityCheckPresneter> implements QualityCheckView {


    @BindView(R.id.ll_content)
    LinearLayout llContent;

    @Override
    public int setLayoutId() {
        return R.layout.activity_quality_check;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.quality_check_title));
    }

    @Override
    public void initView() {
        llContent.removeAllViews();

    }

    @Override
    public void initData() {
        mTableDatas = new ArrayList<ArrayList<String>>();
        Map<String, Object> params = new HashMap<>();
        params.put("tenancyName", "Default");
        params.put("usernameOrEmailAddress", "asdas");
        params.put("password", "123qwe");
        params.put("mac", PackageUtils.getMac());
        getPresenter().getQualityList(params);
    }

    @Override
    public QualityCheckPresneter createPresenter() {
        return new QualityCheckPresneter(this);
    }

    @Override
    public QualityCheckView createView() {
        return this;
    }

    /**
     * 获取质量检验的列表
     *
     * @param datas
     */
    @Override
    public void getQualityList(List<QualityListBean> datas) {
        showExcelDialog(datas);
    }

    ArrayList<ArrayList<String>> mTableDatas;

    /**
     * 展示表体
     */
    public void showExcelDialog(List<QualityListBean> datas) {
        llContent.removeAllViews();
        mTableDatas.clear();
        ArrayList<String> mfristData = new ArrayList<String>();
        mfristData.add("质检");
        mfristData.add("物品编码");
        mfristData.add("供应商");
        mfristData.add("实收数");
        mfristData.add("送检数");
        mfristData.add("合格数");
        mfristData.add("质检结果");
        mTableDatas.add(mfristData);
        /**
         * 存储下方列表的数据
         */
        for (int i = 0; i < datas.size(); i++) {
            ArrayList<String> mRowDatas = new ArrayList<String>();
            QualityListBean detailResultsBean = datas.get(i);
            //质检
            mRowDatas.add(detailResultsBean.isFinsishQuality ? "√" : "×");
            //物料码
            mRowDatas.add(detailResultsBean.getMaterialCode());
            //供应商
            mRowDatas.add(detailResultsBean.getSupplier());
            //实收数
            mRowDatas.add(detailResultsBean.getHaveReceveNum() + "");
            //送检数
            mRowDatas.add(detailResultsBean.getSendQuaskityNum() + "");
            //合格数
            mRowDatas.add(detailResultsBean.getQualitiedNum() + "");
            //质检结果
            mRowDatas.add(detailResultsBean.getQualityResult());
            mTableDatas.add(mRowDatas);
        }
        LockTableView mLockTableView = new LockTableView(this, llContent, mTableDatas);
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
                .setNullableString("0") //空值替换值
                .setTableViewListener(new LockTableView.OnTableViewListener() {
                    @Override
                    public void onTableViewScrollChange(int x, int y) {
                    }

                    @Override
                    public void onTabViewClickListener(int position) {
                        Intent it = new Intent(QualityCheckActivity.this, NormalQualityActivity.class);
                        startActivity(it);
                    }
                })//设置滚动回调监听
                .show(); //显示表格,此方法必须调用
    }
}
