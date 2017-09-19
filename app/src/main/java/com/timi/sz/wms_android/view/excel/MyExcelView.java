package com.timi.sz.wms_android.view.excel;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.timi.sz.wms_android.R;

import java.util.ArrayList;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-13 10:43
 */

public class MyExcelView extends LinearLayout {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 线性布局
     */
    private LinearLayout llContent;
    /**
     * 表格数据，每一行为一条数据，从表头计算
     */
    private ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
    /**
     * 是否锁定首行
     */
    private boolean isLockFristRow = true;
    /**
     * 最大列宽(dp)
     */
    private int maxColumnWidth;
    /**
     * 最小列宽(dp)
     */
    private int minColumnWidth;
    /**
     * 最大行高(dp)
     */
    private int maxRowHeight;
    /**
     * 最小行高dp)
     */
    private int minRowHeight;
    /**
     * 第一行背景颜色
     */
    private int mFristRowBackGroudColor;
    /**
     * 数据为空时的缺省值
     */
    private String mNullableString;
    /**
     * 单元格字体大小
     */
    private int mTextViewSize;
    /**
     * 表格头部字体颜色
     */
    private int mTableHeadTextColor;
    /**
     * 表格内容字体颜色
     */
    private int mTableContentTextColor;
    /**
     * 记录每列最大宽度
     */
    private ArrayList<Integer> mColumnMaxWidths = new ArrayList<Integer>();
    /**
     * 记录每行最大高度
     */
    private ArrayList<Integer> mRowMaxHeights = new ArrayList<Integer>();
    /**
     * 把所有的滚动视图放图列表，后面实现联动效果
     */
    private ArrayList<CustomHorizontalScrollView> mScrollViews = new ArrayList<>();
    /**
     * 表格监听事件
     */
    private OnTableViewListener mTableViewListener;

    /**
     * 上拉加载和下拉刷新
     *
     * @param context
     */
    private SmartRefreshLayout mRefreshLayout;

    public MyExcelView(Context context) {
        super(context, null);
    }

