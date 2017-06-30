package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-06-28
 */
//Name("t_virtual_grade")
public class VirtualGrade {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-成绩id
     */
	//Id("virtual_grade_id")
	private String virtualGradeId;
    /**
     * 外键-学生id
     */
	//Field("virtual_grade_stu_id")
	private String virtualGradeStuId;
    /**
     * 外键-课程id
     */
	//Field("virtual_grade_vcourse_id")
	private String virtualGradeVcourseId;
    /**
     * 经验值
     */
	//Field("virtual_grade_experience")
	private Double virtualGradeExperience;
    /**
     * 创建时间
     */
	//Field("virtual_grade_gmt_created")
	private Timestamp virtualGradeGmtCreated;
    /**
     * 修改时间
     */
	//Field("virtual_grade_gmt_modified")
	private Timestamp virtualGradeGmtModified;
    /**
     * 记录状态
     */
	//Field("virtual_grade_status")
	private String virtualGradeStatus;


	public String getVirtualGradeId() {
		return virtualGradeId;
	}

	public void setVirtualGradeId(String virtualGradeId) {
		this.virtualGradeId = virtualGradeId;
	}

	public String getVirtualGradeStuId() {
		return virtualGradeStuId;
	}

	public void setVirtualGradeStuId(String virtualGradeStuId) {
		this.virtualGradeStuId = virtualGradeStuId;
	}

	public String getVirtualGradeVcourseId() {
		return virtualGradeVcourseId;
	}

	public void setVirtualGradeVcourseId(String virtualGradeVcourseId) {
		this.virtualGradeVcourseId = virtualGradeVcourseId;
	}

	public Double getVirtualGradeExperience() {
		return virtualGradeExperience;
	}

	public void setVirtualGradeExperience(Double virtualGradeExperience) {
		this.virtualGradeExperience = virtualGradeExperience;
	}

	public Timestamp getVirtualGradeGmtCreated() {
		return virtualGradeGmtCreated;
	}

	public void setVirtualGradeGmtCreated(Timestamp virtualGradeGmtCreated) {
		this.virtualGradeGmtCreated = virtualGradeGmtCreated;
	}

	public Timestamp getVirtualGradeGmtModified() {
		return virtualGradeGmtModified;
	}

	public void setVirtualGradeGmtModified(Timestamp virtualGradeGmtModified) {
		this.virtualGradeGmtModified = virtualGradeGmtModified;
	}

	public String getVirtualGradeStatus() {
		return virtualGradeStatus;
	}

	public void setVirtualGradeStatus(String virtualGradeStatus) {
		this.virtualGradeStatus = virtualGradeStatus;
	}


}
