package com.example.checkingsystem.entity;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 * <p>
 * 课程难易反馈条目
每条记录表明是学生每次进行难易反馈操作的记录
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_course_feedback_item")
public class CourseFeedbackItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("course_feedback_item_id")
	private Long courseFeedbackItemId;
    /**
     * 外键-student_id
     */
	//("couese_feedback_item_stu_id")
	private Long coueseFeedbackItemStuId;
    /**
     * 学生反馈的结果
     */
	//("course_feedback_item_result")
	private String courseFeedbackItemResult;
    /**
     * 记录创建时间
     */
	//("course_feedback_item_gmt_created")
	private Timestamp courseFeedbackItemGmtCreated;
    /**
     * 记录修改时间
     */
	//("course_feedback_item_gmt_modified")
	private Timestamp courseFeedbackItemGmtModified;
    /**
     * 课程难易反馈记录状态
     */
	//("course_feedback_item_status")
	private String courseFeedbackItemStatus;


	public Long getCourseFeedbackItemId() {
		return courseFeedbackItemId;
	}

	public void setCourseFeedbackItemId(Long courseFeedbackItemId) {
		this.courseFeedbackItemId = courseFeedbackItemId;
	}

	public Long getCoueseFeedbackItemStuId() {
		return coueseFeedbackItemStuId;
	}

	public void setCoueseFeedbackItemStuId(Long coueseFeedbackItemStuId) {
		this.coueseFeedbackItemStuId = coueseFeedbackItemStuId;
	}

	public String getCourseFeedbackItemResult() {
		return courseFeedbackItemResult;
	}

	public void setCourseFeedbackItemResult(String courseFeedbackItemResult) {
		this.courseFeedbackItemResult = courseFeedbackItemResult;
	}

	public Timestamp getCourseFeedbackItemGmtCreated() {
		return courseFeedbackItemGmtCreated;
	}

	public void setCourseFeedbackItemGmtCreated(Timestamp courseFeedbackItemGmtCreated) {
		this.courseFeedbackItemGmtCreated = courseFeedbackItemGmtCreated;
	}

	public Timestamp getCourseFeedbackItemGmtModified() {
		return courseFeedbackItemGmtModified;
	}

	public void setCourseFeedbackItemGmtModified(Timestamp courseFeedbackItemGmtModified) {
		this.courseFeedbackItemGmtModified = courseFeedbackItemGmtModified;
	}

	public String getCourseFeedbackItemStatus() {
		return courseFeedbackItemStatus;
	}

	public void setCourseFeedbackItemStatus(String courseFeedbackItemStatus) {
		this.courseFeedbackItemStatus = courseFeedbackItemStatus;
	}

}
