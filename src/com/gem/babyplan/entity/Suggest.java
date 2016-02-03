package com.gem.babyplan.entity;

import java.util.Date;

public class Suggest {
	private int suggestId;//建议id，主键
	private String suggestText;//内容
	private Date suggestTime;//时间
	
	public int getSuggestId() {
		return suggestId;
	}
	public void setSuggestId(int suggestId) {
		this.suggestId = suggestId;
	}
	public String getSuggestText() {
		return suggestText;
	}
	public void setSuggestText(String suggestText) {
		this.suggestText = suggestText;
	}
	public Date getSuggestTime() {
		return suggestTime;
	}
	public void setSuggestTime(Date suggestTime) {
		this.suggestTime = suggestTime;
	}
	
	@Override
	public String toString() {
		return "Suggest [suggestId=" + suggestId + ", suggestText=" + suggestText + ", suggestTime=" + suggestTime
				+ "]";
	}
	
	
}
