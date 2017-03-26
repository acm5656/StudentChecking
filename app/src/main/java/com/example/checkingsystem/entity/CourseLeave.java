package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 课程请假信息
每条记录代表某个学生在某一个course_time的请假信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_course_leave")
public class CourseLeave implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-uuid-32
     */
	//Id("course_leave_id")
	private String courseLeaveId;
    /**
     * 外键-学生id
     */
	//Field("course_leave_stu_id")
	private String courseLeaveStuId;
    /**
     * 外键-course_time_id
     */
	//Field("course_leave_ctime_id")
	private String courseLeaveCtimeId;
    /**
     * 请假原因-学生编写
     */
	//Field("course_leave_reason")
	private String courseLeaveReason;
    /**
     * 请假备注-准假人编写
     */
	//Field("course_leave_note")
	private String courseLeaveNote;
    /**
     * 记录创建时间
     */
	//Field("course_leave_gmt_created")
	private Timestamp courseLeaveGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("course_leave_gmt_modified")
	private Timestamp courseLeaveGmtModified;
    /**
     * 请假是否被允许
     */
	//Field("course_leave_permit")
	private Integer courseLeavePermit;
    /**
     * 请假记录状态
     */
	//Field("course_leave_status")
	private String courseLeaveStatus;


	public String getCourseLeaveId() {
		return courseLeaveId;
	}

	public void setCourseLeaveId(String courseLeaveId) {
		this.courseLeaveId = courseLeaveId;
	}

	public String getCourseLeaveStuId() {
		return courseLeaveStuId;
	}

	public void setCourseLeaveStuId(String courseLeaveStuId) {
		this.courseLeaveStuId = courseLeaveStuId;
	}

	public String getCourseLeaveCtimeId() {
		return courseLeaveCtimeId;
	}

	public void setCourseLeaveCtimeId(String courseLeaveCtimeId) {
		this.courseLeaveCtimeId = courseLeaveCtimeId;
	}

	public String getCourseLeaveReason() {
		return courseLeaveReason;
	}

	public void setCourseLeaveReason(String courseLeaveReason) {
		this.courseLeaveReason = courseLeaveReason;
	}

	public String getCourseLeaveNote() {
		return courseLeaveNote;
	}

	public void setCourseLeaveNote(String courseLeaveNote) {
		this.courseLeaveNote = courseLeaveNote;
	}

	public Timestamp getCourseLeaveGmtCreated() {
		return courseLeaveGmtCreated;
	}

	public void setCourseLeaveGmtCreated(Timestamp courseLeaveGmtCreated) {
		this.courseLeaveGmtCreated = courseLeaveGmtCreated;
	}

	public Timestamp getCourseLeaveGmtModified() {
		return courseLeaveGmtModified;
	}

	public void setCourseLeaveGmtModified(Timestamp courseLeaveGmtModified) {
		this.courseLeaveGmtModified = courseLeaveGmtModified;
	}

	public Integer getCourseLeavePermit() {
		return courseLeavePermit;
	}

	public void setCourseLeavePermit(Integer courseLeavePermit) {
		this.courseLeavePermit = courseLeavePermit;
	}

	public String getCourseLeaveStatus() {
		return courseLeaveStatus;
	}

	public void setCourseLeaveStatus(String courseLeaveStatus) {
		this.courseLeaveStatus = courseLeaveStatus;
	}

}
