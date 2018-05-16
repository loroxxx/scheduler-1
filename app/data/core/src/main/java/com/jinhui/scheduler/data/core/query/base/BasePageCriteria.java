package com.jinhui.scheduler.data.core.query.base;

/**
 * Created by jinhui on 2017/6/1.
 */
public abstract class BasePageCriteria {
    private int size;
    private int offset;
    private String sort;

    public BasePageCriteria() {
        size = 10;
        offset = 0;
        sort = null;
    }

    public int getCurrentPage(){
        return (int) Math.ceil((double)offset / size);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "BasePageCriteria{" +
                "size=" + size +
                ", offset=" + offset +
                ", sort='" + sort + '\'' +
                '}';
    }
}
