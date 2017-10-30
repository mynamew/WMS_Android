package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.CommonSimpleHeaderAndFooterTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
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
    @BindView(R.id.rlv_point_record)
    RecyclerView rlvPointRecord;
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
    private List<StockinMaterialBean> mDatas = new ArrayList<>();
    private BaseRecyclerAdapter<StockinMaterialBean> adapter;

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
        showRecordData();
    }

    /**
     * 展示记录的数据
     */
    private void showRecordData() {
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<StockinMaterialBean>(getActivity(), mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_point_record;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, StockinMaterialBean item) {
                    holder.setTextView(R.id.tv_line_name,position+1);
                    holder.setTextView(R.id.tv_material_code,item.getMaterialCode());
                    holder.setTextView(R.id.tv_point_num,item.getCountQty());
                    holder.setTextView(R.id.tv_material_name,item.getMaterialName());
                    holder.setTextView(R.id.tv_spare_num,item.getGiveQty());
                    setTextViewText(holder.getTextView(R.id.tv_point_date),R.string.item_point_date,item.getCreateDateTime());

                }
            };
            rlvPointRecord.setAdapter(adapter);
            rlvPointRecord.setLayoutManager(new LinearLayoutManager(getActivity()));
            rlvPointRecord.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST,R.drawable.item_point_divider));
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                    showGoodsPointRecordDialog(pos);
                }
            });
        }else {
            adapter.notifyDataSetChanged();
        }

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
        adapter.notifyDataSetChanged();
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
