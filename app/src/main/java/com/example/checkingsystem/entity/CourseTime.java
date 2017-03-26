package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 课程时间信息
存储课程表,包括上课的周,天,时间
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_course_time")
public class CourseTime implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键sequence-id
     */
	//Id("course_time_id")
	private String courseTimeId;
    /**
     * 外键-课程计划id
     */
	//Field("course_time_plan_id")
	private String courseTimePlanId;
    /**
     * 上课的周
     */
	//Field("course_time_week")
	private Integer courseTimeWeek;
    /**
     * 上课的星期几
     */
	//Field("course_time_day")
	private Integer courseTimeDay;
    /**
     * 课开始的时间
     */
	//Field("course_time_gmt_begin")
	private Timestamp courseTimeGmtBegin;
    /**
     * 课结束的时间
     */
	//Field("course_time_gmt_end")
	private Timestamp courseTimeGmtEnd;
    /**
     * 创建时间
     */
	//Field("course_time_gmt_created")
	private Timestamp courseTimeGmtCreated;
    /**
     * 修改时间
     */
	//Field("course_time_gmt_modified")
	private Timestamp courseTimeGmtModified;
    /**
     * 课程时间记录状态
     */
	//Field("course_time_status")
	private String courseTimeStatus;


	public String getCourseTimeId() {
		return courseTimeId;
	}

	public void setCourseTimeId(String courseTimeId) {
		this.courseTimeId = courseTimeId;
	}

	public String getCourseTimePlanId() {
		return courseTimePlanId;
	}

	public void setCourseTimePlanId(String courseTimePlanId) {
		this.courseTimePlanId = courseTimePlanId;
	}

	public Integer getCourseTimeWeek() {
		return courseTimeWeek;
	}

	public void setCourseTimeWeek(Integer courseTimeWeek) {
		this.courseTimeWeek = courseTimeWeek;
	}

	public Integer getCourseTimeDay() {
		return courseTimeDay;
	}

	public void setCourseTimeDay(Integer courseTimeDay) {
		this.courseTimeDay = courseTimeDay;
	}

	public Timestamp getCourseTimeGmtBegin() {
		return courseTimeGmtBegin;
	}

	public void setCourseTimeGmtBegin(Timestamp courseTimeGmtBegin) {
		this.courseTimeGmtBegin = courseTimeGmtBegin;
	}

	public Timestamp getCourseTimeGmtEnd() {
		return courseTimeGmtEnd;
	}

	public void setCourseTimeGmtEnd(Timestamp courseTimeGmtEnd) {
		this.courseTimeGmtEnd = courseTimeGmtEnd;
	}

	public Timestamp getCourseTimeGmtCreated() {
		return courseTimeGmtCreated;
	}

	public void setCourseTimeGmtCreated(Timestamp courseTimeGmtCreated) {
		this.courseTimeGmtCreated = courseTimeGmtCreated;
	}

	public Timestamp getCourseTimeGmtModified() {
		return courseTimeGmtModified;
	}

	public void setCourseTimeGmtModified(Timestamp courseTimeGmtModified) {
		this.courseTimeGmtModified = courseTimeGmtModified;
	}

	public String getCourseTimeStatus() {
		return courseTimeStatus;
	}

	public void setCourseTimeStatus(String courseTimeStatus) {
		this.courseTimeStatus = courseTimeStatus;
	}

}
