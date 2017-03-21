package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * App版本信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_app_version")
public class AppVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequence-id
     */
	//Id("app_version_id")
	private String appVersionId;
    /**
     * 外键-app_id
     */
	//("app_version_app_id")
	private Long appVersionAppId;
    /**
     * 版本编号
     */
	//("app_version_code")
	private String appVersionCode;
    /**
     * 版本名
     */
	//("app_version_name")
	private String appVersionName;
    /**
     * 版本日志
     */
	//("app_version_log")
	private String appVersionLog;
    /**
     * 版本记录创建时间
     */
	//("app_version_fmt_created")
	private Timestamp appVersionFmtCreated;
    /**
     * 版本记录修改时间
     */
	//("app_version_fmt_modified")
	private Timestamp appVersionFmtModified;
    /**
     * 版本状态
     */
	//("app_version_status")
	private String appVersionStatus;


	public String getAppVersionId() {
		return appVersionId;
	}

	public void setAppVersionId(String appVersionId) {
		this.appVersionId = appVersionId;
	}

	public Long getAppVersionAppId() {
		return appVersionAppId;
	}

	public void setAppVersionAppId(Long appVersionAppId) {
		this.appVersionAppId = appVersionAppId;
	}

	public String getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(String appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public String getAppVersionName() {
		return appVersionName;
	}

	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}

	public String getAppVersionLog() {
		return appVersionLog;
	}

	public void setAppVersionLog(String appVersionLog) {
		this.appVersionLog = appVersionLog;
	}

	public Timestamp getAppVersionFmtCreated() {
		return appVersionFmtCreated;
	}

	public void setAppVersionFmtCreated(Timestamp appVersionFmtCreated) {
		this.appVersionFmtCreated = appVersionFmtCreated;
	}

	public Timestamp getAppVersionFmtModified() {
		return appVersionFmtModified;
	}

	public void setAppVersionFmtModified(Timestamp appVersionFmtModified) {
		this.appVersionFmtModified = appVersionFmtModified;
	}

	public String getAppVersionStatus() {
		return appVersionStatus;
	}

	public void setAppVersionStatus(String appVersionStatus) {
		this.appVersionStatus = appVersionStatus;
	}

}
