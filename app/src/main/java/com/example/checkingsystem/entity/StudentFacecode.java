package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 学生人脸ID对应信息
学生通过此表与人脸ID能得到注册人脸的图片(此表只在注册时使用存于注册服务中)
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_student_facecode")
public class StudentFacecode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("student_facecode_id")
	private String studentFacecodeId;
    /**
     * 外键-student_id
     */
	//("student_facecode_stu_id")
	private Long studentFacecodeStuId;
    /**
     * 外键_picture_id
     */
	//("student_facecode_pic_id")
	private Long studentFacecodePicId;
    /**
     * 人脸ID-36位UUID
     */
	//("student_facecode")
	private String studentFacecode;
    /**
     * 记录创建时间
     */
	//("student_facecode_gmt_created")
	private Timestamp studentFacecodeGmtCreated;
    /**
     * 记录修改时间
     */
	//("student_facecode_gmt_modified")
	private Timestamp studentFacecodeGmtModified;
    /**
     * 记录状态
     */
	//("student_facecode_status")
	private String studentFacecodeStatus;


	public String getStudentFacecodeId() {
		return studentFacecodeId;
	}

	public void setStudentFacecodeId(String studentFacecodeId) {
		this.studentFacecodeId = studentFacecodeId;
	}

	public Long getStudentFacecodeStuId() {
		return studentFacecodeStuId;
	}

	public void setStudentFacecodeStuId(Long studentFacecodeStuId) {
		this.studentFacecodeStuId = studentFacecodeStuId;
	}

	public Long getStudentFacecodePicId() {
		return studentFacecodePicId;
	}

	public void setStudentFacecodePicId(Long studentFacecodePicId) {
		this.studentFacecodePicId = studentFacecodePicId;
	}

	public String getStudentFacecode() {
		return studentFacecode;
	}

	public void setStudentFacecode(String studentFacecode) {
		this.studentFacecode = studentFacecode;
	}

	public Timestamp getStudentFacecodeGmtCreated() {
		return studentFacecodeGmtCreated;
	}

	public void setStudentFacecodeGmtCreated(Timestamp studentFacecodeGmtCreated) {
		this.studentFacecodeGmtCreated = studentFacecodeGmtCreated;
	}

	public Timestamp getStudentFacecodeGmtModified() {
		return studentFacecodeGmtModified;
	}

	public void setStudentFacecodeGmtModified(Timestamp studentFacecodeGmtModified) {
		this.studentFacecodeGmtModified = studentFacecodeGmtModified;
	}

	public String getStudentFacecodeStatus() {
		return studentFacecodeStatus;
	}

	public void setStudentFacecodeStatus(String studentFacecodeStatus) {
		this.studentFacecodeStatus = studentFacecodeStatus;
	}

}
