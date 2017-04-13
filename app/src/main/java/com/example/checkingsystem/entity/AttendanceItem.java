package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 考勤记录
每条记录对应某个学生参与考勤成功与否的记录
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_attendance_item")
public class AttendanceItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键-uuid-32
	 */
	//Id("course_attendance_item_id")
	private String courseAttendanceItemId;
	/**
	 * 外键-student-id
	 */
	//Field("course_attendance_item_student_id")
	private String courseAttendanceItemStudentId;
	/**
	 * 外键-attendance_id
	 */
	//Field("course_attendance_id")
	private String courseAttendanceId;
	/**
	 * 参与考勤学生设备的mac地址
	 */
	//Field("course_attendance_item_mac")
	private String courseAttendanceItemMac;
	/**
	 * 记录创建时间
	 */
	//Field("course_attendance_item_gmt_created")
	private Timestamp courseAttendanceItemGmtCreated;
	/**
	 * 记录修改时间
	 */
	//Field("course_attendance_item_gmt_modified")
	private Timestamp courseAttendanceItemGmtModified;
	/**
	 * 考勤结果
	 */
	//Field("course_attendance_item_result")
	private String courseAttendanceItemResult;
	/**
	 * 考勤记录状态
	 */
	//Field("course_attendance_item_status")
	private String courseAttendanceItemStatus;


	public String getCourseAttendanceItemId() {
		return courseAttendanceItemId;
	}

	public void setCourseAttendanceItemId(String courseAttendanceItemId) {
		this.courseAttendanceItemId = courseAttendanceItemId;
	}

	public String getCourseAttendanceItemStudentId() {
		return courseAttendanceItemStudentId;
	}

	public void setCourseAttendanceItemStudentId(String courseAttendanceItemStudentId) {
		this.courseAttendanceItemStudentId = courseAttendanceItemStudentId;
	}

	public String getCourseAttendanceId() {
		return courseAttendanceId;
	}

	public void setCourseAttendanceId(String courseAttendanceId) {
		this.courseAttendanceId = courseAttendanceId;
	}

	public String getCourseAttendanceItemMac() {
		return courseAttendanceItemMac;
	}

	public void setCourseAttendanceItemMac(String courseAttendanceItemMac) {
		this.courseAttendanceItemMac = courseAttendanceItemMac;
	}

	public Timestamp getCourseAttendanceItemGmtCreated() {
		return courseAttendanceItemGmtCreated;
	}

	public void setCourseAttendanceItemGmtCreated(Timestamp courseAttendanceItemGmtCreated) {
		this.courseAttendanceItemGmtCreated = courseAttendanceItemGmtCreated;
	}

	public Timestamp getCourseAttendanceItemGmtModified() {
		return courseAttendanceItemGmtModified;
	}

	public void setCourseAttendanceItemGmtModified(Timestamp courseAttendanceItemGmtModified) {
		this.courseAttendanceItemGmtModified = courseAttendanceItemGmtModified;
	}

	public String getCourseAttendanceItemResult() {
		return courseAttendanceItemResult;
	}

	public void setCourseAttendanceItemResult(String courseAttendanceItemResult) {
		this.courseAttendanceItemResult = courseAttendanceItemResult;
	}

	public String getCourseAttendanceItemStatus() {
		return courseAttendanceItemStatus;
	}

	public void setCourseAttendanceItemStatus(String courseAttendanceItemStatus) {
		this.courseAttendanceItemStatus = courseAttendanceItemStatus;
	}

}
