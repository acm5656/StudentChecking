package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 课程提问信息
每条记录代表教师提问的一个问题记录
一个问题可以有多个学生回答
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_course_quiz")
public class CourseQuiz implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-uuid-32
     */
	//Id("course_quiz_id")
	private String courseQuizId;
    /**
     * 外键-course_time_id
     */
	//Field("course_quiz_ctime_id")
	private String courseQuizCtimeId;
    /**
     * 记录创建时间
     */
	//Field("course_quiz_gmt_created")
	private Timestamp courseQuizGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("course_quiz_gmt_modified")
	private Timestamp courseQuizGmtModified;
    /**
     * 问题内容
     */
	//Field("course_quiz_content")
	private String courseQuizContent;
    /**
     * 问题记录状态
     */
	//Field("course_quiz_status")
	private String courseQuizStatus;


	public String getCourseQuizId() {
		return courseQuizId;
	}

	public void setCourseQuizId(String courseQuizId) {
		this.courseQuizId = courseQuizId;
	}

	public String getCourseQuizCtimeId() {
		return courseQuizCtimeId;
	}

	public void setCourseQuizCtimeId(String courseQuizCtimeId) {
		this.courseQuizCtimeId = courseQuizCtimeId;
	}

	public Timestamp getCourseQuizGmtCreated() {
		return courseQuizGmtCreated;
	}

	public void setCourseQuizGmtCreated(Timestamp courseQuizGmtCreated) {
		this.courseQuizGmtCreated = courseQuizGmtCreated;
	}

	public Timestamp getCourseQuizGmtModified() {
		return courseQuizGmtModified;
	}

	public void setCourseQuizGmtModified(Timestamp courseQuizGmtModified) {
		this.courseQuizGmtModified = courseQuizGmtModified;
	}

	public String getCourseQuizContent() {
		return courseQuizContent;
	}

	public void setCourseQuizContent(String courseQuizContent) {
		this.courseQuizContent = courseQuizContent;
	}

	public String getCourseQuizStatus() {
		return courseQuizStatus;
	}

	public void setCourseQuizStatus(String courseQuizStatus) {
		this.courseQuizStatus = courseQuizStatus;
	}

}
