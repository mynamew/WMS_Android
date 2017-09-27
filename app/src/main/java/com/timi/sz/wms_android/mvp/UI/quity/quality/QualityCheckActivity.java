package com.timi.sz.wms_android.mvp.UI.quity.quality;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.bean.quality.QualityListBean;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.excelview.DisplayUtil;
import com.timi.sz.wms_android.view.excelview.MeasureExcelViewUtils;
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
    /**
     * 第一行
     */
    ArrayList<String> mfristData = new ArrayList<String>();
    ArrayList<ArrayList<String>> mTableDatas = new ArrayList<>();
    ArrayList<ArrayList<String>> mTabNewDatas = new ArrayList<>();
    /**
     * 每一行宽度的集合
     */
    ArrayList<Integer> allRowWidth;

    @Override
    public int setLayoutId() {
        return R.layout.activity_quality_check;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.quality_check_title));
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
        mTabNewDatas.clear();

        for (int i = 0; i < datas.size(); i++) {
            ArrayList<String> mRowDatas = new ArrayList<String>();
            QualityListBean detailResultsBean = datas.get(i);
            //质检
            mRowDatas.add(detailResultsBean.isFinsishQuality ? "√" : "×");
            //物料码
            mRowDatas.add(detailResultsBean.getMaterialCode());
            //供应商
            String  str="大大大大叔";
            for (int j = 0; j < mTableDatas.size()/10; j++) {
                str=str+"笑笑笑";
            }
            mRowDatas.add(detailResultsBean.getSupplier()+str);
            //实收数
            mRowDatas.add(detailResultsBean.getHaveReceveNum() + "");
            //送检数
            mRowDatas.add(detailResultsBean.getSendQuaskityNum() + "");
            //合格数
            mRowDatas.add(detailResultsBean.getQualitiedNum() + "");
            //质检结果
            mRowDatas.add(detailResultsBean.getQualityResult());
            mTabNewDatas.add(mRowDatas);
        }
        final ArrayList<Integer> allRowWidth = myexcelQuality.getAllRowWidth(mTableDatas, mTabNewDatas, mfristData);
        myexcelQuality.loadData(mTableDatas, mTabNewDatas, mfristData, new CommonSimpleTypeAdapter<ArrayList<String>>(mTableDatas) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_quality;
            }

            @Override
            public void convert(CommonViewHolder holder, ArrayList<String> strings, int position) {
                /**
                 * 设置第一行的颜色
                 */
                LinearLayout llcontent= (LinearLayout) holder.getView(R.id.ll_content);
                ViewGroup.LayoutParams layoutParams1 = llcontent.getLayoutParams();
                int width=0;
                for (int i = 0; i <allRowWidth.size() ; i++) {
                    width=width+allRowWidth.get(i);
                }
                layoutParams1.width= DisplayUtil.dip2px(QualityCheckActivity.this,width+ allRowWidth.size()+1);
                llcontent.setLayoutParams(layoutParams1);
                if (position == 0) {
                    llcontent.setBackgroundColor(getResources().getColor(R.color.statuscolor));
                } else {
                    llcontent.setBackgroundColor(getResources().getColor(R.color.white));
                }
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
        });
        /**
         * 数据都加载完成调用 finishRefresh()方法
         */
        myexcelQuality.finishRefresh();
    }

}
