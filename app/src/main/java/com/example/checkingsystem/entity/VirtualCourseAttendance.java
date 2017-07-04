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
 * @since 2017-06-28
 */
//Name("t_virtual_course_attendance")
public class VirtualCourseAttendance  {

    private static final long serialVersionUID = 1L;
	public static final String STATUS_READY = "ready";//考勤准备中
	public static final String STATUS_OPEN = "open";//考勤开启中
	public static final String STATUS_CLOSE = "close";//考勤结束
	public static final String STATUS_INVALID = "invalid";//记录无效

    /**
     * 主键-uuid-32
     */
	//Id("virtual_course_attendance_id")
	private String virtualCourseAttendanceId;
    /**
     * 外键-和某个virtualcourse关联
     */
	//Field("virtual_course_attendance_course_id")
	private String virtualCourseAttendanceCourseId;
    /**
     * 考勤开始时间
     */
	//Field("virtual_course_attendance_gmt_begin")
	private Timestamp virtualCourseAttendanceGmtBegin;
    /**
     * 考勤结束时间
     */
	//Field("virtual_course_attendance_gmt_end")
	private Timestamp virtualCourseAttendanceGmtEnd;
    /**
     * 开始考勤的教师mac地址
     */
	//Field("virtual_course_attendance_mac")
	private String virtualCourseAttendanceMac;
    /**
     * 记录创建时间
     */
	//Field("virtual_course_attendance_gmt_created")
	private Timestamp virtualCourseAttendanceGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("virtual_course_attendance_gmt_modified")
	private Timestamp virtualCourseAttendanceGmtModified;
    /**
     * 备注
     */
	//Field("virtual_course_attendance_note")
	private String virtualCourseAttendanceNote;
    /**
     * 缺勤总数
     */
	//Field("virtual_course_attendance_absent_count")
	private Integer virtualCourseAttendanceAbsentCount;
    /**
     * 总人数
     */
	//Field("virtual_course_attendance_total_count")
	private Integer virtualCourseAttendanceTotalCount;
    /**
     * 出勤总数
     */
	//Field("virtual_course_attendance_present_count")
	private Integer virtualCourseAttendancePresentCount;
    /**
     * 考勤记录状态
     */
	//Field("virtual_course_attendance_status")
	private String virtualCourseAttendanceStatus;


	public String getVirtualCourseAttendanceId() {
		return virtualCourseAttendanceId;
	}

	public void setVirtualCourseAttendanceId(String virtualCourseAttendanceId) {
		this.virtualCourseAttendanceId = virtualCourseAttendanceId;
	}

	public String getVirtualCourseAttendanceCourseId() {
		return virtualCourseAttendanceCourseId;
	}

	public void setVirtualCourseAttendanceCourseId(String virtualCourseAttendanceCourseId) {
		this.virtualCourseAttendanceCourseId = virtualCourseAttendanceCourseId;
	}

	public Timestamp getVirtualCourseAttendanceGmtBegin() {
		return virtualCourseAttendanceGmtBegin;
	}

	public void setVirtualCourseAttendanceGmtBegin(Timestamp virtualCourseAttendanceGmtBegin) {
		this.virtualCourseAttendanceGmtBegin = virtualCourseAttendanceGmtBegin;
	}

	public Timestamp getVirtualCourseAttendanceGmtEnd() {
		return virtualCourseAttendanceGmtEnd;
	}

	public void setVirtualCourseAttendanceGmtEnd(Timestamp virtualCourseAttendanceGmtEnd) {
		this.virtualCourseAttendanceGmtEnd = virtualCourseAttendanceGmtEnd;
	}

	public String getVirtualCourseAttendanceMac() {
		return virtualCourseAttendanceMac;
	}

	public void setVirtualCourseAttendanceMac(String virtualCourseAttendanceMac) {
		this.virtualCourseAttendanceMac = virtualCourseAttendanceMac;
	}

	public Timestamp getVirtualCourseAttendanceGmtCreated() {
		return virtualCourseAttendanceGmtCreated;
	}

	public void setVirtualCourseAttendanceGmtCreated(Timestamp virtualCourseAttendanceGmtCreated) {
		this.virtualCourseAttendanceGmtCreated = virtualCourseAttendanceGmtCreated;
	}

	public Timestamp getVirtualCourseAttendanceGmtModified() {
		return virtualCourseAttendanceGmtModified;
	}

	public void setVirtualCourseAttendanceGmtModified(Timestamp virtualCourseAttendanceGmtModified) {
		this.virtualCourseAttendanceGmtModified = virtualCourseAttendanceGmtModified;
	}

	public String getVirtualCourseAttendanceNote() {
		return virtualCourseAttendanceNote;
	}

	public void setVirtualCourseAttendanceNote(String virtualCourseAttendanceNote) {
		this.virtualCourseAttendanceNote = virtualCourseAttendanceNote;
	}

	public Integer getVirtualCourseAttendanceAbsentCount() {
		return virtualCourseAttendanceAbsentCount;
	}

	public void setVirtualCourseAttendanceAbsentCount(Integer virtualCourseAttendanceAbsentCount) {
		this.virtualCourseAttendanceAbsentCount = virtualCourseAttendanceAbsentCount;
	}

	public Integer getVirtualCourseAttendanceTotalCount() {
		return virtualCourseAttendanceTotalCount;
	}

	public void setVirtualCourseAttendanceTotalCount(Integer virtualCourseAttendanceTotalCount) {
		this.virtualCourseAttendanceTotalCount = virtualCourseAttendanceTotalCount;
	}

	public Integer getVirtualCourseAttendancePresentCount() {
		return virtualCourseAttendancePresentCount;
	}

	public void setVirtualCourseAttendancePresentCount(Integer virtualCourseAttendancePresentCount) {
		this.virtualCourseAttendancePresentCount = virtualCourseAttendancePresentCount;
	}

	public String getVirtualCourseAttendanceStatus() {
		return virtualCourseAttendanceStatus;
	}

	public void setVirtualCourseAttendanceStatus(String virtualCourseAttendanceStatus) {
		this.virtualCourseAttendanceStatus = virtualCourseAttendanceStatus;
	}



}
