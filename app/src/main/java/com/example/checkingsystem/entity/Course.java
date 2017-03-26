package com.example.checkingsystem.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 课程信息

 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程-sequence-id
     */
	//Id("course_id")
	private String courseId;
    /**
     * 外键-先修课ID
     */
	//Field("precourse_id")
	private String precourseId;
    /**
     * 课程名称
     */
	//Field("course_name")
	private String courseName;
    /**
     * 课程代码
     */
	//Field("course_code")
	private String courseCode;
    /**
     * 课程类型
     */
	//Field("course_type")
	private String courseType;
    /**
     * 课程学分
     */
	//Field("course_credit")
	private BigDecimal courseCredit;
    /**
     * 课程创建时间
     */
	//Field("course_gmt_created")
	private Timestamp courseGmtCreated;
    /**
     * 课程修改时间
     */
	//Field("course_gmt_modified")
	private Timestamp courseGmtModified;
    /**
     * 课程状态
     */
	//Field("course_status")
	private String courseStatus;


	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getPrecourseId() {
		return precourseId;
	}

	public void setPrecourseId(String precourseId) {
		this.precourseId = precourseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public BigDecimal getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(BigDecimal courseCredit) {
		this.courseCredit = courseCredit;
	}

	public Timestamp getCourseGmtCreated() {
		return courseGmtCreated;
	}

	public void setCourseGmtCreated(Timestamp courseGmtCreated) {
		this.courseGmtCreated = courseGmtCreated;
	}

	public Timestamp getCourseGmtModified() {
		return courseGmtModified;
	}

	public void setCourseGmtModified(Timestamp courseGmtModified) {
		this.courseGmtModified = courseGmtModified;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

}
