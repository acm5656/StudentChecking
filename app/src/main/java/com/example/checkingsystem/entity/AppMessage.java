package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * APP消息推送
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_app_message")
public class AppMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-uuid-32
     */
	//Id("app_message_id")
	private String appMessageId;
    /**
     * app消息类型(如系统消息,特定用户类型的消息,特定用户的消息)
     */
	//Field("app_message_type")
	private String appMessageType;
    /**
     * app消息优先级(0-9优先级从低到高)
     */
	//Field("app_message_priority")
	private Integer appMessagePriority;
    /**
     * app消息内容
     */
	//Field("app_message_content")
	private String appMessageContent;
    /**
     * app消息创建时间
     */
	//Field("app_message_gmt_created")
	private Timestamp appMessageGmtCreated;
    /**
     * app消息修改时间
     */
	//Field("app_message_gmt_modified")
	private Timestamp appMessageGmtModified;
    /**
     * app消息状态
     */
	//Field("app_message_status")
	private String appMessageStatus;


	public String getAppMessageId() {
		return appMessageId;
	}

	public void setAppMessageId(String appMessageId) {
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
