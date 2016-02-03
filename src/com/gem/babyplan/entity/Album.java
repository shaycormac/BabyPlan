package com.gem.babyplan.entity;

import java.util.Date;

public class Album {
	private int albumId;//相册id，主键
	
	private Classes classes;//班级
	
	private String albumDescribe;//描述
	private String coverPaper;//封面URL
	private Date createTime;//创建时间
	
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public String getAlbumDescribe() {
		return albumDescribe;
	}
	public void setAlbumDescribe(String albumDescribe) {
		this.albumDescribe = albumDescribe;
	}
	public String getCoverPaper() {
		return coverPaper;
	}
	public void setCoverPaper(String coverPaper) {
		this.coverPaper = coverPaper;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", albumDescribe=" + albumDescribe + ", coverPaper=" + coverPaper
				+ ", createTime=" + createTime + "]";
	}
	
}
