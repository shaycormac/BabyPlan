package com.gem.babyplan.entity;

import java.util.Date;

public class Discuss {
	private int discussId;//评论id，主键
	
	private Discuss discuss;//父id，外键自连接:0一级评论
	private Dynamic dynamic;//动态
	private Parent parent;//家长
	
	private Date discussPublishTime;
	private String discussText;//内容
	private int isLast;//是否为最后一级:0最后一级、1不是最后一级
	
	public int getDiscussId() {
		return discussId;
	}
	public void setDiscussId(int discussId) {
		this.discussId = discussId;
	}
	public Discuss getDiscuss() {
		return discuss;
	}
	public void setDiscuss(Discuss discuss) {
		this.discuss = discuss;
	}
	public Dynamic getDynamic() {
		return dynamic;
	}
	public void setDynamic(Dynamic dynamic) {
		this.dynamic = dynamic;
	}
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public Date getDiscussPublishTime() {
		return discussPublishTime;
	}
	public void setDiscussPublishTime(Date discussPublishTime) {
		this.discussPublishTime = discussPublishTime;
	}
	public String getDiscussText() {
		return discussText;
	}
	public void setDiscussText(String discussText) {
		this.discussText = discussText;
	}
	public int getIsLast() {
		return isLast;
	}
	public void setIsLast(int isLast) {
		this.isLast = isLast;
	}
	
	@Override
	public String toString() {
		return "Discuss [discussId=" + discussId + ", discuss=" + discuss + ", dynamic=" + dynamic + ", parent="
				+ parent + ", discussPublishTime=" + discussPublishTime + ", discussText=" + discussText + ", isLast="
				+ isLast + "]";
	}
	
	
}
