package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 学校信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_school")
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("school_id")
	private Long schoolId;
    /**
     * 外键-地理位置id
     */
	//("school_location_id")
	private Long schoolLocationId;
    /**
     * 学校名称
     */
	//("school_name")
	private String schoolName;
    /**
     * 记录创建时间
     */
	//("school_gmt_created")
	private Timestamp schoolGmtCreated;
    /**
     * 记录修改时间
     */
	//("school_gmt_modified")
	private Timestamp schoolGmtModified;
    /**
     * 学校类型
     */
	//("school_type")
	private String schoolType;
    /**
     * 备注
     */
	//("school_note")
	private String schoolNote;
    /**
     * 学校状态
     */
	//("school_status")
	private String schoolStatus;


	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getSchoolLocationId() {
		return schoolLocationId;
	}

	public void setSchoolLocationId(Long schoolLocationId) {
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
