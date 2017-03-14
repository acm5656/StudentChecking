package com.example.checkingsystem.entity;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 * <p>
 * 成绩信息
用来关联学生和课程计划,表明学生选了此门课
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_grade")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("grade_id")
	private Long gradeId;
    /**
     * 外键-student-id
     */
	//("grade_student_id")
	private Long gradeStudentId;
    /**
     * 外键-course_plan_id
     */
	//("grade_course_plan_id")
	private Long gradeCoursePlanId;
    /**
     * 成绩类型-表明使用score还是level记录成绩
     */
	//("grade_type")
	private String gradeType;
    /**
     * 成绩分数
     */
	//("grade_score")
	private Integer gradeScore;
    /**
     * 成绩级别(一种记录分数的方式如优良中差)
     */
	//("grade_level")
	private String gradeLevel;
	//("grade_gmt_created")
	private Timestamp gradeGmtCreated;
	//("grade_gmt_modified")
	private Timestamp gradeGmtModified;
    /**
     * 成绩记录的状态
     */
	//("grade_status")
	private String gradeStatus;


	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getGradeStudentId() {
		return gradeStudentId;
	}

	public void setGradeStudentId(Long gradeStudentId) {
		this.gradeStudentId = gradeStudentId;
	}

	public Long getGradeCoursePlanId() {
		return gradeCoursePlanId;
	}

	public void setGradeCoursePlanId(Long gradeCoursePlanId) {
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
