package com.example.checkingsystem.entity;


import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 记录每次考勤学生考勤验证的照片
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_student_face_verify_pic")
public class StudentFaceVerifyPic  {

    private static final long serialVersionUID = 1L;
	public static final String STATUS_INVALID = "invalid";//无效,被删除等
	public static final String STATUS_NEW = "new";//最新,当前使用的
	public static final String STATUS_OLD = "old";//原来用过的,被删除等

    /**
     * 主键-uuid-32
     */
	//Id("student_face_verify_pic_id")
	private String studentFaceVerifyPicId;
    /**
     * 外键-student_id
     */
	//Field("student_face_verify_pic_stu_id")
	private String studentFaceVerifyPicStuId;
    /**
     * 外键-picture_id
     */
	//Field("student_face_verify_pic_pic_id")
	private String studentFaceVerifyPicPicId;
    /**
     * 记录创建时间
     */
	//Field("student_face_verify_pic_gmt_created")
	private Timestamp studentFaceVerifyPicGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("student_face_verify_pic_gmt_modified")
	private Timestamp studentFaceVerifyPicGmtModified;
    /**
     * 记录的状态
     */
	//Field("student_face_verify_pic_status")
	private String studentFaceVerifyPicStatus;


	public String getStudentFaceVerifyPicId() {
		return studentFaceVerifyPicId;
	}

	public void setStudentFaceVerifyPicId(String studentFaceVerifyPicId) {
		this.studentFaceVerifyPicId = studentFaceVerifyPicId;
	}

	public String getStudentFaceVerifyPicStuId() {
		return studentFaceVerifyPicStuId;
	}

	public void setStudentFaceVerifyPicStuId(String studentFaceVerifyPicStuId) {
		this.studentFaceVerifyPicStuId = studentFaceVerifyPicStuId;
	}

	public String getStudentFaceVerifyPicPicId() {
		return studentFaceVerifyPicPicId;
	}

	public void setStudentFaceVerifyPicPicId(String studentFaceVerifyPicPicId) {
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
