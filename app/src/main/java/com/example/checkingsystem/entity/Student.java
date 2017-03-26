package com.example.checkingsystem.entity;

import com.j256.ormlite.field.DatabaseField;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 学生信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,一个生成全局唯一Sequence ID
     */
	//Id("student_id")
	@DatabaseField(id = true)
	private String studentId;
    /**
     * 外键-学生所属班级
     */
	//Field("student_class_id")
	@DatabaseField
	private String studentClassId;
    /**
     * 人脸验证id
     */
	//Field("student_facecode")
	@DatabaseField
	private String studentFacecode;
    /**
     * 声纹id
     */
	//Field("student_voiceprint_id")
	@DatabaseField
	private String studentVoiceprintId;
    /**
     * 学生手机号
     */
	//Field("student_tel")
	@DatabaseField
	private String studentTel;
    /**
     * 学生密码
     */
	//Field("student_password")
	@DatabaseField
	private String studentPassword;
    /**
     * 学生学号
     */
	//Field("student_no")
	@DatabaseField
	private String studentNo;
    /**
     * 学生真实姓名
     */
	//Field("student_name")
	@DatabaseField
	private String studentName;
    /**
     * 学生昵称
     */
	//Field("student_nickname")
	@DatabaseField
	private String studentNickname;
    /**
     * 学生email地址
     */
	//Field("student_email")
	@DatabaseField
	private String studentEmail;
    /**
     * 学生头像URL
     */
	//Field("student_headimage_url")
	@DatabaseField
	private String studentHeadimageUrl;
    /**
     * 学生性别
     */
	//Field("student_gender")
	@DatabaseField
	private String studentGender;
    /**
     * 教务系统帐号
     */
	//Field("student_school_username")
	@DatabaseField
	private String studentSchoolUsername;
    /**
     * 教务系统密码
     */
	//Field("student_school_password")
	@DatabaseField
	private String studentSchoolPassword;
    /**
     * 记录修改时间
     */
	//Field("student_gmt_modified")
	@DatabaseField
	private Timestamp studentGmtModified;
    /**
     * 记录创建时间
     */
	//Field("student_gmt_created")
	@DatabaseField
	private Timestamp studentGmtCreated;
    /**
     * 记录状态
     */
	//Field("student_status")
	@DatabaseField
	private String studentStatus;


	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentClassId() {
		return studentClassId;
	}

	public void setStudentClassId(String studentClassId) {
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

	@Override
	public String toString() {
		return "Student{" +
				"studentId='" + studentId + '\'' +
				", studentClassId='" + studentClassId + '\'' +
				", studentFacecode='" + studentFacecode + '\'' +
				", studentVoiceprintId='" + studentVoiceprintId + '\'' +
				", studentTel='" + studentTel + '\'' +
				", studentPassword='" + studentPassword + '\'' +
				", studentNo='" + studentNo + '\'' +
				", studentName='" + studentName + '\'' +
				", studentNickname='" + studentNickname + '\'' +
				", studentEmail='" + studentEmail + '\'' +
				", studentHeadimageUrl='" + studentHeadimageUrl + '\'' +
				", studentGender='" + studentGender + '\'' +
				", studentSchoolUsername='" + studentSchoolUsername + '\'' +
				", studentSchoolPassword='" + studentSchoolPassword + '\'' +
				", studentGmtModified=" + studentGmtModified +
				", studentGmtCreated=" + studentGmtCreated +
				", studentStatus='" + studentStatus + '\'' +
				'}';
	}
}
