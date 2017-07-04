package com.example.checkingsystem.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 教师信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-20
 */
//Name("t_teacher")
@DatabaseTable
public class Teacher  {

	private static final long serialVersionUID = 1L;
	public static final String STATUS_INVALID = "invalid";//班级无效,被删除等

	/**
	 * 主键-sequence-id
	 */
	//Id("teacher_id")
	@DatabaseField(id = true)
	private String teacherId;
	/**
	 * 人脸ID
	 */
	//Field("teacher_face_id")
	@DatabaseField
	private String teacherFaceId;
	/**
	 * 声纹ID
	 */
	//Field("teacher_voiceprint_id")
	@DatabaseField
	private String teacherVoiceprintId;
	/**
	 * 教师手机号码
	 */
	//Field("teacher_tel")
	@DatabaseField
	private String teacherTel;
	/**
	 * 密码-md5
	 */
	//Field("teacher_password")
	@DatabaseField
	private String teacherPassword;
	/**
	 * 教师编号
	 */
	//Field("teacher_no")
	@DatabaseField
	private String teacherNo;
	/**
	 * 教师姓名
	 */
	//Field("teacher_name")
	@DatabaseField
	private String teacherName;
	/**
	 * 教师昵称
	 */
	//Field("teacher_nickname")
	@DatabaseField
	private String teacherNickname;
	/**
	 * 教师邮箱
	 */
	//Field("teacher_email")
	@DatabaseField
	private String teacherEmail;
	/**
	 * 教师头像URL
	 */
	//Field("teacher_headimage_url")
	@DatabaseField
	private String teacherHeadimageUrl;
	/**
	 * 性别
	 */
	//Field("teacher_gender")
	@DatabaseField
	private String teacherGender;
	/**
	 * 教师所在系/部门
	 */
	//Field("teacher_department")
	@DatabaseField
	private String teacherDepartment;
	/**
	 * 教师教务管理系统用户名
	 */
	//Field("teacher_school_username")
	@DatabaseField
	private String teacherSchoolUsername;
	/**
	 * 教师教务管理系统密码
	 */
	//Field("teacher_school_password")
	@DatabaseField
	private String teacherSchoolPassword;
	/**
	 * 记录创建时间
	 */
	//Field("teacher_gmt_created")
	@DatabaseField
	private Timestamp teacherGmtCreated;
	/**
	 * 记录修改时间
	 */
	//Field("teacher_gmt_modified")
	@DatabaseField
	private Timestamp teacherGmtModified;
	/**
	 * 教师状态
	 */
	//Field("teacher_status")
	@DatabaseField
	private String teacherStatus;


	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
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
