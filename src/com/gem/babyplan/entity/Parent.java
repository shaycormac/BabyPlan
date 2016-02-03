package com.gem.babyplan.entity;

public class Parent {
	private int parentId;//家长id，主键
	
	private Student student;//学生
	
	private String parentName;//姓名
	private String parentNickName;//昵称
	private String parentHeader;//头像
	private String parentPwd;//密码
	private String parentSex;//性别
	private String parentTelePhone;//联系方式
	private String address;//家庭住址
	private String backgroundPhoto;//背景图片
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentNickName() {
		return parentNickName;
	}
	public void setParentNickName(String parentNickName) {
		this.parentNickName = parentNickName;
	}
	public String getParentHeader() {
		return parentHeader;
	}
	public void setParentHeader(String parentHeader) {
		this.parentHeader = parentHeader;
	}
	public String getParentPwd() {
		return parentPwd;
	}
	public void setParentPwd(String parentPwd) {
		this.parentPwd = parentPwd;
	}
	public String getParentSex() {
		return parentSex;
	}
	public void setParentSex(String parentSex) {
		this.parentSex = parentSex;
	}
	public String getParentTelePhone() {
		return parentTelePhone;
	}
	public void setParentTelePhone(String parentTelePhone) {
		this.parentTelePhone = parentTelePhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBackgroundPhoto() {
		return backgroundPhoto;
	}
	public void setBackgroundPhoto(String backgroundPhoto) {
		this.backgroundPhoto = backgroundPhoto;
	}
	
	@Override
	public String toString() {
		return "Parent [parentId=" + parentId + ", student=" + student + ", parentName=" + parentName
				+ ", parentNickName=" + parentNickName + ", parentHeader=" + parentHeader + ", parentPwd=" + parentPwd
				+ ", parentSex=" + parentSex + ", parentTelePhone=" + parentTelePhone + ", address=" + address
				+ ", backgroundPhoto=" + backgroundPhoto + "]";
	}
	
	
}
