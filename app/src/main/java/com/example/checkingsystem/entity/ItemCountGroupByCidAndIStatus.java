package com.example.checkingsystem.entity;

/**
 * Created by eggyer on 2017/6/30.
 */
public class ItemCountGroupByCidAndIStatus {
    private int count;
    private String courseId;
    private String itemStatus;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public ItemCountGroupByCidAndIStatus() {
    }
}
