package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 导员信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_assistant")
public class Assistant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequence-id
     */
	//Id("assistant_id")
	private Long assistantId;
    /**
     * 导员电话号码
     */
	//("assistant_tel")
	private String assistantTel;
    /**
     * 导员密码
     */
	//("assistant_password")
	private String assistantPassword;
    /**
     * 导员编号(工作证ID)
     */
	//("assistant_no")
	private String assistantNo;
    /**
     * 导员姓名
     */
	//("assistant_name")
	private String assistantName;
    /**
     * 导员邮箱
     */
	//("assistant_email")
	private String assistantEmail;
    /**
     * 导员头像URL
     */
	//("assistant_headimage_url")
	private String assistantHeadimageUrl;
    /**
     * 导员性别
     */
	//("assistant_gender")
	private String assistantGender;
    /**
     * 导员教务系统用户名
     */
	//("assistant_school_username")
	private String assistantSchoolUsername;
    /**
     * 导员教务系统密码
     */
	//("assistant_school_password")
	private String assistantSchoolPassword;
    /**
     * 记录创建时间
     */
	//("assistant_gmt_created")
	private Timestamp assistantGmtCreated;
    /**
     * 记录更改时间
     */
	//("assistant_gmt_modified")
	private Timestamp assistantGmtModified;
    /**
     * 导员信息状态码
     */
	//("assistant_status")
	private String assistantStatus;


	public Long getAssistantId() {
		return assistantId;
	}

	public void setAssistantId(Long assistantId) {
		this.assistantId = assistantId;
	}

	public String getAssistantTel() {
		return assistantTel;
	}

	public void setAssistantTel(String assistantTel) {
		this.assistantTel = assistantTel;
	}

	public String getAssistantPassword() {
		return assistantPassword;
	}

	public void setAssistantPassword(String assistantPassword) {
		this.assistantPassword = assistantPassword;
	}

	public String getAssistantNo() {
		return assistantNo;
	}

	public void setAssistantNo(String assistantNo) {
		this.assistantNo = assistantNo;
	}

	public String getAssistantName() {
		return assistantName;
	}

	public void setAssistantName(String assistantName) {
		this.assistantName = assistantName;
	}

	public String getAssistantEmail() {
		return assistantEmail;
	}

	public void setAssistantEmail(String assistantEmail) {
		this.assistantEmail = assistantEmail;
	}

	public String getAssistantHeadimageUrl() {
		return assistantHeadimageUrl;
	}

	public void setAssistantHeadimageUrl(String assistantHeadimageUrl) {
		this.assistantHeadimageUrl = assistantHeadimageUrl;
	}

	public String getAssistantGender() {
		return assistantGender;
	}

	public void setAssistantGender(String assistantGender) {
		this.assistantGender = assistantGender;
	}

	public String getAssistantSchoolUsername() {
		return assistantSchoolUsername;
	}

	public void setAssistantSchoolUsername(String assistantSchoolUsername) {
		this.assistantSchoolUsername = assistantSchoolUsername;
	}

	public String getAssistantSchoolPassword() {
		return assistantSchoolPassword;
	}

	public void setAssistantSchoolPassword(String assistantSchoolPassword) {
		this.assistantSchoolPassword = assistantSchoolPassword;
	}

	public Timestamp getAssistantGmtCreated() {
		return assistantGmtCreated;
	}

	public void setAssistantGmtCreated(Timestamp assistantGmtCreated) {
		this.assistantGmtCreated = assistantGmtCreated;
	}

	public Timestamp getAssistantGmtModified() {
		return assistantGmtModified;
	}

	public void setAssistantGmtModified(Timestamp assistantGmtModified) {
		this.assistantGmtModified = assistantGmtModified;
	}

	public String getAssistantStatus() {
		return assistantStatus;
	}

	public void setAssistantStatus(String assistantStatus) {
		this.assistantStatus = assistantStatus;
	}

}
