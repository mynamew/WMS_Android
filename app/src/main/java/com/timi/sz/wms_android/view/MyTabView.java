package com.timi.sz.wms_android.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;

/**
 * $dsc  自定义的选项卡
 * author: timi
 * create at: 2017-10-11 08:42
 */

public class MyTabView extends LinearLayout {
    private Context mContext;
    //2个tab
    private TextView tab1, tab2;

    public MyTabView(Context context) {
        super(context);
        initView(context);
    }

    public MyTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_my_tab, null);
        tab1 = inflate.findViewById(R.id.tab1);
        tab2 = inflate.findViewById(R.id.tab2);
        addView(inflate);
    }
   public void setTabOnclickListener(final OnClickListener listener1, OnClickListener listener2){
       tab1.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View view) {
               listener1.onClick(view);
           }
       });
   }

}
