package com.example.checkingsystem.entity;

import java.io.Serializable;

/**
 * Created by eggyer on 2017/3/17.
 * 返回结果元信息
 */
public class Meta implements Serializable{
    private static final long serialVersionUID = 1L;
    //响应是否成功
    private Boolean result;
    //响应码
    private Integer code;
    //响应信息
    private String msg;
    //信息(日志)
    private String log;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
