package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * APP消息推送
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_app_message")
public class AppMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * app消息id
     */
	//Id("app_message_id")
	private Long appMessageId;
    /**
     * app消息类型(如系统消息,特定用户类型的消息,特定用户的消息)
     */
	//("app_message_type")
	private String appMessageType;
    /**
     * app消息优先级(0-9优先级从低到高)
     */
	//("app_message_priority")
	private Integer appMessagePriority;
    /**
     * app消息内容
     */
	//("app_message_content")
	private String appMessageContent;
    /**
     * app消息创建时间
     */
	//("app_message_gmt_created")
	private Timestamp appMessageGmtCreated;
    /**
     * app消息修改时间
     */
	//("app_message_gmt_modified")
	private Timestamp appMessageGmtModified;
    /**
     * app消息状态
     */
	//("app_message_status")
	private String appMessageStatus;


	public Long getAppMessageId() {
		return appMessageId;
	}

	public void setAppMessageId(Long appMessageId) {
		this.appMessageId = appMessageId;
	}

	public String getAppMessageType() {
		return appMessageType;
	}

	public void setAppMessageType(String appMessageType) {
		this.appMessageType = appMessageType;
	}

	public Integer getAppMessagePriority() {
		return appMessagePriority;
	}

	public void setAppMessagePriority(Integer appMessagePriority) {
		this.appMessagePriority = appMessagePriority;
	}

	public String getAppMessageContent() {
		return appMessageContent;
	}

	public void setAppMessageContent(String appMessageContent) {
		this.appMessageContent = appMessageContent;
	}

	public Timestamp getAppMessageGmtCreated() {
		return appMessageGmtCreated;
	}

	public void setAppMessageGmtCreated(Timestamp appMessageGmtCreated) {
		this.appMessageGmtCreated = appMessageGmtCreated;
	}

	public Timestamp getAppMessageGmtModified() {
		return appMessageGmtModified;
	}

	public void setAppMessageGmtModified(Timestamp appMessageGmtModified) {
		this.appMessageGmtModified = appMessageGmtModified;
	}

	public String getAppMessageStatus() {
		return appMessageStatus;
	}

	public void setAppMessageStatus(String appMessageStatus) {
		this.appMessageStatus = appMessageStatus;
	}

}
