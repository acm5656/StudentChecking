package com.example.checkingsystem.entity;


import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 验证码对象
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-21
 */
//Name("t_tel_verify_code")
public class TelVerifyCode {

    private static final long serialVersionUID = 1L;
	public static final String STUDENT_REGISTER_VERIFYCODE_FUNC = "STUDENT_REGISTER_VERIFYCODE_FUNC";
	public static final String STUDENT_MODIFY_PWD_VERIFYCODE_FUNC = "STUDENT_MODIFY_PWD_VERIFYCODE_FUNC";
	public static final String TEACHER_REGISTER_VERIFYCODE_FUNC = "TEACHER_REGISTER_VERIFYCODE_FUNC";
	public static final String TEACHER_MODIFY_PWD_VERIFYCODE_FUNC = "TEACHER_MODIFY_PWD_VERIFYCODE_FUNC";
	public static final String STATUS_OLD = "old";
	public static final String STATUS_OK = "ok";
    /**
     * 主键-uuid
     */
	//Id("tel_verify_code_id")
	private String telVerifyCodeId;
    /**
     * 用户tel
     */
	//Field("tel_verify_code_tel")
	private String telVerifyCodeTel;
    /**
     * 验证码内容
     */
	//Field("tel_verify_code_context")
	private String telVerifyCodeContext;
    /**
     * 验证码功能
     */
	//Field("tel_verify_code_function")
	private String telVerifyCodeFunction;
    /**
     * model
     */
	//Field("tel_verify_code_model")
	private String telVerifyCodeModel;
    /**
     * requestID
     */
	//Field("tel_verify_code_request_id")
	private String telVerifyCodeRequestId;
    /**
     * 记录创建时间
     */
	//Field("tel_verify_code_gmt_created")
	private Timestamp telVerifyCodeGmtCreated;
    /**
     * 记录修改时间
     */
	//Field("tel_verify_code_gmt_modified")
	private Timestamp telVerifyCodeGmtModified;
    /**
     * 记录状态
     */
	//Field("tel_verify_code_status")
	private String telVerifyCodeStatus;


	public String getTelVerifyCodeId() {
		return telVerifyCodeId;
	}

	public void setTelVerifyCodeId(String telVerifyCodeId) {
		this.telVerifyCodeId = telVerifyCodeId;
	}

	public String getTelVerifyCodeTel() {
		return telVerifyCodeTel;
	}

	public void setTelVerifyCodeTel(String telVerifyCodeTel) {
		this.telVerifyCodeTel = telVerifyCodeTel;
	}

	public String getTelVerifyCodeContext() {
		return telVerifyCodeContext;
	}

	public void setTelVerifyCodeContext(String telVerifyCodeContext) {
		this.telVerifyCodeContext = telVerifyCodeContext;
	}

	public String getTelVerifyCodeFunction() {
		return telVerifyCodeFunction;
	}

	public void setTelVerifyCodeFunction(String telVerifyCodeFunction) {
		this.telVerifyCodeFunction = telVerifyCodeFunction;
	}

	public String getTelVerifyCodeModel() {
		return telVerifyCodeModel;
	}

	public void setTelVerifyCodeModel(String telVerifyCodeModel) {
		this.telVerifyCodeModel = telVerifyCodeModel;
	}

	public String getTelVerifyCodeRequestId() {
		return telVerifyCodeRequestId;
	}

	public void setTelVerifyCodeRequestId(String telVerifyCodeRequestId) {
		this.telVerifyCodeRequestId = telVerifyCodeRequestId;
	}

	public Timestamp getTelVerifyCodeGmtCreated() {
		return telVerifyCodeGmtCreated;
	}

	public void setTelVerifyCodeGmtCreated(Timestamp telVerifyCodeGmtCreated) {
		this.telVerifyCodeGmtCreated = telVerifyCodeGmtCreated;
	}

	public Timestamp getTelVerifyCodeGmtModified() {
		return telVerifyCodeGmtModified;
	}

	public void setTelVerifyCodeGmtModified(Timestamp telVerifyCodeGmtModified) {
		this.telVerifyCodeGmtModified = telVerifyCodeGmtModified;
	}

	public String getTelVerifyCodeStatus() {
		return telVerifyCodeStatus;
	}

	public void setTelVerifyCodeStatus(String telVerifyCodeStatus) {
		this.telVerifyCodeStatus = telVerifyCodeStatus;
	}


}
