package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 学生信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,一个生成全局唯一Sequence ID
     */
	//Id("student_id")
	private Long studentId;
    /**
     * 外键-学生所属班级
     */
	//("student_class_id")
	private Long studentClassId;
    /**
     * 人脸验证id
     */
	//("student_facecode")
	private String studentFacecode;
    /**
     * 声纹id
     */
	//("student_voiceprint_id")
	private String studentVoiceprintId;
    /**
     * 学生手机号
     */
	//("student_tel")
	private String studentTel;
    /**
     * 学生密码
     */
	//("student_password")
	private String studentPassword;
    /**
     * 学生学号
     */
	//("student_no")
	private String studentNo;
    /**
     * 学生真实姓名
     */
	//("student_name")
	private String studentName;
    /**
     * 学生昵称
     */
	//("student_nickname")
	private String studentNickname;
    /**
     * 学生email地址
     */
	//("student_email")
	private String studentEmail;
    /**
     * 学生头像URL
     */
	//("student_headimage_url")
	private String studentHeadimageUrl;
    /**
     * 学生性别
     */
	//("student_gender")
	private String studentGender;
    /**
     * 教务系统帐号
     */
	//("student_school_username")
	private String studentSchoolUsername;
    /**
     * 教务系统密码
     */
	//("student_school_password")
	private String studentSchoolPassword;
    /**
     * 记录修改时间
     */
	//("student_gmt_modified")
	private Timestamp studentGmtModified;
    /**
     * 记录创建时间
     */
	//("student_gmt_created")
	private Timestamp studentGmtCreated;
    /**
     * 记录状态
     */
	//("student_status")
	private String studentStatus;


	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getStudentClassId() {
		return studentClassId;
	}

	public void setStudentClassId(Long studentClassId) {
		this.studentClassId = studentClassId;
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
