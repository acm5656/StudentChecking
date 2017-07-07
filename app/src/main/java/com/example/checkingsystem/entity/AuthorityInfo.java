package com.example.checkingsystem.entity;

import java.util.List;

/**
 * Created by eggyer on 2017/4/4.
 */
public class AuthorityInfo {
    private List<String> macList;
    private String virtualCourseId;
    private String stuMac;

    public List<String> getMacList() {
        return macList;
    }

    public void setMacList(List<String> macList) {
        this.macList = macList;
    }

    public String getVirtualCourseId() {
        return virtualCourseId;
    }

    public void setVirtualCourseId(String virtualCourseId) {
        this.virtualCourseId = virtualCourseId;
    }

    public String getStuMac() {
        return stuMac;
    }

    public void setStuMac(String stuMac) {
        this.stuMac = stuMac;
    }
}
