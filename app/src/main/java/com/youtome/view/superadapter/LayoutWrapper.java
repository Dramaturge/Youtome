package com.youtome.view.superadapter;

/**
 * 布局包装类
 * 参数：int layoutId, int spanSize（可选）, T data, DataHolder<T> holder
 */
public class LayoutWrapper<T> {

    private int layoutId;//布局id
    private int spanSize;//布局所占列数
    private T data;//数据源
    private DataHolder<T> holder;//控制器-负责将数据源绑定到布局上，并设置监听


    /**
     * 布局包装类：包装每个item的布局信息
     * @param layoutId  布局id。注意：layoutId必须是layoutIds数组中的成员！
     * @param spanSize 布局所占列数（可选）
     * @param data 数据源
     * @param holder 控制器-负责将数据源绑定到布局上，并设置监听
     */
    //默认每个Item占spanSize列
    public LayoutWrapper(int layoutId, int spanSize, T data, DataHolder<T> holder) {
        this.layoutId = layoutId;
        this.spanSize = spanSize;
        this.data = data;
        this.holder = holder;
    }
    /**
     * 布局包装类：包装每个item的布局信息，可以设置布局所占列数
     * @param layoutId  布局id。注意：layoutId必须是layoutIds数组中的成员！
     * @param data 数据源
     * @param holder 控制器-负责将数据源绑定到布局上，并设置监听
     */
    //默认每个Item占一列
    public LayoutWrapper(int layoutId, T data, DataHolder<T> holder) {
        this.layoutId = layoutId;
        this.spanSize = 1;
        this.data = data;
        this.holder = holder;
    }

    public DataHolder<T> getHolder() {
        return holder;
    }

    public void setHolder(DataHolder<T> holder) {
        this.holder = holder;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

}
