package com.example.checkingsystem.entity;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 * <p>
 * 班级信息
导员通过此信息对学生具有管理权限
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_class")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("class_id")
	private Long classId;
    /**
     * 班级所属导员的id
     */
	//("class_assistant_id")
	private Long classAssistantId;
    /**
     * 班级所属学校id
     */
	//("class_school_id")
	private Long classSchoolId;
    /**
     * 班级所在系(院)
     */
	//("class_department")
	private String classDepartment;
    /**
     * 班级所属专业(如软件工程)
     */
	//("class_major")
	private String classMajor;
    /**
     * 班级所属级(如2014)
     */
	//("class_year")
	private String classYear;
    /**
     * 班级编号(如1)
     */
	//("class_no")
	private String classNo;
    /**
     * 记录创建时间
     */
	//("class_gmt_created")
	private Timestamp classGmtCreated;
    /**
     * 记录修改时间
     */
	//("class_gmt_modified")
	private Timestamp classGmtModified;
    /**
     * 记录状态
     */
	//("class_status")
	private String classStatus;


	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getClassAssistantId() {
		return classAssistantId;
	}

	public void setClassAssistantId(Long classAssistantId) {
		this.classAssistantId = classAssistantId;
	}

	public Long getClassSchoolId() {
		return classSchoolId;
	}

	public void setClassSchoolId(Long classSchoolId) {
		this.classSchoolId = classSchoolId;
	}

	public String getClassDepartment() {
		return classDepartment;
	}

	public void setClassDepartment(String classDepartment) {
		this.classDepartment = classDepartment;
	}

	public String getClassMajor() {
		return classMajor;
	}

	public void setClassMajor(String classMajor) {
		this.classMajor = classMajor;
	}

	public String getClassYear() {
		return classYear;
	}

	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public Timestamp getClassGmtCreated() {
		return classGmtCreated;
	}

	public void setClassGmtCreated(Timestamp classGmtCreated) {
		this.classGmtCreated = classGmtCreated;
	}

	public Timestamp getClassGmtModified() {
		return classGmtModified;
	}

	public void setClassGmtModified(Timestamp classGmtModified) {
		this.classGmtModified = classGmtModified;
	}

	public String getClassStatus() {
		return classStatus;
	}

	public void setClassStatus(String classStatus) {
		this.classStatus = classStatus;
	}

}
