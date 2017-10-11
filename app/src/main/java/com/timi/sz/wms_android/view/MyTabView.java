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
        this.addView(inflate);
    }
    /**
     * 设置监听器
     *
     * @param clickListener
     */
    public MyTabView setTabOnclickListener(final TabClickListener clickListener) {
        tab1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.setBackgroundResource(R.drawable.bg_mytab_selected);
                tab2.setBackgroundResource(R.drawable.bg_mytab_unselected);
                tab1.setTextColor(getResources().getColor(R.color.white));
                tab2.setTextColor(getResources().getColor(R.color.login_txt_color));
                clickListener.tab1Click(view);
            }
        });
        tab2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.setBackgroundResource(R.drawable.bg_mytab_unselected);
                tab2.setBackgroundResource(R.drawable.bg_mytab_selected);
                tab1.setTextColor(getResources().getColor(R.color.login_txt_color));
                tab2.setTextColor(getResources().getColor(R.color.white));
                clickListener.tab2Click(view);
            }
        });
        return this;
    }

    /**
     * 设置tab 的内容
      * @param tab1Content
     * @param tab2Content
     * @return
     */
    public MyTabView setTabContent(String tab1Content, String tab2Content) {
        tab1.setText(tab1Content);
        tab2.setText(tab2Content);
        return this;
    }

    /**
     * 点击事件
     */
    public interface TabClickListener {
        void tab1Click(View view);

        void tab2Click(View view);
    }
}
