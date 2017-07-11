package com.example.checkingsystem.entity;


import java.sql.Timestamp;


import java.io.Serializable;


/**
 * <p>
 * 课程请假信息
 * 每条记录代表某个学生在某一个course_time的请假信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-06-28
 */
//Name("t_virtual_course_leave")
public class VirtualCourseLeave implements Serializable{

    private static final long serialVersionUID = 1L;
    public static final String STATUS_WAIT_HANDLER = "nondeal";
    public static final String STATUS_PERMITTED = "permitted";
    public static final String STATUS_REFUSE = "refused";
    public static final String STATUS_INVALID = "invalid";

    /**
     * 主键-uuid-32
     */
    //Id("virtual_course_leave_id")
    private String virtualCourseLeaveId;
    /**
     * 外键-学生id
     */
    //Field("virtual_course_leave_stu_id")
    private String virtualCourseLeaveStuId;
    /**
     * 导员id
     */
    //Field("virtual_course_leave_assis_id")
    private String virtualCourseLeaveAssisId;
    /**
     * 请假原因-学生编写
     */
    //Field("virtual_course_leave_reason")
    private String virtualCourseLeaveReason;
    /**
     * 请假备注-准假人编写
     */
    //Field("virtual_course_leave_feedback")
    private String virtualCourseLeaveFeedback;
    /**
     * 请假开始时间
     */
    //Field("virtual_course_leave_begin")
    private Timestamp virtualCourseLeaveBegin;
    /**
     * 请假结束时间
     */
    //Field("virtual_course_leave_end")
    private Timestamp virtualCourseLeaveEnd;
    /**
     * 记录创建时间
     */
    //Field("virtual_course_leave_gmt_created")
    private Timestamp virtualCourseLeaveGmtCreated;
    /**
     * 记录修改时间
     */
    //Field("virtual_course_leave_gmt_modified")
    private Timestamp virtualCourseLeaveGmtModified;
    /**
     * 请假记录状态
     */
    //Field("virtual_course_leave_status")
    private String virtualCourseLeaveStatus;


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

    public String getVirtualCourseLeaveAssisId() {
        return virtualCourseLeaveAssisId;
    }

    public void setVirtualCourseLeaveAssisId(String virtualCourseLeaveAssisId) {
        this.virtualCourseLeaveAssisId = virtualCourseLeaveAssisId;
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

    public Timestamp getVirtualCourseLeaveBegin() {
        return virtualCourseLeaveBegin;
    }

    public void setVirtualCourseLeaveBegin(Timestamp virtualCourseLeaveBegin) {
        this.virtualCourseLeaveBegin = virtualCourseLeaveBegin;
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

    public String getVirtualCourseLeaveStatus() {
        return virtualCourseLeaveStatus;
    }

    public void setVirtualCourseLeaveStatus(String virtualCourseLeaveStatus) {
        this.virtualCourseLeaveStatus = virtualCourseLeaveStatus;
    }

    public String statusToChinese()
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
