package com.example.checkingsystem.entity;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 * <p>
 * 课程请假信息
每条记录代表某个学生在某一个course_time的请假信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_course_leave")
public class CourseLeave implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("course_leave_id")
	private String courseLeaveId;
    /**
     * 外键-学生id
     */
	//("course_leave_stu_id")
	private Long courseLeaveStuId;
    /**
     * 外键-course_time_id
     */
	//("course_leave_ctime_id")
	private Long courseLeaveCtimeId;
    /**
     * 请假原因-学生编写
     */
	//("course_leave_reason")
	private String courseLeaveReason;
    /**
     * 请假备注-准假人编写
     */
	//("course_leave_note")
	private String courseLeaveNote;
    /**
     * 记录创建时间
     */
	//("course_leave_gmt_created")
	private Timestamp courseLeaveGmtCreated;
    /**
     * 记录修改时间
     */
	//("course_leave_gmt_modified")
	private Timestamp courseLeaveGmtModified;
    /**
     * 请假是否被允许
     */
	//("course_leave_permit")
	private Integer courseLeavePermit;
    /**
     * 请假记录状态
     */
	//("course_leave_status")
	private String courseLeaveStatus;


	public String getCourseLeaveId() {
		return courseLeaveId;
	}

	public void setCourseLeaveId(String courseLeaveId) {
		this.courseLeaveId = courseLeaveId;
	}

	public Long getCourseLeaveStuId() {
		return courseLeaveStuId;
	}

	public void setCourseLeaveStuId(Long courseLeaveStuId) {
		this.courseLeaveStuId = courseLeaveStuId;
	}

	public Long getCourseLeaveCtimeId() {
		return courseLeaveCtimeId;
	}

	public void setCourseLeaveCtimeId(Long courseLeaveCtimeId) {
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
