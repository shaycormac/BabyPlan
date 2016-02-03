package com.gem.babyplan.entity;

import java.util.Date;

public class Homework {
	private int hwId;//作业id，主键
	
	private Classes classes;//班级
	
	private String hwType;//作业类型
	private String hwTitle;//标题
	private String hwExplain;//说明
	private String hwPicture;//作业图片
	private Date hwPublishTime;//发布时间
	private Date hwEndTime;//截止时间
	private int hwStatus;//状态：0未完成、1已完成
	
	public int getHwId() {
		return hwId;
	}
	public void setHwId(int hwId) {
		this.hwId = hwId;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public String getHwType() {
		return hwType;
	}
	public void setHwType(String hwType) {
		this.hwType = hwType;
	}
	public String getHwTitle() {
		return hwTitle;
	}
	public void setHwTitle(String hwTitle) {
		this.hwTitle = hwTitle;
	}
	public String getHwExplain() {
		return hwExplain;
	}
	public void setHwExplain(String hwExplain) {
		this.hwExplain = hwExplain;
	}
	public String getHwPicture() {
		return hwPicture;
	}
	public void setHwPicture(String hwPicture) {
		this.hwPicture = hwPicture;
	}
	public Date getHwPublishTime() {
		return hwPublishTime;
	}
	public void setHwPublishTime(Date hwPublishTime) {
		this.hwPublishTime = hwPublishTime;
	}
	public Date getHwEndTime() {
		return hwEndTime;
	}
	public void setHwEndTime(Date hwEndTime) {
		this.hwEndTime = hwEndTime;
	}
	public int getHwStatus() {
		return hwStatus;
	}
	public void setHwStatus(int hwStatus) {
		this.hwStatus = hwStatus;
	}
	
	@Override
	public String toString() {
		return "Homework [hwId=" + hwId + ", classes=" + classes + ", hwType=" + hwType + ", hwTitle=" + hwTitle
				+ ", hwExplain=" + hwExplain + ", hwPublishTime=" + hwPublishTime
				+ ", hwEndTime=" + hwEndTime + ", hwStatus=" + hwStatus + "]";
	}
	
	
}
