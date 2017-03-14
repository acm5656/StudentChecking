package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 记录每次考勤学生考勤验证的照片
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_student_face_verify_pic")
public class StudentFaceVerifyPic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("student_face_verify_pic_id")
	private Long studentFaceVerifyPicId;
    /**
     * 外键-student_id
     */
	//("student_face_verify_pic_stu_id")
	private Long studentFaceVerifyPicStuId;
    /**
     * 外键-picture_id
     */
	//("student_face_verify_pic_pic_id")
	private Long studentFaceVerifyPicPicId;
    /**
     * 记录创建时间
     */
	//("student_face_verify_pic_gmt_created")
	private Timestamp studentFaceVerifyPicGmtCreated;
    /**
     * 记录修改时间
     */
	//("student_face_verify_pic_gmt_modified")
	private Timestamp studentFaceVerifyPicGmtModified;
    /**
     * 记录的状态
     */
	//("student_face_verify_pic_status")
	private String studentFaceVerifyPicStatus;


	public Long getStudentFaceVerifyPicId() {
		return studentFaceVerifyPicId;
	}

	public void setStudentFaceVerifyPicId(Long studentFaceVerifyPicId) {
		this.studentFaceVerifyPicId = studentFaceVerifyPicId;
	}

	public Long getStudentFaceVerifyPicStuId() {
		return studentFaceVerifyPicStuId;
	}

	public void setStudentFaceVerifyPicStuId(Long studentFaceVerifyPicStuId) {
		this.studentFaceVerifyPicStuId = studentFaceVerifyPicStuId;
	}

	public Long getStudentFaceVerifyPicPicId() {
		return studentFaceVerifyPicPicId;
	}

	public void setStudentFaceVerifyPicPicId(Long studentFaceVerifyPicPicId) {
		this.studentFaceVerifyPicPicId = studentFaceVerifyPicPicId;
	}

	public Timestamp getStudentFaceVerifyPicGmtCreated() {
		return studentFaceVerifyPicGmtCreated;
	}

	public void setStudentFaceVerifyPicGmtCreated(Timestamp studentFaceVerifyPicGmtCreated) {
		this.studentFaceVerifyPicGmtCreated = studentFaceVerifyPicGmtCreated;
	}

	public Timestamp getStudentFaceVerifyPicGmtModified() {
		return studentFaceVerifyPicGmtModified;
	}

	public void setStudentFaceVerifyPicGmtModified(Timestamp studentFaceVerifyPicGmtModified) {
		this.studentFaceVerifyPicGmtModified = studentFaceVerifyPicGmtModified;
	}

	public String getStudentFaceVerifyPicStatus() {
		return studentFaceVerifyPicStatus;
	}

	public void setStudentFaceVerifyPicStatus(String studentFaceVerifyPicStatus) {
		this.studentFaceVerifyPicStatus = studentFaceVerifyPicStatus;
	}

}
