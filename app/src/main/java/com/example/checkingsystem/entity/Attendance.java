package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 考勤对象
每一条记录对应老师的一次考勤(某次考勤考勤结果的汇总)
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_attendance")
public class Attendance implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键-uuid-32
	 */
	//("course_attendance_id")
	private String courseAttendanceId;
	/**
	 * 外键-课程时间表-对应课表上的某节课
	 */
	//Field("course_attendance_course_time_id")
	private String courseAttendanceCourseTimeId;
	/**
	 * 考勤开始时间
	 */
	//Field("course_attendance_gmt_begin")
	private Timestamp courseAttendanceGmtBegin;
	/**
	 * 考勤结束时间
	 */
	//Field("course_attendance_gmt_end")
	private Timestamp courseAttendanceGmtEnd;
	/**
	 * 开始考勤的教师mac地址
	 */
	//Field("course_attendance_mac")
	private String courseAttendanceMac;
	/**
	 * 记录创建时间
	 */
	//Field("course_attendance_gmt_created")
	private Timestamp courseAttendanceGmtCreated;
	/**
	 * 记录修改时间
	 */
	//Field("course_attendance_gmt_modified")
	private Timestamp courseAttendanceGmtModified;
	/**
	 * 备注
	 */
	//Field("course_attendance_note")
	private String courseAttendanceNote;
	/**
	 * 缺勤总数
	 */
	//Field("course_attendance_absent_count")
	private Integer courseAttendanceAbsentCount;
	/**
	 * 总人数
	 */
	//Field("course_attendance_total_count")
	private Integer courseAttendanceTotalCount;
	/**
	 * 出勤总数
	 */
	//Field("course_attendance_present_count")
	private Integer courseAttendancePresentCount;
	/**
	 * 考勤记录状态
	 */
	//Field("course_attendance_status")
	private String courseAttendanceStatus;


	public String getCourseAttendanceId() {
		return courseAttendanceId;
	}

	public void setCourseAttendanceId(String courseAttendanceId) {
		this.courseAttendanceId = courseAttendanceId;
	}

	public String getCourseAttendanceCourseTimeId() {
		return courseAttendanceCourseTimeId;
	}

	public void setCourseAttendanceCourseTimeId(String courseAttendanceCourseTimeId) {
		this.courseAttendanceCourseTimeId = courseAttendanceCourseTimeId;
	}

	public Timestamp getCourseAttendanceGmtBegin() {
		return courseAttendanceGmtBegin;
	}

	public void setCourseAttendanceGmtBegin(Timestamp courseAttendanceGmtBegin) {
		this.courseAttendanceGmtBegin = courseAttendanceGmtBegin;
	}

	public Timestamp getCourseAttendanceGmtEnd() {
		return courseAttendanceGmtEnd;
	}

	public void setCourseAttendanceGmtEnd(Timestamp courseAttendanceGmtEnd) {
		this.courseAttendanceGmtEnd = courseAttendanceGmtEnd;
	}

	public String getCourseAttendanceMac() {
		return courseAttendanceMac;
	}

	public void setCourseAttendanceMac(String courseAttendanceMac) {
		this.courseAttendanceMac = courseAttendanceMac;
	}

	public Timestamp getCourseAttendanceGmtCreated() {
		return courseAttendanceGmtCreated;
	}

	public void setCourseAttendanceGmtCreated(Timestamp courseAttendanceGmtCreated) {
		this.courseAttendanceGmtCreated = courseAttendanceGmtCreated;
	}

	public Timestamp getCourseAttendanceGmtModified() {
		return courseAttendanceGmtModified;
	}

	public void setCourseAttendanceGmtModified(Timestamp courseAttendanceGmtModified) {
		this.courseAttendanceGmtModified = courseAttendanceGmtModified;
	}

	public String getCourseAttendanceNote() {
		return courseAttendanceNote;
	}

	public void setCourseAttendanceNote(String courseAttendanceNote) {
		this.courseAttendanceNote = courseAttendanceNote;
	}

	public Integer getCourseAttendanceAbsentCount() {
		return courseAttendanceAbsentCount;
	}

	public void setCourseAttendanceAbsentCount(Integer courseAttendanceAbsentCount) {
		this.courseAttendanceAbsentCount = courseAttendanceAbsentCount;
	}

	public Integer getCourseAttendanceTotalCount() {
		return courseAttendanceTotalCount;
	}

	public void setCourseAttendanceTotalCount(Integer courseAttendanceTotalCount) {
		this.courseAttendanceTotalCount = courseAttendanceTotalCount;
	}

	public Integer getCourseAttendancePresentCount() {
		return courseAttendancePresentCount;
	}

	public void setCourseAttendancePresentCount(Integer courseAttendancePresentCount) {
		this.courseAttendancePresentCount = courseAttendancePresentCount;
	}

	public String getCourseAttendanceStatus() {
		return courseAttendanceStatus;
	}

	public void setCourseAttendanceStatus(String courseAttendanceStatus) {
		this.courseAttendanceStatus = courseAttendanceStatus;
	}


}
