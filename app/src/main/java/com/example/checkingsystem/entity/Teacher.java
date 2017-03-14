package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 教师信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequence-id
     */
	//Id("teacher_id")
	private Long teacherId;
    /**
     * 人脸ID
     */
	//("teacher_face_id")
	private String teacherFaceId;
    /**
     * 声纹ID
     */
	//("teacher_voiceprint_id")
	private String teacherVoiceprintId;
    /**
     * 教师手机号码
     */
	//("teacher_tel")
	private String teacherTel;
    /**
     * 密码-md5
     */
	//("teacher_password")
	private String teacherPassword;
    /**
     * 教师编号
     */
	//("teacher_no")
	private String teacherNo;
    /**
     * 教师姓名
     */
	//("teacher_name")
	private String teacherName;
    /**
     * 教师昵称
     */
	//("teacher_nickname")
	private String teacherNickname;
    /**
     * 教师邮箱
     */
	//("teacher_email")
	private String teacherEmail;
    /**
     * 教师头像URL
     */
	//("teacher_headimage_url")
	private String teacherHeadimageUrl;
    /**
     * 性别
     */
	//("teacher_gender")
	private String teacherGender;
    /**
     * 教师所在系/部门
     */
	//("teacher_ department")
	private String teacherDepartment;
    /**
     * 教师教务管理系统用户名
     */
	//("teacher_school_username")
	private String teacherSchoolUsername;
    /**
     * 教师教务管理系统密码
     */
	//("teacher_school_password")
	private String teacherSchoolPassword;
    /**
     * 记录创建时间
     */
	//("teacher_gmt_created")
	private Timestamp teacherGmtCreated;
    /**
     * 记录修改时间
     */
	//("teacher_gmt_modified")
	private Timestamp teacherGmtModified;
    /**
     * 教师状态
     */
	//("teacher_status")
	private String teacherStatus;


	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherFaceId() {
		return teacherFaceId;
	}

	public void setTeacherFaceId(String teacherFaceId) {
		this.teacherFaceId = teacherFaceId;
	}

	public String getTeacherVoiceprintId() {
		return teacherVoiceprintId;
	}

	public void setTeacherVoiceprintId(String teacherVoiceprintId) {
		this.teacherVoiceprintId = teacherVoiceprintId;
	}

	public String getTeacherTel() {
		return teacherTel;
	}

	public void setTeacherTel(String teacherTel) {
		this.teacherTel = teacherTel;
	}

	public String getTeacherPassword() {
		return teacherPassword;
	}

	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherNickname() {
		return teacherNickname;
	}

	public void setTeacherNickname(String teacherNickname) {
		this.teacherNickname = teacherNickname;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherHeadimageUrl() {
		return teacherHeadimageUrl;
	}

	public void setTeacherHeadimageUrl(String teacherHeadimageUrl) {
		this.teacherHeadimageUrl = teacherHeadimageUrl;
	}

	public String getTeacherGender() {
		return teacherGender;
	}

	public void setTeacherGender(String teacherGender) {
		this.teacherGender = teacherGender;
	}

	public String getTeacherDepartment() {
		return teacherDepartment;
	}

	public void setTeacherDepartment(String teacherDepartment) {
		this.teacherDepartment = teacherDepartment;
	}

	public String getTeacherSchoolUsername() {
		return teacherSchoolUsername;
	}

	public void setTeacherSchoolUsername(String teacherSchoolUsername) {
		this.teacherSchoolUsername = teacherSchoolUsername;
	}

	public String getTeacherSchoolPassword() {
		return teacherSchoolPassword;
	}

	public void setTeacherSchoolPassword(String teacherSchoolPassword) {
		this.teacherSchoolPassword = teacherSchoolPassword;
	}

	public Timestamp getTeacherGmtCreated() {
		return teacherGmtCreated;
	}

	public void setTeacherGmtCreated(Timestamp teacherGmtCreated) {
		this.teacherGmtCreated = teacherGmtCreated;
	}

	public Timestamp getTeacherGmtModified() {
		return teacherGmtModified;
	}

	public void setTeacherGmtModified(Timestamp teacherGmtModified) {
		this.teacherGmtModified = teacherGmtModified;
	}

	public String getTeacherStatus() {
		return teacherStatus;
	}

	public void setTeacherStatus(String teacherStatus) {
		this.teacherStatus = teacherStatus;
	}

}
