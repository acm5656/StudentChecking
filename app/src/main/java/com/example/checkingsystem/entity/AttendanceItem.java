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
	//Id("attendance_item_id")
	private String attendanceItemId;
    /**
     * 外键-student-id
     */
	//Field("attendance_item_student_id")
	private String attendanceItemStudentId;
    /**
     * 外键-attendance_id
     */
	//Field("attendance_id")
	private String attendanceId;
    /**
     * 记录创建时间
     */
	//Field("attendance_item_gmt_created")
	private Timestamp attendanceItemGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("attendance_item_gmt_modified")
	private Timestamp attendanceItemGmtModified;
    /**
     * 考勤结果
     */
	//Field("attendance_item_result")
	private String attendanceItemResult;
    /**
     * 考勤记录状态
     */
	//Field("attendance_item_status")
	private String attendanceItemStatus;


	public String getAttendanceItemId() {
		return attendanceItemId;
	}

	public void setAttendanceItemId(String attendanceItemId) {
		this.attendanceItemId = attendanceItemId;
	}

	public String getAttendanceItemStudentId() {
		return attendanceItemStudentId;
	}

	public void setAttendanceItemStudentId(String attendanceItemStudentId) {
		this.attendanceItemStudentId = attendanceItemStudentId;
	}

	public String getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Timestamp getAttendanceItemGmtCreated() {
		return attendanceItemGmtCreated;
	}

	public void setAttendanceItemGmtCreated(Timestamp attendanceItemGmtCreated) {
		this.attendanceItemGmtCreated = attendanceItemGmtCreated;
	}

	public Timestamp getAttendanceItemGmtModified() {
		return attendanceItemGmtModified;
	}

	public void setAttendanceItemGmtModified(Timestamp attendanceItemGmtModified) {
		this.attendanceItemGmtModified = attendanceItemGmtModified;
	}

	public String getAttendanceItemResult() {
		return attendanceItemResult;
	}

	public void setAttendanceItemResult(String attendanceItemResult) {
		this.attendanceItemResult = attendanceItemResult;
	}

	public String getAttendanceItemStatus() {
		return attendanceItemStatus;
	}

	public void setAttendanceItemStatus(String attendanceItemStatus) {
		this.attendanceItemStatus = attendanceItemStatus;
	}

}
