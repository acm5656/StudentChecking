package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 班级信息
导员通过此信息对学生具有管理权限
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_class")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-uuid-32
     */
	//Id("class_id")
	private String classId;
    /**
     * 班级所属导员的id
     */
	//Field("class_assistant_id")
	private String classAssistantId;
    /**
     * 班级所属学校id
     */
	//Field("class_school_id")
	private String classSchoolId;
    /**
     * 班级所在系(院)
     */
	//Field("class_department")
	private String classDepartment;
    /**
     * 班级所属专业(如软件工程)
     */
	//Field("class_major")
	private String classMajor;
    /**
     * 班级所属级(如2014)
     */
	//Field("class_year")
	private String classYear;
    /**
     * 班级编号(如1)
     */
	//Field("class_no")
	private String classNo;
    /**
     * 记录创建时间
     */
	//Field("class_gmt_created")
	private Timestamp classGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("class_gmt_modified")
	private Timestamp classGmtModified;
    /**
     * 记录状态
     */
	//Field("class_status")
	private String classStatus;


	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassAssistantId() {
		return classAssistantId;
	}

	public void setClassAssistantId(String classAssistantId) {
		this.classAssistantId = classAssistantId;
	}

	public String getClassSchoolId() {
		return classSchoolId;
	}

	public void setClassSchoolId(String classSchoolId) {
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
