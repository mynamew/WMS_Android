package com.timi.sz.wms_android.view.excelview;

import android.content.Context;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * $dsc  表格单元格的测量方法
 * author: timi
 * create at: 2017-09-19 19:12
 */

public class MeasureExcelViewUtils {
    /**
     * 记录每列最大宽度
     */
    private static ArrayList<Integer> mColumnMaxWidths = new ArrayList<Integer>();
    /**
     * 记录每行最大高度
     */
    private static ArrayList<Integer> mRowMaxHeights = new ArrayList<Integer>();
    /**
     * 上下文
     */
    private static Context mContext;

    public int getMaxColumnWidth() {
        return maxColumnWidth;
    }

    public void setMaxColumnWidth(int maxColumnWidth) {
        this.maxColumnWidth = maxColumnWidth;
    }

    public int getMinColumnWidth() {
        return minColumnWidth;
    }

    public void setMinColumnWidth(int minColumnWidth) {
        this.minColumnWidth = minColumnWidth;
    }

    public int getMaxRowHeight() {
        return maxRowHeight;
    }

    public void setMaxRowHeight(int maxRowHeight) {
        this.maxRowHeight = maxRowHeight;
    }

    public int getMinRowHeight() {
        return minRowHeight;
    }

    public void setMinRowHeight(int minRowHeight) {
        this.minRowHeight = minRowHeight;
    }

    public int getmTextViewSize() {
        return mTextViewSize;
    }

    public void setmTextViewSize(int mTextViewSize) {
        this.mTextViewSize = mTextViewSize;
    }

    /**
     * 最大列宽(dp)
     */
    private int maxColumnWidth = 1000;
    /**
     * 最小列宽(dp)
     */
    private int minColumnWidth = 0;
    /**
     * 最大行高(dp)
     */
    private int maxRowHeight = 1000;
    /**
     * 最小行高dp)
     */
    private int minRowHeight = 0;
    /**
     * 文字的大小
     */
    private int mTextViewSize = 14;


    public MeasureExcelViewUtils(Context context) {
        mContext = context;
    }