    public MyExcelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        /**
         * 初始化表格的属性
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyExcelView);
        maxColumnWidth = (int) typedArray.getDimension(R.styleable.MyExcelView_maxWidth, 1000);
        minColumnWidth = (int) typedArray.getDimension(R.styleable.MyExcelView_minWidth, 50);
        maxRowHeight = (int) typedArray.getDimension(R.styleable.MyExcelView_maxHeight, 100);
        minRowHeight = (int) typedArray.getDimension(R.styleable.MyExcelView_minHeight, 10);
        mTableHeadTextColor = typedArray.getInt(R.styleable.MyExcelView_firshTabColor, R.color.white);
        mTableContentTextColor = typedArray.getInt(R.styleable.MyExcelView_contentColor, R.color.black);
        mFristRowBackGroudColor = typedArray.getInt(R.styleable.MyExcelView_firshTabBackground, R.color.beijin);
        mTextViewSize = typedArray.getInteger(R.styleable.MyExcelView_textSize, 16);
        isLockFristRow = typedArray.getBoolean(R.styleable.MyExcelView_isLockFristRow, false);
        typedArray.recycle();
        initView(context);
    }

    public MyExcelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化view
     *
     * @param context
     */
    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_excel, null);
        llContent = (LinearLayout) inflate.findViewById(R.id.ll_content);
        mRefreshLayout = (SmartRefreshLayout) inflate.findViewById(R.id.refreshlayout);
        this.addView(inflate);
    }

    /**
     * 设置初始数据
     *
     * @param TableDatas
     */
    public void setExcelFirstData(ArrayList<ArrayList<String>> TableDatas) {
        this.mTableDatas.clear();
        this.mTableDatas.addAll(TableDatas);
        if (mTableDatas != null && mTableDatas.size() > 0) {
            //检查数据，如果有一行数据长度不一致，以最长为标准填"N/A"字符串,如果有null也替换
            int maxLength = 0;
            for (int i = 0; i < mTableDatas.size(); i++) {
                if (mTableDatas.get(i).size() >= maxLength) {
                    maxLength = mTableDatas.get(i).size();
                }
                ArrayList<String> rowDatas = mTableDatas.get(i);
                for (int j = 0; j < rowDatas.size(); j++) {
                    if (rowDatas.get(j) == null || rowDatas.get(j).equals("")) {
                        rowDatas.set(j, mNullableString);
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
                        rowDatas.add(mNullableString);
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

            initTextView();
        } else {
            Toast.makeText(mContext, "表格数据为空！", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 初始化表格textview
     */
    private void initTextView() {
        llContent.removeAllViews();
        View lockTableViewContent = LayoutInflater.from(mContext).inflate(R.layout.locktablecontentview, null);
        LinearLayout lockViewParent = (LinearLayout) lockTableViewContent.findViewById(R.id.lockView_parent);
        CustomHorizontalScrollView lockScrollViewParent = (CustomHorizontalScrollView) lockTableViewContent.findViewById(R.id.lockScrollView_parent);
        //构造滚动视图
        LinearLayout scollViewItemContentView = new LinearLayout(mContext);
        LinearLayout.LayoutParams scollViewItemContentViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        scollViewItemContentView.setLayoutParams(scollViewItemContentViewParams);
        scollViewItemContentView.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < mTableDatas.size(); i++) {
            //顶部分割线
            if (i == 0) {
                View splite = new View(mContext);
                ViewGroup.LayoutParams spliteLayoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        DisplayUtil.dip2px(mContext, 1));
                splite.setLayoutParams(spliteLayoutParam);
                splite.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_gray));
                scollViewItemContentView.addView(splite);
            }
            ArrayList<String> datas = mTableDatas.get(i);
            //设置LinearLayout
            LinearLayout linearLayout = new LinearLayout(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            if (!isLockFristRow) {
                if (i == 0) {
                    linearLayout.setBackgroundColor(ContextCompat.getColor(mContext, mFristRowBackGroudColor));
                }
            }
            for (int j = 0; j < datas.size(); j++) {

                //左侧分隔线
                if (j == 0) {
                    View splitView = new View(mContext);
                    ViewGroup.LayoutParams splitViewParmas = new ViewGroup.LayoutParams(DisplayUtil.dip2px(mContext, 1),
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    splitView.setLayoutParams(splitViewParmas);
                    splitView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_gray));
                    linearLayout.addView(splitView);
                }
                //构造单元格
                TextView textView = new TextView(mContext);
                if (!isLockFristRow) {
                    if (i == 0) {
                        textView.setTextColor(ContextCompat.getColor(mContext, mTableHeadTextColor));
                    } else {
                        textView.setTextColor(ContextCompat.getColor(mContext, mTableContentTextColor));
                    }
                } else {
                    textView.setTextColor(ContextCompat.getColor(mContext, mTableContentTextColor));
                }
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextViewSize);
                textView.setGravity(Gravity.CENTER);
                textView.setText(datas.get(j));
                //设置布局
                LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setPadding(20, 20, 20, 20);
                textView.setLayoutParams(textViewParams);
                ViewGroup.LayoutParams textViewParamsCopy = textView.getLayoutParams();
                textViewParamsCopy.width = DisplayUtil.dip2px(mContext, mColumnMaxWidths.get(j));
                linearLayout.addView(textView);
                //右侧分隔线
                if (j != datas.size() - 1) {
                    View splitView = new View(mContext);
                    ViewGroup.LayoutParams splitViewParmas = new ViewGroup.LayoutParams(DisplayUtil.dip2px(mContext, 1),
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    splitView.setLayoutParams(splitViewParmas);
                    if (!isLockFristRow) {
                        if (i == 0) {
                            splitView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_gray));
                        } else {
                            splitView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_gray));
                        }
                    } else {
                        splitView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_gray));
                    }
                    linearLayout.addView(splitView);
                }
                /**
                 * textView的点击事件
                 */
                final int finalI = i;
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTableViewListener.onTabViewClickListener(finalI);
                    }
                });
            }
            scollViewItemContentView.addView(linearLayout);
            //底部分隔线
            View splite = new View(mContext);
            ViewGroup.LayoutParams spliteLayoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    DisplayUtil.dip2px(mContext, 1));
            splite.setLayoutParams(spliteLayoutParam);
            splite.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_gray));
            scollViewItemContentView.addView(splite);
        }
        lockScrollViewParent.addView(scollViewItemContentView);
        mScrollViews.add(lockScrollViewParent);
        lockScrollViewParent.setOnScrollChangeListener(new CustomHorizontalScrollView.onScrollChangeListener() {
            @Override
            public void onScrollChanged(HorizontalScrollView scrollView, int x, int y) {
                changeAllScrollView(x, y);
            }
        });
        llContent.addView(lockTableViewContent);
    }

    /**
     * 设置追加数据
     *
     * @param addDatas
     */
    public void addExcelData(ArrayList<ArrayList<String>> addDatas) {
        //添加数据
        mTableDatas.addAll(addDatas);
        if (mTableDatas != null && mTableDatas.size() > 0) {
            //检查数据，如果有一行数据长度不一致，以最长为标准填"N/A"字符串,如果有null也替换
            int maxLength = 0;
            for (int i = 0; i < mTableDatas.size(); i++) {
                if (mTableDatas.get(i).size() >= maxLength) {
                    maxLength = mTableDatas.get(i).size();
                }
                ArrayList<String> rowDatas = mTableDatas.get(i);
                for (int j = 0; j < rowDatas.size(); j++) {
                    if (rowDatas.get(j) == null || rowDatas.get(j).equals("")) {
                        rowDatas.set(j, mNullableString);
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
                        rowDatas.add(mNullableString);
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

            initTextView();
        } else {
            Toast.makeText(mContext, "表格数据为空！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 改变所有滚动视图位置
     *
     * @param x
     * @param y
     */
    private void changeAllScrollView(int x, int y) {
        if (mScrollViews.size() > 0) {
            for (int i = 0; i < mScrollViews.size(); i++) {
                CustomHorizontalScrollView scrollView = mScrollViews.get(i);
                scrollView.scrollTo(x, y);
            }
        }
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

    public MyExcelView setTableViewListener(OnTableViewListener mTableViewListener) {
        this.mTableViewListener = mTableViewListener;
        return this;
    }

    /**
     * 设置刷新的监听器
     */
    public void setRefreshLayoutRefreshListener(OnRefreshListener listener) {
        mRefreshLayout.setOnRefreshListener(listener);
    }

    /**
     * 设置刷新的监听器
     */
    public void setRefreshLayoutLoadmoreListener(OnLoadmoreListener listener) {
        mRefreshLayout.setOnLoadmoreListener(listener);
    }

    /**
     * 显示刷新
     */
    public void showRefresh(){
        mRefreshLayout.autoRefresh();
    }

    /**
     * 关闭刷新
     */
    public  void refreshComplete(){
        mRefreshLayout.finishRefresh();
    }
    //回调监听
    public interface OnTableViewListener {
        /**
         * 点击事件
         *
         * @param position
         */
        void onTabViewClickListener(int position);
    }
}
