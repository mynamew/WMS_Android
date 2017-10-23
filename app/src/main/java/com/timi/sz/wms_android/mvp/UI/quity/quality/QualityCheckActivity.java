package com.timi.sz.wms_android.mvp.UI.quity.quality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.DateUtils;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.GetAQLList;
import com.timi.sz.wms_android.bean.quality.QualityListBean;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.UI.quity.advance1_quality.Advance1QualityActivity;
import com.timi.sz.wms_android.mvp.UI.quity.advance_quality.AdvanceQualityActivity;
import com.timi.sz.wms_android.mvp.UI.quity.nomal_quality.NormalQualityActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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
    @BindView(R.id.rlv_quality)
    RecyclerView rlvQuality;

    private MyDialog myDialog;

    List<QualityListBean> mDatas = null;
    /**
     * 免检的Dialog
     */
    private MyDialog dontNeedQualityDialog;
    /**
     * 设置aql的Dialog
     */
    private MyDialog setAQLDialog;
    /**
     * adapter
     */
    private BaseRecyclerAdapter<QualityListBean> adapter;

    /**
     * 待定的spinner 的adapter
     */
    private ArrayAdapter<String> adapterStandardLevel;
    private ArrayAdapter<String> adapterAQL;

    /**
     * 待定的设置aql的参数
     */
    private List<String> levelCodeLists = new ArrayList<>();//标准水平
    private List<String> aqlLists = new ArrayList<>();//aql
    /**
     * 标准水平 选择的字符串
     */
    String standardLevelStr = "";
    /**
     * aql参数选择的字符串
     */
    String aqlStr = "";

    @Override
    public int setLayoutId() {
        return R.layout.activity_quality_check;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.quality_check_title));
        mDatas = new ArrayList<>();
        BaseMessage.register(this);
    }

    @Override
    public void initView() {
        setRightImg(R.mipmap.quatily_fliter, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null == myDialog) {
                    myDialog = new MyDialog(QualityCheckActivity.this, R.layout.dialog_quality_query);
                    /**
                     * 点击确定
                     */
                    myDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            Map<String, Object> params = new HashMap<>();
                            params.put("UserId", SpUtils.getInstance().getUserId());
                            params.put("OrgId", SpUtils.getInstance().getOrgId());
                            params.put("mac", PackageUtils.getMac());
                            /**
                             * 开始日期
                             */
                            String startDate = dialog.getTextView(R.id.tv_start_time).getText().toString();
                            /**
                             * 结束日期
                             */
                            String endDate = dialog.getTextView(R.id.tv_end_time).getText().toString();
                            // TODO: 2017/10/13 对日期格式进行判断，开始和结束日期不能超过1个月
                            //1、 开始时间和结束时间是否正确
                            //2、 判断开始时间和结束时间 相差是否超过1个月
                            //3、 以上条件均满足则 添加到params中去
                            try {
                                long startTime = DateUtils.stringToLong(startDate, "yyyy-MM-dd");
                                long endTime = DateUtils.stringToLong(endDate, "yyyy-MM-dd");
                                /**
                                 * 当只用 整型相乘时，会造成越界
                                 */
                                if ((endTime - startTime) > (30 * 24 * 60 * 60 * 1000.0)) {//一个月
                                    ToastUtils.showShort("开始时间和结束时间相差不能超过1个月");
                                    return;
                                }
                                /**
                                 * 设置参数：开始日期 和结束日期
                                 */
                                params.put("StartDate", startDate);
                                params.put("EndDate", endDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            /**
                             * 收料单号
                             */
                            String recevieMaterialNum = dialog.getEdittext(R.id.et_arrive_orderno).getText().toString();
                            if (!TextUtils.isEmpty(recevieMaterialNum)) {
                                params.put("ReceiptCode", recevieMaterialNum);
                            }
                            /**
                             * 物品代码
                             */
                            String goodsCode = dialog.getEdittext(R.id.et_material_code).getText().toString();
                            if (!TextUtils.isEmpty(goodsCode)) {
                                params.put("MaterialCode", goodsCode);
                            }
                            /**
                             * 物品名称
                             */
                            String materialName = dialog.getEdittext(R.id.et_material_name).getText().toString();
                            if (!TextUtils.isEmpty(materialName)) {
                                params.put("MaterialName", materialName);
                            }
                            /**
                             * 物品名称
                             */
                            String supplierName = dialog.getEdittext(R.id.et_supplier).getText().toString();
                            if (!TextUtils.isEmpty(supplierName)) {
                                params.put("SupplierName", supplierName);
                            }
                            /**
                             * 包装条码
                             */
                            String barcodeNo = dialog.getEdittext(R.id.et_pack_code).getText().toString();
                            if (!TextUtils.isEmpty(barcodeNo)) {
                                params.put("BarcodeNo", barcodeNo);
                            }
                            dialog.dismiss();
                            showProgressDialog();
                            getPresenter().queryReceiptForIQC(params);
                        }
                    });
                    myDialog.setTextViewListener(R.id.tv_start_time, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(final MyDialog dialog) {
                            Calendar now = Calendar.getInstance();
                            DatePickerDialog dpd = DatePickerDialog.newInstance(
                                    new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
                                            /**
                                             * 选择 开始和结束时间的返回
                                             */
                                            String date = "You picked the following date: From- " + dayOfMonth + "/" + (++monthOfYear) + "/" + year + " To " + dayOfMonthEnd + "/" + (++monthOfYearEnd) + "/" + yearEnd;
                                            LogUitls.e("时间--->", date);
                                            myDialog.getTextView(R.id.tv_start_time).setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                                            myDialog.getTextView(R.id.tv_end_time).setText(yearEnd + "-" + monthOfYearEnd + "-" + dayOfMonthEnd);

                                        }
                                    },
                                    now.get(Calendar.YEAR),
                                    now.get(Calendar.MONTH),
                                    now.get(Calendar.DAY_OF_MONTH)
                            );
                            dpd.setAutoHighlight(true);
                            dpd.show(getFragmentManager(), "Datepickerdialog");
                        }
                    });
                    myDialog.setTextViewListener(R.id.tv_end_time, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(final MyDialog dialog) {
                            Calendar now = Calendar.getInstance();
                            DatePickerDialog dpd = DatePickerDialog.newInstance(
                                    new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
                                            /**
                                             * 选择 开始和结束时间的返回
                                             */
                                            String date = "You picked the following date: From- " + dayOfMonth + "/" + (++monthOfYear) + "/" + year + " To " + dayOfMonthEnd + "/" + (++monthOfYearEnd) + "/" + yearEnd;
                                            LogUitls.e("时间--->", date);
                                            myDialog.getTextView(R.id.tv_start_time).setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                                            myDialog.getTextView(R.id.tv_end_time).setText(yearEnd + "-" + monthOfYearEnd + "-" + dayOfMonthEnd);

                                        }
                                    },
                                    now.get(Calendar.YEAR),
                                    now.get(Calendar.MONTH),
                                    now.get(Calendar.DAY_OF_MONTH)
                            );
                            dpd.setAutoHighlight(true);
                            dpd.show(getFragmentManager(), "Datepickerdialog");
                        }
                    });
                    /**
                     * 包装码二维码扫描
                     */
                    myDialog.getView(R.id.iv_pack_scan).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                                @Override
                                public void scanSuccess(int requestCode, String result) {
                                    /**
                                     * 设置扫描的包装码
                                     */
                                    myDialog.getEdittext(R.id.et_pack_code).setText(result);
                                }
                            });
                        }
                    });
                    /**
                     * 点击半透明图层的点击事件
                     */
                    myDialog.getView(R.id.view_left).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            myDialog.dismiss();
                        }
                    });
                    /**
                     * 点击取消
                     */
                    myDialog.setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    });
                }
                myDialog.show();
            }
        });
    }

    private void showDontNeedQualityDialog(final int position) {
        if (null == dontNeedQualityDialog) {
            dontNeedQualityDialog = new MyDialog(QualityCheckActivity.this, R.layout.dialog_dont_need_quality);
            dontNeedQualityDialog.setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                }
            });
            dontNeedQualityDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                    /**
                     * 免检请求
                     */
                    Map<String, Object> params = new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("MAC", PackageUtils.getMac());
                    params.put("ReceiptId", mDatas.get(position).getReceiptId());
                    params.put("ReceiptDetailId", mDatas.get(position).getReceiptDetailId());
                    getPresenter().submitExemption(params, position);
                }
            });
            dontNeedQualityDialog.show();
        }
    }

    @Override
    public void initData() {
        showProgressDialog();
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
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
         * 做判断，当时用户进行查询并且查询结果为空的情况下则进行 相应的提示
         */
        if (datas.isEmpty() && null != myDialog) {//当结果为空
            ToastUtils.showShort(getString(R.string.quality_query_result_nodata));
            return;
        }
        mDatas.clear();
        /**
         * 存储下方列表的数据
         */
        mDatas.addAll(datas);
        /**
         * 初始化adapter
         */
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<QualityListBean>(QualityCheckActivity.this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_quality;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, QualityListBean qualityListBean) {
                    /**
                     * 根据状态 设置图片
                     */
                    switch (qualityListBean.getQcStatus()) {
                        case 0://待检
                            holder.setImageView(R.id.iv_quality_status, R.mipmap.quality_wait_quality);
                            break;
                        case 1:
                            break;
                        case 2://未完成
                            holder.setImageView(R.id.iv_quality_status, R.mipmap.quality_no_complete);
                            break;
                        case 3://已完成
                            holder.setImageView(R.id.iv_quality_status, R.mipmap.quality_complete);
                            break;
                        default:
                            holder.setImageView(R.id.iv_quality_status, R.mipmap.quality_wait_quality);
                            break;
                    }
                    /**
                     * 免检
                     */
                    if (qualityListBean.getQcType() == 0) {
                        holder.setImageView(R.id.iv_quality_status, R.mipmap.quality_dont_quality);
                    }
                    /**
                     * 设置物品编码
                     */
                    holder.setTextView(R.id.tv_material_code, qualityListBean.getMaterialCode());
                    /**
                     * 实收数
                     */
                    holder.setTextView(R.id.tv_receive_num, qualityListBean.getReceiveQty());
                    /**
                     * 送检数
                     */
                    holder.setTextView(R.id.tv_send_quality_num, qualityListBean.getSampleQty());
                    /**
                     * 合格数
                     */
                    holder.setTextView(R.id.tv_quality_num, qualityListBean.getPassQty());
                    /**
                     * 质检结果
                     */
                    if (qualityListBean.getQcStatus() == 0) {//未检
                        holder.setTextView(R.id.tv_quality_result, R.color.wait_quality, getString(R.string.wait_quality));
                    } else {
                        switch (qualityListBean.getQcResult()) {
                            case 1://合格
                                holder.setTextView(R.id.tv_quality_result, R.color.tab_txt_unselect, getString(R.string.excel_head_quality_num));
                                break;
                            case 2://待定
                                holder.setTextView(R.id.tv_quality_result, R.color.wait_quality, getString(R.string.wait_deal));
                                break;
                            case 3://不合格
                                holder.setTextView(R.id.tv_quality_result, R.color.unquality, getString(R.string.unquality));
                                break;
                            case 4://挑选
                                holder.setTextView(R.id.tv_quality_result, R.color.unquality, getString(R.string.quality_pick));
                                break;
                            case 5://特采
                                holder.setTextView(R.id.tv_quality_result, R.color.unquality, getString(R.string.quality_special_get));
                                break;
                            case 6://全退
                                holder.setTextView(R.id.tv_quality_result, R.color.unquality, getString(R.string.quality_all_return));
                                break;

                        }
                    }
                    /**
                     * 物品名称
                     */
                    holder.setTextView(R.id.tv_material_name, qualityListBean.getMaterialName());
                    /**
                     * 到货日期
                     */
                    setTextViewText(holder.getTextView(R.id.tv_arrive_date), R.string.item_arrive_date, qualityListBean.getReceiptDate());
                    /**
                     * 供应商
                     */
                    holder.setTextView(R.id.tv_supplier, qualityListBean.getSupplierName());
                    /**
                     * 单号
                     */
                    setTextViewText(holder.getTextView(R.id.tv_arrive_orderno), R.string.item_arrive_orderno, qualityListBean.getReceiptCode());
                    /**
                     * 底部的分割线
                     */
                    if (position == datas.size() - 1) {
                        holder.getView(R.id.view_divide_bottom).setVisibility(View.VISIBLE);
                    } else {
                        holder.getView(R.id.view_divide_bottom).setVisibility(View.GONE);

                    }
                }
            };
            rlvQuality.setAdapter(adapter);
            rlvQuality.setLayoutManager(new LinearLayoutManager(this));
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    QualityListBean qualityListBean = mDatas.get(position);
                    if (qualityListBean.getQcStatus() != 0 && qualityListBean.getQcResult() != 2) {
                        return;
                    }

                    /**
                     * 免检
                     */
                    if (qualityListBean.getQcType() == 0) {
                        showDontNeedQualityDialog(position);
                    }
                    /**
                     * 待定的来料 需要先设置AQL
                     */
                    else if (qualityListBean.getQcResult() == 2) {//待定的状态 单独处理
                        /**
                         * 对dialog 进行判断 是否进行网络请求
                         */
                        if (null == setAQLDialog) {
                            showProgressDialog();
                            Map params = new HashMap();
                            params.put("UserId", SpUtils.getInstance().getUserId());
                            params.put("OrgId", SpUtils.getInstance().getOrgId());
                            params.put("mac", PackageUtils.getMac());
                            params.put("ReceiveQty", mDatas.get(position).getReceiveQty());
                            getPresenter().getAQLList(params, position);
                        } else {
                            setAQLDialog.show();
                        }
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("ReceiptId", qualityListBean.getReceiptId());
                        intent.putExtra("ReceiptDetailId", qualityListBean.getReceiptDetailId());
                        switch (qualityListBean.getQcType()) {
                            case 1:
                                /**
                                 * 普通检验
                                 */
                                intent.setClass(QualityCheckActivity.this, NormalQualityActivity.class);
                                break;
                            case 2://高级质检1
                                intent.setClass(QualityCheckActivity.this, Advance1QualityActivity.class);
                                break;
                            case 3://高级质检2
                                intent.setClass(QualityCheckActivity.this, AdvanceQualityActivity.class);
                                break;
                            default:
                                intent.setClass(QualityCheckActivity.this, NormalQualityActivity.class);
                                break;
                        }
                        startActivity(intent);
                    }
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void queryReceiptForIQC(int position) {
        /**
         * 免检 请求通过  设置免检的条目的数据
         */
        QualityListBean qualityListBean = mDatas.get(position);
        qualityListBean.setQcStatus(3);
        qualityListBean.setQcResult(1);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getAQLList(GetAQLList datas, final int position) {
        levelCodeLists = datas.getLevelCodeLists();
        aqlLists = datas.getAqlLists();
        /**
         *
         * 设置AQL 参数的弹框
         */
        /**
         * 标准水平的spinner
         */
        setAQLDialog = new MyDialog(QualityCheckActivity.this, R.layout.dialog_set_aql);
        Spinner spStandardLevel = (Spinner) setAQLDialog.getView(R.id.spinner_standard_level);
        adapterStandardLevel = new ArrayAdapter<>(QualityCheckActivity.this, android.R.layout.simple_list_item_1, levelCodeLists);
        adapterStandardLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStandardLevel.setAdapter(adapterStandardLevel);
        /**
         * aql spinner
         */
        Spinner spAQL = (Spinner) setAQLDialog.getView(R.id.spinner_aql);
        adapterAQL = new ArrayAdapter<>(QualityCheckActivity.this, android.R.layout.simple_list_item_1, aqlLists);
        adapterAQL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAQL.setAdapter(adapterAQL);

        /**
         * 标准水平的点击事件
         */
        spStandardLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                standardLevelStr = levelCodeLists.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /**
         * aql列表的点击事件
         */
        spAQL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                aqlStr = aqlLists.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /**
         * 取消
         */
        setAQLDialog.setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
                setAQLDialog.dismiss();
            }
        });
        /**
         * 确定
         */
        setAQLDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
                if (TextUtils.isEmpty(standardLevelStr)) {
                    ToastUtils.showShort(getString(R.string.please＿select_standard_level));
                    return;
                }
                if (TextUtils.isEmpty(aqlStr)) {
                    ToastUtils.showShort(getString(R.string.please_select_aql));
                    return;
                }
                /**
                 * 设置AQL的请求
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("ReceiptId", mDatas.get(position).getReceiptId());
                params.put("ReceiptDetailId", mDatas.get(position).getReceiptDetailId());
                params.put("LevelCode", standardLevelStr);
                params.put("AQL", aqlStr);
                getPresenter().setAQLValue(params, position);
            }
        });
        /**
         * 叉叉
         */
        setAQLDialog.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAQLDialog.dismiss();
            }
        });
        setAQLDialog.show();
    }

    @Override
    public void setAQLValue(int position) {
        ToastUtils.showShort("设置AQL参数成功！");
        Intent intent = new Intent();
        intent.putExtra("ReceiptId", mDatas.get(position).getReceiptId());
        intent.putExtra("ReceiptDetailId", mDatas.get(position).getReceiptDetailId());
        switch (mDatas.get(position).getQcType()) {
            case 1:
                /**
                 * 普通检验
                 */
                intent.setClass(QualityCheckActivity.this, NormalQualityActivity.class);
                break;
            case 2://高级质检1
                intent.setClass(QualityCheckActivity.this, Advance1QualityActivity.class);
                break;
            case 3://高级质检2
                intent.setClass(QualityCheckActivity.this, AdvanceQualityActivity.class);
                break;
            default:
                intent.setClass(QualityCheckActivity.this, NormalQualityActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresgQualityList(QualityEvent event) {
        if (event.getEvent().equals(QualityEvent.QUALITY_SUCCESS)) {
            /**
             * 清除数据 并且重新加载数据
             */
            mDatas.clear();
            if (null == myDialog) {
                initData();
            } else {
                /**
                 * 调用点击事件
                 */
                myDialog.getView(R.id.btn_confirm).callOnClick();
            }
        }

    }
}
