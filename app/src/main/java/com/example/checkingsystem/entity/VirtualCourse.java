package com.example.checkingsystem.entity;


import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-06-28
 */
//Name("t_virtual_course")
public class VirtualCourse implements Serializable  {

    private static final long serialVersionUID = 1L;

	public static final String STATUS_OPEN = "open";//可以被加入
	public static final String STATUS_CLOSE = "close";//不可再被加入
    public static final String STATUS_AUTO_REFUSE = "auto_refuse";//自动拒绝此人的请求
    public static final String STATUS_FINAL = "final";//此班级不再进行任何操作即这个课被结课
	public static final String STATUS_INVALID = "invalid";//班级无效,被删除等

	//Id("virtual_course_id")
	private String virtualCourseId;
	//Field("virtual_course_teacher_id")
	private String virtualCourseTeacherId;
	//Field("virtual_course_textbook_id")
	private String virtualCourseTextbookId;
	//Field("virtual_course_img_url")
	private String virtualCourseImgUrl;
	//Field("virtual_course_name")
	private String virtualCourseName;
	//Field("virtual_course_type")
	private String virtualCourseType;
	//Field("virtual_course_ask")
	private String virtualCourseAsk;
	//Field("virtual_course_schedule")
	private String virtualCourseSchedule;
	//Field("virtual_course_exam")
	private String virtualCourseExam;
	//Field("virtual_course_code")
	private String virtualCourseCode;
	//Field("virtual_course_gmt_created")
	private Timestamp virtualCourseGmtCreated;
	//Field("virtual_course_gmt_modified")
	private Timestamp virtualCourseGmtModified;
	//Field("virtual_course_status")
	private String virtualCourseStatus;


	public String getVirtualCourseId() {
		return virtualCourseId;
	}

	public void setVirtualCourseId(String virtualCourseId) {
		this.virtualCourseId = virtualCourseId;
	}

	public String getVirtualCourseTeacherId() {
		return virtualCourseTeacherId;
	}

	public void setVirtualCourseTeacherId(String virtualCourseTeacherId) {
		this.virtualCourseTeacherId = virtualCourseTeacherId;
	}

	public String getVirtualCourseTextbookId() {
		return virtualCourseTextbookId;
	}

	public void setVirtualCourseTextbookId(String virtualCourseTextbookId) {
		this.virtualCourseTextbookId = virtualCourseTextbookId;
	}

	public String getVirtualCourseImgUrl() {
		return virtualCourseImgUrl;
	}

	public void setVirtualCourseImgUrl(String virtualCourseImgUrl) {
		this.virtualCourseImgUrl = virtualCourseImgUrl;
	}

	public String getVirtualCourseName() {
		return virtualCourseName;
	}

	public void setVirtualCourseName(String virtualCourseName) {
		this.virtualCourseName = virtualCourseName;
	}

	public String getVirtualCourseType() {
		return virtualCourseType;
	}

	public void setVirtualCourseType(String virtualCourseType) {
		this.virtualCourseType = virtualCourseType;
	}

	public String getVirtualCourseAsk() {
		return virtualCourseAsk;
	}

	public void setVirtualCourseAsk(String virtualCourseAsk) {
		this.virtualCourseAsk = virtualCourseAsk;
	}

	public String getVirtualCourseSchedule() {
		return virtualCourseSchedule;
	}

	public void setVirtualCourseSchedule(String virtualCourseSchedule) {
		this.virtualCourseSchedule = virtualCourseSchedule;
	}

	public String getVirtualCourseExam() {
		return virtualCourseExam;
	}

	public void setVirtualCourseExam(String virtualCourseExam) {
		this.virtualCourseExam = virtualCourseExam;
	}

	public String getVirtualCourseCode() {
		return virtualCourseCode;
	}

	public void setVirtualCourseCode(String virtualCourseCode) {
		this.virtualCourseCode = virtualCourseCode;
	}

	public Timestamp getVirtualCourseGmtCreated() {
		return virtualCourseGmtCreated;
	}

	public void setVirtualCourseGmtCreated(Timestamp virtualCourseGmtCreated) {
		this.virtualCourseGmtCreated = virtualCourseGmtCreated;
	}

	public Timestamp getVirtualCourseGmtModified() {
		return virtualCourseGmtModified;
	}

	public void setVirtualCourseGmtModified(Timestamp virtualCourseGmtModified) {
		this.virtualCourseGmtModified = virtualCourseGmtModified;
	}

	public String getVirtualCourseStatus() {
		return virtualCourseStatus;
	}

	public void setVirtualCourseStatus(String virtualCourseStatus) {
		this.virtualCourseStatus = virtualCourseStatus;
	}



}
