package com.example.checkingsystem.entity;

import java.io.Serializable;

/**
 * Created by eggyer on 2017/3/17.
 */
public class PagerResult implements Serializable{
    private static final long serialVersionUID = 1L;

    /**每页显示*/
    private int pageSize = 20;
    /**页码*/
    private int pageNo = 1;
    /**开始数*/
    private int start = 0;
    /**总条数*/
    private int totalRows = 0;
    /**总页码*/
    private int pageCount = 0;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
