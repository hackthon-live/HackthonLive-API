package com.iydsj.sw.common.dto;

import java.util.List;

/**
 * 分页参数
 *
 * @author wangyan
 * @date 2014-11-02 17:19
 */
public class Pager<T> {

    /**
     * 总记录数。
     */
    private int totalCount;

    /**
     * 当前页的记录列表。
     */
    private List<T> list;

    /**
     * 构造函数。
     * @param totalCount 总记录数。
     * @param list 当前页的记录列表。
     */
    public Pager(int totalCount,List<T> list){
        this.totalCount=totalCount;
        this.list=list;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<T> list) {
        this.list = list;
    }
}

