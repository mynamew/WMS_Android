package com.timi.sz.wms_android.base.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by timi on 2016/12/19.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    private CommonViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param layoutId
     * @return
     */
    public static CommonViewHolder createViewHolder(ViewGroup parent, int layoutId) {
        CommonViewHolder holder = new CommonViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, null));
        return holder;
    }

    /**
     * 根据ItemView的id获取子视图View
     *
     * @param viewId
     * @return
     */
    public View getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    /**
     * 返回整体的view
     *
     * @return
     */
    public View getmConvertView() {
        return mConvertView;
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
            view = (TextView) mConvertView.findViewById(viewId);
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
            view = (TextView) mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        if (null!=content) {
            view.setText(String.valueOf(content));
        }
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
            view = (ImageView) mConvertView.findViewById(viewId);
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
            view = (ImageView) mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        view.setImageResource(resId);
        return view;
    }
}
