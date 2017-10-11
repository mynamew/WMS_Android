package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleHeaderAndFooterTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.StockinMaterialBean;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.StockInPointEvent;
import com.timi.sz.wms_android.mvp.base.BaseFragment;
import com.timi.sz.wms_android.view.MyDialog;
import com.timi.sz.wms_android.view.excelview.DisplayUtil;
import com.timi.sz.wms_android.view.excelview.MyExcelView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.timi.sz.wms_android.base.uils.Constants.BUY_ORDE_NUM;

/**
 * $dsc  清点记录
 * author: timi
 * create at: 2017-08-25 15:50
 */

public class FragmentPointRecord extends BaseFragment<FragmentPointRecordView, FragmentPointRecordPresenter> implements FragmentPointRecordView {
    @BindView(R.id.myexcel_point_record)
    MyExcelView myexcelPointRecord;
    /**
     * 当前的位置
     */
    private int currentPostion;
    /**
     * 跳转的code值
     */
    private int intentCode;

    /**
     * 修改的清点数量
     */
    private int pointNum;
    /**
     * 备品的请点数量
     */
    private int spareNum;
    //tabs
    /**
     * adapter
     */
    CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>> commonSimpleHeaderTypeAdapter;    //code 码
    private List<StockinMaterialBean> mDatas = new ArrayList<>();
    ArrayList<ArrayList<String>> mTableDatas = new ArrayList<>();
    ArrayList<String> mfristData = new ArrayList<>();
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
        BaseMessage.register(this);
        int receiveId = ((StockInPointActivity) getActivity()).getReceiveId();
        intentCode = ((StockInPointActivity) getActivity()).getIntentCode().getIntExtra(Constants.CODE_STR, Constants.COME_MATERAIL_NUM);
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("ReceiveId", receiveId);
        params.put("MAC", PackageUtils.getMac());
        getPresenter().getPointRecord(params);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_stockin_point_record;
    }

    /**
     * 显示物品清点的弹出框
     */
    private MyDialog mPointRecordDialog = null;

    public void showGoodsPointRecordDialog(final int position) {
        currentPostion = position;
        if (null == mPointRecordDialog) {
            final StockinMaterialBean stockinMaterialBean = mDatas.get(position);
            mPointRecordDialog = new MyDialog(getActivity(), R.layout.dialog_stockin_record)
                    .setTextViewContent(R.id.tv_stockin_point_record_pronum, String.format(getString(R.string.material_code), stockinMaterialBean.getMaterialCode()))
                    .setTextViewContent(R.id.tv_stockin_point_record_proname, String.format(getString(R.string.material_name), stockinMaterialBean.getMaterialName()))
                    .setTextViewContent(R.id.tv_stockin_point_record_promodel, String.format(getString(R.string.material_model), stockinMaterialBean.getMaterialStandard()))
                    .setButtonListener(R.id.btn_stockin_point_record_delete, getString(R.string.delete), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                            Map<String, Object> params = new HashMap<>();
                            params.put("UserId", SpUtils.getInstance().getUserId());
                            params.put("MAC", PackageUtils.getMac());
                            params.put("OrgId", SpUtils.getInstance().getOrgId());
                            params.put("ReceiveRecordId", stockinMaterialBean.getId());
                            getPresenter().deleteMaterialPoint(params, intentCode == Constants.BUY_ORDE_NUM);
                        }
                    })
                    .setButtonListener(R.id.btn_stockin_point_record_update, getString(R.string.update), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                            String pointNumStr = dialog.getEdittext(R.id.et_stockin_point_record_pro_point_num).getText().toString();
                            String spareNumStr = dialog.getEdittext(R.id.et_stockin_poin_recordt_sparenum).getText().toString();
                            pointNum = TextUtils.isEmpty(pointNumStr) ? stockinMaterialBean.getCountQty() : Integer.parseInt(pointNumStr);
                            spareNum = TextUtils.isEmpty(spareNumStr) ? stockinMaterialBean.getGiveQty() : Integer.parseInt(spareNumStr);
                            Map<String, Object> params = new HashMap<>();
                            params.put("UserId", SpUtils.getInstance().getUserId());
                            params.put("MAC", PackageUtils.getMac());
                            params.put("OrgId", SpUtils.getInstance().getOrgId());
                            params.put("ReceiveRecordId", stockinMaterialBean.getId());
                            params.put("CountQty", pointNum);
                            params.put("GiveQty", spareNum);
                            getPresenter().updateMaterialPoint(params, intentCode == Constants.BUY_ORDE_NUM);


                        }
                    }).setAnimation(R.style.popWindow_animation_push);
        }
        mPointRecordDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mPointRecordDialog = null;
            }
        });
        mPointRecordDialog.show();
    }
    @Override
    public void getPointRecord(List<StockinMaterialBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        showExcelDialog();
    }

    @Override
    public void updatePointRecord() {
        /**
         * 更新清点界面的表体
         */
        BaseMessage.post(new StockInPointEvent(StockInPointEvent.MATERIAL_POINT_UPDATE));
        /**
         * 更新数据
         */
        ToastUtils.showShort("清点记录修改成功");
        StockinMaterialBean stockinMaterialBean = mDatas.get(currentPostion);
        stockinMaterialBean.setCountQty(pointNum);
        stockinMaterialBean.setGiveQty(spareNum);
        showExcelDialog();
    }

    @Override
    public void deletePointRecord() {
        /**
         * 更新清点界面的表体
         */
        BaseMessage.post(new StockInPointEvent(StockInPointEvent.MATERIAL_POINT_UPDATE));
        /**
         * 更新数据
         */
        ToastUtils.showShort("清点记录删除成功");
        mDatas.remove(currentPostion);
        showExcelDialog();
    }

    /**
     * 展示表体
     */
    public void showExcelDialog() {
        mfristData.add("行号");
        mfristData.add("物品编码");
        mfristData.add("物品名称");
        mfristData.add("请点数");
        mfristData.add("备品数");
        mfristData.add("清点日期");
        mTableDatas.add(mfristData);

        for (int i = 0; i < mDatas.size(); i++) {
            ArrayList<String> mRowDatas = new ArrayList<String>();
            StockinMaterialBean stockinMaterialBean = mDatas.get(i);
            mRowDatas.add("" + stockinMaterialBean.getId());
            mRowDatas.add(stockinMaterialBean.getMaterialCode());
            mRowDatas.add(stockinMaterialBean.getMaterialName());
            mRowDatas.add(stockinMaterialBean.getCountQty() + "");
            mRowDatas.add(stockinMaterialBean.getGiveQty() + "");
            mRowDatas.add(stockinMaterialBean.getCreateDateTime());
            mTableDatas.add(mRowDatas);
        }
        final ArrayList<Integer> allRowWidth = myexcelPointRecord.getAllRowWidth(mTableDatas, mfristData);
        /**
         * adapter  为空 则初始化
         */
        if (null == commonSimpleHeaderTypeAdapter) {
            commonSimpleHeaderTypeAdapter = new CommonSimpleHeaderAndFooterTypeAdapter<ArrayList<String>>(mTableDatas) {
                @Override
                public int getLayoutId(int viewType) {

                    return R.layout.item_point_record;
                }

                @Override
                public void convert(CommonViewHolder holder, ArrayList<String> strings, int position) {
                    /**
                     * 初始化不同的布局 id
                     */
                    int[] ids = new int[]{R.id.tv_line_name, R.id.tv_goods_code, R.id.tv_buy_num, R.id.tv_arrive_good_num, R.id.tv_in_stock_num, R.id.tv_point_num, R.id.tv_spare_num};
                    for (int i = 0; i < ids.length; i++) {
                        TextView textView = holder.getTextView(ids[i]);
                        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                        layoutParams.width = DisplayUtil.dip2px(getActivity(), allRowWidth.get(i));
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
                        return R.layout.header_point_record;
                }

                @Override
                protected void bindHeader(CommonViewHolder holder, int position) {
                    /**
                     * 初始化不同的布局 id
                     */
                    int[] ids = new int[]{R.id.tv_line_name, R.id.tv_goods_code, R.id.tv_buy_num, R.id.tv_arrive_good_num, R.id.tv_in_stock_num, R.id.tv_point_num, R.id.tv_spare_num};
                    /**
                     * 设置布局
                     */
                    for (int i = 0; i < ids.length; i++) {
                        TextView textView = holder.getTextView(ids[i]);
                        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                        layoutParams.width = DisplayUtil.dip2px(getActivity(), allRowWidth.get(i));
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
        myexcelPointRecord.loadData(commonSimpleHeaderTypeAdapter);
        commonSimpleHeaderTypeAdapter.notifyDataSetChanged();
        /**
         * 数据都加载完成调用 finishRefresh()方法
         */
        commonSimpleHeaderTypeAdapter.setOnItemClickListener(R.id.ll_content, new CommonSimpleHeaderAndFooterTypeAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                /**
                 * 弹出修改清点的弹出框
                 */
                showGoodsPointRecordDialog(position);
            }
        });
        myexcelPointRecord.finishRefresh();
    }

    /**
     * 当清点记录 做修改和 删除时 更新物料清点的v表体
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshPointMaterialPoDetails(StockInPointEvent event) {
        if (event.getEvent().equals(StockInPointEvent.MATERIAL_POINT_RECORD_UPDATE)) {
            Map<String, Object> params = new HashMap<>();
            params.put("UserId", SpUtils.getInstance().getUserId());
            params.put("OrgId", SpUtils.getInstance().getOrgId());
            params.put("ReceiveId", event.receiveId);
            params.put("MAC", PackageUtils.getMac());
            getPresenter().getPointRecord(params);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }
}
