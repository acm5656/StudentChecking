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
 * @since 2017-07-03
 */

public class ClassGrade {

    private static final long serialVersionUID = 1L;
	public static final String STATUS_OK = "ok";//有效
	public static final String STATUS_AUTO_REFUSE = "auto_refuse";//自动拒绝此人的请求
	public static final String STATUS_INVALID = "invalid";//无效,被删除等

    /**
     * 主键-uuid-32
     */

	private String classGradeId;
    /**
     * 外键学生id
     */

	private String classGradeStudentId;
    /**
     * 外键-班级id
     */

	private String classGradeClassId;
    /**
     * 成绩
     */

	private Double classGrade;
    /**
     * 记录创建时间
     */
	private Timestamp classGradeGmtCreated;
    /**
     * 记录修改时间
     */

	private Timestamp classGradeGmtModified;
    /**
     * 记录状态
     */

	private String classGradeStatus;


	public String getClassGradeId() {
		return classGradeId;
	}

	public void setClassGradeId(String classGradeId) {
		this.classGradeId = classGradeId;
	}

	public String getClassGradeStudentId() {
		return classGradeStudentId;
	}

	public void setClassGradeStudentId(String classGradeStudentId) {
		this.classGradeStudentId = classGradeStudentId;
	}

	public String getClassGradeClassId() {
		return classGradeClassId;
	}

	public void setClassGradeClassId(String classGradeClassId) {
		this.classGradeClassId = classGradeClassId;
	}

	public Double getClassGrade() {
		return classGrade;
	}

	public void setClassGrade(Double classGrade) {
		this.classGrade = classGrade;
	}

	public Timestamp getClassGradeGmtCreated() {
		return classGradeGmtCreated;
	}

	public void setClassGradeGmtCreated(Timestamp classGradeGmtCreated) {
		this.classGradeGmtCreated = classGradeGmtCreated;
	}

	public Timestamp getClassGradeGmtModified() {
		return classGradeGmtModified;
	}

	public void setClassGradeGmtModified(Timestamp classGradeGmtModified) {
		this.classGradeGmtModified = classGradeGmtModified;
	}

	public String getClassGradeStatus() {
		return classGradeStatus;
	}

	public void setClassGradeStatus(String classGradeStatus) {
		this.classGradeStatus = classGradeStatus;
	}



}
