package com.gem.babyplan.entity;

import java.util.Date;

public class PublicVideo {
	private int publicId;//公共视频id，主键
	private int publicAddress;//地点
	private Date publicTime;//时间
	private String publicVideoURL;//视频地址
	private String publicDescribe;//描述
	private String publicThumbnail;//公共视频缩略图

	public int getPublicId() {
		return publicId;
	}
	public void setPublicId(int publicId) {
		this.publicId = publicId;
	}
	public int getPublicAddress() {
		return publicAddress;
	}
	public void setPublicAddress(int publicAddress) {
		this.publicAddress = publicAddress;
	}
	public Date getPublicTime() {
		return publicTime;
	}
	public void setPublicTime(Date publicTime) {
		this.publicTime = publicTime;
	}
	public String getPublicVideoURL() {
		return publicVideoURL;
	}
	public void setPublicVideoURL(String publicVideoURL) {
		this.publicVideoURL = publicVideoURL;
	}
	public String getPublicDescribe() {
		return publicDescribe;
	}
	public void setPublicDescribe(String publicDescribe) {
		this.publicDescribe = publicDescribe;
	}
	public String getPublicThumbnail() {
		return publicThumbnail;
	}
	public void setPublicThumbnail(String publicThumbnail) {
		this.publicThumbnail = publicThumbnail;
	}
	
	@Override
	public String toString() {
		return "PublicVideo [publicId=" + publicId + ", publicAddress=" + publicAddress + ", publicTime=" + publicTime
				+ ", publicVideoURL=" + publicVideoURL + ", publicDescribe=" + publicDescribe + ", publicThumbnail="
				+ publicThumbnail + "]";
	}

}
