package com.timi.sz.wms_android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.timi.sz.wms_android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * $dsc  质检结果的view
 * author: timi
 * create at: 2017-12-12 17:30
 */

public class QualityResultView extends LinearLayout implements View.OnClickListener {
    private Button btnQuality, btnUnQuality, btnWaitDeal;
    private List<Button> btns = new ArrayList<>();
    private int tabQty = 3;//默认质检结果有3个 合格 不合格 待定
    private int qcResult = -1;//合格的 0  不合格 1 待定 2

    private Context mContext;
    private boolean isCanClick = true;
    public static final int QUALITY_RESULT_DEFAULT = -1;
    public static final int QUALITY_RESULT_SUCCESS = 0;
    public static final int QUALITY_RESULT_FAIL = 1;
    public static final int QUALITY_RESULT_WAIT_DEAL = 2;

    public QualityResultView(Context context) {
        super(context);
        initView(context);
    }

    public QualityResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        /**
         * 初始化表格的属性
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QualityResultView);
        tabQty = typedArray.getInt(R.styleable.QualityResultView_tabNum, 3);
        isCanClick = typedArray.getBoolean(R.styleable.QualityResultView_canClick, true);
        typedArray.recycle();
        initView(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void initView(Context context) {
        mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_quality_result, null);
        //初始化
        btnQuality = inflate.findViewById(R.id.btn_qualified);
        btnUnQuality = inflate.findViewById(R.id.btn_unqualified);
        btnWaitDeal = inflate.findViewById(R.id.btn_wait_deal);
        //tab的数量 如果不是3 设置待定为 GONE
        if (tabQty != 3) {
            btnWaitDeal.setVisibility(INVISIBLE);
        }
        //加入 view
        btns.add(btnQuality);
        btns.add(btnUnQuality);
        btns.add(btnWaitDeal);
        //设置监听器
        if (isCanClick) {
            btnQuality.setOnClickListener(this);
            btnUnQuality.setOnClickListener(this);
            btnWaitDeal.setOnClickListener(this);
        }
        addView(inflate);
    }

    /**
     * 设置是否可点击
     *
     * @param isCanClick
     */
    public void setQualityResultCanClick(boolean isCanClick) {
        //不可点击
        btnQuality.setClickable(isCanClick);
        btnUnQuality.setClickable(isCanClick);
        btnWaitDeal.setClickable(isCanClick);
        if (isCanClick) {
            //监听器
            btnUnQuality.setOnClickListener(this);
            btnWaitDeal.setOnClickListener(this);
            btnQuality.setOnClickListener(this);
        } else {
            //监听器
            btnUnQuality.setOnClickListener(null);
            btnWaitDeal.setOnClickListener(null);
            btnQuality.setOnClickListener(null);
        }

    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < btns.size(); i++) {
            btns.get(i).setSelected(false);
            btns.get(i).setTextColor(view.getResources().getColor(R.color.color_666));
        }
        int clickPosition = 0;
        switch (view.getId()) {
            case R.id.btn_qualified://合格
                clickPosition = 0;
                qcResult = QUALITY_RESULT_SUCCESS;
                break;
            case R.id.btn_unqualified://不合格
                clickPosition = 1;
                qcResult = QUALITY_RESULT_FAIL;
                break;
            case R.id.btn_wait_deal://待定
                clickPosition = 2;
                qcResult = QUALITY_RESULT_WAIT_DEAL;
                break;
        }
        btns.get(clickPosition).setSelected(true);
        btns.get(clickPosition).setTextColor(view.getResources().getColor(R.color.white));
        btns.get(1).setTextColor(view.getResources().getColor(R.color.red));
    }

    /**
     * 设置质检结果
     *
     * @param qcResult
     */
    public void setQualityResult(int qcResult) {
        /**
         * 通过 qcResult 设置不同的状态值
         */
        this.qcResult = qcResult;
        //设置按钮状态
        for (int i = 0; i < btns.size(); i++) {
            btns.get(i).setSelected(false);
            btns.get(i).setTextColor(mContext.getResources().getColor(R.color.color_666));
        }
        btns.get(qcResult).setSelected(true);
        if (qcResult == QUALITY_RESULT_FAIL) {
            btns.get(qcResult).setTextColor(mContext.getResources().getColor(R.color.red));
        } else {
            btns.get(qcResult).setTextColor(mContext.getResources().getColor(R.color.white));
        }
    }

    /**
     * 清楚所有状态
     */
    public void clearBtnsStatus() {
        //重置质检结果
        qcResult = -1;
        //清楚按钮状态
        for (int i = 0; i < btns.size(); i++) {
            btns.get(i).setSelected(false);
            btns.get(i).setTextColor(mContext.getResources().getColor(R.color.color_666));
        }
    }

    /**
     * 获取 质检结果
     *
     * @return
     */
    public int getQcResult() {
        return qcResult;
    }
}
