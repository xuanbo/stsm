package com.whut.stsm.common.util;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 *
 * Created by null on 2017/2/28.
 */
public class Page<T> implements Serializable {

    private int current;
    private int size;
    // 偏移量
    private int offset;
    private int last;

    private long count;
    private int pages;
    private List<T> list;

    public Page() {
        this.current = 1;
        this.size = 10;
    }

    public Page(int current, int size) {
        this.current = current > 0 ? current : 1;
        this.size = size > 0 ? size : 10;
    }

    public void setCurrent(int current) {
        this.current = current > 0 ? current : 1;
    }

    public void setSize(int size) {
        this.size = size > 0 ? size : 10;
    }

    public int getCurrent() {
        return current;
    }

    public int getSize() {
        return size;
    }

    public int getOffset() {
        return offset;
    }

    public int getLast() {
        return last;
    }

    public long getCount() {
        return count;
    }

    /**
     * 在setList之前调用，否则不会正确的设置分页信息
     *
     * @param count 总记录条数
     */
    public void setCount(long count) {
        this.count = count > 0 ? count : 0;
        // 计算总页数
        this.pages = (int) (this.count % this.size == 0 ? this.count / this.size
                : this.count / this.size + 1);
        // 当前页大于最后一页的情况
        if (this.current > this.pages) {
            this.current = this.pages > 0 ? current : 1;
        }
        this.offset = (this.current - 1) * this.size;
        this.last = this.offset + this.size;
    }

    public int getPages() {
        return pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = CollectionUtils.isEmpty(list) ? new ArrayList<>() : list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "current=" + current +
                ", size=" + size +
                ", offset=" + offset +
                ", last=" + last +
                ", count=" + count +
                ", pages=" + pages +
                ", list=" + list +
                '}';
    }
}
