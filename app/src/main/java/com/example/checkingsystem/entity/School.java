package com.example.checkingsystem.entity;


import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 学校信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_school")
public class School  {

    private static final long serialVersionUID = 1L;
	public static final String STATUS_OPEN = "open";//可以被加入
	public static final String STATUS_CLOSE = "close";//不可再被加入
	public static final String STATUS_FINAL = "final";//班级结束
	public static final String STATUS_INVALID = "invalid";//无效,被删除等

    /**
     * 主键-uuid-32
     */
	//Id("school_id")
	private String schoolId;
    /**
     * 外键-地理位置id
     */
	//Field("school_location_id")
	private String schoolLocationId;
    /**
     * 学校名称
     */
	//Field("school_name")
	private String schoolName;
    /**
     * 记录创建时间
     */
	//Field("school_gmt_created")
	private Timestamp schoolGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("school_gmt_modified")
	private Timestamp schoolGmtModified;
    /**
     * 学校类型
     */
	//Field("school_type")
	private String schoolType;
    /**
     * 备注
     */
	//Field("school_note")
	private String schoolNote;
    /**
     * 学校状态
     */
	//Field("school_status")
	private String schoolStatus;


	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolLocationId() {
		return schoolLocationId;
	}

	public void setSchoolLocationId(String schoolLocationId) {
		this.schoolLocationId = schoolLocationId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Timestamp getSchoolGmtCreated() {
		return schoolGmtCreated;
	}

	public void setSchoolGmtCreated(Timestamp schoolGmtCreated) {
		this.schoolGmtCreated = schoolGmtCreated;
	}

	public Timestamp getSchoolGmtModified() {
		return schoolGmtModified;
	}

	public void setSchoolGmtModified(Timestamp schoolGmtModified) {
		this.schoolGmtModified = schoolGmtModified;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getSchoolNote() {
		return schoolNote;
	}

	public void setSchoolNote(String schoolNote) {
		this.schoolNote = schoolNote;
	}

	public String getSchoolStatus() {
		return schoolStatus;
	}

	public void setSchoolStatus(String schoolStatus) {
		this.schoolStatus = schoolStatus;
	}



}
