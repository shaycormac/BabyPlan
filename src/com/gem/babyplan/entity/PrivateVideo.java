package com.gem.babyplan.entity;

import java.util.Date;

public class PrivateVideo {
	private int privateId;//班级视频id，主键
	
	private Classes classes;//班级
	
	private int privateAddress;//地点
	private Date privateTime;//时间
	private String privateVideoURL;//视频地址
	private String privateDescribe;//描述
	private String privateThumbnail;//班级视频缩略图

	public int getPrivateId() {
		return privateId;
	}
	public void setPrivateId(int privateId) {
		this.privateId = privateId;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public int getPrivateAddress() {
		return privateAddress;
	}
	public void setPrivateAddress(int privateAddress) {
		this.privateAddress = privateAddress;
	}
	public Date getPrivateTime() {
		return privateTime;
	}
	public void setPrivateTime(Date privateTime) {
		this.privateTime = privateTime;
	}
	public String getPrivateVideoURL() {
		return privateVideoURL;
	}
	public void setPrivateVideoURL(String privateVideoURL) {
		this.privateVideoURL = privateVideoURL;
	}
	public String getPrivateDescribe() {
		return privateDescribe;
	}
	public void setPrivateDescribe(String privateDescribe) {
		this.privateDescribe = privateDescribe;
	}
	public String getPrivateThumbnail() {
		return privateThumbnail;
	}
	public void setPrivateThumbnail(String privateThumbnail) {
		this.privateThumbnail = privateThumbnail;
	}
	
	@Override
	public String toString() {
		return "PrivateVideo [privateId=" + privateId + ", classes=" + classes + ", privateAddress=" + privateAddress
				+ ", privateTime=" + privateTime + ", privateVideoURL=" + privateVideoURL + ", privateDescribe="
				+ privateDescribe + ", privateThumbnail=" + privateThumbnail + "]";
	}

}
