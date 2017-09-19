package com.timi.sz.wms_android.mvp.UI.quity.quality;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.rmondjone.locktableview.LockTableView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.bean.quality.QualityListBean;
import com.timi.sz.wms_android.mvp.UI.quity.nomal_quality.NormalQualityActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.excel.MyExcelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 品质检测
 * author: timi
 * create at: 2017/9/18 20:05
 */
public class QualityCheckActivity extends BaseActivity<QualityCheckView, QualityCheckPresneter> implements QualityCheckView {

    @BindView(R.id.excel_quality)
    MyExcelView excelQuality;

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
      excelQuality.showRefresh();
        excelQuality.setTableViewListener(new MyExcelView.OnTableViewListener() {
            @Override
            public void onTabViewClickListener(int position) {

            }
        });
        excelQuality.setRefreshLayoutLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });
        excelQuality.setRefreshLayoutRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
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
        excelQuality.showRefresh();
        showExcelDialog(datas);
    }

    ArrayList<ArrayList<String>> mTableDatas;

    /**
     * 展示表体
     */
    public void showExcelDialog(List<QualityListBean> datas) {
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
        excelQuality.setExcelFirstData(mTableDatas);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
