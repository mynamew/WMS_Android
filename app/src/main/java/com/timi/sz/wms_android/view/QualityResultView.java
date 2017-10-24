package com.timi.sz.wms_android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;

/**
 * $dsc 检验结果的view
 * author: timi
 * create at: 2017-10-21 08:42
 */

public class QualityResultView extends LinearLayout {
    private TextView tvTitle;
    private ImageView ivContent;
    private String title = "";

    public QualityResultView(Context context) {
        super(context);
        initView(context);
    }

    public QualityResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public QualityResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_quality_result, null);
        tvTitle = inflate.findViewById(R.id.tv_title);
        ivContent = inflate.findViewById(R.id.iv_content);
        addView(inflate);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title) && null != tvTitle) {
            tvTitle.setText(title);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(Object title) {
        if (null != title && null != tvTitle) {
            tvTitle.setText(String.valueOf(title));
        }
    }

    /**
     * 设置 检验结果是否为合格
     *
     * @param isQuality
     */
    public void setQualityResult(boolean isQuality) {
        if (null != ivContent) {
            ivContent.setSelected(isQuality);
        }
    }

}
