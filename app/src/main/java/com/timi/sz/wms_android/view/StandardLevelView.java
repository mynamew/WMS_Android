package com.timi.sz.wms_android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-20 16:53
 */

public class StandardLevelView extends LinearLayout {
    private TextView tvTitle;
    private TextView tvContent;
    private String title = "";

    public StandardLevelView(Context context) {
        super(context);
        initView(context);
    }

    public StandardLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StandardLevelView);
        title = typedArray.getString(R.styleable.StandardLevelView_standardLevelTitle);
        typedArray.recycle();
        initView(context);
    }

    public StandardLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化view
     *
     * @param context
     */
    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_quality_level, null);
        tvTitle = inflate.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        tvContent = inflate.findViewById(R.id.tv_content);
        addView(inflate);
    }

    /**
     * 设置内容
     *
     * @param content
     */
    public void setTextViewContent(Object content) {
        if (null != content) {
            tvContent.setText(String.valueOf(content));
        }
    }
}
