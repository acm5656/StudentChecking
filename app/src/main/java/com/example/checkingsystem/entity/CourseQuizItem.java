package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 课堂提问记录
对应某学生对某个问题的回答记录
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_course_quiz_item")
public class CourseQuizItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-uuid-32
     */
	//Id("course_quiz_item_id")
	private String courseQuizItemId;
    /**
     * 外键-student_id
     */
	//Field("course_quiz_item_stu_id")
	private String courseQuizItemStuId;
    /**
     * 回答内容
     */
	//Field("course_quiz_item_result")
	private String courseQuizItemResult;
    /**
     * 回答评价
     */
	//Field("course_quiz_item_assessment")
	private String courseQuizItemAssessment;
    /**
     * 回答分数
     */
	//Field("course_quiz_item_score")
	private Integer courseQuizItemScore;
    /**
     * 记录创建时间
     */
	//Field("course_quiz_item_gmt_created")
	private Timestamp courseQuizItemGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("course_quiz_item_gmt_modified")
	private Timestamp courseQuizItemGmtModified;
    /**
     * 记录状态
     */
	//Field("course_quiz_item_status")
	private String courseQuizItemStatus;


	public String getCourseQuizItemId() {
		return courseQuizItemId;
	}

	public void setCourseQuizItemId(String courseQuizItemId) {
		this.courseQuizItemId = courseQuizItemId;
	}

	public String getCourseQuizItemStuId() {
		return courseQuizItemStuId;
	}

	public void setCourseQuizItemStuId(String courseQuizItemStuId) {
		this.courseQuizItemStuId = courseQuizItemStuId;
	}

	public String getCourseQuizItemResult() {
		return courseQuizItemResult;
	}

	public void setCourseQuizItemResult(String courseQuizItemResult) {
		this.courseQuizItemResult = courseQuizItemResult;
	}

	public String getCourseQuizItemAssessment() {
		return courseQuizItemAssessment;
	}

	public void setCourseQuizItemAssessment(String courseQuizItemAssessment) {
		this.courseQuizItemAssessment = courseQuizItemAssessment;
	}

	public Integer getCourseQuizItemScore() {
		return courseQuizItemScore;
	}

	public void setCourseQuizItemScore(Integer courseQuizItemScore) {
		this.courseQuizItemScore = courseQuizItemScore;
	}

	public Timestamp getCourseQuizItemGmtCreated() {
		return courseQuizItemGmtCreated;
	}

	public void setCourseQuizItemGmtCreated(Timestamp courseQuizItemGmtCreated) {
		this.courseQuizItemGmtCreated = courseQuizItemGmtCreated;
	}

	public Timestamp getCourseQuizItemGmtModified() {
		return courseQuizItemGmtModified;
	}

	public void setCourseQuizItemGmtModified(Timestamp courseQuizItemGmtModified) {
		this.courseQuizItemGmtModified = courseQuizItemGmtModified;
	}

	public String getCourseQuizItemStatus() {
		return courseQuizItemStatus;
	}

	public void setCourseQuizItemStatus(String courseQuizItemStatus) {
		this.courseQuizItemStatus = courseQuizItemStatus;
	}

}
