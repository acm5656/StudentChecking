package com.example.checkingsystem.entity;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 * <p>
 * 班级信息
导员管理此信息

public static final String STATUS_OPEN = "open";//可以被加入
public static final String STATUS_CLOSE = "close";//不可再被加入
public static final String STATUS_FINAL = "final";//班级结束
public static final String STATUS_INVALID = "invalid";//无效,被删除等
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-07-03
 */

public class Class {

    private static final long serialVersionUID = 1L;
	public static final String STATUS_OPEN = "open";//可以被加入
	public static final String STATUS_CLOSE = "close";//不可再被加入
	public static final String STATUS_FINAL = "final";//班级结束
	public static final String STATUS_INVALID = "invalid";//无效,被删除等

	/**
     * 主键-uuid-32
     */

	private String classId;
    /**
     * 班级所属导员的id
     */

	private String classAssistantId;
    /**
     * 班级所属学校id
     */

	private String classSchoolId;
    /**
     * class代码
     */

	private String classCode;
    /**
     * 班级所在系(院)
     */

	private String classDepartment;
    /**
     * 班级所属专业(如软件工程)
     */

	private String classMajor;
    /**
     * 班级所属级(如2014)
     */

	private String classYear;
    /**
     * 班级编号(如1)
     */

	private String classNo;
    /**
     * 记录创建时间
     */

	private Timestamp classGmtCreated;
    /**
     * 记录修改时间
     */

	private Timestamp classGmtModified;
    /**
     * 记录状态
     */

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

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
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
