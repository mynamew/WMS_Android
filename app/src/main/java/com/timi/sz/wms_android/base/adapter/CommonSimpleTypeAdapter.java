package com.timi.sz.wms_android.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/** 
 * base recycleview  adapter
 * @autor timi
 * create at 2017/5/22 14:06
 */
public abstract class CommonSimpleTypeAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    //存储监听回调
    private SparseArray<ItemClickListener> onClickListeners;
    private List<T> dataList;

    public interface ItemClickListener {
        void onItemClicked(View view, int position);
    }

    public CommonSimpleTypeAdapter(List<T> dataList) {
        this.onClickListeners = new SparseArray<>();
        this.dataList = dataList;
    }

    /**
     * 存储viewId对应的回调监听实例listener
     *
     * @param viewId
     * @param listener
     */
    public void setOnItemClickListener(int viewId, ItemClickListener listener) {
        ItemClickListener listener_ = onClickListeners.get(viewId);
        if (listener_ == null) {
            onClickListeners.put(viewId, listener);
        }
    }

    /**
     * 创建viewholder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = getLayoutId(viewType);
        CommonViewHolder holder = CommonViewHolder.createViewHolder(parent, layoutId);
        return holder;
    }

    /**
     * 绑定viewholder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(CommonViewHolder holder,final  int position) {
        T item = dataList.get(position);
        convert(holder, item, position);//子类实现
        //设置点击监听
        for (int i = 0; i < onClickListeners.size(); ++i){
            int id = onClickListeners.keyAt(i);
            View view = holder.getView(id);
            if(view == null)
                continue;
            final ItemClickListener listener = onClickListeners.get(id);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClicked(v,position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList== null?0:dataList.size();
    }

    /**
     * 销毁Adapter 优化
     */
    public void destroyAdapter(){
        if(onClickListeners != null)
            onClickListeners.clear();
        onClickListeners = null;

        if(dataList != null)
            dataList.clear();
        dataList = null;
    }
    /**
     * 获取列表控件的视图id(由子类负责完成)
     *
     * @param viewType
     * @return
     */
    public abstract int getLayoutId(int viewType);

    //更新itemView视图(由子类负责完成)
    public abstract void convert(CommonViewHolder holder, T t, int position);

}
