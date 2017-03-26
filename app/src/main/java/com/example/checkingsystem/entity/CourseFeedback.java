package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 课程难易反馈
每条记录代表老师发起的一次难易反馈
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_course_feedback")
public class CourseFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-uuid-32
     */
	//Id("course_feedback_id")
	private String courseFeedbackId;
    /**
     * 外键-course_time_id
     */
	//Field("course_feedback_ctime_id")
	private String courseFeedbackCtimeId;
    /**
     * 备注
     */
	//Field("course_feedback_note")
	private String courseFeedbackNote;
    /**
     * 记录创建时间
     */
	//Field("course_feedback_gmt_created")
	private Timestamp courseFeedbackGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("course_feedback_gmt_modified")
	private Timestamp courseFeedbackGmtModified;
    /**
     * 计数-认为简单的人数
     */
	//Field("course_feedback_easy_count")
	private Integer courseFeedbackEasyCount;
    /**
     * 计数-认为难的人数
     */
	//Field("course_feedback_difficulty_count")
	private Integer courseFeedbackDifficultyCount;
    /**
     * 计数-认为难度一般的人数
     */
	//Field("course_feedback_general_count")
	private Integer courseFeedbackGeneralCount;
    /**
     * 计数-没有反馈或其他难度的人数
     */
	//Field("course_feedback_other_count")
	private Integer courseFeedbackOtherCount;
    /**
     * 课程难易反馈记录状态
     */
	//Field("course_feedback_status")
	private String courseFeedbackStatus;


	public String getCourseFeedbackId() {
		return courseFeedbackId;
	}

	public void setCourseFeedbackId(String courseFeedbackId) {
		this.courseFeedbackId = courseFeedbackId;
	}

	public String getCourseFeedbackCtimeId() {
		return courseFeedbackCtimeId;
	}

	public void setCourseFeedbackCtimeId(String courseFeedbackCtimeId) {
		this.courseFeedbackCtimeId = courseFeedbackCtimeId;
	}

	public String getCourseFeedbackNote() {
		return courseFeedbackNote;
	}

	public void setCourseFeedbackNote(String courseFeedbackNote) {
		this.courseFeedbackNote = courseFeedbackNote;
	}

	public Timestamp getCourseFeedbackGmtCreated() {
		return courseFeedbackGmtCreated;
	}

	public void setCourseFeedbackGmtCreated(Timestamp courseFeedbackGmtCreated) {
		this.courseFeedbackGmtCreated = courseFeedbackGmtCreated;
	}

	public Timestamp getCourseFeedbackGmtModified() {
		return courseFeedbackGmtModified;
	}

	public void setCourseFeedbackGmtModified(Timestamp courseFeedbackGmtModified) {
		this.courseFeedbackGmtModified = courseFeedbackGmtModified;
	}

	public Integer getCourseFeedbackEasyCount() {
		return courseFeedbackEasyCount;
	}

	public void setCourseFeedbackEasyCount(Integer courseFeedbackEasyCount) {
		this.courseFeedbackEasyCount = courseFeedbackEasyCount;
	}

	public Integer getCourseFeedbackDifficultyCount() {
		return courseFeedbackDifficultyCount;
	}

	public void setCourseFeedbackDifficultyCount(Integer courseFeedbackDifficultyCount) {
		this.courseFeedbackDifficultyCount = courseFeedbackDifficultyCount;
	}

	public Integer getCourseFeedbackGeneralCount() {
		return courseFeedbackGeneralCount;
	}

	public void setCourseFeedbackGeneralCount(Integer courseFeedbackGeneralCount) {
		this.courseFeedbackGeneralCount = courseFeedbackGeneralCount;
	}

	public Integer getCourseFeedbackOtherCount() {
		return courseFeedbackOtherCount;
	}

	public void setCourseFeedbackOtherCount(Integer courseFeedbackOtherCount) {
		this.courseFeedbackOtherCount = courseFeedbackOtherCount;
	}

	public String getCourseFeedbackStatus() {
		return courseFeedbackStatus;
	}

	public void setCourseFeedbackStatus(String courseFeedbackStatus) {
		this.courseFeedbackStatus = courseFeedbackStatus;
	}

}
