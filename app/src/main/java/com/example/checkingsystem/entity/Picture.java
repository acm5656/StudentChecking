package com.example.checkingsystem.entity;

import java.sql.Timestamp;

import java.io.Serializable;


/**
 * <p>
 * 图片基本信息
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2017-03-13
 */
//Name("t_picture")
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键-sequenceid
     */
	//Id("picture_id")
	private String pictureId;
    /**
     * 图片URL
     */
	//("picture_url")
	private String pictureUrl;
    /**
     * 图片CDN加速地址
     */
	//("picture_cdn")
	private String pictureCdn;
    /**
     * 记录创建时间
     */
	//("picture_gmt_created")
	private Timestamp pictureGmtCreated;
    /**
     * 记录修改时间
     */
	//("picture_gmt_modified")
	private Timestamp pictureGmtModified;
    /**
     * 记录的状态
     */
	//("picture_status")
	private String pictureStatus;


	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getPictureCdn() {
		return pictureCdn;
	}

	public void setPictureCdn(String pictureCdn) {
		this.pictureCdn = pictureCdn;
	}

	public Timestamp getPictureGmtCreated() {
		return pictureGmtCreated;
	}

	public void setPictureGmtCreated(Timestamp pictureGmtCreated) {
		this.pictureGmtCreated = pictureGmtCreated;
	}

	public Timestamp getPictureGmtModified() {
		return pictureGmtModified;
	}

	public void setPictureGmtModified(Timestamp pictureGmtModified) {
		this.pictureGmtModified = pictureGmtModified;
	}

	public String getPictureStatus() {
		return pictureStatus;
	}

	public void setPictureStatus(String pictureStatus) {
		this.pictureStatus = pictureStatus;
	}

}
