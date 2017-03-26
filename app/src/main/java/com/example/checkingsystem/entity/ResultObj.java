package com.example.checkingsystem.entity;

import java.io.Serializable;

/**
 * Created by eggyer on 2017/3/17.
 */
public class ResultObj<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    private Meta meta;
    private T data;
    private PagerResult pagerResult;

    public ResultObj() {}

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public PagerResult getPagerResult() {
        return pagerResult;
    }

    public void setPagerResult(PagerResult pagerResult) {
        this.pagerResult = pagerResult;
    }



}
