package com.example.checkingsystem.entity;

import android.graphics.Bitmap;

import java.sql.Timestamp;

/**
 * Created by 那年.盛夏 on 2017/7/9.
 */

public class ClassLeaveShow {

    public static final String STATUS_WAIT_HANDLER = "nondeal";
    public static final String STATUS_PERMITTED = "permitted";
    public static final String STATUS_REFUSE = "refused";
    public static final String STATUS_INVALID = "invalid";

    private String virtualCourseLeaveId;
    private String virtualCourseLeaveStuId;
    private String virtualCourseLeaveReason;
    private String virtualCourseLeaveFeedback;
    private Timestamp virtualCourseLeaveBegin;
    private Timestamp virtualCourseLeaveEnd;
    private Timestamp virtualCourseLeaveGmtCreated;
    private Timestamp virtualCourseLeaveGmtModified;
    private String virtualCourseLeaveStatus;

    private String studentSchoolName;
    private String studentName;

    private String studentImgUrl;
    private Bitmap studentBitmap;

    public ClassLeaveShow()
    {

    }
    public ClassLeaveShow(VirtualCourseLeave virtualCourseLeave)
    {
        virtualCourseLeaveId         = virtualCourseLeave.getVirtualCourseLeaveId()   ;
        virtualCourseLeaveStuId      = virtualCourseLeave.getVirtualCourseLeaveStuId() ;
        virtualCourseLeaveReason     = virtualCourseLeave.getVirtualCourseLeaveReason()   ;
        virtualCourseLeaveFeedback   = virtualCourseLeave.getVirtualCourseLeaveFeedback()   ;
        virtualCourseLeaveBegin      = virtualCourseLeave.getVirtualCourseLeaveBegin()   ;
        virtualCourseLeaveEnd        = virtualCourseLeave.getVirtualCourseLeaveEnd()   ;
        virtualCourseLeaveGmtCreated = virtualCourseLeave.getVirtualCourseLeaveGmtCreated()   ;
        virtualCourseLeaveGmtModified= virtualCourseLeave.getVirtualCourseLeaveGmtModified()   ;
        virtualCourseLeaveStatus     = virtualCourseLeave.getVirtualCourseLeaveStatus()   ;
    }

    public Timestamp getVirtualCourseLeaveBegin() {
        return virtualCourseLeaveBegin;
    }

    public void setVirtualCourseLeaveBegin(Timestamp virtualCourseLeaveBegin) {
        this.virtualCourseLeaveBegin = virtualCourseLeaveBegin;
    }

    public String getVirtualCourseLeaveId() {
        return virtualCourseLeaveId;
    }

    public void setVirtualCourseLeaveId(String virtualCourseLeaveId) {
        this.virtualCourseLeaveId = virtualCourseLeaveId;
    }

    public String getVirtualCourseLeaveStuId() {
        return virtualCourseLeaveStuId;
    }

    public void setVirtualCourseLeaveStuId(String virtualCourseLeaveStuId) {
        this.virtualCourseLeaveStuId = virtualCourseLeaveStuId;
    }

    public String getVirtualCourseLeaveReason() {
        return virtualCourseLeaveReason;
    }

    public void setVirtualCourseLeaveReason(String virtualCourseLeaveReason) {
        this.virtualCourseLeaveReason = virtualCourseLeaveReason;
    }

    public String getVirtualCourseLeaveFeedback() {
        return virtualCourseLeaveFeedback;
    }

    public void setVirtualCourseLeaveFeedback(String virtualCourseLeaveFeedback) {
        this.virtualCourseLeaveFeedback = virtualCourseLeaveFeedback;
    }

    public Timestamp getVirtualCourseLeaveEnd() {
        return virtualCourseLeaveEnd;
    }

    public void setVirtualCourseLeaveEnd(Timestamp virtualCourseLeaveEnd) {
        this.virtualCourseLeaveEnd = virtualCourseLeaveEnd;
    }

    public Timestamp getVirtualCourseLeaveGmtCreated() {
        return virtualCourseLeaveGmtCreated;
    }

    public void setVirtualCourseLeaveGmtCreated(Timestamp virtualCourseLeaveGmtCreated) {
        this.virtualCourseLeaveGmtCreated = virtualCourseLeaveGmtCreated;
    }

    public Timestamp getVirtualCourseLeaveGmtModified() {
        return virtualCourseLeaveGmtModified;
    }

    public void setVirtualCourseLeaveGmtModified(Timestamp virtualCourseLeaveGmtModified) {
        this.virtualCourseLeaveGmtModified = virtualCourseLeaveGmtModified;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentImgUrl() {
        return studentImgUrl;
    }

    public void setStudentImgUrl(String studentImgUrl) {
        this.studentImgUrl = studentImgUrl;
    }

    public Bitmap getStudentBitmap() {
        return studentBitmap;
    }

    public void setStudentBitmap(Bitmap studentBitmap) {
        this.studentBitmap = studentBitmap;
    }



    public String getVirtualCourseLeaveStatus() {
        return virtualCourseLeaveStatus;
    }

    public void setVirtualCourseLeaveStatus(String virtualCourseLeaveStatus) {
        this.virtualCourseLeaveStatus = virtualCourseLeaveStatus;
    }

    public String getStudentSchoolName() {
        return studentSchoolName;
    }

    public void setStudentSchoolName(String studentSchoolName) {
        this.studentSchoolName = studentSchoolName;
    }

    public String getChinese()
    {
        String result = "";
        if(virtualCourseLeaveStatus.equals(STATUS_WAIT_HANDLER))
        {
            result = "待处理";
        }
        if(virtualCourseLeaveStatus.equals(STATUS_PERMITTED))
        {
            result = "已通过";
        }
        if(virtualCourseLeaveStatus.equals(STATUS_REFUSE))
        {
            result = "已拒绝";
        }
        if(virtualCourseLeaveStatus.equals(STATUS_INVALID))
        {
            result = "无效";
        }
        return result;
    }


}
