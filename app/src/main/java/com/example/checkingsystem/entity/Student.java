package com.example.checkingsystem.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 * <p>
 * 学生信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
@DatabaseTable
public class Student{

    private static final long serialVersionUID = 1L;
    public static final String STATUS_WAIT_AUTH = "wait_auth";//
    public static final String STATUS_WAIT_AUTH_DEAL = "wait_auth_deal";//可以被加入
    public static final String STATUS_AUTH_FAIL = "auth_fail";//可以被加入
    public static final String STATUS_AUTH_PASS = "auth_pass";//可以被加入
    public static final String STATUS_INVALID = "invalid";//无效,被删除等


    /**
     * 主键,一个生成全局唯一Sequence ID
     */
	@DatabaseField(id = true)
	private String studentId;
    /**
     * 人脸验证id
     */
	@DatabaseField
	private String studentFacecode;
    /**
     * 声纹id
     */
	@DatabaseField
	private String studentVoiceprintId;
    /**
     * 学生手机号
     */
	@DatabaseField
	private String studentTel;
    /**
     * 学生密码
     */
	@DatabaseField
	private String studentPassword;
    /**
     * 学生学号
     */
	@DatabaseField
	private String studentNo;
    /**
     * 学生真实姓名
     */
	@DatabaseField
	private String studentName;
    /**
     * 学生昵称
     */
	@DatabaseField
	private String studentNickname;
    /**
     * 学生email地址
     */
	@DatabaseField
	private String studentEmail;
    /**
     * 学生头像URL
     */
	@DatabaseField
	private String studentHeadimageUrl;
    /**
     * 学生性别
     */
	@DatabaseField
	private String studentGender;
    /**
     * 教务系统帐号
     */
	@DatabaseField
	private String studentSchoolUsername;
    /**
     * 教务系统密码
     */
	@DatabaseField
	private String studentSchoolPassword;
    /**
     * 记录修改时间
     */
	@DatabaseField
	private Timestamp studentGmtModified;
    /**
     * 记录创建时间
     */
	@DatabaseField
	private Timestamp studentGmtCreated;
    /**
     * 记录状态
     */
	@DatabaseField
	private String studentStatus;


	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentFacecode() {
		return studentFacecode;
	}

	public void setStudentFacecode(String studentFacecode) {
		this.studentFacecode = studentFacecode;
	}

	public String getStudentVoiceprintId() {
		return studentVoiceprintId;
	}

	public void setStudentVoiceprintId(String studentVoiceprintId) {
		this.studentVoiceprintId = studentVoiceprintId;
	}

	public String getStudentTel() {
		return studentTel;
	}

	public void setStudentTel(String studentTel) {
		this.studentTel = studentTel;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentNickname() {
		return studentNickname;
	}

	public void setStudentNickname(String studentNickname) {
		this.studentNickname = studentNickname;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentHeadimageUrl() {
		return studentHeadimageUrl;
	}

	public void setStudentHeadimageUrl(String studentHeadimageUrl) {
		this.studentHeadimageUrl = studentHeadimageUrl;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public String getStudentSchoolUsername() {
		return studentSchoolUsername;
	}

	public void setStudentSchoolUsername(String studentSchoolUsername) {
		this.studentSchoolUsername = studentSchoolUsername;
	}

	public String getStudentSchoolPassword() {
		return studentSchoolPassword;
	}

	public void setStudentSchoolPassword(String studentSchoolPassword) {
		this.studentSchoolPassword = studentSchoolPassword;
	}

	public Timestamp getStudentGmtModified() {
		return studentGmtModified;
	}

	public void setStudentGmtModified(Timestamp studentGmtModified) {
		this.studentGmtModified = studentGmtModified;
	}

	public Timestamp getStudentGmtCreated() {
		return studentGmtCreated;
	}

	public void setStudentGmtCreated(Timestamp studentGmtCreated) {
		this.studentGmtCreated = studentGmtCreated;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}


}
