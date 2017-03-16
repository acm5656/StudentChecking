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
 * @since 2017-03-13
 */
@DatabaseTable
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequence-id
     */
	@DatabaseField(id = true)
	private Long teacherId;
    /**
     * 人脸ID
     */
	@DatabaseField
	private String teacherFaceId;
    /**
     * 声纹ID
     */
	@DatabaseField
	private String teacherVoiceprintId;
    /**
     * 教师手机号码
     */
	@DatabaseField
	private String teacherTel;
    /**
     * 密码-md5
     */
	@DatabaseField
	private String teacherPassword;
    /**
     * 教师编号
     */
	@DatabaseField
	private String teacherNo;
    /**
     * 教师姓名
     */
	@DatabaseField
	private String teacherName;
    /**
     * 教师昵称
     */
	@DatabaseField
	private String teacherNickname;
    /**
     * 教师邮箱
     */
	@DatabaseField
	private String teacherEmail;
    /**
     * 教师头像URL
     */
	@DatabaseField
	private String teacherHeadimageUrl;
    /**
     * 性别
     */
	@DatabaseField
	private String teacherGender;
    /**
     * 教师所在系/部门
     */
	@DatabaseField
	private String teacherDepartment;
    /**
     * 教师教务管理系统用户名
     */
	@DatabaseField
	private String teacherSchoolUsername;
    /**
     * 教师教务管理系统密码
     */
	@DatabaseField
	private String teacherSchoolPassword;
    /**
     * 记录创建时间
     */
	@DatabaseField
	private Timestamp teacherGmtCreated;
    /**
     * 记录修改时间
     */
	@DatabaseField
	private Timestamp teacherGmtModified;
    /**
     * 教师状态
     */
	@DatabaseField
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

	@Override
	public String toString() {
		return "Teacher{" +
				"teacherId=" + teacherId +
				", teacherFaceId='" + teacherFaceId + '\'' +
				", teacherVoiceprintId='" + teacherVoiceprintId + '\'' +
				", teacherTel='" + teacherTel + '\'' +
				", teacherPassword='" + teacherPassword + '\'' +
				", teacherNo='" + teacherNo + '\'' +
				", teacherName='" + teacherName + '\'' +
				", teacherNickname='" + teacherNickname + '\'' +
				", teacherEmail='" + teacherEmail + '\'' +
				", teacherHeadimageUrl='" + teacherHeadimageUrl + '\'' +
				", teacherGender='" + teacherGender + '\'' +
				", teacherDepartment='" + teacherDepartment + '\'' +
				", teacherSchoolUsername='" + teacherSchoolUsername + '\'' +
				", teacherSchoolPassword='" + teacherSchoolPassword + '\'' +
				", teacherGmtCreated=" + teacherGmtCreated +
				", teacherGmtModified=" + teacherGmtModified +
				", teacherStatus='" + teacherStatus + '\'' +
				'}';
	}
}
