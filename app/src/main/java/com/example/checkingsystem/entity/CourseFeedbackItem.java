package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 课程难易反馈条目
每条记录表明是学生每次进行难易反馈操作的记录
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_course_feedback_item")
public class CourseFeedbackItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-uuid-32
     */
	//Id("course_feedback_item_id")
	private String courseFeedbackItemId;
    /**
     * 外键-student_id
     */
	//Field("couese_feedback_item_stu_id")
	private String coueseFeedbackItemStuId;
    /**
     * 学生反馈的结果
     */
	//Field("course_feedback_item_result")
	private String courseFeedbackItemResult;
    /**
     * 记录创建时间
     */
	//Field("course_feedback_item_gmt_created")
	private Timestamp courseFeedbackItemGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("course_feedback_item_gmt_modified")
	private Timestamp courseFeedbackItemGmtModified;
    /**
     * 课程难易反馈记录状态
     */
	//Field("course_feedback_item_status")
	private String courseFeedbackItemStatus;


	public String getCourseFeedbackItemId() {
		return courseFeedbackItemId;
	}

	public void setCourseFeedbackItemId(String courseFeedbackItemId) {
		this.courseFeedbackItemId = courseFeedbackItemId;
	}

	public String getCoueseFeedbackItemStuId() {
		return coueseFeedbackItemStuId;
	}

	public void setCoueseFeedbackItemStuId(String coueseFeedbackItemStuId) {
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
