package com.timi.sz.wms_android.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * base recycleview  adapter
 *
 * @autor timi
 * create at 2017/5/22 14:06
 */
public abstract class CommonSimpleHeaderAndFooterTypeAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    //存储监听回调
    private SparseArray<ItemClickListener> onClickListeners;
    private List<T> dataList;
    protected final List<T> mItems;


    public static final int TYPE_ITEM = 0;
    public static final int TYPE_HEADER = -1;
    public static final int TYPE_FOOTER = -2;

    public interface ItemClickListener {
        void onItemClicked(View view, int position);
    }

    public CommonSimpleHeaderAndFooterTypeAdapter(List<T> dataList) {
        mItems = (dataList != null) ? dataList : new ArrayList<T>();
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
        int layoutId = 0;
        if (viewType == TYPE_ITEM) {
            layoutId = getLayoutId(viewType);
        } else if (viewType == TYPE_HEADER) {
            layoutId = getHeaderLayoutId();
        } else {
            layoutId = getFooterLayoutId();
        }
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
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        if (position == 0) {
            bindHeader(holder, position);
        } else if (position == mItems.size()+1) {
            bindFooter(holder, position);
        } else {
            //调用中间部分（就是数据展示部分的）的绑定数据的方法
            if (mItems != null && mItems.size() > 0) {
                convert(holder, mItems.get(position-1), position);//子类实现
            } else {
                convert(holder, null, position);//子类实现
            }
            //设置点击监听
            for (int i = 0; i < onClickListeners.size(); ++i) {
                int id = onClickListeners.keyAt(i);
                View view = holder.getView(id);
                if (view == null)
                    continue;
                final ItemClickListener listener = onClickListeners.get(id);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onItemClicked(v, position-1);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() + (isHeaderExist() ? 1 : 0) + (isFooterExist() ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (!isHeaderExist() && !isFooterExist())
            //头部和底部都不存在的时候直接使用父类的方法
            return TYPE_ITEM;
        else {
            if (position == getHeaderPosition())
                //即将要加载的布局的位置和头部的位置是一样的返回加载头部的状态值
                return TYPE_HEADER;
            if (position == getFooterPosition())
                //即将要加载的布局的位置和头部的位置是一样的返回加载底部部的状态值
                return TYPE_FOOTER;
            return TYPE_ITEM;
        }
    }

    /**
     * 在加载底部布局的时候需要实现该方法，同时返回底部布局的layoutID
     *
     * @return
     */
    protected int getFooterLayoutId() {
        return -1;
    }

    /**
     * 判断是否存在底部的布局
     *
     * @return
     */
    final public boolean isFooterExist() {
        return getFooterLayoutId() != -1;
    }

    /**
     * 在加载头部布局的时候需要实现该方法，同时返回头部布局的layoutID
     *
     * @return
     */
    protected int getHeaderLayoutId() {
        return -1;
    }

    /**
     * 判断当前头部是否存在不同的布局
     *
     * @return
     */
    final public boolean isHeaderExist() {
        return getHeaderLayoutId() != -1;
    }

    /**
     * 当头部存在的时候返回头部的位置
     *
     * @return
     */
    final public int getHeaderPosition() {
        if (isHeaderExist()) return 0;
        return -1;
    }

    /**
     * 当底部存在的时候返回底部的位置
     *
     * @return
     */
    final public int getFooterPosition() {
        if (isFooterExist()) return getItemCount() - 1;
        return -2;
    }

    /**
     * 重写该方法进行header视图的数据绑定
     *
     * @param holder
     * @param position
     */
    protected void bindHeader(CommonViewHolder holder, int position) {
    }

    /**
     * 重写该方法进行footer视图的数据绑定
     *
     * @param holder
     * @param position
     */

    protected void bindFooter(CommonViewHolder holder, int position) {
    }

    /**
     * 销毁Adapter 优化
     */
    public void destroyAdapter() {
        if (onClickListeners != null)
            onClickListeners.clear();
        onClickListeners = null;

        if (dataList != null)
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
