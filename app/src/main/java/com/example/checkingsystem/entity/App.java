package com.example.checkingsystem.entity;

import java.sql.Timestamp;
//.mybatisplus.annotations.TableId;
//.mybatisplus.annotations.TableField;
//.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * App的基本信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-19
 */
//Name("t_app")
public class App implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-uuid-32
     */
	//Id("app_id")
	private String appId;
    /**
     * app名称
     */
	//Field("app_name")
	private String appName;
    /**
     * APP功能
     */
	//Field("app_function")
	private String appFunction;
    /**
     * APP用户指导
     */
	//Field("app_direction")
	private String appDirection;
    /**
     * APP服务条款
     */
	//Field("app_terms")
	private String appTerms;
    /**
     * APP版权信息
     */
	//Field("app_ copyright_info")
	private String appCopyrightInfo;
    /**
     * APP官方网站
     */
	//Field("app_website")
	private String appWebsite;
    /**
     * APP声明
     */
	//Field("app_statement")
	private String appStatement;
    /**
     * 记录创建时间
     */
	//Field("app_gmt_created")
	private Timestamp appGmtCreated;
    /**
     * 记录最新修改时间
     */
	//Field("app_gmt_modified")
	private Timestamp appGmtModified;
    /**
     * APP状态
     */
	//Field("app_status")
	private String appStatus;


	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppFunction() {
		return appFunction;
	}

	public void setAppFunction(String appFunction) {
		this.appFunction = appFunction;
	}

	public String getAppDirection() {
		return appDirection;
	}

	public void setAppDirection(String appDirection) {
		this.appDirection = appDirection;
	}

	public String getAppTerms() {
		return appTerms;
	}

	public void setAppTerms(String appTerms) {
		this.appTerms = appTerms;
	}

	public String getAppCopyrightInfo() {
		return appCopyrightInfo;
	}

	public void setAppCopyrightInfo(String appCopyrightInfo) {
		this.appCopyrightInfo = appCopyrightInfo;
	}

	public String getAppWebsite() {
		return appWebsite;
	}

	public void setAppWebsite(String appWebsite) {
		this.appWebsite = appWebsite;
	}

	public String getAppStatement() {
		return appStatement;
	}

	public void setAppStatement(String appStatement) {
		this.appStatement = appStatement;
	}

	public Timestamp getAppGmtCreated() {
		return appGmtCreated;
	}

	public void setAppGmtCreated(Timestamp appGmtCreated) {
		this.appGmtCreated = appGmtCreated;
	}

	public Timestamp getAppGmtModified() {
		return appGmtModified;
	}

	public void setAppGmtModified(Timestamp appGmtModified) {
		this.appGmtModified = appGmtModified;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

}
