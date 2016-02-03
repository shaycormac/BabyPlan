package com.gem.babyplan.entity;

import java.util.Date;

public class Dynamic {
	private int dynamicId;//动态id，主键
	
	private Parent parent;//家长
	
	private String dynamicText;//文字内容
	private String dynamicFile;//文件内容
	private Date dynamicPublishTime;//发表时间
	
	public int getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(int dynamicId) {
		this.dynamicId = dynamicId;
	}
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public String getDynamicText() {
		return dynamicText;
	}
	public void setDynamicText(String dynamicText) {
		this.dynamicText = dynamicText;
	}
	public String getDynamicFile() {
		return dynamicFile;
	}
	public void setDynamicFile(String dynamicFile) {
		this.dynamicFile = dynamicFile;
	}
	public Date getDynamicPublishTime() {
		return dynamicPublishTime;
	}
	public void setDynamicPublishTime(Date dynamicPublishTime) {
		this.dynamicPublishTime = dynamicPublishTime;
	}
	
	@Override
	public String toString() {
		return "Dynamic [dynamicId=" + dynamicId + ", parent=" + parent + ", dynamicText=" + dynamicText
				+ ", dynamicFile=" + dynamicFile + ", dynamicPublishTime=" + dynamicPublishTime + "]";
	}
	
	
}
