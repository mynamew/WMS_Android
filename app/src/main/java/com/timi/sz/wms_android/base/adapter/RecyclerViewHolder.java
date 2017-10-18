package com.timi.sz.wms_android.base.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;


/**
 * Created by tlh on 2016/2/15.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;//集合类，layout里包含的View,以view的id作为key，value是view对象
    private Context mContext;//上下文对象

    public RecyclerViewHolder(Context ctx, View itemView) {
        super(itemView);
        mContext = ctx;
        mViews = new SparseArray<View>();
    }

    public View getItemView() {
        return itemView;
    }

    /*
     * 通过空间id在SparseArray集合中找出用户View
     * @param viewId  控件的id
     * @param <T>  具体的是那个控件
     * @return  当然是返回你要找的控件了
     */
    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 通过findViewById以及控件id的到用户的空间对象
     *
     * @param viewId
     * @return
     */
    public View getView(int viewId) {
        return findViewById(viewId);
    }


    public CheckBox getCheckBox(int viewId) {
        return (CheckBox) getView(viewId);
    }

    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }


    public ImageButton getImageButton(int viewId) {
        return (ImageButton) getView(viewId);
    }

    public EditText getEditText(int viewId) {
        return (EditText) getView(viewId);
    }

    public RecyclerViewHolder setText(int viewId, String value) {
        TextView view = findViewById(viewId);
        view.setText(value);
        return this;
    }

    public RecyclerViewHolder setBackground(int viewId, int resId) {
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }


    /**
     * 根据ItemView的id获取子视图View
     *
     * @param viewId
     * @return
     */
    public TextView getTextView(int viewId) {
        TextView view = (TextView) mViews.get(viewId);
        if (view == null) {
            view = (TextView) itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    /**
     * 根据ItemView的id获取子视图View 设置文本
     *
     * @param viewId
     * @return
     */
    public TextView setTextView(int viewId, Object content) {
        TextView view = (TextView) mViews.get(viewId);
        if (view == null) {
            view = (TextView) itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        if (null != content) {
            view.setText(String.valueOf(content));
        }
        return view;
    }

    /**
     * 根据ItemView的id获取子视图View 设置文本
     *
     * @param viewId
     * @return
     */
    public TextView setTextView(int viewId, int color, Object content) {
        TextView view = (TextView) mViews.get(viewId);
        if (view == null) {
            view = (TextView) itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        if (null != content) {
            view.setText(String.valueOf(content));
        }
        view.setTextColor(mContext.getColor(color));
        return view;
    }

    /**
     * 根据ItemView的id获取子视图View 并设置 图片
     *
     * @param viewId
     * @return
     */
    public ImageView getImageView(int viewId) {
        ImageView view = (ImageView) mViews.get(viewId);
        if (view == null) {
            view = (ImageView) itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    /**
     * 根据ItemView的id获取子视图View 并设置 图片
     *
     * @param viewId
     * @return
     */
    public ImageView setImageView(int viewId, int resId) {
        ImageView view = (ImageView) mViews.get(viewId);
        if (view == null) {
            view = (ImageView) itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        view.setImageResource(resId);
        return view;
    }

    /**
     * 通过该方法可以的到对应控件的点击事件
     *
     * @param viewId   控件的id
     * @param listener 需要实现的监听器
     * @return
     */
    public RecyclerViewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }


}
