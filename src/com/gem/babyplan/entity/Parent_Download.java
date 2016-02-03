package com.gem.babyplan.entity;

import java.util.Date;

public class Parent_Download {
	private Parent parent;//家长
	private Download download;//下载
	private Date downloadTime;//下载时间
	
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public Download getDownload() {
		return download;
	}
	public void setDownload(Download download) {
		this.download = download;
	}
	public Date getDownloadTime() {
		return downloadTime;
	}
	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}
	
	@Override
	public String toString() {
		return "Parent_Download [parent=" + parent + ", download=" + download + ", downloadTime=" + downloadTime + "]";
	}

}
