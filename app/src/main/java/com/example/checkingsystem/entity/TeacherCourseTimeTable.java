package com.example.checkingsystem.entity;
import java.sql.Timestamp;

/**
 * Created by eggyer on 2017/4/16.
 */
public class TeacherCourseTimeTable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键sequence-id
     */

    private String courseTimeId;
    /**
     * 外键-课程计划id
     */
    private String courseTimePlanId;
    /**
     * 上课的周
     */
    private Integer courseTimeWeek;
    /**
     * 上课的星期几
     */
    private Integer courseTimeDay;
    /**
     * 课开始的时间
     */
    private Timestamp courseTimeGmtBegin;
    /**
     * 课结束的时间
     */
    private Timestamp courseTimeGmtEnd;
    /**
     * 创建时间
     */
    private Timestamp courseTimeGmtCreated;
    /**
     * 修改时间
     */
    private Timestamp courseTimeGmtModified;
    /**
     * 课程时间记录状态
     */
    private String courseTimeStatus;

    private String courseName;

    public String getCourseTimeId() {
        return courseTimeId;
    }

    public void setCourseTimeId(String courseTimeId) {
        this.courseTimeId = courseTimeId;
    }

    public String getCourseTimePlanId() {
        return courseTimePlanId;
    }

    public void setCourseTimePlanId(String courseTimePlanId) {
        this.courseTimePlanId = courseTimePlanId;
    }

    public Integer getCourseTimeWeek() {
        return courseTimeWeek;
    }

    public void setCourseTimeWeek(Integer courseTimeWeek) {
        this.courseTimeWeek = courseTimeWeek;
    }

    public Integer getCourseTimeDay() {
        return courseTimeDay;
    }

    public void setCourseTimeDay(Integer courseTimeDay) {
        this.courseTimeDay = courseTimeDay;
    }

    public Timestamp getCourseTimeGmtBegin() {
        return courseTimeGmtBegin;
    }

    public void setCourseTimeGmtBegin(Timestamp courseTimeGmtBegin) {
        this.courseTimeGmtBegin = courseTimeGmtBegin;
    }

    public Timestamp getCourseTimeGmtEnd() {
        return courseTimeGmtEnd;
    }

    public void setCourseTimeGmtEnd(Timestamp courseTimeGmtEnd) {
        this.courseTimeGmtEnd = courseTimeGmtEnd;
    }

    public Timestamp getCourseTimeGmtCreated() {
        return courseTimeGmtCreated;
    }

    public void setCourseTimeGmtCreated(Timestamp courseTimeGmtCreated) {
        this.courseTimeGmtCreated = courseTimeGmtCreated;
    }

    public Timestamp getCourseTimeGmtModified() {
        return courseTimeGmtModified;
    }

    public void setCourseTimeGmtModified(Timestamp courseTimeGmtModified) {
        this.courseTimeGmtModified = courseTimeGmtModified;
    }

    public String getCourseTimeStatus() {
        return courseTimeStatus;
    }

    public void setCourseTimeStatus(String courseTimeStatus) {
        this.courseTimeStatus = courseTimeStatus;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
