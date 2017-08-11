package com.timi.sz.wms_android.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by timi on 2016/12/19.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    private CommonViewHolder(View itemView) {
        super(itemView);
        mConvertView=itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 创建ViewHolder
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
}
