package com.timi.sz.wms_android.mvp.UI.query.orderno_barcode;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.bean.query.response.QueryBillBarcodeResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ;
 * '
 * '''''
 * <p>
 * 单据条码
 * author: timi
 * create at: 2018/2/6 13:48
 */
public class OrdernoBarcodeActivity extends BaseActivity<OrdernoBarcodeView, OrdernoBarcodePresenter> implements OrdernoBarcodeView {


    @BindView(R.id.spinner_select_type)
    Spinner spinnerSelectType;
    @BindView(R.id.et_barcode_num)
    EditText etBarcodeNum;
    @BindView(R.id.tv_businness_type)
    TextView tvBusinnessType;
    @BindView(R.id.tv_out_in_type)
    TextView tvOutInType;
    @BindView(R.id.tv_barcode_num)
    TextView tvBarcodeNum;
    @BindView(R.id.tv_barcode_date)
    TextView tvBarcodeDate;
    @BindView(R.id.rlv_barcode_query)
    RecyclerView rlvBarcodeQuery;
    @BindView(R.id.ll_query_result)
    LinearLayout llQueryResult;
    private PopupWindow mPop;

    @Override
    public int setLayoutId() {
        return R.layout.activity_orderno_barcode;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("单据条码查询");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public OrdernoBarcodePresenter createPresenter() {
        return new OrdernoBarcodePresenter(this);
    }

    @Override
    public OrdernoBarcodeView createView() {
        return this;
    }

    @Override
    public void queryBillBarcode(QueryBillBarcodeResult o) {

    }

    public void showSelectLanguagePopwindow(View view) {
        if (null == mPop) {
            mPop = new PopupWindow(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            View content = LayoutInflater.from(this).inflate(R.layout.popwindow_select_language, null);
            final TextView tvSimple = (TextView) content.findViewById(R.id.tv_language_simple);
            tvSimple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSimple.setText(getResources().getString(R.string.language_simple));
                    setCurrentActivityLanguage(0);
                    //发送事件 更新主界面的文字
                }
            });
            final TextView tvTrad = (TextView) content.findViewById(R.id.tv_language_trad);
            tvTrad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvTrad.setText(getResources().getString(R.string.language_tradtional));
                    setCurrentActivityLanguage(1);
                }
            });
            final TextView tvEnglish = (TextView) content.findViewById(R.id.tv_language_en);
            tvEnglish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvEnglish.setText(getResources().getString(R.string.language_tradtional));
                    setCurrentActivityLanguage(2);
                }
            });
            mPop.setContentView(content);
            mPop.setOutsideTouchable(false);
            mPop.setTouchable(true);
            mPop.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
            mPop.setAnimationStyle(R.style.popWindow_animation_push);
        }
        if (null != mPop) {
            //显示窗体
            mPop.showAsDropDown(view);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
