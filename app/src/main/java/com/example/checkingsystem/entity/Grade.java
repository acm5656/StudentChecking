package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 成绩信息
用来关联学生和课程计划,表明学生选了此门课
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_grade")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-uuid-32
     */
	//Id("grade_id")
	private String gradeId;
    /**
     * 外键-student-id
     */
	//Field("grade_student_id")
	private String gradeStudentId;
    /**
     * 外键-course_plan_id
     */
	//Field("grade_course_plan_id")
	private String gradeCoursePlanId;
    /**
     * 成绩类型-表明使用score还是level记录成绩
     */
	//Field("grade_type")
	private String gradeType;
    /**
     * 成绩分数
     */
	//Field("grade_score")
	private Integer gradeScore;
    /**
     * 成绩级别(一种记录分数的方式如优良中差)
     */
	//Field("grade_level")
	private String gradeLevel;
	//Field("grade_gmt_created")
	private Timestamp gradeGmtCreated;
	//Field("grade_gmt_modified")
	private Timestamp gradeGmtModified;
    /**
     * 成绩记录的状态
     */
	//Field("grade_status")
	private String gradeStatus;


	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeStudentId() {
		return gradeStudentId;
	}

	public void setGradeStudentId(String gradeStudentId) {
		this.gradeStudentId = gradeStudentId;
	}

	public String getGradeCoursePlanId() {
		return gradeCoursePlanId;
	}

	public void setGradeCoursePlanId(String gradeCoursePlanId) {
		this.gradeCoursePlanId = gradeCoursePlanId;
	}

	public String getGradeType() {
		return gradeType;
	}

	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}

	public Integer getGradeScore() {
		return gradeScore;
	}

	public void setGradeScore(Integer gradeScore) {
		this.gradeScore = gradeScore;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public Timestamp getGradeGmtCreated() {
		return gradeGmtCreated;
	}

	public void setGradeGmtCreated(Timestamp gradeGmtCreated) {
		this.gradeGmtCreated = gradeGmtCreated;
	}

	public Timestamp getGradeGmtModified() {
		return gradeGmtModified;
	}

	public void setGradeGmtModified(Timestamp gradeGmtModified) {
		this.gradeGmtModified = gradeGmtModified;
	}

	public String getGradeStatus() {
		return gradeStatus;
	}

	public void setGradeStatus(String gradeStatus) {
		this.gradeStatus = gradeStatus;
	}

}
