package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * app反馈信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_app_feedback")
public class AppFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-uuid-32
     */
	//Id("app_feedback_id")
	private String appFeedbackId;
    /**
     * 外键-app_id
     */
	//Field("app_feedback_app_id")
	private String appFeedbackAppId;
    /**
     * 外键-user_id
     */
	//Field("app_feedback_user_id")
	private String appFeedbackUserId;
    /**
     * 外键-processor_id(外键处理者id,未实现)
     */
	//Field("app_feedback_processor_id")
	private String appFeedbackProcessorId;
    /**
     * 反馈用户的手机号
     */
	//Field("app_feedback_user_tel")
	private String appFeedbackUserTel;
    /**
     * 反馈的用户email
     */
	//Field("app_feedback_user_email")
	private String appFeedbackUserEmail;
    /**
     * 反馈用户的姓名
     */
	//Field("app_feedback_user_name")
	private String appFeedbackUserName;
    /**
     * 反馈的内容
     */
	//Field("app_feedback_content")
	private String appFeedbackContent;
    /**
     * 反馈的创建时间
     */
	//Field("app_feedback_gmt_created")
	private Timestamp appFeedbackGmtCreated;
    /**
     * 反馈的修改时间
     */
	//Field("app_feedback_gmt_modified")
	private Timestamp appFeedbackGmtModified;
    /**
     * 反馈的状态
     */
	//Field("app_feedback_status")
	private String appFeedbackStatus;


	public String getAppFeedbackId() {
		return appFeedbackId;
	}

	public void setAppFeedbackId(String appFeedbackId) {
		this.appFeedbackId = appFeedbackId;
	}

	public String getAppFeedbackAppId() {
		return appFeedbackAppId;
	}

	public void setAppFeedbackAppId(String appFeedbackAppId) {
		this.appFeedbackAppId = appFeedbackAppId;
	}

	public String getAppFeedbackUserId() {
		return appFeedbackUserId;
	}

	public void setAppFeedbackUserId(String appFeedbackUserId) {
		this.appFeedbackUserId = appFeedbackUserId;
	}

	public String getAppFeedbackProcessorId() {
		return appFeedbackProcessorId;
	}

	public void setAppFeedbackProcessorId(String appFeedbackProcessorId) {
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
