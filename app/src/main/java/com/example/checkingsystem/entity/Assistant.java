package com.example.checkingsystem.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 * <p>
 * 导员信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_assistant")
@DatabaseTable
public class Assistant {

    private static final long serialVersionUID = 1L;
	public static final String STATUS_INVALID = "invalid";//班级无效,被删除等

    /**
     * 主键-sequence-id
     */
	//Id("assistant_id")
	@DatabaseField(id = true)
	private String assistantId;
    /**
     * 导员电话号码
     */
	//Field("assistant_tel")
	@DatabaseField
	private String assistantTel;
    /**
     * 导员密码
     */
	//Field("assistant_password")
	@DatabaseField
	private String assistantPassword;
    /**
     * 导员编号(工作证ID)
     */
	//Field("assistant_no")
	@DatabaseField
	private String assistantNo;
    /**
     * 导员姓名
     */
	//Field("assistant_name")
	@DatabaseField
	private String assistantName;
    /**
     * 导员邮箱
     */
	//Field("assistant_email")
	@DatabaseField
	private String assistantEmail;
    /**
     * 导员头像URL
     */
	//Field("assistant_headimage_url")
	@DatabaseField
	private String assistantHeadimageUrl;
    /**
     * 导员性别
     */
	//Field("assistant_gender")
	@DatabaseField
	private String assistantGender;
    /**
     * 导员教务系统用户名
     */
	//Field("assistant_school_username")
	@DatabaseField
	private String assistantSchoolUsername;
    /**
     * 导员教务系统密码
     */
	//Field("assistant_school_password")
	@DatabaseField
	private String assistantSchoolPassword;
    /**
     * 记录创建时间
     */
	//Field("assistant_gmt_created")
	@DatabaseField
	private Timestamp assistantGmtCreated;
    /**
     * 记录更改时间
     */
	//Field("assistant_gmt_modified")
	@DatabaseField
	private Timestamp assistantGmtModified;
    /**
     * 导员信息状态码
     */
	//Field("assistant_status")
	@DatabaseField
	private String assistantStatus;


	public String getAssistantId() {
		return assistantId;
	}

	public void setAssistantId(String assistantId) {
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
