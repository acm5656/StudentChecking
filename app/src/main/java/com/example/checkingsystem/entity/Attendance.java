package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 考勤对象
每一条记录对应老师的一次考勤(某次考勤考勤结果的汇总)
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_attendance")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("attendance_id")
	private Long attendanceId;
    /**
     * 外键-课程时间表-对应课表上的某节课
     */
	//("attendance_course_time_id")
	private Long attendanceCourseTimeId;
    /**
     * 考勤开始时间
     */
	//("attendance_gmt_begin")
	private Timestamp attendanceGmtBegin;
    /**
     * 考勤结束时间
     */
	//("attendance_gmt_end")
	private Timestamp attendanceGmtEnd;
    /**
     * 记录创建时间
     */
	//("attendance_gmt_created")
	private Timestamp attendanceGmtCreated;
    /**
     * 记录修改时间
     */
	//("attendance_gmt_modified")
	private Timestamp attendanceGmtModified;
    /**
     * 备注
     */
	//("attendance_note")
	private String attendanceNote;
    /**
     * 缺勤总数
     */
	//("attendance_absent_count")
	private Integer attendanceAbsentCount;
    /**
     * 总人数
     */
	//("attendance_total_count")
	private Integer attendanceTotalCount;
    /**
     * 出勤总数
     */
	//("attendance_present_count")
	private Integer attendancePresentCount;
    /**
     * 考勤记录状态
     */
	//("attendance_status")
	private String attendanceStatus;


	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Long getAttendanceCourseTimeId() {
		return attendanceCourseTimeId;
	}

	public void setAttendanceCourseTimeId(Long attendanceCourseTimeId) {
		this.attendanceCourseTimeId = attendanceCourseTimeId;
	}

	public Timestamp getAttendanceGmtBegin() {
		return attendanceGmtBegin;
	}

	public void setAttendanceGmtBegin(Timestamp attendanceGmtBegin) {
		this.attendanceGmtBegin = attendanceGmtBegin;
	}

	public Timestamp getAttendanceGmtEnd() {
		return attendanceGmtEnd;
	}

	public void setAttendanceGmtEnd(Timestamp attendanceGmtEnd) {
		this.attendanceGmtEnd = attendanceGmtEnd;
	}

	public Timestamp getAttendanceGmtCreated() {
		return attendanceGmtCreated;
	}

	public void setAttendanceGmtCreated(Timestamp attendanceGmtCreated) {
		this.attendanceGmtCreated = attendanceGmtCreated;
	}

	public Timestamp getAttendanceGmtModified() {
		return attendanceGmtModified;
	}

	public void setAttendanceGmtModified(Timestamp attendanceGmtModified) {
		this.attendanceGmtModified = attendanceGmtModified;
	}

	public String getAttendanceNote() {
		return attendanceNote;
	}

	public void setAttendanceNote(String attendanceNote) {
		this.attendanceNote = attendanceNote;
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