    /**
     * 测量每个单元格的宽高
     *
     * @param mTableDatas
     * @return
     */
    public MeasureExcelViewUtils getExcelViewWidth(ArrayList<ArrayList<String>> mTableDatas) {
        //检查数据，如果有一行数据长度不一致，以最长为标准填"N/A"字符串,如果有null也替换
        int maxLength = 0;
        for (int i = 0; i < mTableDatas.size(); i++) {
            if (mTableDatas.get(i).size() >= maxLength) {
                maxLength = mTableDatas.get(i).size();
            }
            ArrayList<String> rowDatas = mTableDatas.get(i);
            for (int j = 0; j < rowDatas.size(); j++) {
                if (rowDatas.get(j) == null || rowDatas.get(j).equals("")) {
                    rowDatas.set(j, "");
                }
            }
            mTableDatas.set(i, rowDatas);
        }
//            Log.e("每行最多个数",maxLength+"");
        for (int i = 0; i < mTableDatas.size(); i++) {
            ArrayList<String> rowDatas = mTableDatas.get(i);
            if (rowDatas.size() < maxLength) {
                int size = maxLength - rowDatas.size();
                for (int j = 0; j < size; j++) {
                    rowDatas.add("");
                }
                mTableDatas.set(i, rowDatas);
            }
        }
        //初始化每列最大宽度
        for (int i = 0; i < mTableDatas.size(); i++) {
            ArrayList<String> rowDatas = mTableDatas.get(i);
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < rowDatas.size(); j++) {
                TextView textView = new TextView(mContext);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextViewSize);
                textView.setText(rowDatas.get(j));
                textView.setGravity(Gravity.CENTER);
                //设置布局
                LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setPadding(10, 10, 10, 10);
                textView.setLayoutParams(textViewParams);
                if (i == 0) {
                    mColumnMaxWidths.add(measureTextWidth(textView, rowDatas.get(j)));
                    buffer.append("[" + measureTextWidth(textView, rowDatas.get(j)) + "]");
                } else {
                    int length = mColumnMaxWidths.get(j);
                    int current = measureTextWidth(textView, rowDatas.get(j));
                    if (current > length) {
                        mColumnMaxWidths.set(j, current);
                    }
                    buffer.append("[" + measureTextWidth(textView, rowDatas.get(j)) + "]");
                }
            }
            Log.e("第" + i + "行列最大宽度", buffer.toString());
        }
        Log.e("每列最大宽度dp:", mColumnMaxWidths.toString());


        //初始化每行最大高度
        for (int i = 0; i < mTableDatas.size(); i++) {
            ArrayList<String> rowDatas = mTableDatas.get(i);
            StringBuffer buffer = new StringBuffer();

            TextView textView = new TextView(mContext);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextViewSize);
            textView.setGravity(Gravity.CENTER);
            //设置布局
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setPadding(10, 10, 10, 10);
            textView.setLayoutParams(textViewParams);
            int maxHeight = measureTextHeight(textView, rowDatas.get(0));
            mRowMaxHeights.add(maxHeight);
            for (int j = 0; j < rowDatas.size(); j++) {
                int currentHeight = measureTextHeight(textView, rowDatas.get(j));
                buffer.append("[" + currentHeight + "]");
                if (currentHeight > maxHeight) {
                    mRowMaxHeights.set(i, currentHeight);
                }
            }
            Log.e("第" + i + "行高度", buffer.toString());
        }
        Log.e("每行最大高度dp:", mRowMaxHeights.toString());
        return this;
    }

    /**
     * 获取每个单元格的最大宽度
     *
     * @return
     */
    public ArrayList<Integer> getAllRowWidth() {
        return mColumnMaxWidths;
    }

    /**
     * 获取每行的最大高度
     * @return
     */
    public ArrayList<Integer> getAllRowHeight() {
        return mRowMaxHeights;
    }

    /**
     * 根据最大最小值，计算TextView的宽度
     *
     * @param textView
     * @param text
     * @return
     */
    private int measureTextWidth(TextView textView, String text) {
        if (textView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            int width = DisplayUtil.px2dip(mContext, layoutParams.leftMargin) +
                    DisplayUtil.px2dip(mContext, layoutParams.rightMargin) + textView.getPaddingLeft() + textView.getPaddingRight() +
                    getTextViewWidth(textView, text);
            if (width <= minColumnWidth) {
                return minColumnWidth;
            } else if (width > minColumnWidth && width <= maxColumnWidth) {
                return width;
            } else {
                return maxColumnWidth;
            }
        }
        return 0;
    }

    /**
     * 计算TextView高度
     *
     * @param textView
     * @param text
     * @return
     */
    private int measureTextHeight(TextView textView, String text) {
        if (textView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            int height = getTextViewHeight(textView, text) + textView.getPaddingTop() + textView.getPaddingBottom();
            if (height < minRowHeight) {
                return minRowHeight;
            } else if (height > minRowHeight && height < maxRowHeight) {
                return height;
            } else {
                return maxRowHeight;
            }
        }
        return 0;
    }

    /**
     * 根据文字计算textview的高度
     *
     * @param textView
     * @param text
     * @return
     */
    private int getTextViewHeight(TextView textView, String text) {
        if (textView != null) {
            int width = measureTextWidth(textView, text);
            TextPaint textPaint = textView.getPaint();
            StaticLayout staticLayout = new StaticLayout(text, textPaint, DisplayUtil.dip2px(mContext, width), Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
            int height = DisplayUtil.px2dip(mContext, staticLayout.getHeight());
            return height;
        }
        return 0;
    }

    /**
     * 根据文字计算textview的高度
     *
     * @param view
     * @param text
     * @return
     */
    private int getTextViewWidth(TextView view, String text) {
        if (view != null) {
            TextPaint paint = view.getPaint();
            return DisplayUtil.px2dip(mContext, (int) paint.measureText(text));
        }
        return 0;
    }

}
