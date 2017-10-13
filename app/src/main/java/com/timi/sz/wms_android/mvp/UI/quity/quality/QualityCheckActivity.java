package com.timi.sz.wms_android.mvp.UI.quity.quality;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleHeaderAndFooterTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.bean.quality.QualityListBean;
import com.timi.sz.wms_android.mvp.UI.quity.advance_quality.AdvanceQualityActivity;
import com.timi.sz.wms_android.mvp.UI.quity.nomal_quality.NormalQualityActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;
import com.timi.sz.wms_android.view.excelview.DisplayUtil;
import com.timi.sz.wms_android.view.excelview.MyExcelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 品质检测
 * author: timi
 * create at: 2017/9/18 20:05
 */
public class QualityCheckActivity extends BaseActivity<QualityCheckView, QualityCheckPresneter> implements QualityCheckView {

    @BindView(R.id.myexcel_quality)
    MyExcelView myexcelQuality;

    private MyDialog myDialog;
    /**
     * 第一行
     */
    ArrayList<String> mfristData = new ArrayList<>();
    ArrayList<ArrayList<String>> mTableDatas = null;
    /**
     * adapter
     */
    CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>> commonSimpleHeaderTypeAdapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_quality_check;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.quality_check_title));
        mTableDatas = new ArrayList<>();
        /**
         * 设置表头
         */
        mfristData.add("质检");
        mfristData.add("物品编码");
        mfristData.add("供应商");
        mfristData.add("实收数");
        mfristData.add("送检数");
        mfristData.add("合格数");
        mfristData.add("质检结果");
    }

    @Override
    public void initView() {
        myexcelQuality.setRefreshing();
        myexcelQuality.setRefreshListener(new MyExcelView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTableDatas.clear();
                Map<String, Object> params = new HashMap<>();
                params.put("tenancyName", "Default");
                params.put("usernameOrEmailAddress", "asdas");
                params.put("password", "123qwe");
                params.put("mac", PackageUtils.getMac());
                getPresenter().getQualityList(params);
            }

            @Override
            public void onLoadMore() {
                Map<String, Object> params = new HashMap<>();
                params.put("tenancyName", "Default");
                params.put("usernameOrEmailAddress", "asdas");
                params.put("password", "123qwe");
                params.put("mac", PackageUtils.getMac());
                getPresenter().getQualityList(params);
            }
        });
        setRightImg(R.mipmap.quatily_fliter, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null == myDialog) {
                    myDialog = new MyDialog(QualityCheckActivity.this, R.layout.dialog_quality_query);
                    
                }else{
                    // TODO: 2017/10/13 设置上次填写的数据

                }
                myDialog.show();
            }
        });
    }

    @Override
    public void initData() {
        showProgressDialog();
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
    public void getQualityList(final List<QualityListBean> datas) {
        /**
         * 存储下方列表的数据
         */
        LogUitls.e("数据1--->", mTableDatas);
        for (int i = 0; i < datas.size(); i++) {
            ArrayList<String> mRowDatas = new ArrayList<>();
            QualityListBean detailResultsBean = datas.get(i);
            //质检
            mRowDatas.add(detailResultsBean.isFinsishQuality ? "√" : "");
            //物料码
            mRowDatas.add(detailResultsBean.getMaterialCode() + (System.currentTimeMillis() % 2 == 0 ? "9.05.0022" : "9.05.0022111"));
            //供应商
            mRowDatas.add(detailResultsBean.getSupplier() + "超然");
            //实收数
            mRowDatas.add(detailResultsBean.getHaveReceveNum() + "30");
            //送检数
            mRowDatas.add(detailResultsBean.getSendQuaskityNum() + "0");
            //合格数
            mRowDatas.add(detailResultsBean.getQualitiedNum() + "0");
            //质检结果
            mRowDatas.add(detailResultsBean.getQualityResult() + "合格");
            mTableDatas.add(mRowDatas);
        }
        final ArrayList<Integer> allRowWidth = myexcelQuality.getAllRowWidth(mTableDatas, mfristData);
        /**
         * adapter  为空 则初始化
         */
        if (null == commonSimpleHeaderTypeAdapter) {
            commonSimpleHeaderTypeAdapter = new CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>>(mTableDatas) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.item_quality;
                }

                @Override
                public void convert(CommonViewHolder holder, ArrayList<String> strings, int position) {
                    /**
                     * 设置第一行的颜色
                     */
                    int[] ids = new int[]{R.id.tv_quality, R.id.tv_material_code, R.id.tv_supplier, R.id.tv_receive_num, R.id.tv_send_quality_num, R.id.tv_quality_num, R.id.tv_quality_result};
                    for (int i = 0; i < ids.length; i++) {
                        TextView textView = holder.getTextView(ids[i]);
                        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                        layoutParams.width = DisplayUtil.dip2px(QualityCheckActivity.this, allRowWidth.get(i));
                        textView.setLayoutParams(layoutParams);
                        textView.setPadding(20, 20, 20, 20);
                        textView.setText(strings.get(i));
                    }
                    /**
                     * 设置底边分割线
                     */
                    if (position == 0) {
                        holder.getView(R.id.divide_bottom).setVisibility(View.VISIBLE);
                    } else {
                        holder.getView(R.id.divide_bottom).setVisibility(View.GONE);

                    }
                }

                @Override
                protected int getHeaderLayoutId() {
                    return R.layout.header_quality;
                }

                @Override
                protected void bindHeader(CommonViewHolder holder, int position) {
                    /**
                     * 设置第一行的颜色
                     */
                    int[] ids = new int[]{R.id.tv_quality, R.id.tv_material_code, R.id.tv_supplier, R.id.tv_receive_num, R.id.tv_send_quality_num, R.id.tv_quality_num, R.id.tv_quality_result};
                    for (int i = 0; i < ids.length; i++) {
                        TextView textView = holder.getTextView(ids[i]);
                        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                        layoutParams.width = DisplayUtil.dip2px(QualityCheckActivity.this, allRowWidth.get(i));
                        textView.setLayoutParams(layoutParams);
                        textView.setPadding(20, 20, 20, 20);
                        textView.setText(mfristData.get(i));
                    }
                    /**
                     * 设置底边分割线
                     */
                    if (position == 0) {
                        holder.getView(R.id.divide_bottom).setVisibility(View.VISIBLE);
                    } else {
                        holder.getView(R.id.divide_bottom).setVisibility(View.GONE);

                    }
                    holder.getView(R.id.ll_content).setBackgroundColor(getResources().getColor(R.color.beijin));
                }

            };
        }
        myexcelQuality.loadData(commonSimpleHeaderTypeAdapter, mTableDatas);
        commonSimpleHeaderTypeAdapter.notifyDataSetChanged();
        commonSimpleHeaderTypeAdapter.setOnItemClickListener(R.id.ll_content, new CommonSimpleHeaderAndFooterTypeAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                startActivity(new Intent(QualityCheckActivity.this, NormalQualityActivity.class));
//                startActivity(new Intent(QualityCheckActivity.this, AdvanceQualityActivity.class));
            }
        });
        /**
         * 数据都加载完成调用 finishRefresh()方法
         */
        myexcelQuality.finishRefresh();
    }

}
