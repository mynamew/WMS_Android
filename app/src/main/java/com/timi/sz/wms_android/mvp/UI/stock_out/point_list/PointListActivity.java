package com.timi.sz.wms_android.mvp.UI.stock_out.point_list;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rmondjone.locktableview.LockTableView;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.mvp.UI.stock_out.point_detail.PointDetailActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_POINT_DETAIL_BEAN;

/**
 * 委外补料的物品清单
 * author: timi
 * create at: 2017/9/6 10:58
 */
public class PointListActivity extends BaseActivity<PointListView, PointListPresenter> implements PointListView {

    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_orderno_date)
    TextView tvOrdernoDate;
    @BindView(R.id.tv_lib_name)
    TextView tvLibName;
    @BindView(R.id.tv_area_name)
    TextView tvAreaName;
    @BindView(R.id.tv_have_get_num)
    TextView tvHaveGetNum;
    @BindView(R.id.tv_can_get_num)
    TextView tvCanGetNum;
    @BindView(R.id.ll_putaway_date)
    LinearLayout llPutawayDate;
    @BindView(R.id.tv_wait_point_num)
    TextView tvWaitPointNum;
    @BindView(R.id.tv_have_point_num)
    TextView tvHavePointNum;
    @BindView(R.id.tv_send_material_num)
    TextView tvSendMaterialNum;
    @BindView(R.id.cb_show_all)
    CheckBox cbShowAll;
    @BindView(R.id.ll_feed_list_content)
    LinearLayout llFeedListContent;
    @BindView(R.id.btn_point_commit)
    Button btnPointCommit;
    private OutSourceFeedBean bean;

    @Override
    public int setLayoutId() {
        return R.layout.activity_oursource_return_material;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.outsource_feed_point_title));
        String stringExtra = getIntent().getStringExtra(STOCK_OUT_OUTSOURCE_FEED_BEAN);
        if (!TextUtils.isEmpty(stringExtra)) {
            bean = new Gson().fromJson(stringExtra, OutSourceFeedBean.class);
        }
        if (null != bean) {
            setTextViewText(tvOrderno, R.string.outsource_orderno, bean.Orderno);
            setTextViewText(tvOrdernoDate, R.string.create_orderno_date, bean.date);
            setTextViewText(tvLibName, R.string.lib_name, bean.libName);
            setTextViewText(tvAreaName, R.string.area_name, bean.area);
            setTextViewText(tvSendMaterialNum, R.string.send_material_num, bean.sendNum);
            setTextViewText(tvHavePointNum, R.string.have_count_num, bean.havePointNum);
            setTextViewText(tvCanGetNum, R.string.can_get_num, bean.canReceiveNum);
            setTextViewText(tvHaveGetNum, R.string.have_get_num, bean.haveGetNum);
            setTextViewText(tvWaitPointNum, R.string.wait_count_num, bean.waitPointNum);
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        showPointList();
    }

    @Override
    public PointListPresenter createPresenter() {
        return new PointListPresenter(this);
    }

    @Override
    public PointListView createView() {
        return this;
    }

    @OnClick({R.id.cb_show_all, R.id.btn_point_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_show_all:
                break;
            case R.id.btn_point_commit:
                break;
        }
    }

    ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();

    /**
     * 显示清点的列表
     */
    public void showPointList() {
        llFeedListContent.removeAllViews();
        mTableDatas.clear();
        ArrayList<String> mfristData = new ArrayList<String>();
        mfristData.add("行号");
        mfristData.add("物品编码");
        mfristData.add("物品名称");
        mfristData.add("请点数");
        mfristData.add("备品数");
        mfristData.add("清点日期");
        mTableDatas.add(mfristData);
        for (int i = 0; i < bean.datas.size(); i++) {
            ArrayList<String> mRowDatas = new ArrayList<String>();
            OutSourceFeedBean.MaterialBean stockinMaterialBean = bean.datas.get(i);
            mRowDatas.add("" + i);
            mRowDatas.add(stockinMaterialBean.materialCode);
            mRowDatas.add(stockinMaterialBean.materialName);
            mRowDatas.add(stockinMaterialBean.sendNum + "");
            mRowDatas.add(stockinMaterialBean.haveReceiveNum + "");
            mRowDatas.add(stockinMaterialBean.pointNum + "");
            mTableDatas.add(mRowDatas);
        }

        LockTableView mLockTableView = new LockTableView(this, llFeedListContent, mTableDatas);
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
                        OutSourceFeedBean.MaterialBean materialBean = bean.datas.get(position);
                        Intent intent = new Intent(PointListActivity.this, PointDetailActivity.class);
                        intent.putExtra(STOCK_OUT_POINT_DETAIL_BEAN, new Gson().toJson(materialBean));
                        startActivity(intent);
                    }
                })//设置滚动回调监听
                .show(); //显示表格,此方法必须调用

    }
}
