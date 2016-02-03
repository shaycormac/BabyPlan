package com.gem.babyplan.entity;

import java.util.Date;

public class Photo {
	private int photoId;//照片id，主键
	
	private Album album;//相册
	
	private String photoURL;//照片URL
	private Date photoPublishTime;//上传时间
	private String photoDescribe;//照片描述
	
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	public Date getPhotoPublishTime() {
		return photoPublishTime;
	}
	public void setPhotoPublishTime(Date photoPublishTime) {
		this.photoPublishTime = photoPublishTime;
	}
	public String getPhotoDescribe() {
		return photoDescribe;
	}
	public void setPhotoDescribe(String photoDescribe) {
		this.photoDescribe = photoDescribe;
	}
	
	@Override
	public String toString() {
		return "Photo [photoId=" + photoId + ", album=" + album + ", photoURL=" + photoURL + ", photoPublishTime="
				+ photoPublishTime + ", photoDescribe=" + photoDescribe + "]";
	}
	
	
}
