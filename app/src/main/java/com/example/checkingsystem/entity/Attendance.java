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
	//Id("course_attendance_id")
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
	//Field("attendance_absent_count")
	private Integer attendanceAbsentCount;
    /**
     * 总人数
     */
	//Field("attendance_total_count")
	private Integer attendanceTotalCount;
    /**
     * 出勤总数
     */
	//Field("attendance_present_count")
	private Integer attendancePresentCount;
    /**
     * 考勤记录状态
     */
	//Field("attendance_status")
	private String attendanceStatus;


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

	public Integer getAttendanceAbsentCount() {
		return attendanceAbsentCount;
	}

	public void setAttendanceAbsentCount(Integer attendanceAbsentCount) {
		this.attendanceAbsentCount = attendanceAbsentCount;
	}

	public Integer getAttendanceTotalCount() {
		return attendanceTotalCount;
	}

	public void setAttendanceTotalCount(Integer attendanceTotalCount) {
		this.attendanceTotalCount = attendanceTotalCount;
	}

	public Integer getAttendancePresentCount() {
		return attendancePresentCount;
	}

	public void setAttendancePresentCount(Integer attendancePresentCount) {
		this.attendancePresentCount = attendancePresentCount;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

}
