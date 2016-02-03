package com.gem.babyplan.entity;

import java.util.Date;

public class Apply { 
	private int applyId;//申请id，主键
	
	private Parent applyParent;//申请家长
	private Parent beApplyParent;//被申请家长
	
	private String applyText;//申请内容
	private int applyStatus;//申请状态：0待审核、1未通过、2已通过
	private Date applyTime;//申请时间
	
	public int getApplyId() {
		return applyId;
	}
	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}
	public Parent getApplyParent() {
		return applyParent;
	}
	public void setApplyParent(Parent applyParent) {
		this.applyParent = applyParent;
	}
	public Parent getBeApplyParent() {
		return beApplyParent;
	}
	public void setBeApplyParent(Parent beApplyParent) {
		this.beApplyParent = beApplyParent;
	}
	public String getApplyText() {
		return applyText;
	}
	public void setApplyText(String applyText) {
		this.applyText = applyText;
	}
	public int getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(int applyStatus) {
		this.applyStatus = applyStatus;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	@Override
	public String toString() {
		return "Apply [applyId=" + applyId + ", applyParent=" + applyParent + ", beApplyParent=" + beApplyParent
				+ ", applyText=" + applyText + ", applyStatus=" + applyStatus + ", applyTime=" + applyTime + "]";
	}
	
	
}
