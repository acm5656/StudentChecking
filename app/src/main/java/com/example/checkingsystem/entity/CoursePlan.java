package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 课程计划表
表明开课的学期与上课的教师
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_course_plan")
public class CoursePlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程计划主键-sequence-id
     */
	//Id("course_plan_id")
	private String coursePlanId;
    /**
     * 外键-教师id
     */
	//Field("course_plan_teacher_id")
	private String coursePlanTeacherId;
    /**
     * 外键-课程id
     */
	//Field("course_plan_course_id")
	private String coursePlanCourseId;
    /**
     * 教学班号
     */
	//Field("course_plan_class_no")
	private String coursePlanClassNo;
    /**
     * 班级容量
     */
	//Field("course_plan_capacity")
	private Integer coursePlanCapacity;
    /**
     * 学期开始时间
     */
	//Field("course_plan_gmt_term_begin")
	private Timestamp coursePlanGmtTermBegin;
    /**
     * 学期结束时间
     */
	//Field("course_plan_gmt_term_end")
	private Timestamp coursePlanGmtTermEnd;
    /**
     * 计划创建时间
     */
	//Field("course_plan_gmt_created")
	private Timestamp coursePlanGmtCreated;
    /**
     * 计划修改时间
     */
	//Field("course_plan_gmt_modified")
	private Timestamp coursePlanGmtModified;
    /**
     * 课程计划状态
     */
	//Field("course_plan_status")
	private String coursePlanStatus;


	public String getCoursePlanId() {
		return coursePlanId;
	}

	public void setCoursePlanId(String coursePlanId) {
		this.coursePlanId = coursePlanId;
	}

	public String getCoursePlanTeacherId() {
		return coursePlanTeacherId;
	}

	public void setCoursePlanTeacherId(String coursePlanTeacherId) {
		this.coursePlanTeacherId = coursePlanTeacherId;
	}

	public String getCoursePlanCourseId() {
		return coursePlanCourseId;
	}

	public void setCoursePlanCourseId(String coursePlanCourseId) {
		this.coursePlanCourseId = coursePlanCourseId;
	}

	public String getCoursePlanClassNo() {
		return coursePlanClassNo;
	}

	public void setCoursePlanClassNo(String coursePlanClassNo) {
		this.coursePlanClassNo = coursePlanClassNo;
	}

	public Integer getCoursePlanCapacity() {
		return coursePlanCapacity;
	}

	public void setCoursePlanCapacity(Integer coursePlanCapacity) {
		this.coursePlanCapacity = coursePlanCapacity;
	}

	public Timestamp getCoursePlanGmtTermBegin() {
		return coursePlanGmtTermBegin;
	}

	public void setCoursePlanGmtTermBegin(Timestamp coursePlanGmtTermBegin) {
		this.coursePlanGmtTermBegin = coursePlanGmtTermBegin;
	}

	public Timestamp getCoursePlanGmtTermEnd() {
		return coursePlanGmtTermEnd;
	}

	public void setCoursePlanGmtTermEnd(Timestamp coursePlanGmtTermEnd) {
		this.coursePlanGmtTermEnd = coursePlanGmtTermEnd;
	}

	public Timestamp getCoursePlanGmtCreated() {
		return coursePlanGmtCreated;
	}

	public void setCoursePlanGmtCreated(Timestamp coursePlanGmtCreated) {
		this.coursePlanGmtCreated = coursePlanGmtCreated;
	}

	public Timestamp getCoursePlanGmtModified() {
		return coursePlanGmtModified;
	}

	public void setCoursePlanGmtModified(Timestamp coursePlanGmtModified) {
		this.coursePlanGmtModified = coursePlanGmtModified;
	}

	public String getCoursePlanStatus() {
		return coursePlanStatus;
	}

	public void setCoursePlanStatus(String coursePlanStatus) {
		this.coursePlanStatus = coursePlanStatus;
	}

}
