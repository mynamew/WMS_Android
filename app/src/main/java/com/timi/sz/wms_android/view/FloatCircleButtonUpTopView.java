package com.timi.sz.wms_android.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * $dsc   单据详情 返回到顶部的view
 * 1、滑动的时候显示当前多少条数据 和当前处于多少条数据
 * 2、 不滑动时 显示回到顶部
 * author: timi
 * create at: 2017-11-05 08:59
 */

public class FloatCircleButtonUpTopView extends AutoLinearLayout {
    private LinearLayout llUpTop;
    private AutoRelativeLayout llCurrentItem;
    private TextView tvCurrentPosition;
    private TextView tvTotalNum;

    public FloatCircleButtonUpTopView(Context context) {
        super(context);
        initView(context);
    }

    public FloatCircleButtonUpTopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FloatCircleButtonUpTopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_float_button_up_top, null);
        llUpTop = inflate.findViewById(R.id.ll_up_top);
        llCurrentItem = inflate.findViewById(R.id.ll_current_item);
        tvCurrentPosition = inflate.findViewById(R.id.tv_current_num);
        tvTotalNum = inflate.findViewById(R.id.tv_total_num);
        this.addView(inflate);
    }

    /**
     * 显示 滑到顶部的布局 并且提供点击事件
     *
     * @param listener
     */
    public void showUpTop(OnClickListener listener) {
        llCurrentItem.setVisibility(GONE);
        llUpTop.setVisibility(VISIBLE);
        llUpTop.setOnClickListener(listener);
    }

    /**
     * 设置显示 当前item 的布局，并且显示当前的位置，不断的刷新
     */
    public void showCurrentItem(int position, int totalNum) {
        llCurrentItem.setVisibility(VISIBLE);
        llUpTop.setVisibility(GONE);
        tvCurrentPosition.setText(String.valueOf(position));
        tvTotalNum.setText(String.valueOf(totalNum));
    }
}
