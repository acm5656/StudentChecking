package com.example.checkingsystem.entity;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 * <p>
 * 考勤记录
每条记录对应某个学生参与考勤成功与否的记录
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-06-28
 */
//Name("t_virtual_course_attendance_item")
public class VirtualCourseAttendanceItem {

    private static final long serialVersionUID = 1L;
	public static final String STATUS_BE_LATE = "late";
	public static final String STATUS_ATTENDANCE = "attendance";
	public static final String STATUS_ABSENCE = "absence";
	public static final String STATUS_LEAVE = "leave";
	public static final String STATUS_INVALID = "invalid";

    /**
     * 主键-uuid-32
     */
	//Id("virtual_course_attendance_item_id")
	private String virtualCourseAttendanceItemId;
    /**
     * 外键-student-id
     */
	//Field("virtual_course_attendance_item_student_id")
	private String virtualCourseAttendanceItemStudentId;
    /**
     * 外键-attendance_id
     */
	//Field("virtual_course_attendance_id")
	private String virtualCourseAttendanceId;
    /**
     * 参与考勤学生设备的mac地址
     */
	//Field("virtual_course_attendance_item_mac")
	private String virtualCourseAttendanceItemMac;
    /**
     * 记录创建时间
     */
	//Field("virtual_course_attendance_item_gmt_created")
	private Timestamp virtualCourseAttendanceItemGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("virtual_course_attendance_item_gmt_modified")
	private Timestamp virtualCourseAttendanceItemGmtModified;
    /**
     * 考勤结果
     */
	//Field("virtual_course_attendance_item_result")
	private String virtualCourseAttendanceItemResult;
    /**
     * 考勤记录状态
     */
	//Field("virtual_course_attendance_item_status")
	private String virtualCourseAttendanceItemStatus;


	public String getVirtualCourseAttendanceItemId() {
		return virtualCourseAttendanceItemId;
	}

	public void setVirtualCourseAttendanceItemId(String virtualCourseAttendanceItemId) {
		this.virtualCourseAttendanceItemId = virtualCourseAttendanceItemId;
	}

	public String getVirtualCourseAttendanceItemStudentId() {
		return virtualCourseAttendanceItemStudentId;
	}

	public void setVirtualCourseAttendanceItemStudentId(String virtualCourseAttendanceItemStudentId) {
		this.virtualCourseAttendanceItemStudentId = virtualCourseAttendanceItemStudentId;
	}

	public String getVirtualCourseAttendanceId() {
		return virtualCourseAttendanceId;
	}

	public void setVirtualCourseAttendanceId(String virtualCourseAttendanceId) {
		this.virtualCourseAttendanceId = virtualCourseAttendanceId;
	}

	public String getVirtualCourseAttendanceItemMac() {
		return virtualCourseAttendanceItemMac;
	}

	public void setVirtualCourseAttendanceItemMac(String virtualCourseAttendanceItemMac) {
		this.virtualCourseAttendanceItemMac = virtualCourseAttendanceItemMac;
	}

	public Timestamp getVirtualCourseAttendanceItemGmtCreated() {
		return virtualCourseAttendanceItemGmtCreated;
	}

	public void setVirtualCourseAttendanceItemGmtCreated(Timestamp virtualCourseAttendanceItemGmtCreated) {
		this.virtualCourseAttendanceItemGmtCreated = virtualCourseAttendanceItemGmtCreated;
	}

	public Timestamp getVirtualCourseAttendanceItemGmtModified() {
		return virtualCourseAttendanceItemGmtModified;
	}

	public void setVirtualCourseAttendanceItemGmtModified(Timestamp virtualCourseAttendanceItemGmtModified) {
		this.virtualCourseAttendanceItemGmtModified = virtualCourseAttendanceItemGmtModified;
	}

	public String getVirtualCourseAttendanceItemResult() {
		return virtualCourseAttendanceItemResult;
	}

	public void setVirtualCourseAttendanceItemResult(String virtualCourseAttendanceItemResult) {
		this.virtualCourseAttendanceItemResult = virtualCourseAttendanceItemResult;
	}

	public String getVirtualCourseAttendanceItemStatus() {
		return virtualCourseAttendanceItemStatus;
	}

	public void setVirtualCourseAttendanceItemStatus(String virtualCourseAttendanceItemStatus) {
		this.virtualCourseAttendanceItemStatus = virtualCourseAttendanceItemStatus;
	}

	public String getChineseShow()
	{
		String result = "";
		if(virtualCourseAttendanceItemStatus.equals(STATUS_BE_LATE))
			result  = "迟到";
		if(virtualCourseAttendanceItemStatus.equals(STATUS_ATTENDANCE))
		{
			result = "考勤成功";
		}
		if(virtualCourseAttendanceItemStatus.equals(STATUS_ABSENCE))
		{
			result = "缺勤";
		}
		if(virtualCourseAttendanceItemStatus.equals(STATUS_LEAVE))
		{
			result = "请假";
		}

		return result;
	}
}
