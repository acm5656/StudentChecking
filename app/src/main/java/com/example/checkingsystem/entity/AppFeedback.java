package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * app反馈信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_app_feedback")
public class AppFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("app_feedback_id")
	private Long appFeedbackId;
    /**
     * 外键-app_id
     */
	//("app_feedback_app_id")
	private Long appFeedbackAppId;
    /**
     * 外键-user_id
     */
	//("app_feedback_user_id")
	private Long appFeedbackUserId;
    /**
     * 外键-processor_id(外键处理者id,未实现)
     */
	//("app_feedback_processor_id")
	private Long appFeedbackProcessorId;
    /**
     * 反馈用户的手机号
     */
	//("app_feedback_user_tel")
	private String appFeedbackUserTel;
    /**
     * 反馈的用户email
     */
	//("app_feedback_user_email")
	private String appFeedbackUserEmail;
    /**
     * 反馈用户的姓名
     */
	//("app_feedback_user_name")
	private String appFeedbackUserName;
    /**
     * 反馈的内容
     */
	//("app_feedback_content")
	private String appFeedbackContent;
    /**
     * 反馈的创建时间
     */
	//("app_feedback_gmt_created")
	private Timestamp appFeedbackGmtCreated;
    /**
     * 反馈的修改时间
     */
	//("app_feedback_gmt_modified")
	private Timestamp appFeedbackGmtModified;
    /**
     * 反馈的状态
     */
	//("app_feedback_status")
	private String appFeedbackStatus;


	public Long getAppFeedbackId() {
		return appFeedbackId;
	}

	public void setAppFeedbackId(Long appFeedbackId) {
		this.appFeedbackId = appFeedbackId;
	}

	public Long getAppFeedbackAppId() {
		return appFeedbackAppId;
	}

	public void setAppFeedbackAppId(Long appFeedbackAppId) {
		this.appFeedbackAppId = appFeedbackAppId;
	}

	public Long getAppFeedbackUserId() {
		return appFeedbackUserId;
	}

	public void setAppFeedbackUserId(Long appFeedbackUserId) {
		this.appFeedbackUserId = appFeedbackUserId;
	}

	public Long getAppFeedbackProcessorId() {
		return appFeedbackProcessorId;
	}

	public void setAppFeedbackProcessorId(Long appFeedbackProcessorId) {
		this.appFeedbackProcessorId = appFeedbackProcessorId;
	}

	public String getAppFeedbackUserTel() {
		return appFeedbackUserTel;
	}

	public void setAppFeedbackUserTel(String appFeedbackUserTel) {
		this.appFeedbackUserTel = appFeedbackUserTel;
	}

	public String getAppFeedbackUserEmail() {
		return appFeedbackUserEmail;
	}

	public void setAppFeedbackUserEmail(String appFeedbackUserEmail) {
		this.appFeedbackUserEmail = appFeedbackUserEmail;
	}

	public String getAppFeedbackUserName() {
		return appFeedbackUserName;
	}

	public void setAppFeedbackUserName(String appFeedbackUserName) {
		this.appFeedbackUserName = appFeedbackUserName;
	}

	public String getAppFeedbackContent() {
		return appFeedbackContent;
	}

	public void setAppFeedbackContent(String appFeedbackContent) {
		this.appFeedbackContent = appFeedbackContent;
	}

	public Timestamp getAppFeedbackGmtCreated() {
		return appFeedbackGmtCreated;
	}

	public void setAppFeedbackGmtCreated(Timestamp appFeedbackGmtCreated) {
		this.appFeedbackGmtCreated = appFeedbackGmtCreated;
	}

	public Timestamp getAppFeedbackGmtModified() {
		return appFeedbackGmtModified;
	}

	public void setAppFeedbackGmtModified(Timestamp appFeedbackGmtModified) {
		this.appFeedbackGmtModified = appFeedbackGmtModified;
	}

	public String getAppFeedbackStatus() {
		return appFeedbackStatus;
	}

	public void setAppFeedbackStatus(String appFeedbackStatus) {
		this.appFeedbackStatus = appFeedbackStatus;
	}

}
