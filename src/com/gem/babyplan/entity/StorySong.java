package com.gem.babyplan.entity;

public class StorySong {
	private int ssId;//故事儿歌id，主键
	private String ssName;//名字
	private int ssType;//类型
	private String ssThumbnail;//缩略图
	private String ssURL;//地址
	
	public int getSsId() {
		return ssId;
	}
	public void setSsId(int ssId) {
		this.ssId = ssId;
	}
	public String getSsName() {
		return ssName;
	}
	public void setSsName(String ssName) {
		this.ssName = ssName;
	}
	public int getSsType() {
		return ssType;
	}
	public void setSsType(int ssType) {
		this.ssType = ssType;
	}
	public String getSsThumbnail() {
		return ssThumbnail;
	}
	public void setSsThumbnail(String ssThumbnail) {
		this.ssThumbnail = ssThumbnail;
	}
	public String getSsURL() {
		return ssURL;
	}
	public void setSsURL(String ssURL) {
		this.ssURL = ssURL;
	}
	
	@Override
	public String toString() {
		return "StorySong [ssId=" + ssId + ", ssName=" + ssName + ", ssType=" + ssType + ", ssThumbnail=" + ssThumbnail
				+ ", ssURL=" + ssURL + "]";
	}
	
	
}
