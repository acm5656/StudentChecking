package com.example.checkingsystem.entity;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 * <p>
 * 课程提问信息
每条记录代表教师提问的一个问题记录
一个问题可以有多个学生回答
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_course_quiz")
public class CourseQuiz implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("course_quiz_id")
	private Long courseQuizId;
    /**
     * 外键-course_time_id
     */
	//("course_quiz_ctime_id")
	private Long courseQuizCtimeId;
    /**
     * 记录创建时间
     */
	//("course_quiz_gmt_created")
	private Timestamp courseQuizGmtCreated;
    /**
     * 记录修改时间
     */
	//("course_quiz_gmt_modified")
	private Timestamp courseQuizGmtModified;
    /**
     * 问题内容
     */
	//("course_quiz_content")
	private String courseQuizContent;
    /**
     * 问题记录状态
     */
	//("course_quiz_status")
	private String courseQuizStatus;


	public Long getCourseQuizId() {
		return courseQuizId;
	}

	public void setCourseQuizId(Long courseQuizId) {
		this.courseQuizId = courseQuizId;
	}

	public Long getCourseQuizCtimeId() {
		return courseQuizCtimeId;
	}

	public void setCourseQuizCtimeId(Long courseQuizCtimeId) {
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
