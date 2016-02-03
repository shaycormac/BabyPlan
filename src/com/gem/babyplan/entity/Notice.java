package com.gem.babyplan.entity;

import java.util.Date;

public class Notice {
	private int noticeId;//班级通知id
	
	private Classes classes;//班级
	
	private String noticeText;//通知内容
	private Date noticeTime;//时间
	
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public String getNoticeText() {
		return noticeText;
	}
	public void setNoticeText(String noticeText) {
		this.noticeText = noticeText;
	}
	public Date getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	
	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", classes=" + classes + ", noticeText=" + noticeText + ", noticeTime="
				+ noticeTime + "]";
	}
	

}
